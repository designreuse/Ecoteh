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
        <title>Редактирование статьи &quot;<c:out value="${article.title}"/>&quot; | <c:out
                value="${main_company.title}"/></title>
        <meta name="title"
              content="Редактирование статьи &quot;<c:out value="${article.title}"/>&quot; | <c:out value="${main_company.title}"/>">
        <meta name="robots" content="noindex,nofollow">
        <meta name="description"
              content="Форма для редактирования статьи &quot;<c:out value="${article.title}"/>&quot;.">
        <meta name="keywords"
              content="Редактирование статии, <c:out value="${article.title}"/>, <c:out value="${article.keywords}"/>"/>
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
                        → <a href="<c:url value="/admin/article/all"/>">Все статьи</a>
                        → <a href="#">Редактирование статьи</a>
                    </p>
                    <hr>
                    <h3 class="text-center">
                        Редактирование статьи
                        &quot;<a href="<c:url value="/admin/article/${article.url}"/>">
                        <c:out value="${article.title}"/>
                    </a>&quot;
                    </h3>
                    <hr>
                    <div class="text-center">
                        <form action="<c:url value="/admin/article/update"/>" method="post"
                              enctype="multipart/form-data">
                            <input type="hidden" name="url" value="<c:out value="${article.url}"/>">
                            <table align="center" class="table-size">
                                <tr>
                                    <td class="ths"><span class="red">*</span>&nbsp;Название</td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="title" minlength="2"
                                               maxlength="100" placeholder="Название статьи" required
                                               value="<c:out value="${article.title}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Описание</td>
                                    <td class="tds">
                                    <textarea class="form-control textarea" name="description" title=""
                                              placeholder="Краткое описание статьи (анонс)."
                                              rows="6"><c:out value="${article.description}"/></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths"><span class="red">*</span>&nbsp;Основной текст</td>
                                    <td class="tds">
                                    <textarea class="form-control textarea" name="text" required
                                              placeholder="Основная информация статьи." title=""
                                              rows="10"><c:out value="${article.text}"/></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths"><span class="red">*</span>&nbsp;Ключевые слова</td>
                                    <td class="tds">
                                    <textarea class="form-control textarea" name="keywords" required title=""
                                              placeholder="Ключевые слова, которые описывают статью, необходимы для ботов-поисковиков, на страницах сайта не отображаются."
                                              rows="5"><c:out value="${article.keywords}"/></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Артикль</td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="number" minlength="2"
                                               maxlength="100" placeholder="Номер статьи, например: АС142."
                                               value="<c:out value="${article.number}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Категория</td>
                                    <td class="tds">
                                        <select class="form-control" name="category_url">
                                            <c:choose>
                                                <c:when test="${article.category eq null}">
                                                    <option value="">Нет</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="<c:out value="${article.category.url}"/>"><c:out
                                                            value="${article.category.title}"/></option>
                                                </c:otherwise>
                                            </c:choose>
                                            <c:forEach items="${categories}" var="category">
                                                <c:if test="${category.id ne article.category.id}">
                                                    <option value="<c:out value="${category.url}"/>"><c:out
                                                            value="${category.title}"/></option>
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${article.category ne null}">
                                                <option value="">Нет</option>
                                            </c:if>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <a href="<c:url value="/resources/img/static/where_article_photo.jpg"/>"
                                           rel="lightgallery" title="Главное фото, это где?">
                                            Главное фото&nbsp;<span class="glyphicon glyphicon-info-sign"
                                                                    aria-hidden="true"></span>
                                        </a>
                                    </td>
                                    <td class="tds">
                                        <c:choose>
                                            <c:when test="${article.photo ne null}">
                                                <a href="<c:url value="/resources/${article.photo.url}"/>"
                                                   rel="lightgallery[slides]">
                                                    <img class="img-logo" alt="<c:out value="${article.title}"/>"
                                                         src="<c:url value="/resources/${article.photo.url}"/>">
                                                </a><br><br>
                                                <label title="Заменить главное фото">
                                                    <b><input type="radio" name="photo_action" value="replace" checked
                                                              class="radio" required/>&nbsp;Заменить</b>
                                                </label>&nbsp;&nbsp;
                                                <label title="Удалить главное фото">
                                                    <b><input type="radio" name="photo_action" value="delete"
                                                              class="radio" required/>&nbsp;Удалить</b>
                                                </label>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="hidden" name="photo_action" value="replace" checked
                                                       required/>
                                            </c:otherwise>
                                        </c:choose>
                                        <br>
                                        <input type="file" name="photo" accept="image/*" class="form-control"><br>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <label title="Если статья позначеная для отображения, она будет доступна любому пользователю, иначе ее сможет увидеть только адмиистратор.">
                                            Отображение&nbsp;<span class="glyphicon glyphicon-info-sign"
                                                                      aria-hidden="true"></span>
                                        </label>
                                    </td>
                                    <td class="tds">
                                        <label title="Статью смогут увидеть все пользователей">
                                            <input type="radio" name="is_valid" value="true"
                                                      <c:if test="${article.validated}">checked</c:if>
                                                      required/>&nbsp;Отображать
                                        </label>&nbsp;&nbsp;
                                        <label title="Статью смогут увидеть только администраторы">
                                            <input type="radio" name="is_valid" value="false"
                                                      <c:if test="${!article.validated}">checked</c:if>
                                                      required/>&nbsp;Не отображать
                                        </label>
                                    </td>
                                </tr>
                            </table>
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
                            <p>
                                <span class="red">*</span>&nbsp;
                                Поля обязательные для заполнения. Хорошим тоном является заполнения всех полей объекта.
                            </p>
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
    <script src="<c:url value="/resources/js/lightgallery.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/easing.min.js"/>" type="text/javascript" async></script>
    <script src="<c:url value="/resources/js/totop.min.js"/>" type="text/javascript" async></script>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
