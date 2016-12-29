package com.salimov.yurii.config;

import com.salimov.yurii.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public final class RootConfigTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Test
    public void repositoryTest() {
        assertNotNull(articleRepository);
        assertNotNull(categoryRepository);
        assertNotNull(companyRepository);
        assertNotNull(photoRepository);
        assertNotNull(responseRepository);
        assertNotNull(sectionRepository);
        assertNotNull(userRepository);
        assertNotNull(videoRepository);
    }

    @Test
    public void dataSourceTest() throws Exception {
        assertNotNull(
                new RootConfig().dataSource()
        );
    }

    @Test
    public void jpaVendorAdapterTest() throws Exception {
        assertNotNull(
                new RootConfig().hibernateJpaVendorAdapter()
        );
    }

    @Test
    public void entityManagerFactoryTest() throws Exception {
        assertNotNull(
                new RootConfig().entityManagerFactory(
                        mock(DataSource.class),
                        mock(HibernateJpaVendorAdapter.class)
                )
        );
    }

    @Test
    public void transactionManagerTest() {
        assertNotNull(
                new RootConfig().transactionManager(
                        mock(EntityManagerFactory.class)
                )
        );
    }

    @Test
    public void persistenceTranslationTest() {
        assertNotNull(
                new RootConfig().persistenceTranslation()
        );
    }

    @Test
    public void multipartResolverTest() {
        assertNotNull(
                new RootConfig().multipartResolver()
        );
    }

    @Test
    public void propertyConfigInDev() {
        assertNotNull(
                RootConfig.propertyConfigInDev()
        );
    }
}
