package com.salimov.yurii.controller.seo;

import com.salimov.yurii.service.seo.SeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * The class of controller to adjust the search engine optimization (SEO).
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Controller
@ComponentScan(basePackages = "com.salimov.yurii.service")
public class SeoController {

    /**
     * The implementation of the {@link SeoService} interface.
     */
    private final SeoService seoService;

    /**
     * Constructor.
     * Initializes a implementation of the interface.
     *
     * @param seoService a implementation of the {@link SeoService} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public SeoController(final SeoService seoService) {
        this.seoService = seoService;
    }

    /**
     * Returns information about the site for search engines.
     * Request mapping: /robots.txt, /robots
     * Produces: text/plain
     *
     * @return The information about a site.
     */
    @ResponseBody
    @RequestMapping(
            value = {"/robots.txt", "/robots"},
            produces = "text/plain"
    )
    public ModelAndView getRobotsTxt() {
        return this.seoService.getRobotsTxt();
    }

    /**
     * Creates and returns a sitemap.
     * This is a list of all pages on the site,
     * which consists of the links to these pages.
     * Request mapping: /sitemap.xml, /sitemap
     * Produces: text/plain
     *
     * @return Information about links on the site to search engines.
     */
    @ResponseBody
    @RequestMapping(
            value = {"/sitemap.xml", "/sitemap"},
            produces = "application/xml"
    )
    public ModelAndView getSiteMapXml() {
        return this.seoService.getSiteMapXml();
    }
}
