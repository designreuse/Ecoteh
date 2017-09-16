<%--
Form to search

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="<c:url value="/search/result"/>" method="post">
    <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <div class="inner-addon left-addon">
            <span class="glyphicon glyphicon-search"></span>
            <input type="text" class="form-control" name="keywords" value="${keywords}"
                   placeholder="Поиск" required autofocus/>
        </div>
    </div>
    <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <label>
            <b>
                <span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span>
                &nbsp;Где искать?
            </b>
        </label>&nbsp;&nbsp;&nbsp;
        <label title="Искать среди категорий">
            <input type="checkbox" name="content" title="" value="in_categories"
                   <c:if test="${in_categories}">checked</c:if>>&nbsp;Категории
        </label>&nbsp;&nbsp;&nbsp;
        <label title="Искать среди статьей">
            <input type="checkbox" name="content" title="" value="in_articles"
                   <c:if test="${in_articles}">checked</c:if>>&nbsp;Статьи
        </label>&nbsp;&nbsp;&nbsp;
        <label title="Искать среди статьей">
            <input type="checkbox" name="content" title="" value="in_posts"
                   <c:if test="${in_posts}">checked</c:if>>&nbsp;Блог
        </label>&nbsp;&nbsp;&nbsp;
        <label title="Искать среди партнеров">
            <input type="checkbox" name="content" title="" value="in_companies"
                   <c:if test="${in_companies}">checked</c:if>>&nbsp;Партнеры
        </label>&nbsp;&nbsp;&nbsp;
        <label title="Искать везде">
            <input type="checkbox" name="content" title="" value="all"
                   <c:if test="${(all eq null) or all}">checked</c:if>>&nbsp;Везде
        </label>
        <input type="hidden" name="content" value="">
    </div>
    <div class="text-center form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <button type="submit" class="btn btn-default">
            <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>&nbsp;Поиск
        </button>
    </div>
</form>
