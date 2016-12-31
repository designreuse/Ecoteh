<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.ResourceBundle" %>

<%! private ResourceBundle resource = ResourceBundle.getBundle("captcha");
    private String key = resource.getString("captcha.client-key");%>
<c:set var="sitekey" value="<%= key %>"/>

<div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-8 col-lg-offset-2 col-xl-8 col-xl-offset-2">
    <hr>
    <h3 class="intro-text text-center">Написать отзыв</h3>
    <c:choose>
        <c:when test="${is_captcha eq true}">
            <div class="alert green text-center" role="alert"><b>Отзыв отправлено</b></div>
        </c:when>
        <c:when test="${is_captcha eq false}">
            <div class="alert red text-center" role="alert"><b>Вы не прошли верификацию капчи</b></div>
        </c:when>
    </c:choose>
    <hr>
    <form action="<c:if test="${authorized_user ne null}">/admin</c:if>/response/send" method="post">
        <div class="row">
            <div class="form-group col-lg-12">
                <label>
                    <b><span class="glyphicon glyphicon-user"
                             aria-hidden="true"></span>&nbsp;Представтесь, пожалуйста:</b>
                </label>
                <input type="text" class="form-control" name="name" minlength="2" maxlength="100" autofocus
                       placeholder="Иванов Иван Иванович" required>
            </div>
            <div class="clearfix"></div>
            <div class="form-group col-lg-12">
                <label>
                    <b><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>&nbsp;Ваш отзыв:</b>
                </label>
                <textarea class="form-control textarea" name="response" rows="5" minlength="10" maxlength="1500"
                          placeholder="Ваш отзыв" required></textarea>
            </div>
            <%-- Google reCaptcha --%>
            <c:if test="${authorized_user eq null}">
                <div class="form-group col-lg-12">
                    <div class="g-recaptcha" data-sitekey="${sitekey}"></div>
                </div>
            </c:if>
            <div class="form-group col-lg-12">
                <button type="submit" class="btn btn-default">
                    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>&nbsp;Отправить
                </button>
            </div>
        </div>
        <input type="hidden" name="url" value="/contacts">
    </form>
</div>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
