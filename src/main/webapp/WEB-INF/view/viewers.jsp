<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp" />
<!-- Page Content -->
<div class="container">

<c:if test="${map != null}">
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
				<th>E-Mail</th>
				<th>Expira</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="entry" items="${map}">
				<tr>
					<td width="100%">${entry.key}</td>
					<td nowrap="nowrap">${entry.value}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>

</div>
<!-- /.container -->
<jsp:include page="footer.jsp" />
