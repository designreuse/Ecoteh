<%--
Page of the one incoming product.
Information about one input product is displayed,
as well as a list of similar products.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru" prefix="og: http://ogp.me/ns#">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Yurii Salimov (yuriy.alex.salimov@gmail.com)">
        <meta name="robots" content="index,follow">
        <title><c:out value="${article.title} | ${main_company.title}"/></title>
        <meta name="title" content="<c:out value="${article.title} | ${main_company.title}"/>">
        <meta name="description" content="<c:out value="${article.description}"/>"/>
        <meta name="keywords" content="Статья <c:out value="${article.title}, ${article.keywords}"/>"/>
        <meta property="og:title" content="<c:out value="${article.title}"/>"/>
        <meta property="og:type" content="product"/>
        <meta property="og:description" content="<c:out value="${article.description}"/>"/>
        <meta property="og:image" content="<c:url value="${article.logo.url}"/>"/>
        <meta property="og:url" content="http://${main_company.domain}/article/${article.url}"/>
        <meta property="fb:admins" content="100002639406164" />
        <link rel="shortcut icon" href="<c:url value="${favicon.url}"/>" type="image/x-icon">
        <link rel="icon" href="<c:url value="${favicon.url}"/>" type="image/x-icon">
            <%-- CSS styles --%>
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
              rel="stylesheet">
        <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
              rel="stylesheet">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="<c:url value="/resources/css/style.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/lightgallery.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/carousel.min.css"/>" rel="stylesheet">
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
                        <c:choose>
                            <c:when test="${(article.category ne null)
                            and (article.category.validated or (authorized_user ne null))}">
                                →
                                <a href="<c:url value="/category/all"/>" title="Перейти к всем категориям">
                                    Все&nbsp;категории
                                </a>
                                →
                                <a href="<c:url value="/category/${article.category.url}"/>"
                                   title="Перейти к категории &quot;<c:out value="${article.category.title}"/>&quot;">
                                    <c:out value="${article.category.title}"/>
                                </a>
                            </c:when>
                            <c:otherwise>
                                → <a href="<c:url value="/article/all"/>" title="Все товары">Товары</a>
                            </c:otherwise>
                        </c:choose>
                        →
                        <a href="<c:url value="/article/${article.url}"/>">
                            <c:out value="${article.title}"/>
                        </a>
                    </p>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <hr>
                        <h3 class="text-center">
                            <c:out value="${article.title}"/>
                        </h3>
                        <hr>
                            <%-- Administrator actions --%>
                        <c:if test="${authorized_user ne null}">
                            <div class="text-center">
                                <a href="<c:url value="/admin/article/new"/>" title="Добавить новую статью">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                        &nbsp;Новая
                                    </button>
                                </a>&nbsp;&nbsp;
                                <a href="<c:url value="/admin/article/edit/${article.url}"/>"
                                   title="Редактировать статью &quot;<c:out value="${article.title}"/>&quot;">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-edit yellow" aria-hidden="true"></span>
                                        &nbsp;Редактировать
                                    </button>
                                </a>&nbsp;&nbsp;
                                <a href="<c:url value="/admin/article/delete/${article.url}"/>"
                                   title="Удалить статью &quot;<c:out value="${article.title}"/>&quot;"
                                   onclick="if(confirm('Вы точно хотите удалить статью &quot;<c:out
                                           value="${article.title}"/>&quot;? Удаленные объекты восстановлению не подлежат!')) this.submit; else return false;">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                                        &nbsp;Удалить
                                    </button>
                                </a>
                            </div>
                        </c:if>
                        <c:choose>
                            <c:when test="${article.price gt 0}">
                                <h4 class="green">
                                    Цена: <c:out value="${article.price}"/>&nbsp;<c:out value="${article.currency}"/>
                                </h4>
                            </c:when>
                            <c:when test="${empty article.currency}">
                                <h4 class="green">
                                    Цену уточняйте
                                </h4>
                            </c:when>
                        </c:choose>
                        <span class="little">
                            <c:if test="${!article.validated}">
                                <span class="glyphicon glyphicon-eye-close red" aria-hidden="true"
                                      title="Не отображается для клиентов"></span>&nbsp;
                            </c:if>
                            <c:out value="${article.dateToString}"/>,&nbsp;&nbsp;Артикль:
                            <a href="<c:url value="/article/num_${article.number}"/>">
                                <c:out value="${article.number}"/>
                            </a>
                        </span>
                        <p>
                            <c:choose>
                                <c:when test="${not empty article.text}">
                                    ${article.text}
                                </c:when>
                                <c:otherwise>
                                    <c:out value="${article.description}"/>
                                </c:otherwise>
                            </c:choose>
                        </p>
                        <div class="text-center share42init"></div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
            <%-- List of similar products --%>
        <%@include file="/WEB-INF/views/article/slider.jsp" %>
    </div>
        <%-- Footer --%>
    <jsp:include page="/WEB-INF/views/home/footer.jsp"/>
        <%-- Scripts --%>
    <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <c:if test="${not empty article.text}">
        <script src="<c:url value="/resources/js/lightgallery.min.js"/>"></script>
        <script src="<c:url value="/resources/js/easing.min.js"/>" async></script>
        <script src="<c:url value="/resources/js/totop.min.js"/>" async></script>
    </c:if>
        <%-- Share plugin --%>
    <script src="<c:url value="/resources/js/share/share42.min.js"/>" async></script>
    </body>
    </html>
</compress:html>
