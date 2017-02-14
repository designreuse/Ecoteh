package com.salimov.yurii.mocks.properties;

import com.salimov.yurii.util.properties.ContentProperties;
import com.salimov.yurii.util.properties.ContentPropertiesImpl;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class MockContentProperties {

    private static ContentProperties contentProperties;

    public static ContentProperties getContentProperties() {
        if (contentProperties == null) {
            initContentProperties();
        }
        return contentProperties;
    }

    private static void initContentProperties() {
        contentProperties = new ContentPropertiesImpl(
                "catalinaHome", "contentType",
                "prefix", "suffix", true,
                "resourcesUrl", "resourcesLocation",
                10485760L, "loginRequest",
                "loginViewName", "projectDirectory"
        );
    }
}
