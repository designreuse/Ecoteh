<%--
Page for editing a incoming article.

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
        <title>Редактирование отзыва | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="Редактирование отзыва | <c:out value="${main_company.title}"/>">
        <meta name="robots" content="noindex,nofollow">
        <meta name="description" content="Форма для редактирования отзыва.">
        <meta name="keywords" content="Редактирование отзыва"/>
        <link rel="shortcut icon" href="<c:url value="${favicon.url}"/>" type="image/x-icon">
        <link rel="icon" href="<c:url value="${favicon.url}"/>" type="image/x-icon">
            <%-- CSS styles --%>
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
              rel="stylesheet" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
              rel="stylesheet" type="text/css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet"
              type="text/css">
        <link href="<c:url value="/resources/css/style.min.css"/>" rel="stylesheet" type="text/css">
    </head>
    <body>
        <%-- Navigation bar --%>
    <jsp:include page="/WEB-INF/views/client/main/navigation.jsp"/>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <div class="container">
            <div class="row">
                <div class="box">
                        <%-- Site page path --%>
                    <p class="path">
                        <a href="<c:url value="/"/>" title="Перейти на главную странцу">Главная</a>
                        → <a href="<c:url value="/responses"/>" title="Все отзывы">Отзывы</a>
                        →
                        <a href="<c:url value="/admin/response/edit/${response.id}"/>">
                            Редактирование статьи
                        </a>
                    </p>
                    <hr>
                    <h3 class="text-center">
                        Редактирование отзыва
                    </h3>
                    <hr>
                    <div class="text-center">
                            <%-- Form for editing a incoming article --%>
                        <form action="<c:url value="/admin/response/update"/>" method="post">
                            <input type="hidden" name="id" value="<c:out value="${response.id}"/>">
                            <table align="center" class="table-size">
                                <tr>
                                    <td class="ths">
                                        <span class="red">*</span>&nbsp;Имя пользователя
                                    </td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="username" minlength="2"
                                               maxlength="100" placeholder="Имя пользователя" required
                                               value="<c:out value="${response.username}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <span class="red">*</span>&nbsp;Отзыв
                                    </td>
                                    <td class="tds">
                                    <textarea class="form-control textarea" name="text" title="" required rows="10"
                                              placeholder="Текст отзыва"><c:out value="${response.text}"/></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">
                                        <label title="Если отзыв позначен для отображения, он будет доступен любому пользователю, иначе его сможет увидеть только адмиистратор.">
                                            Отображение&nbsp;
                                            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
                                        </label>
                                    </td>
                                    <td class="tds">
                                        <label title="Одобрить отзыв, его смогут увидеть клиенты.">
                                            <input type="radio" name="is_valid" value="true" required
                                                   <c:if test="${response.validated}">checked</c:if>/>
                                            &nbsp;Отображать
                                        </label>&nbsp;&nbsp;
                                        <label title="Не отображать отзыв, его не смогут увидеть клиенты.">
                                            <input type="radio" name="is_valid" value="false" required
                                                   <c:if test="${!response.validated}">checked</c:if>/>
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
    <jsp:include page="/WEB-INF/views/client/main/footer.jsp"/>
        <%-- Scripts --%>
    <script src="<c:url value="/resources/js/jquery.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/easing.min.js"/>" type="text/javascript" async></script>
    <script src="<c:url value="/resources/js/totop.min.js"/>" type="text/javascript" async></script>
    </body>
    </html>
</compress:html>
