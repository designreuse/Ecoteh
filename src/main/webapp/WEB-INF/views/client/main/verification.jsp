<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%!
    private final static java.util.ResourceBundle RESOURCE =
            java.util.ResourceBundle.getBundle("verification");
    private final static String GOOGLE =
            RESOURCE.getString("google.verification");
    private final static String YANDEX =
            RESOURCE.getString("yandex.verification");
%>

<meta name="google-site-verification" content="<%= GOOGLE %>"/>
<meta name="yandex-verification" content="<%= YANDEX %>"/>

<%-- Yuriy Salimov (yurii.alex.salimov@gmail.com) --%>
