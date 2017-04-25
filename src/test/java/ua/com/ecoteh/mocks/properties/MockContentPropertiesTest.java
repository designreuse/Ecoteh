package ua.com.ecoteh.mocks.properties;

import ua.com.ecoteh.util.properties.ContentProperties;
import org.junit.Before;
import org.junit.Test;

import static ua.com.ecoteh.mocks.properties.MockContentProperties.getContentProperties;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class MockContentPropertiesTest {

    private ContentProperties contentProperties;

    @Before
    public void beforeTests() {
        contentProperties = getContentProperties();
    }

    @Test
    public void contentPropertiesNotNull() {
        assertNotNull(this.contentProperties);
    }

    @Test
    public void whenGetCatalinaHomeThenReturnNotNull() {
        assertNotNull(this.contentProperties.getCatalinaHome());
    }

    @Test
    public void whenGetPrefixThenReturnNotNull() {
        assertNotNull(this.contentProperties.getPrefix());
    }

    @Test
    public void whenGetSuffixThenReturnNotNull() {
        assertNotNull(this.contentProperties.getSuffix());
    }

    @Test
    public void whenIsExposeContextBeansAsAttributesThenReturnTrue() {
        assertTrue(this.contentProperties.isExposeContextBeansAsAttributes());
    }

    @Test
    public void whenGeResourcesUrlThenReturnNotNull() {
        assertNotNull(this.contentProperties.getResourcesUrl());
    }

    @Test
    public void whenResourcesLocationThenReturnNotNull() {
        assertNotNull(this.contentProperties.getResourcesLocation());
    }

    @Test
    public void whenGetMaxFileSizeThenReturnNotNull() {
        assertNotNull(this.contentProperties.getMaxFileSize());
        assertTrue(this.contentProperties.getMaxFileSize() > 0);
    }

    @Test
    public void whenGetLoginRequestThenReturnNotNull() {
        assertNotNull(this.contentProperties.getLoginRequest());
    }

    @Test
    public void whenGetLoginViewNameThenReturnNotNull() {
        assertNotNull(this.contentProperties.getLoginViewName());
    }

    @Test
    public void whenGetProjectDirectoryThenReturnNotNull() {
        assertNotNull(this.contentProperties.getProjectDirectory());
    }

    @Test
    public void whenGetResourcesAbsolutePathThenReturnNotNull() {
        assertNotNull(this.contentProperties.getResourcesAbsolutePath());
    }

    @Test
    public void whenGetContentTypeThenReturnNotNull() {
        assertNotNull(this.contentProperties.getContentType());
    }
}
