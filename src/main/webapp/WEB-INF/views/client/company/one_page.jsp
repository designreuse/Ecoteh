<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Yurii Salimov (yuriy.alex.salimov@gmail.com)">
        <title>О компании &quot;<c:out value="${company.title}"/>&quot; | <c:out value="${main_company.title}"/></title>
        <meta name="title"
              content="О компании &quot;<c:out value="${company.title}"/>&quot; | <c:out value="${main_company.title}"/>">
        <meta name="robots" content="index,follow">
        <meta name="description"
              content="Описание компании &quot;<c:out value="${company.title}"/>&quot;: <c:out value="${company.description}"/>.">
        <meta name="keywords" content="Партнер, <c:out value="${company.keywords}"/>"/>
        <c:if test="${main_company.faviconUrl ne null}">
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
    </head>
    <body>
    <jsp:include page="/WEB-INF/views/client/main/navigation.jsp"/>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
        <div class="container">
            <div class="row">
                <div class="box">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                        <c:if test="${authorized_user ne null}">
                            <c:set var="reqmap" value="/admin"/>
                            <div class="text-center">
                                <a href="<c:url value="/admin/company/new"/>" title="Добавить нового партнера">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Новый
                                    </button>
                                </a>&nbsp;&nbsp;
                                <a href="<c:url value="/admin/company/edit/${company.url}"/>"
                                   title="Редактировать партнера &quot;<c:out value="${company.title}"/>&quot;">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-edit yellow" aria-hidden="true"></span>&nbsp;Редактировать
                                    </button>
                                </a>&nbsp;&nbsp;
                                <a href="<c:url value="/admin/company/delete/${company.url}"/>"
                                   title="Удалить партнера &quot;<c:out value="${company.title}"/>&quot;">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>&nbsp;Удалить
                                    </button>
                                </a>
                            </div>
                        </c:if>
                        <p class="path">
                            <a href="<c:url value="${reqmap}/"/>" title="Перейти на главную страницу">Главная</a>
                            → <a href="<c:url value="${reqmap}/company/all"/>" title="Наши партнеры">Партнеры</a>
                            → <a href="#"><c:out value="${company.title}"/></a>
                        </p>
                        <c:if test="${company.logoUrl ne null}">
                            <hr>
                            <h3 class="text-center"><c:out value="${company.title}"/></h3>
                            <hr>
                        </c:if>
                        <jsp:include page="/WEB-INF/views/client/company/logo.jsp"/>
                        <c:if test="${!company.validated}">
                            <p class="no-valid" title="Не отображается для клиентов">
                                <span class="glyphicon glyphicon-eye-close red" aria-hidden="true"
                                      title="Не отображается для клиентов"></span>
                            </p>
                        </c:if>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                        <p>${company.information}</p>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
            <div class="row">
                <div class="box">
                    <hr>
                    <h3 class="intro-text text-center">Контакты</h3>
                    <hr>
                    <jsp:include page="/WEB-INF/views/client/company/contacts.jsp"/>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/views/client/main/footer.jsp"/>
    <script src="<c:url value="/resources/js/jquery.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/easing.min.js"/>" type="text/javascript" async></script>
    <script src="<c:url value="/resources/js/totop.min.js"/>" type="text/javascript" async></script>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
