<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="content" var="content"/>

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
                                    <fmt:message bundle="${content}" key="apache.catalina.home"/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Tomcat server port</td>
                            <td class="text-center">
                                <label title="tomcat.server.port">
                                    <fmt:message bundle="${content}" key="tomcat.server.port"/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Project directory name</td>
                            <td class="text-center">
                                <label title="project.directory">
                                    <fmt:message bundle="${content}" key="project.directory"/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Content Type encoding</td>
                            <td class="text-center">
                                <label title="view.type">
                                    <fmt:message bundle="${content}" key="view.type"/>
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
                                    <fmt:message bundle="${content}" key="view.name-prefix"/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>View name suffix</td>
                            <td class="text-center">
                                <label title="view.name-suffix">
                                    <fmt:message bundle="${content}" key="view.name-suffix"/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Context beans as attributes</td>
                            <td class="text-center">
                                <label title="view.expose_beans_as_attributes">
                                    <fmt:message bundle="${content}" key="view.expose_beans_as_attributes"/>
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
                                    <fmt:message bundle="${content}" key="resources.url"/>
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
                                    <fmt:message bundle="${content}" key="resources.location"/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Maximum file size (bytes)</td>
                            <td class="text-center">
                                <label title="file.max.size">
                                    <fmt:message bundle="${content}" key="file.max.size"/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Request for authorization</td>
                            <td class="text-center">
                                <label title="login.request">
                                    <fmt:message bundle="${content}" key="login.request"/>
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
                                    <fmt:message bundle="${content}" key="login.view-url"/>
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
