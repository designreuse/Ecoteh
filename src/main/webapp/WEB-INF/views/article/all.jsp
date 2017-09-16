<%--
Page with all products of the company.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru" prefix="og: http://ogp.me/ns#">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Yurii Salimov (yuriy.alex.salimov@gmail.com)">
        <meta name="robots" content="noindex,nofollow">
        <title>Все товары | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="Все товары | <c:out value="${main_company.title}"/>">
        <meta name="description"
              content="Все товары <c:out value="${main_company.title} - ${main_company.tagline}"/>">
        <meta name="keywords"
              content="<c:out value="${main_company.title}"/><c:forEach items="${articles}" var="article">, <c:out value="${article.title}"/></c:forEach>"/>
        <meta property="og:title" content="Все статьи | <c:out value="${main_company.title}"/>">
        <meta property="og:type" content="product"/>
        <meta property="og:description" content="<c:out value="${main_company.title} - ${main_company.tagline}"/>">
        <meta property="og:image" content="<c:url value="${main_company.logo.url}"/>">
        <meta property="og:url" content="http://${main_company.domain}/articles">
        <meta property="fb:admins" content="100002639406164">
        <link rel="shortcut icon" href="<c:url value="${favicon.url}"/>">
        <link rel="icon" href="<c:url value="${favicon.url}"/>">
            <%-- CSS styles --%>
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
              rel="stylesheet">
        <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
              rel="stylesheet">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="<c:url value="/resources/css/style.min.css"/>" rel="stylesheet">
        <c:set var="length" value="${fn:length(articles)}"/>
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
                        →
                        <a href="<c:url value="/article/all"/>">Все&nbsp;товары</a>
                    </p>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <hr>
                        <h3 class="text-center">
                            Все товары<c:if test="${length le 0}"> - список пуст!</c:if>
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
                                </a>
                                <c:if test="${length gt 0}">
                                    &nbsp;&nbsp;
                                    <a href="<c:url value="/admin/article/delete/all"/>" title="Удалить все статьи"
                                       onclick="if(confirm('Вы точно хотите удалить все статьи? Удаленные объекты восстановлению не подлежат!')) this.submit; else return false;">
                                        <button class="btn btn-default">
                                            <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                                            &nbsp;Удалить&nbsp;все
                                        </button>
                                    </a>
                                </c:if>
                            </div>
                        </c:if>

                            <%-- Articles sorting --%>
                        <c:if test="${length gt 1}">
                            <p class="path">
                                <a href="#">Сортировка</a>:
                                <a href="<c:url value="/article/all/sort?type=price&revers=${revers}"/>"
                                   title="Сортировать по цене">
                                    <c:choose>
                                        <c:when test="${revers}">
                                            По&nbsp;цене&nbsp;
                                            <span class="glyphicon glyphicon-sort-by-order-alt"
                                                  aria-hidden="true"></span>
                                        </c:when>
                                        <c:otherwise>
                                            По&nbsp;цене&nbsp;
                                            <span class="glyphicon glyphicon-sort-by-order" aria-hidden="true"></span>
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                                |
                                <a href="<c:url value="/article/all/sort?type=title&revers=${revers}"/>"
                                   title="Сортировать по названию">
                                    <c:choose>
                                        <c:when test="${revers}">
                                            По&nbsp;названия&nbsp;
                                            <span class="glyphicon glyphicon-sort-by-alphabet-alt"
                                                  aria-hidden="true"></span>
                                        </c:when>
                                        <c:otherwise>
                                            По&nbsp;названия&nbsp;
                                            <span class="glyphicon glyphicon-sort-by-alphabet"
                                                  aria-hidden="true"></span>
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                                |
                                <a href="<c:url value="/article/all/sort?type=number&revers=${revers}"/>"
                                   title="Сортировать по номеру (артиклю)">
                                    <c:choose>
                                        <c:when test="${revers}">
                                            По&nbsp;номеру&nbsp;
                                            <span class="glyphicon glyphicon-sort-by-order-alt"
                                                  aria-hidden="true"></span>
                                        </c:when>
                                        <c:otherwise>
                                            По&nbsp;номеру&nbsp;
                                            <span class="glyphicon glyphicon-sort-by-order" aria-hidden="true"></span>
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                                |
                                <a href="<c:url value="/article/all/sort?type=date&revers=${revers}"/>"
                                   title="Сортировать по дате">
                                    <c:choose>
                                        <c:when test="${revers}">
                                            По&nbsp;дате&nbsp;<span class="glyphicon glyphicon-sort-by-attributes-alt"
                                            aria-hidden="true"></span>
                                        </c:when>
                                        <c:otherwise>
                                            По&nbsp;дате&nbsp;<span class="glyphicon glyphicon-sort-by-attributes"
                                            aria-hidden="true"></span>
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                            </p>
                        </c:if>
                            <%-- Articles list --%>
                        <jsp:include page="/WEB-INF/views/article/short_list.jsp"/>
                    </div>
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
        <script src="<c:url value="/resources/js/lightgallery.min.js"/>"></script>
        <script src="<c:url value="/resources/js/easing.min.js"/>" async></script>
        <script src="<c:url value="/resources/js/totop.min.js"/>" async></script>
    </c:if>
    </body>
    </html>
</compress:html>
