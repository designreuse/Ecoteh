<%@ page contentType="application/xml;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:xml removeIntertagSpaces="true">
    <?xml version="1.0" encoding="UTF-8"?>
    <urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://www.sitemaps.org/schemas/sitemap/0.9
            http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd">
        <url>
            <loc>http://${domain}/</loc>
            <changefreq>weekly</changefreq>
            <priority>1.0</priority>
        </url>
        <url>
            <loc>http://${domain}/index</loc>
            <changefreq>weekly</changefreq>
            <priority>1.0</priority>
        </url>
        <url>
            <loc>http://${domain}/home</loc>
            <changefreq>weekly</changefreq>
            <priority>1.0</priority>
        </url>
        <url>
            <loc>http://${domain}/company/main</loc>
            <changefreq>weekly</changefreq>
            <priority>0.9</priority>
        </url>
        <url>
            <loc>http://${domain}/contacts</loc>
            <changefreq>weekly</changefreq>
            <priority>0.9</priority>
        </url>
        <url>
            <loc>http://${domain}/address</loc>
            <changefreq>weekly</changefreq>
            <priority>0.5</priority>
        </url>
        <url>
            <loc>http://${domain}/responses</loc>
            <changefreq>weekly</changefreq>
            <priority>0.5</priority>
        </url>
        <c:if test="${fn:length(categories) gt 0}">
            <url>
                <loc>http://${domain}/category/all</loc>
                <changefreq>weekly</changefreq>
                <priority>0.8</priority>
            </url>
            <c:forEach items="${categories}" var="cactegory">
                <url>
                    <loc>http://${domain}/category/${category.url}</loc>
                    <changefreq>weekly</changefreq>
                    <priority>0.6</priority>
                </url>
            </c:forEach>
        </c:if>
        <c:if test="${fn:length(articles) gt 0}">
            <url>
                <loc>http://${domain}/article/all</loc>
                <changefreq>weekly</changefreq>
                <priority>0.8</priority>
            </url>
            <c:forEach items="${articles}" var="article">
                <url>
                    <loc>http://${domain}/article/${article.url}</loc>
                    <changefreq>weekly</changefreq>
                    <priority>0.5</priority>
                </url>
            </c:forEach>
        </c:if>
        <c:if test="${fn:length(companies) gt 0}">
            <url>
                <loc>http://${domain}/company/all</loc>
                <changefreq>weekly</changefreq>
                <priority>0.5</priority>
            </url>
            <c:forEach items="${companies}" var="company">
                <url>
                    <loc>http://${domain}/company/${company.url}</loc>
                    <changefreq>weekly</changefreq>
                    <priority>0.5</priority>
                </url>
            </c:forEach>
        </c:if>
    </urlset>
</compress:xml>

<%-- Yuriy Salimov (yuriy.alex.salimov@gmail.com) --%>
