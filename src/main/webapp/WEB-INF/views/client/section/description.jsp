<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${section.description ne null}">
    <div class="container">
        <div class="row">
            <div class="box">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <hr>
                    <h3 class="text-center"><c:out value="${section.title}"/></h3>
                    <hr>
                    <c:if test="${section.photo ne null}">
                        <a href="<c:url value="/resources/img/${section.photo.url}"/>"
                           rel="lightgallery" title="<c:out value="${section.title}"/>">
                            <img class="img-responsive img-border img-left img-section"
                                 alt="<c:out value="${section.title}"/>"
                                 src="<c:url value="/resources/img/${section.photo.url}"/>">
                        </a>
                        <hr class="visible-xs">
                    </c:if>
                    <c:if test="${!section.validated}">
                        <p class="little">
                            <span class="glyphicon glyphicon-eye-close red" aria-hidden="true"
                                  title="Не отображается для клиентов"></span>&nbsp;
                        </p>
                    </c:if>
                    <p><c:out value="${section.description}"/></p>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</c:if>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
