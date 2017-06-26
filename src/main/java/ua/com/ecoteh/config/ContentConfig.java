package ua.com.ecoteh.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ua.com.ecoteh.util.properties.ContentProperties;
import ua.com.ecoteh.util.properties.ContentPropertiesBuilder;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNull;

/**
 * The class describes a Content configurations.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
@Configuration
@PropertySource("classpath:content.properties")
public class ContentConfig {

    /**
     * The implementation of the interface describes the methods
     * for getting application properties.
     */
    private static ContentProperties contentProperties;

    /**
     * Apache Catalina home path.
     */
    @Value("${apache.catalina.home}")
    private String catalinaHome;

    /**
     * Content Type encoding.
     */
    @Value("${project.directory}")
    private String projectDirectory;

    /**
     * Content Type encoding.
     */
    @Value("${view.type}")
    private String contentType;

    /**
     * View name prefix.
     */
    @Value("${view.name-prefix}")
    private String prefix;

    /**
     * View name suffix.
     */
    @Value("${view.name-suffix}")
    private String suffix;

    /**
     * It is exposed context beans as attributes.
     */
    @Value("${view.expose_beans_as_attributes}")
    private boolean exposeContextBeansAsAttributes;

    /**
     * The url of resources.
     */
    @Value("${resources.url}")
    private String resourcesUrl;

    /**
     * The location of resources.
     */
    @Value("${resources.location}")
    private String resourcesLocation;

    /**
     * Request for authorization.
     */
    @Value("${login.request}")
    private String loginRequest;

    /**
     * Maximum file size (Mb).
     */
    @Value("${file.max.size}")
    private long maxFileSize;

    /**
     * Login view name (path).
     */
    @Value("${login.view-url}")
    private String loginViewName;

    /**
     * Return a content properties.
     *
     * @return The object with a content properties.
     */
    @Bean
    public ContentProperties contentProperties() {
        if (isNull(contentProperties)) {
            initContentProperties();
        }
        return contentProperties;
    }

    /**
     * Initializes the content properties object.
     */
    private void initContentProperties() {
        final ContentPropertiesBuilder builder = ContentProperties.getBuilder();
        builder.addCatalinaHome(this.catalinaHome).addContentType(this.contentType)
                .addPrefix(this.prefix).addSuffix(this.suffix)
                .addExposeContextBeansAsAttributes(this.exposeContextBeansAsAttributes)
                .addResourcesUrl(this.resourcesUrl).addResourcesLocation(this.resourcesLocation)
                .addLoginRequest(this.loginRequest).addLoginViewName(this.loginViewName)
                .addMaxFileSize(this.maxFileSize).addProjectDirectory(this.projectDirectory);
        contentProperties = builder.build();
    }
}
