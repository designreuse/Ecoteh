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
        <meta name="author" content="Yurii Salimov (yurii.alex.salimov@gmail.com)">
        <title>Контакты | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="Контакты | <c:out value="${main_company.title}"/>">
        <meta name="robots" content="index,follow">
        <meta name="description" content="Контакты и адрес нашей компании.">
        <meta name="keywords"
              content="Контакты, адрес, как проехать, карта, google maps, телефон, e-mail, социальные сети"/>
        <c:if test="${main_company.faviconUrl ne null}">
            <link rel="shortcut icon" href="<c:url value="/resources/${main_company.faviconUrl}"/>"
                  type="image/x-icon">
            <link rel="icon" href="<c:url value="/resources/${main_company.faviconUrl}"/>" type="image/x-icon">
        </c:if>
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
              rel="stylesheet" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
              rel="stylesheet" type="text/css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet"
              type="text/css">
        <link href="<c:url value="/resources/css/style.min.css"/>" rel="stylesheet" type="text/css">
    </head>
    <body>
        <%-- NAVIGATION --%>
    <jsp:include page="/WEB-INF/views/client/main/navigation.jsp"/>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
        <div class="container">
            <div class="row">
                <div class="box">
                    <c:if test="${authorized_user ne null}">
                        <c:set var="reqmap" value="/admin"/>
                        <%-- Actions --%>
                        <div class="text-center">
                            <a href="<c:url value="/admin/company/edit/main"/>"
                               title="Редактировать информацию о компании &quot;<c:out value="${company.title}"/>&quot;">
                                <button class="btn btn-default">
                                    <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>&nbsp;Редактировать
                                </button>
                            </a>
                        </div>
                    </c:if>
                        <%-- Path --%>
                    <p class="path">
                        <a href="<c:url value="${reqmap}/"/>" title="Перейти на главную страницу">Главная</a>
                        → <a href="<c:url value="${reqmap}/company/main"/>"
                             title="Описание нашей компании">Описание компании</a>
                        → <a href="<c:url value="${reqmap}/contacts"/>" title="Контакты">Контакты</a>
                    </p>
                    <hr>
                    <h3 class="text-center">Контакты</h3>
                    <hr>
                    <jsp:include page="/WEB-INF/views/client/company/contacts.jsp"/>
                </div>
            </div>
        </div>
            <%-- Message Form --%>
        <div class="container">
            <div class="row">
                <div class="box">
                    <jsp:include page="/WEB-INF/views/client/message/to_contacts.jsp"/>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/views/client/main/footer.jsp"/>
    <script src="<c:url value="/resources/js/jquery.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/mask.min.js"/>" type="text/javascript" async></script>
    <script src="<c:url value="/resources/js/easing.min.js"/>" type="text/javascript" async></script>
    <script src="<c:url value="/resources/js/totop.min.js"/>" type="text/javascript" async></script>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
