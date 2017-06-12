package ua.com.ecoteh.mocks.service.data;

import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.service.seo.SeoService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.com.ecoteh.mocks.MockConstants.DOMAIN;
import static ua.com.ecoteh.mocks.enity.MockModels.getArticles;
import static ua.com.ecoteh.mocks.enity.MockModels.getCategories;
import static ua.com.ecoteh.mocks.enity.MockModels.getCompanies;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class MockSeoService {

    private final SeoService seoService;

    MockSeoService() {
        seoService = mock(SeoService.class);
    }

    SeoService create() {
        initGetRobotsTxt();
        initGetSiteMapXml();
        return this.seoService;
    }

    private void initGetRobotsTxt() {
        final ModelAndView robotsModelAndView = new ModelAndView();
        robotsModelAndView.addObject("domain", DOMAIN);
        robotsModelAndView.setViewName("seo/robots");
        when(this.seoService.getRobotsTxt()).thenReturn(robotsModelAndView);
    }

    private void initGetSiteMapXml() {
        final ModelAndView siteMapModelAndView = new ModelAndView();
        siteMapModelAndView.addObject("domain", DOMAIN);
        siteMapModelAndView.addObject("categories", getCategories());
        siteMapModelAndView.addObject("articles", getArticles());
        siteMapModelAndView.addObject("companies", getCompanies());
        siteMapModelAndView.setViewName("seo/sitemap");
        when(seoService.getSiteMapXml()).thenReturn(siteMapModelAndView);
    }
}
