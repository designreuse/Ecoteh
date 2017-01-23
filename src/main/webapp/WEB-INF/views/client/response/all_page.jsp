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
        <title>Отзывы о компании | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="Отзывы о компании | <c:out value="${main_company.title}"/>">
        <meta name="robots" content="index,follow">
        <meta name="description" content="Отзывы о компании &quot;<c:out value="${main_company.title}"/>&quot;.">
        <meta name="keywords" content="Отзывы о компании, <c:out value="${main_company.keywords}"/>"/>
        <c:if test="${main_company.faviconUrl ne null}">
            <link rel="shortcut icon" href="<c:url value="${main_company.faviconUrl}"/>" type="image/x-icon">
            <link rel="icon" href="<c:url value="${main_company.faviconUrl}"/>" type="image/x-icon">
        </c:if>
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
              rel="stylesheet" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
              rel="stylesheet" type="text/css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet"
              type="text/css">
        <link href="<c:url value="/resources/css/style.min.css"/>" rel="stylesheet" type="text/css">
    </head>
    <body>
    <jsp:include page="/WEB-INF/views/client/main/navigation.jsp"/>
    <c:set var="length" value="${fn:length(responses_list)}"/>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
        <div class="container">
            <div class="row">
                <div class="box">
                    <c:if test="${(authorized_user ne null) and (length gt 0)}">
                        <c:set var="reqmap" value="/admin"/>
                        <%-- Actions --%>
                        <div class="text-center">
                            <a href="<c:url value="/admin/response/delete/all"/>" title="Удалить все отзывы о компании">
                                <button class="btn btn-default">
                                    <span class="glyphicon glyphicon-remove red"
                                          aria-hidden="true"></span>&nbsp;Удалить все
                                </button>
                            </a>
                        </div>
                    </c:if>
                        <%-- Path --%>
                    <p class="path">
                        <a href="<c:url value="${reqmap}/"/>"
                           title="Перейти на главную страницу">Главная</a>
                        → <a href="<c:url value="${reqmap}/company/main"/>"
                             title="Описание нашей компании">Описание компании</a>
                        → <a href="<c:url value="${reqmap}/responses"/>">Отзывы о компании</a>
                    </p>
                    <hr>
                    <h3 class="text-center">Отзывы о компании</h3>
                    <hr>
                    <c:if test="${length gt 1}">
                        <p class="path">
                            <a href="#">Сортировка</a>:
                            <a href="<c:url value="${reqmap}/responses/sort?revers=${revers}"/>"
                               title="Сортировать по дате">По дате</a>
                        </p>
                    </c:if>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
        <c:if test="${length gt 0}">
            <c:if test="${(print_responses eq null) or (print_responses gt length) or (print_responses le 0)}">
                <c:set var="print_responses" value="${length}"/>
            </c:if>
            <div class="container">
                <div class="row">
                    <div class="box">
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                            <c:forEach items="${responses_list}" var="response" end="${print_responses - 1}">
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                                    <p>
                                        <c:if test="${!response.validated}">
                                            <span class="glyphicon glyphicon-eye-close little red" aria-hidden="true"
                                                  title="Не отображается для клиентов"></span>&nbsp;
                                        </c:if>
                                        <span class="response green"><c:out value="${response.username}"/></span>&nbsp;
                                        <span class="little">(<c:out value="${response.dateToString}"/>)</span>
                                    </p>
                                    <p class="response"><c:out value="${response.text}"/></p>
                                    <c:if test="${authorized_user ne null}">
                                        <p class="response">
                                            <c:choose>
                                                <c:when test="${response.validated}">
                                                    <a href="<c:url value="/admin/response/valid/${response.id}"/>"
                                                       title="Не отображать отзыв, его не смогут увидеть клиенты.">
                                                        <button class="btn btn-default">
                                                            <span class="glyphicon glyphicon-eye-close yellow"
                                                                  aria-hidden="true"></span>&nbsp;Не отображать
                                                        </button>
                                                    </a>&nbsp;
                                                </c:when>
                                                <c:when test="${!response.validated}">
                                                    <a href="<c:url value="/admin/response/valid/${response.id}"/>"
                                                       title="Одобрить отзыв, его смогут увидеть клиенты.">
                                                        <button class="btn btn-default">
                                                            <span class="glyphicon glyphicon-ok"
                                                                  aria-hidden="true"></span>&nbsp;Отображать
                                                        </button>
                                                    </a>&nbsp;
                                                </c:when>
                                            </c:choose>&nbsp;&nbsp;
                                            <a href="<c:url value="/admin/response/delete/${response.id}"/>"
                                               title="Удалить отзыв">
                                                <button class="btn btn-default">
                                                    <span class="glyphicon glyphicon-minus red"
                                                          aria-hidden="true"></span>&nbsp;Удалить
                                                </button>
                                            </a>
                                        </p>
                                    </c:if>
                                    <br>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
        </c:if>
        <div class="container">
            <div class="row">
                <div class="box">
                    <jsp:include page="/WEB-INF/views/client/message/to_responses.jsp"/>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/views/client/main/footer.jsp"/>
    <script src="<c:url value="/resources/js/jquery.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/mask.min.js"/>" type="text/javascript" async></script>
    <script src="<c:url value="/resources/js/easing.min.js"/>" type="text/javascript" async></script>
    <script src="<c:url value="/resources/js/totop.min.js"/>" type="text/javascript" async></script>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
