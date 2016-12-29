<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Calculates period of company work --%>
<%! private String now = new java.text.SimpleDateFormat("y").format(new java.util.Date()); %>
<c:set var="start" value="2016"/>
<c:set var="now" value="<%= now %>"/>
<c:choose>
    <c:when test="${now ne start}"><c:set var="years" value="${start}-${now}"/></c:when>
    <c:otherwise><c:set var="years" value="${start}"/></c:otherwise>
</c:choose>
<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
    <footer>
        <div class="container">
            <div class="row">
                <%-- Left --%>
                <div class="hidden-xs col-sm-6 col-md-4 col-lg-4 col-xl-4">
                    <div class="text-left">
                        <p>
                            <c:if test="${main_company.mobilePhone ne null}">
                                <a href="tel:<c:out value="${main_company.mobilePhone}"/>" title="Мобильный телефон">
                                    <span class="glyphicon glyphicon-phone"
                                          aria-hidden="true"></span>&nbsp;<c:out value="${main_company.mobilePhone}"/>
                                </a><br>
                            </c:if>
                            <c:if test="${main_company.fax ne null}">
                                <a href="tel:<c:out value="${main_company.fax}"/>" title="Телефон / Факс">
                                    <span class="glyphicon glyphicon-print"
                                          aria-hidden="true"></span>&nbsp;<c:out value="${main_company.fax}"/>
                                </a><br>
                            </c:if>
                            <c:if test="${main_company.email ne null}">
                                <a href="mailto:<c:out value="${main_company.email}"/>"
                                   title="Написать письмо" target="_blank">
                                    <span class="glyphicon glyphicon-envelope"
                                          aria-hidden="true"></span>&nbsp;<c:out value="${main_company.email}"/>
                                </a>
                            </c:if>
                        </p>
                    </div>
                </div>
                <%-- Center --%>
                <div class="hidden-xs hidden-sm col-md-4 col-lg-4 col-xl-4">
                    <div class="text-center">
                        <div style="margin-top: 20px">
                            <p>
                                <c:if test="${main_company.vkontakte ne null}">
                                    <a href="https://vk.com/<c:out value="${main_company.vkontakte}"/>"
                                       title="Группа в ВКонтакте" target="_blank">
                                        <span class="fa fa-vk fa-2x vk"></span>
                                    </a>&nbsp;&nbsp;
                                </c:if>
                                <c:if test="${main_company.facebook ne null}">
                                    <a href="https://www.facebook.com/<c:out value="${main_company.facebook}"/>"
                                       title="Группа в Facebook" target="_blank">
                                        <span class="fa fa-facebook-official fa-2x fb"></span>
                                    </a>&nbsp;&nbsp;
                                </c:if>
                                <c:if test="${main_company.twitter ne null}">
                                    <a href="https://twitter.com/<c:out value="${main_company.twitter}"/>"
                                       title="Мы в Twitter" target="_blank">
                                        <span class="fa fa-twitter fa-2x tw"></span>
                                    </a>&nbsp;&nbsp;
                                </c:if>
                                <c:if test="${main_company.skype ne null}">
                                    <a href="skype:<c:out value="${main_company.skype}"/>?call"
                                       title="Позвонить в Skype"><span class="fa fa-skype fa-2x sk"></span></a>
                                </c:if>
                            </p>
                        </div>
                    </div>
                </div>
                <%-- Right --%>
                <div class="hidden-xs col-sm-6 col-md-4 col-lg-4 col-xl-4">
                    <div class="text-right">
                        <p class="copyright">
                            <c:if test="${main_company.domain ne null}">
                                <a href=" http://<c:out value="${main_company.domain}"/>"
                                   title="<c:out value="${main_company.title}"/>">
                                    <c:choose>
                                        <c:when test="${main_company.favicon ne null}">
                                            <img class="icon-size" alt=""
                                                 src="/resources/img/<c:out value="${main_company.favicon.url}"/>">
                                        </c:when>
                                        <c:otherwise>
                                            <span class="glyphicon glyphicon-link" aria-hidden="true"></span>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:out value="${main_company.domain}"/>
                                </a><br>
                            </c:if>
                            <c:out value="${main_company.title}"/>&nbsp;&copy;&nbsp;${years}<br>
                            <a href="https://www.linkedin.com/in/yurii-salimov" target="_blank"
                               title="Разработчик Yurii Salimov | Профиль в Linkedin">
                                <img class="icon-size" src="/resources/img/static/mr_alex_icon.png"
                                     alt="Mr. Alex" title="Mr. Alex">&nbsp;Yurii Salimov
                            </a>
                        </p>
                    </div>
                </div>
                <%-- Only for Mobile devices --%>
                <div class="col-xs-12 hidden-sm hidden-md hidden-lg hidden-xl">
                    <div class="text-center">
                        <p class="copyright">
                            <c:if test="${main_company.mobilePhone ne null}">
                                <a href="tel:<c:out value="${main_company.mobilePhone}"/>" title="Мобильный телефон">
                                    <span class="glyphicon glyphicon-phone" aria-hidden="true"></span>
                                    &nbsp;<c:out value="${main_company.mobilePhone}"/>
                                </a><br>
                            </c:if>
                            <c:if test="${main_company.fax ne null}">
                                <a href="tel:<c:out value="${main_company.fax}"/>" title="Телефон / Факс">
                                    <span class="glyphicon glyphicon-print" aria-hidden="true"></span>
                                    &nbsp;<c:out value="${main_company.fax}"/>
                                </a><br>
                            </c:if>
                            <c:if test="${main_company.email ne null}">
                                <a href="mailto:<c:out value="${main_company.email}"/>"
                                   title="Написать письмо" target="_blank">
                                    <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
                                    &nbsp;<c:out value="${main_company.email}"/>
                                </a><br>
                            </c:if>
                            <c:if test="${main_company.domain ne null}">
                                <a href="http://<c:out value="${main_company.domain}"/>"
                                   title="<c:out value="${main_company.title}"/>">
                                    <c:choose>
                                        <c:when test="${main_company.favicon ne null}">
                                            <img class="icon-size" alt=""
                                                 src="/resources/img/<c:out value="${main_company.favicon.url}"/>">
                                        </c:when>
                                        <c:otherwise>
                                            <span class="glyphicon glyphicon-link" aria-hidden="true"></span>
                                        </c:otherwise>
                                    </c:choose>
                                    &nbsp;<c:out value="${main_company.domain}"/>
                                </a><br>
                            </c:if>
                            <c:out value="${main_company.title}"/>&nbsp;&copy;&nbsp;${years}<br>
                            <a href="https://www.linkedin.com/in/yurii-salimov"
                               title="Разработчик Yurii Salimov | Профиль в Linkedin">
                                <img class="icon-size" src="/resources/img/static/mr_alex_icon.png"
                                     alt="Mr. Alex" title="Mr. Alex">&nbsp;Yurii Salimov
                            </a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </footer>
</div>

<%-- Yurii Salimov (yurii.alex.salimov@gmail.com) --%>
