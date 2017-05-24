<%--
Page with a exception information.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <%-- Exception description --%>
    <c:choose>
        <c:when test="${status eq 400}">
            <c:set var="description" value="Ошибка ${status}. Объект не найден!"/>
        </c:when>
        <c:when test="${status eq 401}">
            <c:set var="description" value="Ошибка ${status}. Ошибка в запросе!"/>
        </c:when>
        <c:when test="${status eq 403}">
            <c:set var="description" value="Ошибка ${status}. Нет прав доступа!"/>
        </c:when>
        <c:when test="${status eq 404}">
            <c:set var="description" value="Ошибка ${status}. Не найдено!"/>
        </c:when>
        <c:when test="${status eq 405}">
            <c:set var="description" value="Ошибка ${status}. Запрещенный запрос!"/>
        </c:when>
        <c:when test="${status eq 406}">
            <c:set var="description" value="Ошибка ${status}. Ошибка аргументов!"/>
        </c:when>
        <c:when test="${status eq 409}">
            <c:set var="description" value="Ошибка ${status}. Объект уже существует!"/>
        </c:when>
        <c:when test="${status eq 500}">
            <c:set var="description"
                   value="Ошибка ${status}. Временные неполадки с сервером... Приносим свои извинения!"/>
        </c:when>
        <c:otherwise>
            <c:set var="description" value="Неизвестная ошибка..."/>
        </c:otherwise>
    </c:choose>

    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Yurii Salimov (yuriy.alex.salimov@gmail.com)">
        <meta name="robots" content="noindex,nofollow">
        <title><c:out value="${description}"/></title>
        <meta name="title" content="<c:out value="${description}"/>">
        <meta name="description" content="<c:out value="${description}"/> - <c:out value="${message}"/>">
        <link rel="shortcut icon" href="<c:url value="/resources/img/static/error.ico"/>" type="image/x-icon">
        <link rel="icon" href="<c:url value="/resources/img/static/error.ico"/>" type="image/x-icon">
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
                        → <a href="#" title="Ошибка работы сервера">Ошибка</a>
                    </p>
                    <div class="col-xs-12 col-sm-12 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1">
                        <div class="text-center">
                            <hr>
                            <h2 class="intro-text">Упс... Ошибочка!</h2>
                            <hr>
                            <br><br>
                            <div class="error-text">
                                <b><c:out value="${description}"/></b>
                            </div>
                            <br><br>
                            <div class="error-message">
                                <c:out value="${message}"/>
                            </div>
                        </div>
                        <br><br><br><br>
                    </div>
                    <div class="clearfix"></div>
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
