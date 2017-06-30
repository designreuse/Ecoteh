<%--
Users list.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="length" value="${fn:length(users)}"/>
<%-- How many users in the each line --%>
<c:choose>
    <c:when test="${(length % 3 eq 0) or (length % 3 eq 2)}">
        <c:set var="in_line" value="3"/>
    </c:when>
    <c:when test="${(length % 2 eq 0) or (length % 2 eq 1)}">
        <c:set var="in_line" value="2"/>
    </c:when>
    <c:otherwise>
        <c:set var="in_line" value="1"/>
    </c:otherwise>
</c:choose>
<%-- How many users in the last line --%>
<c:set var="last_line" value="${length - length % in_line}"/>
<c:set var="printed_in_line" value="0"/>
<c:set var="printed" value="0"/>

<c:forEach items="${users}" var="user">
    <%-- Users in the last line --%>
    <c:if test="${(last_line ne length) and (printed eq last_line)}">
        <c:set var="in_line" value="${length - last_line}"/>
    </c:if>
    <div class="col-xs-12 col-sm-12
        <c:choose>
        <c:when test="${in_line eq 1}">col-md-12 col-lg-12</c:when>
        <c:when test="${in_line eq 2}">col-md-6 col-lg-6</c:when>
        <c:otherwise>col-md-4 col-lg-4</c:otherwise>
        </c:choose>">
        <div class="text-center">
            <c:choose>
                <c:when test="${not empty user.photo.url}">
                    <a href="<c:url value="${user.photo.url}"/>" rel="lightgallery"
                       title="<c:out value="${user.name}"/>">
                        <img class="img-responsive img-in-list" alt="<c:out value="${user.name}"/>"
                             src="<c:url value="${user.photo.url}"/>"
                             onerror="this.src='<c:url value="/resources/img/static/default_user.png"/>'">
                    </a>
                </c:when>
                <c:otherwise>
                    <img class="img-responsive img-in-list" alt="<c:out value="${user.name}"/>"
                         src="<c:url value="/resources/img/static/default_user.png"/>">
                </c:otherwise>
            </c:choose>
            <h4><b><c:out value="${user.name}"/></b></h4>
            <c:if test="${(authorized_user ne null) and (!user.mailing or !user.validated or user.locked)}">
                <h4>
                    <c:if test="${!user.mailing}">
                        <span class="glyphicon glyphicon-envelope red" aria-hidden="true"
                              title="Рассылка отключена"></span>&nbsp;
                    </c:if>
                    <c:if test="${!user.validated}">
                        <span class="glyphicon glyphicon-eye-close red" aria-hidden="true"
                              title="Не отображается для клиентов"></span>&nbsp;
                    </c:if>
                    <c:if test="${user.locked}">
                        <span class="glyphicon glyphicon-lock red" aria-hidden="true"
                              title="Заблокированый пользователь"></span>
                    </c:if>
                </h4>
            </c:if>
            <h4>
                <c:choose>
                    <c:when test="${not empty user.description}">
                        <c:out value="${user.description}"/>
                    </c:when>
                    <c:when test="${not empty user.role}">
                        <c:out value="${user.role}"/>
                    </c:when>
                </c:choose>
            </h4>
            <c:set var="contacts" value="${user.contacts}"/>
            <c:if test="${not empty contacts.mobilePhone}">
                <h4>
                    <a href="tel:<c:out value="${contacts.mobilePhone}"/>"
                       title="Позвонить на мобильный телефон">
                        <span class="glyphicon glyphicon-phone" aria-hidden="true"></span>
                        &nbsp;<c:out value="${contacts.mobilePhone}"/>
                    </a>
                </h4>
            </c:if>
            <c:if test="${not empty contacts.landlinesPhone}">
                <h4>
                    <a href="tel:<c:out value="${contacts.landlinesPhone}"/>"
                       title="Позвонить на стационарный телефон">
                        <span class="glyphicon glyphicon-phone-alt" aria-hidden="true"></span>
                        &nbsp;<c:out value="${contacts.landlinesPhone}"/>
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
                       title="Написать письмо для &quot;<c:out value="${user.name}"/>&quot;">
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
                <%-- Administrator actions --%>
            <c:if test="${authorized_user ne null}">
                <div class="pad">
                    <a href="<c:url value="/admin/user/edit/${user.url}"/>"
                       title="Редактировать информацию о &quot;<c:out value="${user.name}"/>&quot;">
                        <button class="btn btn-default">
                            <span class="glyphicon glyphicon-edit yellow" aria-hidden="true"></span>
                            &nbsp;Редактировать
                        </button>
                    </a>&nbsp;&nbsp;
                    <a href="<c:url value="/admin/user/delete/${user.url}"/>"
                       title="Удалить &quot;<c:out value="${user.name}"/>&quot;"
                       onclick="if(confirm('Вы точно хотите удалить пользователя &quot;<c:out value="${user.name}"/>&quot;? Удаленные объекты восстановлению не подлежат!')) this.submit; else return false;">
                        <button class="btn btn-default">
                            <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span>
                            &nbsp;Удалить
                        </button>
                    </a>
                </div>
                <br>
            </c:if>
        </div>
    </div>

    <c:set var="printed" value="${printed + 1}"/>
    <c:set var="printed_in_line" value="${printed_in_line + 1}"/>
    <c:if test="${printed_in_line eq in_line}">
        <c:if test="${length gt printed}">
            <div class="hidden-xs hidden-sm col-md-12 col-lg-12">
                <hr>
            </div>
        </c:if>
        <c:set var="printed_in_line" value="0"/>
    </c:if>
</c:forEach>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
