<%--
Site's home page.
Displays product categories, main company description,
partner-companies logos and client responses.

Yurii Salimov (yurii.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru" prefix="og: http://ogp.me/ns#">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Yurii Salimov (yuriy.alex.salimov@gmail.com)">
        <meta name="robots" content="index,follow">
        <title>Главная | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="Главная | <c:out value="${main_company.title}"/>">
        <meta name="description" content="<c:out value="${main_company.title} - ${main_company.tagline}"/>.">
        <meta name="keywords" content="<c:out value="${main_company.keywords}"/>">
        <meta property="og:title" content="<c:out value="${main_company.title}"/>">
        <meta property="og:type" content="website"/>
        <meta property="og:description" content="<c:out value="${main_company.tagline}"/>">
        <meta property="og:image" content="<c:url value="${main_company.logo.url}"/>">
        <meta property="og:url" content="http://${main_company.domain}/index">
        <meta property="fb:admins" content="100002639406164">
            <%-- Site verification --%>
        <%@include file="/WEB-INF/views/seo/verification.jsp" %>
        <link rel="shortcut icon" href="<c:url value="${favicon.url}"/>">
        <link rel="icon" href="<c:url value="${favicon.url}"/>">
            <%-- CSS styles --%>
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
              rel="stylesheet">
        <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
              rel="stylesheet">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="<c:url value="/resources/css/style.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/carousel.min.css"/>" rel="stylesheet">
    </head>
    <body>
        <%-- Main company logo --%>
    <jsp:include page="/WEB-INF/views/company/logo.jsp"/>
        <%-- Navigation bar --%>
    <jsp:include page="/WEB-INF/views/home/navigation.jsp"/>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <div class="container">
            <div class="row">
                <div class="box">
                        <%-- Main company sliders --%>
                    <%@include file="/WEB-INF/views/home/slider.jsp" %>
                        <%-- Quick message from client --%>
                    <%@include file="/WEB-INF/views/message/to_home.jsp" %>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <c:choose>
            <c:when test="${category ne null}">
                <%-- Category --%>
                <%@include file="/WEB-INF/views/category/to_home.jsp" %>
            </c:when>
            <c:when test="${fn:length(categories) gt 0}">
                <%-- Categories list --%>
                <div class="container">
                    <div class="row">
                        <div class="box">
                            <hr>
                            <h3 class="intro-text text-center">
                                <a href="<c:url value="/category/all"/>" title="Категории товаров">Товары</a>
                            </h3>
                            <hr>
                                <%-- Product categories --%>
                            <jsp:include page="/WEB-INF/views/category/list.jsp"/>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </c:when>
        </c:choose>
            <%-- Main company description --%>
        <%@include file="/WEB-INF/views/company/to_home.jsp" %>
            <%-- Partner-companies logo list  --%>
        <%@include file="/WEB-INF/views/company/logos_list.jsp" %>
            <%-- Client responses--%>
        <%@include file="/WEB-INF/views/response/list_to_home.jsp" %>

        <div class="container">
            <div class="row">
                <div class="box">
                    <hr>
                    <h3 class="text-center">Контакты</h3>
                    <hr>
                        <%-- Contact information of the main company --%>
                    <jsp:include page="/WEB-INF/views/company/contacts.jsp"/>
                </div>
            </div>
        </div>
    </div>
        <%-- Footer --%>
    <jsp:include page="/WEB-INF/views/home/footer.jsp"/>
        <%-- Scripts --%>
    <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <c:if test="${fn:length(slides) gt 0}">
        <script src="<c:url value="/resources/js/carousel.min.js"/>"></script>
    </c:if>
    <script src="<c:url value="/resources/js/mask.min.js"/>" async></script>
    <script src="<c:url value="/resources/js/easing.min.js"/>" async></script>
    <script src="<c:url value="/resources/js/totop.min.js"/>" async></script>
    </body>
    </html>
</compress:html>
