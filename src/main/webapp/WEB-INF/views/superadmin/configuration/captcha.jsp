<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="captcha" var="captcha"/>

<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
    <div class="container">
        <div class="row">
            <div class="box">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <hr>
                    <h3 class="text-center" title="captcha.properties">
                        Google reCAPTCHA Configuration
                    </h3>
                    <hr>
                </div>
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <table align="center" border="1" cellpadding="3" cellspacing="0">
                        <tr>
                            <td>User-agent</td>
                            <td class="text-center">
                                <label title="captcha.user-agent">
                                    <fmt:message bundle="${captcha}" key="captcha.user-agent"/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Accept language</td>
                            <td class="text-center">
                                <label title="captcha.accept-language">
                                    <fmt:message bundle="${captcha}" key="captcha.accept-language"/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Do output</td>
                            <td class="text-center">
                                <label title="captcha.do-output">
                                    <fmt:message bundle="${captcha}" key="captcha.do-output"/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>URL</td>
                            <td class="text-center">
                                <label title="captcha.url">
                                    <fmt:message bundle="${captcha}" key="captcha.url"/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Server key</td>
                            <td class="text-center">
                                <label title="captcha.server-key">
                                    <fmt:message bundle="${captcha}" key="captcha.server-key"/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Client key</td>
                            <td class="text-center">
                                <label title="captcha.client-key">
                                    <fmt:message bundle="${captcha}" key="captcha.client-key"/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Request parameter</td>
                            <td class="text-center">
                                <label title="captcha.parameter">
                                    <fmt:message bundle="${captcha}" key="captcha.parameter"/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Header parameter</td>
                            <td class="text-center">
                                <label title="captcha.header">
                                    <fmt:message bundle="${captcha}" key="captcha.header"/>
                                </label>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</div>

<%-- Yuriy Salimov (yuriy.alex.salimov@gmail.com) --%>
