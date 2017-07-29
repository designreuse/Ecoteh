<%--
Contact information of the main company.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>Контакты | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="Контакты | <c:out value="${main_company.title}"/>">
        <meta name="description" content="Контакты и адрес нашей компании.">
        <meta name="keywords"
              content="Контакты, адрес, как проехать, карта, google maps, телефон, e-mail, электронная почта"/>
        <meta property="og:title" content="Контакты | <c:out value="${main_company.title}"/>"/>
        <meta property="og:type" content="website"/>
        <meta property="og:description" content="<c:out value="${main_company.tagline}"/>"/>
        <meta property="og:image" content="<c:url value="${main_company.logo.url}"/>"/>
        <meta name="twitter:title" content="Контакты | <c:out value="${main_company.title}"/>">
        <meta name="twitter:description" content="<c:out value="${main_company.tagline}"/>">
        <meta name="twitter:image" content="<c:url value="${main_company.logo.url}"/>">
        <meta itemprop="name" content="Контакты | <c:out value="${main_company.title}"/>"/>
        <meta itemprop="description" content="<c:out value="${main_company.tagline}"/>"/>
        <meta itemprop="image" content="<c:url value="${main_company.logo.url}"/>"/>
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
    <jsp:include page="/WEB-INF/views/home/navigation.jsp"/>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <div class="container">
            <div class="row">
                <div class="box">
                        <%-- Site page path --%>
                    <p class="path">
                        <a href="<c:url value="/"/>" title="Перейти на главную страницу">Главная</a>
                        → <a href="<c:url value="/company/main"/>"
                             title="Описание нашей компании">Описание&nbsp;компании</a>
                        → <a href="<c:url value="/contacts"/>" title="Контакты">Контакты</a>
                    </p>
                    <hr>
                    <h3 class="text-center">Контакты</h3>
                    <hr>
                        <%-- Administrator actions --%>
                    <c:if test="${authorized_user ne null}">
                        <div class="text-center">
                            <a href="<c:url value="/admin/company/edit/main"/>"
                               title="Редактировать информацию о компании &quot;<c:out value="${company.title}"/>&quot;">
                                <button class="btn btn-default">
                                    <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
                                    &nbsp;Редактировать
                                </button>
                            </a>
                        </div>
                    </c:if>
                        <%-- Contact information of the incoming company --%>
                    <jsp:include page="/WEB-INF/views/company/contacts.jsp"/>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="box">
                        <%-- Form for sending a message --%>
                    <%@include file="/WEB-INF/views/message/to_contacts.jsp" %>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
        <%-- Footer --%>
    <jsp:include page="/WEB-INF/views/home/footer.jsp"/>
        <%-- Scripts --%>
    <script src="<c:url value="/resources/js/jquery.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/mask.min.js"/>" type="text/javascript" async></script>
    <script src="<c:url value="/resources/js/easing.min.js"/>" type="text/javascript" async></script>
    <script src="<c:url value="/resources/js/totop.min.js"/>" type="text/javascript" async></script>
    </body>
    </html>
</compress:html>
