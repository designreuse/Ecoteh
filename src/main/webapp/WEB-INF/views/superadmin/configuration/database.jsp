<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%! private final static java.util.ResourceBundle RESOURCE = java.util.ResourceBundle.getBundle("database"); %>

<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
    <div class="container">
        <div class="row">
            <div class="box">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <hr>
                    <h3 class="text-center" title="database.properties">
                        Database Configuration
                    </h3>
                    <hr>
                </div>
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                    <table align="center" border="1" cellpadding="3" cellspacing="0">
                        <tr>
                            <td>
                                <label title="Driver for connection to the database">
                                    JDBS Driver
                                </label>
                            </td>
                            <td class="text-center">
                                <label title="jdbc.driver">
                                    <%= RESOURCE.getString("jdbc.driver") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label title="URL of driver for connection to the database">
                                    JDBS Driver URL
                                </label>
                            </td>
                            <td class="text-center">
                                <label title="jdbc.driver.url">
                                    <%= RESOURCE.getString("jdbc.driver.url") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label title="Host ip where staying database">
                                    JDBS Host IP
                                </label>
                            </td>
                            <td class="text-center">
                                <label title="jdbc.host.ip">
                                    <%= RESOURCE.getString("jdbc.host.ip") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label title="Host ip where staying database">
                                    JDBS Host Port
                                </label>
                            </td>
                            <td class="text-center">
                                <label title="jdbc.host.port">
                                    <%= RESOURCE.getString("jdbc.host.port") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Database Name</td>
                            <td class="text-center">
                                <label title="jdbc.database">
                                    <%= RESOURCE.getString("jdbc.database") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label title="User name which will be work with database">
                                    Database Username
                                </label>
                            </td>
                            <td class="text-center">
                                <label title="jdbc.username">
                                    <%= RESOURCE.getString("jdbc.username") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label title="User password which will be work with database">
                                    Database User Password
                                </label>
                            </td>
                            <td class="text-center">
                                <label title="jdbc.password">
                                    <%= RESOURCE.getString("jdbc.password") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label title="Initial size of the connection pool">
                                    Initial Size
                                </label>
                            </td>
                            <td class="text-center">
                                <label title="jdbc.initial-size">
                                    <%= RESOURCE.getString("jdbc.initial-size") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label title="Maximum number of active connections that can be allocated at the same time">
                                    Max active
                                </label>
                            </td>
                            <td class="text-center">
                                <label title="jdbc.max-active">
                                    <%= RESOURCE.getString("jdbc.max-active") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label title="This property determines whether or not the pool will validate objects before they are borrowed from the pool">
                                    Test On Borrow
                                </label>
                            </td>
                            <td class="text-center">
                                <label title="jdbc.test-on-borrow">
                                    <%= RESOURCE.getString("jdbc.test-on-borrow") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label title="The SQL query that will be used to validate connections from this pool before returning them to the caller">
                                    Validation query
                                </label>
                            </td>
                            <td class="text-center">
                                <label title="jdbc.validation-query">
                                    <%= RESOURCE.getString("jdbc.validation-query") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label title="It is use SSL">
                                    Used SSL
                                </label>
                            </td>
                            <td class="text-center">
                                <label title="jdbc.use-ssl">
                                    <%= RESOURCE.getString("jdbc.use-ssl") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label title="It is use Unicode">
                                    Used Unicode
                                </label>
                            </td>
                            <td class="text-center">
                                <label title="jdbc.use-unicode">
                                    <%= RESOURCE.getString("jdbc.use-unicode") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Character Encoding</td>
                            <td class="text-center">
                                <label title="jdbc.character-encoding">
                                    <%= RESOURCE.getString("jdbc.character-encoding") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label title="It is use JDBC compliant timezone shift">
                                    Used Compliant Timezone Shift
                                </label>
                            </td>
                            <td class="text-center">
                                <label title="jdbc.use-jdbc-compliant-timezone-shift">
                                    <%= RESOURCE.getString("jdbc.use-jdbc-compliant-timezone-shift") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label title="It is use legacy datetime code">
                                    User Legacy Datetime Code
                                </label>
                            </td>
                            <td class="text-center">
                                <label title="jdbc.use-legacy-datetime-code">
                                    <%= RESOURCE.getString("jdbc.use-legacy-datetime-code") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>Server timezone</td>
                            <td class="text-center">
                                <label title="jdbc.server-timezone">
                                    <%= RESOURCE.getString("jdbc.server-timezone") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label title="Hibernate SQL dialect for database">
                                    Hibernate SQL dialect
                                </label>
                            </td>
                            <td class="text-center">
                                <label title="hibernate.dialect">
                                    <%= RESOURCE.getString("hibernate.dialect") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label title="It is show sql in the console.">
                                    Hibernate show sql
                                </label>
                            </td>
                            <td class="text-center">
                                <label title="hibernate.show-sql">
                                    <%= RESOURCE.getString("hibernate.show-sql") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label title="It is use DDL.">
                                    Hibernate generate DDL
                                </label>
                            </td>
                            <td class="text-center">
                                <label title="hibernate.generate-ddl">
                                    <%= RESOURCE.getString("hibernate.generate-ddl") %>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label title="Packages of entity for Entity Manager">
                                    Entity Package
                                </label>
                            </td>
                            <td class="text-center">
                                <label title="hibernate.entity-packages">
                                    <%= RESOURCE.getString("hibernate.entity-packages") %>
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
