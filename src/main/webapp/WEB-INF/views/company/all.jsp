<%--
Page with all partner-companies.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru" prefix="og: http://ogp.me/ns#">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Yurii Salimov (yuriy.alex.salimov@gmail.com)">
        <meta name="robots" content="index,follow">
        <title>Партнеры | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="Партнеры | <c:out value="${main_company.title}"/>">
        <meta name="description" content="Партнеры компании &quot;<c:out value="${main_company.title}"/>&quot;.">
        <meta name="keywords"
              content="Партнеры<c:forEach items="${companies}" var="company">, <c:out value="${company.title}"/></c:forEach>"/>
        <meta property="og:title" content="Партнеры | <c:out value="${main_company.title}"/>"/>
        <meta property="og:type" content="website"/>
        <meta property="og:description" content="<c:out value="${main_company.title} - ${main_company.tagline}"/>"/>
        <meta property="og:image" content="<c:url value="${main_company.logo.url}"/>"/>
        <meta name="twitter:title" content="Партнеры | <c:out value="${main_company.title}"/>">
        <meta name="twitter:description" content="<c:out value="${main_company.title} - ${main_company.tagline}"/>">
        <meta name="twitter:image" content="<c:url value="${main_company.logo.url}"/>">
        <meta itemprop="name" content="Партнеры | <c:out value="${main_company.title}"/>"/>
        <meta itemprop="description" content="<c:out value="${main_company.title} - ${main_company.tagline}"/>"/>
        <meta itemprop="image" content="<c:url value="${main_company.logo.url}"/>"/>
        <link rel="shortcut icon" href="<c:url value="${favicon.url}"/>" type="image/x-icon">
        <link rel="icon" href="<c:url value="${favicon.url}"/>" type="image/x-icon">
            <%-- CSS styles --%>
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
              rel="stylesheet">
        <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
              rel="stylesheet">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="<c:url value="/resources/css/style.min.css"/>" rel="stylesheet">
        <c:set var="length" value="${fn:length(companies)}"/>
        <c:if test="${length gt 0}">
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
                        →
                        <a href="<c:url value="/company/all"/>" title="Все партнеры">Партнеры</a>
                    </p>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <hr>
                        <h3 class="text-center">Наши партнеры</h3>
                        <hr>
                            <%-- Administrator actions --%>
                        <c:if test="${authorized_user ne null}">
                            <div class="text-center">
                                <a href="<c:url value="/admin/company/new"/>" title="Добавить нового партнера">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                        &nbsp;Новый
                                    </button>
                                </a>
                                <c:if test="${length gt 0}">
                                    &nbsp;&nbsp;
                                    <a href="<c:url value="/admin/company/delete/all"/>" title="Удалить всех партнеров"
                                       onclick="if(confirm('Вы точно хотите удалить всех партнеров? Удаленные объекты восстановлению не подлежат!')) this.submit; else return false;">
                                        <button class="btn btn-default">
                                            <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                                            &nbsp;Удалить&nbsp;всех
                                        </button>
                                    </a>
                                </c:if>
                            </div>
                        </c:if>
                    </div>
                        <%-- Companies sorting --%>
                    <c:if test="${length gt 1}">
                        <p class="path">
                            <a href="#">Сортировка</a>:
                            <a href="<c:url value="/company/all/sort?revers=${revers}"/>"
                               title="Сортировать по названию">
                                <c:choose>
                                    <c:when test="${revers}">
                                        По&nbsp;названия&nbsp;<span class="glyphicon glyphicon-sort-by-alphabet-alt"
                                        aria-hidden="true"></span>
                                    </c:when>
                                    <c:otherwise>
                                        По&nbsp;названия&nbsp;<span class="glyphicon glyphicon-sort-by-alphabet"
                                        aria-hidden="true"></span>
                                    </c:otherwise>
                                </c:choose>
                            </a>
                        </p>
                    </c:if>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
            <%-- Partner-companies list --%>
        <jsp:include page="/WEB-INF/views/company/list.jsp"/>
        <div class="container">
            <div class="row">
                <div class="box">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <a href="<c:url value="/resources/img/static/ukraine.gif"/>" rel="lightgallery"
                           title="Наши партнеры на карте Украины">
                            <img src="<c:url value="/resources/img/static/ukraine.gif"/>" class="map" alt=""
                                 title="Карта Украины"
                                 onerror="this.src='<c:url value="/resources/img/static/default_file.gif"/>'">
                        </a>
                        <h4 class="text-center">
                            Наши партнеры на карте Украины
                        </h4>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
        <%-- Footer --%>
    <jsp:include page="/WEB-INF/views/home/footer.jsp"/>
        <%-- Scripts --%>
    <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <c:if test="${length gt 0}">
        <script src="<c:url value="/resources/js/lightgallery.min.js"/>"></script>
        <script src="<c:url value="/resources/js/easing.min.js"/>" async></script>
        <script src="<c:url value="/resources/js/totop.min.js"/>" async></script>
    </c:if>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
