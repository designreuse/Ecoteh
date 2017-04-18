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
        <c:if test="${not empty company.domain}">
            <div class="text-center hidden-xs">
                <a href="http://<c:out value="${company.domain}"/>" target="_blank"
                   title="Сайт компании &quot;${company.title}&quot;">
                    <c:choose>
                        <c:when test="${not empty company.logo.url}">
                            <img class="img-logo" alt="<c:out value="${company.title}"/>"
                                 src="<c:url value="${company.logo.url}"/>"
                                 onerror="this.src='<c:url value="/resources/img/static/default_file.gif"/>'">
                        </c:when>
                        <c:otherwise>
                            <br>
                            <span class="glyphicon glyphicon-globe" aria-hidden="true"></span>
                            &nbsp;&nbsp;<b><c:out value="${company.domain}"/></b>
                            <br>
                        </c:otherwise>
                    </c:choose>
                </a>
            </div>
            <div class="text-center visible-xs">
                <br>
                <a href="http://<c:out value="${company.domain}"/>" target="_blank"
                   title="Сайт компании &quot;${company.title}&quot;">
                    <c:choose>
                        <c:when test="${not empty company.logo.url}">
                            <img class="icon-size" alt="<c:out value="${company.title}"/>"
                                 src="<c:url value="/${company.logo.url}"/>"
                                 onerror="this.src='<c:url value="/resources/img/static/default_file.gif"/>'">
                        </c:when>
                        <c:otherwise>
                            <span class="glyphicon glyphicon-link" aria-hidden="true"></span>
                        </c:otherwise>
                    </c:choose>
                    &nbsp;&nbsp;<b><c:out value="${company.domain}"/></b>
                </a>
                <br>
            </div>
        </c:if>
        <c:if test="${not empty company.address.address}">
            <div class="text-center">
                <br>
                <span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>
                &nbsp;<c:out value="${company.address.address}"/>
                <br><br>
            </div>
        </c:if>
        <c:set var="contacts" value="${company.contacts}"/>
        <c:if test="${not empty contacts.mobilePhone}">
            <div class="text-center">
                <a href="tel:<c:out value="${contacts.mobilePhone}"/>"
                   title="Позвонить на мобильный телефон &quot;${company.title}&quot;">
                    <span class="glyphicon glyphicon-phone" aria-hidden="true"></span>
                    &nbsp;&nbsp;<b><c:out value="${contacts.mobilePhone}"/></b>
                </a>
                <br>
            </div>
        </c:if>
        <c:if test="${not empty contacts.landlinePhone}">
            <div class="text-center">
                <a href="tel:<c:out value="${contacts.landlinePhone}"/>"
                   title="Позвонить на стационарный телефон &quot;${company.title}&quot;">
                    <span class="glyphicon glyphicon-phone-alt" aria-hidden="true"></span>
                    &nbsp;&nbsp;<c:out value="${contacts.landlinePhone}"/>
                </a>
                <br>
            </div>
        </c:if>
        <c:if test="${not empty contacts.fax}">
            <div class="text-center">
                <a href="tel:<c:out value="${contacts.fax}"/>" title="Факс &quot;${company.title}&quot;">
                    <span class="glyphicon glyphicon-print" aria-hidden="true"></span>
                    &nbsp;&nbsp;<c:out value="${contacts.fax}"/>
                </a>
                <br>
            </div>
        </c:if>
        <c:if test="${not empty contacts.email}">
            <div class="text-center">
                <br>
                <a href="mailto:<c:out value="${contacts.email}"/>"
                   title="Написать письмо &quot;${company.title}&quot;" target="_blank">
                    <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
                    &nbsp;<c:out value="${contacts.email}"/>
                </a>
                <br>
            </div>
        </c:if>
        <c:if test="${(not empty contacts.vkontakte) or (not empty contacts.facebook) or
                             (not empty contacts.twitter) or (not empty contacts.skype)}">
            <div class="text-center">
                <br>В социальных сетях:<br>
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
                <br>
            </div>
        </c:if>
        <br>
    </div>
</div>
<%-- Google map with address of the incoming company. --%>
<jsp:include page="/WEB-INF/views/google/map.jsp"/>
<div class="clearfix"></div>
