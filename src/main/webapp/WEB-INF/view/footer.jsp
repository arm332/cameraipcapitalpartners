<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Calendar"%>

	<!-- Footer -->
    <footer>
		<c:if test="${not empty sessionScope.status}">
		<div class="container">
	        <div class="row">
	            	<hr />
	                <div class="col-md-6">
	                    <p>Copyright &copy; <%= Calendar.getInstance().get(Calendar.YEAR) %>, IP Capital Partners. Todos os direitos reservados.</p>
	                </div>
	                <div class="col-md-6">
	                	<p class="text-right" id="viewers"></p>
	                </div>
            </div>
            <!-- /.row -->
		</div> 
		<!-- /.container -->	
        </c:if>
    </footer>
	
	<!-- Generic confirmation dialog -->
	<div class="modal fade" id="confirm-dialog" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">Confirmar</h4>
				</div>
				<div class="modal-body">
					<p>Você tem certeza?</p>
				</div>
				<div class="modal-footer">
					<a class="btn btn-primary">Confirmar</a>
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
				</div>
			</div>
		</div>
	</div>
	<!-- /.modal -->
</body>
</html>
