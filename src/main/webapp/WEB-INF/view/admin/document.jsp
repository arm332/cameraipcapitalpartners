<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header.jsp" />
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

	<table class="table table-hover table-striped">
		<thead>
			<tr>
				<th>Posição</th>
				<th width="100%">Nome</th>
				<!-- <th class="text-center">Download</th> -->
				<th class="text-center">Editar</th>
				<th class="text-center">Excluir</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}">
				<tr>
					<td class="text-center">${item.position}</td>
					<td>${item.name}</td>
					<!-- <td><a href="/admin/document?download&id=${item.id}"
						class="btn btn-default">Download</a></td> -->
					<td><a href="/admin/document?load&id=${item.id}"
						class="btn btn-primary">Editar</a></td>
					<td><a href="/admin/document?delete&id=${item.id}"
						class="btn btn-danger" onclick="return confirm('Are you sure?')">Excluir</a></td>
					<!-- <td><a href="#" class="btn btn-danger"
						data-message="Excluir o item selecionado?"
						data-href="/admin/profile?delete&email=${item.id}"
						data-toggle="modal" data-target="#confirm-dialog">Excluir</a></td> -->
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<p>
		<a href="/admin/document?load" class="btn btn-primary">Novo</a>
	</p>
</c:if>

<c:if test="${item != null}">
	<!-- Page Heading -->
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				Documento <small>Detalhes do document</small>
			</h1>
		</div>
	</div>
	<!-- /.row -->

	<form method="POST" action="/admin/document?save" class="form-horizontal" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${item.id}">
		<div class="form-group">
			<label for="name" class="col-md-2 control-label">Nome</label>
			<div class="col-md-10">
				<input type="text" class="form-control" id="name" name="name"
					placeholder="Nome" value="${item.name}">
			</div>
		</div>
		<div class="form-group">
			<label for="description" class="col-md-2 control-label">Descrição</label>
			<div class="col-md-10">
				<textarea class="form-control" id="description" name="description"
					rows="5">${item.description}</textarea>
			</div>
		</div>
		<div class="form-group">
			<label for="file" class="col-md-2 control-label">Arquivo</label>
			<div class="col-md-10">
				<input type="file" id="file" name="file">
			</div>
		</div>
		<div class="form-group">
			<label for="position" class="col-md-2 control-label">Posição</label>
			<div class="col-md-1">
				<input type="text" class="form-control" id="position"
					name="position" placeholder="Posição" value="${item.position}">
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
