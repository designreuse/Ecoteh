<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${category.description ne null}">
    <div class="container">
        <div class="row">
            <div class="box">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <hr>
                    <h3 class="text-center"><c:out value="${category.title}"/></h3>
                    <hr>
                    <c:if test="${category.photo ne null}">
                        <a href="<c:url value="/resources/img/${category.photo.url}"/>"
                           rel="lightgallery" title="<c:out value="${category.title}"/>">
                            <img class="img-responsive img-border img-left img-section"
                                 alt="<c:out value="${category.title}"/>"
                                 src="<c:url value="/resources/img/${category.photo.url}"/>">
                        </a>
                        <hr class="visible-xs">
                    </c:if>
                    <c:if test="${!category.validated}">
                        <p class="little">
                            <span class="glyphicon glyphicon-eye-close red" aria-hidden="true"
                                  title="Не отображается для клиентов"></span>&nbsp;
                        </p>
                    </c:if>
                    <p><c:out value="${category.description}"/></p>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</c:if>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
