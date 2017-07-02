package ua.com.ecoteh.mocks.properties;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.config.properties.ContentProperties;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static ua.com.ecoteh.mocks.properties.MockContentProperties.getContentProperties;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class MockContentPropertiesTest {

    private static ContentProperties contentProperties;

    @BeforeClass
    public static void beforeTests() {
        contentProperties = getContentProperties();
    }

    @Test
    public void contentPropertiesNotNull() {
        assertNotNull(contentProperties);
    }

    @Test
    public void whenGetCatalinaHomeThenReturnNotNull() {
        assertNotNull(contentProperties.getCatalinaHome());
    }

    @Test
    public void whenGetPrefixThenReturnNotNull() {
        assertNotNull(contentProperties.getPrefix());
    }

    @Test
    public void whenGetSuffixThenReturnNotNull() {
        assertNotNull(contentProperties.getSuffix());
    }

    @Test
    public void whenIsExposeContextBeansAsAttributesThenReturnTrue() {
        assertTrue(contentProperties.isExposeContextBeansAsAttributes());
    }

    @Test
    public void whenGeResourcesUrlThenReturnNotNull() {
        assertNotNull(contentProperties.getResourcesUrl());
    }

    @Test
    public void whenResourcesLocationThenReturnNotNull() {
        assertNotNull(contentProperties.getResourcesLocation());
    }

    @Test
    public void whenGetMaxFileSizeThenReturnNotNull() {
        assertNotNull(contentProperties.getMaxFileSize());
        assertTrue(contentProperties.getMaxFileSize() > 0);
    }

    @Test
    public void whenGetLoginRequestThenReturnNotNull() {
        assertNotNull(contentProperties.getLoginRequest());
    }

    @Test
    public void whenGetLoginViewNameThenReturnNotNull() {
        assertNotNull(contentProperties.getLoginViewName());
    }

    @Test
    public void whenGetProjectDirectoryThenReturnNotNull() {
        assertNotNull(contentProperties.getProjectDirectory());
    }

    @Test
    public void whenGetResourcesAbsolutePathThenReturnNotNull() {
        assertNotNull(contentProperties.getResourcesAbsolutePath());
    }

    @Test
    public void whenGetContentTypeThenReturnNotNull() {
        assertNotNull(contentProperties.getContentType());
    }
}
