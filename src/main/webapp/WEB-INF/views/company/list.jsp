<%--
Companies list.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${fn:length(partners_list) gt 0}">
    <div class="container">
        <div class="row">
            <div class="box">
                <c:forEach items="${partners_list}" var="partner">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <h3 class="text-center">
                            <a href="<c:url value="/company/${partner.url}"/>">
                                <c:out value="${partner.title}"/>
                            </a>
                        </h3>
                            <%-- Administrator actions --%>
                        <c:if test="${authorized_user ne null}">
                            <h3 class="text-center">
                                <a href="<c:url value="/admin/company/edit/${partner.url}"/>"
                                   title="Редактировать компанию &quot;<c:out value="${partner.title}"/>&quot;">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-edit yellow" aria-hidden="true"></span>
                                        &nbsp;Редактировать
                                    </button>
                                </a>
                                &nbsp;&nbsp;
                                <a href="<c:url value="/admin/company/delete/${partner.url}"/>"
                                   title="Удалить компанию &quot;<c:out value="${partner.title}"/>&quot;"
                                   onclick="if(confirm('Вы точно хотите удалить компанию &quot;<c:out
                                           value="${partner.title}"/>&quot;? Удаленные объекты восстановлению не подлежат!')) this.submit; else return false;">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                                        &nbsp;Удалить
                                    </button>
                                </a>
                            </h3>
                        </c:if>
                        <c:if test="${!partner.validated}">
                            <p class="little">
                                <span class="glyphicon glyphicon-eye-close red" aria-hidden="true"
                                      title="Не отображается для клиентов"></span>&nbsp;
                            </p>
                        </c:if>
                        <c:choose>
                            <c:when test="${not empty partner.description}">
                                <p>${partner.description}</p>
                            </c:when>
                            <c:when test="${not empty partner.information}">
                                <p>${partner.information}</p>
                            </c:when>
                            <c:otherwise>
                                <p>${partner.tagline}</p>
                            </c:otherwise>
                        </c:choose>
                        <p class="text-right">
                            <a href="<c:url value="/company/${partner.url}"/>"
                               title="Подробнее о &quot;<c:out value="${partner.title}"/>&quot;">
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
