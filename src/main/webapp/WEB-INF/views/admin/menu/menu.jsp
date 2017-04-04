<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Yurii Salimov (yuriy.alex.salimov@gmail.com)">
        <title>Меню | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="Меню | <c:out value="${main_company.title}"/>">
        <meta name="robots" content="noindex,nofollow">
        <meta name="description" content="Возможности администратора этого прекрасного сайта.">
        <link rel="shortcut icon" href="<c:url value="${favicon.url}"/>" type="image/x-icon">
        <link rel="icon" href="<c:url value="${favicon.url}"/>" type="image/x-icon">
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
                        → <a href="#" title="">Меню администратора</a>
                    </p>
                    <hr>
                    <h3 class="text-center green">Меню администратора</h3>
                    <hr>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <div class="container">
            <div class="row">
                <div class="box">
                    <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                        <jsp:include page="/WEB-INF/views/admin/user/auth_user.jsp"/>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
                        <div class="text-center">
                            <table align="center">
                                <tr>
                                    <th class="pad"><a href="<c:url value="/company/main"/>">Компания</a></th>
                                    <td class="pad">
                                        <a href="<c:url value="/admin/company/edit/main"/>"
                                           title="Редактировать информацию о компании &quot;<c:out value="${main_company.title}"/>&quot;">
                                            <button class="btn btn-default">
                                                <span class="glyphicon glyphicon-edit yellow" aria-hidden="true"></span>
                                                &nbsp;Редактировать
                                            </button>
                                        </a>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="pad">
                                        <a href="<c:url value="/category/all"/>" title="Все категории">Категории</a>
                                    </th>
                                    <td class="pad">
                                        <a href="<c:url value="/admin/category/new"/>" title="Добавить новую категорию">
                                            <button class="btn btn-default">
                                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                                &nbsp;Новая
                                            </button>
                                        </a>&nbsp;&nbsp;
                                        <a href="<c:url value="/admin/category/delete/all"/>"
                                           title="Удалить все категории">
                                            <button class="btn btn-default">
                                                <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                                                &nbsp;Удалить все
                                            </button>
                                        </a>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="pad">
                                        <a href="<c:url value="/article/all"/>" title="Все статьи">Статьи</a>
                                    </th>
                                    <td class="pad">
                                        <a href="<c:url value="/admin/article/new"/>" title="Добавить новую статью">
                                            <button class="btn btn-default">
                                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                                &nbsp;Новая
                                            </button>
                                        </a>&nbsp;&nbsp;
                                        <a href="<c:url value="/admin/article/delete/all"/>" title="Удалить все статьи">
                                            <button class="btn btn-default">
                                                <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                                                &nbsp;Удалить все
                                            </button>
                                        </a>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="pad">
                                        <a href="<c:url value="/company/all"/>" title="Наши партнеры">Партнеры</a>
                                    </th>
                                    <td class="pad">
                                        <a href="<c:url value="/admin/company/new"/>" title="Добавить нового партнера">
                                            <button class="btn btn-default">
                                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                                &nbsp;Новый
                                            </button>
                                        </a>&nbsp;&nbsp;
                                        <a href="<c:url value="/admin/company/delete/all"/>"
                                           title="Удалить всех партнеров">
                                            <button class="btn btn-default">
                                                <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                                                &nbsp;Удалить всех
                                            </button>
                                        </a>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="pad">
                                        <a href="<c:url value="/admin/user/all"/>" title="Весь персонал">Персонал</a>
                                    </th>
                                    <td class="pad">
                                        <a href="<c:url value="/admin/user/new"/>" title="Добавить нового пользователя">
                                            <button class="btn btn-default">
                                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                                &nbsp;Новый
                                            </button>
                                        </a>&nbsp;&nbsp;
                                        <a href="<c:url value="/admin/user/delete/all"/>"
                                           title="Удалить всех пользователей">
                                            <button class="btn btn-default">
                                                <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                                                &nbsp;Удалить всех
                                            </button>
                                        </a>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="pad">
                                        <a href="<c:url value="/admin/file/all"/>"
                                           title="Сохраненный файлы на сервере">Файлы</a>
                                    </th>
                                    <td class="pad">
                                        <a href="<c:url value="/admin/file/new"/>" title="Добавить новый файл">
                                            <button class="btn btn-default">
                                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                                &nbsp;Новый
                                            </button>
                                        </a>&nbsp;&nbsp;
                                        <a href="<c:url value="/admin/file/delete/all"/>" title="Удалить все файлы">
                                            <button class="btn btn-default">
                                                <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                                                &nbsp;Удалить все
                                            </button>
                                        </a>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="pad">
                                        <a href="<c:url value="/responses"/>" title="Отзывы о компании">Отзывы</a>
                                    </th>
                                    <td class="pad">
                                        <a href="<c:url value="/admin/response/delete/all"/>"
                                           title="Удалить все отзывы о компании">
                                            <button class="btn btn-default">
                                                <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                                                &nbsp;Удалить все
                                            </button>
                                        </a>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="pad">
                                        <a href="<c:url value="/admin/messages/"/>" title="Сообщения от пользователей">
                                            Сообщения
                                        </a>
                                    </th>
                                    <td class="pad">
                                        <a href="<c:url value="/admin/messages/delete/all"/>"
                                           title="Удалить все сообщения от пользователей">
                                            <button class="btn btn-default">
                                                <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                                                &nbsp;Удалить
                                                все
                                            </button>
                                        </a>
                                    </td>
                                </tr>
                                <c:if test="${authorized_user.role eq 'SUPERADMIN'}">
                                    <tr>
                                        <th class="pad">
                                            <a href="<c:url value="/superadmin/cache/"/>"
                                               title="Список обьектов в памяти">Кэш</a>
                                        </th>
                                        <td class="pad">
                                            <a href="<c:url value="/superadmin/cache/"/>"
                                               title="Информация об объектах, которых хранятся в памяти в данный момент времени.">
                                                <button class="btn btn-default">
                                                    <span class="glyphicon glyphicon-floppy-disk"
                                                          aria-hidden="true"></span>&nbsp;Кэш
                                                </button>
                                            </a>&nbsp;&nbsp;
                                            <a href="/superadmin/cache/clear" title="Удалить все объекты с памяти">
                                                <button class="btn btn-default">
                                                    <span class="glyphicon glyphicon-floppy-remove red"
                                                          aria-hidden="true"></span>&nbsp;Очистить
                                                </button>
                                            </a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th class="pad">
                                            <a href="<c:url value="/admin/styles/"/>" title="CSS">Стиля</a>
                                        </th>
                                        <td class="pad">
                                            <a href="<c:url value="/admin/styles/edit"/>"
                                               title="Изменить стиля сайта (CSS)">
                                                <button class="btn btn-default">
                                                    <span class="glyphicon glyphicon-edit yellow"
                                                          aria-hidden="true"></span>&nbsp;Изменить
                                                </button>
                                            </a>&nbsp;&nbsp;
                                            <a href="<c:url value="/admin/styles/rollback"/>"
                                               title="Возобновить по-умолчанию">
                                                <button class="btn btn-default">
                                                    <span class="glyphicon glyphicon-share-alt"
                                                          aria-hidden="true"></span>&nbsp;Возобновить
                                                </button>
                                            </a>
                                        </td>
                                    </tr>
                                </c:if>
                            </table>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/views/client/main/footer.jsp"/>
    <script src="<c:url value="/resources/js/jquery.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/easing.min.js"/>" type="text/javascript" async></script>
    <script src="<c:url value="/resources/js/totop.min.js"/>" type="text/javascript" async></script>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
