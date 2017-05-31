<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table align="center" border="1" cellpadding="3" cellspacing="0">
    <tr>
        <th>№</th>
        <th>Объект</th>
        <th></th>
    </tr>
    <c:forEach items="${objects}" var="object">
        <tr>
            <td>
                <c:set var="count" value="${count + 1}"/>${count}
            </td>
            <td>
                <c:out value="${object.key}"/>
            </td>
            <td>
                <a href="<c:url value="/superadmin/cache/remove/${object.key}"/>" title="Удалить объект с кэша">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                    </button>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
