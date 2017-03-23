package com.salimov.ecoteh.mocks;

import org.junit.Ignore;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.junit.Assert.*;

@Ignore
public final class ModelAndViews {

    public static void checkModelAndView(
            final ModelAndView modelAndView,
            final  String view
    ) {
        checkModelAndView(modelAndView, view, null);
    }

    public static void checkModelAndView(
            final ModelAndView modelAndView,
            final String view,
            final String[] keys
    ) {
        assertNotNull(modelAndView);
        if (isNotBlank(view)) {
            assertEquals(modelAndView.getViewName(), view);
        }
        if (keys != null && keys.length > 0) {
            final Map<String, Object> map = modelAndView.getModel();
            assertTrue(map.size() >= keys.length);
            for (String key : keys) {
                assertTrue(map.containsKey(key));
            }
        }
    }
}
