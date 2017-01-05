<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8 col-xl-8">
    <br>
    <div id="carousel-example-generic" class="carousel slide">
        <c:choose>
            <c:when test="${fn:length(slides) gt 0}">
                <%-- Indicators --%>
                <ol class="carousel-indicators hidden-xs">
                    <c:set var="count" value="0"/>
                    <c:forEach items="${slides}" var="slide">
                        <c:if test="${slide ne null}">
                            <li data-target="#carousel-example-generic" data-slide-to="${count}"
                                <c:if test="${count eq 0}">class="active"</c:if>>
                            </li>
                            <c:set var="count" value="${count + 1}"/>
                        </c:if>
                    </c:forEach>
                </ol>
                <%-- Slides --%>
                <div class="carousel-inner">
                    <c:set var="count" value="true"/>
                    <c:forEach items="${slides}" var="slide">
                        <c:if test="${slide ne null}">
                            <div class="text-center item<c:if test="${count}"> active
                                                    <c:set var="count" value="false"/></c:if>">
                                <img class="slide" alt="" src="/resources/img/<c:out value="${slide.url}"/>">
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
                <%-- Controls --%>
                <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                    <span class="icon-prev"></span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic"
                   data-slide="next"><span class="icon-next"></span></a>
            </c:when>
            <c:otherwise>
                <%-- Default slide --%>
                <div class="carousel-inner">
                    <div class="text-center item active">
                        <img class="slide" alt="" src="/resources/img/static/default_slide.jpg">
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
