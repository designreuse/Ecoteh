<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-xs-12 col-sm-12 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 col-xl-8 col-xl-offset-2">
    <hr>
    <h3 class="intro-text text-center">Сообщение колегам</h3>
    <c:if test="${is_captcha}">
        <div class="alert green text-center" role="alert"><b>Сообщение отправлено</b></div>
    </c:if>
    <hr>
    <form action="<c:url value="/admin/user/send_message"/> method="post">
        <div class="row">
            <div class="form-group col-lg-12">
                <label>
                    <b><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>&nbsp;Тема сообщения:</b>
                </label>
                <input type="text" class="form-control" name="subject" minlength="2" maxlength="100"
                       placeholder="Тема сообщения" required>
            </div>
            <div class="form-group col-lg-12">
                <label>
                    <b><span class="glyphicon glyphicon-comment" aria-hidden="true"></span>&nbsp;Текст сообщение:</b>
                </label>
                <textarea class="form-control textarea" name="message" rows="6" maxlength="1000"
                          placeholder="Сообщение для колег" required></textarea>
            </div>
            <div class="form-group col-lg-12">
                <button type="submit" class="btn btn-default">
                    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>&nbsp;Отправить
                </button>
            </div>
        </div>
    </form>
</div>

<!-- Yurii Salimov (yurii.alex.salimov@gmail.com) -->
