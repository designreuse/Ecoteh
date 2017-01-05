<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Yurii Salimov (yurii.alex.salimov@gmail.com)">
        <title>Добавление нового пользователя | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="Добавление нового пользователя | <c:out value="${main_company.title}"/>">
        <meta name="robots" content="noindex,nofollow">
        <meta name="description" content="Форма для добавление нового пользователя.">
        <meta name="keywords" content="Новый пользователь, добавление пользователя"/>
        <c:if test="${main_company.favicon ne null}">
            <link rel="shortcut icon" href="/resources/img/<c:out value="${main_company.favicon.url}"/>"
                  type="image/x-icon">
            <link rel="icon" href="/resources/img/<c:out value="${main_company.favicon.url}"/>" type="image/x-icon">
        </c:if>
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
              rel="stylesheet" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
              rel="stylesheet" type="text/css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet"
              type="text/css">
        <link href="/resources/css/style.min.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <%-- NAVIGATION --%>
    <jsp:include page="/WEB-INF/views/client/main/navigation.jsp"/>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
        <div class="container">
            <div class="row">
                <div class="box">
                        <%-- Path --%>
                    <p class="path">
                        <a href="/admin/" title="Перейти на главную странцу">Главная</a>
                        → <a href="/admin/menu" title="Меню администратора">Меню</a>
                        → <a href="/admin/user/all" title="Весь персонал">Персонал</a>
                        → <a href="#" title="Добавление нового пользователя">Новый пользователь</a>
                    </p>
                    <hr>
                    <h3 class="text-center" title="Добавление нового пользователя">Новый пользователь</h3>
                    <hr>
                    <div class="text-center">
                        <form enctype="multipart/form-data" method="post" action="/admin/user/add">
                            <table align="center" class="table-size">
                                <tr>
                                    <th class="ths"><span class="red">*</span>&nbsp;Имя</th>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="name" minlength="2"
                                               maxlength="100" placeholder="Имя пользователя" required>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">Логин</th>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="login" minlength="5"
                                               maxlength="100" placeholder="Логин для входа на сайт.">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">Пароль</th>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="password" minlength="5"
                                               maxlength="100" placeholder="Пароль для входа на сайт.">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">Описание</th>
                                    <td class="tds">
                                    <textarea class="form-control textarea" name="description" rows="3" title=""
                                              placeholder="Краткое описание пользователя"></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">Телефон</th>
                                    <td class="tds">
                                        <input type="text" class="phone form-control" name="phone"
                                               maxlength="20" placeholder="+38 (000) 00-00-000">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">Электронная почта</th>
                                    <td class="tds">
                                        <input type="email" class="form-control" name="email" maxlength="100"
                                               placeholder="name@mail.com">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">
                                        <a href="https://vk.com" target="_blank"
                                           title="Социальная сеть Vkontakte">Vkontakte</a>
                                    </th>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="vkontakte" minlength="5"
                                               maxlength="200" placeholder="Ссылка на профиль в Vkontakte">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">
                                        <a href="https://www.facebook.com" target="_blank"
                                           title="Социальная сеть Facebook">Facebook</a>
                                    </th>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="facebook" minlength="5"
                                               maxlength="200" placeholder="Ссылка на профиль или профиль в Facebook">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">
                                        <a href="https://twitter.com" target="_blank"
                                           title="Социальная сеть Twitter">Twitter</a>
                                    </th>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="twitter" minlength="5"
                                               maxlength="200" placeholder="Ссылка на профиль в Twitter">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">Skype</th>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="skype" minlength="5"
                                               maxlength="100" placeholder="Имя в месенджере Skype">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">Фото профиля</th>
                                    <td class="tds">
                                        <input type="file" name="photo" accept="image/*" class="form-control">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">
                                        <label title="Если пользователь позначен для отображения, он будет доступна любому пользователю, иначе еге сможет увидеть только адмиистратор.">
                                            <b>Отображение&nbsp;<span class="glyphicon glyphicon-info-sign"
                                                                      aria-hidden="true"></span></b>
                                        </label>
                                    </th>
                                    <td class="tds">
                                        <label title="Отображать пользователя">
                                            <b><input type="radio" name="is_valid" value="true" checked
                                                      required/>&nbsp;Отображать</b>
                                        </label>&nbsp;&nbsp;
                                        <label title="Не отображать пользователя">
                                            <b><input type="radio" name="is_valid" value="false"
                                                      required/>&nbsp;Не отображать</b>
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">
                                        <label title="Включить или отключить автоматическую рассылку писем от пользователей.">
                                            <b>Рассылка&nbsp;<span class="glyphicon glyphicon-info-sign"
                                                                   aria-hidden="true"></span></b>
                                        </label>
                                    </th>
                                    <td class="tds">
                                        <label title="Пользователю будут приходить письма от клиентов.">
                                            <b><input type="radio" name="is_mailing" value="true" checked
                                                      required/>&nbsp;Включить</b>
                                        </label>&nbsp;&nbsp;
                                        <label title="Не беспокоить пользователя.">
                                            <b><input type="radio" name="is_mailing" value="false"
                                                      required/>&nbsp;Отключить</b>
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">
                                        <label title="Если пользователь включен, он сможет авторизироваться на сайте, иначе доступ для него будет запрещен.">
                                            <b>Активность&nbsp;<span class="glyphicon glyphicon-info-sign"
                                                                     aria-hidden="true"></span></b>
                                        </label>
                                    </th>
                                    <td class="tds">
                                        <label title="Разблокировать пользователя">
                                            <b><input type="radio" name="is_locked" value="false"
                                                      required checked/>&nbsp;Разблокировать</b>
                                        </label>&nbsp;&nbsp;
                                        <label title="Заблокировать пользователя">
                                            <b><input type="radio" name="is_locked" value="true"
                                                      required/>&nbsp;Заблокировать</b>
                                        </label>
                                    </td>
                                </tr>
                            </table>
                            <div style="margin: 10px">
                                <button type="submit" class="btn btn-default" title="Добавить пользователя">
                                    <span class="glyphicon glyphicon-save" aria-hidden="true"></span>&nbsp;Сохранить
                                </button>
                                &nbsp;&nbsp;
                                <button type="reset" class="btn btn-default" title="Сбросить информацию">
                                    <span class="glyphicon glyphicon-retweet yellow" aria-hidden="true"></span>&nbsp;Сброс
                                </button>
                            </div>
                        </form>
                        <div align="left" class="little">
                            <p><span class="red">*</span>&nbsp;Поля обязательные для заполнения. Хорошим тоном
                                является заполнения всех полей объекта.</p>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
        <%-- FOOTER --%>
    <jsp:include page="/WEB-INF/views/client/main/footer.jsp"/>
        <%-- Scripts --%>
    <script src="/resources/js/jquery.min.js" type="text/javascript"></script>
    <script src="/resources/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/resources/js/mask.min.js" type="text/javascript" async></script>
    <script src="/resources/js/easing.min.js" type="text/javascript" async></script>
    <script src="/resources/js/totop.min.js" type="text/javascript" async></script>
    </body>
    </html>
</compress:html>

<!-- Yurii Salimov (yurii.alex.salimov@gmail.com) -->
