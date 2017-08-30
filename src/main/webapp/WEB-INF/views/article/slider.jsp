<%--
List of similar products.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="length" value="${fn:length(articles)}"/>
<c:if test="${length gt 1}">
    <c:if test="${(print_products eq null) or (print_products gt length) or (print_products le 0)}">
        <c:set var="print_products" value="3"/>
    </c:if>

    <div class="container">
        <div class="row">
            <div class="box">
                <p class="alert">
                    <a href="<c:url value="/category/${category.url}"/>" title="${category.title}">
                        Другие товары:
                    </a>
                </p>
                <c:forEach items="${articles}" var="article_s" end="${print_products}">
                    <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
                        <div class="text-center">
                            <a href="<c:url value="/article/${article_s.url}"/>"
                               title="&quot;<c:out value="${article_s.title}"/>&quot;">
                                <c:if test="${not empty article_s.logo.url}">
                                    <img class="img-logo" alt="<c:out value="${article_s.title}"/>"
                                         src="<c:url value="${article_s.logo.url}"/>"
                                         onerror="this.src='<c:url
                                                 value="/resources/img/static/default_file.gif"/>'">
                                </c:if>
                                <c:if test="${not empty article_s.price}">
                                    <h5 class="price-slider back-green">
                                        <c:out value="${article_s.price}"/>
                                    </h5>
                                </c:if>
                                <c:if test="${article_s.novelty}">
                                    <c:choose>
                                        <c:when test="${not empty article_s.price}">
                                            <h5 class="novelty-slider back-yellow">Новинка</h5>
                                        </c:when>
                                        <c:otherwise>
                                            <h5 class="top-novelty-slider back-yellow">Новинка</h5>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                                <h5><c:out value="${article_s.title}"/></h5>
                            </a>
                        </div>
                    </div>
                </c:forEach>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</c:if>
