<%--
Sitemap is a list of all pages on the site,
which consists of the links to these pages.

Yuriy Salimov (yuriy.alex.salimov@gmail.com)
--%>

<%@ page contentType="application/xml;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<c:set var="changefreq" value="weekly"/>
<c:set var="main_priority" value="1.0"/>
<c:set var="category_priority" value="0.5"/>
<c:set var="article_priority" value="0.5"/>
<c:set var="company_priority" value="0.5"/>
<c:set var="other_priority" value="0.5"/>

<compress:xml removeIntertagSpaces="true">
    <?xml version="1.0" encoding="UTF-8"?>
    <urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://www.sitemaps.org/schemas/sitemap/0.9
            http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd">
            <%-- Main URLs --%>
        <url>
            <loc>
                http://<c:out value="${domain}"/>/
            </loc>
            <changefreq>${changefreq}</changefreq>
            <priority>${main_priority}</priority>
        </url>
        <url>
            <loc>
                http://<c:out value="${domain}"/>/index
            </loc>
            <changefreq>${changefreq}</changefreq>
            <priority>${main_priority}</priority>
        </url>
        <url>
            <loc>
                http://<c:out value="${domain}"/>/home
            </loc>
            <changefreq>${changefreq}</changefreq>
            <priority>${main_priority}</priority>
        </url>
        <url>
            <loc>
                http://<c:out value="${domain}"/>/company/main
            </loc>
            <changefreq>${changefreq}</changefreq>
            <priority>${main_priority}</priority>
        </url>
        <url>
            <loc>
                http://<c:out value="${domain}"/>/contacts
            </loc>
            <changefreq>${changefreq}</changefreq>
            <priority>${main_priority}</priority>
        </url>
        <url>
            <loc>
                http://<c:out value="${domain}"/>/address
            </loc>
            <changefreq>${changefreq}</changefreq>
            <priority>${main_priority}</priority>
        </url>
        <url>
            <loc>
                http://<c:out value="${domain}"/>/responses
            </loc>
            <changefreq>${changefreq}</changefreq>
            <priority>${other_priority}</priority>
        </url>
        <url>
            <loc>
                http://<c:out value="${domain}"/>/response/all
            </loc>
            <changefreq>${changefreq}</changefreq>
            <priority>${other_priority}</priority>
        </url>
            <%-- Categories URLs --%>
        <c:if test="${fn:length(categories) gt 0}">
            <url>
                <loc>
                    http://<c:out value="${domain}"/>/category/all
                </loc>
                <changefreq>${changefreq}</changefreq>
                <priority>${category_priority}</priority>
            </url>
            <url>
                <loc>
                    http://<c:out value="${domain}"/>/categories
                </loc>
                <changefreq>${changefreq}</changefreq>
                <priority>${category_priority}</priority>
            </url>
            <c:forEach items="${categories}" var="cactegory">
                <url>
                    <loc>
                        http://<c:out value="${domain}"/>/category/<c:out value="${cactegory.url}"/>
                    </loc>
                    <changefreq>${changefreq}</changefreq>
                    <priority>${category_priority}</priority>
                </url>
            </c:forEach>
        </c:if>
            <%-- Articles URLs --%>
        <c:if test="${fn:length(articles) gt 0}">
            <url>
                <loc>
                    http://<c:out value="${domain}"/>/article/all
                </loc>
                <changefreq>${changefreq}</changefreq>
                <priority>${article_priority}</priority>
            </url>
            <url>
                <loc>
                    http://<c:out value="${domain}"/>/articles
                </loc>
                <changefreq>${changefreq}</changefreq>
                <priority>${article_priority}</priority>
            </url>
            <c:forEach items="${articles}" var="article">
                <url>
                    <loc>
                        http://<c:out value="${domain}"/>/article/<c:out value="${article.url}"/>
                    </loc>
                    <changefreq>${changefreq}</changefreq>
                    <priority>${article_priority}</priority>
                </url>
            </c:forEach>
        </c:if>
            <%-- Companies URLs --%>
        <c:if test="${fn:length(companies) gt 0}">
            <url>
                <loc>
                    http://<c:out value="${domain}"/>/company/all
                </loc>
                <changefreq>${changefreq}</changefreq>
                <priority>${company_priority}</priority>
            </url>
            <url>
                <loc>
                    http://<c:out value="${domain}"/>/companies
                </loc>
                <changefreq>${changefreq}</changefreq>
                <priority>${company_priority}</priority>
            </url>
            <url>
                <loc>
                    http://<c:out value="${domain}"/>/partners
                </loc>
                <changefreq>${changefreq}</changefreq>
                <priority>${company_priority}</priority>
            </url>
            <c:forEach items="${companies}" var="company">
                <url>
                    <loc>
                        http://<c:out value="${domain}"/>/company/<c:out value="${company.url}"/>
                    </loc>
                    <changefreq>${changefreq}</changefreq>
                    <priority>${company_priority}</priority>
                </url>
            </c:forEach>
        </c:if>
    </urlset>
</compress:xml>
