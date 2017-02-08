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
        <title>Редактирование &quot;<c:out value="${company.title}"/>&quot; | <c:out
                value="${main_company.title}"/></title>
        <meta name="title"
              content="Редактирование &quot;<c:out value="${company.title}"/>&quot; | <c:out value="${main_company.title}"/>">
        <meta name="robots" content="noindex,nofollow">
        <meta name="description"
              content="Форма для редактирования информации о компании &quot;<c:out value="${company.title}"/>&quot;">
        <meta name="keywords" content="Редактирование компании, <c:out value="${company.keywords}"/>"/>
        <c:if test="${main_company.faviconUrl ne null}">
            <link rel="shortcut icon" href="<c:url value="${main_company.faviconUrl}"/>" type="image/x-icon">
            <link rel="icon" href="<c:url value="${main_company.faviconUrl}"/>" type="image/x-icon">
        </c:if>
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
                        <c:if test="${company ne main_company}">
                            → <a href="<c:url value="/company/all"/>">Все партнеры</a>
                        </c:if>
                        →
                        <a href="#" title="Редактировать&nbsp;&quot;<c:out value="${company.title}&quot;"/>">
                            Редактирование компании
                        </a>
                    </p>
                    <hr>
                    <h3 class="text-center">
                        <c:choose>
                            <c:when test="${main}"><c:set var="com" value="main"/></c:when>
                            <c:otherwise><c:set var="com" value="${company.url}"/></c:otherwise>
                        </c:choose>
                        Редактировать&nbsp;&quot;<a href="<c:url value="/company/${com}"/>"><c:out
                            value="${company.title}"/></a>&quot;
                    </h3>
                    <hr>
                    <div class="text-center">
                        <c:choose>
                            <c:when test="${main}"><c:set var="action" value="/main"/></c:when>
                            <c:otherwise><c:set var="action" value=""/></c:otherwise>
                        </c:choose>
                        <form action="<c:url value="/admin/company/update/${action}"/>" method="post">
                            <table align="center" class="table-size">
                                <tr>
                                    <td class="ths"><span class="red">*</span>&nbsp;Название</td>
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
                                    <td class="ths">Описание</td>
                                    <td class="tds">
                                    <textarea class="form-control textarea" name="text" title=""
                                              placeholder="Краткое описание компании"
                                              rows="6"><c:out value="${company.description}"/></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Информация</td>
                                    <td class="tds">
                                    <textarea class="form-control textarea" name="information"
                                              placeholder="Основная информация о компании" title=""
                                              rows="10"><c:out value="${company.information}"/></textarea>
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
                                <tr>
                                    <td class="ths">Мобильный телефон</td>
                                    <td class="tds">
                                        <input type="text" class="phone form-control" name="mobile_phone"
                                               maxlength="20" placeholder="+38 (000) 00-00-000"
                                               value="<c:out value="${company.mobilePhone}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Стационарный телефон</td>
                                    <td class="tds">
                                        <input type="text" class="phone form-control" name="landline_phone"
                                               maxlength="20" placeholder="+38 (000) 00-00-000"
                                               value="<c:out value="${company.landlinePhone}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Факс</td>
                                    <td class="tds">
                                        <input type="text" class="phone form-control" name="fax" maxlength="20"
                                               placeholder="+38 (000) 00-00-000"
                                               value="<c:out value="${company.fax}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Электронная почта</td>
                                    <td class="tds">
                                        <input type="email" class="form-control" name="email" maxlength="100"
                                               minlength="3" placeholder="name@mail.com"
                                               value="<c:out value="${company.email}"/>">
                                    </td>
                                </tr>
                                <c:if test="${main}">
                                    <tr>
                                        <td class="ths">
                                            <label title="Эта почта будет привязана к форме обратной связи.">
                                                Электронная почта (бот)&nbsp;<span class="glyphicon glyphicon-info-sign"
                                                                                   aria-hidden="true"></span>
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
                                            <c:when test="${company.vkontakte ne null}">
                                                <a href="<c:out value="${company.vkontakte}"/>"
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
                                               maxlength="200" value="<c:out value="${company.vkontakte}"/>"
                                               placeholder="Ссылка на групу или профиль в социальной сети Vkontakte">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <c:choose>
                                            <c:when test="${company.facebook ne null}">
                                                <a href="<c:out value="${company.facebook}"/>"
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
                                               maxlength="200" value="<c:out value="${company.facebook}"/>"
                                               placeholder="Ссылка на групу или профиль в социальной сети Facebook">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <c:choose>
                                            <c:when test="${company.twitter ne null}">
                                                <a href="<c:out value="${company.twitter}"/>"
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
                                               value="<c:out value="${company.twitter}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Skype</td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="skype" minlength="5"
                                               maxlength="100" placeholder="Имя в месенджере Skype"
                                               value="<c:out value="${company.skype}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Адрес офиса</td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="address" maxlength="300"
                                               placeholder="Адрес главного офиса компании."
                                               value="<c:out value="${company.address}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <a href="<c:url value="/resources/img/static/google_maps_1.jpg"/>"
                                           rel="lightgallery[maps]" title="Офис на карте Google Maps. Где это взять?">
                                            Google Maps&nbsp;<span class="glyphicon glyphicon-info-sign"
                                                                   aria-hidden="true"></span>
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
                                              rows="5"><c:out value="${company.googleMaps}"/></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <a href="<c:url value="/resources/img/static/where_icon.jpg"/>"
                                           rel="lightgallery" title="Логотип, это где?">
                                            Логотип&nbsp;<span class="glyphicon glyphicon-info-sign"
                                                               aria-hidden="true"></span>
                                        </a>
                                    </td>
                                    <td class="tds">
                                        <c:if test="${company.logoUrl ne null}">
                                            <a href="<c:url value="/${company.logoUrl}"/>"
                                               title="<c:out value="${company.title}"/>" rel="lightgallery">
                                                <img src="<c:url value="${company.logoUrl}"/>"
                                                     class="main-logo" alt="" title="Увеличить"
                                                     onerror="this.src='<c:url
                                                             value="/resources/img/static/default_file.gif"/>'">
                                            </a><br><br>
                                        </c:if>
                                        <input type="text" class="form-control" name="logo" minlength="2"
                                               maxlength="100" placeholder="Ссылка на логотип компании"
                                               value="<c:out value="${company.logoUrl}"/>">
                                    </td>
                                </tr>
                                <c:choose>
                                    <c:when test="${main}">
                                        <tr>
                                            <td class="ths">
                                                <a href="<c:url value="/resources/img/static/where_icon.jpg"/>"
                                                   rel="lightgallery" title="Значок, это где?">
                                                    Значок&nbsp;<span class="glyphicon glyphicon-info-sign"
                                                                      aria-hidden="true"></span>
                                                </a>
                                            </td>
                                            <td class="tds">
                                                <a href="<c:url value="/${company.faviconUrl}"/>"
                                                   title="<c:out value="${company.title}"/>" rel="lightgallery">
                                                    <img src="<c:url value="${company.faviconUrl}"/>"
                                                         class="main-logo" alt="" title="Увеличить"
                                                         onerror="this.src='<c:url
                                                                 value="/resources/img/static/default_file.gif"/>'">
                                                </a><br><br>
                                                <input type="text" class="form-control" name="favicon" minlength="2"
                                                       maxlength="100" placeholder="Ссылка на фавикон сайта"
                                                       value="<c:out value="${company.faviconUrl}"/>">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="ths">
                                                <a href="<c:url value="/resources/img/static/where_slides.jpg"/>"
                                                   rel="lightgallery" title="Слайды, это где?">
                                                    Слайды&nbsp;<span class="glyphicon glyphicon-info-sign"
                                                                      aria-hidden="true"></span>
                                                </a>
                                            </td>
                                            <td class="tds">
                                                <c:if test="${fn:length(company.slidesList) gt 0}">
                                                    <c:forEach items="${company.slidesList}" var="slide">
                                                        <c:if test="${slide ne null}">
                                                            <a href="<c:url value="${slide}"/>"
                                                               rel="lightgallery[slides]"
                                                               title="<c:out value="${company.title}"/>">
                                                                <img src="<c:url value="${slide}"/>" class="main-logo"
                                                                     onerror="this.src='<c:url
                                                                             value="/resources/img/static/default_file.gif"/>'"
                                                                     alt="<c:out value="${company.title}"/>"/>
                                                            </a>&nbsp;
                                                        </c:if>
                                                    </c:forEach><br><br>
                                                </c:if>
                                                <textarea class="form-control textarea" name="slides" title=""
                                                          placeholder="URL слайдов, которые будут отображатся на главной страницы сайта. Вводите значение через запятую (,)ю"
                                                          rows="5"><c:out value="${company.slides}"/></textarea>
                                            </td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td class="ths">
                                                <label title="Если компания позначеная для отображения, она будет доступна любому пользователю, иначе ее сможет увидеть только адмиистратор.">
                                                    Отображение&nbsp;<span class="glyphicon glyphicon-info-sign"
                                                                           aria-hidden="true"></span>
                                                </label>
                                            </td>
                                            <td class="tds">
                                                <label title="Компанию смогут увидеть все пользователей">
                                                    <input type="radio" name="is_valid" value="true"
                                                           <c:if test="${company.validated}">checked</c:if>
                                                           required/>&nbsp;Отображать
                                                </label>&nbsp;&nbsp;
                                                <label title="Компанию смогут увидеть только администраторы">
                                                    <input type="radio" name="is_valid" value="false"
                                                           <c:if test="${!company.validated}">checked</c:if>
                                                           required/>&nbsp;Не отображать
                                                </label>
                                            </td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                            </table>
                            <input type="hidden" name="url" value="<c:out value="${company.url}"/>">
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
    <script src="<c:url value="/resources/ckeditor/ckeditor.js"/>" type="text/javascript"></script>
    <script>CKEDITOR.replace("text");</script>
    <script>CKEDITOR.replace("information");</script>
    <script src="<c:url value="/resources/js/lightgallery.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/mask.min.js"/>" type="text/javascript" async></script>
    <script src="<c:url value="/resources/js/easing.min.js"/>" type="text/javascript" async></script>
    <script src="<c:url value="/resources/js/totop.min.js"/>" type="text/javascript" async></script>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
