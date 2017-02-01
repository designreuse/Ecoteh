package com.salimov.yurii.config;

import com.salimov.yurii.util.properties.ContentProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Class Spring MVC configuration. Spring Indicates where the views components,
 * and how to display them. Class is the source of bean definitions.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see AppInitializer
 * @see RootConfig
 */
@Configuration
@EnableWebMvc
@ComponentScan(
        basePackages = {
                "com.salimov.yurii.controller",
                "com.salimov.yurii.config"
        }
)
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * The implementation of the interface describes
     * the methods for getting application properties..
     */
    private final ContentProperties properties;

    /**
     * Constructor.
     *
     * @param properties implementation of the {@link ContentProperties}
     *                   interface.
     */
    @Autowired
    public WebConfig(final ContentProperties properties) {
        super();
        this.properties = properties;
    }

    /**
     * Indicates to Spring where are the views components,
     * and how to display them.
     *
     * @return The object of the InternalResourceViewResolver class.
     */
    @Bean
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver viewResolver
                = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setContentType(this.properties.getContentType());
        viewResolver.setPrefix(this.properties.getPrefix());
        viewResolver.setSuffix(this.properties.getSuffix());
        viewResolver.setExposeContextBeansAsAttributes(
                this.properties.isExposeContextBeansAsAttributes()
        );
        return viewResolver;
    }

    /**
     * Add handlers to serve static resources such as images, js, and, css
     * files from specific locations under web application root, the classpath,
     * and others.
     *
     * @param resource The object of the ResourceHandlerRegistry class.
     */
    @Override
    public void addResourceHandlers(
            final ResourceHandlerRegistry resource) {
        resource.addResourceHandler(this.properties.getResourcesUrl()
        ).addResourceLocations(this.properties.getResourcesLocation());
    }

    /**
     * Setting the login controller.
     * Configure simple automated controllers pre-configured with the response
     * status code and/or a view to render the response body. This is useful in
     * cases where there is no need for custom controller logic -- e.g. render a
     * home page, perform simple site URL redirects, return a 404 status with
     * HTML content, a 204 with no content, and more.
     *
     * @param controller The object of the ViewControllerRegistry class.
     */
    @Override
    public void addViewControllers(
            final ViewControllerRegistry controller
    ) {
        controller.addViewController(
                this.properties.getLoginRequest()
        ).setViewName(
                this.properties.getLoginViewName()
        );
        controller.setOrder(
                Ordered.HIGHEST_PRECEDENCE
        );
    }
}
