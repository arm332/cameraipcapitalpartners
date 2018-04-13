<%@page import="com.google.gson.Gson"%>
<%@page import="com.googlecode.objectify.ObjectifyService"%>
<%@page import="com.invprof.cameras.model.Camera"%>
<%@page import="java.util.List"%>
<%@page import="com.invprof.cameras.Constant"%>
<%@page import="com.invprof.cameras.model.Project"%>
<%@page import="com.googlecode.objectify.Key"%>
<%@page contentType="application/json" %>
<%@page trimDirectiveWhitespaces="true" %>
<%
Key<Project> parent = Key.create(Project.class, Constant.PROJECT_NAME);
List<Camera> list = ObjectifyService.ofy().load().type(Camera.class).ancestor(parent).order("position").list();
Gson gson = new Gson();
String json = gson.toJson(list);
out.print(json);
%>