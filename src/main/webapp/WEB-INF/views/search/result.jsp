<%--
Search result.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:choose>
    <c:when test="${result}">
        <%-- Categories list --%>
        <%@include file="/WEB-INF/views/category/search_list.jsp" %>
        <%-- Articles list --%>
        <%@include file="/WEB-INF/views/article/search_list.jsp" %>
        <%-- Partners list --%>
        <%@include file="/WEB-INF/views/company/search_list.jsp" %>
        <%-- Users list --%>
        <%@include file="/WEB-INF/views/user/search_list.jsp" %>
    </c:when>
    <c:otherwise>
        <br><br><br>
    </c:otherwise>
</c:choose>

