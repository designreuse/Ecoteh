<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${authorized_user ne null}"><c:set var="reqmap" value="/admin"/></c:if>
<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
    <nav class="navbar navbar-default">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${reqmap}/">
                    <c:choose>
                        <c:when test="${main_company ne null}">
                            <c:out value="${main_company.title}"/>
                        </c:when>
                        <c:otherwise>
                            <span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;Главная
                        </c:otherwise>
                    </c:choose>
                </a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <%-- For not mobile device --%>
                <ul class="hidden-xs dropdown nav navbar-nav">
                    <li>
                        <a href="${reqmap}/" title="Перейти на главную страницу">
                            <span class="glyphicon glyphicon-home" aria-hidden="true"></span>
                            <span class="hidden-sm">&nbsp;Главная</span>
                        </a>
                    </li>
                    <li class="dropdown-top">
                        <a class="dropdown-top" href="${reqmap}/article/all">Продукция</a>
                        <div class="dropdown-inside" role="menu">
                            <c:set var="length" value="${fn:length(sections)}"/>
                            <div class="<c:choose>
                                        <c:when test="${length eq 3}">nav-container-4</c:when>
                                        <c:when test="${length ne 0}">nav-container-5</c:when>
                                        </c:choose>">
                                <c:if test="${length gt 0}">
                                    <c:forEach items="${sections}" var="section" end="4">
                                        <div class="<c:choose>
                                            <c:when test="${(length eq 3) or (length le 2)}">
                                            col-sm-6 col-md-6 col-lg-6 col-xl-6</c:when>
                                            <c:otherwise>col-sm-4 col-md-4 col-lg-4 col-xl-4</c:otherwise>
                                            </c:choose>">
                                            <a href="${reqmap}/section/<c:out value="${section.url}"/>"
                                               title="Перейти к разделу &quot;<c:out value="${section.title}"/>&quot;">
                                                <h4 class="text-center"><c:out value="${section.title}"/></h4>
                                            </a>
                                            <c:forEach items="${section.categories}" var="category" end="4">
                                                <a href="${reqmap}/category/<c:out value="${category.url}"/>"
                                                   title="Перейти к категории &quot;<c:out value="${category.title}"/>&quot;">
                                                    <h5 class="text-center"><c:out value="${category.title}"/></h5>
                                                </a>
                                            </c:forEach>
                                        </div>
                                    </c:forEach>
                                </c:if>
                                <div class="<c:choose>
                                            <c:when test="${(length eq 3) or (length le 2)}">
                                            col-sm-6 col-md-6 col-lg-6 col-xl-6</c:when>
                                            <c:when test="${length eq 0}">
                                            col-sm-12 col-md-12 col-lg-12 col-xl-12</c:when>
                                            <c:otherwise>col-sm-4 col-md-4 col-lg-4 col-xl-4</c:otherwise>
                                            </c:choose>">
                                    <h4 class="text-center"><a href="${reqmap}/section/all">Разделы</a></h4>
                                    <h4 class="text-center"><a href="${reqmap}/category/all">Категории</a></h4>
                                    <h4 class="text-center"><a href="${reqmap}/article/all">Статьи</a></h4>
                                </div>
                            </div>
                        </div>
                    </li>
                    <c:if test="${main_company ne null}">
                        <li class="dropdown-top">
                            <a class="dropdown-top" href="${reqmap}/company/main">О компании</a>
                            <ul class="dropdown-inside" role="menu">
                                <li class="text-center">
                                    <a href="${reqmap}/company/main" title="Описание нашей компании">Описание</a>
                                    <hr>
                                </li>
                                <li class="text-center">
                                    <a href="${reqmap}/contacts" title="Как с нами связаться">Контакты</a>
                                    <hr>
                                </li>
                                <li class="text-center">
                                    <a href="${reqmap}/responses" title="Отзывы о нашей компании">Отзывы</a>
                                </li>
                            </ul>
                        </li>
                    </c:if>
                    <c:if test="${fn:length(partners) gt 0}">
                        <li><a href="${reqmap}/company/all" title="Наши партнеры">Партнеры</a></li>
                    </c:if>
                    <li>
                        <a href="/search" title="Поиск необходимого контента">
                            <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                        </a>
                    </li>
                    <c:choose>
                        <c:when test="${authorized_user ne null}">
                            <li>
                                <a href="/logout" title="Выйти">
                                    <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                                </a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a href="/login" title="Авторизироваться">
                                    <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>
                                </a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
                <c:if test="${authorized_user ne null}">
                    <ul class="hidden-xs dropdown nav navbar-nav">
                        <li><a href="/admin/user/all" title="Весь персонал">Персонал</a></li>
                        <li><a href="/admin/cache/" title="Список обьектов в памяти">Кэш</a></li>
                        <li>
                            <a href="/admin/menu" title="Mеню | <c:out value="${authorized_user.name}"/>">
                                <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                            </a>
                        </li>
                        <li>
                            <a href="/admin/messages" title="Сообщения от пользователей">
                                <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
                            </a>
                        </li>
                    </ul>
                </c:if>
                <%-- For mobile device --%>
                <ul class="hidden-sm hidden-md hidden-lg hidden-xl dropdown nav navbar-nav">
                    <li>
                        <a href="${reqmap}/">
                            <span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;Главная
                        </a>
                    </li>
                    <c:choose>
                        <c:when test="${authorized_user ne null}">
                            <li><a href="/admin/section/all">Разделы</a></li>
                            <li><a href="/admin/category/all">Категории</a></li>
                            <li><a href="/admin/article/all">Статьи</a></li>
                        </c:when>
                        <c:when test="${length gt 0}">
                            <li><a href="${reqmap}/section/all">Все разделы</a></li>
                        </c:when>
                    </c:choose>
                    <c:if test="${main_company ne null}">
                        <li><a href="${reqmap}/company/main">О компании</a></li>
                        <li><a href="${reqmap}/contacts">Контакты</a></li>
                        <li><a href="${reqmap}/responses">Отзывы</a></li>
                    </c:if>
                    <c:if test="${fn:length(partners) gt 0}">
                        <li><a href="${reqmap}/company/all">Партнеры</a></li>
                    </c:if>
                    <c:choose>
                        <c:when test="${authorized_user ne null}">
                            <li><a href="/admin/user/all">Персонал</a></li>
                            <li><a href="/admin/cache/">Кэш</a></li>
                            <li>
                                <a href="/admin/messages">
                                    <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>&nbsp;Сообщения
                                </a>
                            </li>
                            <li>
                                <a href="/search">
                                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;Поиск
                                </a>
                            </li>
                            <li>
                                <a href="/admin/menu" title="Mеню | <c:out value="${authorized_user.name}"/>">
                                    <span class="glyphicon glyphicon-user"
                                          aria-hidden="true"></span>&nbsp;<c:out value="${authorized_user.name}"/>
                                </a>
                            </li>
                            <li>
                                <a href="/logout">
                                    <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>&nbsp;Выйти
                                </a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a href="/search">
                                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;Поиск
                                </a>
                            </li>
                            <li>
                                <a href="/login">
                                    <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>&nbsp;Войти
                                </a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </nav>
</div>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
