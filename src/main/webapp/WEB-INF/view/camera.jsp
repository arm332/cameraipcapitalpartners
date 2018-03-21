<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="header.jsp" />

<!-- Page Content -->
<div class="container">
    <!-- Page Heading -->
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">
                Câmeras <small>Lista de câmeras</small>
            </h1>
        </div>
    </div>
    <!-- /.row -->
</div>

<c:if test="${list != null}">

<div class="container-fluid">
    <div class="row">
        <c:forEach var="item" items="${list}" varStatus="status">
			<c:set var="label" value="Vídeo"></c:set>
			<c:set var="state" value="disabled"></c:set>
            <c:if test="${item.interval > 0}">
	            <c:set var="label" value="${item.interval} s"></c:set>
    	        <c:set var="state" value=""></c:set>
            </c:if>
        
            <!-- Camera ${item} -->
            <!-- <div class="cam col-lg-3 col-md-4 col-sm-6 col-xs-12"> -->
            <div class="cam col-lg-4 col-md-6 col-sm-12">
                <div class="btn-group" role="group">
                    <div class="btn-group" role="group">
                        <button type="button" class="btn btn-default dropdown-toggle ${state}"
                            aria-haspopup="true" aria-expanded="false"
                            data-toggle="dropdown" title="Atualizar a cada">
                            <%-- ${item.interval} ${item.interval > 0 ? 's' : 'Vídeo'} --%>
                            ${label} <span class="caret"></span>
                        </button>
                        <c:if test="${item.interval > 0}">
	                        <ul class="dropdown-menu">
	                            <!-- <li><a href="javascript:void(0)">1 s</a></li> -->
	                            <li><a href="javascript:void(0)">2 s</a></li>
	                            <li><a href="javascript:void(0)">5 s</a></li>
	                            <li><a href="javascript:void(0)">10 s</a></li>
	                            <li><a href="javascript:void(0)">30 s</a></li>
	                            <li><a href="javascript:void(0)">60 s</a></li>
	                            <!-- <li><a href="javascript:void(0)">Vídeo</a></li> -->
	                        </ul>
                        </c:if>
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

				<c:if test="${item.interval > 0}">
	                <img src="placeholder.png" alt="${item.title}" 
    	            	id="img-${item.id}" data-interval="${item.interval}" 
        	        	data-url="${item.url}">
				</c:if>
				<c:if test="${item.interval == 0}">
	                <img src="${item.url}" alt="${item.title}" 
    	            	id="img-${item.id}" data-interval="${item.interval}" 
        	        	data-url="${item.url}">
				</c:if>
				<c:if test="${item.interval < 0}">
					<!-- <video src="${item.url}" type="video/ogg; codecs=theora" 
						autoplay="autoplay" controls="controls"
						poster="placeholder.png" width="100%"
						onclick="this.play();"></video> -->
					<video id="cam${item.id}" autoplay="autoplay" controls="controls" poster="placeholder.png" width="100%">
						<source src="${item.url}" type="video/ogg; codecs=theora">
					</video>
				</c:if>
				
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
                                    <%-- https://stackoverflow.com/a/11598961 --%>
                                    <!-- - <a href="skype:${token}?call">Call</a>
                                    - <a href="skype:${token}?chat">Chat</a> -->
                                    <%-- https://stackoverflow.com/a/8852679 --%>
                                    - <a href="https://mail.google.com/mail/?view=cm&fs=1&to=${token}
                                    ">GMail</a>
                                </c:if>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>
            
            <c:if test="${status.count % 3 == 0}">
                <div class="clearfix visible-lg"></div>
            </c:if>

            <c:if test="${status.count % 2 == 0}">
                <div class="clearfix visible-md"></div>
            </c:if>
        </c:forEach>
    </div>
    <!-- /.row -->

    <!-- Generic camera dialog -->
    <div class="modal fade" id="cam-dialog" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-md">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Ampliar</h4>
                </div>
                <div class="modal-body">
                    <img src="placeholder.png" alt="Ampliar" id="img-dialog" />
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
                </div>
            </div>
        </div>
    </div>
    <!-- /.modal -->
</div>
<!-- /.container -->

</c:if>

<jsp:include page="footer.jsp" />
