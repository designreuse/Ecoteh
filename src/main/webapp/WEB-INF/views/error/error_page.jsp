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
        <title>Ошибка | <c:out value="${text}"/></title>
        <meta name="title" content="Ошибка | <c:out value="${text}"/>">
        <meta name="description" content="<c:out value="${message}"/>">
        <meta name="robots" content="noindex,nofollow">
        <link rel="shortcut icon" href="/resources/img/static/error_icon.ico" type="image/x-icon">
        <link rel="icon" href="/resources/img/static/error_icon.ico" type="image/x-icon">
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
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
        <div class="container">
                <%-- Error information --%>
            <div class="row">
                <div class="box">
                        <%-- Path --%>
                    <p class="path">
                        <a href="/" title="Перейти на главную страницу">Главная</a>
                        → <a href="#" title="Ошибка работы сервера">Ошибка</a>
                    </p>
                    <div class="col-xs-12 col-sm-12 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                        <div class="text-center">
                            <hr>
                            <h2 class="intro-text">Упс... Ошибочка!</h2>
                            <hr>
                            <br><br>
                                <%-- Error text --%>
                            <div class="error-text"><b><c:out value="${text}"/></b></div>
                            <br><br>
                                <%-- Error message --%>
                            <div class="error-message"><c:out value="${message}"/></div>
                        </div>
                        <br><br><br><br>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
    </body>
    </html>
</compress:html>

<!-- Yurii Salimov (yurii.alex.salimov@gmail.com) -->
