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
				ACME <small>Lista de autenticações ACME</small>
			</h1>
		</div>
	</div>
	<!-- /.row -->

	<table class="table table-hover table-striped">
		<thead>
			<tr>
				<th width="100%">Domínio</th>
				<!-- <th class="text-center">Download</th> -->
				<th class="text-center">Editar</th>
				<th class="text-center">Excluir</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}">
				<tr>
					<td>${item.domain}</td>
					<td><a href="/admin/acme?load&id=${item.id}"
						class="btn btn-primary">Editar</a></td>
					<td><a href="/admin/acme?delete&id=${item.id}"
						class="btn btn-danger" onclick="return confirm('Are you sure?')">Excluir</a></td>
					<!-- <td><a href="#" class="btn btn-danger"
						data-message="Excluir o item selecionado?"
						data-href="/admin/acme?delete&email=${item.id}"
						data-toggle="modal" data-target="#confirm-dialog">Excluir</a></td> -->
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<p>
		<a href="/admin/acme?load" class="btn btn-primary">Novo</a>
	</p>
</c:if>

<c:if test="${item != null}">
	<!-- Page Heading -->
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				ACME <small>Detalhes da autenticação ACME</small>
			</h1>
		</div>
	</div>
	<!-- /.row -->

	<form method="POST" action="/admin/acme?save" class="form-horizontal">
		<input type="hidden" name="id" value="${item.id}">
		<div class="form-group">
			<label for="domain" class="col-md-2 control-label">Domínio</label>
			<div class="col-md-10">
				<input type="text" class="form-control" id="domain" name="domain"
					placeholder="Domínio" value="${item.domain}">
			</div>
		</div>
		<div class="form-group">
			<label for="token" class="col-md-2 control-label">Token</label>
			<div class="col-md-10">
				<textarea class="form-control" id="token" name="token"
					rows="5" placeholder="Token">${item.token}</textarea>
			</div>
		</div>
		<div class="form-group">
			<label for="auth" class="col-md-2 control-label">Autenticação</label>
			<div class="col-md-10">
				<textarea class="form-control" id="auth" name="auth"
					rows="5" placeholder="Autenticação">${item.auth}</textarea>
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
