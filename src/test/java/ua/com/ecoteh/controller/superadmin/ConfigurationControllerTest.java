package ua.com.ecoteh.controller.superadmin;

import org.junit.BeforeClass;
import org.junit.Test;

import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.controller.MockSuperadminController.getConfigurationController;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class ConfigurationControllerTest {

    private static ConfigurationController controller;

    @BeforeClass
    public static void setUp() {
        controller = getConfigurationController();
    }

    @Test
    public void whenGetPropertiesPageThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.getPropertiesPage(),
                "configuration/configuration",
                new String[] { "main_company", "categories", "properties" }
        );
    }
}
