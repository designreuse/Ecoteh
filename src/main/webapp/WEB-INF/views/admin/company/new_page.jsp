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
        <title>Добавление нового партнера | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="Добавление нового партнера | <c:out value="${main_company.title}"/>">
        <meta name="robots" content="noindex,nofollow">
        <meta name="description" content="Форма для добавления новой компании-партнера.">
        <meta name="keywords" content="Партнер, новый партнер, добавление партнера"/>
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
                        → <a href="<c:url value="/admin/company/all"/>">Все партнеры</a>
                        → <a href="#">Новый партнер</a>
                    </p>
                    <hr>
                    <h3 class="text-center" title="Добавление нового партнера">Новый партнер</h3>
                    <hr>
                    <div class="text-center">
                        <form action="<c:url value="/admin/company/add"/>" method="post" enctype="multipart/form-data">
                            <table align="center" class="table-size">
                                <tr>
                                    <td class="ths"><span class="red">*</span>&nbsp;Название</td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="title" minlength="2"
                                               maxlength="100" placeholder="Название компании" required>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Домен</td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="domain" minlength="5"
                                               maxlength="200" placeholder="Адрес сайта компании в интернете">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Слоган</td>
                                    <td class="tds">
                                        <textarea class="form-control textarea" name="tagline" rows="3" maxlength="200"
                                                  title=""
                                                  placeholder="Короткий и броский рекламный призыв или девиз."></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths"><span class="red">*</span>&nbsp;Описание</td>
                                    <td class="tds">
                                        <textarea class="form-control textarea" name="text" rows="6" title=""
                                                  placeholder="Краткое описание компании" required></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <span class="red">*</span>&nbsp;Информация
                                    </td>
                                    <td class="tds">
                                        <textarea class="form-control textarea" name="information" rows="10" title=""
                                                  required
                                                  placeholder="Основная информация о компании"></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <span class="red">*</span>&nbsp;Ключевые слова
                                    </td>
                                    <td class="tds">
                                        <textarea class="form-control textarea" name="keywords" rows="5" title=""
                                                  required
                                                  placeholder="Ключевые слова, которые описывают компанию, необходимы для ботов-поисковиков, на страницах сайта не отображаются."></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Мобильный телефон</td>
                                    <td class="tds">
                                        <input type="text" class="phone form-control" name="mobile_phone"
                                               maxlength="20" placeholder="+38 (000) 00-00-000">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Cтационарный телефон</td>
                                    <td class="tds">
                                        <input type="text" class="phone form-control" name="landline_phone"
                                               maxlength="20" placeholder="+38 (000) 00-00-000">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Факс</td>
                                    <td class="tds">
                                        <input type="text" class="phone form-control" name="fax" maxlength="20"
                                               placeholder="+38 (000) 00-00-000">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Электронная почта</td>
                                    <td class="tds">
                                        <input type="email" class="form-control" name="email" maxlength="100"
                                               placeholder="name@mail.com">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <a href="https://vk.com" target="_blank"
                                           title="Социальная сеть Vkontakte">Vkontakte</a>
                                    </td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="vkontakte"
                                               minlength="5" maxlength="200"
                                               placeholder="Ссылка на групу или профиль в социальной сети Vkontakte">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <a href="https://www.facebook.com" target="_blank"
                                           title="Социальная сеть Facebook">Facebook</a>
                                    </td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="facebook"
                                               minlength="5" maxlength="200"
                                               placeholder="Ссылка на групу или профиль в социальной сети Facebook">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <a href="https://twitter.com" target="_blank" title="Социальная сеть Twitter">Twitter</a>
                                    </td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="twitter" minlength="5"
                                               maxlength="200" placeholder="Ссылка в социальной сети Twitter">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Skype</td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="skype" minlength="5"
                                               maxlength="100" placeholder="Имя в месенджере Skype">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <span class="red">*</span>&nbsp;Адрес офиса
                                    </td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="address" maxlength="300" required
                                               placeholder="Адрес главного офиса компании">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <a href="https://www.google.com.ua/maps/" target="_blank"
                                           title="Перейти к Google Maps">Google Maps</a>&nbsp;
                                        <a href="<c:url value="/resources/img/static/google_maps_1.jpg"/>"
                                           rel="lightgallery[maps]" title="Офис на карте Google Maps. Где это взять?">
                                            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
                                        </a>
                                        <a href="<c:url value="/resources/img/static/google_maps_2.jpg"/>"
                                           rel="lightgallery[maps]" title="Офис на карте Google Maps."></a>
                                        <a href="<c:url value="/resources/img/static/google_maps_3.jpg"/>"
                                           rel="lightgallery[maps]" title="Офис на карте Google Maps."></a>
                                        <a href="<c:url value="/resources/img/static/google_maps_4.jpg"/>"
                                           rel="lightgallery[maps]" title="Офис на карте Google Maps."></a>
                                    </td>
                                    <td class="tds">
                                        <textarea class="form-control textarea" name="google_maps" title=""
                                                  placeholder="URL миникарты Google Maps. Желательно чтобы на карте отображался офис, адрес которого указан выше."
                                                  rows="5"></textarea>
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
                                        <input type="file" name="logo_photo" accept="image/*" class="form-control">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <label title="Если компания позначеная для отображения, она будет доступна любому пользователю, иначе ее сможет увидеть только адмиистратор.">
                                            Отображение&nbsp;<span class="glyphicon glyphicon-info-sign"
                                                      aria-hidden="true"></span>
                                        </label>
                                    </td>
                                    <td class="tds">
                                        <label title="Компанию смогут увидеть все пользователей">
                                            <input type="radio" name="is_valid" value="true" checked
                                                      required/>&nbsp;Отображать
                                        </label>&nbsp;&nbsp;
                                        <label title="Компанию смогут увидеть только администраторы">
                                            <input type="radio" name="is_valid" value="false"
                                                      required/>&nbsp;Не отображать
                                        </label>
                                    </td>
                                </tr>
                            </table>
                            <div style="margin: 10px">
                                <button type="submit" class="btn btn-default" title="Добавить партнера">
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

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
