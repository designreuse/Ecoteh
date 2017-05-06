<%--
Information about the site for search engines.

Yuriy Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="text/plain;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<%-- Yandex search engine --%>
User-agent: Yandex
Disallow: /admin
Disallow: /superadmin
Disallow: /login
Disallow: /search
Disallow: /resources/ckeditor
Disallow: /resources/css
Disallow: /resources/fonts
Disallow: /resources/js
Disallow: /resources/img/static
Disallow: /resources/img/users
Host: ${domain}

<%-- Google search engine --%>
User-agent: Googlebot
Disallow: /admin
Disallow: /superadmin
Disallow: /login
Disallow: /search
Disallow: /resources/ckeditor
Disallow: /resources/css
Disallow: /resources/fonts
Disallow: /resources/js
Disallow: /resources/img/static
Disallow: /resources/img/users

<%-- Other search engines --%>
User-agent: *
Crawl-delay: 30
Disallow: /admin
Disallow: /superadmin
Disallow: /login
Disallow: /search
Disallow: /resources/ckeditor
Disallow: /resources/css
Disallow: /resources/fonts
Disallow: /resources/js
Disallow: /resources/img/static
Disallow: /resources/img/users


<%-- Sitemap.xml link --%>
Sitemap: http://${domain}/sitemap.xml
