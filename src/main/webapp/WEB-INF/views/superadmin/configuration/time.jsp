<%--
Server Time Configuration.

Yuriy Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
    <div class="container">
        <div class="row">
            <div class="box">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <hr>
                    <h3 class="text-center" title="verification.properties">
                        Time Configuration
                    </h3>
                    <hr>
                </div>
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <table align="center" border="1" cellpadding="3" cellspacing="0">
                        <tr>
                            <td>Server Time</td>
                            <td class="text-center">
                                <fmt:formatDate value="${server_date}" type="both"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Location Time</td>
                            <td class="text-center">
                                <c:out value="${conf_date}"/>
                            </td>
                        </tr>
                        <%-- Main company work time --%>
                        <c:if test="${(main_company ne null) and
                        (not empty main_company.workTimeFrom) and
                        (not empty main_company.workTimeTo)}">
                            <tr>
                                <td>Company Work Time</td>
                                <td class="text-center">
                                    <c:out value="${main_company.workTimeFrom}"/> - <c:out
                                        value="${main_company.workTimeTo}"/>
                                </td>
                            </tr>
                            <tr>
                                <td>Is open</td>
                                <td class="text-center">
                                    <c:out value="${main_company.open}"/>
                                </td>
                            </tr>
                        </c:if>
                    </table>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</div>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
