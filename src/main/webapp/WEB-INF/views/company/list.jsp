<%--
Companies list.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${fn:length(companies) gt 0}">
    <div class="container">
        <div class="row">
            <div class="box">
                <c:forEach items="${companies}" var="company">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <h3 class="text-center">
                            <a href="<c:url value="/company/${company.url}"/>">
                                <c:out value="${company.title}"/>
                            </a>
                        </h3>
                            <%-- Administrator actions --%>
                        <c:if test="${authorized_user ne null}">
                            <h3 class="text-center">
                                <a href="<c:url value="/admin/company/edit/${company.url}"/>"
                                   title="Редактировать компанию &quot;<c:out value="${company.title}"/>&quot;">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-edit yellow" aria-hidden="true"></span>
                                        &nbsp;Редактировать
                                    </button>
                                </a>
                                &nbsp;&nbsp;
                                <a href="<c:url value="/admin/company/delete/${company.url}"/>"
                                   title="Удалить компанию &quot;<c:out value="${company.title}"/>&quot;"
                                   onclick="if(confirm('Вы точно хотите удалить компанию &quot;<c:out
                                           value="${company.title}"/>&quot;? Удаленные объекты восстановлению не подлежат!')) this.submit; else return false;">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                                        &nbsp;Удалить
                                    </button>
                                </a>
                            </h3>
                        </c:if>
                        <c:if test="${!company.validated}">
                            <p class="little">
                                <span class="glyphicon glyphicon-eye-close red" aria-hidden="true"
                                      title="Не отображается для клиентов"></span>&nbsp;
                            </p>
                        </c:if>
                        <c:choose>
                            <c:when test="${not empty company.description}">
                                <p>${company.description}</p>
                            </c:when>
                            <c:when test="${not empty company.information}">
                                <p>${company.information}</p>
                            </c:when>
                            <c:otherwise>
                                <p>${company.tagline}</p>
                            </c:otherwise>
                        </c:choose>
                        <p class="text-right">
                            <a href="<c:url value="/company/${company.url}"/>"
                               title="Подробнее о &quot;<c:out value="${company.title}"/>&quot;">
                                <span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span>
                                &nbsp;Подробнее...
                            </a>
                        </p>
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
