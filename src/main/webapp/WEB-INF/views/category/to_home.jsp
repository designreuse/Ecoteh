<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="container">
    <div class="row">
        <div class="box">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <hr>
                <h3 class="text-center">
                    <c:out value="${category.title}"/><c:if test="${length le 0}"> - список пуст!</c:if>
                </h3>
                <hr>
                <%-- Administrator actions --%>
                <c:if test="${authorized_user ne null}">
                    <div class="text-center">
                        <a href="<c:url value="/admin/article/new"/>" title="Добавить новую статью">
                            <button class=" btn btn-default">
                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                &nbsp;Новая&nbsp;статья
                            </button>
                        </a>&nbsp;&nbsp;
                        <a href="<c:url value="/admin/category/edit/${category.url}"/>"
                           title="Редактировать категорию &quot;<c:out value="${category.title}"/>&quot;">
                            <button class="btn btn-default">
                                <span class="glyphicon glyphicon-edit yellow" aria-hidden="true"></span>
                                &nbsp;Редактировать
                            </button>
                        </a>&nbsp;&nbsp;
                        <a href="<c:url value="/admin/category/delete/${category.url}"/>"
                           title="Удалить категорию &quot;<c:out value="${category.title}"/>&quot;"
                           onclick="if(confirm('Вы точно хотите удалить статью категорию &quot;<c:out
                                   value="${category.title}"/>&quot;? Удаленные объекты восстановлению не подлежат!')) this.submit; else return false;">
                            <button class="btn btn-default">
                                <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                                &nbsp;Удалить
                            </button>
                        </a>
                    </div>
                </c:if>
                <%-- Articles list --%>
                <jsp:include page="/WEB-INF/views/article/short_list.jsp"/>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>

<%-- The category description --%>
<%@include file="/WEB-INF/views/category/description.jsp" %>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
