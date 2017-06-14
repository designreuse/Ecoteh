package ua.com.ecoteh.config;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public final class AppInitializerTest {

    private static AppInitializer appInitializer;

    @BeforeClass
    public static void beforeClass() {
        appInitializer = new AppInitializer();
    }

    @Test
    public void whenGetServletConfigClassesThenReturnNotEmptyArray() {
        final Class<?>[] classes = appInitializer.getServletConfigClasses();
        assertTrue(classes.length > 0);
    }

    @Test
    public void whenGgetRootConfigClassesThenReturnNotEmptyArray() {
        final Class<?>[] classes = appInitializer.getRootConfigClasses();
        assertTrue(classes.length > 0);
    }

    @Test
    public void whenGetServletMappingsThenReturnNotEmptyArray() {
        final String[] strings = appInitializer.getServletMappings();
        assertTrue(strings.length > 0);
    }

    @Test
    public void whenCreateDispatcherServletThenReturnNotNull() {
        final WebApplicationContext context = mock(WebApplicationContext.class);
        final DispatcherServlet dispatcherServlet = appInitializer.createDispatcherServlet(context);
        assertNotNull(dispatcherServlet);
    }
}
