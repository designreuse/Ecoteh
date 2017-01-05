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
        <title>Сообщения от пользователей | <c:out value="${main_company.title}"/></title>
        <meta name="title" content="Сообщения от пользователей | <c:out value="${main_company.title}"/>">
        <meta name="robots" content="index,follow">
        <meta name="description"
              content="Сообщения от пользователей &quot;<c:out value="${main_company.title}"/>&quot;.">
        <meta name="keywords" content="Сообщения от пользователей, <c:out value="${main_company.keywords}"/>"/>
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
                    <c:set var="length" value="${fn:length(messages)}"/>
                        <%-- Actions --%>
                    <c:if test="${length gt 0}">
                        <div class="text-center">
                            <a href="/admin/messages/delete/all" title="Удалить все сообщения от клиентов">
                                <button class="btn btn-default">
                                    <span class="glyphicon glyphicon-remove red"
                                          aria-hidden="true"></span>&nbsp;Удалить все
                                </button>
                            </a>
                        </div>
                    </c:if>
                        <%-- Path --%>
                    <p class="path">
                        <a href="/admin/" title="Перейти на главную страницу">Главная</a>
                        → <a href="/admin/menu" title="Меню администратора">Меню</a>
                        → <a href="#">Сообщения</a>
                    </p>
                    <hr>
                    <h3 class="text-center" title="Сообщения, которые прислали клиенты">
                        Сообщения<c:if test="${length eq 0}"> - список пуст!</c:if>
                    </h3>
                    <hr>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
            <%-- Messages --%>
        <c:if test="${length gt 0}">
            <c:if test="${(print_messages eq null) or (print_messages gt length) or (print_messages le 0)}">
                <c:set var="print_messages" value="${length}"/>
            </c:if>
            <div class="container">
                <div class="row">
                    <div class="box">
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                            <c:forEach items="${messages}" var="message" end="${print_messages - 1}">
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                                    <p class="response green">
                                        <c:out value="${message.dateToString}"/>&nbsp;&nbsp;&nbsp;
                                        <a href="/admin/messages/delete/${message.id}" title="Удалить сообщение">
                                            <button class="btn btn-default">
                                                    <span class="glyphicon glyphicon-minus red"
                                                          aria-hidden="true"></span>&nbsp;Удалить
                                            </button>
                                        </a>
                                    </p>
                                    <p class="response"><c:out value="${message.text}"/></p>
                                    <p class="response">
                                        <c:out value="${message.username}"/>,&nbsp;
                                        <a href="tel:<c:out value="${message.phone}"/>">
                                            <c:out value="${message.phone}"/></a>
                                        <c:if test="${message.email ne null}">
                                            ,&nbsp;
                                            <a href="mailto:<c:out value="${message.email}"/>">
                                                <c:out value="${message.email}"/>
                                            </a>
                                        </c:if>
                                    </p>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
        <%-- FOOTER --%>
    <jsp:include page="/WEB-INF/views/client/main/footer.jsp"/>
        <%-- Scripts --%>
    <script src="/resources/js/jquery.min.js" type="text/javascript"></script>
    <script src="/resources/js/bootstrap.min.js" type="text/javascript"></script>
    <c:if test="${length gt 0}">
        <script src="/resources/js/easing.min.js" type="text/javascript" async></script>
        <script src="/resources/js/totop.min.js" type="text/javascript" async></script>
    </c:if>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
