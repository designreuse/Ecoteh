package ua.com.ecoteh.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.com.ecoteh.service.data.UserService;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
public final class SecurityConfigTest {

    @Autowired
    private UserService userService;

    @Test
    public void serviceTest() {
        assertNotNull(this.userService);
    }

    @Test
    public void configTest() {
        assertNotNull(new SecurityConfig());
    }
}
