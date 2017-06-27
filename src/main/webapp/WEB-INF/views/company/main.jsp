<%--
Page of the main incoming company.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

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
        <meta name="robots" content="index,follow">
        <title>О компании | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="О компании | <c:out value="${main_company.title}"/>">
        <meta name="description" content="<c:out value="${main_company.description}"/>.">
        <meta name="keywords" content="<c:out value="${main_company.keywords}"/>"/>
        <link rel="shortcut icon" href="<c:url value="${favicon.url}"/>" type="image/x-icon">
        <link rel="icon" href="<c:url value="${favicon.url}"/>" type="image/x-icon">
            <%-- CSS styles --%>
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
              rel="stylesheet" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
              rel="stylesheet" type="text/css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet"
              type="text/css">
        <link href="<c:url value="/resources/css/style.min.css"/>" rel="stylesheet" type="text/css">
        <c:set var="length" value="${fn:length(users)}"/>
        <c:if test="${length gt 0}">
            <link href="<c:url value="/resources/css/lightgallery.min.css"/>" rel="stylesheet" type="text/css">
        </c:if>
    </head>
    <body>
        <%-- Navigation bar --%>
    <jsp:include page="/WEB-INF/views/home/navigation.jsp"/>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <div class="container">
            <div class="row">
                <div class="box">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <%-- Site page path --%>
                        <p class="path">
                            <a href="<c:url value="/"/>" title="Перейти на главную страницу">Главная</a>
                            →
                            <a href="<c:url value="/company/main"/>">
                                Описание&nbsp;компании &quot;<c:out value="${company.title}"/>&quot;
                            </a>
                        </p>
                        <c:if test="${not empty company.logo.url}">
                            <hr>
                            <h3 class="text-center">О нашей компании</h3>
                            <hr>
                            <%-- Administrator actions --%>
                            <c:if test="${authorized_user ne null}">
                                <div class="text-center">
                                    <a href="<c:url value="/admin/company/edit/main"/>"
                                       title="Редактировать информацию о компании &quot;<c:out value="${company.title}"/>&quot;">
                                        <button class="btn btn-default">
                                            <span class="glyphicon glyphicon-edit yellow" aria-hidden="true"></span>
                                            &nbsp;Редактировать
                                        </button>
                                    </a>
                                </div>
                            </c:if>
                        </c:if>
                            <%-- Main company logo --%>
                        <jsp:include page="/WEB-INF/views/company/logo.jsp"/>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <p>${company.description}</p>
                        <p>${company.information}</p>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
        <c:if test="${length gt 0}">
            <div class="container">
                <div class="row">
                    <div class="box">
                        <hr>
                        <h3 class="intro-text text-center">Наша команда</h3>
                        <hr>
                            <%-- Personnel list --%>
                        <jsp:include page="/WEB-INF/views/user/list.jsp"/>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
        <%-- Footer --%>
    <jsp:include page="/WEB-INF/views/home/footer.jsp"/>
        <%-- Scripts --%>
    <script src="<c:url value="/resources/js/jquery.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <c:if test="${length gt 0}">
        <script src="<c:url value="/resources/js/lightgallery.min.js"/>" type="text/javascript"></script>
    </c:if>
    <script src="<c:url value="/resources/js/easing.min.js"/>" type="text/javascript" async></script>
    <script src="<c:url value="/resources/js/totop.min.js"/>" type="text/javascript" async></script>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
