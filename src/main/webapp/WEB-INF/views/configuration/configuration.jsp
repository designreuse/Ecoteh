<%--
Page with all project configurations.

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
        <title>Project Configuration</title>
        <meta name="title" content="Project Configuration">
        <link rel="shortcut icon" href="<c:url value="${favicon.url}"/>" type="image/x-icon">
        <link rel="icon" href="<c:url value="${favicon.url}"/>" type="image/x-icon">
            <%-- CSS styles --%>
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
              rel="stylesheet">
        <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
              rel="stylesheet">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="<c:url value="/resources/css/style.min.css"/>" rel="stylesheet">
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
                        <a href="<c:url value="/"/>" title="Перейти на главную странцу">Главная</a>
                        → <a href="<c:url value="/admin/menu"/>" title="Меню администратора">Меню</a>
                        → <a href="<c:url value="/superadmin/configuration/"/>">Конфигурация</a>
                    </p>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <hr>
                        <h3 class="text-center">
                            Конфигурация проекта
                        </h3>
                        <hr>
                    </div>
                </div>
            </div>
        </div>
    </div>
        <%-- Content Configuration --%>
    <%@include file="/WEB-INF/views/configuration/content.jsp" %>
        <%-- Content Properties Configuration --%>
    <%@include file="/WEB-INF/views/configuration/content_properties.jsp" %>
        <%-- Database Configuration --%>
    <%@include file="/WEB-INF/views/configuration/database.jsp" %>
        <%-- Securiyt Configuration --%>
    <%@include file="/WEB-INF/views/configuration/security.jsp" %>
        <%-- Log4j Configuration --%>
    <%@include file="/WEB-INF/views/configuration/log4j.jsp" %>
        <%-- Google reCaptcha Configuration --%>
    <%@include file="/WEB-INF/views/configuration/captcha.jsp" %>
        <%-- Site Verification Configuration --%>
    <%@include file="/WEB-INF/views/configuration/verification.jsp" %>
        <%-- Server Time Configuration --%>
    <%@include file="/WEB-INF/views/configuration/time.jsp" %>
    <c:if test="${main_company ne null}">
        <%-- Footer --%>
        <jsp:include page="/WEB-INF/views/home/footer.jsp"/>
    </c:if>
        <%-- Scripts --%>
    <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/easing.min.js"/>" async></script>
    <script src="<c:url value="/resources/js/totop.min.js"/>" async></script>
    </body>
    </html>
</compress:html>
