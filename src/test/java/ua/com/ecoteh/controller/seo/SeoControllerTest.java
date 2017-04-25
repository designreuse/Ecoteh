package ua.com.ecoteh.controller.seo;

import org.junit.BeforeClass;
import org.junit.Test;
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
        checkModelAndView(
                controller.getRobotsTxt(),
                "seo/robots",
                new String[]{"domain"}
        );
    }

    @Test
    public void whenGetSiteMapXmlThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.getSiteMapXml(),
                "seo/sitemap",
                new String[]{"domain", "categories", "articles", "companies"}
        );
    }
}
