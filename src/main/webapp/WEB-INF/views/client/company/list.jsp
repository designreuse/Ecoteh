<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${fn:length(partners_list) gt 0}">
    <c:if test="${authorized_user ne null}"><c:set var="reqmap" value="/admin"/></c:if>
    <c:set var="desc_length" value="650"/>
    <div class="container">
        <div class="row">
            <div class="box">
                <c:forEach items="${partners_list}" var="partner">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                        <hr>
                        <h3 class="text-center">
                            <b><a href="<c:url value="${reqmap}/company/${partner.url}"/>">
                                <c:out value="${partner.title}"/>
                            </a></b>
                        </h3>
                        <hr>
                        <c:if test="${partner.logo ne null}">
                            <a href="<c:url value="${reqmap}/company/${partner.url}"/>">
                                <img class="img-responsive img-left logo-in-list"
                                     alt="<c:out value="${partner.title}"/>"
                                     src="/resources/img/<c:out value="${partner.logo.url}"/>">
                            </a>
                            <hr class="visible-xs">
                        </c:if>
                        <c:if test="${!partner.validated}">
                            <p class="little">
                                <span class="glyphicon glyphicon-eye-close red" aria-hidden="true"
                                      title="Не отображается для клиентов"></span>&nbsp;
                            </p>
                        </c:if>
                        <c:choose>
                            <c:when test="${partner.description ne null}">
                                <c:choose>
                                    <c:when test="${fn:length(partner.description) gt desc_length}">
                                        <p><c:out value="${fn:substring(partner.description, 0, desc_length)}"/>...</p>
                                    </c:when>
                                    <c:otherwise><p><c:out value="${partner.description}"/></p></c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:when test="${partner.information ne null}">
                                <c:choose>
                                    <c:when test="${fn:length(partner.information) gt desc_length}">
                                        <c:out value="${fn:substring(partner.information, 0, desc_length)}"/>...
                                    </c:when>
                                    <c:otherwise><p>${partner.information}</p></c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise><p><c:out value="${partner.tagline}"/></p></c:otherwise>
                        </c:choose>
                        <p class="text-right">
                            <a href="<c:url value="${reqmap}/company/${partner.url}"/>"
                               title="Подробнее о &quot;<c:out value="${partner.title}"/>&quot;">
                                        <span class="glyphicon glyphicon-share-alt"
                                              aria-hidden="true"></span>&nbsp;Подробнее...
                            </a>
                        </p>
                    </div>
                </c:forEach>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</c:if>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
