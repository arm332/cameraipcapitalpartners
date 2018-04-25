<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="header.jsp" />
<!-- Page Content -->
<div class="container-fluid">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<h2>Login - Cameras - IP Capital Partners</h2>
			<br>
			<% if (request.getParameter("logout") != null) { %>
				<div class="alert alert-success">You have been logged out successfully.</div>
			<% } %>
			<% if (request.getParameter("error") != null) { %>
				<div class="alert alert-danger">User not registered. Please contact your administrator.</div>
			<% } %>
			<div class="bloco-login">
				<p class="text-center lead">Precisamos que você se autentique para continuar.</p>
				<p class="text-center"><a href="<%= request.getAttribute("loginUrl") %>" class="btn btn-danger btn-login">
					<span class="fab fa-google" style="margin-left: 1px"></span> Sign in with Google</a>
				</p>
			</div>
		</div>	
	</div>
</div>
<!-- /.container -->
<jsp:include page="footer.jsp" />
