<%--
Page for editing a incoming company.

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
        <title>
            Редактирование <c:out value="&quot;${company.title}&quot; | ${main_company.title}"/>
        </title>
        <meta name="title"
              content="Редактирование <c:out value="&quot;${company.title}&quot; | ${main_company.title}"/>">
        <link rel="shortcut icon" href="<c:url value="${favicon.url}"/>" type="image/x-icon">
        <link rel="icon" href="<c:url value="${favicon.url}"/>" type="image/x-icon">
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
                        <c:choose>
                            <c:when test="${company ne main_company}">
                                → <a href="<c:url value="/company/all"/>">Все&nbsp;партнеры</a>
                                →
                                <a href="<c:url value="/admin/company/edit/${company.url}"/>">
                                    Редактирование компании&nbsp;&quot;<c:out value="${company.title}"/>&quot;
                                </a>
                            </c:when>
                            <c:otherwise>
                                →
                                <a href="<c:url value="/admin/company/edit/main"/>">
                                    Редактирование компании&nbsp;&quot;<c:out value="${company.title}"/>&quot;
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <hr>
                    <h3 class="text-center">
                        <c:choose>
                            <c:when test="${main}">
                                <c:set var="com" value="main"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="com" value="${company.url}"/>
                            </c:otherwise>
                        </c:choose>
                        Редактировать&nbsp;
                        &quot;<a href="<c:url value="/company/${com}"/>"
                                 title=""><c:out value="${company.title}"/></a>&quot;
                    </h3>
                    <hr>
                    <div class="text-center">
                        <c:choose>
                            <c:when test="${main}">
                                <c:set var="action" value="/main"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="action" value=""/>
                            </c:otherwise>
                        </c:choose>
                            <%-- Form for editing a incoming company --%>
                        <form action="<c:url value="/admin/company/update/${action}"/>" method="post"
                              enctype="multipart/form-data">
                            <table align="center" class="table-size">
                                <tr>
                                    <td class="ths">
                                        <span class="red">*</span>
                                        &nbsp;Название
                                    </td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="title" minlength="2"
                                               maxlength="100" placeholder="Название компании" required
                                               value="<c:out value="${company.title}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Домен</td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="domain" minlength="5"
                                               maxlength="200" placeholder="Адрес сайта компании в интернете"
                                               value="<c:out value="${company.domain}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Слоган</td>
                                    <td class="tds">
                                    <textarea class="form-control textarea" name="tagline" maxlength="200" title=""
                                              placeholder="Короткий и броский рекламный призыв или девиз."
                                              rows="3"><c:out value="${company.tagline}"/></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <span class="red">*</span>&nbsp;Основной текст
                                    </td>
                                    <td class="tds">
                                    <textarea class="form-control textarea" name="text" rows="10" required
                                              placeholder="Основная информация статьи."
                                              title=""><c:out value="${company.text}"/></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Описание</td>
                                    <td class="tds">
                                    <textarea class="form-control textarea" name="description" title=""
                                              placeholder="Краткое описание компании"
                                              rows="6"><c:out value="${company.description}"/></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Ключевые слова</td>
                                    <td class="tds">
                                    <textarea class="form-control textarea" name="keywords" title=""
                                              placeholder="Ключевые слова, которые описывают компанию, необходимы для ботов-поисковиков, на страницах сайта не отображаются."
                                              rows="7"><c:out value="${company.keywords}"/></textarea>
                                    </td>
                                </tr>
                                <c:if test="${main}">
                                    <tr>
                                        <td class="ths">Время работы</td>
                                        <td class="tds">
                                            <input type="text" class="time form-control"
                                                   name="time_from" maxlength="10" placeholder="Начало рабочего дня"
                                                   value="<c:out value="${company.workTimeFrom}"/>">
                                            -
                                            <input type="text" class="time form-control" name="time_to"
                                                   maxlength="10" placeholder="Конец рабочего дня"
                                                   value="<c:out value="${company.workTimeTo}"/>">
                                        </td>
                                    </tr>
                                </c:if>
                                <c:set var="contacts" value="${company.contacts}"/>
                                <tr>
                                    <td class="ths">Мобильный телефон</td>
                                    <td class="tds">
                                        <input type="text" class="phone form-control" name="mobile_phone"
                                               maxlength="20" placeholder="+38 (000) 00-00-000"
                                               value="<c:out value="${contacts.mobilePhone}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Стационарный телефон</td>
                                    <td class="tds">
                                        <input type="text" class="phone form-control" name="landlines_phone"
                                               maxlength="20" placeholder="+38 (000) 00-00-000"
                                               value="<c:out value="${contacts.landlinesPhone}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Факс</td>
                                    <td class="tds">
                                        <input type="text" class="phone form-control" name="fax" maxlength="20"
                                               placeholder="+38 (000) 00-00-000"
                                               value="<c:out value="${contacts.fax}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Электронная почта</td>
                                    <td class="tds">
                                        <input type="email" class="form-control" name="email" maxlength="100"
                                               minlength="3" placeholder="name@mail.com"
                                               value="<c:out value="${contacts.email}"/>">
                                    </td>
                                </tr>
                                <c:if test="${main}">
                                    <tr>
                                        <td class="ths">
                                            <label title="Эта почта будет привязана к форме обратной связи.">
                                                Электронная почта (бот)&nbsp;
                                                <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
                                            </label>
                                        </td>
                                        <td class="tds">
                                            <input type="email" class="form-control" name="sender_email"
                                                   minlength="3" maxlength="100" placeholder="name@mail.com"
                                                   value="<c:out value="${company.senderEmail}"/>">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="ths">Пароль электронной почты (бот)</td>
                                        <td class="tds">
                                            <input type="text" class="form-control" name="sender_pass"
                                                   minlength="3" maxlength="100" placeholder="password"
                                                   value="<c:out value="${company.senderPass}"/>">
                                        </td>
                                    </tr>
                                </c:if>
                                <tr>
                                    <td class="ths">
                                        <c:choose>
                                            <c:when test="${not empty contacts.vkontakte}">
                                                <a href="<c:out value="${contacts.vkontakte}"/>"
                                                   title="Профиль &quot;<c:out value="${company.title}"/>&quot; в ВКонтакте"
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
                                                   title="Профиль &quot;<c:out value="${company.title}"/>&quot; в Facebook"
                                                   target="_blank">Facebook</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="https://www.facebook.com" target="_blank"
                                                   title="Социальная сеть Facebook">Facebook</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="facebook" minlength="5"
                                               maxlength="200" value="<c:out value="${contacts.facebook}"/>"
                                               placeholder="Ссылка на групу или профиль в социальной сети Facebook">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <c:choose>
                                            <c:when test="${not empty contacts.twitter}">
                                                <a href="<c:out value="${contacts.twitter}"/>"
                                                   title="Профиль &quot;<c:out value="${company.title}"/>&quot; в Twitter"
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
                                    <td class="ths">Skype</td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="skype" minlength="5"
                                               maxlength="100" placeholder="Имя в месенджере Skype"
                                               value="<c:out value="${contacts.skype}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Адрес офиса</td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="post_address" maxlength="300"
                                               placeholder="Адрес главного офиса компании."
                                               value="<c:out value="${company.address.postAddress}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <a href="<c:url value="/resources/img/static/google_maps_1.jpg"/>"
                                           rel="lightgallery[maps]" title="Офис на карте Google Maps. Где это взять?">
                                            Google Maps&nbsp;
                                            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
                                        </a>&nbsp;
                                        <a href="<c:url value="/resources/img/static/google_maps_2.jpg"/>"
                                           rel="lightgallery[maps]" title="Офис на карте Google Maps."></a>
                                        <a href="<c:url value="/resources/img/static/google_maps_3.jpg"/>"
                                           rel="lightgallery[maps]" title="Офис на карте Google Maps."></a>
                                        <a href="<c:url value="/resources/img/static/google_maps_4.jpg"/>"
                                           rel="lightgallery[maps]" title="Офис на карте Google Maps."></a>
                                        <a href="https://www.google.com.ua/maps/" target="_blank"
                                           title="Перейти к Google Maps">
                                            <span class="glyphicon glyphicon-link" aria-hidden="true"></span>
                                        </a>
                                    </td>
                                    <td class="tds">
                                        <textarea class="form-control textarea" name="google_maps" title=""
                                                  placeholder="URL миникарты Google Maps. Желательно чтобы на карте отображался офис, адрес которого указан выше."
                                                  rows="5"><c:out value="${company.address.googleMaps}"/></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Логотип</td>
                                    <td class="tds">
                                        <c:choose>
                                            <c:when test="${not empty company.logo.url}">
                                                <a href="<c:url value="${company.logo.url}"/>" rel="lightgallery"
                                                   title="<c:out value="${company.title}"/>">
                                                    <img class="img-responsive img-in-list"
                                                         alt="<c:out value="${company.title}"/>"
                                                         src="<c:url value="${company.logo.url}"/>"
                                                         onerror="this.src='<c:url
                                                                 value="/resources/img/static/default_file.gif"/>'">
                                                </a>
                                            </c:when>
                                            <c:otherwise>
                                                <img class="img-responsive img-in-list"
                                                     alt="<c:out value="${company.title}"/>"
                                                     src="<c:url value="/resources/img/static/default_file.gif"/>">
                                            </c:otherwise>
                                        </c:choose><br>
                                        <input type="file" name="logo" class="form-control">
                                    </td>
                                </tr>
                                <c:if test="${main eq false}">
                                    <tr>
                                        <td class="ths">
                                            <label title="Если компания позначеная для отображения, она будет доступна любому пользователю, иначе ее сможет увидеть только адмиистратор.">
                                                Отображение&nbsp;
                                                <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
                                            </label>
                                        </td>
                                        <td class="tds">
                                            <label title="Компанию смогут увидеть все пользователей">
                                                <input type="radio" name="is_valid" value="true" required
                                                       <c:if test="${company.validated}">checked</c:if>/>
                                                &nbsp;Отображать
                                            </label>&nbsp;&nbsp;
                                            <label title="Компанию смогут увидеть только администраторы">
                                                <input type="radio" name="is_valid" value="false" required
                                                       <c:if test="${!company.validated}">checked</c:if>/>
                                                &nbsp;Не&nbsp;отображать
                                            </label>
                                        </td>
                                    </tr>
                                </c:if>
                            </table>
                            <input type="hidden" name="url" value="<c:out value="${company.url}"/>">
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
    <script src="<c:url value="/resources/ckeditor/ckeditor.js"/>"></script>
    <script>CKEDITOR.replace("text");</script>
    <script>CKEDITOR.replace("description");</script>
    <script src="<c:url value="/resources/js/lightgallery.min.js"/>"></script>
    <script src="<c:url value="/resources/js/mask.min.js"/>" async></script>
    <script src="<c:url value="/resources/js/easing.min.js"/>" async></script>
    <script src="<c:url value="/resources/js/totop.min.js"/>" async></script>
    </body>
    </html>
</compress:html>
