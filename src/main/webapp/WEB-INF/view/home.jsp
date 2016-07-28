<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="header.jsp" />
<!-- Page Content -->
<div class="container">

<c:if test="${list != null}">

    <!-- Page Heading -->
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">
                Começo <small>Lista de opções</small>
            </h1>
        </div>
    </div>
    <!-- /.row -->

    <div class="row">
       	<div class="col-lg-12">
       		<div class="list-group">
       			<a href="/camera" class="list-group-item">
		       		<span class="glyphicon glyphicon-facetime-video" aria-hidden="true"></span> Câmeras
		        </a>
       			<a href="/document" class="list-group-item">
		       		<span class="glyphicon glyphicon-file" aria-hidden="true"></span> Documentos
		        </a>
			</div>
       	</div>
	</div>
    <!-- /.row -->
    
    <div class="row">
        <div class="col-lg-12">
            <h3>
                Documentos <small>Lista de documentos</small>
            </h3>
        </div>
    </div>
	<!-- /.row -->
	
    <div class="row">
		<div class="col-lg-12">
    		<div class="list-group">
		        <c:forEach var="item" items="${list}">
		        	<a href="/document?load&id=${item.id}" class="list-group-item">
			        	<span class="glyphicon glyphicon-file" aria-hidden="true"></span> ${item.name}
		        	</a>
		        </c:forEach>
			</div>
		</div>
    </div>
    <!-- /.row -->

</c:if>

</div>
<!-- /.container -->
<jsp:include page="footer.jsp" />
