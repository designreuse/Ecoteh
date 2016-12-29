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
        <title>Новый раздел | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="Новый раздел | <c:out value="${main_company.title}"/>">
        <meta name="robots" content="noindex,nofollow">
        <meta name="description" content="Форма для добавления нового раздела.">
        <meta name="keywords" content="Новый раздел, добавление раздела"/>
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
                        → <a href="/admin/menu" title="Меню администратора">Меню администратора</a>
                        → <a href="/admin/section/all" title="Все разделы">Все разделы</a>
                        → <a href="#">Новый раздел</a>
                    </p>
                    <hr>
                    <h3 class="text-center" title="Добавление нового раздела">Новый раздел</h3>
                    <hr>
                    <div class="text-center">
                        <form enctype="multipart/form-data" method="post" action="/admin/section/add">
                            <table align="center" class="table-size">
                                <tr>
                                    <th class="ths"><span class="red">*</span>&nbsp;Название</th>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="title" minlength="2"
                                               maxlength="100" placeholder="Название раздела" required>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">Описание</th>
                                    <td class="tds">
                                        <textarea class="form-control textarea" name="description" required rows="6"
                                                  title="" placeholder="Краткое описание раздела."></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths"><span class="red">*</span>&nbsp;Ключевые слова</th>
                                    <td class="tds">
                                        <textarea class="form-control textarea" name="keywords" rows="3" required
                                                  title=""
                                                  placeholder="Ключевые слова, которые описывают статью, необходимы для ботов-поисковиков, на страницах сайта не отображаются."></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">Фото</th>
                                    <td class="tds">
                                        <input type="file" name="main_photo" accept="image/*" class="form-control">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">
                                        <label title="Если раздел позначен для отображения, он будет доступный любому пользователю, иначе его сможет увидеть только адмиистратор.">
                                            <b>Отображение&nbsp;<span class="glyphicon glyphicon-info-sign"
                                                      aria-hidden="true"></span></b>
                                        </label>
                                    </th>
                                    <td class="tds">
                                        <label title="Раздел смогут увидеть все пользователей">
                                            <b><input type="radio" name="is_valid" value="true" checked
                                                      required/>&nbsp;Отображать</b>
                                        </label>&nbsp;&nbsp;
                                        <label title="Раздел смогут увидеть только администраторы">
                                            <b><input type="radio" name="is_valid" value="false"
                                                      required/>&nbsp;Не отображать</b>
                                        </label>
                                    </td>
                                </tr>
                            </table>
                            <div style="margin: 10px">
                                <button type="submit" class="btn btn-default" title="Добавить раздел">
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
    <script src="/resources/js/easing.min.js" type="text/javascript" async></script>
    <script src="/resources/js/totop.min.js" type="text/javascript" async></script>
    </body>
    </html>
</compress:html>

<!-- Yurii Salimov (yurii.alex.salimov@gmail.com) -->
