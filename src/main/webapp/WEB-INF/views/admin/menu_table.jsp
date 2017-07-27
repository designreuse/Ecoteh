<%--
Table with an admin menu.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table align="center">
    <tr>
        <th class="pad">
            <a href="<c:url value="/company/main"/>">Компания</a>
        </th>
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
            <a href="<c:url value="/admin/category/delete/all"/>" title="Удалить все категории"
               onclick="if(confirm('Вы точно хотите удалить все категории? Удаленные объекты восстановлению не подлежат!')) this.submit; else return false;">
                <button class="btn btn-default">
                    <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                    &nbsp;Удалить&nbsp;все
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
            <a href="<c:url value="/admin/article/delete/all"/>" title="Удалить все статьи"
               onclick="if(confirm('Вы точно хотите удалить все статьи? Удаленные объекты восстановлению не подлежат!')) this.submit; else return false;">
                <button class="btn btn-default">
                    <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                    &nbsp;Удалить&nbsp;все
                </button>
            </a>
        </td>
    </tr>
    <tr>
        <th class="pad">
            <a href="<c:url value="/blog"/>" title="Блог компании">Блог</a>
        </th>
        <td class="pad">
            <a href="<c:url value="/admin/post/new"/>" title="Добавить новый пост">
                <button class="btn btn-default">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    &nbsp;Новый
                </button>
            </a>&nbsp;&nbsp;
            <a href="<c:url value="/admin/posts/delete/all"/>" title="Удалить все посты"
               onclick="if(confirm('Вы точно хотите удалить все посты? Удаленные объекты восстановлению не подлежат!')) this.submit; else return false;">
                <button class="btn btn-default">
                    <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                    &nbsp;Удалить&nbsp;все
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
            <a href="<c:url value="/admin/company/delete/all"/>" title="Удалить всех партнеров"
               onclick="if(confirm('Вы точно хотите удалить всех партнеров? Удаленные объекты восстановлению не подлежат!')) this.submit; else return false;">
                <button class="btn btn-default">
                    <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                    &nbsp;Удалить&nbsp;всех
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
            <a href="<c:url value="/admin/user/delete/all"/>" title="Удалить всех пользователей"
               onclick="if(confirm('Вы точно хотите удалить всех пользователей? Удаленные объекты восстановлению не подлежат!')) this.submit; else return false;">
                <button class="btn btn-default">
                    <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                    &nbsp;Удалить&nbsp;всех
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
            <a href="<c:url value="/admin/file/delete/all"/>" title="Удалить все файлы"
               onclick="if(confirm('Вы точно хотите удалить все файлы? Удаленные объекты восстановлению не подлежат!')) this.submit; else return false;">
                <button class="btn btn-default">
                    <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                    &nbsp;Удалить&nbsp;все
                </button>
            </a>
        </td>
    </tr>
    <tr>
        <th class="pad">
            <a href="<c:url value="/responses"/>" title="Отзывы о компании">Отзывы</a>
        </th>
        <td class="pad">
            <a href="<c:url value="/admin/response/delete/all"/>" title="Удалить все отзывы о компании"
               onclick="if(confirm('Вы точно хотите удалить все отзывы? Удаленные объекты восстановлению не подлежат!')) this.submit; else return false;">
                <button class="btn btn-default">
                    <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                    &nbsp;Удалить&nbsp;все
                </button>
            </a>
        </td>
    </tr>
    <tr>
        <th class="pad">
            <a href="<c:url value="/admin/messages/"/>" title="Сообщения от пользователей">Сообщения</a>
        </th>
        <td class="pad">
            <a href="<c:url value="/admin/messages/delete/all"/>" title="Удалить все сообщения от пользователей"
               onclick="if(confirm('Вы точно хотите удалить все сообщения? Удаленные объекты восстановлению не подлежат!')) this.submit; else return false;">
                <button class="btn btn-default">
                    <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                    &nbsp;Удалить&nbsp;все
                </button>
            </a>
        </td>
    </tr>
    <%-- Superadmin menu --%>
    <%@include file="/WEB-INF/views/admin/super_menu_table.jsp" %>
</table>
