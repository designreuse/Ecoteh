<%@ page contentType="text/plain;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

User-agent: Yandex
Disallow: /admin
Disallow: /login
Disallow: /search
Disallow: /resources
Host: ${domain}

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

Sitemap: http://${domain}/sitemap.xml

<%-- Yuriy Salimov (yuriy.alex.salimov@gmail.com) --%>
