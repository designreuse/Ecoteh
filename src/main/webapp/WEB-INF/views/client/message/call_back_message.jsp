<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

Оставьте нам свое сообщение, и мы перезвоним Вам
<c:choose>
    <c:when test="${main_company.open}">
        в течении часа.
    </c:when>
    <c:otherwise>
        в рабочие дни с 9:00 до 17:00.
    </c:otherwise>
</c:choose>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
