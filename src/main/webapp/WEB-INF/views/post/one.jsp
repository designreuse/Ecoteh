<%--
Page of the one incoming post.
Information about one input post is displayed,
as well as a list of similar posts.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <title><c:out value="${post.title} | ${main_company.title}"/></title>
        <meta name="title" content="<c:out value="${post.title} | ${main_company.title}"/>">
        <meta name="description" content="<c:out value="${post.description}"/>"/>
        <meta name="keywords" content="Статья <c:out value="${post.title}, ${post.keywords}"/>"/>
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
        <link href="<c:url value="/resources/css/lightgallery.min.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/css/carousel.min.css"/>" rel="stylesheet" type="text/css">
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
                        <a href="<c:url value="/blog"/>"
                           title="Блог компании <c:out value="${main_company.title}"/>">Блог</a>
                        →
                        <a href="<c:url value="/blog/${post.url}"/>">
                            <c:out value="${post.title}"/>
                        </a>
                    </p>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <hr>
                        <h3 class="text-center">
                            <c:out value="${post.title}"/>
                        </h3>
                        <hr>
                            <%-- Administrator actions --%>
                        <c:if test="${authorized_user ne null}">
                            <div class="text-center">
                                <a href="<c:url value="/admin/post/new"/>" title="Добавить новый пост">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                        &nbsp;Новый
                                    </button>
                                </a>&nbsp;&nbsp;
                                <a href="<c:url value="/admin/post/edit/${post.url}"/>"
                                   title="Редактировать пост &quot;<c:out value="${post.title}"/>&quot;">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-edit yellow" aria-hidden="true"></span>
                                        &nbsp;Редактировать
                                    </button>
                                </a>&nbsp;&nbsp;
                                <a href="<c:url value="/admin/post/delete/${post.url}"/>"
                                   title="Удалить пост &quot;<c:out value="${post.title}"/>&quot;"
                                   onclick="if(confirm('Вы точно хотите удалить пост &quot;<c:out
                                           value="${post.title}"/>&quot;? Удаленные объекты восстановлению не подлежат!')) this.submit; else return false;">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                                        &nbsp;Удалить
                                    </button>
                                </a>
                            </div>
                        </c:if>
                        <span class="little">
                            <c:if test="${!post.validated}">
                                <span class="glyphicon glyphicon-eye-close red" aria-hidden="true"
                                      title="Не отображается для клиентов"></span>&nbsp;
                            </c:if>
                            <c:out value="${post.dateToString}"/>
                        </span>
                        <p>
                            <c:choose>
                                <c:when test="${not empty post.text}">
                                    ${post.text}
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${not empty post.logo.url}">
                                        <a href="<c:url value="${post.logo.url}"/>" rel="lightgallery"
                                           title="<c:out value="${post.title}"/>">
                                            <img src="<c:url value="${post.logo.url}"/>" class="content-logo"
                                                 title="<c:out value="${post.title}"/>">
                                        </a>
                                    </c:if>
                                    <c:out value="${post.description}"/>
                                </c:otherwise>
                            </c:choose>
                        </p>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
            <%-- List of similar posts --%>
        <%@include file="/WEB-INF/views/post/slider.jsp" %>
    </div>
        <%-- Footer --%>
    <jsp:include page="/WEB-INF/views/home/footer.jsp"/>
        <%-- Scripts --%>
    <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/lightgallery.min.js"/>"></script>
    <script src="<c:url value="/resources/js/easing.min.js"/>" async></script>
    <script src="<c:url value="/resources/js/totop.min.js"/>" async></script>
    </body>
    </html>
</compress:html>
