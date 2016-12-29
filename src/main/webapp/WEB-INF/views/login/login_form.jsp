<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
    <div class="container">
        <div class="row">
            <div class="box">
                <%-- Path --%>
                <p class="path">
                    <a href="/" title="Перейти на главную страницу">Главная</a>
                    → <a href="#">Авторизация</a>
                </p>
                <div class="col-xs-12 col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4 col-lg-4 col-lg-offset-4 col-xl-4 col-xl-offset-4">
                    <div class="text-center">
                        <hr>
                        <h1 class="intro-text text-center">Авторизация</h1>
                        <c:choose>
                            <c:when test="${param.error ne null}">
                                <div class="alert red" role="alert">
                                    <b>Неверный логин или пароль</b><br>
                                </div>
                            </c:when>
                            <c:when test="${param.logout ne null}">
                                <div class="alert green" role="alert">
                                    <b>Вы вышли из системы</b><br>
                                </div>
                            </c:when>
                        </c:choose>
                        <hr>
                        <%-- Login form --%>
                        <form action="/login" method="post">
                            <div class="row">
                                <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                                    <label title="Введите логин в формате (A-Z, a-z, 0-9, _)">
                                            <span class="glyphicon glyphicon-user"
                                                  aria-hidden="true"></span>&nbsp;Имя пользователя:
                                    </label>
                                    <input id="username" class="form-control" type="text" name="username" required
                                           pattern="[A-Za-z0-9_]{3,100}" minlength="3" maxlength="100" autofocus
                                           placeholder="Введите логин">
                                </div>
                                <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                                    <label title="Введите пароль в формате (A-Z, a-z, 0-9)">
                                            <span class="glyphicon glyphicon-check"
                                                  aria-hidden="true"></span>&nbsp;Пароль:
                                    </label>
                                    <input id="password" class="form-control" type="password" name="password"
                                           pattern="[A-Za-z0-9]{3,100}" minlength="3" maxlength="100" required
                                           placeholder="Введите пароль">
                                </div>
                                <div class="text-center form-group col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                                    <button type="submit" class="btn btn-default">
                                        <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>&nbsp;Войти
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

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
