<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
    <c:choose>
        <c:when test="${company.logo ne null}">
            <div class="brand">
                <a href="http://<c:out value="${company.domain}"/>">
                    <img src="<c:url value="/resources/${company.logo.url}"/>"
                         class="main-logo" alt="<c:out value="${company.title}"/>"
                         onerror="this.src='<c:url
                                 value="/resources/img/static/default_file.gif"/>'">
                </a>
            </div>
        </c:when>
        <c:when test="${company.title ne null}">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                <hr>
                <a href="http://<c:out value="${company.domain}"/>">
                    <h1 class="text-center"><b><c:out value="${company.title}"/></b></h1>
                </a>
                <hr>
            </div>
        </c:when>
    </c:choose>
    <c:if test="${company.tagline ne null}">
        <div class="col-xs-12 col-sm-12 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
            <div class="address-bar">
                <c:out value="${company.tagline}"/>
            </div>
        </div>
    </c:if>
</div>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
