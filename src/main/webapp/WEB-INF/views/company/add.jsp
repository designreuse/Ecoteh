<%--
Page to add a new company.

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
        <title>Добавление нового партнера | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="Добавление нового партнера | <c:out value="${main_company.title}"/>">
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
                        → <a href="<c:url value="/company/all"/>">Все&nbsp;партнеры</a>
                        → <a href="<c:url value="/admin/company/new"/>">Новый&nbsp;партнер</a>
                    </p>
                    <hr>
                    <h3 class="text-center" title="Добавление нового партнера">Новый партнер</h3>
                    <hr>
                    <div class="text-center">
                            <%-- Form to add a new company --%>
                        <form action="<c:url value="/admin/company/add"/>" method="post" enctype="multipart/form-data">
                            <table align="center" class="table-size">
                                <tr>
                                    <td class="ths">
                                        <span class="red">*</span>&nbsp;Название
                                    </td>
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
                                                  placeholder="Короткий и броский рекламный призыв или девиз."
                                                  title=""></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <span class="red">*</span>&nbsp;Основной текст
                                    </td>
                                    <td class="tds">
                                    <textarea class="form-control textarea" name="text" rows="10" title="" required
                                              placeholder="Основная информация статьи."></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Описание</td>
                                    <td class="tds">
                                        <textarea class="form-control textarea" name="description" rows="6" title=""
                                                  placeholder="Краткое описание компании"></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Ключевые слова</td>
                                    <td class="tds">
                                        <textarea class="form-control textarea" name="keywords" rows="7"
                                                  placeholder="Ключевые слова, которые описывают компанию, необходимы для ботов-поисковиков, на страницах сайта не отображаются."
                                                  title=""></textarea>
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
                                        <input type="text" class="phone form-control" name="landlines_phone"
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
                                        <a href="https://twitter.com" target="_blank"
                                           title="Социальная сеть Twitter">Twitter</a>
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
                                    <td class="ths">Адрес офиса</td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="post_address" maxlength="300"
                                               placeholder="Адрес главного офиса компании">
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
                                                  rows="5"></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Логотип</td>
                                    <td class="tds">
                                        <input type="file" name="logo" class="form-control">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <label title="Если компания позначеная для отображения, она будет доступна любому пользователю, иначе ее сможет увидеть только адмиистратор.">
                                            Отображение&nbsp;
                                            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
                                        </label>
                                    </td>
                                    <td class="tds">
                                        <label title="Компанию смогут увидеть все пользователей">
                                            <input type="radio" name="is_valid" value="true" checked required/>
                                            &nbsp;Отображать
                                        </label>&nbsp;&nbsp;
                                        <label title="Компанию смогут увидеть только администраторы">
                                            <input type="radio" name="is_valid" value="false" required/>
                                            &nbsp;Не&nbsp;отображать
                                        </label>
                                    </td>
                                </tr>
                            </table>
                            <div style="margin: 10px">
                                <button type="submit" class="btn btn-default" title="Добавить партнера">
                                    <span class="glyphicon glyphicon-save" aria-hidden="true"></span>
                                    &nbsp;Сохранить
                                </button>
                                &nbsp;&nbsp;
                                <button type="reset" class="btn btn-default" title="Сбросить информацию">
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
