<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp" />
<!-- Page Content -->
<div class="container">

<c:if test="${list != null}">
	<!-- Page Heading -->
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				Documentos <small>Lista de documentos</small>
			</h1>
		</div>
	</div>
	<!-- /.row -->
	
	<div class="list-group">
		<c:forEach var="item" items="${list}">
			<a href="/document?load&id=${item.id}" 
				class="list-group-item">${item.name}</a>
		</c:forEach>
	</div>
</c:if>

<c:if test="${item != null}">
	<!-- Page Heading -->
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				${item.name}
			</h1>
		</div>
	</div>
	<!-- /.row -->

	<div class="row">
		<div class="col-lg-12">
			${item.description}
		</div>
	</div>
	<!-- /.row -->
</c:if>

</div>
<!-- /.container -->
<jsp:include page="footer.jsp" />
