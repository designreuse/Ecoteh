<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${(not empty main_company.workTimeFrom) and (not empty main_company.workTimeTo)}">
        Оставьте нам свое сообщение, и мы перезвоним Вам
        <c:choose>
            <c:when test="${main_company.open}">
                в течении часа.
            </c:when>
            <c:otherwise>
                в рабочие дни с <c:out value="${main_company.workTimeFrom}"/>
                до <c:out value="${main_company.workTimeTo}"/>.
            </c:otherwise>
        </c:choose>
    </c:when>
    <c:otherwise>
        Оставьте нам свое сообщение, и мы перезвоним Вам.
    </c:otherwise>
</c:choose>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
