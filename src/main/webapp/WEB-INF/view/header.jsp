<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<c:if test="${refresh}">
		<meta http-equiv="refresh" content="60">
	</c:if>
	<title>Câmeras IP Capital Partners</title>	
	<link rel="stylesheet" href="/css/bootstrap.min.css" type="text/css" />
	<link rel="stylesheet" href="/css/style.css" type="text/css" />
	<script src="/js/jquery.min.js" type="text/javascript"></script>
	<script src="/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="/js/script.js" type="text/javascript"></script>
</head>
<body>
	<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/">
					<img src="/logo.png" alt="IP Capital Partners">
				</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav navbar-right">
					<!-- <li><a href="/">Começo</a></li> --> <!-- class="active" -->
					<li><a href="/camera">Câmeras</a></li>
					<li><a href="/document">Documentos</a></li>
					<!-- <li><a href="/viewers">Usuários Ativos</a></li> -->
					<%--
					<c:if test="${sessionScope.status == 1}">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Admin <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="/admin/camera">Câmeras</a></li>
								<li><a href="/admin/document">Documentos</a></li>
								<!-- <li><a href="/admin/product">Produtos</a></li> -->
								<li><a href="/admin/profile">Usuários</a></li>
			                                </ul>
						</li>
					</c:if>
					--%>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${sessionScope.email} <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<c:if test="${sessionScope.status == 1}">
								<li class="dropdown-header">ADMINISTRAÇÃO</li>
								<li><a href="/admin/camera">Câmeras</a></li>
								<li><a href="/admin/document">Documentos</a></li>
								<!-- <li><a href="/admin/product">Produtos</a></li> -->
								<li><a href="/admin/profile">Usuários</a></li>
								<li role="separator" class="divider"></li>
							</c:if>
							<li><a href="#" data-href="/logout"
								data-message="Sair da Conta do Google?"
								data-toggle="modal" data-target="#confirm-dialog">Sair</a></li>
						</ul>
					</li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
