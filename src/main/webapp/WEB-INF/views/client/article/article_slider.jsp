<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="length" value="${fn:length(articles)}"/>
<c:if test="${length gt 1}">
    <div class="container">
        <div class="row">
            <div class="box">
                <p class="alert">
                    <a href="<c:url value="/category/${category.url}"/>" title="${category.title}">
                        Также просматривают:
                    </a>
                </p>
                <c:forEach items="${articles}" var="article_s" end="3">
                    <div class="hidden-xs col-sm-3 col-md-3 col-lg-3">
                        <div class="text-center">
                            <a href="<c:url value="/article/${article_s.url}"/>"
                               title="&quot;<c:out value="${article_s.title}"/>&quot;">
                                <c:if test="${(article_s.logo ne null) and (article_s.logo.url ne '')}">
                                    <img class="img-logo" alt="<c:out value="${article_s.title}"/>"
                                         src="<c:url value="${article_s.logo.url}"/>"
                                         onerror="this.src='<c:url
                                                 value="/resources/img/static/default_file.gif"/>'">
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

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
