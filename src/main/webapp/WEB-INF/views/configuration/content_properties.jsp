<%--
Content Properties Configuration.

Yuriy Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${properties ne null}">
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
        <div class="container">
            <div class="row">
                <div class="box">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                        <hr>
                        <h3 class="text-center" title="content.properties">
                            Project Content Configuration
                        </h3>
                        <hr>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                        <table align="center" border="1" cellpadding="3" cellspacing="0">
                            <tr>
                                <td>The Apache Catalina home path</td>
                                <td class="text-center">
                                    <label title="apache.catalina.home">
                                        <c:out value="${properties.catalinaHome}"/>
                                    </label>
                                </td>
                            </tr>
                            <tr>
                            <tr>
                                <td>Project directory name</td>
                                <td class="text-center">
                                    <label title="project.directory">
                                        <c:out value="${properties.projectDirectory}"/>
                                    </label>
                                </td>
                            </tr>
                            <tr>
                                <td>Project absolute path</td>
                                <td class="text-center">
                                    <c:out value="${properties.projectAbsolutePath}"/>
                                </td>
                            </tr>
                            <tr>
                                <td>Resources absolute path</td>
                                <td class="text-center">
                                    <c:out value="${properties.resourcesAbsolutePath}"/>
                                </td>
                            </tr>
                            <tr>
                                <td>Content Type encoding</td>
                                <td class="text-center">
                                    <label title="view.type">
                                        <c:out value="${properties.contentType}"/>
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
                                        <c:out value="${properties.prefix}"/>
                                    </label>
                                </td>
                            </tr>
                            <tr>
                                <td>View name suffix</td>
                                <td class="text-center">
                                    <label title="view.name-suffix">
                                        <c:out value="${properties.suffix}"/>
                                    </label>
                                </td>
                            </tr>
                            <tr>
                                <td>Context beans as attributes</td>
                                <td class="text-center">
                                    <label title="view.expose_beans_as_attributes">
                                        <c:out value="${properties.exposeContextBeansAsAttributes}"/>
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
                                        <c:out value="${properties.resourcesUrl}"/>
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
                                        <c:out value="${properties.resourcesLocation}"/>
                                    </label>
                                </td>
                            </tr>
                            <tr>
                                <td>Maximum file size (bytes)</td>
                                <td class="text-center">
                                    <label title="file.max.size">
                                        <c:out value="${properties.maxFileSize}"/>
                                    </label>
                                </td>
                            </tr>
                            <tr>
                                <td>Request for authorization</td>
                                <td class="text-center">
                                    <label title="login.request">
                                        <c:out value="${properties.loginRequest}"/>
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
                                        <c:out value="${properties.loginViewName}"/>
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
</c:if>
