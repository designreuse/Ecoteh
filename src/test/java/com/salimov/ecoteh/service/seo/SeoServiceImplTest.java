package com.salimov.ecoteh.service.seo;

import org.junit.Before;
import org.junit.Test;

import static com.salimov.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static com.salimov.ecoteh.mocks.service.data.MockServices.getArticleService;
import static com.salimov.ecoteh.mocks.service.data.MockServices.getCategoryService;
import static com.salimov.ecoteh.mocks.service.data.MockServices.getCompanyService;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class SeoServiceImplTest {

    private SeoService seoService;

    @Before
    public void beforeTests() {
        this.seoService = new SeoServiceImpl(
                getCompanyService(),
                getCategoryService(),
                getArticleService()
        );
    }

    @Test
    public void whenGetRobotsTxtThenReturnsSomeModelAndView() {
        checkModelAndView(
                this.seoService.getRobotsTxt(),
                "seo/robots", new String[]{"domain"}
        );
    }

    @Test
    public void whenGetSitemapThenReturnsSomeModelAndView() {
        checkModelAndView(
                this.seoService.getSiteMapXml(),
                "seo/sitemap",
                new String[]{"domain", "categories", "articles", "companies"}
        );
    }
}
