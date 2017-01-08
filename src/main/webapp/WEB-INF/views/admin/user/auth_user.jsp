<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="text-center">
    <br>
    <c:choose>
        <c:when test="${authorized_user.photo ne null}">
            <a href="<c:url value="/resources/img/${authorized_user.photo.url}"/>" rel="lightgallery">
                <img class="img-responsive img-in-list" alt="<c:out value="${authorized_user.name}"/>"
                     src="<c:url value="/resources/img/${authorized_user.photo.url}"/>">
            </a>
        </c:when>
        <c:otherwise>
            <img class="img-responsive img-in-list" alt="<c:out value="${authorized_user.name}"/>"
                 src="<c:url value="/resources/img/users/default_user.png"/>">
        </c:otherwise>
    </c:choose>
    <h3><c:out value="${authorized_user.name}"/></h3>
    <c:if test="${(authorized_user.id ne null) and (!authorized_user.mailing or !authorized_user.validated or authorized_user.locked)}">
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
            <c:when test="${authorized_user.description ne null}">
                <c:out value="${authorized_user.description}"/>
            </c:when>
            <c:when test="${authorized_user.role ne null}"><c:out value="${authorized_user.role}"/></c:when>
        </c:choose>
    </h4>
    <c:if test="${authorized_user.phone ne null}">
        <h4>
            <a href="tel:<c:out value="${authorized_user.phone}"/>" title="Позвонить на телефон">
                <span class="glyphicon glyphicon-phone"
                      aria-hidden="true"></span>&nbsp;<c:out value="${authorized_user.phone}"/>
            </a>
        </h4>
    </c:if>
    <c:if test="${authorized_user.email ne null}">
        <h4>
            <a href="mailto:<c:out value="${authorized_user.email}"/>" target="_blank"
               title="Написать письмо для &quot;<c:out value="${authorized_user.name}"/>&quot;">
                <span class="glyphicon glyphicon-envelope"
                      aria-hidden="true"></span>&nbsp;<c:out value="${authorized_user.email}"/>
            </a>
        </h4>
    </c:if>
    <c:if test="${authorized_user.vkontakte ne null}">
        <a href="https://vk.com/<c:out value="${authorized_user.vkontakte}"/>" title="Профиль в ВКонтакте"
           target="_blank">
            <span class="fa fa-vk fa-2x vk"></span>
        </a>&nbsp;&nbsp;
    </c:if>
    <c:if test="${authorized_user.facebook ne null}">
        <a href="https://www.facebook.com/<c:out value="${authorized_user.facebook}"/>" title="Профиль в Facebook"
           target="_blank">
            <span class="fa fa-facebook-official fa-2x fb"></span>
        </a>&nbsp;&nbsp;
    </c:if>
    <c:if test="${authorized_user.twitter ne null}">
        <a href="https://twitter.com/<c:out value="${authorized_user.twitter}"/>" title="Профиль в Twitter"
           target="_blank">
            <span class="fa fa-twitter fa-2x tw"></span>
        </a>&nbsp;&nbsp;
    </c:if>
    <c:if test="${authorized_user.skype ne null}">
        <a href="skype:<c:out value="${authorized_user.skype}"/>?call" title="Позвонить в Skype">
            <span class="fa fa-skype fa-2x sk"></span>
        </a>
    </c:if>
    <c:if test="${authorized_user.id ne null}">
        <div class="pad">
            <a href="<c:url value="/admin/user/edit/${authorized_user.url}"/>"
               title="Редактировать информацию о <c:out value="${authorized_user.name}"/>">
                <button class="btn btn-default">
                    <span class="glyphicon glyphicon-edit yellow" aria-hidden="true"></span>&nbsp;Редактировать
                </button>
            </a>
        </div>
    </c:if>
    <br><br>
</div>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
