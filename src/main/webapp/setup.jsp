<%@page import="com.google.appengine.api.datastore.Query"%>
<%@page import="com.google.appengine.api.datastore.PreparedQuery"%>
<%@page import="com.google.appengine.api.datastore.DatastoreServiceFactory"%>
<%@page import="com.google.appengine.api.datastore.DatastoreService"%>
<%@page import="com.google.appengine.api.datastore.Entity"%>
<%@page import="com.google.appengine.api.datastore.Key"%>
<%@page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@page import="com.google.appengine.api.datastore.Transaction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// Using low-level API just for learning purpose
DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
Transaction txn = datastore.beginTransaction();

try {
	Entity project = new Entity("Project", "default");
    datastore.put(txn, project);
    Key projectKey = project.getKey();
    
    final String[] profiles = {"camera@invprof.com.br"};
    
    for (int i = 0; i < profiles.length; i++) {
	    Entity entity = new Entity("Profile", profiles[i], projectKey);
	    entity.setUnindexedProperty("name", profiles[i]);
	    entity.setUnindexedProperty("status", 1);
	    datastore.put(txn, entity);
    }
    /*
    final int CAMERA_COUNT = 9;
    
    for (int i = 0; i < CAMERA_COUNT; i++) {
        Entity entity = new Entity("Camera", (i + 1), projectKey);
        entity.setUnindexedProperty("title", "Camera " + (i + 1));
        entity.setUnindexedProperty("description", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Laudantium veniam exercitationem expedita laborum at voluptate. Labore, voluptates totam at aut nemo deserunt rem magni pariatur quos perspiciatis atque eveniet unde.");
        entity.setUnindexedProperty("url", "placeholder.png");
        entity.setUnindexedProperty("interval", 0);
        entity.setProperty("position", (i + 1));
        datastore.put(txn, entity);
    }
    
    final String[] products = {"Um", "Dois", "Três", "Quatro", "Cinco"};
    
    for (int i = 0; i < products.length; i++) {
        Entity entity = new Entity("Product", "P" + (i + 1), projectKey);
        entity.setUnindexedProperty("title", "Produto " + products[i]);
        entity.setUnindexedProperty("subtitle", "Subtítulo");
        entity.setUnindexedProperty("description", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Laudantium veniam exercitationem expedita laborum at voluptate. Labore, voluptates totam at aut nemo deserunt rem magni pariatur quos perspiciatis atque eveniet unde.");
        entity.setUnindexedProperty("url", "http://placehold.it/320x240");
        entity.setProperty("position", (i + 1)); 
        datastore.put(txn, entity);
    }
    */
    txn.commit();
} finally {
    if (txn.isActive()) {
        txn.rollback();
    }
}
%>