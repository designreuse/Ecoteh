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
        <title>Редактирование раздела &quot;<c:out value="${section.title}"/>&quot; | <c:out
                value="${main_company.title}"/></title>
        <meta name="title"
              content="Редактирование раздела &quot;<c:out value="${section.title}"/>&quot; | <c:out value="${main_company.title}"/>">
        <meta name="robots" content="noindex,nofollow">
        <meta name="description"
              content="Форма для редактирования раздела &quot;<c:out value="${section.title}"/>&quot;.">
        <meta name="keywords"
              content="Редактирование раздела, <c:out value="${section.title}"/>, <c:out value="${section.keywords}"/>"/>
        <c:if test="${main_company.favicon ne null}">
            <link rel="shortcut icon" href="<c:url value="/resources/img/${main_company.favicon.url}"/>"
                  type="image/x-icon">
            <link rel="icon" href="<c:url value="/resources/img/${main_company.favicon.url}"/>" type="image/x-icon">
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
        <%-- NAVIGATION --%>
    <jsp:include page="/WEB-INF/views/client/main/navigation.jsp"/>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
        <div class="container">
            <div class="row">
                <div class="box">
                        <%-- Path --%>
                    <p class="path">
                        <a href="<c:url value="/admin/"/>" title="Перейти на главную странцу">Главная</a>
                        → <a href="<c:url value="/admin/menu"/>" title="Меню администратора">Меню администратора</a>
                        → <a href="<c:url value="/admin/section/all"/>" title="Все разделы">Все разделы</a>
                        → <a href="#">Редактирование раздела</a>
                    </p>
                    <hr>
                    <h3 class="text-center">
                        Редактирование раздела &quot;<a href="<c:url value="/admin/section/${section.url}"/>"><c:out
                            value="${section.title}"/></a>&quot;
                    </h3>
                    <hr>
                    <div class="text-center">
                        <form action="<c:url value="/admin/section/update"/>" method="post"
                              enctype="multipart/form-data">
                            <table align="center" class="table-size">
                                <tr>
                                    <th class="ths"><span class="red">*</span>&nbsp;Название</th>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="title" minlength="2"
                                               maxlength="100" placeholder="Название раздела" required
                                               value="<c:out value="${section.title}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">Описание</th>
                                    <td class="tds">
                                    <textarea class="form-control textarea" name="description" required rows="6"
                                              placeholder="Краткое описание раздела."
                                              title=""><c:out value="${section.description}"/></textarea>
                                    </td>
                                </tr>

                                <tr>
                                    <th class="ths"><span class="red">*</span>&nbsp;Ключевые слова</th>
                                    <td class="tds">
                                    <textarea class="form-control textarea" name="keywords" required title=""
                                              placeholder="Ключевые слова, которые описывают статью, необходимы для ботов-поисковиков, на страницах сайта не отображаются."
                                              rows="3"><c:out value="${section.keywords}"/></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="ths">Фото</th>
                                    <td class="tds">
                                        <c:choose>
                                            <c:when test="${section.photo ne null}">
                                                <a href="<c:url value="/resources/img/${section.photo.url}"/>"
                                                   rel="lightgallery">
                                                    <img class="img-logo" alt="<c:out value="${section.title}"/>"
                                                         src="<c:url value="/resources/img/${section.photo.url}"/>">
                                                </a><br><br>
                                                <label title="Добавить новое фото">
                                                    <b><input type="radio" name="photo_action" value="replace" checked
                                                              required/>&nbsp;Заменить</b>
                                                </label>&nbsp;&nbsp;
                                                <label title="Удалить фото">
                                                    <b><input type="radio" name="photo_action" value="delete"
                                                              required/>&nbsp;Удалить</b>
                                                </label><br>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="hidden" name="photo_action" value="replace" checked
                                                       required/>
                                            </c:otherwise>
                                        </c:choose>
                                        <br>
                                        <input type="file" name="main_photo" accept="image/*" class="form-control"><br>
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
                                            <b><input type="radio" name="is_valid" value="true"
                                                      <c:if test="${section.validated}">checked</c:if>
                                                      required/>&nbsp;Отображать</b>
                                        </label>&nbsp;&nbsp;
                                        <label title="Раздел смогут увидеть только администраторы">
                                            <b><input type="radio" name="is_valid" value="false"
                                                      <c:if test="${!section.validated}">checked</c:if>
                                                      required/>&nbsp;Не отображать</b>
                                        </label>
                                    </td>
                                </tr>
                            </table>
                            <input type="hidden" name="url" value="<c:out value="${section.url}"/>">
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
        <%-- FOOTER --%>
    <jsp:include page="/WEB-INF/views/client/main/footer.jsp"/>
        <%-- Scripts --%>
    <script src="<c:url value="/resources/js/jquery.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/lightgallery.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/easing.min.js"/>" type="text/javascript" async></script>
    <script src="<c:url value="/resources/js/totop.min.js"/>" type="text/javascript" async></script>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
