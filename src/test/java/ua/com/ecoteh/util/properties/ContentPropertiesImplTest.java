package ua.com.ecoteh.util.properties;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public final class ContentPropertiesImplTest {

    private static ContentProperties contentProperties;

    @BeforeClass
    public static void beforeTest() {
        contentProperties = new ContentPropertiesImpl(
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
    public void constructorWithNullProperties() {
        new ContentPropertiesImpl(null, null, null, null, true, null, null, -1L, null, null, null);
    }

    @Test
    public void constructorWithEmptyProperties() {
        new ContentPropertiesImpl("", "", "", "", true, "", "", -1L, "", "", "");
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
