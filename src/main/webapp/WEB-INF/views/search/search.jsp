<%--
Page for searching objects on the site.
Also on this page is the result of the search.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<c:set var="categories_length" value="${fn:length(categories_list)}" scope="page"/>
<c:set var="articles_length" value="${fn:length(articles_list)}" scope="page"/>
<c:set var="posts_length" value="${fn:length(posts_list)}" scope="page"/>
<c:set var="companies_length" value="${fn:length(companies_list)}" scope="page"/>
<c:set var="users_length" value="${fn:length(users_list)}" scope="page"/>
<c:set var="result" value="${(categories_length gt 0) or (articles_length gt 0) or
 (posts_length gt 0) or (companies_length gt 0) or (users_length gt 0)}"/>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Yurii Salimov (yuriy.alex.salimov@gmail.com)">
        <meta name="robots" content="noindex,nofollow">
        <title>Результаты поиска | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="Результаты поиска | <c:out value="${main_company.title}"/>">
        <meta name="description" content="Результаты поиска | <c:out value="${main_company.title}"/>">
        <meta name="keywords" content="Результаты поиска | <c:out value="${main_company.title}"/>">
        <meta property="og:title" content="<c:out value="${main_company.title}"/>"/>
        <meta property="og:type" content="website"/>
        <meta property="og:url" content="<c:url value="/index"/>"/>
        <meta property="og:description" content="<c:out value="${main_company.description}"/>"/>
        <meta property="og:image" content="<c:url value="${main_company.logo.url}"/>"/>
        <link rel="shortcut icon" href="<c:url value="${favicon.url}"/>" type="image/x-icon">
        <link rel="icon" href="<c:url value="${favicon.url}"/>" type="image/x-icon">
            <%-- CSS styles --%>
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
              rel="stylesheet">
        <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
              rel="stylesheet">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="<c:url value="/resources/css/style.min.css"/>" rel="stylesheet">
        <c:if test="${result}">
            <link href="<c:url value="/resources/css/lightgallery.min.css"/>" rel="stylesheet">
        </c:if>
    </head>
    <body>
        <%-- Navigation bar --%>
    <jsp:include page="/WEB-INF/views/home/navigation.jsp"/>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <div class="container">
            <div class="row">
                <div class="box">
                        <%-- Site page path --%>
                    <p class="path">
                        <a href="<c:url value="/"/>" title="Перейти на главную страницу">Главная</a>
                        → <a href="<c:url value="/search/"/>">Поиск</a>
                    </p>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <hr>
                        <h3 class="text-center">Поиск</h3>
                        <c:if test="${is_search and !result}">
                            <h4 class="text-center red"><b>Поиск не дал результатов</b></h4>
                        </c:if>
                        <hr>
                    </div>
                    <div class="col-xs-12 col-sm-10 col-sm-offset-2 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2">
                        <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <label title="Введите ключевые слова для поиска. Если ключевых слов несколько, их стоит вводить через запятую &quot;,&quot;.">
                                <b>
                                    <span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span>
                                    <c:if test="${is_search and !result}">Может попробуем еще?</c:if>
                                    Что будем искать?
                                </b>
                            </label>
                        </div>
                            <%-- Form to search --%>
                        <%@include file="/WEB-INF/views/search/form.jsp" %>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
        <%-- Search result --%>
    <c:choose>
        <c:when test="${result}">
            <%-- Categories list --%>
            <%@include file="/WEB-INF/views/category/search_list.jsp" %>
            <%-- Articles list --%>
            <%@include file="/WEB-INF/views/article/search_list.jsp" %>
            <%-- Posts list --%>
            <%@include file="/WEB-INF/views/post/search_list.jsp" %>
            <%-- Partners list --%>
            <%@include file="/WEB-INF/views/company/search_list.jsp" %>
            <%-- Users list --%>
            <%@include file="/WEB-INF/views/user/search_list.jsp" %>
        </c:when>
        <c:otherwise>
            <br><br><br>
        </c:otherwise>
    </c:choose>
        <%-- Footer --%>
    <jsp:include page="/WEB-INF/views/home/footer.jsp"/>
        <%-- Scripts --%>
    <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <c:if test="${result}">
        <script src="<c:url value="/resources/js/lightgallery.min.js"/>"></script>
        <script src="<c:url value="/resources/js/easing.min.js"/>" async></script>
        <script src="<c:url value="/resources/js/totop.min.js"/>" async></script>
    </c:if>
    </body>
    </html>
</compress:html>
