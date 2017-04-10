<%--
Site verification in the Google and Yandex.
Verification keys are stored in the verification.properties
configuration file.

Yuriy Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="verification" var="verification" scope="application"/>
<fmt:message var="google" bundle="${verification}" key="google.verification" scope="application"/>
<fmt:message var="yandex" bundle="${verification}" key="yandex.verification" scope="application"/>

<meta name="google-site-verification" content="<c:out value="${google}"/>"/>
<meta name="yandex-verification" content="<c:out value="${yandex}"/>"/>
