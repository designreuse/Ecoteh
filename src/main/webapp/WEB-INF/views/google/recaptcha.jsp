<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%!
    private final static String KEY = java.util.ResourceBundle
            .getBundle("captcha")
            .getString("captcha.client-key");
%>
<div class="form-group col-lg-12">
    <div class="g-recaptcha" data-sitekey="<%= KEY %>"></div>
</div>
<script src="https://www.google.com/recaptcha/api.js" type="text/javascript" async></script>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
