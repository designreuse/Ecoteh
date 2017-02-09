<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%! private final static java.util.ResourceBundle RESOURCE = java.util.ResourceBundle.getBundle("captcha"); %>

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
                                    <%= RESOURCE.getString("captcha.user-agent") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Accept language</td>
                            <td class="text-center">
                                <label title="captcha.accept-language">
                                    <%= RESOURCE.getString("captcha.accept-language") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Do output</td>
                            <td class="text-center">
                                <label title="captcha.do-output">
                                    <%= RESOURCE.getString("captcha.do-output") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>URL</td>
                            <td class="text-center">
                                <label title="captcha.url">
                                    <%= RESOURCE.getString("captcha.url") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Server key</td>
                            <td class="text-center">
                                <label title="captcha.server-key">
                                    <%= RESOURCE.getString("captcha.server-key") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Client key</td>
                            <td class="text-center">
                                <label title="captcha.client-key">
                                    <%= RESOURCE.getString("captcha.client-key") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Request parameter</td>
                            <td class="text-center">
                                <label title="captcha.parameter">
                                    <%= RESOURCE.getString("captcha.parameter") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Header parameter</td>
                            <td class="text-center">
                                <label title="captcha.header">
                                    <%= RESOURCE.getString("captcha.header") %>
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
