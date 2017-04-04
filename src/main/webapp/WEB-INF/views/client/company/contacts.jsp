<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-xs-12 col-sm-12 <c:choose>
            <c:when test="${company.address.googleMaps ne ''}">col-md-4 col-lg-4</c:when>
            <c:otherwise>col-md-12 col-lg-12</c:otherwise>
        </c:choose>">
    <div class="text-contact">
        <c:if test="${company.domain ne ''}">
            <div class="text-center hidden-xs">
                <a href="http://<c:out value="${company.domain}"/>" target="_blank"
                   title="Сайт компании &quot;${company.title}&quot;">
                    <c:choose>
                        <c:when test="${company.logo.url ne ''}">
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
                        <c:when test="${company.logo.url ne ''}">
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
        <c:if test="${company.address.address ne ''}">
            <div class="text-center">
                <br>
                <span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>
                &nbsp;<c:out value="${company.address.address}"/>
                <br><br>
            </div>
        </c:if>
        <c:if test="${company.contacts.mobilePhone ne ''}">
            <div class="text-center">
                <a href="tel:<c:out value="${company.contacts.mobilePhone}"/>"
                   title="Позвонить на мобильный телефон &quot;${company.title}&quot;">
                    <span class="glyphicon glyphicon-phone" aria-hidden="true"></span>
                    &nbsp;&nbsp;<b><c:out value="${company.contacts.mobilePhone}"/></b>
                </a><br>
            </div>
        </c:if>
        <c:if test="${company.contacts.landlinePhone ne ''}">
            <div class="text-center">
                <a href="tel:<c:out value="${company.contacts.landlinePhone}"/>"
                   title="Позвонить на стационарный телефон &quot;${company.title}&quot;">
                    <span class="glyphicon glyphicon-phone-alt" aria-hidden="true"></span>
                    &nbsp;&nbsp;<c:out value="${company.contacts.landlinePhone}"/>
                </a><br>
            </div>
        </c:if>
        <c:if test="${company.contacts.fax ne ''}">
            <div class="text-center">
                <a href="tel:<c:out value="${company.contacts.fax}"/>" title="Факс &quot;${company.title}&quot;">
                    <span class="glyphicon glyphicon-print" aria-hidden="true"></span>
                    &nbsp;&nbsp;<c:out value="${company.contacts.fax}"/>
                </a><br>
            </div>
        </c:if>
        <c:if test="${company.contacts.email ne ''}">
            <div class="text-center">
                <br>
                <a href="mailto:<c:out value="${company.contacts.email}"/>"
                   title="Написать письмо &quot;${company.title}&quot;" target="_blank">
                    <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
                    &nbsp;<c:out value="${company.contacts.email}"/>
                </a><br>
            </div>
        </c:if>
        <c:if test="${(company.contacts.vkontakte ne '') or (company.contacts.facebook ne '') or
                             (company.contacts.twitter ne '') or (company.contacts.skype ne '')}">
            <div class="text-center">
                <br>В социальных сетях:<br>
                <c:if test="${company.contacts.vkontakte ne ''}">
                    <a href="<c:out value="${company.contacts.vkontakte}"/>" target="_blank"
                       title="Группа &quot;${company.title}&quot; в ВКонтакте">
                        <span class="fa fa-vk fa-2x"></span>
                    </a>&nbsp;&nbsp;
                </c:if>
                <c:if test="${company.contacts.facebook ne ''}">
                    <a href="<c:out value="${company.contacts.facebook}"/>" target="_blank"
                       title="Группа &quot;${company.title}&quot; в Facebook">
                        <span class="fa fa-facebook-official fa-2x"></span>
                    </a>&nbsp;&nbsp;
                </c:if>
                <c:if test="${company.contacts.twitter ne ''}">
                    <a href="<c:out value="${company.contacts.twitter}"/>" target="_blank"
                       title="&quot;${company.title}&quot; в Twitter">
                        <span class="fa fa-twitter fa-2x"></span>
                    </a>&nbsp;&nbsp;
                </c:if>
                <c:if test="${company.contacts.skype ne ''}">
                    <a href="skype:<c:out value="${company.contacts.skype}"/>?call"
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
<jsp:include page="/WEB-INF/views/google/map.jsp"/>
<div class="clearfix"></div>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
