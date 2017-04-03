<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${fn:length(articles_list) gt 0}">
    <div class="container">
        <div class="row">
            <div class="box">
                <c:forEach items="${articles_list}" var="article">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <h3 class="text-center">
                            <c:choose>
                                <c:when test="${article.text ne ''}">
                                    <a href="<c:url value="/article/${article.url}"/>">
                                        <c:out value="${article.title}"/>
                                    </a>
                                </c:when>
                                <c:otherwise><c:out value="${article.title}"/></c:otherwise>
                            </c:choose>
                        </h3>
                        <c:if test="${authorized_user ne null}">
                            <h3 class="text-center">
                                <a href="<c:url value="/admin/article/edit/${article.url}"/>"
                                   title="Редактировать статью &quot;<c:out value="${article.title}"/>&quot;">
                                    <button class="btn btn-default">
                                                <span class="glyphicon glyphicon-edit yellow"
                                                      aria-hidden="true"></span>&nbsp;Редактировать
                                    </button>
                                </a>&nbsp;&nbsp;
                                <a href="<c:url value="/admin/article/delete/${article.url}"/>"
                                   title="Удалить статью &quot;<c:out value="${article.title}"/>&quot;">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>&nbsp;Удалить
                                    </button>
                                </a>
                            </h3>
                        </c:if>
                        <c:if test="${article.price ne ''}">
                            <h4 class="green">
                                <c:choose>
                                    <c:when test="${article.price ne '0'}">
                                        Цена: <c:out value="${article.price}"/>
                                    </c:when>
                                    <c:otherwise>Цену уточняйте</c:otherwise>
                                </c:choose>
                            </h4>
                        </c:if>
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
                            <c:when test="${article.description ne ''}"><p>${article.description}</p></c:when>
                            <c:when test="${article.text ne ''}">${article.text}</c:when>
                            <c:otherwise>
                                <a href="<c:url value="/article/${article.logo.url}"/>"
                                   title="Перейти к &quot;<c:out value="${article.title}"/>&quot;">
                                    <c:if test="${article.logo.url ne ''}">
                                        <img class="img-responsive img-in-list" alt="<c:out value="${article.title}"/>"
                                             src="<c:url value="${article.logo.url}"/>" onerror="this.src='<c:url
                                                value="/resources/img/static/default_file.gif"/>'">
                                    </c:if>
                                </a>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${article.text ne ''}">
                            <p class="text-right">
                                <a href="<c:url value="/article/${article.url}"/>"
                                   title="Подробнее о &quot;<c:out value="${article.title}"/>&quot;">
                                <span class="glyphicon glyphicon-share-alt"
                                      aria-hidden="true"></span>&nbsp;Подробнее...
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

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
