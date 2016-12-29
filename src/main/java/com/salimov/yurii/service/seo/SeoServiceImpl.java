package com.salimov.yurii.service.seo;

import com.salimov.yurii.entity.Article;
import com.salimov.yurii.entity.Category;
import com.salimov.yurii.entity.Company;
import com.salimov.yurii.entity.Section;
import com.salimov.yurii.service.data.interfaces.*;
import com.salimov.yurii.util.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * The class of the service layer, implements a set of methods
 * to adjust the search engine optimization (SEO).
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 */
@Service
@ComponentScan(basePackages = "com.salimov.yurii.service")
public class SeoServiceImpl implements SeoService {

    /**
     * The default lifetime of objects (milliseconds).
     * 86400000 milliseconds = 1 day
     */
    private static final long DEFAULT_TIMEOUT = 86400000L;

    /**
     * The describes a set of methods for working
     * with objects of the {@link Company} class.
     *
     * @see CompanyService
     */
    private final CompanyService companyService;

    /**
     * The describes a set of methods for working
     * with objects of the {@link Section} class.
     *
     * @see SectionService
     */
    private final SectionService sectionService;

    /**
     * The describes a set of methods for working
     * with objects of the {@link Category} class.
     *
     * @see CategoryService
     */
    private final CategoryService categoryService;

    /**
     * The describes a set of methods for working
     * with objects of the {@link Article} class.
     *
     * @see ArticleService
     */
    private final ArticleService articleService;

    /**
     * The domain of the main company.
     */
    private final String domain;

    /**
     * Constructor.
     * Initializes a implementation of the interface.
     *
     * @param companyService  a implementation
     *                        of the {@link CompanyService} interface.
     * @param sectionService  a implementation
     *                        of the {@link SectionService} interface.
     * @param categoryService a implementation
     *                        of the {@link CategoryService} interface.
     * @param articleService  a implementation
     *                        of the {@link ArticleService} interface.
     * @see CompanyService
     * @see SectionService
     * @see CategoryService
     * @see ArticleService
     */
    @Autowired
    public SeoServiceImpl(
            final CompanyService companyService,
            final SectionService sectionService,
            final CategoryService categoryService,
            final ArticleService articleService
    ) {
        this.companyService = companyService;
        this.sectionService = sectionService;
        this.categoryService = categoryService;
        this.articleService = articleService;
        final Company company = this.companyService.getMainCompany();
        this.domain = company.getDomain()
                .replace("http://", "")
                .replace("https://", "");
    }

    /**
     * Returns information about the site for the search engines.
     *
     * @return Information about the site for the search engines.
     */
    public String getRobotsTxt() {
        final String key = "SEO robots.txt";
        String robotsTxt = (String) Cache.get(key);
        if (isBlank(robotsTxt)) {
            robotsTxt = createRobotsTxt();
            Cache.put(key, robotsTxt, DEFAULT_TIMEOUT);
        }
        return robotsTxt;
    }

    /**
     * Creates and returns a sitemap. This is a list of all pages on the site,
     * which consists of the links to these pages.
     *
     * @return Information about links on the site to search engines.
     */
    public String getSiteMapXml() {
        final String key = "SEO sitemap.xml";
        String result = (String) Cache.get(key);
        if (isBlank(result)) {
            result = createSitemap();
            Cache.put(key, result, DEFAULT_TIMEOUT);
        }
        return result;
    }

    /**
     * Creates and returns information about the site for the search engines.
     *
     * @return The information about the site for the search engines.
     */
    private String createRobotsTxt() {
        return "User-agent: Yandex" +
                "\nDisallow: /admin" +
                "\nDisallow: /login" +
                "\nDisallow: /resources" +
                "\nHost: " + this.domain +
                "\nUser-agent: Googlebot" +
                "\nDisallow: /admin" +
                "\nDisallow: /manager" +
                "\nDisallow: /login" +
                "\nDisallow: /resources\n" +
                "\nUser-agent: *" +
                "\nCrawl-delay: 30" +
                "\nDisallow: /admin" +
                "\nDisallow: /login" +
                "\nDisallow: /search" +
                "\nDisallow: /resources\n" +
                "\nSitemap: http://" + this.domain + "/sitemap.xml";
    }

    /**
     * Creates and returns a sitemap.
     *
     * @return The sitemap information.
     */
    private String createSitemap() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\"" +
                " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
                " xsi:schemaLocation=\"http://www.sitemaps.org/schemas/sitemap/0.9" +
                " http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd\">" +
                "<url>\n  <loc>http://" +
                domain +
                "/</loc>\n<priority>1.0</priority>\n</url>\n" +
                "<url>\n  <loc>http://" +
                domain +
                "/index</loc>\n<priority>1.0</priority>\n</url>\n" +
                "<url>\n  <loc>http://" +
                domain +
                "/home</loc>\n<priority>1.0</priority>\n</url>\n" +
                "<url>\n  <loc>http://" +
                domain +
                "/company/main</loc>\n<priority>1.0</priority>\n</url>\n" +
                "<url>\n  <loc>http://" +
                domain +
                "/contacts</loc>\n<priority>1.0</priority>\n</url>\n" +
                "<url>\n  <loc>http://" +
                domain +
                "/address</loc>\n<priority>1.0</priority>\n</url>\n" +
                "<url>\n  <loc>http://" +
                domain +
                "/responses</loc>\n</url>\n" +
                getSectionsUrls() +
                getCategoriesUrls() +
                getArticlesUrls() +
                getPartnersUrls() +
                "</urlset>";
    }

    /**
     * Creates and returns a list of all pages of sections on the site,
     * which consists of the links to these pages.
     *
     * @return Information about links on the all pages of sections
     * to search engines.
     * @see Section
     */
    private String getSectionsUrls() {
        final Collection<Section> sections = this.sectionService.getAll();
        final StringBuilder sb = new StringBuilder();
        if (sections != null && !sections.isEmpty()) {
            sb.append("<url>\n  <loc>http://")
                    .append(domain)
                    .append("/section/all</loc>\n</url>\n");
            for (Section section : sections) {
                sb.append("<url>\n  <loc>http://")
                        .append(domain)
                        .append("/section/")
                        .append(section.getUrl())
                        .append("</loc>\n</url>\n")
                        .append("<url>\n  <loc>http://")
                        .append(domain)
                        .append("/category/all/")
                        .append(section.getUrl())
                        .append("</loc>\n</url>\n");
            }
        }
        return sb.toString();
    }

    /**
     * Creates and returns a list of all pages of categories on the site,
     * which consists of the links to these pages.
     *
     * @return Information about links on the all pages
     * of categories to search engines.
     * @see Category
     */
    private String getCategoriesUrls() {
        final Collection<Category> categories = this.categoryService.getAll();
        final StringBuilder sb = new StringBuilder();
        if (categories != null && !categories.isEmpty()) {
            sb.append("<url>\n  <loc>http://")
                    .append(domain)
                    .append("/category/all</loc>\n</url>\n");
            for (Category category : categories) {
                sb.append("<url>\n  <loc>http://")
                        .append(domain)
                        .append("/category/")
                        .append(category.getUrl())
                        .append("</loc>\n</url>\n");
            }
        }
        return sb.toString();
    }

    /**
     * Creates and returns a list of all pages of articles on the site,
     * which consists of the links to these pages.
     *
     * @return Information about links on the all pages
     * of articles to search engines.
     * @see Article
     */
    private String getArticlesUrls() {
        final Collection<Article> articles = this.articleService.getAll();
        final StringBuilder sb = new StringBuilder();
        if (articles != null && !articles.isEmpty()) {
            for (Article article : articles) {
                sb.append("<url>\n  <loc>http://")
                        .append(domain)
                        .append("/article/")
                        .append(article.getUrl())
                        .append("</loc>\n</url>\n")
                        .append("<url>\n  <loc>http://")
                        .append(domain)
                        .append("/article/num_")
                        .append(article.getNumber())
                        .append("</loc>\n</url>\n");
            }
        }
        return sb.toString();
    }

    /**
     * Creates and returns a list of all pages of partners on the site,
     * which consists of the links to these pages.
     *
     * @return Information about links on the all pages
     * of partners to search engines.
     * @see Company
     */
    private String getPartnersUrls() {
        final Collection<Company> partners
                = this.companyService.getPartners(true);
        final StringBuilder sb = new StringBuilder();
        if (partners != null && !partners.isEmpty()) {
            sb.append("<url>\n  <loc>http://")
                    .append(domain)
                    .append("/company/all</loc>\n</url>\n");
            for (Company partner : partners) {
                sb.append("<url>\n  <loc>http://")
                        .append(domain)
                        .append("/company/")
                        .append(partner.getUrl())
                        .append("</loc>\n</url>\n")
                        .append("<url>\n  <loc>http://")
                        .append(partner.getDomain())
                        .append("</loc>\n</url>\n");
            }
        }
        return sb.toString();
    }
}
