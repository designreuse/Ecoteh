<%--
Message list.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="length" value="${fn:length(messages)}"/>
<c:if test="${(print_messages eq null) or (print_messages gt length) or (print_messages le 0)}">
    <c:set var="print_messages" value="${length}"/>
</c:if>
<c:forEach items="${messages}" var="message" end="${print_messages - 1}">
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <p class="username">
            <span class="green"><c:out value="${message.user.name}"/></span>
            ,&nbsp;
            <a href="tel:<c:out value="${message.user.contacts.mobilePhone}"/>">
                <c:out value="${message.user.contacts.mobilePhone}"/>
            </a>
            <c:if test="${not empty message.user.contacts.email}">
                ,&nbsp;
                <a href="mailto:<c:out value="${message.user.contacts.email}"/>">
                    <c:out value="${message.user.contacts.email}"/>
                </a>
            </c:if>
            &nbsp;
            <span class="little">(<c:out value="${message.dateToString}"/>)</span>
        </p>
        <p class="message">
            <c:out value="${message.text}"/>
        </p>
            <%-- Message action --%>
        <p class="message green">
            <a href="<c:url value="/admin/messages/delete/${message.id}"/>" title="Удалить сообщение"
               onclick="if(confirm('Вы точно хотите удалить это сообщение? Удаленные объекты восстановлению не подлежат!')) this.submit; else return false;">
                <button class="btn btn-default">
                    <span class="glyphicon glyphicon-minus red" aria-hidden="true"></span>
                    &nbsp;Удалить
                </button>
            </a>
        </p>
    </div>
</c:forEach>