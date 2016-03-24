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
				Câmeras <small>Lista de câmeras</small>
			</h1>
		</div>
	</div>
	<!-- /.row -->

	<table class="table table-hover table-striped">
		<thead>
			<tr>
				<!-- <th>Código</th> -->
				<th>Posição</th>
				<th width="100%">Título</th>
				<th class="text-center">Editar</th>
				<th class="text-center">Excluir</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}">
				<tr>
					<!-- <td>${item.id}</td> -->
					<td class="text-center">${item.position}</td>
					<td>${item.title}</td>
					<td><a href="/admin/camera?load&id=${item.id}"
						class="btn btn-primary">Editar</a></td>
					<td><a href="/admin/camera?delete&id=${item.id}"
						class="btn btn-danger" onclick="return confirm('Are you sure?')">Excluir</a></td>
					<!-- <td><a href="#" class="btn btn-danger"
						data-message="Excluir o item selecionado?"
						data-href="/admin/camera?delete&id=${item.id}"
						data-toggle="modal" data-target="#confirm-dialog">Excluir</a></td> -->
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<p>
		<a href="/admin/camera?load" class="btn btn-primary">Novo</a>
	</p>

</c:if>

<c:if test="${item != null}">
	<!-- Page Heading -->
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				Câmera <small>Detalhes da câmeras</small>
			</h1>
		</div>
	</div>
	<!-- /.row -->

	<form method="POST" action="/admin/camera?save" class="form-horizontal">
		<input type="hidden" name="id" value="${item.id}">
		<!-- <div class="form-group">
			<label class="col-md-2 control-label">Código</label>
			<div class="col-md-10">
				<p class="form-control-static">${item.id}</p>
			</div>
		</div> -->
		<div class="form-group">
			<label for="title" class="col-md-2 control-label">Título</label>
			<div class="col-md-10">
				<input type="text" class="form-control" id="title" name="title"
					placeholder="Título" value="${item.title}">
			</div>
		</div>
		<div class="form-group">
			<label for="description" class="col-md-2 control-label">Tooltip</label>
			<div class="col-md-10">
				<textarea class="form-control" id="description" name="description"
					rows="5">${item.description}</textarea>
			</div>
		</div>
		<div class="form-group">
			<label for="url" class="col-md-2 control-label">Url</label>
			<div class="col-md-10">
				<input type="text" class="form-control" id="url" name="url"
					placeholder="URL" value="${item.url}">
			</div>
		</div>
		<div class="form-group">
			<label for="interval" class="col-md-2 control-label">Intervalo</label>
			<div class="col-md-1">
				<input type="text" class="form-control" id="interval"
					name="interval" placeholder="Intervalo" value="${item.interval}">
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
