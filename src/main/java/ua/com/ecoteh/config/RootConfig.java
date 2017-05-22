package ua.com.ecoteh.config;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * Class with a basic beans: BeanPostProcessor, CommonsMultipartResolver,
 * PropertySourcesPlaceholderConfigurer.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Configuration
@ComponentScan(basePackages = "ua.com.ecoteh.entity")
public class RootConfig {

    /**
     * Creates and return object of the PersistenceExceptionTranslationPostProcessor class.
     * Intercepts any JPA or Hibernate exceptions in Spring exception.
     *
     * @return The new object of class which implements BeanPostProcessor
     * - PersistenceExceptionTranslationPostProcessor.
     */
    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    /**
     * Creates and return object of the CommonsMultipartResolver class
     * for save files in the Servlet Container temporary catalog.
     *
     * @return The new object of the CommonsMultipartResolver class.
     */
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }

    /**
     * Creates and return object of the PropertySourcesPlaceholderConfigurer class.
     *
     * @return The new object of the PropertySourcesPlaceholderConfigurer class.
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
