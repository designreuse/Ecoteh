<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.ResourceBundle" %>

<%! private ResourceBundle resource = ResourceBundle.getBundle("captcha");
    private String key = resource.getString("captcha.client-key");%>
<c:set var="sitekey" value="<%= key %>"/>

<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 col-xl-4">
    <hr>
    <h3 class="intro-text text-center">Закажите бесплатный звонок</h3>
    <hr>
    <c:choose>
        <c:when test="${is_captcha eq true}">
            <h4 class="message-text green">Сообщение отправлено</h4>
        </c:when>
        <c:when test="${is_captcha eq false}">
            <h4 class="message-text red">Вы не прошли верификацию капчи</h4>
        </c:when>
    </c:choose>
    <form action="<c:if test="${authorized_user ne null}">/admin</c:if>/send_message" method="post">
        <div class="row">
            <div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-6 col-xl-6">
                <label>
                    <b><span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;Имя:</b>
                </label>
                <input type="text" class="form-control" name="name" minlength="2"
                       maxlength="50" placeholder="Иванов Иван" required>
            </div>
            <div class="form-group col-xs-12 col-sm-6 col-md-6 col-lg-6 col-xl-6">
                <label>
                    <b><span class="glyphicon glyphicon-phone" aria-hidden="true"></span>&nbsp;Телефона:</b>
                </label>
                <input type="text" class="phone form-control" name="phone" placeholder="+38 (000) 00-00-000" required>
            </div>
            <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                <label>
                    <b><span class="glyphicon glyphicon-comment" aria-hidden="true"></span>&nbsp;Коментарий:</b>
                </label>
                <textarea class="form-control textarea" name="message" rows="3" title=""
                          maxlength="300" placeholder="Ваш коментарий"></textarea>
            </div>
            <%-- Google reCaptcha --%>
            <c:if test="${authorized_user eq null}">
                <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <div class="text-center g-recaptcha" data-sitekey="${sitekey}"></div>
                </div>
            </c:if>
            <div class="text-center form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                <button type="submit" class="btn btn-default">
                    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>&nbsp;Перезвоните
                </button>
            </div>
        </div>
        <input type="hidden" name="url" value="/">
        <input type="hidden" name="email" value="">
    </form>
    <div class="message-text">
        Оставьте нам свое сообщение, и мы перезвоним Вам
        <c:choose>
            <c:when test="${main_company.open}">в течении часа.</c:when>
            <c:otherwise>в рабочие дни с 9:00 до 17:00.</c:otherwise>
        </c:choose>
    </div>
    <hr>
</div>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
