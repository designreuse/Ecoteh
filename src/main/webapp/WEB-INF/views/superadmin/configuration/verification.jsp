<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%! private final static java.util.ResourceBundle RESOURCE = java.util.ResourceBundle.getBundle("verification"); %>

<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
    <div class="container">
        <div class="row">
            <div class="box">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <hr>
                    <h3 class="text-center" title="verification.properties">
                        Site Verification Configuration
                    </h3>
                    <hr>
                </div>
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <table align="center" border="1" cellpadding="3" cellspacing="0">
                        <tr>
                            <td>Google Verification</td>
                            <td class="text-center">
                                <label title="google.verification">
                                    <%= RESOURCE.getString("google.verification") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Yandex Verification</td>
                            <td class="text-center">
                                <label title="yandex.verification">
                                    <%= RESOURCE.getString("yandex.verification") %>
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
