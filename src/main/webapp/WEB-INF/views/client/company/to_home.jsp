<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:choose>
    <c:when test="${company.advantages ne null}">
        <c:if test="${authorized_user ne null}"><c:set var="reqmap" value="/admin"/></c:if>
        <%-- Company Advantages --%>
        <div class="container">
            <div class="row">
                <div class="box">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                        <c:set var="desc_length" value="650"/>
                        <c:choose>
                            <c:when test="${company.advantages ne null}">
                                <hr>
                                <h3 class="intro-text text-center">Наши преимущества</h3>
                                <hr>
                                <c:choose>
                                    <c:when test="${fn:length(company.advantages) gt desc_length}">
                                        <p><c:out value="${fn:substring(company.advantages, 0, desc_length)}"/>...</p>
                                    </c:when>
                                    <c:otherwise><p><c:out value="${company.advantages}"/></p></c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:when test="${company.information ne null}">
                                <hr>
                                <h3 class="intro-text text-center">О нас</h3>
                                <hr>
                                <c:choose>
                                    <c:when test="${fn:length(company.information) gt desc_length}">
                                        <c:out value="${fn:substring(company.information, 0, desc_length)}"/>...
                                    </c:when>
                                    <c:otherwise><c:out value="${company.information}"/></c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <hr>
                                <h3 class="intro-text text-center">О нас</h3>
                                <hr>
                                <p><c:out value="${company.tagline}"/></p>
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
    </c:when>
    <c:when test="${company.description ne null}">
        <%-- Company Description --%>
        <div class="container">
            <div class="row">
                <div class="box">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                        <hr>
                        <h3 class="intro-text text-center">Описание</h3>
                        <hr>
                        <p><c:out value="${company.description}"/></p>
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
    </c:when>
</c:choose>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
