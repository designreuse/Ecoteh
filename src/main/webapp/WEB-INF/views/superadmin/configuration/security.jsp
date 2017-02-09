<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%! private final static java.util.ResourceBundle RESOURCE = java.util.ResourceBundle.getBundle("security"); %>

<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
    <div class="container">
        <div class="row">
            <div class="box">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <hr>
                    <h3 class="text-center" title="security.properties">
                        Security Configuration
                    </h3>
                    <hr>
                </div>
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <table align="center" border="1" cellpadding="3" cellspacing="0">
                        <tr>
                            <td>
                                <label title="Request prefix for administrators">
                                    Admin Request Prefix
                                </label>
                            </td>
                            <td class="text-center">
                                <label title="request.admin">
                                    <%= RESOURCE.getString("request.admin") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label title="Request prefix for super administrators">
                                    Super Admin Request Prefix
                                </label>
                            </td>
                            <td class="text-center">
                                <label title="request.superadmin">
                                    <%= RESOURCE.getString("request.superadmin") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label title="Request for authorization">
                                    Login Request
                                </label>
                            </td>
                            <td class="text-center">
                                <label title="request.login">
                                    <%= RESOURCE.getString("request.login") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Denied Access Request</td>
                            <td class="text-center">
                                <label title="request.access-denied-page">
                                    <%= RESOURCE.getString("request.access-denied-page") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Default success request</td>
                            <td class="text-center">
                                <label title="request.default-success">
                                    <%= RESOURCE.getString("request.default-success") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label title="It is always use default success request">
                                    Always default success request
                                </label>
                            </td>
                            <td class="text-center">
                                <label title="request.default-success.always">
                                    <%= RESOURCE.getString("request.default-success.always") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label title="Parameter username title">
                                    Username Parameter
                                </label>
                            </td>
                            <td class="text-center">
                                <label title="parameter.username">
                                    <%= RESOURCE.getString("parameter.username") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label title="Parameter password title">
                                    Password parameter
                                </label>
                            </td>
                            <td class="text-center">
                                <label title="parameter.password">
                                    <%= RESOURCE.getString("parameter.password") %>
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
