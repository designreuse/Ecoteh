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
        <title>Редактирование &quot;<c:out value="${company.title}"/>&quot; | <c:out
                value="${main_company.title}"/></title>
        <meta name="title"
              content="Редактирование &quot;<c:out value="${company.title}"/>&quot; | <c:out value="${main_company.title}"/>">
        <meta name="robots" content="noindex,nofollow">
        <meta name="description"
              content="Форма для редактирования информации о компании &quot;<c:out value="${company.title}"/>&quot;">
        <meta name="keywords" content="Редактирование компании, <c:out value="${company.keywords}"/>"/>
        <c:if test="${main_company.favicon ne null}">
            <link rel="shortcut icon" href="<c:url value="/resources/${main_company.favicon.url}"/>"
                  type="image/x-icon">
            <link rel="icon" href="<c:url value="/resources/${main_company.favicon.url}"/>" type="image/x-icon">
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
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
        <div class="container">
            <div class="row">
                <div class="box">
                    <p class="path">
                        <a href="<c:url value="/admin/"/>" title="Перейти на главную странцу">Главная</a>
                        → <a href="<c:url value="/admin/menu"/>" title="Меню администратора">Меню</a>
                        <c:if test="${company ne main_company}">
                            → <a href="<c:url value="/admin/company/all"/>">Все партнеры</a>
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
                        Редактировать&nbsp;&quot;<a href="<c:url value="/admin/company/${com}"/>">
                        <c:out value="${company.title}"/></a>&quot;
                    </h3>
                    <hr>
                    <div class="text-center">
                        <c:choose>
                            <c:when test="${main}"><c:set var="action" value="/main"/></c:when>
                            <c:otherwise><c:set var="action" value=""/></c:otherwise>
                        </c:choose>
                        <form action="<c:url value="/admin/company/update/${action}"/>" method="post"
                              enctype="multipart/form-data">
                            <table align="center" class="table-size">
                                <tr>
                                    <th class="ths"><span class="red">*</span>&nbsp;Название</th>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="title" minlength="2"
                                               maxlength="100" placeholder="Название компании" required
                                               value="<c:out value="${company.title}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">Домен</th>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="domain" minlength="5"
                                               maxlength="200" placeholder="Адрес сайта компании в интернете"
                                               value="<c:out value="${company.domain}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">Слоган</th>
                                    <td class="tds">
                                    <textarea class="form-control textarea" name="tagline" maxlength="200" title=""
                                              placeholder="Короткий и броский рекламный призыв или девиз."
                                              rows="3"><c:out value="${company.tagline}"/></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths"><span class="red">*</span>&nbsp;Описание</th>
                                    <td class="tds">
                                    <textarea class="form-control textarea" name="description" title=""
                                              placeholder="Краткое описание компании" required
                                              rows="6"><c:out value="${company.description}"/></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">Преимущества</th>
                                    <td class="tds">
                                    <textarea class="form-control textarea" name="advantages" title=""
                                              placeholder="Преимущества над другими компаниями"
                                              rows="6"><c:out value="${company.advantages}"/></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths"><span class="red">*</span>&nbsp;Информация</th>
                                    <td class="tds">
                                    <textarea class="form-control textarea" name="information"
                                              placeholder="Основная информация о компании" title=""
                                              rows="10"><c:out value="${company.information}"/></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths"><span class="red">*</span>&nbsp;Ключевые слова</th>
                                    <td class="tds">
                                    <textarea class="form-control textarea" name="keywords" required title=""
                                              placeholder="Ключевые слова, которые описывают компанию, необходимы для ботов-поисковиков, на страницах сайта не отображаются."
                                              rows="3"><c:out value="${company.keywords}"/></textarea>
                                    </td>
                                </tr>
                                <c:if test="${main}">
                                    <tr>
                                        <th class="ths"><span class="red">*</span>&nbsp;Время работы</th>
                                        <td class="tds">
                                            <input type="text" class="time form-control" required
                                                   name="time_from" maxlength="10" placeholder="Начало рабочего дня"
                                                   value="<c:out value="${company.workTimeFrom}"/>">
                                            -
                                            <input type="text" class="time form-control" name="time_to"
                                                   maxlength="10" placeholder="Конец рабочего дня" required
                                                   value="<c:out value="${company.workTimeTo}"/>">
                                        </td>
                                    </tr>
                                </c:if>
                                <tr>
                                    <th class="ths">Мобильный телефон</th>
                                    <td class="tds">
                                        <input type="text" class="phone form-control" name="mobile_phone"
                                               maxlength="20" placeholder="+38 (000) 00-00-000"
                                               value="<c:out value="${company.mobilePhone}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">Cтационарный телефон</th>
                                    <td class="tds">
                                        <input type="text" class="phone form-control" name="landline_phone"
                                               maxlength="20" placeholder="+38 (000) 00-00-000"
                                               value="<c:out value="${company.landlinePhone}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">Факс</th>
                                    <td class="tds">
                                        <input type="text" class="phone form-control" name="fax" maxlength="20"
                                               placeholder="+38 (000) 00-00-000"
                                               value="<c:out value="${company.fax}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">Электронная почта</th>
                                    <td class="tds">
                                        <input type="email" class="form-control" name="email" maxlength="100"
                                               minlength="3" placeholder="name@mail.com"
                                               value="<c:out value="${company.email}"/>">
                                    </td>
                                </tr>
                                <c:if test="${main}">
                                    <tr>
                                        <th class="ths">
                                            <label title="Эта почта будет привязана к форме обратной связи.">
                                                <b>Электронная почта (бот)&nbsp;
                                                    <span class="glyphicon glyphicon-info-sign"
                                                          aria-hidden="true"></span></b>
                                            </label>
                                        </th>
                                        <td class="tds">
                                            <input type="email" class="form-control" name="sender_email"
                                                   minlength="3" maxlength="100" placeholder="name@mail.com"
                                                   value="<c:out value="${company.senderEmail}"/>">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th class="ths">Пароль электронной почты (бот)</th>
                                        <td class="tds">
                                            <input type="text" class="form-control" name="sender_pass"
                                                   minlength="3" maxlength="100" placeholder="password"
                                                   value="<c:out value="${company.senderPass}"/>">
                                        </td>
                                    </tr>
                                </c:if>
                                <tr>
                                    <th class="ths">
                                        <a href="https://vk.com" target="_blank"
                                           title="Социальная сеть Vkontakte">Vkontakte</a>
                                    </th>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="vkontakte" minlength="5"
                                               maxlength="200" value="<c:out value="${company.vkontakte}"/>"
                                               placeholder="Ссылка на групу или профиль в социальной сети Vkontakte">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">
                                        <a href="https://www.facebook.com" target="_blank"
                                           title="Социальная сеть Facebook">Facebook</a>
                                    </th>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="facebook" minlength="5"
                                               maxlength="200" value="<c:out value="${company.facebook}"/>"
                                               placeholder="Ссылка на групу или профиль в социальной сети Facebook">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">
                                        <a href="https://twitter.com" target="_blank"
                                           title="Социальная сеть Twitter">Twitter</a>
                                    </th>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="twitter" minlength="5"
                                               maxlength="200" placeholder="Ссылка в социальной сети Twitter"
                                               value="<c:out value="${company.twitter}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">Skype</th>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="skype" minlength="5"
                                               maxlength="100" placeholder="Имя в месенджере Skype"
                                               value="<c:out value="${company.skype}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths"><span class="red">*</span>&nbsp;Адрес офиса</th>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="address" maxlength="300"
                                               placeholder="Адрес главного офиса компании." required
                                               value="<c:out value="${company.address}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">
                                        <a href="https://www.google.com.ua/maps/"
                                           title="Перейти к Google Maps">Google Maps</a>&nbsp;
                                        <a href="<c:url value="/resources/img/static/google_maps_1.jpg"/>"
                                           title="Офис на карте Google Maps. Где это взять?" rel="lightgallery[maps]">
                                            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
                                        </a>
                                        <a href="<c:url value="/resources/img/static/google_maps_2.jpg"/>"
                                           rel="lightgallery[maps]" title="Офис на карте Google Maps."></a>
                                        <a href="<c:url value="/resources/img/static/google_maps_3.jpg"/>"
                                           rel="lightgallery[maps]" title="Офис на карте Google Maps."></a>
                                        <a href="<c:url value="/resources/img/static/google_maps_4.jpg"/>"
                                           rel="lightgallery[maps]" title="Офис на карте Google Maps."></a>
                                    </th>
                                    <td class="tds">
                                    <textarea class="form-control textarea" name="google_maps" title=""
                                              placeholder="URL миникарты Google Maps. Желательно чтобы на карте отображался офис, адрес которого указан выше."
                                              rows="5"><c:out value="${company.googleMaps}"/></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">
                                        <a href="<c:url value="/resources/img/static/where_icon.jpg"/>"
                                           rel="lightgallery" title="Логотип, это где?">
                                            Логотип&nbsp;<span class="glyphicon glyphicon-info-sign"
                                                               aria-hidden="true"></span>
                                        </a>
                                    </th>
                                    <td class="tds">
                                        <c:choose>
                                            <c:when test="${company.logo ne null}">
                                                <a href="<c:url value="/resources/${company.logo.url}"/>"
                                                   rel="lightgallery">
                                                    <img class="img-logo" alt="<c:out value="${company.title}"/>"
                                                         src="<c:url value="/resources/${company.logo.url}"/>">
                                                </a><br><br>
                                                <label title="Добавить новый значок">
                                                    <b><input type="radio" name="logo_action" value="replace" checked
                                                              required/>&nbsp;Заменить</b>
                                                </label>&nbsp;&nbsp;
                                                <label title="Удалить логотип">
                                                    <b><input type="radio" name="logo_action" value="delete"
                                                              required/>&nbsp;Удалить</b>
                                                </label>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="hidden" name="logo_action" value="replace" required/>
                                            </c:otherwise>
                                        </c:choose>
                                        <br>
                                        <input type="file" name="logo_photo" accept="image/*" class="form-control"><br>
                                    </td>
                                </tr>
                                <c:choose>
                                    <c:when test="${main}">
                                        <tr>
                                            <th class="ths">
                                                <a href="<c:url value="/resources/img/static/where_icon.jpg"/>"
                                                   rel="lightgallery" title="Значок, это где?">
                                                    Значок&nbsp;<span class="glyphicon glyphicon-info-sign"
                                                                      aria-hidden="true"></span>
                                                </a>
                                            </th>
                                            <td class="tds">
                                                <c:choose>
                                                    <c:when test="${company.favicon ne null}">
                                                        <a href="<c:url value="/resources/${company.favicon.url}"/>"
                                                           rel="lightgallery">
                                                            <img class="img-favicon"
                                                                 src="<c:url value="/resources/${company.favicon.url}"/>">
                                                        </a><br><br>
                                                        <label title="Добавить новый значок">
                                                            <b><input type="radio" name="favicon_action" value="replace"
                                                                      checked required/>&nbsp;Заменить</b>
                                                        </label>&nbsp;&nbsp;
                                                        <label title="Удалить значок">
                                                            <b><input type="radio" name="favicon_action" value="delete"
                                                                      required/>&nbsp;Удалить</b>
                                                        </label><br>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="hidden" name="favicon_action" value="replace"
                                                               checked required/>
                                                    </c:otherwise>
                                                </c:choose>
                                                <input type="file" name="favicon_photo" accept="image/*"
                                                       class="form-control"><br>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th class="ths">
                                                <a href="<c:url value="/resources/img/static/where_slides.jpg"/>"
                                                   rel="lightgallery" title="Слайды, это где?">
                                                    Слайды&nbsp;<span class="glyphicon glyphicon-info-sign"
                                                                      aria-hidden="true"></span>
                                                </a>
                                            </th>
                                            <td class="tds">
                                                <c:choose>
                                                    <c:when test="${fn:length(slides) gt 0}">
                                                        <c:forEach items="${slides}" var="slide">
                                                            <c:if test="${slide ne null}">
                                                                <a href="<c:url value="/resources/${slide.url}"/>"
                                                                   rel="lightgallery[slides]"
                                                                   title="<c:out value="${slide.title}"/>">
                                                                    <img class="img-preview"
                                                                         src="<c:url value="/resources/${slide.url}"/>"
                                                                         alt="<c:out value="${company.title}"/>"/>
                                                                </a>
                                                            </c:if>
                                                        </c:forEach>
                                                        <br><br>
                                                        <label title="Заменить существующие слайды">
                                                            <b><input type="radio" name="slides_action" value="replace"
                                                                      checked required/>&nbsp;Заменить</b>
                                                        </label>&nbsp;&nbsp;
                                                        <label title="Добавить новые сайды">
                                                            <b><input type="radio" name="slides_action" value="add"
                                                                      required/>&nbsp;Добавить</b>
                                                        </label>&nbsp;&nbsp;
                                                        <label title="Удалить все сайды">
                                                            <b><input type="radio" name="slides_action" value="delete"
                                                                      required/>&nbsp;Удалить</b>
                                                        </label><br>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="hidden" name="slides_action" value="replace"
                                                               checked required/>
                                                    </c:otherwise>
                                                </c:choose>
                                                <input type="file" name="slides[]" accept="image/*" multiple
                                                       class="form-control"><br>
                                            </td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <th class="ths">
                                                <label title="Если компания позначеная для отображения, она будет доступна любому пользователю, иначе ее сможет увидеть только адмиистратор.">
                                                    <b>Отображение&nbsp;<span aria-hidden="true"
                                                                              class="glyphicon glyphicon-info-sign"></span></b>
                                                </label>
                                            </th>
                                            <td class="tds">
                                                <label title="Компанию смогут увидеть все пользователей">
                                                    <b><input type="radio" name="is_valid" value="true"
                                                              <c:if test="${company.validated}">checked</c:if>
                                                              required/>&nbsp;Отображать</b>
                                                </label>&nbsp;&nbsp;
                                                <label title="Компанию смогут увидеть только администраторы">
                                                    <b><input type="radio" name="is_valid" value="false"
                                                              <c:if test="${!company.validated}">checked</c:if>
                                                              required/>&nbsp;Не отображать</b>
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
    <script>CKEDITOR.replace("information");</script>
    <script>CKEDITOR.replace("advantages");</script>
    <script src="<c:url value="/resources/js/lightgallery.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/mask.min.js"/>" type="text/javascript" async></script>
    <script src="<c:url value="/resources/js/easing.min.js"/>" type="text/javascript" async></script>
    <script src="<c:url value="/resources/js/totop.min.js"/>" type="text/javascript" async></script>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
