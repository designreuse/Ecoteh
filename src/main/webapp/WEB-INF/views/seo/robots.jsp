<%@ page contentType="text/plain;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<%!
    private final static String PORT = java.util.ResourceBundle
            .getBundle("content")
            .getString("tomcat.server.port");
%>

User-agent: Yandex
Disallow: /admin
Disallow: /login
Disallow: /search
Disallow: /resources
Host: ${domain}:<%= PORT %>

User-agent: Googlebot
Disallow: /admin
Disallow: /login
Disallow: /search
Disallow: /resources

User-agent: *
Crawl-delay: 30
Disallow: /admin
Disallow: /login
Disallow: /search
Disallow: /resources

Sitemap: http://${domain}:<%= PORT %>/sitemap.xml

<%-- Yuriy Salimov (yuriy.alex.salimov@gmail.com) --%>
