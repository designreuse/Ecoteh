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
                <a href="<c:url value="${reqmap}/"/>" class="navbar-brand">
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
                        <a href="<c:url value="${reqmap}/"/>" title="Перейти на главную страницу">
                            <span class="glyphicon glyphicon-home" aria-hidden="true"></span>
                            <span class="hidden-sm">&nbsp;Главная</span>
                        </a>
                    </li>
                    <li class="dropdown-top">
                        <a class="dropdown-top" href="<c:url value="${reqmap}/category/all"/>">Продукция</a>
                        <ul class="dropdown-inside" role="menu">
                            <c:forEach items="${categories}" var="category">
                                <li class="text-center">
                                    <a href="<c:url value="${reqmap}/category/${category.url}"/>"
                                       title="Перейти к &quot;<c:out value="${category.title}"/>&quot;">
                                        <h4 class="text-center">
                                            <c:out value="${category.title}"/>
                                        </h4>
                                    </a>
                                    <hr>
                                </li>
                            </c:forEach>
                            <li class="text-center">
                                <a href="<c:url value="${reqmap}/article/all"/>" title="Перейти к списку всех товаров">
                                    <h4 class="text-center">Продукция</h4>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <c:if test="${main_company ne null}">
                        <li class="dropdown-top">
                            <a class="dropdown-top" href="<c:url value="${reqmap}/company/main"/>">О компании</a>
                            <ul class="dropdown-inside" role="menu">
                                <li class="text-center">
                                    <a href="<c:url value="${reqmap}/company/main"/>"
                                       title="Описание нашей компании">Описание</a>
                                    <hr>
                                </li>
                                <li class="text-center">
                                    <a href="<c:url value="${reqmap}/contacts"/>"
                                       title="Как с нами связаться">Контакты</a>
                                    <hr>
                                </li>
                                <li class="text-center">
                                    <a href="<c:url value="${reqmap}/responses"/>"
                                       title="Отзывы о нашей компании">Отзывы</a>
                                </li>
                            </ul>
                        </li>
                    </c:if>
                    <c:if test="${fn:length(partners) gt 0}">
                        <li><a href="<c:url value="${reqmap}/company/all"/>" title="Наши партнеры">Партнеры</a></li>
                    </c:if>
                    <li>
                        <a href="<c:url value="/search"/>" title="Поиск необходимого контента">
                            <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                        </a>
                    </li>
                    <c:choose>
                        <c:when test="${authorized_user ne null}">
                            <li>
                                <a href="<c:url value="/logout"/>" title="Выйти">
                                    <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                                </a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a href="<c:url value="/login"/>" title="Авторизироваться">
                                    <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>
                                </a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
                <c:if test="${authorized_user ne null}">
                    <ul class="hidden-xs dropdown nav navbar-nav">
                        <li><a href="<c:url value="/admin/user/all"/>" title="Весь персонал">Персонал</a></li>
                        <li><a href="<c:url value="/admin/cache"/>" title="Список обьектов в памяти">Кэш</a></li>
                        <li>
                            <a href="<c:url value="/admin/menu"/>"
                               title="Mеню | <c:out value="${authorized_user.name}"/>">
                                <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                            </a>
                        </li>
                        <li>
                            <a href="<c:url value="/admin/messages"/>" title="Сообщения от пользователей">
                                <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
                            </a>
                        </li>
                    </ul>
                </c:if>
                <%-- For mobile device --%>
                <ul class="hidden-sm hidden-md hidden-lg hidden-xl dropdown nav navbar-nav">
                    <li>
                        <a href="<c:url value="${reqmap}/"/>">
                            <span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;Главная
                        </a>
                    </li>
                    <c:choose>
                        <c:when test="${authorized_user ne null}">
                            <li><a href="<c:url value="/admin/section/all"/>">Разделы</a></li>
                            <li><a href="<c:url value="/admin/category/all"/>">Категории</a></li>
                            <li><a href="<c:url value="/admin/article/all"/>">Статьи</a></li>
                        </c:when>
                    </c:choose>
                    <c:if test="${main_company ne null}">
                        <li><a href="<c:url value="${reqmap}/company/main"/>">О компании</a></li>
                        <li><a href="<c:url value="${reqmap}/contacts"/>">Контакты</a></li>
                        <li><a href="<c:url value="${reqmap}/responses"/>">Отзывы</a></li>
                    </c:if>
                    <c:if test="${fn:length(partners) gt 0}">
                        <li><a href="<c:url value="${reqmap}/company/all"/>">Партнеры</a></li>
                    </c:if>
                    <c:choose>
                        <c:when test="${authorized_user ne null}">
                            <li><a href="<c:url value="/admin/user/all"/>">Персонал</a></li>
                            <li><a href="<c:url value="/admin/cache/"/>">Кэш</a></li>
                            <li>
                                <a href="<c:url value="/admin/messages/"/>">
                                    <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>&nbsp;Сообщения
                                </a>
                            </li>
                            <li>
                                <a href="<c:url value="/search"/>">
                                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;Поиск
                                </a>
                            </li>
                            <li>
                                <a href="<c:url value="/admin/menu"/>"
                                   title="Mеню | <c:out value="${authorized_user.name}"/>">
                                    <span class="glyphicon glyphicon-user"
                                          aria-hidden="true"></span>&nbsp;<c:out value="${authorized_user.name}"/>
                                </a>
                            </li>
                            <li>
                                <a href="<c:url value="/logout"/>">
                                    <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>&nbsp;Выйти
                                </a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a href="<c:url value="/search"/>">
                                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;Поиск
                                </a>
                            </li>
                            <li>
                                <a href="<c:url value="/login"/>">
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
