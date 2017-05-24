<%--
Page for authorization of the user.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

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
        <meta name="robots" content="noindex,nofollow">
        <title>Авторизация</title>
        <meta name="title" content="Авторизация">
        <meta name="description" content="Авторизация">
        <link rel="shortcut icon" href="<c:url value="/resources/img/static/login.ico"/>" type="image/x-icon">
        <link rel="icon" href="<c:url value="/resources/img/static/login.ico"/>" type="image/x-icon">
            <%-- CSS styles --%>
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
              rel="stylesheet" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
              rel="stylesheet" type="text/css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet"
              type="text/css">
        <link href="<c:url value="/resources/css/style.min.css"/>" rel="stylesheet" type="text/css">
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
                        → <a href="/login">Авторизация</a>
                    </p>
                    <div class="col-xs-12 col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4 col-lg-4 col-lg-offset-4">
                        <div class="text-center">
                            <hr>
                            <h1 class="intro-text text-center">Авторизация</h1>
                                <%-- Authorization response --%>
                            <c:choose>
                                <c:when test="${param.error ne null}">
                                    <div class="alert red" role="alert">
                                        <b>Неверный логин или пароль</b><br>
                                    </div>
                                </c:when>
                                <c:when test="${param.logout ne null}">
                                    <div class="alert green" role="alert">
                                        <b>Вы вышли из системы</b><br>
                                    </div>
                                </c:when>
                            </c:choose>
                            <hr>
                                <%-- Authorization form --%>
                            <form action="<c:url value="/login"/>" method="post">
                                <div class="row">
                                    <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <label title="Введите логин в формате (A-Z, a-z, 0-9, _)">
                                            <b>
                                                <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                                                &nbsp;Имя пользователя:
                                            </b>
                                        </label>
                                        <input id="username" class="form-control" type="text" name="username" required
                                               pattern="[A-Za-z0-9_]{3,100}" minlength="3" maxlength="100" autofocus
                                               placeholder="Введите логин">
                                    </div>
                                    <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <label title="Введите пароль в формате (A-Z, a-z, 0-9)">
                                            <b>
                                                <span class="glyphicon glyphicon-check" aria-hidden="true"></span>
                                                &nbsp;Пароль:
                                            </b>
                                        </label>
                                        <input id="password" class="form-control" type="password" name="password"
                                               pattern="[A-Za-z0-9]{3,100}" minlength="3" maxlength="100" required
                                               placeholder="Введите пароль">
                                    </div>
                                    <div class="text-center form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <button type="submit" class="btn btn-default">
                                            <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                                            &nbsp;Войти
                                        </button>
                                        <%-- Link to resume access --%>
                                        <c:if test="${(param.error ne null)}">
                                            &nbsp;&nbsp;
                                            <a href="<c:url value="/forgot_password"/>"
                                               title="Напомнить логин или пароль">
                                                <span class="glyphicon glyphicon-question-sign"
                                                      aria-hidden="true"></span>
                                                &nbsp;Забыли пароль?
                                            </a>
                                        </c:if>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class=" clearfix">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <c:if test="${main_company ne null}">
        <%-- Footer --%>
        <jsp:include page="/WEB-INF/views/home/footer.jsp"/>
    </c:if>
        <%-- Scripts --%>
    <script src="<c:url value="/resources/js/jquery.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
    </body>
    </html>
</compress:html>
