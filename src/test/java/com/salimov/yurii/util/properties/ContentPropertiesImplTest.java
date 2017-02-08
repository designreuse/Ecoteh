package com.salimov.yurii.util.properties;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public final class ContentPropertiesImplTest {

    private ContentProperties properties;

    @Before
    public void beforeTest() {
        this.properties = new ContentPropertiesImpl(
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
    public void whenGetCatalinaHomeThenReturnNotNull() {
        assertNotNull(this.properties.getCatalinaHome());
    }

    @Test
    public void whenGetContentTypeThenReturnNotNull() {
        assertNotNull(this.properties.getContentType());
    }

    @Test
    public void whenGetPrefixThenReturnNotNull() {
        assertNotNull(this.properties.getPrefix());
    }

    @Test
    public void whenGetSuffixThenReturnNotNull() {
        assertNotNull(this.properties.getSuffix());
    }

    @Test
    public void whenIsExposeContextBeansAsAttributesThenReturnTrue() {
        assertTrue(this.properties.isExposeContextBeansAsAttributes());
    }

    @Test
    public void whenGetResourcesUrlThenReturnNotNull() {
        assertNotNull(this.properties.getResourcesUrl());
    }

    @Test
    public void whenGetResourcesLocationThenReturnNotNull() {
        assertNotNull(this.properties.getResourcesLocation());
    }

    @Test
    public void whenGetLoginRequestThenReturnNotNull() {
        assertNotNull(this.properties.getLoginRequest());
    }

    @Test
    public void whenGetLoginViewNameThenReturnNotNull() {
        assertNotNull(this.properties.getLoginViewName());
    }

    @Test
    public void whenGetProjectDirectoryThenReturnNotNull() {
        assertNotNull(this.properties.getProjectDirectory());
    }

    @Test
    public void whenGetProjectAbsolutePathThenReturnNotNull() {
        assertNotNull(this.properties.getProjectAbsolutePath());
    }

    @Test
    public void whenGetResourcesAbsolutePathThenReturnNotNull() {
        assertNotNull(this.properties.getResourcesAbsolutePath());
    }
}
