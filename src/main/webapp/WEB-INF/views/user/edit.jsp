<%--
Page for editing a incoming user.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

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
        <meta name="robots" content="noindex,nofollow">
        <title>Редактирование <c:out value="&quot;${user.name}&quot; | ${main_company.title}"/></title>
        <meta name="title" content="Редактирование <c:out value="&quot;${user.name}&quot; | ${main_company.title}"/>">
        <link rel="shortcut icon" href="<c:url value="${favicon.url}"/>">
        <link rel="icon" href="<c:url value="${favicon.url}"/>">
            <%-- CSS styles --%>
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
              rel="stylesheet">
        <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
              rel="stylesheet">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="<c:url value="/resources/css/style.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/lightgallery.min.css"/>" rel="stylesheet">
    </head>
    <body>
        <%-- Navigation bar --%>
    <jsp:include page="/WEB-INF/views/home/navigation.jsp"/>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <div class="container">
            <div class="row">
                <div class="box">
                        <%-- Site page path --%>
                    <p class="path">
                        <a href="<c:url value="/"/>" title="Перейти на главную странцу">Главная</a>
                        → <a href="<c:url value="/admin/menu"/>" title="Меню администратора">Меню</a>
                        → <a href="<c:url value="/admin/user/all"/>">Персонал</a>
                        → <a href="<c:url value="/admin/user/edit/${user.url}"/>">Редактирование пользователя</a>
                    </p>
                    <hr>
                    <h3 class="text-center">
                        Редактировать
                        &quot;<a href="<c:url value="/admin/user/${user.url}"/>"
                                 title=""><c:out value="${user.name}"/></a>&quot;
                    </h3>
                    <hr>
                    <div class="text-center">
                            <%-- Form for editing a incoming user --%>
                        <form action="<c:url value="/admin/user/update"/>" method="post" enctype="multipart/form-data">
                            <table align="center" class="table-size">
                                <tr>
                                    <td class="ths">
                                        <span class="red">*</span>&nbsp;Имя
                                    </td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="name" minlength="2"
                                               maxlength="100" placeholder="Имя пользователя" required
                                               value="<c:out value="${user.name}"/>">
                                    </td>
                                </tr>
                                <c:set var="me"
                                       value="${(user eq authorized_user) or (authorized_user.role eq 'SUPERADMIN')}"/>
                                <tr>
                                    <td class="ths">Логин</td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="login"
                                        <c:choose>
                                        <c:when test="${me eq true}">
                                               minlength="5" maxlength="100" type="text"
                                               placeholder="Логин для входа на сайт."
                                               value="<c:out value="${user.login}"/>"
                                        </c:when>
                                        <c:otherwise>
                                               type="password" value="******" disabled
                                        </c:otherwise>
                                        </c:choose>
                                        >
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Пароль</td>
                                    <td class="tds">
                                        <input class="form-control" name="password"
                                        <c:choose>
                                        <c:when test="${me eq true}">
                                               minlength="5" maxlength="100" type="text"
                                               placeholder="Пароль для входа на сайт."
                                               value="<c:out value="${user.password}"/>"
                                        </c:when>
                                        <c:otherwise>
                                               type="password" value="******" disabled
                                        </c:otherwise>
                                        </c:choose>
                                        >
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
                                <c:set var="contacts" value="${user.contacts}"/>
                                <tr>
                                    <td class="ths">
                                        <span class="red">*</span>
                                        <c:choose>
                                            <c:when test="${not empty contacts.mobilePhone}">
                                                <a href="tel:<c:out value="${contacts.mobilePhone}"/>"
                                                   title="Позвонить &quot;<c:out value="${user.name}"/>&quot; на телефон">
                                                    Мобильный телефон
                                                </a>
                                            </c:when>
                                            <c:otherwise>Мобильный телефон</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="tds">
                                        <input type="text" class="phone form-control" name="mobile_phone" required
                                               maxlength="20" placeholder="+38 (000) 00-00-000"
                                               value="<c:out value="${contacts.mobilePhone}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <c:choose>
                                            <c:when test="${not empty contacts.landlinesPhone}">
                                                <a href="tel:<c:out value="${contacts.landlinesPhone}"/>"
                                                   title="Позвонить &quot;<c:out value="${user.name}"/>&quot; на телефон">
                                                    Стационарный телефон
                                                </a>
                                            </c:when>
                                            <c:otherwise>Стационарный телефон</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="tds">
                                        <input type="text" class="phone form-control" name="landlines_phone"
                                               maxlength="20" placeholder="+38 (000) 00-00-000"
                                               value="<c:out value="${contacts.landlinesPhone}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <c:choose>
                                            <c:when test="${not empty contacts.fax}">
                                                <a href="tel:<c:out value="${contacts.fax}"/>"
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
                                               value="<c:out value="${contacts.fax}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <span class="red">*</span>
                                        <c:choose>
                                            <c:when test="${not empty contacts.email}">
                                                <a href="mailto:<c:out value="${contacts.email}"/>" target="_blank"
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
                                               value="<c:out value="${contacts.email}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <c:choose>
                                            <c:when test="${not empty contacts.vkontakte}">
                                                <a href="<c:out value="${contacts.vkontakte}"/>"
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
                                               maxlength="200" value="<c:out value="${contacts.vkontakte}"/>"
                                               placeholder="Ссылка на групу или профиль в социальной сети Vkontakte">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <c:choose>
                                            <c:when test="${not empty contacts.facebook}">
                                                <a href="<c:out value="${contacts.facebook}"/>"
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
                                               value="<c:out value="${contacts.facebook}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <c:choose>
                                            <c:when test="${not empty contacts.twitter}">
                                                <a href="<c:out value="${contacts.twitter}"/>"
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
                                               value="<c:out value="${contacts.twitter}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <c:choose>
                                            <c:when test="${not empty contacts.skype}">
                                                <a href="skype:<c:out value="${contacts.skype}"/>?call"
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
                                               value="<c:out value="${contacts.skype}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Фото профиля</td>
                                    <td class="tds">
                                        <c:choose>
                                            <c:when test="${not empty user.photo.url}">
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
                                            Отображение&nbsp;
                                            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
                                        </label>
                                    </td>
                                    <td class="tds">
                                        <label title="Отображать пользователя">
                                            <input type="radio" name="is_valid" value="true" required
                                                   <c:if test="${user.validated}">checked</c:if>/>
                                            &nbsp;Отображать
                                        </label>&nbsp;&nbsp;
                                        <label title="Не отображать пользователя">
                                            <input type="radio" name="is_valid" value="false" required
                                                   <c:if test="${!user.validated}">checked</c:if>/>
                                            &nbsp;Не отображать
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <label title="Включить или отключить автоматическую рассылку писем от пользователей.">
                                            Рассылка&nbsp;
                                            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
                                        </label>
                                    </td>
                                    <td class="tds">
                                        <label title="Пользователю будут приходить письма от клиентов.">
                                            <input type="radio" name="is_mailing" value="true" required
                                                   <c:if test="${user.mailing}">checked</c:if>/>
                                            &nbsp;Включить
                                        </label>&nbsp;&nbsp;
                                        <label title="Не беспокоить пользователя.">
                                            <input type="radio" name="is_mailing" value="false" required
                                                   <c:if test="${!user.mailing}">checked</c:if>/>
                                            &nbsp;Отключить
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <label title="Если пользователь включен, он сможет авторизироваться на сайте, иначе доступ для него будет запрещен.">
                                            Активность&nbsp;
                                            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
                                        </label>
                                    </td>
                                    <td class="tds">
                                        <label title="Разблокировать пользователя">
                                            <input type="radio" name="is_locked" value="false" required
                                                   <c:if test="${!user.locked}">checked</c:if>/>
                                            &nbsp;Разблокировать
                                        </label>&nbsp;&nbsp;
                                        <label title="Заблокировать пользователя">
                                            <input type="radio" name="is_locked" value="true" required
                                                   <c:if test="${user.locked}">checked</c:if>/>
                                            &nbsp;Заблокировать
                                        </label>
                                    </td>
                                </tr>
                            </table>
                            <input type="hidden" name="url" value="<c:out value="${user.url}"/>">
                            <div style="margin: 10px">
                                <button type="submit" class="btn btn-default" title="Сохранить изменения">
                                    <span class="glyphicon glyphicon-save" aria-hidden="true"></span>
                                    &nbsp;Сохранить
                                </button>
                                &nbsp;&nbsp;
                                <button type="reset" class="btn btn-default" title="Сбросить изменения">
                                    <span class="glyphicon glyphicon-retweet yellow" aria-hidden="true"></span>
                                    &nbsp;Сброс
                                </button>
                            </div>
                        </form>
                        <div align="left" class="little">
                            <p>
                                <span class="red">*</span>&nbsp;
                                Поля обязательные для заполнения.
                                Хорошим тоном является заполнения всех полей объекта.
                            </p>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
        <%-- Footer --%>
    <jsp:include page="/WEB-INF/views/home/footer.jsp"/>
        <%-- Scripts --%>
    <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/lightgallery.min.js"/>"></script>
    <script src="<c:url value="/resources/js/mask.min.js"/>" async></script>
    <script src="<c:url value="/resources/js/easing.min.js"/>" async></script>
    <script src="<c:url value="/resources/js/totop.min.js"/>" async></script>
    </body>
    </html>
</compress:html>
