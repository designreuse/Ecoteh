package com.salimov.ecoteh.controller.superadmin;

import org.junit.BeforeClass;
import org.junit.Test;

import static com.salimov.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static com.salimov.ecoteh.mocks.controller.MockConfigurationController.getConfigurationController;

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
                "superadmin/configuration/configuration",
                new String[]{"main_company", "categories", "properties"}
        );
    }
}
