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
        <title>Файлы | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="Файлы | <c:out value="${main_company.title}"/>">
        <meta name="robots" content="noindex,nofollow">
        <meta name="description" content="Сохраненный файлы на сервере.">
        <c:if test="${main_company.favicon ne null}">
            <link rel="shortcut icon" href="<c:url value="/resources/${main_company.favicon.url}"/>"
                  type="image/x-icon">
            <link rel="icon" href="<c:url value="/resources/${main_company.favicon.url}"/>" type="image/x-icon">
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
        <%-- NAVIGATION --%>
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
                    <hr>
                    <h3 class="text-center">Файлы<c:if test="${length le 0}"> - список пуст!</c:if></h3>
                    <hr>
                    <c:if test="${length gt 0}">
                        <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 col-xl-8 col-xl-offset-2">
                            <table align="center">
                                <tr>
                                    <th class="pad">Фото</th>
                                    <th class="pad">Название</th>
                                    <th class="pad">Действие</th>
                                </tr>
                                <c:set var="path" value="${pageContext.request.contextPath}"/>
                                <c:forEach items="${files}" var="file">
                                    <tr>
                                        <td class="pad">
                                            <a href="<c:url value="/resources/${file.url}"/>" title="${file.title}"
                                               rel="lightgallery">
                                                <img src="<c:url value="/resources/${file.url}"/>" class="img-preview"
                                                     alt="" title="Увеличить">
                                            </a>
                                        </td>
                                        <td class="pad">
                                            <a href="<c:url value="/resources/${file.url}"/>" target="_blank"
                                               title="Открыть в новом окне.">
                                                <c:out value="${file.title}"/>
                                            </a>
                                        </td>
                                        <td class="pad">
                                            <button class="btn-clipboard btn btn-default"
                                                    title="Скорировать ссылку файла &quot;${file.title}&quot;"
                                                    data-clipboard-text="<c:url value="/resources/${file.url}"/>">
                                                <span class="glyphicon glyphicon-floppy-disk green"
                                                      aria-hidden="true"></span>
                                            </button>
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
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <br>
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

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
