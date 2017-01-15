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
        <title>Новая статья | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="Новая статья | <c:out value="${main_company.title}"/>">
        <meta name="robots" content="noindex,nofollow">
        <meta name="description" content="Форма для добавления новой статьи.">
        <meta name="keywords" content="Новая статья, добавление статьи"/>
        <c:if test="${main_company.favicon ne null}">
            <link rel="shortcut icon" href="/resources/<c:out value="${main_company.favicon.url}"/>"
                  type="image/x-icon">
            <link rel="icon" href="/resources/<c:out value="${main_company.favicon.url}"/>" type="image/x-icon">
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
                        → <a href="<c:url value="/admin/article/all"/>">Все статьи</a>
                        → <a href="#">Новая статья</a>
                    </p>
                    <hr>
                    <h3 class="text-center" title="Добавление новой статьи">Новая статья</h3>
                    <hr>
                    <div class="text-center">
                        <form action="<c:url value="/admin/article/add"/>" method="post" enctype="multipart/form-data">
                            <table align="center" class="table-size">
                                <tr>
                                    <th class="ths"><span class="red">*</span>&nbsp;Название</th>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="title" minlength="2"
                                               maxlength="100" placeholder="Название статьи" required>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">Описание</th>
                                    <td class="tds">
                                    <textarea class="form-control textarea" name="description" rows="6" title=""
                                              placeholder="Краткое описание статьи (анонс)."></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths"><span class="red">*</span>&nbsp;Основной текст</th>
                                    <td class="tds">
                                    <textarea class="form-control textarea" name="text" required rows="10" title=""
                                              placeholder="Основная информация статьи."></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths"><span class="red">*</span>&nbsp;Ключевые слова</th>
                                    <td class="tds">
                                    <textarea class="form-control textarea" name="keywords" rows="3" title="" required
                                              placeholder="Ключевые слова, которые описывают статью, необходимы для ботов-поисковиков, на страницах сайта не отображаются."></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">Артикль</th>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="number" minlength="2"
                                               maxlength="100" placeholder="Номер статьи, например: АС142.">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">Категория</th>
                                    <td class="tds">
                                        <select class="form-control" name="category_url">
                                            <option value="">Нет</option>
                                            <c:forEach items="${categories}" var="category">
                                                <option value="<c:out value="${category.url}"/>"><c:out
                                                        value="${category.title}"/></option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">
                                        <a href="<c:url value="/resources/img/static/where_article_photo.jpg"/>"
                                           rel="lightgallery" title="Главное фото, это где?">
                                            Главное фото&nbsp;<span class=" glyphicon glyphicon-info-sign"
                                        aria-hidden="true"></span>
                                        </a>
                                    </th>
                                    <td class="tds">
                                        <input type="file" name="photo" accept="image/*" class="form-control">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">
                                        <label title="Если статья позначеная для отображения, она будет доступна любому пользователю, иначе ее сможет увидеть только адмиистратор.">
                                            <b>Отображение&nbsp;<span aria-hidden="true"
                                                                      class="glyphicon glyphicon-info-sign"></span></b>
                                        </label>
                                    </th>
                                    <td class="tds">
                                        <label title="Статью смогут увидеть все пользователей">
                                            <b><input type="radio" name="is_valid" value="true" checked
                                                      required/>&nbsp;Отображать</b>
                                        </label>&nbsp;&nbsp;
                                        <label title="Статью смогут увидеть только администраторы">
                                            <b><input type="radio" name="is_valid" value="false"
                                                      required/>&nbsp;Не отображать</b>
                                        </label>
                                    </td>
                                </tr>
                            </table>
                            <div style="margin: 10px">
                                <button type="submit" class="btn btn-default" title="Сохранить информацию">
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
    <script src="/resources/js/jquery.min.js" type="text/javascript"></script>
    <script src="/resources/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/resources/ckeditor/ckeditor.js" type="text/javascript"></script>
    <script>CKEDITOR.replace("text");</script>
    <script src="/resources/js/lightgallery.min.js" type="text/javascript"></script>
    <script src="/resources/js/easing.min.js" type="text/javascript" async></script>
    <script src="/resources/js/totop.min.js" type="text/javascript" async></script>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
