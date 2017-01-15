<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="col-xs-12 col-sm-12 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
    <hr>
    <h3 class="intro-text text-center">Оставить сообщение</h3>
    <c:choose>
        <c:when test="${is_captcha eq true}">
            <div class="alert green text-center" role="alert"><b>Сообщение отправлено</b></div>
        </c:when>
        <c:when test="${is_captcha eq false}">
            <div class="alert red text-center" role="alert"><b>Вы не прошли верификацию капчи</b></div>
        </c:when>
    </c:choose>
    <hr>
    <p>
        Оставьте нам свое сообщение, и мы свяжемся с Вами
        <c:choose>
            <c:when test="${main_company.open}">в течении часа.</c:when>
            <c:otherwise>в рабочие дни с 9:00 до 17:00.</c:otherwise>
        </c:choose>
    </p>
    <c:if test="${authorized_user ne null}"><c:set var="request" value="/admin"/></c:if>
    <form action="<c:url value="${request}/send_message"/>" method="post">
        <div class="row">
            <div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4 col-xl-4">
                <label>
                    <b><span class="glyphicon glyphicon-user"
                             aria-hidden="true"></span>&nbsp;Представтесь, пожалуйста:</b>
                </label>
                <input type="text" class="form-control" name="name" minlength="2" maxlength="100"
                       placeholder="Иванов Иван Иванович" required>
            </div>
            <div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4 col-xl-4">
                <label>
                    <b><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>&nbsp;Ваш E-mail:</b>
                </label>
                <input type="email" class="form-control" name="email" minlength="5" maxlength="200"
                       placeholder="myemail@email.com">
            </div>
            <div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-4 col-xl-4">
                <label>
                    <b><span class="glyphicon glyphicon-phone" aria-hidden="true"></span>&nbsp;Ваш номер телефона:</b>
                </label>
                <input type="text" class="phone form-control" name="phone" placeholder="+38 (000) 00-00-000" required>
            </div>
            <div class="form-group col-lg-12">
                <label>
                    <b><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>&nbsp;Ваше сообщение:</b>
                </label>
                <textarea class="form-control textarea" name="message" rows="6" maxlength="1000"
                          title="" placeholder="Ваше сообщение" required></textarea>
            </div>
            <c:if test="${authorized_user eq null}">
                <jsp:include page="/WEB-INF/views/google/recaptcha.jsp"/>
            </c:if>
            <div class="form-group col-lg-12">
                <button type="submit" class="btn btn-default">
                    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>&nbsp;Отправить
                </button>
            </div>
            <div class="clearfix"></div>
        </div>
        <input type="hidden" name="url" value="/contacts">
    </form>
</div>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
