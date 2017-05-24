<%--
The result of the search.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:choose>
    <c:when test="${result}">
        <%-- Categories list --%>
        <c:if test="${categories_length gt 0}">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <jsp:include page="/WEB-INF/views/category/list.jsp"/>
            </div>
        </c:if>
        <%-- Articles list --%>
        <c:if test="${articles_length gt 0}">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                <jsp:include page="/WEB-INF/views/article/list.jsp"/>
            </div>
        </c:if>
        <%-- Partners list --%>
        <c:if test="${partners_length gt 0}">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                <jsp:include page="/WEB-INF/views/company/list.jsp"/>
            </div>
        </c:if>
        <%-- Users list --%>
        <c:if test="${users_length gt 0}">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                <div class="container">
                    <div class="row">
                        <div class="box">
                            <jsp:include page="/WEB-INF/views/user/list.jsp"/>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </c:when>
    <c:otherwise>
        <br><br><br>
    </c:otherwise>
</c:choose>
