package com.salimov.ecoteh.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Dispatcher servlet that is responsible for initializing
 * Spring MVC and mapping for URL.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * Encoding parameter for the FilterRegistration.Dynamic.
     */
    private static final String ENCODING = "UTF-8";

    /**
     * Force encoding parameter for the FilterRegistration.Dynamic.
     */
    private static final boolean FORCE_ENCODING = true;

    /**
     * Boolean parameter - is match after mapping for url patterns.
     */
    private static final boolean MAPPING_FOR_URL_IS_MATH_AFTER = true;

    /**
     * URL patterns for the FilterRegistration.Dynamic.
     */
    private static final String MAPPING_FOR_URL_PATTERNS = "/*";

    /**
     * Boolean parameter - is throw exception if no handler found.
     */
    private static final boolean THROW_EXCEPTION_IF_NO_HANDLER_FOUND = true;

    /**
     * Specify @Configuration and/or @Component classes to be provided
     * to the createServletApplicationContext() dispatcher servlet
     * application context.
     *
     * @return the configuration classes for the dispatcher servlet
     * application context or null if all configuration
     * is specified through root config classes.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
                WebConfig.class
        };
    }

    /**
     * Specify @Configuration and/or @Component classes to be provided
     * to the createRootApplicationContext() root application context.
     *
     * @return the configuration classes for the root application context,
     * or null if creation and registration of a root context
     * is not desired.
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                RootConfig.class,
                SecurityConfig.class,
                ContentConfig.class,
                CaptchaConfig.class
        };
    }

    /**
     * Specify the servlet mapping(s) for the DispatcherServlet;
     * for example "/", "/app", etc.
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * Configure the given ServletContext with any servlets,
     * filters, listeners context-params and attributes necessary
     * for initializing this web application.
     *
     * @param servletContext the ServletContext to initialize
     * @throws ServletException if any call against the given ServletContext throws a ServletException.
     */
    @Override
    public void onStartup(final ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        final FilterRegistration.Dynamic filter =
                servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
        filter.setInitParameter("encoding", ENCODING);
        filter.setInitParameter("forceEncoding", Boolean.toString(FORCE_ENCODING));
        filter.addMappingForUrlPatterns(
                null,
                MAPPING_FOR_URL_IS_MATH_AFTER,
                MAPPING_FOR_URL_PATTERNS
        );
    }

    /**
     * Create a DispatcherServlet (or other kind of FrameworkServlet-derived
     * dispatcher) with the specified WebApplicationContext}.
     * <p>Note: This allows for any FrameworkServlet subclass as of 4.2.3.
     * Previously, it insisted on returning a DispatcherServlet
     * or subclass thereof.
     */
    @Override
    protected DispatcherServlet createDispatcherServlet(
            final WebApplicationContext webApplicationContext
    ) {
        final DispatcherServlet dispatcherServlet =
                (DispatcherServlet) super.createDispatcherServlet(webApplicationContext);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(THROW_EXCEPTION_IF_NO_HANDLER_FOUND);
        return dispatcherServlet;
    }
}
