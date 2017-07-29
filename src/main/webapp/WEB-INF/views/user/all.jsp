<%--
Page with all users.

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
        <title>Персонал | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="Персонал | <c:out value="${main_company.title}"/>">
        <link rel="shortcut icon" href="<c:url value="${favicon.url}"/>" type="image/x-icon">
        <link rel="icon" href="<c:url value="${favicon.url}"/>" type="image/x-icon">
            <%-- CSS styles --%>
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
              rel="stylesheet">
        <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
              rel="stylesheet">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="<c:url value="/resources/css/style.min.css"/>" rel="stylesheet">
        <c:set var="length" value="${fn:length(users)}"/>
        <c:if test="${length gt 0}">
            <link href="<c:url value="/resources/css/lightgallery.min.css"/>" rel="stylesheet">
        </c:if>
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
                        <a href="<c:url value="/"/>" title="Перейти на главную страницу">Главная</a>
                        → <a href="<c:url value="/admin/menu"/>" title="Меню администратора">Меню</a>
                        → <a href="<c:url value="/admin/user/all"/>">Персонал</a>
                    </p>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <hr>
                        <h3 class="text-center">
                            Персонал<c:if test="${length le 0}"> - список пуст!</c:if>
                        </h3>
                        <hr>
                            <%-- User action --%>
                        <div class="text-center">
                            <a href="<c:url value="/admin/user/new"/>" title="Добавить нового пользователя">
                                <button class="btn btn-default">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Новый
                                </button>
                            </a>
                            <c:if test="${length gt 0}">
                                &nbsp;&nbsp;
                                <a href="<c:url value="/admin/user/delete/all"/>" title="Удалить всех пользователей"
                                   onclick="if(confirm('Вы точно хотите удалить всех пользователей? Удаленные объекты восстановлению не подлежат!')) this.submit; else return false;">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                                        &nbsp;Удалить&nbsp;всех
                                    </button>
                                </a>
                            </c:if>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
        <c:if test="${length gt 0}">
            <div class="container">
                <div class="row">
                    <div class="box">
                            <%-- Users list --%>
                        <jsp:include page="/WEB-INF/views/user/list.jsp"/>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="box">
                            <%-- Form for sending a message to users --%>
                        <%@include file="/WEB-INF/views/message/to_user.jsp" %>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
        <%-- Footer --%>
    <jsp:include page="/WEB-INF/views/home/footer.jsp"/>
        <%-- Scripts --%>
    <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <c:if test="${length gt 0}">
        <script src="<c:url value="/resources/js/lightgallery.min.js"/>"></script>
    </c:if>
    <script src="<c:url value="/resources/js/easing.min.js"/>" async></script>
    <script src="<c:url value="/resources/js/totop.min.js"/>" async></script>
    </body>
    </html>
</compress:html>
