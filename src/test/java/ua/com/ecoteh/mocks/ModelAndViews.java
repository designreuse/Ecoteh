package ua.com.ecoteh.mocks;

import org.junit.Ignore;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static org.junit.Assert.*;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

@Ignore
public final class ModelAndViews {

    public static void checkModelAndView(
            final ModelAndView modelAndView,
            final String view
    ) {
        checkModelAndView(modelAndView, view, null);
    }

    public static void checkModelAndView(
            final ModelAndView modelAndView,
            final String view,
            final String[] keys
    ) {
        assertNotNull(modelAndView);
        checkView(modelAndView, view);
        checkKeys(modelAndView, keys);
    }

    private static void checkView(final ModelAndView modelAndView, final String view) {
        if (isNotEmpty(view)) {
            assertEquals(modelAndView.getViewName(), view);
        }
    }

    private static void checkKeys(final ModelAndView modelAndView, final String[] keys) {
        if (isNotEmpty(keys)) {
            final Map<String, Object> map = modelAndView.getModel();
            assertTrue(map.size() >= keys.length);
            for (String key : keys) {
                assertTrue(map.containsKey(key));
            }
        }
    }
}
