<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="length" value="${fn:length(responses)}"/>
<c:if test="${length gt 0}">
    <c:if test="${authorized_user ne null}"><c:set var="reqmap" value="/admin"/></c:if>
    <c:if test="${(print_responses eq null) or (print_responses gt length) or (print_responses le 0)}">
        <c:set var="print_responses" value="${length}"/>
    </c:if>
    <div class="container">
        <div class="row">
            <div class="box">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <hr>
                    <h3 class="intro-text text-center">
                        <a href="<c:url value="${reqmap}/responses"/>">Отзывы</a>
                    </h3>
                    <hr>
                    <c:forEach items="${responses}" var="response" end="${print_responses - 1}">
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                            <p>
                                <span class="response green"><c:out value="${response.username}"/></span>&nbsp;
                                <span class="little">(<c:out value="${response.dateToString}"/>)</span>
                            </p>
                            <p class="response"><c:out value="${response.text}"/></p><br>
                        </div>
                    </c:forEach>
                    <c:if test="${length gt print_responses}">
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                            <hr class="hidden-md hidden-lg hidden-xl">
                            <p class="text-right">
                                <a href="<c:url value="${reqmap}/responses"/>"
                                   title="Перейти ко всем отзывам о нашей компании">
                                    <span class="glyphicon glyphicon-share-alt"
                                          aria-hidden="true"></span>&nbsp;Все отзывы...
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

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
