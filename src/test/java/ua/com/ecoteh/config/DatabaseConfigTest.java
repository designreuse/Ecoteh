package ua.com.ecoteh.config;

import org.junit.Test;
import org.junit.runner.RunWith;
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

    @Test
    public void dataSourceTest() throws Exception {
        assertNotNull(new DatabaseConfig().dataSource());
    }

    @Test
    public void jpaVendorAdapterTest() throws Exception {
        assertNotNull(new DatabaseConfig().hibernateJpaVendorAdapter());
    }

    @Test
    public void entityManagerFactoryTest() throws Exception {
        assertNotNull(
                new DatabaseConfig().entityManagerFactory(
                        mock(DataSource.class),
                        mock(HibernateJpaVendorAdapter.class)
                )
        );
    }

    @Test
    public void transactionManagerTest() {
        assertNotNull(
                new DatabaseConfig().transactionManager(
                        mock(EntityManagerFactory.class)
                )
        );
    }
}
