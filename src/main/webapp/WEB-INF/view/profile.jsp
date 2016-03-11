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
				Usuários <small>Lista de usuários</small>
			</h1>
		</div>
	</div>
	<!-- /.row -->

	<table class="table table-hover table-striped">
		<thead>
			<tr>
				<th>E-Mail</th>
				<th width="100%">Nome</th>
				<th class="text-center">Editar</th>
				<th class="text-center">Excluir</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}">
				<tr>
					<td>${item.email}</td>
					<td>${item.name}</td>
					<td><a href="/profile?load&email=${item.email}"
						class="btn btn-primary">Editar</a></td>
					<td><a href="/profile?delete&email=${item.email}"
						class="btn btn-danger" onclick="return confirm('Are you sure?')">Excluir</a></td>
					<!-- <td><a href="#" class="btn btn-danger"
						data-message="Excluir o item selecionado?"
						data-href="/profile?delete&email=${item.email}"
						data-toggle="modal" data-target="#confirm-dialog">Excluir</a></td> -->
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<p>
		<a href="/profile?load" class="btn btn-primary">Novo</a>
	</p>
</c:if>

<c:if test="${item != null}">
	<!-- Page Heading -->
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">
				Usuário <small>Detalhes do usuário</small>
			</h1>
		</div>
	</div>
	<!-- /.row -->

	<form method="POST" action="/profile?save" class="form-horizontal">
		<input type="hidden" name="old" value="${item.email}">
		<div class="form-group">
			<label for="email" class="col-md-2 control-label">E-Mail</label>
			<div class="col-md-10">
				<input type="text" class="form-control" id="email" name="email"
					placeholder="E-Mail" value="${item.email}">
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-md-2 control-label">Name</label>
			<div class="col-md-10">
				<input type="text" class="form-control" id="name" name="name"
					placeholder="Nome" value="${item.name}">
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
<jsp:include page="footer.jsp" />
