<%--
Responses list.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="length" value="${fn:length(responses)}"/>
<c:if test="${(print_responses eq null) or (print_responses gt length) or (print_responses le 0)}">
    <c:set var="print_responses" value="${length}"/>
</c:if>
<c:forEach items="${responses}" var="response" end="${print_responses - 1}">
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <p class="username">
            <c:if test="${!response.validated}">
                <span class="glyphicon glyphicon-eye-close little red" aria-hidden="true"
                      title="Не отображается для клиентов"></span>&nbsp;
            </c:if>
            <span class="green"><c:out value="${response.username}"/></span>&nbsp;
            <span class="little">(<c:out value="${response.dateToString}"/>)</span>
        </p>
        <p class="response">
            <c:out value="${response.text}"/>
        </p>
            <%-- Administrator actions --%>
        <c:if test="${authorized_user ne null}">
            <p class="response">
                <c:choose>
                    <c:when test="${response.validated}">
                        <a href="<c:url value="/admin/response/valid/${response.id}"/>"
                           title="Не отображать отзыв, его не смогут увидеть клиенты.">
                            <button class="btn btn-default">
                                <span class="glyphicon glyphicon-eye-close red" aria-hidden="true"></span>
                                &nbsp;Не&nbsp;отображать
                            </button>
                        </a>&nbsp;
                    </c:when>
                    <c:when test="${!response.validated}">
                        <a href="<c:url value="/admin/response/valid/${response.id}"/>"
                           title="Одобрить отзыв, его смогут увидеть клиенты.">
                            <button class="btn btn-default">
                                <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                                &nbsp;Отображать
                            </button>
                        </a>&nbsp;
                    </c:when>
                </c:choose>
                <a href="<c:url value="/admin/response/edit/${response.id}"/>"
                   title="Редактировать отзыв">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-edit yellow" aria-hidden="true"></span>
                        &nbsp;Редактировать
                    </button>
                </a>&nbsp;&nbsp;
                <a href="<c:url value="/admin/response/delete/${response.id}"/>" title="Удалить отзыв"
                   onclick="if(confirm('Вы точно хотите удалить этот отзыв? Удаленные объекты восстановлению не подлежат!')) this.submit; else return false;">
                    <button class="btn btn-default">
                        <span class="glyphicon glyphicon-minus red" aria-hidden="true"></span>
                        &nbsp;Удалить
                    </button>
                </a>
            </p>
        </c:if>
        <br>
    </div>
</c:forEach>
