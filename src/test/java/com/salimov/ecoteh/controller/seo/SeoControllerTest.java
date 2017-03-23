package com.salimov.ecoteh.controller.seo;

import org.junit.BeforeClass;
import org.junit.Test;

import static com.salimov.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static com.salimov.ecoteh.mocks.controller.MockSeoController.getSeoController;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class SeoControllerTest {

    private static SeoController controller;

    @BeforeClass
    public static void setUp() {
        controller = getSeoController();
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
