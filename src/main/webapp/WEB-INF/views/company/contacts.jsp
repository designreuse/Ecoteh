<%--
Contact information of the incoming company.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-xs-12 col-sm-12 <c:choose>
            <c:when test="${not empty company.address.googleMaps}">col-md-4 col-lg-4</c:when>
            <c:otherwise>col-md-12 col-lg-12</c:otherwise>
        </c:choose>">
    <div class="text-contact">
        <div class="text-center">
            <c:choose>
                <c:when test="${not empty company.domain}">
                    <a href="http://<c:out value="${company.domain}"/>" target="_blank"
                       title="Сайт компании &quot;<c:out value="${company.title}"/>&quot;">
                        <c:choose>
                            <c:when test="${not empty company.logo.url}">
                                <img class="img-logo" alt="<c:out value="${company.title}"/>"
                                     src="<c:url value="${company.logo.url}"/>"
                                     onerror="this.src='<c:url value="/resources/img/static/default_file.gif"/>'">
                            </c:when>
                            <c:otherwise>
                                <b><c:out value="${company.title}"/></b>
                            </c:otherwise>
                        </c:choose>
                    </a>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${not empty company.logo.url}">
                            <img class="img-logo" alt="<c:out value="${company.title}"/>"
                                 src="<c:url value="${company.logo.url}"/>"
                                 onerror="this.src='<c:url value="/resources/img/static/default_file.gif"/>'">
                        </c:when>
                        <c:otherwise>
                            <b><c:out value="${company.title}"/></b>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </div>
        <br>
        <c:if test="${not empty company.address.postAddress}">
            <div class="text-center">
                <span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>
                &nbsp;<c:out value="${company.address.postAddress}"/>
            </div>
        </c:if>
        <br>
        <c:if test="${company.workTimeFrom ne company.workTimeTo}">
            <div class="text-center">
                <span class="glyphicon glyphicon-time" aria-hidden="true"></span>
                &nbsp;Пн&nbsp;-&nbsp;Пт,&nbsp;
                <c:out value="${company.workTimeFrom}"/>&nbsp;-&nbsp;<c:out value="${company.workTimeTo}"/>
            </div>
            <br>
        </c:if>
        <c:set var="contacts" value="${company.contacts}"/>
        <c:if test="${not empty contacts.mobilePhone}">
            <div class="text-center">
                <a href="tel:<c:out value="${contacts.mobilePhone}"/>"
                   title="Позвонить на мобильный телефон &quot;${company.title}&quot;">
                    <span class="glyphicon glyphicon-phone" aria-hidden="true"></span>
                    &nbsp;<b><c:out value="${contacts.mobilePhone}"/></b>
                </a>
            </div>
        </c:if>
        <c:if test="${not empty contacts.landlinesPhone}">
            <div class="text-center">
                <a href="tel:<c:out value="${contacts.landlinesPhone}"/>"
                   title="Позвонить на стационарный телефон &quot;${company.title}&quot;">
                    <span class="glyphicon glyphicon-phone-alt" aria-hidden="true"></span>
                    &nbsp;<c:out value="${contacts.landlinesPhone}"/>
                </a>
            </div>
        </c:if>
        <c:if test="${not empty contacts.fax}">
            <div class="text-center">
                <a href="tel:<c:out value="${contacts.fax}"/>" title="Факс &quot;${company.title}&quot;">
                    <span class="glyphicon glyphicon-print" aria-hidden="true"></span>
                    &nbsp;<c:out value="${contacts.fax}"/>
                </a>
            </div>
        </c:if>
        <br>
        <c:if test="${not empty contacts.email}">
            <div class="text-center">
                <a href="mailto:<c:out value="${contacts.email}"/>"
                   title="Написать письмо &quot;${company.title}&quot;" target="_blank">
                    <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
                    &nbsp;<c:out value="${contacts.email}"/>
                </a>
            </div>
        </c:if>
        <c:if test="${not empty company.domain}">
            <div class="text-center">
                <a href="http://<c:out value="${company.domain}"/>" target="_blank"
                   title="Сайт компании &quot;${company.title}&quot;">
                    <span class="glyphicon glyphicon-link" aria-hidden="true"></span>
                    &nbsp;<c:out value="${company.domain}"/>
                </a>
            </div>
        </c:if>
        <br>
        <c:if test="${(not empty contacts.vkontakte) or (not empty contacts.facebook) or
                             (not empty contacts.twitter) or (not empty contacts.skype)}">
            <div class="text-center">
                <c:if test="${not empty contacts.vkontakte}">
                    <a href="<c:out value="${contacts.vkontakte}"/>" target="_blank"
                       title="Группа &quot;${company.title}&quot; в ВКонтакте">
                        <span class="fa fa-vk fa-2x"></span>
                    </a>&nbsp;&nbsp;
                </c:if>
                <c:if test="${not empty contacts.facebook}">
                    <a href="<c:out value="${contacts.facebook}"/>" target="_blank"
                       title="Группа &quot;${company.title}&quot; в Facebook">
                        <span class="fa fa-facebook-official fa-2x"></span>
                    </a>&nbsp;&nbsp;
                </c:if>
                <c:if test="${not empty contacts.twitter}">
                    <a href="<c:out value="${contacts.twitter}"/>" target="_blank"
                       title="&quot;${company.title}&quot; в Twitter">
                        <span class="fa fa-twitter fa-2x"></span>
                    </a>&nbsp;&nbsp;
                </c:if>
                <c:if test="${not empty contacts.skype}">
                    <a href="skype:<c:out value="${contacts.skype}"/>?call"
                       title="Позвонить &quot;${company.title}&quot; в Skype">
                        <span class="fa fa-skype fa-2x"></span>
                    </a>
                </c:if>
            </div>
        </c:if>
        <br>
    </div>
</div>
<%-- Google map with address of the incoming company. --%>
<%@include file="/WEB-INF/views/google/map.jsp" %>
<div class="clearfix"></div>
