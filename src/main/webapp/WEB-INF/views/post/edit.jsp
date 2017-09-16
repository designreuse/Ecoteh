<%--
Page for editing a incoming post.

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
        <title>Редактирование поста <c:out value="&quot;${post.title}&quot; | ${main_company.title}"/></title>
        <meta name="title"
              content="Редактирование поста <c:out value="&quot;${post.title}&quot; | ${main_company.title}"/>">
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
                        → <a href="<c:url value="/blog"/>">Блог</a>
                        →
                        <a href="<c:url value="/admin/post/edit/${post.url}"/>">
                            Редактирование статьи
                        </a>
                    </p>
                    <hr>
                    <h3 class="text-center">
                        Редактирование статьи
                        &quot;<a href="<c:url value="/blog/${post.url}"/>"><c:out
                            value="${post.title}"/></a>&quot;
                    </h3>
                    <hr>
                    <div class="text-center">
                            <%-- Form for editing a incoming post --%>
                        <form action="<c:url value="/admin/post/update"/>" method="post"
                              enctype="multipart/form-data">
                            <input type="hidden" name="url" value="<c:out value="${post.url}"/>">
                            <table align="center" class="table-size">
                                <tr>
                                    <td class="ths">
                                        <span class="red">*</span>&nbsp;Название
                                    </td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="title" minlength="2"
                                               maxlength="100" placeholder="Название поста" required
                                               value="<c:out value="${post.title}"/>">
                                    </td>
                                </tr>

                                <tr>
                                    <td class="ths">
                                        <span class="red">*</span>&nbsp;Основной текст
                                    </td>
                                    <td class="tds">
                                    <textarea class="form-control textarea" name="text" required
                                              placeholder="Основная информация поста." title=""
                                              rows="10"><c:out value="${post.text}"/></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Описание</td>
                                    <td class="tds">
                                    <textarea class="form-control textarea" name="description" title=""
                                              placeholder="Краткое описание поста (анонс)."
                                              rows="10"><c:out value="${post.description}"/></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Ключевые слова</td>
                                    <td class="tds">
                                    <textarea class="form-control textarea" name="keywords" title=""
                                              placeholder="Ключевые слова, которые описывают пост, необходимы для ботов-поисковиков, на страницах сайта не отображаются."
                                              rows="7"><c:out value="${post.keywords}"/></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Логотип</td>
                                    <td class="tds">
                                        <c:choose>
                                            <c:when test="${not empty post.logo.url}">
                                                <a href="<c:url value="${post.logo.url}"/>" rel="lightgallery"
                                                   title="<c:out value="${post.title}"/>">
                                                    <img class="img-responsive img-in-list"
                                                         alt="<c:out value="${post.title}"/>"
                                                         src="<c:url value="${post.logo.url}"/>"
                                                         onerror="this.src='<c:url
                                                                 value="/resources/img/static/default_file.gif"/>'">
                                                </a>
                                            </c:when>
                                            <c:otherwise>
                                                <img class="img-responsive img-in-list"
                                                     alt="<c:out value="${post.title}"/>"
                                                     src="<c:url value="/resources/img/static/default_file.gif"/>">
                                            </c:otherwise>
                                        </c:choose><br>
                                        <input type="file" name="logo" class="form-control">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <label title="Если пост позначеная для отображения, он будет доступна любому пользователю, иначе его сможет увидеть только адмиистратор.">
                                            Отображение&nbsp;
                                            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
                                        </label>
                                    </td>
                                    <td class="tds">
                                        <label title="Пост смогут увидеть все пользователей">
                                            <input type="radio" name="is_valid" value="true" required
                                                   <c:if test="${post.validated}">checked</c:if>/>
                                            &nbsp;Отображать
                                        </label>&nbsp;&nbsp;
                                        <label title="Пост смогут увидеть только администраторы">
                                            <input type="radio" name="is_valid" value="false" required
                                                   <c:if test="${!post.validated}">checked</c:if>/>
                                            &nbsp;Не отображать
                                        </label>
                                    </td>
                                </tr>
                            </table>
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
    <script src="<c:url value="/resources/js/lightgallery.min.js"/>"></script>
    <script src="<c:url value="/resources/js/easing.min.js"/>" async></script>
    <script src="<c:url value="/resources/js/totop.min.js"/>" async></script>
    </body>
    </html>
</compress:html>
