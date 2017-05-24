<%--
Responses list for home page.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="length" value="${fn:length(responses)}"/>
<c:if test="${length gt 0}">
    <div class="container">
        <div class="row">
            <div class="box">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <hr>
                    <h3 class="intro-text text-center">
                        <a href="<c:url value="/responses"/>">Отзывы</a>
                    </h3>
                    <hr>
                    <c:if test="${(authorized_user ne null) and (length gt 0)}">
                        <div class="text-center">
                            <a href="<c:url value="/admin/response/delete/all"/>"
                               title="Удалить все отзывы о компании">
                                <button class="btn btn-default">
                                    <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                                    &nbsp;Удалить&nbsp;все
                                </button>
                            </a>
                        </div>
                    </c:if>
                        <%-- Responses list --%>
                    <jsp:include page="/WEB-INF/views/client/response/list.jsp"/>
                    <c:if test="${length gt print_responses}">
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <hr class="hidden-md hidden-lg hidden-xl">
                            <p class="text-right">
                                <a href="<c:url value="/responses"/>"
                                   title="Перейти ко всем отзывам о нашей компании">
                                    <span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span>
                                    &nbsp;Все&nbsp;отзывы...
                                </a>
                            </p>
                        </div>
                    </c:if>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</c:if>
