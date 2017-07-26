<%--
List of similar products.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="length" value="${fn:length(posts)}"/>
<c:if test="${length gt 1}">
    <c:if test="${(print_posts eq null) or (print_posts gt length) or (print_posts le 0)}">
        <c:set var="print_products" value="3"/>
    </c:if>

    <div class="container">
        <div class="row">
            <div class="box">
                <p class="alert">
                    <a href="<c:url value="/blog"/>"
                       title="Блог компании <c:out value="${main_company.title}"/>">
                        Также просматривают:
                    </a>
                </p>
                <c:forEach items="${posts}" var="post_s" end="${print_products}">
                    <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
                        <div class="text-center">
                            <a href="<c:url value="/blog/${post_s.url}"/>"
                               title="&quot;<c:out value="${post_s.title}"/>&quot;">
                                <c:if test="${not empty post_s.logo.url}">
                                    <img class="img-logo" alt="<c:out value="${post_s.title}"/>"
                                         src="<c:url value="${post_s.logo.url}"/>"
                                         onerror="this.src='<c:url
                                                 value="/resources/img/static/default_file.gif"/>'">
                                </c:if>
                                <h5><c:out value="${post_s.title}"/></h5>
                            </a>
                        </div>
                    </div>
                </c:forEach>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</c:if>
