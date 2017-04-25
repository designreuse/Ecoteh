package ua.com.ecoteh.util.properties;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public final class ContentPropertiesImplTest {

    private ContentProperties contentProperties;

    @Before
    public void beforeTest() {
        this.contentProperties = new ContentPropertiesImpl(
                "catalinaHome",
                "contentType",
                "prefix",
                "suffix",
                true,
                "resourcesUrl",
                "resourcesLocation",
                50000L,
                "loginRequest",
                "loginViewName",
                "projectDirectory"
        );
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
