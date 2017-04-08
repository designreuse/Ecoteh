<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
    <br>
    <div id="carousel-example-generic" class="carousel slide">
        <c:set var="length" value="${fn:length(slides)}"/>
        <c:choose>
            <c:when test="${length gt 0}">
                <c:if test="${length gt 1}">
                    <ol class="carousel-indicators hidden-xs">
                        <c:set var="count" value="0"/>
                        <c:forEach items="${slides}" var="slide">
                            <c:if test="${slide.url ne ''}">
                                <li data-target="#carousel-example-generic" data-slide-to="${count}"
                                    <c:if test="${count eq 0}">class="active"</c:if>>
                                </li>
                                <c:set var="count" value="${count + 1}"/>
                            </c:if>
                        </c:forEach>
                    </ol>
                </c:if>
                <div class="carousel-inner">
                    <c:set var="count" value="true"/>
                    <c:forEach items="${slides}" var="slide">
                        <c:if test="${slide.url ne ''}">
                            <div class="text-center item<c:if test="${count}"> active
                                                    <c:set var="count" value="false"/></c:if>">
                                <img src="<c:url value="${slide.url}"/>" onerror="this.src='<c:url
                                        value="/resources/img/static/default_slide.jpg"/>'" alt="${company.title}">
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
                <c:if test="${length gt 1}">
                    <a href="#carousel-example-generic" class="left carousel-control" data-slide="prev">
                        <span class="icon-prev"></span>
                    </a>
                    <a href="#carousel-example-generic" class="right carousel-control" data-slide="next">
                        <span class="icon-next"></span>
                    </a>
                </c:if>
            </c:when>
            <c:otherwise>
                <div class="carousel-inner">
                    <div class="text-center item active">
                        <img src="<c:url value="/resources/img/static/default_slide.jpg"/>" class="slide" alt="">
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
