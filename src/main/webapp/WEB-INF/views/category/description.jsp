<%--
Description of the incoming category.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test="${not empty category.text}">
    <div class="container">
        <div class="row">
            <div class="box">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <h3 class="text-center">
                        <c:out value="${category.title}"/>
                    </h3>
                    <c:if test="${!category.validated}">
                        <p class="little">
                            <span class="glyphicon glyphicon-eye-close red" aria-hidden="true"
                                  title="Не отображается для клиентов"></span>&nbsp;
                        </p>
                    </c:if>
                    <c:choose>
                        <c:when test="${not empty category.description}">
                            <p>
                                <a href="<c:url value="${category.logo.url}"/>" rel="lightgallery"
                                   title="<c:out value="${category.title}"/>">
                                    <img src="<c:url value="${category.logo.url}"/>" class="content-logo"
                                         title="<c:out value="${category.title}"/>">
                                </a>
                                <c:out value="${category.description}"/>
                            </p>
                        </c:when>
                        <c:when test="${not empty category.text}">
                            <p>${category.text}</p>
                        </c:when>
                        <c:otherwise>
                            <a href="<c:url value="/article/${category.logo.url}"/>"
                               title="Перейти к &quot;<c:out value="${category.title}"/>&quot;">
                                <c:if test="${not empty category.logo.url}">
                                    <img class="img-responsive img-in-list" alt="<c:out value="${category.title}"/>"
                                         src="<c:url value="${category.logo.url}"/>" onerror="this.src='<c:url
                                            value="/resources/img/static/default_file.gif"/>'">
                                </c:if>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</c:if>
