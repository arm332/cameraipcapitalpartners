<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../header.jsp" />
<!-- Page Content -->
<div class="container">

<c:if test="${list != null}">
	<!-- Page Heading -->
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				Log <small>Lista de registros de eventos</small>
			</h1>
		</div>
	</div>
	<!-- /.row -->

	<table class="table table-hover table-striped">
		<thead>
			<tr>
				<th>Data</th>
				<th width="100%">E-Mail</th>
				<!-- <th>Notas</th>
				<th class="text-center">Editar</th>
				<th class="text-center">Excluir</th> -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}">
				<tr>
					<td class="text-nowrap"><fmt:formatDate type="both" 
						pattern="dd/MM/yyyy HH:mm:ss" timeZone="GMT-3" 
						value="${item.date}"/></td>
					<td>${item.email}</td>
					<!-- <td>${item.notes}</td>
					<td><a href="/admin/log?load&id=${item.id}"
						class="btn btn-primary">Editar</a></td>
					<td><a href="/admin/log?delete&id=${item.id}"
						class="btn btn-danger" onclick="return confirm('Are you sure?')">Excluir</a></td> -->
					<!-- <td><a href="#" class="btn btn-danger"
						data-message="Excluir o item selecionado?"
						data-href="/admin/log?delete&email=${item.id}"
						data-toggle="modal" data-target="#confirm-dialog">Excluir</a></td> -->
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<p>
		<!-- <a href="/admin/log?load" class="btn btn-primary">Novo</a> -->
		<a href="/admin/log?download" class="btn btn-primary">Exportar</a>
	</p>
</c:if>

<c:if test="${item != null}">
	<!-- Page Heading -->
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				Log <small>Detalhes do registro de evento</small>
			</h1>
		</div>
	</div>
	<!-- /.row -->

	<form method="POST" action="/admin/log?save" class="form-horizontal">
		<input type="hidden" name="id" value="${item.id}">
		<div class="form-group">
			<label for="date" class="col-md-2 control-label">Data</label>
			<div class="col-md-10">
				<input type="text" class="form-control" id="date" name="date"
					placeholder="Data" value="<fmt:formatDate type="both" 
						pattern="dd/MM/yyyy HH:mm:ss" timeZone="GMT-3"
						value="${item.date}"/>">
			</div>
		</div>
		<div class="form-group">
			<label for="email" class="col-md-2 control-label">E-Mail</label>
			<div class="col-md-10">
				<input type="text" class="form-control" id="email" name="email"
					placeholder="E-Mail" value="${item.email}">
			</div>
		</div>
		<div class="form-group">
			<label for="notes" class="col-md-2 control-label">Notas</label>
			<div class="col-md-10">
				<textarea class="form-control" id="notes" name="notes"
					rows="5" placeholder="Notas">${item.notes}</textarea>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-offset-2 col-md-10">
				<button type="submit" class="btn btn-primary">Salvar</button>
				<a href="javascript:history.back();" class="btn btn-default">Voltar</a>
			</div>
		</div>
	</form>
</c:if>

</div>
<!-- /.container -->
<jsp:include page="../footer.jsp" />
