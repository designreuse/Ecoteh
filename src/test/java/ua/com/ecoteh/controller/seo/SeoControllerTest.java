package ua.com.ecoteh.controller.seo;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.mocks.controller.MockSeoController;

import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class SeoControllerTest {

    private static SeoController controller;

    @BeforeClass
    public static void setUp() {
        controller = MockSeoController.getSeoController();
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
