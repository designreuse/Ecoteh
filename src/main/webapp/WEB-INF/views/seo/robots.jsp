<%--
Information about the site for search engines.

Yuriy Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/plain;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<%-- Yandex search engine --%>
User-agent: Yandex
Disallow: /admin
Disallow: /login
Disallow: /search
Disallow: /resources
Host: ${domain}

<%-- Google search engine --%>
User-agent: Googlebot
Disallow: /admin
Disallow: /login
Disallow: /search
Disallow: /resources

<%-- Other search engines --%>
User-agent: *
Crawl-delay: 30
Disallow: /admin
Disallow: /login
Disallow: /search
Disallow: /resources

<%-- Sitemap.xml link --%>
Sitemap: http://${domain}/sitemap.xml
