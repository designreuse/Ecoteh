<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="text-center">
    <table align="center">
        <tr>
            <th class="pad"><a href="<c:url value="/admin/company/main"/>">Компания</a></th>
            <td class="pad">
                <a href="<c:url value="/admin/company/edit/main"/>"
                   title="Редактировать информацию о компании &quot;<c:out value="${main_company.title}"/>&quot;">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-edit yellow" aria-hidden="true"></span>&nbsp;Редактировать
                    </button>
                </a>
            </td>
        </tr>
        <tr>
            <th class="pad"><a href="<c:url value="/admin/category/all"/>" title="Все категории">Категории</a>
            </th>
            <td class="pad">
                <a href="<c:url value="/admin/category/new"/>" title="Добавить новую категорию">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Новая
                    </button>
                </a>&nbsp;&nbsp;
                <a href="<c:url value="/admin/category/delete/all"/>" title="Удалить все категории">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>&nbsp;Удалить все
                    </button>
                </a>
            </td>
        </tr>
        <tr>
            <th class="pad"><a href="<c:url value="/admin/article/all"/>" title="Все статьи">Статьи</a></th>
            <td class="pad">
                <a href="<c:url value="/admin/article/new"/>" title="Добавить новую статью">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Новая
                    </button>
                </a>&nbsp;&nbsp;
                <a href="<c:url value="/admin/article/delete/all"/>" title="Удалить все статьи">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>&nbsp;Удалить все
                    </button>
                </a>
            </td>
        </tr>
        <tr>
            <th class="pad"><a href="<c:url value="/admin/company/all"/>" title="Наши партнеры">Партнеры</a></th>
            <td class="pad">
                <a href="<c:url value="/admin/company/new"/>" title="Добавить нового партнера">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Новый
                    </button>
                </a>&nbsp;&nbsp;
                <a href="<c:url value="/admin/company/delete/all"/>" title="Удалить всех партнеров">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>&nbsp;Удалить всех
                    </button>
                </a>
            </td>
        </tr>
        <tr>
            <th class="pad"><a href="<c:url value="/admin/user/all"/>" title="Весь персонал">Персонал</a></th>
            <td class="pad">
                <a href="<c:url value="/admin/user/new"/>" title="Добавить нового пользователя">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Новый
                    </button>
                </a>&nbsp;&nbsp;
                <a href="<c:url value="/admin/user/delete/all"/>" title="Удалить всех пользователей">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>&nbsp;Удалить всех
                    </button>
                </a>
            </td>
        </tr>
        <tr>
            <th class="pad">
                <a href="<c:url value="/admin/file/all"/>" title="Сохраненный файлы на сервере">Файлы</a>
            </th>
            <td class="pad">
                <a href="<c:url value="/admin/file/new"/>" title="Добавить новый файл">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Новый
                    </button>
                </a>&nbsp;&nbsp;
                <a href="<c:url value="/admin/file/delete/all"/>" title="Удалить все файлы">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>&nbsp;Удалить все
                    </button>
                </a>
            </td>
        </tr>
        <tr>
            <th class="pad">
                <a href="<c:url value="/admin/responses"/>" title="Отзывы о компании">Отзывы</a>
            </th>
            <td class="pad">
                <a href="<c:url value="/admin/response/delete/all"/>" title="Удалить все отзывы о компании">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>&nbsp;Удалить все
                    </button>
                </a>
            </td>
        </tr>
        <tr>
            <th class="pad">
                <a href="<c:url value="/admin/messages/"/>" title="Сообщения от пользователей">Сообщения</a>
            </th>
            <td class="pad">
                <a href="<c:url value="/admin/messages/delete/all"/>" title="Удалить все сообщения от пользователей">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>&nbsp;Удалить все
                    </button>
                </a>
            </td>
        </tr>
        <tr>
            <th class="pad"></th>
            <td class="pad"></td>
        </tr>
        <tr>
            <th class="pad">
                <a href="<c:url value="/admin/cache/"/>" title="Список обьектов в памяти">Кэш</a>
            </th>
            <td class="pad">
                <a href="<c:url value="/admin/cache/"/>"
                   title="Информация об объектах, которых хранятся в памяти в данный момент времени.">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>&nbsp;Кэш
                    </button>
                </a>&nbsp;&nbsp;
                <a href="<c:url value="/admin/cache/clear"/>" title="Удалить все объекты с памяти">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-floppy-remove red" aria-hidden="true"></span>&nbsp;Очистить
                    </button>
                </a>
            </td>
        </tr>
        <tr>
            <th class="pad"></th>
            <td class="pad"></td>
        </tr>
        <tr>
            <th class="pad"></th>
            <td class="pad">
                <c:choose>
                    <c:when test="${is_enabled}">
                        <button class="open btn btn-default" data-type="zoomin"
                                title="Отключить сайт для пользователей.">
                            <span class="glyphicon glyphicon-off red"
                                  aria-hidden="true"></span>&nbsp;<b>Отключить сайт!</b>
                        </button>
                        <div class="overlay-container">
                            <div class="window-container zoomin">
                                <h3>Отключение сайта</h3>
                                <h5 class="text-center">
                                    Вы уверены, что хотите отключить сайт?<br>
                                    Клиенты не будут иметь доступа к информации на сайте.
                                </h5><br>
                                <div class="text-center">
                                    <a href="<c:url value="/admin/site/off"/>">
                                        <button class="btn btn-default">
                                             <span class="glyphicon glyphicon-off red"
                                                   aria-hidden="true"></span>&nbsp;Да, отключить сайт
                                        </button>
                                    </a>&nbsp;&nbsp;
                                    <button class="close btn btn-default">
                                        <span class="glyphicon glyphicon-remove"
                                              aria-hidden="true"></span>&nbsp;Нет, закрыть окно
                                    </button>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value="/admin/site/on"/>" title="Возобновить сайт для пользователей">
                            <button class="btn btn-default">
                                <span class="glyphicon glyphicon-off"
                                      aria-hidden="true"></span>&nbsp;<b>Включить сайт!</b>
                            </button>
                        </a>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>
</div>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
