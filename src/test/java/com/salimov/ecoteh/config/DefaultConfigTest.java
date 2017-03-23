package com.salimov.ecoteh.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.salimov.ecoteh.mocks.MockConstants.ANY_STRING;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
public final class DefaultConfigTest {

    @Test
    public void whenGetDefaultUserWithNullUsernameThenReturnNull() {
        assertNull(DefaultConfig.getDefaultUser(null));
    }

    @Test
    public void whenGetDefaultUserWithBlankUsernameThenReturnNull() {
        assertNull(DefaultConfig.getDefaultUser(""));
        assertNull(DefaultConfig.getDefaultUser(" "));
        assertNull(DefaultConfig.getDefaultUser("   "));
    }

    @Test
    public void whenGetDefaultUserWithUnknownUsernameThenReturnNull() {
        assertNull(DefaultConfig.getDefaultUser(ANY_STRING));
    }

    @Test
    public void whenGetAdminUserThenReturnSomeUser() {
        assertNotNull(DefaultConfig.getDefaultUser("admin"));
    }

    @Test
    public void whenGetSuperAdminUserThenReturnSomeUser() {
        assertNotNull(DefaultConfig.getDefaultUser("superadmin"));
    }
}
