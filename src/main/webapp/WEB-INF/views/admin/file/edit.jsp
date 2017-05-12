<%--
Page for editing a incoming file.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <fmt:setBundle basename="content" var="content"/>
    <fmt:message var="size" bundle="${content}" key="file.max.size"/>
    <c:set var="maxFileSize" value="${size}"/>

    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Yurii Salimov (yuriy.alex.salimov@gmail.com)">
        <title>
            Редактирование файла &quot;<c:out value="${file.title}"/>&quot; | <c:out value="${main_company.title}"/>
        </title>
        <meta name="title"
              content="Редактирование файла &quot;<c:out value="${file.title}"/>&quot; | <c:out value="${main_company.title}"/>">
        <meta name="robots" content="noindex,nofollow">
        <meta name="description" content="Форма для редактирования файла &quot;<c:out value="${file.title}"/>&quot;">
        <meta name="keywords" content="Редактирование файла, <c:out value="${file.title}"/>"/>
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
        <link href="<c:url value="/resources/css/lightgallery.min.css"/>" rel="stylesheet" type="text/css">
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
                        → <a href="<c:url value="/admin/menu"/>" title="Меню администратора">Меню</a>
                        → <a href="<c:url value="/admin/file/all"/>">Все&nbsp;файлы</a>
                        → <a href="<c:url value="/admin/file/edit/${file.id}"/>">Редактирование файла</a>
                    </p>
                    <hr>
                    <h3 class="text-center">
                        Редактирование файла &quot;<c:out value="${file.title}"/>&quot;
                    </h3>
                    <hr>
                    <div class="text-center">
                            <%-- Form for editing a incoming file --%>
                        <form action="<c:url value="/admin/file/update"/>" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="id" value="<c:out value="${file.id}"/>">
                            <table align="center" class="table-size">
                                <tr>
                                    <td class="ths">
                                        <span class="red">*</span>&nbsp;Название
                                    </td>
                                    <td class="tds">
                                        <input type="text" class="form-control" name="title" maxlength="100" required
                                               placeholder="Название файла" value="<c:out value="${file.title}"/>">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ths">Файл</td>
                                    <td class="tds">
                                        <a href="<c:url value="${file.url}"/>" rel="lightgallery"
                                           title="<c:out value="${file.title}"/>">
                                            <img class="img-logo" alt="<c:out value="${file.title}"/>"
                                                 src="<c:url value="${file.url}"/>"
                                                 onerror="this.src='<c:url
                                                         value="/resources/img/static/default_file.gif"/>'">
                                        </a>
                                        <br>
                                        <input type="file" name="file" class="form-control">
                                    </td>
                                </tr>
                                <c:choose>
                                    <c:when test="${file.validated}">
                                        <tr>
                                            <td class="ths">Роль</td>
                                            <td class="tds">
                                                <label>
                                                    <input type="radio" name="type" value="FAVICON"
                                                           <c:if test="${file.type eq 'FAVICON'}">checked</c:if>/>
                                                    &nbsp;Значок сайта
                                                </label>&nbsp;&nbsp;
                                                <label>
                                                    <input type="radio" name="type" value="SLIDE"
                                                           <c:if test="${file.type eq 'SLIDE'}">checked</c:if>/>
                                                    &nbsp;Слайд
                                                </label>&nbsp;&nbsp;
                                                <label>
                                                    <input type="radio" name="type" value="OTHER"
                                                           <c:if test="${file.type eq 'OTHER'}">checked</c:if>/>
                                                    &nbsp;Другое
                                                </label>&nbsp;&nbsp;
                                            </td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" name="type" value="${file.type}"/>
                                    </c:otherwise>
                                </c:choose>
                            </table>
                            <div style="margin: 10px">
                                <button type="submit" class="btn btn-default" title="Добавить файл">
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
                                (Максимальный размер файла
                                <fmt:formatNumber type="number" maxFractionDigits="3" value="${maxFileSize / 1048576}"/>
                                Мб)
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
    <script src="<c:url value="/resources/js/lightgallery.min.js"/>" type="text/javascript"></script>
    </body>
    </html>
</compress:html>
