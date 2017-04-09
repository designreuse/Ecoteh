<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="captcha" var="recaptcha" scope="application"/>
<fmt:message var="key" bundle="${recaptcha}" key="captcha.client-key" scope="application"/>

<c:if test="${not empty key}">
    <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <div class="g-recaptcha" data-sitekey="<c:out value="${key}"/>"></div>
    </div>
    <script src="https://www.google.com/recaptcha/api.js" type="text/javascript" async></script>
</c:if>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
