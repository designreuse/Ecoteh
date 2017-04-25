package ua.com.ecoteh.mocks.controller;

import ua.com.ecoteh.controller.seo.SeoController;

import static ua.com.ecoteh.mocks.service.data.MockServices.getSeoService;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class MockSeoController {

    private static SeoController seoController;

    public static SeoController getSeoController() {
        if (seoController == null) {
            initSeoController();
        }
        return seoController;
    }

    private static void initSeoController() {
        seoController = new SeoController(getSeoService());
    }
}
