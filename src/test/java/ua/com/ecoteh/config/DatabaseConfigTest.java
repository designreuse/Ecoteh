package ua.com.ecoteh.config;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
public class DatabaseConfigTest {

    private static DatabaseConfig config;

    @BeforeClass
    public static void beforeClass() {
        config = new DatabaseConfig();
    }

    @Test
    public void dataSourceTest() throws Exception {
        assertNotNull(config.dataSource());
    }

    @Test
    public void jpaVendorAdapterTest() throws Exception {
        assertNotNull(config.hibernateJpaVendorAdapter());
    }

    @Test
    public void entityManagerFactoryTest() throws Exception {
        final DataSource dataSource = mock(DataSource.class);
        final HibernateJpaVendorAdapter adapter = mock(HibernateJpaVendorAdapter.class);
        LocalContainerEntityManagerFactoryBean lcemb = config.entityManagerFactory(dataSource, adapter);
        assertNotNull(lcemb);
    }

    @Test
    public void transactionManagerTest() {
        final EntityManagerFactory factory = mock(EntityManagerFactory.class);
        final JpaTransactionManager manager = config.transactionManager(factory);
        assertNotNull(manager);
    }
}
