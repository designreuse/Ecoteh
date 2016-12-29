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
        <title>Главная | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="Главная | <c:out value="${main_company.title}"/>">
        <meta name="description" content="<c:out value="${main_company.title} - ${main_company.tagline}"/>.">
        <meta name="keywords" content="<c:out value="${main_company.keywords}"/>"/>
        <meta name="robots" content="index,follow">
        <meta name="google-site-verification" content="pXHeyG2mW5VlObSDdZOQPx0gWj0dEc7FYi-bBKGv3EQ"/>
        <c:if test="${main_company.favicon ne null}">
            <link rel="shortcut icon" href="/resources/img/<c:out value="${main_company.favicon.url}"/>"
                  type="image/x-icon">
            <link rel="icon" href="/resources/img/<c:out value="${main_company.favicon.url}"/>" type="image/x-icon">
        </c:if>
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
              rel="stylesheet" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
              rel="stylesheet" type="text/css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="/resources/css/style.min.css" rel="stylesheet" type="text/css">
        <link href="/resources/css/carousel.min.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <%-- LOGO --%>
    <jsp:include page="/WEB-INF/views/client/company/logo.jsp"/>
        <%-- NAVIGATION --%>
    <jsp:include page="/WEB-INF/views/client/main/navigation.jsp"/>
        <%-- Slider and Message Form --%>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
        <div class="container">
            <div class="row">
                <div class="box">
                        <%-- Slider --%>
                    <jsp:include page="/WEB-INF/views/client/main/slider.jsp"/>
                        <%-- MESSAGE FORM --%>
                    <jsp:include page="/WEB-INF/views/client/message/to_home.jsp"/>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
            <%-- LAST ARTICLES --%>
        <jsp:include page="/WEB-INF/views/client/article/litle_list.jsp"/>
            <%-- ABOUT ADVANTAGES --%>
        <jsp:include page="/WEB-INF/views/client/company/to_home.jsp"/>
            <%-- PARTNERS LOGO --%>
        <jsp:include page="/WEB-INF/views/client/company/logos_list.jsp"/>
            <%-- RESPONSES --%>
        <jsp:include page="/WEB-INF/views/client/response/list.jsp"/>
    </div>
        <%-- FOOTER --%>
    <jsp:include page="/WEB-INF/views/client/main/footer.jsp"/>
        <%-- Scripts --%>
    <script src="/resources/js/jquery.min.js" type="text/javascript"></script>
    <script src="/resources/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/resources/js/carousel.min.js" type="text/javascript"></script>
    <script src="/resources/js/mask.min.js" type="text/javascript" async></script>
    <c:if test="${authorized_user eq null}">
        <script src="https://www.google.com/recaptcha/api.js" type="text/javascript" async></script>
    </c:if>
    <script src="/resources/js/easing.min.js" type="text/javascript" async></script>
    <script src="/resources/js/totop.min.js" type="text/javascript" async></script>
    </body>
    </html>
</compress:html>

<!-- Yurii Salimov (yurii.alex.salimov@gmail.com) -->
