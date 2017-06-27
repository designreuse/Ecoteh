<%--
Categories list.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="length" value="${fn:length(articles)}"/>
<c:if test="${length gt 0}">
    <c:choose>
        <c:when test="${(length % 3 eq 0) or (length % 3 eq 2)}">
            <c:set var="in_line" value="3"/>
        </c:when>
        <c:when test="${(length % 2 eq 0) or (length % 2 eq 1)}">
            <c:set var="in_line" value="2"/>
        </c:when>
        <c:otherwise>
            <c:set var="in_line" value="1"/>
        </c:otherwise>
    </c:choose>
    <c:set var="last_line" value="${length - length % in_line}"/>
    <c:set var="printed_in_line" value="0"/>
    <c:set var="printed" value="0"/>

    <c:forEach items="${articles}" var="article">
        <c:if test="${(last_line ne length ) and (printed eq last_line)}">
            <c:set var="in_line" value="${length - last_line}"/>
        </c:if>
        <div class="col-xs-12 col-sm-12
                                    <c:choose>
                                    <c:when test="${in_line eq 1}">col-md-12 col-lg-12</c:when>
                                    <c:when test="${in_line eq 2}">col-md-6 col-lg-6</c:when>
                                    <c:otherwise>col-md-4 col-lg-4</c:otherwise>
                                    </c:choose>">
            <c:choose>
                <c:when test="${not empty article.description}">
                    <c:set var="title" value="${article.title} - ${article.description}"/>
                </c:when>
                <c:otherwise>
                    <c:set var="title" value="${article.title}"/>
                </c:otherwise>
            </c:choose>

            <c:if test="${not empty article.logo.url}">
                <a href="<c:url value="/article/${article.url}"/>" title="<c:out value="${title}"/>">
                    <img class="img-in-list" alt="<c:out value="${article.title}"/>"
                         src="<c:url value="${article.logo.url}"/>" onerror="this.src='<c:url
                            value="/resources/img/static/default_file.gif"/>'">
                </a>
            </c:if>
            <c:choose>
                <c:when test="${article.price gt 0}">
                    <h4 class="price-top">
                        <fmt:formatNumber type="number" value="${article.price}"/>&nbsp;<c:out
                            value="${article.currency}"/>
                    </h4>
                </c:when>
                <c:when test="${empty article.currency}">
                    <h5 class="price-top">
                        Цену уточняйте
                    </h5>
                </c:when>
            </c:choose>
            <c:if test="${!article.validated}">
                <p class="little">
                    <span class="glyphicon glyphicon-eye-close red" aria-hidden="true"
                          title="Не отображается для клиентов"></span>
                </p>
            </c:if>
            <a href="<c:url value="/article/${article.url}"/>" title="<c:out value="${title}"/>">
                <h4 class="text-center">
                    <c:out value="${article.title}"/>
                </h4>
            </a>
                <%-- Administrator actions --%>
            <c:if test="${authorized_user ne null}">
                <h3 class="text-center">
                    <a href="<c:url value="/admin/article/edit/${article.url}"/>"
                       title="Редактировать статью &quot;<c:out value="${article.title}"/>&quot;">
                        <button class="btn btn-default">
                            <span class="glyphicon glyphicon-edit yellow" aria-hidden="true"></span>
                        </button>
                    </a>
                    &nbsp;&nbsp;
                    <a href="<c:url value="/admin/article/delete/${article.url}"/>"
                       title="Удалить статью &quot;<c:out value="${article.title}"/>&quot;"
                       onclick="if(confirm('Вы точно хотите удалить статью &quot;<c:out
                               value="${article.title}"/>&quot;? Удаленные объекты восстановлению не подлежат!')) this.submit; else return false;">
                        <button class="btn btn-default">
                            <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                        </button>
                    </a>
                </h3>
            </c:if>
        </div>
        <c:set var="printed" value="${printed + 1}"/>
        <c:set var="printed_in_line" value="${printed_in_line + 1}"/>
        <c:if test="${printed_in_line eq in_line}">
            <c:if test="${length gt printed}">
                <div class="hidden-xs hidden-sm col-md-12 col-lg-12">&nbsp;</div>
            </c:if>
            <c:set var="printed_in_line" value="0"/>
        </c:if>
    </c:forEach>
</c:if>
