<%--
Page to add a new article.

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
        <title>Новая статья | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="Новая статья | <c:out value="${main_company.title}"/>">
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
                        → <a href="<c:url value="/article/all"/>">Все&nbsp;статьи</a>
                        → <a href="/admin/article/new">Новая&nbsp;статья</a>
                    </p>
                    <hr>
                    <h3 class="text-center" title="Добавление новой статьи">Новая статья</h3>
                    <hr>
                    <div class="text-center">
                            <%-- Form to add a new article --%>
                        <form action="<c:url value="/admin/article/add"/>" method="post" enctype="multipart/form-data">
                            <table align="center" class="table-size">
                                <tr>
                                    <td class="ths">
                                        <span class="red">*</span>&nbsp;Название
                                    </td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="title" minlength="2"
                                               maxlength="100" placeholder="Название статьи" required>
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
                                        <textarea class="form-control textarea" name="description" rows="10" title=""
                                                  placeholder="Краткое описание статьи (анонс)."></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Ключевые слова</td>
                                    <td class="tds">
                                    <textarea class="form-control textarea" name="keywords" rows="7" title=""
                                              placeholder="Ключевые слова, которые описывают статью, необходимы для ботов-поисковиков, на страницах сайта не отображаются."></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Артикль</td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="number" minlength="2"
                                               maxlength="100" placeholder="Номер статьи, например: АС142.">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths"
                                        title="Если это поле оставить пустым, тогда на сайте для товара будет отображаться надпись: Цену уточнйте">
                                        Цена&nbsp;<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
                                    </td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="price" placeholder="Цена">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths"
                                        title="Эта цена будет использована для сортировки товаров по цене. Значение этого поля будет сохранятся, если введено значение цены.">
                                        Цена для сортировки&nbsp;<span class="glyphicon glyphicon-info-sign"
                                                                       aria-hidden="true"></span>
                                    </td>
                                    <td class="tds">
                                        <input type="number" class="form-control" name="price" min="0" step="0.5"
                                               placeholder="Сортировочная цена">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Категория</td>
                                    <td class="tds">
                                        <select class="form-control" name="category_url">
                                            <c:forEach items="${categories}" var="category">
                                                <option value="<c:out value="${category.url}"/>"><c:out
                                                        value="${category.title}"/></option>
                                            </c:forEach>
                                        </select>
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
                                        <label title="Это новый товар.">
                                            Новинка&nbsp;
                                            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
                                        </label>
                                    </td>
                                    <td class="tds">
                                        <label title="Это новый товар.">
                                            <input type="radio" name="is_novelty" value="true" checked required/>
                                            &nbsp;Новинка
                                        </label>&nbsp;&nbsp;
                                        <label title="Это не новинка.">
                                            <input type="radio" name="is_novelty" value="false" required/>
                                            &nbsp;Не&nbsp;новинка
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <label title="Если статья позначеная для отображения, она будет доступна любому пользователю, иначе ее сможет увидеть только адмиистратор.">
                                            Отображение&nbsp;
                                            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
                                        </label>
                                    </td>
                                    <td class="tds">
                                        <label title="Статью смогут увидеть все пользователей">
                                            <input type="radio" name="is_valid" value="true" checked required/>
                                            &nbsp;Отображать
                                        </label>&nbsp;&nbsp;
                                        <label title="Статью смогут увидеть только администраторы">
                                            <input type="radio" name="is_valid" value="false" required/>
                                            &nbsp;Не&nbsp;отображать
                                        </label>
                                    </td>
                                </tr>
                            </table>
                            <div style="margin: 10px">
                                <button type="submit" class="btn btn-default" title="Сохранить информацию">
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
    <script src="<c:url value="/resources/js/lightgallery.min.js"/>"></script>
    <script src="<c:url value="/resources/js/easing.min.js"/>" async></script>
    <script src="<c:url value="/resources/js/totop.min.js"/>" async></script>
    </body>
    </html>
</compress:html>
