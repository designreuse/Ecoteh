<%--
Logo of the incoming company.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
    <c:choose>
        <c:when test="${not empty company.logo.url}">
            <div class="brand">
                <a href="http://<c:out value="${company.domain}"/>">
                    <img src="<c:url value="${company.logo.url}"/>" class="main-logo" alt=""
                         onerror="this.src='<c:url value="/resources/img/static/default_file.gif"/>'">
                </a>
            </div>
        </c:when>
        <c:when test="${not empty company.title}">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <hr>
                <a href="http://<c:out value="${company.domain}"/>">
                    <h1 class="text-center">
                        <b><c:out value="${company.title}"/></b>
                    </h1>
                </a>
                <hr>
            </div>
        </c:when>
    </c:choose>
    <c:if test="${not empty company.tagline}">
        <div class="col-xs-12 col-sm-12 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1">
            <div class="address-bar">
                <c:out value="${company.tagline}"/>
            </div>
        </div>
    </c:if>
</div>
