package ua.com.ecoteh.controller.superadmin;

import org.junit.BeforeClass;
import org.junit.Test;

import static ua.com.ecoteh.mocks.MockConstants.KEYWORDS;
import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.controller.MockSuperadminController.getCacheController;
import static org.junit.Assert.assertEquals;

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
                new String[] { "main_company", "categories", "objects" }
        );
    }

    @Test
    public void whenClearCacheThenReturnSomeModelAndView() {
        String viewName = controller.clearCache();
        assertEquals(viewName, "redirect:/superadmin/cache");
    }

    @Test
    public void whenRemoveObjectFromCacheThenReturnSomeModelAndView() {
        String viewName = controller.removeObjectFromCache(KEYWORDS);
        assertEquals(viewName, "redirect:/superadmin/cache");
    }
}
