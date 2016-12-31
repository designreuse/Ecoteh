<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle" %>

<c:if test="${(param.error ne null) or (is_forgot ne null)}">
    <%! private ResourceBundle resource = ResourceBundle.getBundle("captcha");
        private String key = resource.getString("captcha.client-key");%>
    <c:set var="sitekey" value="<%= key %>"/>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
        <div class="container">
            <div class="row">
                <div class="box">
                    <div class="col-xs-12 col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4 col-lg-4 col-lg-offset-4 col-xl-4 col-xl-offset-4">
                        <div class="text-center">
                            <hr>
                            <h1 class="intro-text text-center">Напомнить пароль</h1>
                            <c:choose>
                                <c:when test="${is_captcha eq false}">
                                    <div class="login-alert red" role="alert">
                                        <b>Вы не прошли верификацию капчи</b><br>
                                    </div>
                                </c:when>
                                <c:when test="${is_forgot eq false}">
                                    <div class="login-alert red" role="alert">
                                        <b>Пользователь не найден</b><br>
                                    </div>
                                </c:when>
                                <c:when test="${(is_forgot eq true) and (is_captcha eq true)}">
                                    <div class="login-alert green" role="alert">
                                        <b>Сообщение отправлено на почту</b>
                                    </div>
                                </c:when>
                            </c:choose>
                            <hr>
                                <%-- Forgot password form --%>
                            <form action="/forgot_password" method="post">
                                <div class="row">
                                    <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                                        <label>
                                                <span class="glyphicon glyphicon-user"
                                                      aria-hidden="true"></span>&nbsp;Логин или e-mail:
                                        </label>
                                        <input class="form-control" type="text" name="username" required
                                               minlength="3" maxlength="100" autofocus value="${username}"
                                               placeholder="Введите логин или e-mail">
                                    </div>
                                    <div class="form-group col-lg-12">
                                        <div class="g-recaptcha" data-sitekey="${sitekey}"></div>
                                    </div>
                                    <div class="text-center form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                                        <button type="submit" class="btn btn-default">
                                                <span class="glyphicon glyphicon-send"
                                                      aria-hidden="true"></span>&nbsp;Отправить
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://www.google.com/recaptcha/api.js" type="text/javascript"></script>
</c:if>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
