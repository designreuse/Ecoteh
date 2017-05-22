package ua.com.ecoteh.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.com.ecoteh.repository.*;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
@SuppressWarnings("SpringJavaAutowiringInspection")
public final class RootConfigTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void articleRepositoryNotNull() {
        assertNotNull(this.articleRepository);
    }

    @Test
    public void categoryRepositoryNotNull() {
        assertNotNull(this.categoryRepository);
    }

    @Test
    public void companyRepositoryNotNull() {
        assertNotNull(this.companyRepository);
    }

    @Test
    public void fileRepositoryNotNull() {
        assertNotNull(this.fileRepository);
    }

    @Test
    public void responseRepositoryNotNull() {
        assertNotNull(this.responseRepository);
    }

    @Test
    public void userRepositoryNotNull() {
        assertNotNull(this.userRepository);
    }

    @Test
    public void persistenceTranslationTest() {
        assertNotNull(new RootConfig().persistenceTranslation());
    }

    @Test
    public void multipartResolverTest() {
        assertNotNull(new RootConfig().multipartResolver());
    }

    @Test
    public void propertyConfigInDev() {
        assertNotNull(RootConfig.propertyConfigInDev());
    }
}
