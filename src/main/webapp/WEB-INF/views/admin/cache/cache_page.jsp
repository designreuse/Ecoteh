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
        <title>Кэш | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="Кэш | <c:out value="${main_company.title}"/>">
        <meta name="robots" content="noindex,nofollow">
        <meta name="description" content="Информация об объектах, которых хранятся в памяти в данный момент времени.">
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
    </head>
    <body>
        <%-- NAVIGATION --%>
    <jsp:include page="/WEB-INF/views/client/main/navigation.jsp"/>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
        <div class="container">
            <div class="row">
                <div class="box">
                        <%-- Path --%>
                    <p class="path">
                        <a href="/admin/" title="Перейти на главную странцу">Главная</a>
                        → <a href="/admin/menu" title="Меню администратора">Меню</a>
                        → <a href="#">Кэш сервера</a>
                    </p>
                    <c:set var="length" value="${fn:length(objects)}"/>
                    <hr>
                    <h3 class="text-center">Кэш<c:if test="${length le 0}"> пустой</c:if></h3>
                    <hr>
                    <c:if test="${length gt 0}">
                        <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 col-xl-8 col-xl-offset-2">
                            <table align="center">
                                <tr>
                                    <th class="pad"><h4>№</h4></th>
                                    <th class="pad"><h4>Ключ</h4></th>
                                    <th class="hidden-xs pad"><h4>Объект</h4></th>
                                    <th class="pad"></th>
                                </tr>
                                <c:forEach items="${objects}" var="object">
                                    <tr>
                                        <td class="pad"><c:set var="count" value="${count + 1}"/>${count}</td>
                                        <td class="pad"><c:out value="${object.key}"/></td>
                                        <td class="hidden-xs pad"><c:out value="${object.value}"/></td>
                                        <td class="pad">
                                            <a href="/admin/cache/remove/${object.key}" title="Удалить объект с кэша">
                                                <button class="btn btn-default">
                                                    <span class="glyphicon glyphicon-minus red"
                                                          aria-hidden="true"></span>&nbsp;Удалить
                                                </button>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <br>
                            <div class="text-center">
                                <a href="/admin/cache"
                                   title="Обновить информацию об объектах, которые хранятся в памяти.">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>&nbsp;Обновить
                                    </button>
                                </a>&nbsp;&nbsp;
                                <a href="/admin/cache/clear" title="Удалить все объекты с памяти">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-floppy-remove red"
                                              aria-hidden="true"></span>&nbsp;Очистить
                                    </button>
                                </a>
                            </div>
                        </div>
                    </c:if>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
        <%-- FOOTER --%>
    <jsp:include page="/WEB-INF/views/client/main/footer.jsp"/>
        <%-- Scripts --%>
    <script src="/resources/js/jquery.min.js" type="text/javascript"></script>
    <script src="/resources/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/resources/js/easing.min.js" type="text/javascript" async></script>
    <script src="/resources/js/totop.min.js" type="text/javascript" async></script>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
