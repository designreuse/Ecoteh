<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Yurii Salimov (yuriy.alex.salimov@gmail.com)">
        <title>Наши партнеры | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="Наши партнеры | <c:out value="${main_company.title}"/>">
        <meta name="robots" content="index,follow">
        <meta name="description" content="Партнеры компании &quot;<c:out value="${main_company.title}"/>&quot;.">
        <meta name="keywords"
              content="Партнеры<c:forEach items="${partners_list}" var="partner">, <c:out value="${partner.title}"/></c:forEach>"/>
        <c:if test="${main_company.faviconUrl ne ''}">
            <link rel="shortcut icon" href="<c:url value="${main_company.faviconUrl}"/>" type="image/x-icon">
            <link rel="icon" href="<c:url value="${main_company.faviconUrl}"/>" type="image/x-icon">
        </c:if>
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
              rel="stylesheet" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
              rel="stylesheet" type="text/css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet"
              type="text/css">
        <link href="<c:url value="/resources/css/style.min.css"/>" rel="stylesheet" type="text/css">
        <c:set var="length" value="${fn:length(partners_list)}"/>
        <c:if test="${length gt 0}">
            <link href="<c:url value="/resources/css/lightgallery.min.css"/>" rel="stylesheet" type="text/css">
        </c:if>
    </head>
    <body>
    <jsp:include page="/WEB-INF/views/client/main/navigation.jsp"/>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <div class="container">
            <div class="row">
                <div class="box">
                    <c:if test="${authorized_user ne null}">
                        <div class="text-center">
                            <a href="<c:url value="/admin/company/new"/>" title="Добавить нового партнера">
                                <button class="btn btn-default">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Новый
                                </button>
                            </a>
                            <c:if test="${length gt 0}">
                                &nbsp;&nbsp;
                                <a href="<c:url value="/admin/company/delete/all"/>" title="Удалить всех партнеров">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-remove red"
                                              aria-hidden="true"></span>&nbsp;Удалить всех
                                    </button>
                                </a>
                            </c:if>
                        </div>
                    </c:if>
                    <p class="path">
                        <a href="<c:url value="/"/>" title="Перейти на главную страницу">Главная</a>
                        → <a href="<c:url value="/company/all"/>">Партнеры</a>
                    </p>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <hr>
                        <h3 class="text-center">Наши партнеры</h3>
                        <hr>
                    </div>
                    <c:if test="${length gt 1}">
                        <p class="path">
                            <a href="#">Сортировка</a>:
                            <a href="<c:url value="/company/all/sort?revers=${revers}"/>"
                               title="Сортировать по названию">
                                <c:choose>
                                    <c:when test="${revers}">
                                        По названия&nbsp;<span class="glyphicon glyphicon-sort-by-alphabet-alt"
                                        aria-hidden="true"></span>
                                    </c:when>
                                    <c:otherwise>
                                        По названия&nbsp;<span class="glyphicon glyphicon-sort-by-alphabet"
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
        <jsp:include page="/WEB-INF/views/client/company/list.jsp"/>
        <div class="container">
            <div class="row">
                <div class="box">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <a href="<c:url value="/resources/img/static/ukraine.gif"/>" rel="lightgallery"
                           title="Наши партнеры на карте Украины">
                            <img src="<c:url value="/resources/img/static/ukraine.gif"/>" class="map"
                                 alt="" title="Карта Украины">
                        </a>
                        <h4 class="text-center">Наши партнеры на карте Украины</h4>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/views/client/main/footer.jsp"/>
    <script src="<c:url value="/resources/js/jquery.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <c:if test="${length gt 0}">
        <script src="<c:url value="/resources/js/lightgallery.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/easing.min.js"/>" type="text/javascript" async></script>
        <script src="<c:url value="/resources/js/totop.min.js"/>" type="text/javascript" async></script>
    </c:if>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
