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
        <meta name="author" content="Yurii Salimov (yurii.alex.salimov@gmail.com)">
        <title>О компании | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="О компании | <c:out value="${main_company.title}"/>">
        <meta name="robots" content="index,follow">
        <meta name="description"
              content="Описание компании &quot;<c:out value="${main_company.title}"/>&quot;: <c:out value="${main_company.description}"/>.">
        <meta name="keywords" content="Главная компания, <c:out value="${main_company.keywords}"/>"/>
        <c:if test="${main_company.favicon ne null}">
            <link rel="shortcut icon" href="<c:url value="/resources/${main_company.favicon.url}"/>"
                  type="image/x-icon">
            <link rel="icon" href="<c:url value="/resources/${main_company.favicon.url}"/>" type="image/x-icon">
        </c:if>
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
              rel="stylesheet" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
              rel="stylesheet" type="text/css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet"
              type="text/css">
        <link href="<c:url value="/resources/css/style.min.css"/>" rel="stylesheet" type="text/css">
        <c:set var="length" value="${fn:length(users_list)}"/>
        <c:if test="${length gt 0}">
            <link href="<c:url value="/resources/css/lightgallery.min.css"/>" rel="stylesheet" type="text/css">
        </c:if>
    </head>
    <body>
        <%-- NAVIGATION --%>
    <jsp:include page="/WEB-INF/views/client/main/navigation.jsp"/>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
        <div class="container">
            <div class="row">
                <div class="box">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                        <c:if test="${authorized_user ne null}">
                            <c:set var="reqmap" value="/admin"/>
                            <%-- Actions --%>
                            <div class="text-center">
                                <a href="<c:url value="/admin/company/edit/main"/>"
                                   title="Редактировать информацию о компании &quot;<c:out value="${company.title}"/>&quot;">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-edit yellow"
                                              aria-hidden="true"></span>&nbsp;Редактировать
                                    </button>
                                </a>
                            </div>
                        </c:if>
                            <%-- Path --%>
                        <p class="path">
                            <a href="<c:url value="${reqmap}/"/>" title="Перейти на главную страницу">Главная</a>
                            → <a href="#">Описание компании &quot;<c:out value="${company.title}"/>&quot;</a>
                        </p>
                        <c:if test="${company.logo ne null}">
                            <hr>
                            <h3 class="text-center">О нашей компании</h3>
                            <hr>
                        </c:if>
                            <%-- LOGO --%>
                        <jsp:include page="/WEB-INF/views/client/company/logo.jsp"/>
                        <p>${company.description}</p>
                        <p>${company.information}</p>
                        <p>${company.advantages}</p>
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
                            <%-- USER LIST --%>
                        <jsp:include page="/WEB-INF/views/client/user/list.jsp"/>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
        <%-- FOOTER --%>
    <jsp:include page="/WEB-INF/views/client/main/footer.jsp"/>
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

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
