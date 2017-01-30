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
        <title>Файлы | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="Файлы | <c:out value="${main_company.title}"/>">
        <meta name="robots" content="noindex,nofollow">
        <meta name="description" content="Сохраненный файлы на сервере.">
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
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
        <div class="container">
            <div class="row">
                <div class="box">
                    <div class="text-center">
                        <a href="/admin/file/new" title="Добавить новый файл">
                            <button class="btn btn-default">
                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Добавить
                            </button>
                        </a>
                        <c:set var="length" value="${fn:length(files)}"/>
                        <c:if test="${length gt 0}">
                            &nbsp;&nbsp;
                            <a href="/admin/file/delete/all" title="Удалить все файлы">
                                <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-remove red"
                                              aria-hidden="true"></span>&nbsp;Удалить все
                                </button>
                            </a>
                        </c:if>
                    </div>
                    <p class="path">
                        <a href="<c:url value="/admin/"/>" title="Перейти на главную странцу">Главная</a>
                        → <a href="<c:url value="/admin/menu"/>" title="Меню администратора">Меню</a>
                        → <a href="#">Файлы</a>
                    </p>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                        <hr>
                        <h3 class="text-center">Файлы<c:if test="${length le 0}"> - список пуст!</c:if></h3>
                        <hr>
                    </div>
                    <c:if test="${length gt 0}">
                        <c:if test="${length gt 1}">
                            <p class="path">
                                <a href="#">Сортировка</a>:
                                <a href="<c:url value="/admin/file/all/sort?revers=${revers}"/>"
                                   title="Сортировать по названию">По названия</a>
                            </p><br>
                        </c:if>
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                            <c:forEach items="${files}" var="file">
                                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 col-xl-3">
                                    <div class="text-center">
                                        <a href="<c:url value="${file.url}"/>" title="${file.title}"
                                           rel="lightgallery[files]">
                                            <img src="<c:url value="${file.url}"/>" class="file"
                                                 onerror="this.src='<c:url
                                                         value="/resources/img/static/default_file.gif"/>'"
                                                 alt="" title="Увеличить">
                                        </a><br>
                                        <a href="<c:url value="${file.url}"/>" target="_blank"
                                           title="Открыть в новом окне">
                                            <c:out value="${file.title}"/>
                                        </a><br>
                                        <button class="btn-clipboard btn btn-default"
                                                title="Скорировать ссылку файла &quot;${file.title}&quot;"
                                                data-clipboard-text="<c:url value="${file.url}"/>">
                                            <span class="glyphicon glyphicon-link" aria-hidden="true"></span>
                                        </button>
                                        <c:if test="${file.validated}">
                                            &nbsp;
                                            <a href="<c:url value="/admin/file/edit/${file.id}"/>"
                                               title="Редактировать файл &quot;${file.title}&quot;">
                                                <button class="btn btn-default">
                                                    <span class="glyphicon glyphicon-edit yellow"
                                                          aria-hidden="true"></span>
                                                </button>
                                            </a>&nbsp;
                                            <a href="<c:url value="/admin/file/delete/${file.id}"/>"
                                               title="Удалить файл &quot;${file.title}&quot;">
                                                <button class="btn btn-default">
                                                <span class="glyphicon glyphicon-remove red"
                                                      aria-hidden="true"></span>
                                                </button>
                                            </a>
                                        </c:if>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/views/client/main/footer.jsp"/>
    <script src="<c:url value="/resources/js/jquery.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/lightgallery.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/clipboard.min.js"/>" type="text/javascript"></script>
    <script>new Clipboard('.btn-clipboard');</script>
    <script src="<c:url value="/resources/js/easing.min.js"/>" type="text/javascript" async></script>
    <script src="<c:url value="/resources/js/totop.min.js"/>" type="text/javascript" async></script>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
