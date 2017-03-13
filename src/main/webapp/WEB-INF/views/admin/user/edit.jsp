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
        <meta name="author" content="Yurii Salimov (yuriy.alex.salimov@gmail.com)">
        <title>Редактирование &quot;<c:out value="${user.name}"/>&quot; | <c:out
                value="${main_company.title}"/></title>
        <meta name="title"
              content="Редактирование &quot;<c:out value="${user.name}"/>&quot; | <c:out value="${main_company.title}"/>">
        <meta name="robots" content="noindex,nofollow">
        <meta name="description"
              content="Форма для редактирования информации о &quot;<c:out value="${user.name}"/>&quot;.">
        <meta name="keywords" content="Редактирование, <c:out value="${user.name}"/>"/>
        <link rel="shortcut icon" href="<c:url value="${favicon.url}"/>" type="image/x-icon">
        <link rel="icon" href="<c:url value="${favicon.url}"/>" type="image/x-icon">
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
              rel="stylesheet" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
              rel="stylesheet" type="text/css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet"
              type="text/css">
        <link href="<c:url value="/resources/css/style.min.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/css/lightgallery.min.css"/>" rel="stylesheet" type="text/css">
    </head>
    <body>
    <jsp:include page="/WEB-INF/views/client/main/navigation.jsp"/>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <div class="container">
            <div class="row">
                <div class="box">
                    <p class="path">
                        <a href="<c:url value="/"/>" title="Перейти на главную странцу">Главная</a>
                        → <a href="<c:url value="/admin/menu"/>" title="Меню администратора">Меню</a>
                        → <a href="<c:url value="/admin/user/all"/>">Персонал</a>
                        → <a href="#">Редактирование пользователя</a>
                    </p>
                    <hr>
                    <h3 class="text-center">
                        Редактировать &quot;<a href="<c:url value="/admin/user/${user.url}"/>"><c:out
                            value="${user.name}"/></a>&quot;
                    </h3>
                    <hr>
                    <div class="text-center">
                        <form action="<c:url value="/admin/user/update"/>" method="post" enctype="multipart/form-data">
                            <table align="center" class="table-size">
                                <tr>
                                    <td class="ths"><span class="red">*</span>&nbsp;Имя</td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="name" minlength="2"
                                               maxlength="100" placeholder="Имя пользователя" required
                                               value="<c:out value="${user.name}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Логин</td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="login" minlength="5"
                                               maxlength="100" placeholder="Логин для входа на сайт."
                                               value="<c:out value="${user.login}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Пароль</td>
                                    <td class="tds">
                                        <input type="password" class="form-control" name="password" minlength="5"
                                               maxlength="100" placeholder="Пароль для входа на сайт."
                                               value="<c:out value="${user.password}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Описание</td>
                                    <td class="tds">
                                    <textarea class="form-control textarea" name="description" title=""
                                              placeholder="Краткое описание компании"
                                              rows="3"><c:out value="${user.description}"/></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <span class="red">*</span>
                                        <c:choose>
                                            <c:when test="${user.contacts.mobilePhone ne ''}">
                                                <a href="tel:<c:out value="${user.contacts.mobilePhone}"/>"
                                                   title="Позвонить &quot;<c:out value="${user.name}"/>&quot; на телефон">
                                                    Мобильный телефон
                                                </a>
                                            </c:when>
                                            <c:otherwise>Мобильный телефон</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="tds">
                                        <input type="text" class="phone form-control" name="mobile_phone"
                                               required
                                               maxlength="20" placeholder="+38 (000) 00-00-000"
                                               value="<c:out value="${user.contacts.mobilePhone}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <c:choose>
                                            <c:when test="${user.contacts.landlinePhone ne ''}">
                                                <a href="tel:<c:out value="${user.contacts.landlinePhone}"/>"
                                                   title="Позвонить &quot;<c:out value="${user.name}"/>&quot; на телефон">
                                                    Домашний телефон
                                                </a>
                                            </c:when>
                                            <c:otherwise>Домашний телефон</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="tds">
                                        <input type="text" class="phone form-control" name="landline_phone"
                                               maxlength="20" placeholder="+38 (000) 00-00-000"
                                               value="<c:out value="${user.contacts.landlinePhone}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <c:choose>
                                            <c:when test="${user.contacts.fax ne ''}">
                                                <a href="tel:<c:out value="${user.contacts.fax}"/>"
                                                   title="Позвонить &quot;<c:out value="${user.name}"/>&quot; на телефон">
                                                    Факс
                                                </a>
                                            </c:when>
                                            <c:otherwise>Факс</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="tds">
                                        <input type="text" class="phone form-control" name="fax"
                                               maxlength="20" placeholder="+38 (000) 00-00-000"
                                               value="<c:out value="${user.contacts.fax}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <span class="red">*</span>
                                        <c:choose>
                                            <c:when test="${user.contacts.email ne ''}">
                                                <a href="mailto:<c:out value="${user.contacts.email}"/>"
                                                   target="_blank"
                                                   title="Написать письмо для &quot;<c:out value="${user.name}"/>&quot;">
                                                    Электронная почта
                                                </a>
                                            </c:when>
                                            <c:otherwise>Электронная почта</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="tds">
                                        <input type="email" class="form-control" name="email" maxlength="100"
                                               required placeholder="name@mail.com"
                                               value="<c:out value="${user.contacts.email}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <c:choose>
                                            <c:when test="${user.contacts.vkontakte ne ''}">
                                                <a href="<c:out value="${user.contacts.vkontakte}"/>"
                                                   title="Профиль &quot;<c:out value="${user.name}"/>&quot; в ВКонтакте"
                                                   target="_blank">Vkontakte</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="https://vk.com" target="_blank"
                                                   title="Социальная сеть Vkontakte">Vkontakte</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="vkontakte" minlength="5"
                                               maxlength="200"
                                               value="<c:out value="${user.contacts.vkontakte}"/>"
                                               placeholder="Ссылка на групу или профиль в социальной сети Vkontakte">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <c:choose>
                                            <c:when test="${user.contacts.facebook ne ''}">
                                                <a href="<c:out value="${user.contacts.facebook}"/>"
                                                   title="Профиль &quot;<c:out value="${user.name}"/>&quot; в Facebook"
                                                   target="_blank">Facebook</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="https://www.facebook.com" target="_blank"
                                                   title="Социальная сеть Facebook">Facebook</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="facebook"
                                               minlength="5" maxlength="200"
                                               placeholder="Ссылка на групу или профиль в социальной сети Facebook"
                                               value="<c:out value="${user.contacts.facebook}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <c:choose>
                                            <c:when test="${user.contacts.twitter ne ''}">
                                                <a href="<c:out value="${user.contacts.twitter}"/>"
                                                   title="Профиль &quot;<c:out value="${user.name}"/>&quot; в Twitter"
                                                   target="_blank">Twitter</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="https://twitter.com" target="_blank"
                                                   title="Социальная сеть Twitter">Twitter</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="twitter" minlength="5"
                                               maxlength="200" placeholder="Ссылка в социальной сети Twitter"
                                               value="<c:out value="${user.contacts.twitter}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <c:choose>
                                            <c:when test="${user.contacts.skype ne ''}">
                                                <a href="skype:<c:out value="${user.contacts.skype}"/>?call"
                                                   title="Позвонить &quot;<c:out value="${user.name}"/>&quot; в Skype">
                                                    Skype
                                                </a>
                                            </c:when>
                                            <c:otherwise>Skype</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="skype" minlength="5"
                                               maxlength="100" placeholder="Имя в месенджере Skype"
                                               value="<c:out value="${user.contacts.skype}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Фото профиля</td>
                                    <td class="tds">
                                        <c:choose>
                                            <c:when test="${user.photo.url ne ''}">
                                                <a href="<c:url value="${user.photo.url}"/>" rel="lightgallery"
                                                   title="<c:out value="${user.name}"/>">
                                                    <img class="img-responsive img-in-list"
                                                         alt="<c:out value="${user.name}"/>"
                                                         src="<c:url value="${user.photo.url}"/>"
                                                         onerror="this.src='<c:url
                                                                 value="/resources/img/static/default_user.png"/>'">
                                                </a>
                                            </c:when>
                                            <c:otherwise>
                                                <img class="img-responsive img-in-list"
                                                     alt="<c:out value="${user.name}"/>"
                                                     src="<c:url value="/resources/img/static/default_user.png"/>">
                                            </c:otherwise>
                                        </c:choose><br>
                                        <input type="file" name="photo" class="form-control">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <label title="Если пользователь позначен для отображения, он будет доступна любому пользователю, иначе еге сможет увидеть только адмиистратор.">
                                            Отображение&nbsp;<span class="glyphicon glyphicon-info-sign"
                                                                   aria-hidden="true"></span>
                                        </label>
                                    </td>
                                    <td class="tds">
                                        <label title="Отображать пользователя">
                                            <input type="radio" name="is_valid" value="true"
                                                   <c:if test="${user.validated}">checked</c:if>
                                                   required/>&nbsp;Отображать
                                        </label>&nbsp;&nbsp;
                                        <label title="Не отображать пользователя">
                                            <input type="radio" name="is_valid" value="false"
                                                   <c:if test="${!user.validated}">checked</c:if>
                                                   required/>&nbsp;Не отображать
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <label title="Включить или отключить автоматическую рассылку писем от пользователей.">
                                            Рассылка&nbsp;<span class="glyphicon glyphicon-info-sign"
                                                                aria-hidden="true"></span>
                                        </label>
                                    </td>
                                    <td class="tds">
                                        <label title="Пользователю будут приходить письма от клиентов.">
                                            <input type="radio" name="is_mailing" value="true"
                                                   <c:if test="${user.mailing}">checked</c:if> required/>&nbsp;Включить
                                        </label>&nbsp;&nbsp;
                                        <label title="Не беспокоить пользователя.">
                                            <input type="radio" name="is_mailing" value="false" required
                                                   <c:if test="${!user.mailing}">checked</c:if>/>&nbsp;Отключить
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <label title="Если пользователь включен, он сможет авторизироваться на сайте, иначе доступ для него будет запрещен.">
                                            Активность&nbsp;<span class="glyphicon glyphicon-info-sign"
                                                                  aria-hidden="true"></span>
                                        </label>
                                    </td>
                                    <td class="tds">
                                        <label title="Разблокировать пользователя">
                                            <input type="radio" name="is_locked" value="false"
                                                   <c:if test="${!user.locked}">checked</c:if>
                                                   required/>&nbsp;Разблокировать
                                        </label>&nbsp;&nbsp;
                                        <label title="Заблокировать пользователя">
                                            <input type="radio" name="is_locked" value="true"
                                                   <c:if test="${user.locked}">checked</c:if>
                                                   required/>&nbsp;Заблокировать
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
    <jsp:include page="/WEB-INF/views/client/main/footer.jsp"/>
    <script src="<c:url value="/resources/js/jquery.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/lightgallery.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/mask.min.js"/>" type="text/javascript" async></script>
    <script src="<c:url value="/resources/js/easing.min.js"/>" type="text/javascript" async></script>
    <script src="<c:url value="/resources/js/totop.min.js"/>" type="text/javascript" async></script>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
