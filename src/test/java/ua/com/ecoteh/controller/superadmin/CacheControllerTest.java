package ua.com.ecoteh.controller.superadmin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.service.fabrica.MainMVFabric;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.MockConstants.KEYWORDS;
import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.service.fabrica.MockMVFabric.getCacheMVFabric;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
public class CacheControllerTest {

    private static CacheController controller;

    @BeforeClass
    public static void setUp() {
        final MainMVFabric fabric = getCacheMVFabric();
        controller = new CacheController(fabric);
    }

    @Test
    public void whenGetCachePageThenReturnSomeModelAndView() {
        final String viewName = "cache/cache";
        final String[] keys = { "main_company", "categories", "favicon", "objects" };
        final ModelAndView modelAndView = controller.getCachePage();
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenClearCacheThenReturnSomeModelAndView() {
        final String expectedViewName = controller.clearCache();
        final String actualViewName = "redirect:/superadmin/cache";
        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    public void whenRemoveObjectFromCacheThenReturnSomeModelAndView() {
        final String expectedViewName = controller.removeObjectFromCache(KEYWORDS);
        final String actualViewName = "redirect:/superadmin/cache";
        assertEquals(expectedViewName, actualViewName);
    }
}
