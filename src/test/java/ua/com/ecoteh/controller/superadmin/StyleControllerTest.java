package ua.com.ecoteh.controller.superadmin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.service.data.StyleService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;
import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.service.data.MockServices.getStyleService;
import static ua.com.ecoteh.mocks.service.fabrica.MockMVFabric.getCacheMVFabric;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class StyleControllerTest {

    private static StyleController controller;

    @BeforeClass
    public static void setUp() {
        final MainMVFabric fabric = getCacheMVFabric();
        final StyleService styleService = getStyleService();
        controller = new StyleController(fabric, styleService);
    }

    @Test
    public void whenGetStylesToEditThenReturnSomeModelAndView() {
        final String viewName = "style/edit";
        final String[] keys = { "styles", "main_company", "categories", "favicon" };
        final ModelAndView modelAndView = controller.getStylesToEdit();
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenUpdateStylesThenReturnSomeModelAndView() {
        final String expectedViewName = controller.updateStyles(ANY_STRING);
        final String actualViewName = "redirect:/superadmin/style";
        assertEquals(expectedViewName, actualViewName);
    }

    @Test(expected = IllegalMappingException.class)
    public void whenUpdateStylesByGetMethodThenReturnThrowException() {
        controller.updateStyles();
    }

    @Test
    public void whenRollbackStylesThenReturnSomeModelAndView() {
        final String expectedViewName = controller.rollbackStyles();
        final String actualViewName = "redirect:/superadmin/style";
        assertEquals(expectedViewName, actualViewName);
    }
}
