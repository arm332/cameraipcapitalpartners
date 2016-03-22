<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>IP CAPITAL PARTNERS</title>
	<link rel="stylesheet" href="/css/bootstrap.min.css" type="text/css" />
	<link rel="stylesheet" href="/css/style.css" type="text/css" />
	<script src="/js/jquery.min.js" type="text/javascript"></script>
	<script src="/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="/js/script.js" type="text/javascript"></script>
</head>
<body>
		<!-- Page Heading -->
		<div class="page-center">
			<!-- <h2>IP CAPITAL PARTNERS</h2> -->
			<div class="login-logo"><img src="logobig.png" alt="IP CAPITAL PARTNERS" width="100%"></div>
			<% if (request.getParameter("logout") != null) { %>
				<div class="alert alert-success">You have been logged out successfully.</div>
			<% } %>
			<% if (request.getParameter("error") != null) { %>
				<div class="alert alert-danger">User not registered. Please contact your administrator.</div>
			<% } %>
			<div><a href="<%= request.getAttribute("loginUrl") %>" class="btn btn-block btn-danger">Sign in with Google</a></div>
		</div>
		<!-- /.row -->
</body>
</html>