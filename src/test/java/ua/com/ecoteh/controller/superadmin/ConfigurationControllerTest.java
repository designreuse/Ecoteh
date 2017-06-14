package ua.com.ecoteh.controller.superadmin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.service.fabrica.MainMVFabric;
import ua.com.ecoteh.util.properties.ContentProperties;

import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.properties.MockContentProperties.getContentProperties;
import static ua.com.ecoteh.mocks.service.fabrica.MockMVFabric.getCacheMVFabric;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ConfigurationControllerTest {

    private static ConfigurationController controller;

    @BeforeClass
    public static void setUp() {
        final MainMVFabric fabric = getCacheMVFabric();
        final ContentProperties properties = getContentProperties();
        controller = new ConfigurationController(fabric, properties);
    }

    @Test
    public void whenGetPropertiesPageThenReturnSomeModelAndView() {
        final String viewName = "configuration/configuration";
        final String[] keys = { "main_company", "categories", "properties" };
        final ModelAndView modelAndView = controller.getPropertiesPage();
        checkModelAndView(modelAndView, viewName, keys);
    }
}
