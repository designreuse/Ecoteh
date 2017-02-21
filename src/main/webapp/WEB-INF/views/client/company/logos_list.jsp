<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="length" value="${fn:length(partners)}"/>
<c:if test="${length gt 0}">
    <c:if test="${(print_partners eq null) or (print_partners gt length) or (print_partners le 0)}">
        <c:set var="print_partners" value="${length}"/>
    </c:if>
    <c:choose>
        <c:when test="${(print_partners % 4 eq 0) or (print_partners % 4 eq 3)}">
            <c:set var="in_line" value="4"/></c:when>
        <c:when test="${(print_partners % 3 eq 0) or (print_partners % 3 eq 2)}">
            <c:set var="in_line" value="3"/></c:when>
        <c:when test="${(print_partners % 2 eq 0) or (print_partners % 2 eq 1)}">
            <c:set var="in_line" value="2"/></c:when>
        <c:otherwise><c:set var="in_line" value="1"/></c:otherwise>
    </c:choose>
    <c:set var="last_line" value="${print_partners - print_partners % in_line}"/>
    <c:set var="printed_in_line" value="0"/>
    <c:set var="printed" value="0"/>
    <div class="container">
        <div class="row">
            <div class="box">
                <hr>
                <h3 class="intro-text text-center">
                    <a href="<c:url value="/company/all"/>" title="Наши партнеры">Партнеры</a>
                </h3>
                <hr>
                <c:forEach items="${partners}" var="partner" end="${print_partners - 1}">
                    <c:if test="${(last_line ne print_partners) and (printed eq last_line)}">
                        <c:set var="in_line" value="${print_partners - last_line}"/>
                    </c:if>
                    <div class="col-xs-12 <c:choose>
                                    <c:when test="${in_line eq 1}">col-sm-12 col-md-12 col-lg-12</c:when>
                                    <c:when test="${in_line eq 2}">col-sm-6 col-md-6 col-lg-6</c:when>
                                    <c:otherwise>col-sm-4 col-md-4 col-lg-4</c:otherwise>
                                    </c:choose>">
                        <div class="text-center">
                            <c:choose>
                                <c:when test="${partner.logoUrl ne ''}">
                                    <a href="<c:url value="/company/${partner.url}"/>"
                                       title="Партнер &quot;<c:out value="${partner.title}"/>&quot;">
                                        <img class="img-logo" alt="<c:out value="${partner.title}"/>"
                                             src="<c:url value="${partner.logoUrl}"/>"
                                             onerror="this.src='<c:url
                                                     value="/resources/img/static/default_file.gif"/>'">
                                    </a>
                                </c:when>
                                <c:when test="${partner.title ne ''}">
                                    <a href="<c:url value="/company/${partner.url}"/>"
                                       title="Партнер &quot;<c:out value="${partner.title}"/>&quot;">
                                        <h3 class="text-center"><c:out value="${partner.title}"/></h3>
                                    </a>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                    <c:set var="printed" value="${printed + 1}"/>
                    <c:set var="printed_in_line" value="${printed_in_line + 1}"/>
                    <c:if test="${printed_in_line eq in_line}">
                        <c:if test="${length gt printed}">
                            <div class="hidden-xs hidden-sm hidden-md col-lg-12"></div>
                        </c:if>
                        <c:set var="printed_in_line" value="0"/>
                    </c:if>
                </c:forEach>
                <c:if test="${length gt print_partners}">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <hr class="hidden-md hidden-lg hidden-xl">
                        <p class="text-right">
                            <a href="<c:url value="/company/all"/>" title="Перейти к списку всех партнеров">
                                <span class="glyphicon glyphicon-share-alt"></span>&nbsp;Все партнеры...
                            </a>
                        </p>
                    </div>
                </c:if>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</c:if>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
