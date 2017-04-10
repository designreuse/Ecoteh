<%--
Footer of the site, it contains the contact
details of the main company.

Yurii Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- Calculates period of company work --%>
<jsp:useBean id="utilDate" class="java.util.Date" scope="session"/>
<fmt:formatDate var="now" value="${utilDate}" pattern="y" scope="session"/>
<c:set var="start" value="2016" scope="session"/>
<c:choose>
    <c:when test="${now ne start}">
        <c:set var="years" value="${start}-${now}" scope="session"/>
    </c:when>
    <c:otherwise>
        <c:set var="years" value="${start}" scope="session"/>
    </c:otherwise>
</c:choose>

<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
    <footer>
        <div class="container">
            <div class="row">
                <%-- Left --%>
                <div class="hidden-xs col-sm-6 col-md-4 col-lg-4">
                    <div class="text-left">
                        <p>
                            <c:if test="${not empty main_company.contacts.mobilePhone}">
                                <a href="tel:<c:out value="${main_company.contacts.mobilePhone}"/>"
                                   title="Мобильный телефон">
                                    <span class="glyphicon glyphicon-phone" aria-hidden="true"></span>
                                    &nbsp;<c:out value="${main_company.contacts.mobilePhone}"/>
                                </a>
                                <br>
                            </c:if>
                            <c:if test="${not empty main_company.contacts.fax}">
                                <a href="tel:<c:out value="${main_company.contacts.fax}"/>" title="Телефон / Факс">
                                    <span class="glyphicon glyphicon-print" aria-hidden="true"></span>
                                    &nbsp;<c:out value="${main_company.contacts.fax}"/>
                                </a>
                                <br>
                            </c:if>
                            <c:if test="${not empty main_company.contacts.email}">
                                <a href="mailto:<c:out value="${main_company.contacts.email}"/>"
                                   title="Написать письмо" target="_blank">
                                    <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
                                    &nbsp;<c:out value="${main_company.contacts.email}"/>
                                </a>
                            </c:if>
                        </p>
                    </div>
                </div>
                <%-- Center --%>
                <div class="hidden-xs hidden-sm col-md-4 col-lg-4">
                    <div class="text-center">
                        <div style="margin-top: 20px">
                            <p>
                                <c:if test="${not empty main_company.contacts.vkontakte}">
                                    <a href="https://vk.com/<c:out value="${main_company.contacts.vkontakte}"/>"
                                       title="Группа в ВКонтакте" target="_blank">
                                        <span class="fa fa-vk fa-2x vk"></span>
                                    </a>&nbsp;&nbsp;
                                </c:if>
                                <c:if test="${not empty main_company.contacts.facebook}">
                                    <a href="https://www.facebook.com/<c:out value="${main_company.contacts.facebook}"/>"
                                       title="Группа в Facebook" target="_blank">
                                        <span class="fa fa-facebook-official fa-2x fb"></span>
                                    </a>&nbsp;&nbsp;
                                </c:if>
                                <c:if test="${not empty main_company.contacts.twitter}">
                                    <a href="https://twitter.com/<c:out value="${main_company.contacts.twitter}"/>"
                                       title="Мы в Twitter" target="_blank">
                                        <span class="fa fa-twitter fa-2x tw"></span>
                                    </a>&nbsp;&nbsp;
                                </c:if>
                                <c:if test="${not empty main_company.contacts.skype}">
                                    <a href="skype:<c:out value="${main_company.contacts.skype}"/>?call"
                                       title="Позвонить в Skype">
                                        <span class="fa fa-skype fa-2x sk"></span>
                                    </a>
                                </c:if>
                            </p>
                        </div>
                    </div>
                </div>
                <%-- Right --%>
                <div class="hidden-xs col-sm-6 col-md-4 col-lg-4">
                    <div class="text-right">
                        <p class="copyright">
                            <c:out value="${main_company.title}"/>&nbsp;&copy;&nbsp;${years}<br>
                            <c:if test="${not empty main_company.domain}">
                                <a href=" http://<c:out value="${main_company.domain}"/>"
                                   title="<c:out value="${main_company.title}"/>">
                                    <c:choose>
                                        <c:when test="${not empty favicon.url}">
                                            <img src="<c:url value="${favicon.url}"/>" class="icon-size" alt="">
                                        </c:when>
                                        <c:otherwise>
                                            <span class="glyphicon glyphicon-link" aria-hidden="true"></span>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:out value="${main_company.domain}"/>
                                </a><br>
                            </c:if>
                            <a href="https://www.linkedin.com/in/yurii-salimov" target="_blank"
                               title="Разработчик Yurii Salimov | Профиль в Linkedin">
                                <img class="icon-size" src="<c:url value="/resources/img/static/mr_alex.png"/>"
                                     alt="Mr. Alex" title="Mr. Alex">&nbsp;Yurii Salimov
                            </a>
                        </p>
                    </div>
                </div>
                <%-- Only for Mobile devices --%>
                <div class="col-xs-12 hidden-sm hidden-md hidden-lg hidden-xl">
                    <div class="text-center">
                        <p class="copyright">
                            <c:out value="${main_company.title}"/>&nbsp;&copy;&nbsp;${years}<br>
                            <c:if test="${not empty main_company.contacts.mobilePhone}">
                                <a href="tel:<c:out value="${main_company.contacts.mobilePhone}"/>"
                                   title="Мобильный телефон">
                                    <span class="glyphicon glyphicon-phone" aria-hidden="true"></span>
                                    &nbsp;<c:out value="${main_company.contacts.mobilePhone}"/>
                                </a>
                                <br>
                            </c:if>
                            <c:if test="${not empty main_company.contacts.fax}">
                                <a href="tel:<c:out value="${main_company.contacts.fax}"/>" title="Телефон / Факс">
                                    <span class="glyphicon glyphicon-print" aria-hidden="true"></span>
                                    &nbsp;<c:out value="${main_company.contacts.fax}"/>
                                </a>
                                <br>
                            </c:if>
                            <c:if test="${not empty main_company.contacts.email}">
                                <a href="mailto:<c:out value="${main_company.contacts.email}"/>"
                                   title="Написать письмо" target="_blank">
                                    <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
                                    &nbsp;<c:out value="${main_company.contacts.email}"/>
                                </a>
                                <br>
                            </c:if>
                            <c:if test="${not empty main_company.domain}">
                                <a href="http://<c:out value="${main_company.domain}"/>"
                                   title="<c:out value="${main_company.title}"/>">
                                    <c:choose>
                                        <c:when test="${not empty favicon.url}">
                                            <img src="<c:url value="${favicon.url}"/>" class="icon-size" alt="">
                                        </c:when>
                                        <c:otherwise>
                                            <span class="glyphicon glyphicon-link" aria-hidden="true"></span>
                                        </c:otherwise>
                                    </c:choose>
                                    &nbsp;<c:out value="${main_company.domain}"/>
                                </a>
                                <br>
                            </c:if>
                            <a href="https://www.linkedin.com/in/yurii-salimov"
                               title="Разработчик Yurii Salimov | Профиль в Linkedin">
                                <img src="<c:url value="/resources/img/static/mr_alex.png"/>" class="icon-size"
                                     alt="Mr. Alex" title="Mr. Alex">&nbsp;Yurii Salimov
                            </a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </footer>
</div>
