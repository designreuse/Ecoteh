<%--
Page with information about objects that are stored in cache.

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
        <meta name="robots" content="noindex,nofollow">
        <title>Кэш | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="Кэш | <c:out value="${main_company.title}"/>">
        <meta name="description" content="Информация об объектах, которых хранятся в памяти в данный момент времени.">
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
                        → <a href="<c:url value="/superadmin/cache/"/>">Кэш сервера</a>
                    </p>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <c:set var="length" value="${fn:length(objects)}"/>
                        <hr>
                        <h3 class="text-center">
                            Кэш<c:if test="${length le 0}"> пустой!</c:if>
                        </h3>
                        <hr>
                        <c:if test="${length gt 10}">
                            <%-- Cache actions --%>
                            <%@include file="/WEB-INF/views/cache/actions.jsp" %>
                        </c:if>
                    </div>
                    <c:if test="${length gt 0}">
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <%-- Table with information about objects --%>
                            <%@include file="/WEB-INF/views/cache/table.jsp" %>
                            <br>
                                <%-- Cache actions --%>
                            <%@include file="/WEB-INF/views/cache/actions.jsp" %>
                        </div>
                    </c:if>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
        <%-- Footer --%>
    <jsp:include page="/WEB-INF/views/home/footer.jsp"/>
        <%-- Scripts --%>
    <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <c:if test="${length gt 0}">
        <script src="<c:url value="/resources/js/easing.min.js"/>" async></script>
        <script src="<c:url value="/resources/js/totop.min.js"/>" async></script>
    </c:if>
    </body>
    </html>
</compress:html>
