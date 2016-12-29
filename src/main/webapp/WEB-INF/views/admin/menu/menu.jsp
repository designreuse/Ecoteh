<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="text-center">
    <table align="center">
        <tr>
            <th class="pad"><a href="/admin/company/main">Компания</a></th>
            <td class="pad">
                <a href="/admin/company/edit/main"
                   title="Редактировать информацию о компании &quot;<c:out value="${main_company.title}"/>&quot;">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-edit yellow" aria-hidden="true"></span>&nbsp;Редактировать
                    </button>
                </a>
            </td>
        </tr>
        <tr>
            <th class="pad"><a href="/admin/section/any" title="ВСЕ наявные разделы">Разделы</a></th>
            <td class="pad">
                <a href="/admin/section/new" title="Добавить новый раздел">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Новый
                    </button>
                </a>&nbsp;&nbsp;
                <a href="/admin/section/delete/all" title="Удалить все разделы">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>&nbsp;Удалить все
                    </button>
                </a>
            </td>
        </tr>
        <tr>
            <th class="pad"><a href="/admin/category/any" title="ВСЕ наявные категории">Категории</a></th>
            <td class="pad">
                <a href="/admin/category/new" title="Добавить новую категорию">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Новая
                    </button>
                </a>&nbsp;&nbsp;
                <a href="/admin/category/delete/all" title="Удалить все категории">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>&nbsp;Удалить все
                    </button>
                </a>
            </td>
        </tr>
        <tr>
            <th class="pad"><a href="/admin/article/any" title="ВСЕ наявные статьи">Статьи</a></th>
            <td class="pad">
                <a href="/admin/article/new" title="Добавить новую статью">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Новая
                    </button>
                </a>&nbsp;&nbsp;
                <a href="/admin/article/delete/all" title="Удалить все статьи">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>&nbsp;Удалить все
                    </button>
                </a>
            </td>
        </tr>
        <tr>
            <th class="pad"><a href="/admin/company/all" title="Наши партнеры">Партнеры</a></th>
            <td class="pad">
                <a href="/admin/company/new" title="Добавить нового партнера">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Новый
                    </button>
                </a>&nbsp;&nbsp;
                <a href="/admin/company/delete/all" title="Удалить всех партнеров">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>&nbsp;Удалить всех
                    </button>
                </a>
            </td>
        </tr>
        <tr>
            <th class="pad"><a href="/admin/user/all" title="Весь персонал">Персонал</a></th>
            <td class="pad">
                <a href="/admin/user/new" title="Добавить нового пользователя">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Новый
                    </button>
                </a>&nbsp;&nbsp;
                <a href="/admin/user/delete/all" title="Удалить всех пользователей">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>&nbsp;Удалить всех
                    </button>
                </a>
            </td>
        </tr>
        <tr>
            <th class="pad"><a href="/admin/responses" title="Отзывы о компании">Отзывы</a></th>
            <td class="pad">
                <a href="/admin/response/delete/all" title="Удалить все отзывы о компании">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>&nbsp;Удалить все
                    </button>
                </a>
            </td>
        </tr>
        <tr>
            <th class="pad"><a href="/admin/messages" title="Сообщения от пользователей">Сообщения</a></th>
            <td class="pad">
                <a href="/admin/messages/delete/all" title="Удалить все cообщения от пользователей">
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
            <th class="pad"><a href="/admin/cache/" title="Список обьектов в памяти">Кэш</a></th>
            <td class="pad">
                <a href="/admin/cache/"
                   title="Информация об объектах, которых хранятся в памяти в данный момент времени.">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>&nbsp;Кэш
                    </button>
                </a>&nbsp;&nbsp;
                <a href="/admin/cache/clear" title="Удалить все объекты с памяти">
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
                        <a href="/admin/site/off" title="Отключить сайт для пользователей.">
                            <button class="btn btn-default">
                                <span class="glyphicon glyphicon-off red"
                                      aria-hidden="true"></span>&nbsp;<b>Отключить сайт!</b>
                            </button>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="/admin/site/on" title="Возобновить сайт для пользователей">
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

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
