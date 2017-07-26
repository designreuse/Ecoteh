<%--
Articles list.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${fn:length(posts) gt 0}">
    <c:forEach items="${posts}" var="post">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <h3 class="text-center">
                <a href="<c:url value="/blog/${post.url}"/>">
                    <c:out value="${post.title}"/>
                </a>
            </h3>
                <%-- Administrator actions --%>
            <c:if test="${authorized_user ne null}">
                <h3 class="text-center">
                    <a href="<c:url value="/admin/post/edit/${post.url}"/>"
                       title="Редактировать пост &quot;<c:out value="${post.title}"/>&quot;">
                        <button class="btn btn-default">
                            <span class="glyphicon glyphicon-edit yellow" aria-hidden="true"></span>
                            &nbsp;Редактировать
                        </button>
                    </a>
                    &nbsp;&nbsp;
                    <a href="<c:url value="/admin/post/delete/${post.url}"/>"
                       title="Удалить пост &quot;<c:out value="${post.title}"/>&quot;"
                       onclick="if(confirm('Вы точно хотите удалить плст &quot;<c:out
                               value="${post.title}"/>&quot;? Удаленные объекты восстановлению не подлежат!')) this.submit; else return false;">
                        <button class="btn btn-default">
                            <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                            &nbsp;Удалить
                        </button>
                    </a>
                </h3>
            </c:if>
            <span class="little">
                <c:if test="${!post.validated}">
                    <span class="glyphicon glyphicon-eye-close red" aria-hidden="true"
                          title="Не отображается для клиентов"></span>&nbsp;
                </c:if>
                <c:out value="${post.dateToString}"/>
            </span>
            <c:choose>
                <c:when test="${not empty post.description}">
                    <p class="text-justify">
                        <c:if test="${not empty post.logo.url}">
                            <a href="<c:url value="${post.logo.url}"/>" rel="lightgallery"
                               title="<c:out value="${post.title}"/>">
                                <img src="<c:url value="${post.logo.url}"/>" class="content-logo"
                                     title="<c:out value="${post.title}"/>">
                            </a>
                        </c:if>
                        <c:out value="${post.description}"/>
                    </p>
                </c:when>
                <c:otherwise>
                    <a href="<c:url value="/blog/${post.logo.url}"/>"
                       title="Перейти к &quot;<c:out value="${post.title}"/>&quot;">
                        <c:if test="${not empty post.logo.url}">
                            <img class="img-responsive img-in-list" alt="<c:out value="${post.title}"/>"
                                 src="<c:url value="${post.logo.url}"/>" onerror="this.src='<c:url
                                    value="/resources/img/static/default_file.gif"/>'">
                        </c:if>
                    </a>
                </c:otherwise>
            </c:choose>
            <p class="text-right">
                <a href="<c:url value="/post/${post.url}"/>"
                   title="Подробнее о &quot;<c:out value="${post.title}"/>&quot;">
                    <span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span>
                    &nbsp;Подробнее...
                </a>
            </p>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <hr>
        </div>
    </c:forEach>
</c:if>
