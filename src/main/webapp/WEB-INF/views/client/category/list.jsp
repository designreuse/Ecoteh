<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="length" value="${fn:length(categories)}"/>
<c:if test="${length gt 0}">
    <c:if test="${authorized_user ne null}"><c:set var="reqmap" value="/admin"/></c:if>
    <c:choose>
        <c:when test="${(length % 3 eq 0) or (length % 3 eq 2)}"><c:set var="in_line" value="3"/></c:when>
        <c:when test="${(length % 2 eq 0) or (length % 2 eq 1)}"><c:set var="in_line" value="2"/></c:when>
        <c:otherwise><c:set var="in_line" value="1"/></c:otherwise>
    </c:choose>
    <c:set var="last_line" value="${length - length % in_line}"/>
    <c:set var="printed_in_line" value="0"/>
    <c:set var="printed" value="0"/>
    <div class="container">
        <div class="row">
            <div class="box">
                <c:forEach items="${categories}" var="category">
                    <c:if test="${(last_line ne length ) and (printed eq last_line)}">
                        <c:set var="in_line" value="${length - last_line}"/>
                    </c:if>
                    <div class="col-xs-12 col-sm-12
                                    <c:choose>
                                    <c:when test="${in_line eq 1}">col-md-12 col-lg-12 col-xl-12</c:when>
                                    <c:when test="${in_line eq 2}">col-md-6 col-lg-6 col-xl-6</c:when>
                                    <c:otherwise>col-md-4 col-lg-4 col-xl-4</c:otherwise>
                                    </c:choose>">
                        <a href="<c:url value="${reqmap}/category/${category.url}"/>"
                           title="Перейти к категории &quot;<c:out value="${category.title}"/>&quot;">
                            <c:if test="${category.photo ne null}">
                                <img class="img-responsive img-in-list" alt="<c:out value="${category.title}"/>"
                                     src="<c:url value="/resources/${category.photo.url}"/>">
                            </c:if>
                            <h3 class="text-center"><c:out value="${category.title}"/></h3>
                        </a>
                        <c:if test="${!category.validated}">
                            <p class="little">
                                <span class="glyphicon glyphicon-eye-close red" aria-hidden="true"
                                      title="Не отображается для клиентов"></span>&nbsp;
                            </p>
                        </c:if>
                    </div>
                    <c:set var="printed" value="${printed + 1}"/>
                    <c:set var="printed_in_line" value="${printed_in_line + 1}"/>
                    <c:if test="${printed_in_line eq in_line}">
                        <c:if test="${length gt printed}">
                            <div class="hidden-xs hidden-sm col-md-12 col-lg-12 col-xl-12">
                                <hr>
                            </div>
                        </c:if>
                        <c:set var="printed_in_line" value="0"/>
                    </c:if>
                </c:forEach>
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <hr>
                    <p class="text-right">
                        <a href="<c:url value="${reqmap}/article/all"/>" title="Перейти к списку всех товаров">
                                <span class="glyphicon glyphicon-share-alt"
                                      aria-hidden="true"></span>&nbsp;Продукция...
                        </a>
                    </p>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</c:if>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
