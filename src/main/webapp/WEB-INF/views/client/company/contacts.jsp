<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-xs-12 col-sm-12 <c:choose>
            <c:when test="${company.googleMaps ne null}">col-md-4 col-lg-4 col-xl-4</c:when>
            <c:otherwise>col-md-12 col-lg-12 col-xl-12</c:otherwise>
        </c:choose>">
    <div class="text-contact">
        <div class="text-center hidden-xs">
            <a href="http://<c:out value="${company.domain}"/>" title="Сайт компании &quot;${company.title}&quot;"
               target="_blank">
                <c:choose>
                    <c:when test="${company.logo ne null}">
                        <img class="img-logo" alt="<c:out value="${company.title}"/>"
                             src="/resources/img/<c:out value="${company.logo.url}"/>">
                    </c:when>
                    <c:when test="${company.favicon ne null}">
                        <br><img class="icon-size" alt="<c:out value="${company.title}"/>"
                                 src="/resources/img/<c:out value="${company.favicon.url}"/>">
                        &nbsp;&nbsp;<b><c:out value="${company.domain}"/></b><br>
                    </c:when>
                    <c:otherwise>
                        <br><span class="glyphicon glyphicon-link"
                                  aria-hidden="true"></span>&nbsp;&nbsp;<b><c:out value="${company.domain}"/></b><br>
                    </c:otherwise>
                </c:choose>
            </a>
        </div>
        <div class="text-center visible-xs">
            <br>
            <a href="http://<c:out value="${company.domain}"/>" title="Сайт компании &quot;${company.title}&quot;"
               target="_blank">
                <c:choose>
                    <c:when test="${company.favicon ne null}">
                        <img class="icon-size" alt="<c:out value="${company.title}"/>"
                             src="/resources/img/<c:out value="${company.favicon.url}"/>">
                    </c:when>
                    <c:otherwise><span class="glyphicon glyphicon-link" aria-hidden="true"></span></c:otherwise>
                </c:choose>
                &nbsp;&nbsp;<b><c:out value="${company.domain}"/></b>
            </a>
            <br>
        </div>
        <c:if test="${company.address ne null}">
            <div class="text-center">
                <br><span class="glyphicon glyphicon-map-marker"
                          aria-hidden="true"></span>&nbsp;&nbsp;<c:out value="${company.address}"/><br><br>
            </div>
        </c:if>
        <c:if test="${company.mobilePhone ne null}">
            <div class="text-center">
                <a href="tel:<c:out value="${company.landlinePhone}"/>"
                   title="Позвонить на мобильный телефон &quot;${company.title}&quot;">
                    <span class="glyphicon glyphicon-phone"
                          aria-hidden="true"></span>&nbsp;&nbsp;<b><c:out value="${company.mobilePhone}"/></b>
                </a><br>
            </div>
        </c:if>
        <c:if test="${company.landlinePhone ne null}">
            <div class="text-center">
                <a href="tel:<c:out value="${company.landlinePhone}"/>"
                   title="Позвонить на стационарный телефон &quot;${company.title}&quot;">
                    <span class="glyphicon glyphicon-phone-alt"
                          aria-hidden="true"></span>&nbsp;&nbsp;<c:out value="${company.landlinePhone}"/>
                </a><br>
            </div>
        </c:if>
        <c:if test="${company.fax ne null}">
            <div class="text-center">
                <a href="tel:<c:out value="${company.fax}"/>" title="Факс &quot;${company.title}&quot;">
                    <span class="glyphicon glyphicon-print"
                          aria-hidden="true"></span>&nbsp;&nbsp;<c:out value="${company.fax}"/>
                </a><br>
            </div>
        </c:if>
        <c:if test="${company.email ne null}">
            <div class="text-center">
                <br>
                <a href="mailto:<c:out value="${company.email}"/>" title="Написать письмо &quot;${company.title}&quot;"
                   target="_blank">
                    <span class="glyphicon glyphicon-envelope"
                          aria-hidden="true"></span>&nbsp;&nbsp;<c:out value="${company.email}"/>
                </a><br>
            </div>
        </c:if>
        <c:if test="${(company.vkontakte ne null) or (company.facebook ne null) or
                             (company.twitter ne null) or (company.skype ne null)}">
            <div class="text-center">
                <br>В социальных сетях:<br>
                <c:if test="${company.vkontakte ne null}">
                    <a href="https://vk.com/<c:out value="${company.vkontakte}"/>"
                       title="Группа &quot;${company.title}&quot; в ВКонтакте"
                       target="_blank"><span class="fa fa-vk fa-2x vk"></span></a>&nbsp;&nbsp;
                </c:if>
                <c:if test="${company.facebook ne null}">
                    <a href="https://www.facebook.com/<c:out value="${company.facebook}"/>"
                       title="Группа &quot;${company.title}&quot; в Facebook"
                       target="_blank"><span class="fa fa-facebook-official fa-2x fb"></span></a>&nbsp;&nbsp;
                </c:if>
                <c:if test="${company.twitter ne null}">
                    <a href="https://twitter.com/<c:out value="${company.twitter}"/>"
                       title="&quot;${company.title}&quot; в Twitter"
                       target="_blank"><span class="fa fa-twitter fa-2x tw"></span></a>&nbsp;&nbsp;
                </c:if>
                <c:if test="${company.skype ne null}">
                    <a href="skype:<c:out value="${company.skype}"/>?call"
                       title="Позвонить &quot;${company.title}&quot; в Skype">
                        <span class="fa fa-skype fa-2x sk"></span>
                    </a>
                </c:if>
                <br>
            </div>
        </c:if>
        <br>
    </div>
</div>
<%-- Google Maps --%>
<c:if test="${company.googleMaps ne null}">
    <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8 col-xl-8">
        <iframe src="${company.googleMaps}" allowfullscreen
                width="100%" height="500px" frameborder="0" style="border:0"></iframe>
    </div>
</c:if>
<div class="clearfix"></div>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
