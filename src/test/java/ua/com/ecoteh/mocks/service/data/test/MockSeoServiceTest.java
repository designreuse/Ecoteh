package ua.com.ecoteh.mocks.service.data.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.service.seo.SeoService;

import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.service.data.MockServices.getSeoService;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MockSeoServiceTest {

    private static SeoService service;

    @BeforeClass
    public static void beforeClass() {
        service = getSeoService();
    }

    @Test
    public void whenGetRobotsTxtThenReturnNotNull() {
        final ModelAndView robotsModelAndView = service.getRobotsTxt();
        assertNotNull(robotsModelAndView);
    }

    @Test
    public void whenGetRobotsTxtThenReturnValidModelAndView() {
        final ModelAndView robotsModelAndView = service.getRobotsTxt();
        final String[] keys = { "domain" };
        final String viewName = "seo/robots";
        checkModelAndView(robotsModelAndView, viewName, keys);
    }

    @Test
    public void whenGetSiteMapXmlThenReturnNotNull() {
        final ModelAndView siteMapModelAndView = service.getSiteMapXml();
        assertNotNull(siteMapModelAndView);
    }

    @Test
    public void whenGetSiteMapXmlThenReturnValidModelAndView() {
        final ModelAndView siteMapModelAndView = service.getSiteMapXml();
        final String[] keys = { "domain", "categories", "articles", "companies" };
        final String viewName = "seo/sitemap";
        checkModelAndView(siteMapModelAndView, viewName, keys);
    }
}
