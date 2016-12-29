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
        <meta name="author" content="Yurii Salimov">
        <title>О компании &quot;<c:out value="${company.title}"/>&quot; | <c:out value="${main_company.title}"/></title>
        <meta name="title"
              content="О компании &quot;<c:out value="${company.title}"/>&quot; | <c:out value="${main_company.title}"/>">
        <meta name="robots" content="index,follow">
        <meta name="description"
              content="Описание компании &quot;<c:out value="${company.title}"/>&quot;: <c:out value="${company.description}"/>.">
        <meta name="keywords" content="Партнер, <c:out value="${company.keywords}"/>"/>
        <c:if test="${main_company.favicon ne null}">
            <link rel="shortcut icon" href="/resources/img/<c:out value="${main_company.favicon.url}"/>"
                  type="image/x-icon">
            <link rel="icon" href="/resources/img/<c:out value="${main_company.favicon.url}"/>" type="image/x-icon">
        </c:if>
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
              rel="stylesheet" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
              rel="stylesheet" type="text/css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet"
              type="text/css">
        <link href="/resources/css/style.min.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <%-- NAVIGATION --%>
    <jsp:include page="/WEB-INF/views/client/main/navigation.jsp"/>
    <c:if test="${authorized_user ne null}">
        <c:set var="reqmap" value="/admin"/>
        <%-- Actions --%>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
            <div class="container">
                <div class="row">
                    <div class="box">
                        <div class="text-center">
                            <a href="/admin/company/new" title="Добавить нового партнера">
                                <button class="btn btn-default">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Новый
                                </button>
                            </a>&nbsp;&nbsp;
                            <a href="/admin/company/edit/<c:out value="${company.url}"/>"
                               title="Редактировать партнера &quot;<c:out value="${company.title}"/>&quot;">
                                <button class="btn btn-default">
                                    <span class="glyphicon glyphicon-edit yellow" aria-hidden="true"></span>&nbsp;Редактировать
                                </button>
                            </a>&nbsp;&nbsp;
                            <a href="/admin/company/delete/<c:out value="${company.url}"/>"
                               title="Удалить партнера &quot;<c:out value="${company.title}"/>&quot;">
                                <button class="btn btn-default">
                                    <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>&nbsp;Удалить
                                </button>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
        <div class="container">
            <div class="row">
                <div class="box">
                        <%-- Path --%>
                    <p class="path">
                        <a href="${reqmap}/" title="Перейти на главную страницу">Главная</a>
                        → <a href="${reqmap}/company/all" title="Наши партнеры">Партнеры</a>
                        → <a href="#">Описание компании &quot;<c:out value="${company.title}"/>&quot;</a>
                    </p>
                    <c:if test="${company.logo ne null}">
                        <hr>
                        <h3 class="text-center"><c:out value="${company.title}"/></h3>
                        <hr>
                    </c:if>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                            <%-- LOGO --%>
                        <jsp:include page="/WEB-INF/views/client/company/logo.jsp"/>
                    </div>
                    <c:if test="${!company.validated}">
                        <p class="no-valid" title="Не отображается для клиентов">
                            <span class="glyphicon glyphicon-eye-close red" aria-hidden="true"
                                  title="Не отображается для клиентов"></span>
                        </p>
                    </c:if>
                    <p><c:out value="${company.description}"/></p>
                    <p>${company.information}</p>
                    <div class="clearfix"></div>
                </div>
            </div>
            <div class="row">
                <div class="box">
                    <hr>
                    <h3 class="intro-text text-center">Контакты</h3>
                    <hr>
                        <%-- CONTACTS --%>
                    <jsp:include page="/WEB-INF/views/client/company/contacts.jsp"/>
                </div>
            </div>
        </div>
    </div>
        <%-- FOOTER --%>
    <jsp:include page="/WEB-INF/views/client/main/footer.jsp"/>
        <%-- Scripts --%>
    <script src="/resources/js/jquery.min.js" type="text/javascript"></script>
    <script src="/resources/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/resources/js/easing.min.js" type="text/javascript" async></script>
    <script src="/resources/js/totop.min.js" type="text/javascript" async></script>
    </body>
    </html>
</compress:html>

<!-- Yurii Salimov (yurii.alex.salimov@gmail.com) -->
