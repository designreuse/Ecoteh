<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${fn:length(articles_list) gt 0}">
    <c:if test="${authorized_user ne null}"><c:set var="reqmap" value="/admin"/></c:if>
    <div class="container">
        <div class="row">
            <div class="box">
                <c:forEach items="${articles_list}" var="article">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                        <c:if test="${article.photo ne null}">
                            <a href="<c:url value="/resources/${article.photo.url}"/>"
                               title="${article.title}" rel="lightgallery">
                                <img class="img-responsive img-border img-left img-section"
                                     alt="<c:out value="${article.title}"/>"
                                     src="<c:url value="/resources/${article.photo.url}"/>">
                            </a>
                        </c:if>
                        <c:set var="textNotNull" value="${article.text ne null}"/>
                        <h3 class="text-center">
                            <c:choose>
                                <c:when test="${textNotNull}">
                                    <a href="<c:url value="${reqmap}/article/${article.url}"/>">
                                        <c:out value="${article.title}"/>
                                    </a>
                                </c:when>
                                <c:otherwise><c:out value="${article.title}"/></c:otherwise>
                            </c:choose>
                        </h3>
                        <span class="little">
                            <c:if test="${!article.validated}">
                                <span class="glyphicon glyphicon-eye-close red" aria-hidden="true"
                                      title="Не отображается для клиентов"></span>&nbsp;
                            </c:if>
                            <c:out value="${article.dateToString}"/>,&nbsp;&nbsp;Артикль:
                            <a href="<c:url value="${reqmap}/article/num_${article.number}"/>">
                                <c:out value="${article.number}"/>
                            </a>
                        </span>
                        <c:choose>
                            <c:when test="${article.description ne null}"><p>${article.description}</p></c:when>
                            <c:when test="${textNotNull}">${article.text}</c:when>
                        </c:choose>
                        <c:if test="${textNotNull}">
                            <p class="text-right">
                                <a href="<c:url value="${reqmap}/article/${article.url}"/>"
                                   title="Подробнее о &quot;<c:out value="${article.title}"/>&quot;">
                                <span class="glyphicon glyphicon-share-alt"
                                      aria-hidden="true"></span>&nbsp;Подробнее...
                                </a>
                            </p>
                        </c:if>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                        <hr>
                    </div>
                </c:forEach>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</c:if>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
