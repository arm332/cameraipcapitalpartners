<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="header.jsp" />
<!-- Page Content -->
<div class="container-fluid">

<c:if test="${list != null}">

    <!-- Page Heading -->
    <div class="row">
        <div class="col-lg-12">
	        <div class="pull-right">
				<div class="btn-group" role="group">
				    <button type="button" class="btn btn-default dropdown-toggle"
				        aria-haspopup="true" aria-expanded="false"
				        data-toggle="dropdown" title="Atualizar tudo a cada">Intervalo
				        	<span class="caret"></span>
				    </button>
				    <ul class="dropdown-menu pull-right">
				        <!-- <li><a href="javascript:void(0)">1 s</a></li> -->
				        <li><a href="javascript:void(0)">2 s</a></li>
				        <li><a href="javascript:void(0)">5 s</a></li>
				        <li><a href="javascript:void(0)">10 s</a></li>
				        <li><a href="javascript:void(0)">30 s</a></li>
				        <li><a href="javascript:void(0)">60 s</a></li>
				        <!-- <li><a href="javascript:void(0)">Vídeo</a></li> -->
				    </ul>
				</div>
	        </div>
            <h1 class="page-header">
                Home <small>Câmeras</small>
            </h1>
        </div>
    </div>
    <!-- /.row -->

    <div class="row">
        <c:forEach var="item" items="${list}">
        
            <!-- Camera ${item} -->
            <div class="cam col-lg-3 col-md-4 col-sm-6 col-xs-12">
                <div class="btn-group" role="group">
                    <div class="btn-group" role="group">
                        <button type="button" class="btn btn-default dropdown-toggle"
                            aria-haspopup="true" aria-expanded="false"
                            data-toggle="dropdown" title="Atualizar a cada">
                            ${item.interval} ${not empty item.interval ? 's' : 'Vídeo'}
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                            <!-- <li><a href="javascript:void(0)">1 s</a></li> -->
                            <li><a href="javascript:void(0)">2 s</a></li>
                            <li><a href="javascript:void(0)">5 s</a></li>
                            <li><a href="javascript:void(0)">10 s</a></li>
                            <li><a href="javascript:void(0)">30 s</a></li>
                            <li><a href="javascript:void(0)">60 s</a></li>
                            <!-- <li><a href="javascript:void(0)">Vídeo</a></li> -->
                        </ul>
                    </div>

                    <button type="button" class="btn btn-default"
                        data-toggle="popover" title="Skype">
                        <span class="glyphicon glyphicon-comment"
                            aria-hidden="true"></span>
                    </button>

                    <button type="button" class="btn btn-default"
                        data-toggle="modal" data-target="#cam-dialog"
                        data-title="${item.title}" title="Ampliar">
                        <span class="glyphicon glyphicon-fullscreen"
                            aria-hidden="true"></span>
                    </button>
                </div>

                <h4>${item.title}</h4>

                <img src="image320x240.png" alt="${item.title}" 
                	class="center-block img-responsive" id="img-${item.id}" 
                	data-interval="${item.interval}" data-url="${item.url}">

                <ul class="list-unstyled collapse">
                    <%--http://stackoverflow.com/a/58060 --%>
                    <% pageContext.setAttribute("nl", "\n"); %>
                    <c:set var="lines" value="${fn:split(item.description, nl)}" />
                    <c:forEach var="line" items="${lines}">
                        <c:set var="tokens" value="${fn:split(line, '|')}" />
                        <c:set var="token" value="${fn:trim(tokens[0])}" />
                        <%-- Remove empty lines --%>
                        <c:if test="${not empty token}">
                            <li class="text-nowrap">
                                ${token}
                                <c:if test="${fn:length(tokens) > 1}">
                                    <c:set var="token" value="${fn:trim(tokens[1])}" />
                                    | <a href="skype:${token}?call">Call</a>
                                    | <a href="skype:${token}?chat">Chat</a>
                                </c:if>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>
            
        </c:forEach>
    </div>
    <!-- /.row -->

    <!-- Generic camera dialog -->
    <div class="modal fade" id="cam-dialog" tabindex="-1" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Ampliar</h4>
                </div>
                <div class="modal-body">
                    <img src="image320x240.png" alt="..." class="center-block img-responsive" id="img-dialog" />
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
                </div>
            </div>
        </div>
    </div>
    <!-- /.modal -->

</c:if>

</div>
<!-- /.container -->
<jsp:include page="footer.jsp" />
