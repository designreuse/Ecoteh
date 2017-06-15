package ua.com.ecoteh.controller.seo;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.service.seo.SeoService;

import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.service.data.MockServices.getSeoService;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class SeoControllerTest {

    private static SeoController controller;

    @BeforeClass
    public static void setUp() {
        final SeoService seoService = getSeoService();
        controller = new SeoController(seoService);
    }

    @Test
    public void whenGetRobotsTxtThenReturnSomeModelAndView() {
        final ModelAndView modelAndView = controller.getRobotsTxt();
        final String viewName = "seo/robots";
        final String[] keys = { "domain" };
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetSiteMapXmlThenReturnSomeModelAndView() {
        final ModelAndView modelAndView = controller.getSiteMapXml();
        final String viewName = "seo/sitemap";
        final String[] keys = { "domain", "categories", "articles", "companies" };
        checkModelAndView(modelAndView, viewName, keys);
    }
}
