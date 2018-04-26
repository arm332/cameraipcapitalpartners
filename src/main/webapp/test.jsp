<%@page import="com.google.gson.JsonElement"%>
<%@page import="com.google.gson.JsonParser"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.URLConnection"%>
<%@page import="java.net.URL"%>
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
//TODO: catch exceptions

String oper = (request.getParameter("oper") != null) ? request.getParameter("oper") : "null";
String idToken = (request.getParameter("id_token") != null) ? request.getParameter("id_token") : "null";
String eMail = (request.getParameter("email") != null) ? request.getParameter("email") : "null";

URL url = new URL("https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=" + idToken);
BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
String buffer = "";
String line;

while ((line = reader.readLine()) != null) {
    buffer += line;
}

reader.close();
JsonParser parser = new JsonParser();
JsonElement element = parser.parse(buffer);
JsonObject tokenInfo = element.getAsJsonObject();

out.print(tokenInfo.toString());

String aud = tokenInfo.has("aud") ? tokenInfo.get("aud").toString() : null;
String iss = tokenInfo.has("iss") ? tokenInfo.get("iss").toString() : null;
String exp = tokenInfo.has("exp") ? tokenInfo.get("exp").toString() : null;
String sub = tokenInfo.has("sub") ? tokenInfo.get("sub").toString() : null;
String email = tokenInfo.has("email") ? tokenInfo.get("email").toString() : null;

// TODO: check aud == CLIENT_ID
// TODO: check iss == "https://accounts.google.com"
// TODO: check exp == valid expired time
// TODO: check email with database
// TODO: sub = user's unique Google ID

if ("cameras".equals(oper)) {
	Key<Project> parent = Key.create(Project.class, Constant.PROJECT_NAME);
	List<Camera> list = ObjectifyService.ofy().load().type(Camera.class).ancestor(parent).order("position").list();
	Gson gson = new Gson();
	String json = gson.toJson(list);
	out.print(json);
	return;
}

out.print("{'error':'Operation not found'}");
%>