<%--
Files list.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${files}" var="file">
    <c:if test="${not empty file.url}">
        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
            <div class="text-center">
                <a href="<c:url value="${file.url}"/>" title="<c:out value="${file.title}"/>" rel="lightgallery[files]">
                    <img src="<c:url value="${file.url}"/>" class="file" alt="" title="Увеличить"
                         onerror="this.src='<c:url value="/resources/img/static/default_file.gif"/>'">
                </a>
                <br>
                <a href="<c:url value="${file.url}"/>" target="_blank" title="Открыть в новом окне">
                    <c:out value="${file.title}"/>
                </a>
                <br>
                <button class="btn-clipboard btn btn-default"
                        title="Скорировать ссылку файла &quot;<c:out value="${file.title}"/>&quot;"
                        data-clipboard-text="<c:url value="${file.url}"/>">
                    <span class="glyphicon glyphicon-link" aria-hidden="true"></span>
                </button>
                &nbsp;
                <a href="<c:url value="/admin/file/edit/${file.id}"/>"
                   title="Редактировать файл &quot;<c:out value="${file.title}"/>&quot;">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-edit yellow" aria-hidden="true"></span>
                    </button>
                </a>
                <c:if test="${file.validated}">
                    &nbsp;
                    <a href="<c:url value="/admin/file/delete/${file.id}"/>"
                       title="Удалить файл &quot;<c:out value="${file.title}"/>&quot;"
                       onclick="if(confirm('Вы точно хотите удалить все файл &quot;<c:out
                               value="${file.title}"/>&quot;? Удаленные объекты восстановлению не подлежат!')) this.submit; else return false;">
                        <button class="btn btn-default">
                            <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                        </button>
                    </a>
                </c:if>
            </div>
        </div>
    </c:if>
</c:forEach>
