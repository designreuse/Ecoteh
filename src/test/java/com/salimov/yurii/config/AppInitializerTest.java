package com.salimov.yurii.config;

import org.junit.Test;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public final class AppInitializerTest {

    @Test
    public void getServletConfigClassesTest() {
        assertNotNull(
                new AppInitializer().getServletConfigClasses()
        );
    }

    @Test
    public void getRootConfigClassesTest() {
        assertNotNull(
                new AppInitializer().getRootConfigClasses()
        );
    }

    @Test
    public void getServletMappingsTest() {
        assertNotNull(
                new AppInitializer().getServletMappings()
        );
    }

    @Test
    public void createDispatcherServletTest() {
        assertNotNull(
                new AppInitializer().createDispatcherServlet(
                        mock(WebApplicationContext.class)
                )
        );
    }
}
