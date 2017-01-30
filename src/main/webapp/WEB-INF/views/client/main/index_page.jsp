<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
        <title>Главная | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="Главная | <c:out value="${main_company.title}"/>">
        <meta name="description" content="<c:out value="${main_company.title} - ${main_company.tagline}"/>.">
        <meta name="keywords" content="<c:out value="${main_company.keywords}"/>"/>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Yurii Salimov (yuriy.alex.salimov@gmail.com)">
        <meta name="robots" content="index,follow">
        <jsp:include page="/WEB-INF/views/client/main/verification.jsp"/>
        <c:if test="${main_company.faviconUrl ne null}">
            <link rel="shortcut icon" href="<c:url value="${main_company.faviconUrl}"/>" type="image/x-icon">
            <link rel="icon" href="<c:url value="${main_company.faviconUrl}"/>" type="image/x-icon">
        </c:if>
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
              rel="stylesheet" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
              rel="stylesheet" type="text/css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="<c:url value="/resources/css/style.min.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/css/carousel.min.css"/>" rel="stylesheet" type="text/css">
    </head>
    <body>
    <jsp:include page="/WEB-INF/views/client/company/logo.jsp"/>
    <jsp:include page="/WEB-INF/views/client/main/navigation.jsp"/>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
        <div class="container">
            <div class="row">
                <div class="box">
                    <jsp:include page="/WEB-INF/views/client/main/slider.jsp"/>
                    <jsp:include page="/WEB-INF/views/client/message/to_home.jsp"/>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
        <c:if test="${fn:length(categories) gt 0}">
            <div class="container">
                <div class="row">
                    <div class="box">
                        <hr>
                        <h3 class="intro-text text-center">
                            <a href="${reqmap}/category/all" title="Категории товаров">Товары</a>
                        </h3>
                        <hr>
                        <jsp:include page="/WEB-INF/views/client/category/list.jsp"/>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
        </c:if>
        <jsp:include page="/WEB-INF/views/client/company/to_home.jsp"/>
        <jsp:include page="/WEB-INF/views/client/company/logos_list.jsp"/>
        <jsp:include page="/WEB-INF/views/client/response/list.jsp"/>
    </div>
    <jsp:include page="/WEB-INF/views/client/main/footer.jsp"/>
    <script src="<c:url value="/resources/js/jquery.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <c:if test="${fn:length(company.slidesList) gt 0}">
        <script src="<c:url value="/resources/js/carousel.min.js"/>" type="text/javascript"></script>
    </c:if>
    <script src="<c:url value="/resources/js/mask.min.js"/>" type="text/javascript" async></script>
    <script src="<c:url value="/resources/js/easing.min.js"/>" type="text/javascript" async></script>
    <script src="<c:url value="/resources/js/totop.min.js"/>" type="text/javascript" async></script>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
