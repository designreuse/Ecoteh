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
        <title><c:out value="${category.title} | ${main_company.title}"/></title>
        <meta name="title" content="<c:out value="${category.title} | ${main_company.title}"/>">
        <meta name="robots" content="index,follow">
        <meta name="description" content="<c:out value="${category.title} - ${category.description}"/>">
        <meta name="keywords"
              content="Категория<c:out value=", ${category.title}, ${category.keywords}"/><c:forEach items="${articles_list}" var="article"><c:out value=", ${article.title}"/></c:forEach>"/>
        <c:if test="${main_company.faviconUrl ne null}">
            <link rel="shortcut icon" href="<c:url value="${main_company.faviconUrl}"/>" type="image/x-icon">
            <link rel="icon" href="<c:url value="${main_company.faviconUrl}"/>" type="image/x-icon">
        </c:if>
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
              rel="stylesheet" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
              rel="stylesheet" type="text/css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet"
              type="text/css">
        <link href="<c:url value="/resources/css/style.min.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/css/lightgallery.min.css"/>" rel="stylesheet" type="text/css">
    </head>
    <body>
    <jsp:include page="/WEB-INF/views/client/main/navigation.jsp"/>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <div class="container">
            <div class="row">
                <div class="box">
                    <c:if test="${authorized_user ne null}">
                        <c:set var="reqmap" value="/admin"/>
                        <div class="text-center">
                            <a href="<c:url value="/admin/article/new"/>" title="Добавить новую статью">
                                <button class=" btn btn-default">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Новая статья
                                </button>
                            </a>&nbsp;&nbsp;
                            <a href="<c:url value="/admin/category/edit/${category.url}"/>"
                               title="Редактировать категорию &quot;<c:out value="${category.title}"/>&quot;">
                                <button class="btn btn-default">
                                    <span class="glyphicon glyphicon-edit yellow"
                                          aria-hidden="true"></span>&nbsp;Редактировать
                                </button>
                            </a>&nbsp;&nbsp;
                            <a href="<c:url value="/admin/category/delete/${category.url}"/>"
                               title="Удалить категорию &quot;<c:out value="${category.title}"/>&quot;">
                                <button class="btn btn-default">
                                    <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>&nbsp;Удалить
                                </button>
                            </a>
                        </div>
                    </c:if>
                    <p class="path">
                        <a href="<c:url value="${reqmap}/"/>" title="Перейти на главную страницу">Главная</a>
                        → <a href="<c:url value="${reqmap}/category/all"/>"
                             title="Перейти к всем категориям">Все категории</a>
                        →
                        <a href="<c:url value="${reqmap}/category/${category.url}"/>">
                            <c:out value="${category.title}"/>
                        </a>
                    </p>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <hr>
                        <c:set var="length" value="${fn:length(articles_list)}"/>
                        <h3 class="text-center">
                            <c:out value="${category.title}"/><c:if
                                test="${length le 0}"> - список пуст!</c:if>
                        </h3>
                        <hr>
                    </div>
                    <c:if test="${length gt 1}">
                        <p class="path">
                            <a href="#">Сортировка</a>:
                            <a href="<c:url value="${reqmap}/category/${category.url}/sort?type=title&revers=${revers}"/>"
                               title="Сортировать по названию">
                                <c:choose>
                                    <c:when test="${revers}">
                                        По названия&nbsp;<span class="glyphicon glyphicon-sort-by-alphabet-alt"
                                        aria-hidden="true"></span>
                                    </c:when>
                                    <c:otherwise>
                                        По названия&nbsp;<span class="glyphicon glyphicon-sort-by-alphabet"
                                        aria-hidden="true"></span>
                                    </c:otherwise>
                                </c:choose>
                            </a>
                            |
                            <a href="<c:url value="${reqmap}/category/${category.url}/sort?type=number&revers=${revers}"/>"
                               title="Сортировать по номеру (артиклю)">
                                <c:choose>
                                    <c:when test="${revers}">
                                        По номеру&nbsp;<span class="glyphicon glyphicon-sort-by-order-alt"
                                        aria-hidden="true"></span>
                                    </c:when>
                                    <c:otherwise>
                                        По номеру&nbsp;<span class="glyphicon glyphicon-sort-by-order"
                                        aria-hidden="true"></span>
                                    </c:otherwise>
                                </c:choose>
                            </a>
                            |
                            <a href="<c:url value="${reqmap}/category/${category.url}/sort?type=date&revers=${revers}"/>"
                               title="Сортировать по дате">
                                <c:choose>
                                    <c:when test="${revers}">
                                        По дате&nbsp;<span class="glyphicon glyphicon-sort-by-attributes-alt"
                                        aria-hidden="true"></span>
                                    </c:when>
                                    <c:otherwise>
                                        По дате&nbsp;<span class="glyphicon glyphicon-sort-by-attributes"
                                        aria-hidden="true"></span>
                                    </c:otherwise>
                                </c:choose>
                            </a>
                        </p>
                    </c:if>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/client/article/list.jsp"/>
        <jsp:include page="/WEB-INF/views/client/category/description.jsp"/>
    </div>
    <jsp:include page="/WEB-INF/views/client/main/footer.jsp"/>
    <script src="<c:url value="/resources/js/jquery.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/lightgallery.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/easing.min.js"/>" type="text/javascript" async></script>
    <script src="<c:url value="/resources/js/totop.min.js"/>" type="text/javascript" async></script>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
