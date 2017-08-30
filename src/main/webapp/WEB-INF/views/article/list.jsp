<%--
Articles list.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${fn:length(articles) gt 0}">
    <div class="container">
        <div class="row">
            <div class="box">
                <c:forEach items="${articles}" var="article">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <h3 class="text-center">
                            <c:choose>
                                <c:when test="${not empty article.text}">
                                    <a href="<c:url value="/article/${article.url}"/>">
                                        <c:out value="${article.title}"/>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <c:out value="${article.title}"/>
                                </c:otherwise>
                            </c:choose>
                        </h3>
                            <%-- Administrator actions --%>
                        <c:if test="${authorized_user ne null}">
                            <h3 class="text-center">
                                <a href="<c:url value="/admin/article/edit/${article.url}"/>"
                                   title="Редактировать статью &quot;<c:out value="${article.title}"/>&quot;">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-edit yellow" aria-hidden="true"></span>
                                        &nbsp;Редактировать
                                    </button>
                                </a>
                                &nbsp;&nbsp;
                                <a href="<c:url value="/admin/article/delete/${article.url}"/>"
                                   title="Удалить статью &quot;<c:out value="${article.title}"/>&quot;"
                                   onclick="if(confirm('Вы точно хотите удалить статью &quot;<c:out
                                           value="${article.title}"/>&quot;? Удаленные объекты восстановлению не подлежат!')) this.submit; else return false;">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                                        &nbsp;Удалить
                                    </button>
                                </a>
                            </h3>
                        </c:if>
                        <h4 class="green">
                            <c:choose>
                                <c:when test="${not empty article.price}">
                                    Цена: <c:out value="${article.price}"/>
                                </c:when>
                                <c:otherwise>
                                    Цену уточняйте
                                </c:otherwise>
                            </c:choose>
                        </h4>
                        <span class="little">
                            <c:if test="${!article.validated}">
                                <span class="glyphicon glyphicon-eye-close red" aria-hidden="true"
                                      title="Не отображается для клиентов"></span>&nbsp;
                            </c:if>
                            <c:out value="${article.dateToString}"/>,&nbsp;&nbsp;Артикль:
                            <a href="<c:url value="/article/num_${article.number}"/>">
                                <c:out value="${article.number}"/>
                            </a>
                        </span>
                        <c:choose>
                            <c:when test="${not empty article.description}">
                                <p>
                                    <a href="<c:url value="${article.logo.url}"/>" rel="lightgallery"
                                       title="<c:out value="${article.title}"/>">
                                        <img src="<c:url value="${article.logo.url}"/>" class="content-logo"
                                             title="<c:out value="${article.title}"/>">
                                    </a>
                                    <c:out value="${article.description}"/>
                                </p>
                            </c:when>
                            <c:when test="${not empty article.text}">
                                <p>${article.text}</p>
                            </c:when>
                            <c:otherwise>
                                <a href="<c:url value="/article/${article.logo.url}"/>"
                                   title="Перейти к &quot;<c:out value="${article.title}"/>&quot;">
                                    <c:if test="${not empty article.logo.url}">
                                        <img class="img-responsive img-in-list" alt="<c:out value="${article.title}"/>"
                                             src="<c:url value="${article.logo.url}"/>" onerror="this.src='<c:url
                                                value="/resources/img/static/default_file.gif"/>'">
                                    </c:if>
                                </a>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${not empty article.text}">
                            <p class="text-right">
                                <a href="<c:url value="/article/${article.url}"/>"
                                   title="Подробнее о &quot;<c:out value="${article.title}"/>&quot;">
                                    <span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span>
                                    &nbsp;Подробнее...
                                </a>
                            </p>
                        </c:if>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <hr>
                    </div>
                </c:forEach>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</c:if>
