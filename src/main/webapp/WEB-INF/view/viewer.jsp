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
				Usuários Ativos <small>Lista de usuários ativos</small>
			</h1>
		</div>
	</div>
	<!-- /.row -->

	<table class="table table-hover table-striped">
		<thead>
			<tr>
				<th>Sessão</th>
				<th>E-Mail</th>
				<th>Expira</th>
				<th>Status</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}">
				<tr>
					<td>${item.key}</td>
					<td width="100%">${item.email}</td>
					<td nowrap="nowrap">${item.expires}</td>
					<td align="center">${item.status}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>

</div>
<!-- /.container -->
<jsp:include page="footer.jsp" />
