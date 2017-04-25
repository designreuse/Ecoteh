package ua.com.ecoteh.config;

import ua.com.ecoteh.util.properties.ContentProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Class Spring MVC configuration. Spring Indicates where the views components,
 * and how to display them. Class is the source of bean definitions.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Configuration
@EnableWebMvc
@ComponentScan(
        basePackages = {
                "ua.com.ecoteh.controller",
                "ua.com.ecoteh.config"
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
     * @param properties implementation of the {@link ContentProperties}  interface.
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
        final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
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
     * @param registry The object of the ResourceHandlerRegistry class.
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler(this.properties.getResourcesUrl())
                .addResourceLocations(this.properties.getResourcesLocation());
    }
}
