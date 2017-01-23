<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${map ne null}">
    <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8 col-xl-8">
        <iframe src="${map}" allowfullscreen width="100%" height="500px" frameborder="0" style="border:0"></iframe>
    </div>
</c:if>

<%-- Yuriy Salimov (yuriy.alex.salimov@gmail.com) --%>
