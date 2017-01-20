<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%!
    private final static String KEY = java.util.ResourceBundle
            .getBundle("captcha")
            .getString("captcha.client-key");
%>
<c:if test="<%= KEY != null %>">
    <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
        <div class="g-recaptcha" data-sitekey="<%= KEY %>"></div>
    </div>
    <script src="https://www.google.com/recaptcha/api.js" type="text/javascript" async></script>
</c:if>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
