<%--
Page with information about authorized user.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="text-center">
    <br>
    <c:choose>
        <c:when test="${not empty authorized_user.photo.url}">
            <a href="<c:url value="${authorized_user.photo.url}"/>" rel="lightgallery"
               title="<c:out value="${user.name}"/>">
                <img class="img-responsive img-in-list" alt="<c:out value="${authorized_user.name}"/>"
                     src="<c:url value="${authorized_user.photo.url}"/>"
                     onerror="this.src='<c:url value="/resources/img/static/default_user.png"/>'">
            </a>
        </c:when>
        <c:otherwise>
            <img class="img-responsive img-in-list" alt="<c:out value="${authorized_user.name}"/>"
                 src="<c:url value="/resources/img/static/default_user.png"/>">
        </c:otherwise>
    </c:choose>
    <h3><c:out value="${authorized_user.name}"/></h3>
    <c:if test="${(!authorized_user.mailing or !authorized_user.validated or authorized_user.locked)}">
        <h4>
            <c:if test="${!authorized_user.mailing}">
                <span class="glyphicon glyphicon-envelope red" aria-hidden="true" title="Рассылка отключена"></span>
            </c:if>&nbsp;
            <c:if test="${!authorized_user.validated}">
                <span class="glyphicon glyphicon-eye-close red" aria-hidden="true"
                      title="Не отображается для клиентов"></span>
            </c:if>&nbsp;
            <c:if test="${authorized_user.locked}">
                <span class="glyphicon glyphicon-lock red" aria-hidden="true"
                      title="Заблокированый пользователь"></span>
            </c:if>
        </h4>
    </c:if>
    <h4>
        <c:choose>
            <c:when test="${not empty authorized_user.description}">
                <c:out value="${authorized_user.description}"/>
            </c:when>
            <c:when test="${authorized_user.role ne null}">
                <c:out value="${authorized_user.role}"/>
            </c:when>
        </c:choose>
    </h4>
    <c:set var="contacts" value="${authorized_user.contacts}"/>
    <c:if test="${not empty contacts.mobilePhone}">
        <h4>
            <a href="tel:<c:out value="${contacts.mobilePhone}"/>"
               title="Позвонить на мобильный телефон">
                <span class="glyphicon glyphicon-phone" aria-hidden="true"></span>
                &nbsp;<c:out value="${contacts.mobilePhone}"/>
            </a>
        </h4>
    </c:if>
    <c:if test="${not empty contacts.landlinePhone}">
        <h4>
            <a href="tel:<c:out value="${contacts.landlinePhone}"/>"
               title="Позвонить на стационарный телефон">
                <span class="glyphicon glyphicon-phone-alt" aria-hidden="true"></span>
                &nbsp;<c:out value="${contacts.landlinePhone}"/>
            </a>
        </h4>
    </c:if>
    <c:if test="${not empty contacts.fax}">
        <h4>
            <a href="tel:<c:out value="${contacts.fax}"/>" title="Факс">
                <span class="glyphicon glyphicon-print" aria-hidden="true"></span>
                &nbsp;<c:out value="${contacts.fax}"/>
            </a>
        </h4>
    </c:if>
    <c:if test="${not empty contacts.email}">
        <h4>
            <a href="mailto:<c:out value="${contacts.email}"/>" target="_blank"
               title="Написать письмо для &quot;<c:out value="${authorized_user.name}"/>&quot;">
                <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
                &nbsp;<c:out value="${contacts.email}"/>
            </a>
        </h4>
    </c:if>
    <c:if test="${not empty contacts.vkontakte}">
        <a href="<c:out value="${contacts.vkontakte}"/>" title="Профиль в ВКонтакте" target="_blank">
            <span class="fa fa-vk fa-2x"></span>
        </a>&nbsp;&nbsp;
    </c:if>
    <c:if test="${not empty contacts.facebook}">
        <a href="<c:out value="${contacts.facebook}"/>" title="Профиль в Facebook" target="_blank">
            <span class="fa fa-facebook-official fa-2x"></span>
        </a>&nbsp;&nbsp;
    </c:if>
    <c:if test="${not empty contacts.twitter}">
        <a href="<c:out value="${contacts.twitter}"/>" title="Профиль в Twitter" target="_blank">
            <span class="fa fa-twitter fa-2x"></span>
        </a>&nbsp;&nbsp;
    </c:if>
    <c:if test="${not empty contacts.skype}">
        <a href="skype:<c:out value="${contacts.skype}"/>?call" title="Позвонить в Skype">
            <span class="fa fa-skype fa-2x"></span>
        </a>
    </c:if>
    <c:if test="${authorized_user.id ne '0'}">
        <div class="pad">
            <a href="<c:url value="/admin/user/edit/${authorized_user.url}"/>"
               title="Редактировать информацию о <c:out value="${authorized_user.name}"/>">
                <button class="btn btn-default">
                    <span class="glyphicon glyphicon-edit yellow" aria-hidden="true"></span>
                    &nbsp;Редактировать
                </button>
            </a>
        </div>
    </c:if>
    <br><br>
</div>
