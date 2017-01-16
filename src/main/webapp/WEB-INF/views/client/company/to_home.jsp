<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${authorized_user ne null}"><c:set var="reqmap" value="/admin"/></c:if>
<div class="container">
    <div class="row">
        <div class="box">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                <c:set var="desc_length" value="650"/>
                <c:choose>
                    <c:when test="${company.description ne null}">
                        <hr>
                        <h3 class="intro-text text-center">О нас</h3>
                        <hr>
                        <p>${company.description}</p>
                    </c:when>
                    <c:when test="${company.information ne null}">
                        <hr>
                        <h3 class="intro-text text-center">О нас</h3>
                        <hr>
                        <p><${company.information}</p>
                    </c:when>
                    <c:otherwise>
                        <hr>
                        <h3 class="intro-text text-center">О нас</h3>
                        <hr>
                        <p>${company.tagline}</p>
                    </c:otherwise>
                </c:choose>
                <hr>
                <p class="text-right">
                    <a href="<c:url value="${reqmap}/company/main"/>" title="Описание нашей компании">
                        <span class="glyphicon glyphicon-share-alt"
                              aria-hidden="true"></span>&nbsp;Подробнее о нас...
                    </a>
                </p>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
