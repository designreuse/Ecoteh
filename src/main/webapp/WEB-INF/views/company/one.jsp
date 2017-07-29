<%--
Page of the one incoming company.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>О компании <c:out value="&quot;${company.title}&quot; | ${main_company.title}"/></title>
        <meta name="title" content="О компании <c:out value="&quot;${company.title}&quot; | ${main_company.title}"/>">
        <meta name="description" content="<c:out value="${company.description}"/>.">
        <meta name="keywords" content="<c:out value="${company.keywords}"/>"/>
        <meta property="og:title" content="<c:out value="${company.title}"/>"/>
        <meta property="og:type" content="website"/>
        <meta property="og:description" content="<c:out value="${company.description}"/>"/>
        <meta property="og:image" content="<c:url value="${company.logo.url}"/>"/>
        <meta name="twitter:title" content="<c:out value="${company.title}"/>">
        <meta name="twitter:description" content="<c:out value="${company.description}"/>">
        <meta name="twitter:image" content="<c:url value="${company.logo.url}"/>">
        <meta itemprop="name" content="<c:out value="${company.title}"/>"/>
        <meta itemprop="description" content="<c:out value="${company.description}"/>"/>
        <meta itemprop="image" content="<c:url value="${company.logo.url}"/>"/>
        <link rel="shortcut icon" href="<c:url value="${favicon.url}"/>" type="image/x-icon">
        <link rel="icon" href="<c:url value="${favicon.url}"/>" type="image/x-icon">
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
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <div class="container">
            <div class="row">
                <div class="box">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <%-- Site page path --%>
                        <p class="path">
                            <a href="<c:url value="/"/>" title="Перейти на главную страницу">Главная</a>
                            → <a href="<c:url value="/company/all"/>" title="Наши партнеры">Партнеры</a>
                            →
                            <a href="<c:url value="/company/${company.url}"/>">
                                <c:out value="${company.title}"/>
                            </a>
                        </p>
                        <c:if test="${not empty company.logo.url}">
                            <hr>
                            <h3 class="text-center"><c:out value="${company.title}"/></h3>
                            <hr>
                        </c:if>
                            <%-- Administrator actions --%>
                        <c:if test="${authorized_user ne null}">
                            <div class="text-center">
                                <a href="<c:url value="/admin/company/new"/>" title="Добавить нового партнера">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                        &nbsp;Новый
                                    </button>
                                </a>&nbsp;&nbsp;
                                <a href="<c:url value="/admin/company/edit/${company.url}"/>"
                                   title="Редактировать партнера &quot;<c:out value="${company.title}"/>&quot;">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-edit yellow" aria-hidden="true"></span>
                                        &nbsp;Редактировать
                                    </button>
                                </a>&nbsp;&nbsp;
                                <a href="<c:url value="/admin/company/delete/${company.url}"/>"
                                   title="Удалить партнера &quot;<c:out value="${company.title}"/>&quot;"
                                   onclick="if(confirm('Вы точно хотите удалить компанию &quot;<c:out
                                           value="${company.title}"/>&quot;? Удаленные объекты восстановлению не подлежат!')) this.submit; else return false;">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                                        &nbsp;Удалить
                                    </button>
                                </a>
                            </div>
                        </c:if>
                            <%-- Company logo --%>
                        <jsp:include page="/WEB-INF/views/company/logo.jsp"/>
                        <c:if test="${!company.validated}">
                            <p class="no-valid" title="Не отображается для клиентов">
                                <span class="glyphicon glyphicon-eye-close red" aria-hidden="true"
                                      title="Не отображается для клиентов"></span>
                            </p>
                        </c:if>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <p>
                            <c:choose>
                                <c:when test="${not empty company.text}">
                                    ${company.text}
                                </c:when>
                                <c:otherwise>
                                    ${company.description}
                                </c:otherwise>
                            </c:choose>
                        </p>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
            <div class="row">
                <div class="box">
                    <hr>
                    <h3 class="intro-text text-center">Контакты</h3>
                    <hr>
                        <%-- Contact information of the incoming company --%>
                    <jsp:include page="/WEB-INF/views/company/contacts.jsp"/>
                </div>
            </div>
        </div>
    </div>
        <%-- Footer --%>
    <jsp:include page="/WEB-INF/views/home/footer.jsp"/>
        <%-- Scripts --%>
    <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/easing.min.js"/>" async></script>
    <script src="<c:url value="/resources/js/totop.min.js"/>" async></script>
    </body>
    </html>
</compress:html>
