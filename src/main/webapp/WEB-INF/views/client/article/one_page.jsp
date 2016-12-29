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
        <title><c:out value="${article.title} | ${main_company.title}"/></title>
        <meta name="title" content="<c:out value="${article.title} | ${main_company.title}"/>">
        <meta name="robots" content="index,follow">
        <meta name="description" content="<c:out value="${article.title} - ${article.description}"/>">
        <meta name="keywords" content="Статья<c:out value=", ${article.title}, ${article.keywords}"/>"/>
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
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Новая
                                </button>
                            </a>&nbsp;&nbsp;
                            <a href="/admin/article/edit/<c:out value="${article.url}"/>"
                               title="Редактировать статью &quot;<c:out value="${article.title}"/>&quot;">
                                <button class="btn btn-default">
                                    <span class="glyphicon glyphicon-edit yellow" aria-hidden="true"></span>&nbsp;Редактировать
                                </button>
                            </a>&nbsp;&nbsp;
                            <a href="/admin/article/delete/<c:out value="${article.url}"/>"
                               title="Удалить статью &quot;<c:out value="${article.title}"/>&quot;">
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
                            <c:when test="${(article.category ne null) and ((article.category.validated) or (authorized_user ne null))}">
                                <c:choose>
                                    <c:when test="${(article.category.section ne null) and ((article.category.section.validated) or (authorized_user ne null))}">
                                        → <a href="${reqmap}/section/all"
                                             title="Перейти к всем разделам">Все разделы</a>
                                        →
                                        <a href="${reqmap}/section/<c:out value="${article.category.section.url}"/>"
                                           title="Перейти к разделу &quot;<c:out value="${article.category.section.title}"/>&quot;">
                                            <c:out value="${article.category.section.title}"/>
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        → <a href="${reqmap}/category/all"
                                             title="Перейти к всем категориям">Все категории</a>
                                    </c:otherwise>
                                </c:choose>
                                →
                                <a href="${reqmap}/category/<c:out value="${article.category.url}"/>"
                                   title="Перейти к категории &quot;<c:out value="${article.category.title}"/>&quot;">
                                    <c:out value="${article.category.title}"/>
                                </a>
                            </c:when>
                            <c:otherwise>
                                → <a href="${reqmap}/article/all" title="Статьи">Статьи</a>
                            </c:otherwise>
                        </c:choose>
                        → <a href="#">Статья &quot;<c:out value="${article.title}"/>&quot;</a>
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
                        <a href="${reqmap}/article/num_<c:out value="${article.number}"/>">
                            <c:out value="${article.number}"/>
                        </a>
                    </p>
                    <c:if test="${article.mainPhoto ne null}">
                        <a href="/resources/img/<c:out value="${article.mainPhoto.url}"/>" rel="lightgallery[slides]"
                           title="<c:out value="${article.title}"/>">
                            <img class="img-responsive img-border img-left img-section"
                                 alt="<c:out value="${article.title}"/>"
                                 src="/resources/img/<c:out value="${article.mainPhoto.url}"/>">
                        </a>
                        <hr class="visible-xs">
                    </c:if>
                    <p><c:out value="${article.description}"/></p>
                    <p>${article.text}</p>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
            <%-- SLIDES --%>
        <jsp:include page="/WEB-INF/views/client/article/slides_list.jsp"/>
            <%-- VIDEOS --%>
        <jsp:include page="/WEB-INF/views/client/article/videos_list.jsp"/>
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
