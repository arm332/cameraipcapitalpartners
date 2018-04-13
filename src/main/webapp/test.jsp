<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.appengine.api.datastore.DatastoreServiceFactory"%>
<%@page import="com.google.appengine.api.datastore.DatastoreService"%>
<%@page import="com.google.appengine.api.datastore.FetchOptions"%>
<%@page import="com.google.appengine.api.datastore.Entity"%>
<%@page import="com.google.appengine.api.datastore.Query"%>
<%@page import="com.google.appengine.api.datastore.Key"%>
<%@page import="java.util.List"%>
<%@page contentType="application/json" %>
<%@page trimDirectiveWhitespaces="true" %>
<%
// Using low-level API just for learning purpose
DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
Entity project = new Entity("Project", "default");
Key projectKey = project.getKey();
Query query = new Query("Camera").setAncestor(projectKey);
List<Entity> list = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
Gson gson = new Gson();
out.print(gson.toJson(list));
%>