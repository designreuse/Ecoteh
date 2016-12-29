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
        <meta name="author" content="Yurii Salimov">
        <title>Персонал | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="Персонал | <c:out value="${main_company.title}"/>">
        <meta name="robots" content="noindex,nofollow">
        <meta name="description" content="На этой странице отображаются весь персонал компании.">
        <meta name="keywords"
              content="<c:out value="${main_company.title}"/><c:forEach items="${users}" var="user">, <c:out value="${user.name}"/></c:forEach>"/>
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
        <c:set var="length" value="${fn:length(users_list)}"/>
        <c:if test="${length gt 0}">
            <link href="/resources/css/lightgallery.min.css" rel="stylesheet" type="text/css">
        </c:if>
    </head>
    <body>
        <%-- NAVIGATION --%>
    <jsp:include page="/WEB-INF/views/client/main/navigation.jsp"/>
        <%-- Actions --%>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
        <div class="container">
            <div class="row">
                <div class="box">
                    <div class="text-center">
                        <a href="/admin/user/new" title="Добавить нового пользователя">
                            <button class="btn btn-default">
                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Новый
                            </button>
                        </a>
                        <c:if test="${length gt 0}">
                            &nbsp;&nbsp;
                            <a href="/admin/user/delete/all" title="Удалить всех пользователей">
                                <button class="btn btn-default">
                                    <span class="glyphicon glyphicon-remove red"
                                          aria-hidden="true"></span>&nbsp;Удалить всех
                                </button>
                            </a>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
        <div class="container">
            <div class="row">
                <div class="box">
                        <%-- Path --%>
                    <p class="path">
                        <a href="/admin/" title="Перейти на главную страницу">Главная</a>
                        → <a href="/admin/menu" title="Меню администратора">Меню</a> → Персонал
                    </p>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                        <hr>
                        <h3 class="text-center">Персонал<c:if test="${length le 0}"> - список пуст!</c:if></h3>
                        <hr>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
            <%-- Users --%>
        <c:if test="${length gt 0}">
            <div class="container">
                <div class="row">
                    <div class="box">
                            <%-- USER LIST --%>
                        <jsp:include page="/WEB-INF/views/client/user/list.jsp"/>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
            <%-- Message form --%>
            <div class="container">
                <div class="row">
                    <div class="box">
                        <jsp:include page="/WEB-INF/views/admin/user/message.jsp"/>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
        <%-- FOOTER --%>
    <jsp:include page="/WEB-INF/views/client/main/footer.jsp"/>
        <%-- Scripts --%>
    <script src="/resources/js/jquery.min.js" type="text/javascript"></script>
    <script src="/resources/js/bootstrap.min.js" type="text/javascript"></script>
    <c:if test="${length gt 0}">
        <script src="/resources/js/lightgallery.min.js" type="text/javascript"></script>
    </c:if>
    <script src="/resources/js/easing.min.js" type="text/javascript" async></script>
    <script src="/resources/js/totop.min.js" type="text/javascript" async></script>
    </body>
    </html>
</compress:html>

<!-- Yurii Salimov (yurii.alex.salimov@gmail.com) -->
