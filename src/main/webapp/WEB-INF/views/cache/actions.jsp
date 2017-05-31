<%--
Cache actions: update, clear.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="text-center">
    <a href="<c:url value="/superadmin/cache/"/>"
       title="Обновить информацию об объектах, которые хранятся в памяти.">
        <button class="btn btn-default">
            <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
            &nbsp;Обновить
        </button>
    </a>&nbsp;&nbsp;
    <a href="<c:url value="/superadmin/cache/clear"/>" title="Удалить все объекты с памяти"
       onclick="if(confirm('Вы точно хотите очистить кэш?')) this.submit; else return false;">
        <button class="btn btn-default">
            <span class="glyphicon glyphicon-floppy-remove red" aria-hidden="true"></span>
            &nbsp;Очистить
        </button>
    </a>
</div>
