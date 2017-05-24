<%--
Page with all messages.

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
        <title>Сообщения от пользователей | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="Сообщения от пользователей | <c:out value="${main_company.title}"/>">
        <meta name="description"
              content="Сообщения от пользователей &quot;<c:out value="${main_company.title}"/>&quot;.">
        <meta name="keywords" content="Сообщения от пользователей, <c:out value="${main_company.keywords}"/>"/>
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
    <div class=" col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <div class="container">
            <div class="row">
                <div class="box">
                        <%-- Site page path --%>
                    <p class="path">
                        <a href="<c:url value="/"/>" title="Перейти на главную страницу">Главная</a>
                        → <a href="<c:url value="/admin/menu"/>" title="Меню администратора">Меню</a>
                        → <a href="<c:url value="/admin/message/"/>">Сообщения</a>
                    </p>
                    <hr>
                    <c:set var="length" value="${fn:length(messages)}"/>
                    <h3 class="text-center" title="Сообщения, которые прислали клиенты">
                        Сообщения<c:if test="${length eq 0}"> - список пуст!</c:if>
                    </h3>
                    <hr>
                        <%-- Message action --%>
                    <c:if test="${length gt 0}">
                        <div class="text-center">
                            <a href="<c:url value="/admin/messages/delete/all"/>"
                               title="Удалить все сообщения от клиентов">
                                <button class=" btn btn-default">
                                    <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                                    &nbsp;Удалить&nbsp;все
                                </button>
                            </a>
                        </div>
                    </c:if>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
        <c:if test="${length gt 0}">
            <div class="container">
                <div class="row">
                    <div class="box">
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <%-- Messages list --%>
                            <%@include file="/WEB-INF/views/message/list.jsp" %>
                        </div>
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
        <script src="<c:url value="/resources/js/easing.min.js"/>" type="text/javascript" async></script>
        <script src="<c:url value="/resources/js/totop.min.js"/>" type="text/javascript" async></script>
    </c:if>
    </body>
    </html>
</compress:html>
