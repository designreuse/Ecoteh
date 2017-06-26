package ua.com.ecoteh.mocks.properties;

import ua.com.ecoteh.util.properties.ContentProperties;
import ua.com.ecoteh.util.properties.ContentPropertiesBuilder;

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
        final ContentPropertiesBuilder builder = ContentProperties.getBuilder();
        builder.addCatalinaHome("catalinaHome").addContentType("catalinaHome")
                .addPrefix("prefix").addSuffix("suffix")
                .addExposeContextBeansAsAttributes(true)
                .addResourcesUrl("resourcesUrl").addResourcesLocation("resourcesLocation")
                .addLoginRequest("loginRequest").addLoginViewName("loginViewName")
                .addMaxFileSize(10485760L).addProjectDirectory("projectDirectory");
        contentProperties = builder.build();
    }
}
