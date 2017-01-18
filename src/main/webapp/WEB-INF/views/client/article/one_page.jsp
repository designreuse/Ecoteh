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
        <title><c:out value="${article.title} | ${main_company.title}"/></title>
        <meta name="title" content="<c:out value="${article.title} | ${main_company.title}"/>">
        <meta name="robots" content="index,follow">
        <meta name="description" content="<c:out value="${article.title} - ${article.description}"/>">
        <meta name="keywords" content="Статья<c:out value=", ${article.title}, ${article.keywords}"/>"/>
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
        <link href="<c:url value="/resources/css/lightgallery.min.css"/>" rel="stylesheet" type="text/css">
    </head>
    <body>
    <jsp:include page="/WEB-INF/views/client/main/navigation.jsp"/>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
        <div class="container">
            <div class="row">
                <div class="box">
                    <c:if test="${authorized_user ne null}">
                        <c:set var="reqmap" value="/admin"/>
                        <div class="text-center">
                            <a href="<c:url value="/admin/article/new"/>" title="Добавить новую статью">
                                <button class="btn btn-default">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Новая
                                </button>
                            </a>&nbsp;&nbsp;
                            <a href="<c:url value="/admin/article/edit/${article.url}"/>"
                               title="Редактировать статью &quot;<c:out value="${article.title}"/>&quot;">
                                <button class="btn btn-default">
                                    <span class="glyphicon glyphicon-edit yellow"
                                          aria-hidden="true"></span>&nbsp;Редактировать
                                </button>
                            </a>&nbsp;&nbsp;
                            <a href="<c:url value="/admin/article/delete/${article.url}"/>"
                               title="Удалить статью &quot;<c:out value="${article.title}"/>&quot;">
                                <button class="btn btn-default">
                                    <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>&nbsp;Удалить
                                </button>
                            </a>
                        </div>
                    </c:if>
                    <p class="path">
                        <a href="<c:url value="${reqmap}/"/>" title="Перейти на главную страницу">Главная</a>
                        <c:choose>
                            <c:when test="${(article.category ne null) and ((article.category.validated) or (authorized_user ne null))}">
                                → <a href="<c:url value="${reqmap}/category/all"/>"
                                     title="Перейти к всем категориям">Все категории</a>
                                →
                                <a href="<c:url value="${reqmap}/category/${article.category.url}"/>"
                                   title="Перейти к категории &quot;<c:out value="${article.category.title}"/>&quot;">
                                    <c:out value="${article.category.title}"/>
                                </a>
                            </c:when>
                            <c:otherwise>
                                → <a href="<c:url value="${reqmap}/article/all"/>" title="Статьи">Статьи</a>
                            </c:otherwise>
                        </c:choose>
                        → <a href="#"><c:out value="${article.title}"/></a>
                    </p>
                    <hr>
                    <h3 class="text-center"><c:out value="${article.title}"/></h3>
                    <hr>
                    <p class="little">
                        <c:if test="${!article.validated}">
                            <span class="glyphicon glyphicon-eye-close red" aria-hidden="true"
                                  title="Не отображается для клиентов"></span>&nbsp;
                        </c:if>
                        <c:out value="${article.dateToString}"/>,&nbsp;&nbsp;Артикль:
                        <a href="<c:url value="${reqmap}/article/num_${article.number}"/>">
                            <c:out value="${article.number}"/>
                        </a>
                    </p>
                    <p>${article.text}</p>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/views/client/main/footer.jsp"/>
    <script src="<c:url value="/resources/js/jquery.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <c:if test="${article.text ne null}">
        <script src="<c:url value="/resources/js/lightgallery.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/easing.min.js"/>" type="text/javascript" async></script>
        <script src="<c:url value="/resources/js/totop.min.js"/>" type="text/javascript" async></script>
    </c:if>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
