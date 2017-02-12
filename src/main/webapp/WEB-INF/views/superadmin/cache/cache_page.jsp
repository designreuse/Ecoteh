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
        <title>Кэш | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="Кэш | <c:out value="${main_company.title}"/>">
        <meta name="robots" content="noindex,nofollow">
        <meta name="description" content="Информация об объектах, которых хранятся в памяти в данный момент времени.">
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
    </head>
    <body>
    <jsp:include page="/WEB-INF/views/client/main/navigation.jsp"/>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <div class="container">
            <div class="row">
                <div class="box">
                    <p class="path">
                        <a href="<c:url value="/"/>" title="Перейти на главную странцу">Главная</a>
                        → <a href="<c:url value="/admin/menu"/>" title="Меню администратора">Меню</a>
                        → <a href="#">Кэш сервера</a>
                    </p>
                    <c:set var="length" value="${fn:length(objects)}"/>
                    <hr>
                    <h3 class="text-center">Кэш<c:if test="${length le 0}"> пустой!</c:if></h3>
                    <hr>
                    <c:if test="${length gt 10}">
                        <div class="text-center">
                            <a href="<c:url value="/superadmin/cache"/>"
                               title="Обновить информацию об объектах, которые хранятся в памяти.">
                                <button class="btn btn-default">
                                    <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>&nbsp;Обновить
                                </button>
                            </a>&nbsp;&nbsp;
                            <a href="<c:url value="/superadmin/cache/clear"/>" title="Удалить все объекты с памяти">
                                <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-floppy-remove red"
                                              aria-hidden="true"></span>&nbsp;Очистить
                                </button>
                            </a>
                        </div>
                    </c:if>
                    <c:if test="${length gt 0}">
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <table align="center">
                                <tr>
                                    <th>№</th>
                                    <th>Объект</th>
                                    <th></th>
                                </tr>
                                <c:forEach items="${objects}" var="object">
                                    <tr>
                                        <td><c:set var="count" value="${count + 1}"/>${count}</td>
                                        <td><c:out value="${object.key}"/></td>
                                        <td>
                                            <a href="<c:url value="/superadmin/cache/remove/${object.key}"/>"
                                               title="Удалить объект с кэша">
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
                            <div class="text-center">
                                <a href="<c:url value="/superadmin/cache"/>"
                                   title="Обновить информацию об объектах, которые хранятся в памяти.">
                                    <button class="btn btn-default">
                                        <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>&nbsp;Обновить
                                    </button>
                                </a>&nbsp;&nbsp;
                                <a href="<c:url value="/superadmin/cache/clear"/>" title="Удалить все объекты с памяти">
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
    <jsp:include page="/WEB-INF/views/client/main/footer.jsp"/>
    <script src="<c:url value="/resources/js/jquery.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <c:if test="${length gt 0}">
        <script src="<c:url value="/resources/js/easing.min.js"/>" type="text/javascript" async></script>
        <script src="<c:url value="/resources/js/totop.min.js"/>" type="text/javascript" async></script>
    </c:if>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
