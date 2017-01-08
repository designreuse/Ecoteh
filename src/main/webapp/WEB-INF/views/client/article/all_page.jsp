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
        <title>Все статьи | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="Все статьи | <c:out value="${main_company.title}"/>">
        <meta name="robots" content="noindex,nofollow">
        <meta name="description"
              content="Все статьи <c:out value="${main_company.title}"/> - <c:out value="${main_company.tagline}"/>.">
        <meta name="keywords"
              content="<c:out value="${main_company.title}"/><c:forEach items="${articles_list}" var="article">, <c:out value="${article.title}"/></c:forEach>"/>
        <c:if test="${main_company.favicon ne null}">
            <link rel="shortcut icon" href="/resources/img/<c:out value="${main_company.favicon.url}"/>"
                  type="image/x-icon">
            <link rel="icon" href="/resources/img/<c:out value="${main_company.favicon.url}"/>" type="image/x-icon">
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
                    <c:set var="length" value="${fn:length(articles_list)}"/>
                    <c:if test="${authorized_user ne null}">
                        <c:set var="reqmap" value="/admin"/>
                        <%-- Actions --%>
                        <div class="text-center">
                            <a href="/admin/article/new" title="Добавить новую статью">
                                <button class="btn btn-default">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Новая
                                </button>
                            </a>
                            <c:if test="${length gt 0}">
                                &nbsp;&nbsp;
                                <a href="/admin/article/delete/all" title="Удалить все статьи">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-remove red"
                                              aria-hidden="true"></span>&nbsp;Удалить все
                                    </button>
                                </a>
                            </c:if>
                        </div>
                    </c:if>
                        <%-- Path --%>
                    <p class="path">
                        <a href="${reqmap}/" title="Перейти на главную страницу">Главная</a>
                        → <a href="#" title="Все статьи">Cтатьи</a>
                    </p>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                        <hr>
                        <h3 class="text-center">Статьи<c:if test="${length le 0}"> - список пуст!</c:if></h3>
                        <hr>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
            <%-- ARTICLES --%>
        <jsp:include page="/WEB-INF/views/client/article/list.jsp"/>
    </div>
        <%-- FOOTER --%>
    <jsp:include page="/WEB-INF/views/client/main/footer.jsp"/>
        <%-- Scripts --%>
    <script src="/resources/js/jquery.min.js" type="text/javascript"></script>
    <script src="/resources/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/resources/js/easing.min.js" type="text/javascript" async></script>
    <script src="/resources/js/totop.min.js" type="text/javascript" async></script>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
