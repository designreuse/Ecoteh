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
<c:set var="article_priority" value="1.0"/>
<c:set var="blog_priority" value="0.8"/>
<c:set var="category_priority" value="0.6"/>
<c:set var="company_priority" value="0.4"/>
<c:set var="other_priority" value="0.2"/>

<compress:xml removeIntertagSpaces="true">
    <?xml version="1.0" encoding="UTF-8"?>
    <urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://www.sitemaps.org/schemas/sitemap/0.9
            http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd">
            <%-- Main URLs --%>
        <url>
            <loc>
                <c:out value="http://${domain}/"/>
            </loc>
            <changefreq>${changefreq}</changefreq>
            <priority>${main_priority}</priority>
        </url>
        <url>
            <loc>
                <c:out value="http://${domain}/index"/>
            </loc>
            <changefreq>${changefreq}</changefreq>
            <priority>${main_priority}</priority>
        </url>
        <url>
            <loc>
                <c:out value="http://${domain}/home"/>
            </loc>
            <changefreq>${changefreq}</changefreq>
            <priority>${main_priority}</priority>
        </url>
        <url>
            <loc>
                <c:out value="http://${domain}/company/main"/>
            </loc>
            <changefreq>${changefreq}</changefreq>
            <priority>${main_priority}</priority>
        </url>
        <url>
            <loc>
                <c:out value="http://${domain}/contacts"/>
            </loc>
            <changefreq>${changefreq}</changefreq>
            <priority>${main_priority}</priority>
        </url>
        <url>
            <loc>
                <c:out value="http://${domain}/address"/>
            </loc>
            <changefreq>${changefreq}</changefreq>
            <priority>${main_priority}</priority>
        </url>
        <url>
            <loc>
                <c:out value="http://${domain}/responses"/>
            </loc>
            <changefreq>${changefreq}</changefreq>
            <priority>${other_priority}</priority>
        </url>
        <url>
            <loc>
                <c:out value="http://${domain}/response/all"/>
            </loc>
            <changefreq>${changefreq}</changefreq>
            <priority>${other_priority}</priority>
        </url>
            <%-- Categories URLs --%>
        <c:if test="${fn:length(categories) gt 0}">
            <url>
                <loc>
                    <c:out value="http://${domain}/category/all"/>
                </loc>
                <changefreq>${changefreq}</changefreq>
                <priority>${category_priority}</priority>
            </url>
            <url>
                <loc>
                    <c:out value="http://${domain}/categories"/>
                </loc>
                <changefreq>${changefreq}</changefreq>
                <priority>${category_priority}</priority>
            </url>
            <c:forEach items="${categories}" var="category">
                <url>
                    <loc>
                        <c:out value="http://${domain}/category/${category.url}"/>
                    </loc>
                    <changefreq>${changefreq}</changefreq>
                    <priority>${category_priority}</priority>
                </url>
                <url>
                    <loc>
                        <c:out value="http://${domain}/${category.url}"/>
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
                    <c:out value="http://${domain}/article/all"/>
                </loc>
                <changefreq>${changefreq}</changefreq>
                <priority>${article_priority}</priority>
            </url>
            <url>
                <loc>
                    <c:out value="http://${domain}/articles"/>
                </loc>
                <changefreq>${changefreq}</changefreq>
                <priority>${article_priority}</priority>
            </url>
            <c:forEach items="${articles}" var="article">
                <url>
                    <loc>
                        <c:out value="http://${domain}/article/${article.url}"/>
                    </loc>
                    <changefreq>${changefreq}</changefreq>
                    <priority>${article_priority}</priority>
                </url>
            </c:forEach>
        </c:if>
            <%-- Blog URLs --%>
        <c:if test="${fn:length(posts) gt 0}">
            <url>
                <loc>
                    <c:out value="http://${domain}/blog"/>
                </loc>
                <changefreq>${changefreq}</changefreq>
                <priority>${blog_priority}</priority>
            </url>
            <url>
                <loc>
                    <c:out value="http://${domain}"/>/posts
                </loc>
                <changefreq>${changefreq}</changefreq>
                <priority>${blog_priority}</priority>
            </url>
            <url>
                <loc>
                    <c:out value="http://${domain}/post/all"/>
                </loc>
                <changefreq>${changefreq}</changefreq>
                <priority>${blog_priority}</priority>
            </url>
            <c:forEach items="${posts}" var="post">
                <url>
                    <loc>
                        <c:out value="http://${domain}/blog/${post.url}"/>
                    </loc>
                    <changefreq>${changefreq}</changefreq>
                    <priority>${blog_priority}</priority>
                </url>
                <url>
                    <loc>
                        <c:out value="http://${domain}/post/${post.url}"/>
                    </loc>
                    <changefreq>${changefreq}</changefreq>
                    <priority>${blog_priority}</priority>
                </url>
            </c:forEach>
        </c:if>
    </urlset>
</compress:xml>
