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
        <title><c:out value="${category.title} | ${main_company.title}"/></title>
        <meta name="title" content="<c:out value="${category.title} | ${main_company.title}"/>">
        <meta name="robots" content="index,follow">
        <meta name="description" content="<c:out value="${category.title} - ${category.description}"/>">
        <meta name="keywords"
              content="Категория<c:out value=", ${category.title}, ${category.keywords}"/><c:forEach items="${articles_list}" var="article"><c:out value=", ${article.title}"/></c:forEach>"/>
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
        <link href="/resources/css/style.min.css" rel="stylesheet" type="text/css">
        <link href="/resources/css/lightgallery.min.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <%-- NAVIGATION --%>
    <jsp:include page="/WEB-INF/views/client/main/navigation.jsp"/>
    <c:if test="${authorized_user ne null}">
        <c:set var="reqmap" value="/admin"/>
        <%-- Actions --%>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
            <div class="container">
                <div class="row">
                    <div class="box">
                        <div class="text-center">
                            <a href="/admin/article/new" title="Добавить новую статью">
                                <button class="btn btn-default">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Новая статья
                                </button>
                            </a>&nbsp;&nbsp;
                            <a href="/admin/category/new" title="Добавить новую категорию">
                                <button class="btn btn-default">
                                    <span class="glyphicon glyphicon-plus"
                                          aria-hidden="true"></span>&nbsp;Новая категория
                                </button>
                            </a>&nbsp;&nbsp;
                            <a href="/admin/category/edit/<c:out value="${category.url}"/>"
                               title="Редактировать категорию &quot;<c:out value="${category.title}"/>&quot;">
                                <button class="btn btn-default">
                                    <span class="glyphicon glyphicon-edit yellow" aria-hidden="true"></span>&nbsp;Редактировать
                                </button>
                            </a>&nbsp;&nbsp;
                            <a href="/admin/category/delete/${category.url}"
                               title="Удалить категорию &quot;<c:out value="${category.title}"/>&quot;">
                                <button class="btn btn-default">
                                    <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>&nbsp;Удалить
                                </button>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
        <div class="container">
            <div class="row">
                <div class="box">
                        <%-- Path --%>
                    <p class="path">
                        <a href="${reqmap}/" title="Перейти на главную страницу">Главная</a>
                        <c:choose>
                            <c:when test="${((category.section ne null) and (category.section.validated)) or (authorized_user ne null)}">
                                → <a href="${reqmap}/section/all" title="Перейти к всем разделам">Все разделы</a>
                                →
                                <a href="${reqmap}/section/<c:out value="${category.section.url}"/>"
                                   title="Перейти к разделу &quot;<c:out value="${category.section.title}"/>&quot;">
                                    <c:out value="${category.section.title}"/>
                                </a>
                            </c:when>
                            <c:otherwise>
                                → <a href="${reqmap}/category/all"
                                     title="Перейти к всем категориям">Все категории</a>
                            </c:otherwise>
                        </c:choose>
                        → <a href="#">Категория &quot;<c:out value="${category.title}"/>&quot;</a>
                    </p>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                        <hr>
                        <h3 class="text-center">
                            <c:out value="${category.title}"/><c:if
                                test="${fn:length(articles_list) le 0}"> - список пуст!</c:if>
                        </h3>
                        <hr>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
            <%-- ARTICLES IN THE CATEGORY --%>
        <jsp:include page="/WEB-INF/views/client/article/list.jsp"/>
            <%-- CATEGORY DESCRIPTION --%>
        <jsp:include page="/WEB-INF/views/client/category/description.jsp"/>
    </div>
        <%-- FOOTER --%>
    <jsp:include page="/WEB-INF/views/client/main/footer.jsp"/>
        <%-- Scripts --%>
    <script src="/resources/js/jquery.min.js" type="text/javascript"></script>
    <script src="/resources/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/resources/js/lightgallery.min.js" type="text/javascript"></script>
    <script src="/resources/js/easing.min.js" type="text/javascript" async></script>
    <script src="/resources/js/totop.min.js" type="text/javascript" async></script>
    </body>
    </html>
</compress:html>

<!-- Yurii Salimov (yurii.alex.salimov@gmail.com) -->
