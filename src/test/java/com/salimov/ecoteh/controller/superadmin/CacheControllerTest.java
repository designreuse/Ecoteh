package com.salimov.ecoteh.controller.superadmin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static com.salimov.ecoteh.mocks.MockConstants.KEYWORDS;
import static com.salimov.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static com.salimov.ecoteh.mocks.controller.MockCacheController.getCacheController;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class CacheControllerTest {

    private static CacheController controller;

    @BeforeClass
    public static void setUp() {
        controller = getCacheController();
    }

    @Test
    public void whenGetCachePageThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.getCachePage(),
                "superadmin/cache/cache",
                new String[]{"main_company", "categories", "objects"}
        );
    }

    @Test
    public void whenClearCacheThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.clearCache(new ModelAndView()),
                "redirect:/superadmin/cache",
                null
        );
    }

    @Test
    public void whenRemoveObjectFromCacheThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.removeObjectFromCache(KEYWORDS, new ModelAndView()),
                "redirect:/superadmin/cache",
                null
        );
    }
}
