<%--
Description of the incoming category.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty category.description}">
    <div class="container">
        <div class="row">
            <div class="box">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <h3 class="text-center">
                        <c:out value="${category.title}"/>
                    </h3>
                    <c:if test="${!category.validated}">
                        <p class="little">
                            <span class="glyphicon glyphicon-eye-close red" aria-hidden="true"
                                  title="Не отображается для клиентов"></span>&nbsp;
                        </p>
                    </c:if>
                    <p>${category.description}</p>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</c:if>
