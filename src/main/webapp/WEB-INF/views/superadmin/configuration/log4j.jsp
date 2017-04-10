<%--
Log4j Configuration.

Yuriy Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="log4j" var="log4j"/>

<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
    <div class="container">
        <div class="row">
            <div class="box">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <hr>
                    <h3 class="text-center" title="log4j.properties">
                        Logger Configuration
                    </h3>
                    <hr>
                </div>
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <table align="center" border="1" cellpadding="3" cellspacing="0">
                        <tr>
                            <td>Root Logger</td>
                            <td class="text-center">
                                <label title="log4j.rootLogger">
                                    <fmt:message bundle="${log4j}" key="log4j.rootLogger"/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Appender File</td>
                            <td class="text-center">
                                <label title="log4j.appender.file">
                                    <fmt:message bundle="${log4j}" key="log4j.appender.file"/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Appender File File</td>
                            <td class="text-center">
                                <label title="log4j.appender.file.Filet">
                                    <fmt:message bundle="${log4j}" key="log4j.appender.file.File"/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Appender File Max Size</td>
                            <td class="text-center">
                                <label title="log4j.appender.file.MaxFileSize">
                                    <fmt:message bundle="${log4j}" key="log4j.appender.file.MaxFileSize"/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Appender File Layout</td>
                            <td class="text-center">
                                <label title="log4j.appender.file.layout">
                                    <fmt:message bundle="${log4j}" key="log4j.appender.file.layout"/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Layout Conversion Pattern</td>
                            <td class="text-center">
                                <label title="log4j.appender.file.layout.ConversionPattern">
                                    <fmt:message bundle="${log4j}" key="log4j.appender.file.layout.ConversionPattern"/>
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
