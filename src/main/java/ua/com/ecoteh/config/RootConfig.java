package ua.com.ecoteh.config;

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
 * the package "com.salimov.ecoteh.repository" and set to interact with
 * the database in memory, using the JPA.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ua.com.ecoteh.repository")
@ComponentScan(basePackages = "ua.com.ecoteh.entity")
@PropertySource("classpath:database.properties")
public class RootConfig {

    /**
     * The driver for connection to the database.
     */
    @Value("${jdbc.driver}")
    private String jdbcDriver;

    /**
     * The URL of driver for connection to the database.
     */
    @Value("${jdbc.driver.url}")
    private String jdbcDriverUrl;

    /**
     * The host ip where staying database.
     */
    @Value("${database.host.ip}")
    private String hostIp;

    /**
     * The host port where staying database.
     */
    @Value("${database.host.port}")
    private String hostPort;

    /**
     * The database name.
     */
    @Value("${database.name}")
    private String databaseName;

    /**
     * The user name which will be work with database.
     */
    @Value("${database.username}")
    private String databaseUsername;

    /**
     * The user password which will be work with database.
     */
    @Value("${database.password}")
    private String databasePassword;

    /**
     * It is use SSL.
     */
    @Value("${database.use-ssl}")
    private boolean useSsl;

    /**
     * It is use Unicode.
     */
    @Value("${database.use-unicode}")
    private boolean useUnicode;

    /**
     * The character encoding.
     */
    @Value("${database.character-encoding}")
    private String characterEncoding;

    /**
     * It is use JDBC compliant timezone shift.
     */
    @Value("${database.use-jdbc-compliant-timezone-shift}")
    private boolean useJdbcCompliantTimezoneShift;

    /**
     * It is use legacy datetime code.
     */
    @Value("${database.use-legacy-datetime-code}")
    private boolean useLegacyDatetimeCode;

    /**
     * The server timezone value.
     */
    @Value("${database.server-timezone}")
    private String serverTimezone;

    /**
     * The initial size of the connection pool.
     */
    @Value("${database.initial-size}")
    private int initialSize;

    /**
     * The maximum number of active connections that can be allocated
     * at the same time.
     */
    @Value("${database.max-active}")
    private int maxActive;

    /**
     * This property determines whether or not the pool will validate
     * objects before they are borrowed from the pool.
     */
    @Value("${database.test-on-borrow}")
    private boolean testOnBorrow;

    /**
     * The SQL query that will be used to validate connections from
     * this pool before returning them to the caller.
     */
    @Value("${database.validation-query}")
    private String validationQuery;

    /**
     * An Hibernate SQL dialect for database.
     */
    @Value("${hibernate.dialect}")
    private String hibernateDialect;

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
     * @param factory the object of class which implements
     *                EntityManagerFactory.
     * @return Returns the transaction manager.
     */
    @Bean
    public JpaTransactionManager transactionManager(final EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }

    /**
     * Create the entity manager factory.
     *
     * @param dataSource the object of the DataSource class with
     *                   configurations for to connection to the database.
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
     * a physical connection to a database.
     *
     * @return The object of the DataSource class.
     */
    @Bean
    public DataSource dataSource() {
        final BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(this.jdbcDriver);
        dataSource.setUrl(getDataSourceUrl());
        dataSource.setConnectionProperties(createDatabaseConnectionProperties());
        dataSource.setUsername(this.databaseUsername);
        dataSource.setPassword(this.databasePassword);
        dataSource.setInitialSize(this.initialSize);
        dataSource.setMaxActive(this.maxActive);
        dataSource.setTestOnBorrow(this.testOnBorrow);
        dataSource.setValidationQuery(this.validationQuery);
        return dataSource;
    }

    /**
     * Creates and returns setting adapter (JPA provider)
     * to connect to a database.
     *
     * @return The object of the HibernateJpaVendorAdapter class.
     */
    @Bean
    public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
        final HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(this.isShowSql);
        adapter.setGenerateDdl(this.isGenerateDdl);
        adapter.setDatabasePlatform(this.hibernateDialect);
        return adapter;
    }

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

    /**
     * Creates and returns datasource URL.
     *
     * @return The datasource URL.
     */
    private String getDataSourceUrl() {
        return this.jdbcDriverUrl + "://" + this.hostIp +
                ":" + this.hostPort + "/" + this.databaseName;
    }

    /**
     * Creates and returns a properties for database connection.
     *
     * @return The object of String class with properties.
     */
    private String createDatabaseConnectionProperties() {
        return "useSSL=" + this.useSsl +
                ";useUnicode=" + this.useUnicode +
                ";characterEncoding=" + this.characterEncoding +
                ";useJDBCCompliantTimezoneShift=" + this.useJdbcCompliantTimezoneShift +
                ";useLegacyDatetimeCode=" + this.useLegacyDatetimeCode +
                ";serverTimezone=" + this.serverTimezone;
    }
}
