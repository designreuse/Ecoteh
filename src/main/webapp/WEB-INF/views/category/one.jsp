<%--
Page of one categories and articles in the it.

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
        <title><c:out value="${category.title} | ${main_company.title}"/></title>
        <meta name="title" content="<c:out value="${category.title} | ${main_company.title}"/>">
        <meta name="description" content="<c:out value="${category.title} - ${category.description}"/>">
        <meta name="keywords"
              content="Категория<c:out value=", ${category.title}, ${category.keywords}"/><c:forEach items="${articles_list}" var="article"><c:out value=", ${article.title}"/></c:forEach>"/>
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
        <c:set var="length" value="${fn:length(articles)}"/>
        <c:if test="${(length gt 0) or (not empty category.description)}">
            <link href="<c:url value="/resources/css/lightgallery.min.css"/>" rel="stylesheet" type="text/css">
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
                        → <a href="<c:url value="/category/all"/>"
                             title="Перейти к всем категориям">Все&nbsp;категории</a>
                        →
                        <a href="<c:url value="/category/${category.url}"/>">
                            <c:out value="${category.title}"/>
                        </a>
                    </p>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <hr>
                        <h3 class="text-center">
                            <c:out value="${category.title}"/><c:if test="${length le 0}"> - список пуст!</c:if>
                        </h3>
                        <hr>
                            <%-- Administrator actions --%>
                        <c:if test="${authorized_user ne null}">
                            <div class="text-center">
                                <a href="<c:url value="/admin/article/new"/>" title="Добавить новую статью">
                                    <button class=" btn btn-default">
                                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                        &nbsp;Новая&nbsp;статья
                                    </button>
                                </a>&nbsp;&nbsp;
                                <a href="<c:url value="/admin/category/edit/${category.url}"/>"
                                   title="Редактировать категорию &quot;<c:out value="${category.title}"/>&quot;">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-edit yellow" aria-hidden="true"></span>
                                        &nbsp;Редактировать
                                    </button>
                                </a>&nbsp;&nbsp;
                                <a href="<c:url value="/admin/category/delete/${category.url}"/>"
                                   title="Удалить категорию &quot;<c:out value="${category.title}"/>&quot;"
                                   onclick="if(confirm('Вы точно хотите удалить статью категорию &quot;<c:out
                                           value="${category.title}"/>&quot;? Удаленные объекты восстановлению не подлежат!')) this.submit; else return false;">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                                        &nbsp;Удалить
                                    </button>
                                </a>
                            </div>
                        </c:if>
                    </div>
                        <%-- Articles sorting --%>
                    <c:if test="${length gt 1}">
                        <p class="path">
                            <a href="#">Сортировка</a>:
                            <a href="<c:url value="/category/${category.url}/sort?type=price&revers=${revers}"/>"
                               title="Сортировать по цене">
                                <c:choose>
                                    <c:when test="${revers}">
                                        По&nbsp;цене&nbsp;
                                        <span class="glyphicon glyphicon-sort-by-order-alt" aria-hidden="true"></span>
                                    </c:when>
                                    <c:otherwise>
                                        По&nbsp;цене&nbsp;
                                        <span class="glyphicon glyphicon-sort-by-order" aria-hidden="true"></span>
                                    </c:otherwise>
                                </c:choose>
                            </a>
                            |
                            <a href="<c:url value="/category/${category.url}/sort?type=title&revers=${revers}"/>"
                               title="Сортировать по названию">
                                <c:choose>
                                    <c:when test="${revers}">
                                        По&nbsp;названия&nbsp;
                                        <span class="glyphicon glyphicon-sort-by-alphabet-alt" aria-hidden="true"></span>
                                    </c:when>
                                    <c:otherwise>
                                        По&nbsp;названия&nbsp;
                                        <span class="glyphicon glyphicon-sort-by-alphabet" aria-hidden="true"></span>
                                    </c:otherwise>
                                </c:choose>
                            </a>
                            |
                            <a href="<c:url value="/category/${category.url}/sort?type=number&revers=${revers}"/>"
                               title="Сортировать по номеру (артиклю)">
                                <c:choose>
                                    <c:when test="${revers}">
                                        По&nbsp;номеру&nbsp;
                                        <span class="glyphicon glyphicon-sort-by-order-alt" aria-hidden="true"></span>
                                    </c:when>
                                    <c:otherwise>
                                        По&nbsp;номеру&nbsp;
                                        <span class="glyphicon glyphicon-sort-by-order" aria-hidden="true"></span>
                                    </c:otherwise>
                                </c:choose>
                            </a>
                            |
                            <a href="<c:url value="/category/${category.url}/sort?type=date&revers=${revers}"/>"
                               title="Сортировать по дате">
                                <c:choose>
                                    <c:when test="${revers}">
                                        По&nbsp;дате&nbsp;
                                        <span class="glyphicon glyphicon-sort-by-attributes-alt" aria-hidden="true"></span>
                                    </c:when>
                                    <c:otherwise>
                                        По&nbsp;дате&nbsp;
                                        <span class="glyphicon glyphicon-sort-by-attributes" aria-hidden="true"></span>
                                    </c:otherwise>
                                </c:choose>
                            </a>
                        </p>
                    </c:if>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
            <%-- Articles list --%>
        <jsp:include page="/WEB-INF/views/article/list.jsp"/>
            <%-- The category description --%>
        <%@include file="/WEB-INF/views/category/description.jsp" %>
    </div>
        <%-- Footer --%>
    <jsp:include page="/WEB-INF/views/home/footer.jsp"/>
        <%-- Scripts --%>
    <script src="<c:url value="/resources/js/jquery.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <c:if test="${(length gt 0) or (not empty category.description)}">
        <script src="<c:url value="/resources/js/lightgallery.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/easing.min.js"/>" type="text/javascript" async></script>
        <script src="<c:url value="/resources/js/totop.min.js"/>" type="text/javascript" async></script>
    </c:if>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
