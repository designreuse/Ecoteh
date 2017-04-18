package com.salimov.ecoteh.controller.superadmin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;

import static com.salimov.ecoteh.mocks.MockConstants.ANY_STRING;
import static com.salimov.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static com.salimov.ecoteh.mocks.controller.MockSuperadminController.getStyleController;
import static org.junit.Assert.assertEquals;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class StyleControllerTest {

    private static StyleController controller;

    @BeforeClass
    public static void setUp() {
        controller = getStyleController();
    }

    @Test
    public void whenGetStylesToEditThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.getStylesToEdit(),
                "superadmin/style/edit",
                new String[] { "styles", "main_company", "categories", "favicon" }
        );
    }

    @Test
    public void whenUpdateStylesThenReturnSomeModelAndView() {
        String viewName = controller.updateStyles(ANY_STRING);
        assertEquals(viewName, "redirect:/superadmin/style");
    }

    @Test(expected = IllegalMappingException.class)
    public void whenUpdateStylesByGetMethodThenReturnThrowException() {
        controller.updateStyles();
    }

    @Test
    public void whenRollbackStylesThenReturnSomeModelAndView() {
        String viewName = controller.rollbackStyles();
        assertEquals(viewName, "redirect:/superadmin/style");
    }
}
