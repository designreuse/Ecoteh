<%--
Page with all article categries of the company.

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
        <meta name="robots" content="index,follow">
        <title>Категории товаров | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="Категории товаров | <c:out value="${main_company.title}"/>">
        <meta name="description" content="Категории товаров <c:out value="${main_company.title}"/>.">
        <meta name="keywords"
              content="Все категории<c:forEach items="${categories_list}" var="category">, <c:out value="${category.title}"/></c:forEach>">
        <meta property="og:title" content="Категории товаров | <c:out value="${main_company.title}"/>">
        <meta property="og:type" content="website"/>
        <meta property="og:description" content="<c:out value="${main_company.title} - ${main_company.tagline}"/>">
        <meta property="og:image" content="<c:url value="${main_company.logo.url}"/>">
        <meta property="og:url" content="http://${main_company.domain}/categories">
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
    </head>
    <body>
        <%-- Navigation bar --%>
    <jsp:include page="/WEB-INF/views/home/navigation.jsp"/>
    <c:set var="length" value="${fn:length(categories)}"/>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <div class="container">
            <div class="row">
                <div class="box">
                        <%-- Site page path --%>
                    <p class="path">
                        <a href="<c:url value="/"/>" title="Перейти на главную страницу">Главная</a>
                        →
                        <a href="<c:url value="/category/all"/>" title="Перейти к всем категориям">
                            Все категории
                        </a>
                    </p>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <hr>
                        <h3 class="text-center">
                            Наша продукция<c:if test="${length le 0}"> - список пуст!</c:if>
                        </h3>
                        <hr>
                            <%-- Administrator actions --%>
                        <c:if test="${authorized_user ne null}">
                            <div class="text-center">
                                <a href="<c:url value="/admin/category/new"/>" title="Добавить новую категория">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                        &nbsp;Новая
                                    </button>
                                </a>
                                <c:if test="${length gt 0}">
                                    &nbsp;&nbsp;
                                    <a href="<c:url value="/admin/category/delete/all"/>" title="Удалить все категории"
                                       onclick="if(confirm('Вы точно хотите удалить все категории? Удаленные объекты восстановлению не подлежат!')) this.submit; else return false;">
                                        <button class="btn btn-default">
                                            <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                                            &nbsp;Удалить&nbsp;все
                                        </button>
                                    </a>
                                </c:if>
                            </div>
                        </c:if>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
        <c:if test="${length gt 0}">
            <div class="container">
                <div class="row">
                    <div class="box">
                            <%-- Categories list --%>
                        <jsp:include page="/WEB-INF/views/category/list.jsp"/>
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
        <script src="<c:url value="/resources/js/easing.min.js"/>" async></script>
        <script src="<c:url value="/resources/js/totop.min.js"/>" async></script>
    </c:if>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
