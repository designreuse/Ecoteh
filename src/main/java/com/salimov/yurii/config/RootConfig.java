package com.salimov.yurii.config;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Class of basic configurations for the Spring: DataSource, JpaVendorAdapter,
 * JpaTransactionManager, BeanPostProcessor, CommonsMultipartResolver.
 * Class is the source of bean definitions, activates the Spring transaction
 * opportunities through @Transactional. It activates the Spring Data JPA,
 * which would create a specific implementation for the repository of
 * the package "com.salimov.yurii.repository" and set to interact with
 * the database in memory, using the JPA.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.salimov.yurii.repository")
@ComponentScan(basePackages = "com.salimov.yurii.entity")
@PropertySource("classpath:database.properties")
public class RootConfig {

    /**
     * The driver for connection to the database.
     */
    @Value("${jdbc.driver}")
    private String driver;

    /**
     * The url of driver for connection to the database.
     */
    @Value("${jdbc.driver.url}")
    private String urlDriver;

    /**
     * The host ip where staying database.
     */
    @Value("${jdbc.host.ip}")
    private String hostIp;

    /**
     * The host port where staying database.
     */
    @Value("${jdbc.host.port}")
    private String hostPort;

    /**
     * The database name.
     */
    @Value("${jdbc.database}")
    private String database;

    /**
     * The user name which will be work with database.
     */
    @Value("${jdbc.username}")
    private String username;

    /**
     * The user password which will be work with database.
     */
    @Value("${jdbc.password}")
    private String password;

    /**
     * It is use SSL.
     */
    @Value("${jdbc.use-ssl}")
    private boolean useSSL;

    /**
     * It is use Unicode.
     */
    @Value("${jdbc.use-unicode}")
    private boolean useUnicode;

    /**
     * The character encoding.
     */
    @Value("${jdbc.character-encoding}")
    private String characterEncoding;

    /**
     * It is use JDBC compliant timezone shift.
     */
    @Value("${jdbc.use-jdbc-compliant-timezone-shift}")
    private boolean useJDBCCompliantTimezoneShift;

    /**
     * It is use legacy datetime code.
     */
    @Value("${jdbc.use-legacy-datetime-code}")
    private boolean useLegacyDatetimeCode;

    /**
     * The server timezone value.
     */
    @Value("${jdbc.server-timezone}")
    private String serverTimezone;

    /**
     * The initial size of the connection pool.
     */
    @Value("${jdbc.initial-size}")
    private int initialSize;

    /**
     * The maximum number of active connections that can be allocated
     * at the same time.
     */
    @Value("${jdbc.max-active}")
    private int maxActive;

    /**
     * This property determines whether or not the pool will validate
     * objects before they are borrowed from the pool.
     */
    @Value("${jdbc.test-on-borrow}")
    private boolean testOnBorrow;

    /**
     * The SQL query that will be used to validate connections from
     * this pool before returning them to the caller.
     */
    @Value("${jdbc.validation-query}")
    private String validationQuery;

    /**
     * An Hibernate SQL dialect for database.
     */
    @Value("${hibernate.dialect}")
    private String dialect;

    /**
     * It is to show SQL in the log (or in the console).
     */
    @Value("${hibernate.show-sql}")
    private boolean isShowSql;

    /**
     * It is to generate DDL after the EntityManagerFactory has been
     * initialized creating/updating all relevant tables.
     */
    @Value("${hibernate.generate-ddl}")
    private boolean isGenerateDdl;

    /**
     * The packages of entity for Entity Manager.
     */
    @Value("${hibernate.entity-packages}")
    private String entityPackages;

    /**
     * Returns the transaction manager, which is suitable for applications
     * that use a single JPA EntityManagerFactory for transactional data access.
     *
     * @param factory a object of class which implements
     *                EntityManagerFactory.
     * @return Returns the transaction manager.
     */
    @Bean
    public JpaTransactionManager transactionManager(
            final EntityManagerFactory factory
    ) {
        return new JpaTransactionManager(factory);
    }

    /**
     * Create the entity manager factory.
     *
     * @param dataSource a object of the DataSource class with
     *                   configurations for to connection
     *                   to the database.
     * @param adapter    Adapter to connect to the database.
     * @return The object of the LocalContainerEntityManagerFactoryBean class.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            final DataSource dataSource,
            final HibernateJpaVendorAdapter adapter
    ) {
        final LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setJpaVendorAdapter(adapter);
        factory.setPackagesToScan(this.entityPackages);
        return factory;
    }

    /**
     * Returns a object of class DataSource with configurations for to get
     * a physical connection to the database.
     *
     * @return The object of the DataSource class.
     */
    @Bean
    public DataSource dataSource() {
        final BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(this.driver);
        dataSource.setUrl(
                this.urlDriver + "://" + this.hostIp
                        + ":" + this.hostPort + "/" + this.database
        );
        dataSource.setConnectionProperties(
                createDatabaseConnectionProperties()
        );
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        dataSource.setInitialSize(this.initialSize);
        dataSource.setMaxActive(this.maxActive);
        dataSource.setTestOnBorrow(this.testOnBorrow);
        dataSource.setValidationQuery(this.validationQuery);
        return dataSource;
    }

    /**
     * Returns setting adapter (JPA provider) to connect to the database.
     *
     * @return The object of the HibernateJpaVendorAdapter class.
     */
    @Bean
    public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
        final HibernateJpaVendorAdapter adapter
                = new HibernateJpaVendorAdapter();
        adapter.setShowSql(this.isShowSql);
        adapter.setGenerateDdl(this.isGenerateDdl);
        adapter.setDatabasePlatform(this.dialect);
        return adapter;
    }

    /**
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
     * Return object of the CommonsMultipartResolver class for save files
     * in the Servlet Container temporary catalog.
     *
     * @return The new object of the CommonsMultipartResolver class.
     */
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }

    /**
     * Return object of the PropertySourcesPlaceholderConfigurer class .
     *
     * @return The new object of the PropertySourcesPlaceholderConfigurer class.
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * Creates database connection properties.
     *
     * @return The object of String class with properties.
     */
    private String createDatabaseConnectionProperties() {
        return "useSSL=" + this.useSSL
                + ";useUnicode=" + this.useUnicode
                + ";characterEncoding=" + this.characterEncoding
                + ";useJDBCCompliantTimezoneShift="
                + this.useJDBCCompliantTimezoneShift
                + ";useLegacyDatetimeCode=" + this.useLegacyDatetimeCode
                + ";serverTimezone=" + this.serverTimezone;
    }
}
