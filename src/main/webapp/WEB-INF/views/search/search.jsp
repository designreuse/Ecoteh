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
<c:set var="partners_length" value="${fn:length(partners_list)}" scope="page"/>
<c:set var="users_length" value="${fn:length(users_list)}" scope="page"/>
<c:set var="result" scope="page"
       value="${(categories_length gt 0) or (articles_length gt 0) or (partners_length gt 0) or (users_length gt 0)}"/>

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
        <link rel="shortcut icon" href="<c:url value="${favicon.url}"/>" type="image/x-icon">
        <link rel="icon" href="<c:url value="${favicon.url}"/>" type="image/x-icon">
            <%-- CSS styles --%>
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
              rel="stylesheet" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
              rel="stylesheet" type="text/css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet"
              type="text/css">
        <link href="<c:url value="/resources/css/style.min.css"/>" rel="stylesheet" type="text/css">
        <c:if test="${result}">
            <link href="<c:url value="/resources/css/lightgallery.min.css"/>" rel="stylesheet" type="text/css">
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
                        <form action="<c:url value="/search/result"/>" method="post">
                            <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <div class="inner-addon left-addon">
                                    <span class="glyphicon glyphicon-search"></span>
                                    <input type="text" class="form-control" name="keywords" value="${keywords}"
                                           placeholder="Поиск" required autofocus/>
                                </div>
                            </div>
                            <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <label>
                                    <b>
                                        <span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span>
                                        &nbsp;Где искать?
                                    </b>
                                </label>&nbsp;&nbsp;&nbsp;
                                <label title="Искать среди категорий">
                                    <input type="checkbox" name="content" title="" value="in_categories"
                                           <c:if test="${in_categories}">checked</c:if>>&nbsp;Категории
                                </label>&nbsp;&nbsp;&nbsp;
                                <label title="Искать среди статьей">
                                    <input type="checkbox" name="content" title="" value="in_articles"
                                           <c:if test="${in_articles}">checked</c:if>>&nbsp;Статьи
                                </label>&nbsp;&nbsp;&nbsp;
                                <label title="Искать среди партнеров">
                                    <input type="checkbox" name="content" title="" value="in_companies"
                                           <c:if test="${in_companies}">checked</c:if>>&nbsp;Партнеры
                                </label>&nbsp;&nbsp;&nbsp;
                                <label title="Искать везде">
                                    <input type="checkbox" name="content" title="" value="all"
                                           <c:if test="${(all eq null) or all}">checked</c:if>>&nbsp;Везде
                                </label>
                                <input type="hidden" name="content" value="">
                            </div>
                            <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <label>
                                    <b>
                                        <span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span>
                                        &nbsp;Как искать?
                                    </b>
                                </label>&nbsp;&nbsp;&nbsp;
                                <label title="Искать по точному совпадению слов">
                                    <input type="radio" name="how_search" title="" value="true" required
                                           <c:if test="${how_search}">checked</c:if>>&nbsp;Точное совпадение
                                </label>&nbsp;&nbsp;&nbsp;
                                <label title="Искать по любое из ключевых слов">
                                    <input type="radio" name="how_search" title="" value="false" required
                                           <c:if test="${!how_search}">checked</c:if>>&nbsp;Любое из ключевых слов
                                </label>&nbsp;&nbsp;&nbsp;
                            </div>
                            <div class="text-center form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <button type="submit" class="btn btn-default">
                                    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>&nbsp;Поиск
                                </button>
                            </div>
                        </form>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
        <%-- Search result --%>
    <%@include file="/WEB-INF/views/search/result.jsp" %>
        <%-- Footer --%>
    <jsp:include page="/WEB-INF/views/home/footer.jsp"/>
        <%-- Scripts --%>
    <script src="<c:url value="/resources/js/jquery.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <c:if test="${result}">
        <script src="<c:url value="/resources/js/lightgallery.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/easing.min.js"/>" type="text/javascript" async></script>
        <script src="<c:url value="/resources/js/totop.min.js"/>" type="text/javascript" async></script>
    </c:if>
    </body>
    </html>
</compress:html>