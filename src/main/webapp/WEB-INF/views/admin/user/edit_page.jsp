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
        <meta name="author" content="Yurii Salimov">
        <title>Редактирование &quot;<c:out value="${user.name}"/>&quot; | <c:out
                value="${main_company.title}"/></title>
        <meta name="title"
              content="Редактирование &quot;<c:out value="${user.name}"/>&quot; | <c:out value="${main_company.title}"/>">
        <meta name="robots" content="noindex,nofollow">
        <meta name="description"
              content="Форма для редактирования информации о &quot;<c:out value="${user.name}"/>&quot;.">
        <meta name="keywords" content="Редактирование, <c:out value="${user.name}"/>"/>
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
        <link href="/resources/css/lightgallery.min.css" rel="stylesheet" type="text/css">
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
                        → <a href="#">Редактирование пользователя</a>
                    </p>
                    <hr>
                    <h3 class="text-center">
                        Редактировать &quot;<a href="/admin/user/<c:out value="${user.url}"/>"><c:out
                            value="${user.name}"/></a>&quot;
                    </h3>
                    <hr>
                    <div class="text-center">
                        <form enctype="multipart/form-data" method="post" action="/admin/user/update">
                            <table align="center" class="table-size">
                                <tr>
                                    <th class="ths"><span class="red">*</span>&nbsp;Имя</th>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="name" minlength="2"
                                               maxlength="100" placeholder="Имя пользователя" required
                                               value="<c:out value="${user.name}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">Логин</th>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="login" minlength="5"
                                               maxlength="100" placeholder="Логин для входа на сайт."
                                               value="<c:out value="${user.login}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">Пароль</th>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="password" minlength="5"
                                               maxlength="100" placeholder="Пароль для входа на сайт."
                                               value="<c:out value="${user.password}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">Описание</th>
                                    <td class="tds">
                                    <textarea class="form-control textarea" name="description" title=""
                                              placeholder="Краткое описание компании"
                                              rows="3"><c:out value="${user.description}"/></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">
                                        <c:choose>
                                            <c:when test="${user.phone ne null}">
                                                <a href="tel:<c:out value="${user.phone}"/>"
                                                   title="Позвонить &quot;<c:out value="${user.name}"/>&quot; на телефон">
                                                    Телефон
                                                </a>
                                            </c:when>
                                            <c:otherwise>Телефон</c:otherwise>
                                        </c:choose>
                                    </th>
                                    <td class="tds">
                                        <input type="text" class="phone form-control" name="phone"
                                               maxlength="20" placeholder="+38 (000) 00-00-000"
                                               value="<c:out value="${user.phone}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">
                                        <c:choose>
                                            <c:when test="${user.email ne null}">
                                                <a href="mailto:<c:out value="${user.email}"/>" target="_blank"
                                                   title="Написать письмо для &quot;<c:out value="${user.name}"/>&quot;">
                                                    Электронная почта
                                                </a>
                                            </c:when>
                                            <c:otherwise>Электронная почта</c:otherwise>
                                        </c:choose>
                                    </th>
                                    <td class="tds">
                                        <input type="email" class="form-control" name="email" maxlength="100"
                                               placeholder="name@mail.com" value="<c:out value="${user.email}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">
                                        <c:choose>
                                            <c:when test="${user.vkontakte ne null}">
                                                <a href="https://vk.com/<c:out value="${user.vkontakte}"/>"
                                                   title="Профиль &quot;<c:out value="${user.name}"/>&quot; в ВКонтакте"
                                                   target="_blank">Vkontakte</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="https://vk.com" target="_blank"
                                                   title="Социальная сеть Vkontakte">Vkontakte</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </th>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="vkontakte" minlength="5"
                                               maxlength="200" value="<c:out value="${user.vkontakte}"/>"
                                               placeholder="Ссылка на групу или профиль в социальной сети Vkontakte">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">
                                        <c:choose>
                                            <c:when test="${user.facebook ne null}">
                                                <a href="https://www.facebook.com/<c:out value="${user.facebook}"/>"
                                                   title="Профиль &quot;<c:out value="${user.name}"/>&quot; в Facebook"
                                                   target="_blank">Facebook</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="https://www.facebook.com" target="_blank"
                                                   title="Социальная сеть Facebook">Facebook</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </th>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="facebook"
                                               minlength="5" maxlength="200"
                                               placeholder="Ссылка на групу или профиль в социальной сети Facebook"
                                               value="<c:out value="${user.facebook}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">
                                        <c:choose>
                                            <c:when test="${user.twitter ne null}">
                                                <a href="https://twitter.com/<c:out value="${user.twitter}"/>"
                                                   title="Профиль &quot;<c:out value="${user.name}"/>&quot; в Twitter"
                                                   target="_blank">Twitter</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="https://twitter.com" target="_blank"
                                                   title="Социальная сеть Twitter">Twitter</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </th>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="twitter" minlength="5"
                                               maxlength="200" placeholder="Ссылка в социальной сети Twitter"
                                               value="<c:out value="${user.twitter}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">
                                        <c:choose>
                                            <c:when test="${user.skype ne null}">
                                                <a href="skype:<c:out value="${user.skype}"/>?call"
                                                   title="Позвонить &quot;<c:out value="${user.name}"/>&quot; в Skype">
                                                    Skype
                                                </a>
                                            </c:when>
                                            <c:otherwise>Skype</c:otherwise>
                                        </c:choose>
                                    </th>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="skype" minlength="5"
                                               maxlength="100" placeholder="Имя в месенджере Skype"
                                               value="<c:out value="${user.skype}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">Фото профиля</th>
                                    <td class="tds">
                                        <c:choose>
                                            <c:when test="${user.photo ne null}">
                                                <a href="/resources/img/<c:out value="${user.photo.url}"/>"
                                                   rel="lightgallery">
                                                    <img class="img-logo" alt="<c:out value="${user.name}"/>"
                                                         src="/resources/img/<c:out value="${user.photo.url}"/>">
                                                </a><br><br>
                                                <label title="Добавить новое фото">
                                                    <b><input type="radio" name="photo_action" value="replace" checked
                                                              required/>&nbsp;Заменить</b>
                                                </label>&nbsp;&nbsp;
                                                <label title="Удалить фото">
                                                    <b><input type="radio" name="photo_action" value="delete"
                                                              required/>&nbsp;Удалить</b>
                                                </label><br>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="hidden" name="photo_action" value="replace" checked
                                                       required/>
                                            </c:otherwise>
                                        </c:choose>
                                        <br><input type="file" name="photo" accept="image/*" class="form-control"><br>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">
                                        <label title="Если пользователь позначен для отображения, он будет доступна любому пользователю, иначе еге сможет увидеть только адмиистратор.">
                                            <b>Отображение&nbsp;<span aria-hidden="true"
                                                                      class="glyphicon glyphicon-info-sign"></span>
                                        </label>
                                    </th>
                                    <td class="tds">
                                        <label title="Отображать пользователя">
                                            <b><input type="radio" name="is_valid" value="true"
                                                      <c:if test="${user.validated}">checked</c:if> required/>&nbsp;Отображать
                                        </label>&nbsp;&nbsp;
                                        <label title="Не отображать пользователя">
                                            <b><input type="radio" name="is_valid" value="false" required
                                                      <c:if test="${!user.validated}">checked</c:if>/>&nbsp;Не
                                                отображать
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
                                            <b><input type="radio" name="is_mailing" value="true"
                                                      <c:if test="${user.mailing}">checked</c:if> required/>&nbsp;Включить</b>
                                        </label>&nbsp;&nbsp;
                                        <label title="Не беспокоить пользователя.">
                                            <b><input type="radio" name="is_mailing" value="false" required
                                                      <c:if test="${!user.mailing}">checked</c:if>/>&nbsp;Отключить</b>
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
                                                      <c:if test="${!user.locked}">checked</c:if>
                                                      required/>&nbsp;Разблокировать</b>
                                        </label>&nbsp;&nbsp;
                                        <label title="Заблокировать пользователя">
                                            <b><input type="radio" name="is_locked" value="true"
                                                      <c:if test="${user.locked}">checked</c:if>
                                                      required/>&nbsp;Заблокировать</b>
                                        </label>
                                    </td>
                                </tr>
                            </table>
                            <input type="hidden" name="url" value="<c:out value="${user.url}"/>">
                            <div style="margin: 10px">
                                <button type="submit" class="btn btn-default" title="Сохранить изменения">
                                    <span class="glyphicon glyphicon-save" aria-hidden="true"></span>&nbsp;Сохранить
                                </button>
                                &nbsp;&nbsp;
                                <button type="reset" class="btn btn-default" title="Сбросить изменения">
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
    <script src="/resources/js/lightgallery.min.js" type="text/javascript"></script>
    <script src="/resources/js/mask.min.js" type="text/javascript" async></script>
    <script src="/resources/js/easing.min.js" type="text/javascript" async></script>
    <script src="/resources/js/totop.min.js" type="text/javascript" async></script>
    </body>
    </html>
</compress:html>

<!-- Yurii Salimov (yurii.alex.salimov@gmail.com) -->
