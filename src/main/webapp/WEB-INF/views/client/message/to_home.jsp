<%--
Form for sending a quick message in home page.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
    <hr>
    <h3 class="intro-text text-center">Закажите бесплатный звонок</h3>
    <hr>
    <%-- Captcha response --%>
    <c:choose>
        <c:when test="${is_captcha eq true}">
            <h4 class="message-text green">
                Сообщение отправлено</h4>
        </c:when>
        <c:when test="${is_captcha eq false}">
            <h4 class="message-text red">
                Вы не прошли верификацию капчи
            </h4>
        </c:when>
    </c:choose>
    <%-- Administrator actions --%>
    <c:if test="${authorized_user ne null}">
        <c:set var="request" value="/admin"/>
    </c:if>
    <%-- Form for sending a quick message --%>
    <form action="<c:url value="${request}/send_message"/>" method="post">
        <div class="row">
            <div class="text-center form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div class="inner-addon left-addon">
                    <span class="glyphicon glyphicon-user"></span>
                    <input type="text" class="form-control" name="name" minlength="2" maxlength="50"
                           placeholder="Представтесь, пожалуйста" required/>
                </div>
            </div>
            <div class="text-center form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div class="inner-addon left-addon">
                    <span class="glyphicon glyphicon-phone"></span>
                    <input type="text" class="phone form-control" name="phone"
                           placeholder="Ваш номер телефона" required/>
                </div>
            </div>
            <c:if test="${authorized_user eq null}">
                <%-- Google reCaptcha --%>
                <jsp:include page="/WEB-INF/views/google/recaptcha.jsp"/>
            </c:if>
            <div class="text-center form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <button type="submit" class="btn btn-default">
                    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                    &nbsp;Перезвоните
                </button>
            </div>
        </div>
        <input type="hidden" name="url" value="${request}/">
        <input type="hidden" name="message" value="Перезвоните мне, пожалуйста!">
    </form>
    <div class="message-text">
        <%-- Call back message --%>
        <jsp:include page="/WEB-INF/views/client/message/call_back_message.jsp"/>
    </div>
    <hr>
</div>
