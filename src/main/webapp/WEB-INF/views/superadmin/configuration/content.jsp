<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%! private final static java.util.ResourceBundle RESOURCE = java.util.ResourceBundle.getBundle("content"); %>

<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
    <div class="container">
        <div class="row">
            <div class="box">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <hr>
                    <h3 class="text-center" title="content.properties">
                        Content Configuration
                    </h3>
                    <hr>
                </div>
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <table align="center" border="1" cellpadding="3" cellspacing="0">
                        <tr>
                            <td>The Apache Catalina home path</td>
                            <td class="text-center">
                                <label title="apache.catalina.home">
                                    <%= RESOURCE.getString("apache.catalina.home") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Tomcat server port</td>
                            <td class="text-center">
                                <label title="tomcat.server.port">
                                    <%= RESOURCE.getString("tomcat.server.port") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Project directory name</td>
                            <td class="text-center">
                                <label title="project.directory">
                                    <%= RESOURCE.getString("project.directory") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Content Type encoding</td>
                            <td class="text-center">
                                <label title="view.type">
                                    <%= RESOURCE.getString("view.type") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label title="Path relative to the project.directory">
                                    View name prefix
                                </label>
                            </td>
                            <td class="text-center">
                                <label title="view.name-prefix">
                                    <%= RESOURCE.getString("view.name-prefix") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>View name suffix</td>
                            <td class="text-center">
                                <label title="view.name-suffix">
                                    <%= RESOURCE.getString("view.name-suffix") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Context beans as attributes</td>
                            <td class="text-center">
                                <label title="view.expose_beans_as_attributes">
                                    <%= RESOURCE.getString("view.expose_beans_as_attributes") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label title="Path relative to the view.name-prefix">
                                    URL of resources
                                </label>
                            </td>
                            <td class="text-center">
                                <label title="resources.url">
                                    <%= RESOURCE.getString("resources.url") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label title="Path relative to the view.name-prefix">
                                    Location of resources
                                </label>
                            </td>
                            <td class="text-center">
                                <label title="resources.location">
                                    <%= RESOURCE.getString("resources.location") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Maximum file size (bytes)</td>
                            <td class="text-center">
                                <label title="file.max.size">
                                    <%= RESOURCE.getString("file.max.size") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Request for authorization</td>
                            <td class="text-center">
                                <label title="login.request">
                                    <%= RESOURCE.getString("login.request") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label title="Path relative to the view name prefix">
                                    Login view name
                                </label>
                            </td>
                            <td class="text-center">
                                <label title="login.view-url">
                                    <%= RESOURCE.getString("login.view-url") %>
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
