<%--
Table with an superadmin menu.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${authorized_user.role eq 'SUPERADMIN'}">
    <tr>
        <th class="pad">
            <a href="<c:url value="/superadmin/cache/"/>" title="Список обьектов в памяти">Кэш</a>
        </th>
        <td class="pad">
            <a href="<c:url value="/superadmin/cache/"/>"
               title="Информация об объектах, которых хранятся в памяти в данный момент времени.">
                <button class="btn btn-default">
                    <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>
                    &nbsp;Кэш
                </button>
            </a>&nbsp;&nbsp;
            <a href="/superadmin/cache/clear" title="Удалить все объекты с памяти">
                <button class="btn btn-default">
                    <span class="glyphicon glyphicon-floppy-remove red" aria-hidden="true"></span>
                    &nbsp;Очистить
                </button>
            </a>
        </td>
    </tr>
    <tr>
        <th class="pad">
            <a href="<c:url value="/superadmin/styles"/>" title="CSS">Стиля</a>
        </th>
        <td class="pad">
            <a href="<c:url value="/superadmin/styles/edit"/>" title="Изменить стиля сайта (CSS)">
                <button class="btn btn-default">
                    <span class="glyphicon glyphicon-edit yellow" aria-hidden="true"></span>
                    &nbsp;Изменить
                </button>
            </a>&nbsp;&nbsp;
            <a href="<c:url value="/superadmin/styles/rollback"/>" title="Возобновить по-умолчанию">
                <button class="btn btn-default">
                    <span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span>
                    &nbsp;Возобновить
                </button>
            </a>
        </td>
    </tr>
    <tr>
        <th class="pad">SEO</th>
        <td class="pad">
            <a href="<c:url value="/robots.txt"/>" title="Returns information about the site for search engines.">
                <button class="btn btn-default">
                    <span class="glyphicon glyphicon-registration-mark" aria-hidden="true"></span>
                    &nbsp;robots.txt
                </button>
            </a>&nbsp;&nbsp;
            <a href="<c:url value="/sitemap.xml"/>" title="Creates and returns a sitemap.">
                <button class="btn btn-default">
                    <span class="glyphicon glyphicon-globe" aria-hidden="true"></span>
                    &nbsp;sitemap.xml
                </button>
            </a>
        </td>
    </tr>
    <tr>
        <th class="pad">
            <a href="<c:url value="/superadmin/config"/>">Конфигурация</a>
        </th>
        <td class="pad">
            <a href="<c:url value="/superadmin/config"/>" title="Конфигурация">
                <button class="btn btn-default">
                    <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                    &nbsp;Конфигурация
                </button>
            </a>
        </td>
    </tr>
</c:if>
