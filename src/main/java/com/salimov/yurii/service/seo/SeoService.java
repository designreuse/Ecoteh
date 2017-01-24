package com.salimov.yurii.service.seo;

/**
 * The interface of the service layer, describes a set of methods
 * to adjust the search engine optimization (SEO).
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface SeoService {

    /**
     * Returns information about the site for the search engines.
     *
     * @return Information about the site for the search engines.
     */
    String getRobotsTxt();

    /**
     * Creates and returns a sitemap. This is a list of all pages on the site,
     * which consists of the links to these pages.
     *
     * @return Information about links on the site to search engines.
     */
    String getSiteMapXml();
}
