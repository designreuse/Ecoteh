package com.salimov.yurii.config;

import org.junit.Test;
import com.salimov.yurii.entity.User;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static com.salimov.yurii.mocks.MockConstants.ANY_STRING;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
public final class DefaultConfigTest {

    @Test
    public void whenGetDefaultUserWithInvalidUsernameThenReturnNull() {
        User user = DefaultConfig.getDefaultUser(null);
        assertNull(user);
        user = DefaultConfig.getDefaultUser("");
        assertNull(user);
        user = DefaultConfig.getDefaultUser(" ");
        assertNull(user);
        user = DefaultConfig.getDefaultUser("   ");
        assertNull(user);
        user = DefaultConfig.getDefaultUser(ANY_STRING);
        assertNull(user);
    }

    @Test
    public void whenGetDefaultUserWithValidUsernameThenReturnSomeUser() {
        User user = DefaultConfig.getDefaultUser("admin");
        assertNotNull(user);
        user = DefaultConfig.getDefaultUser("super");
        assertNotNull(user);
    }

    @Test
    public void whenDefaultIsAdminEnabledThenReturnTrue() {
        assertTrue(DefaultConfig.isAdminEnabled());
    }

    @Test
    public void whenDefaultIsClientEnabledThenReturnTrue() {
        assertTrue(DefaultConfig.isClientEnabled());
    }

    @Test
    public void whenDefaultSuperOnOrOffThenToDoNothing() {
        DefaultConfig.superOff();
        assertTrue(DefaultConfig.isAdminEnabled());
        assertTrue(DefaultConfig.isClientEnabled());
        DefaultConfig.superOn();
        assertTrue(DefaultConfig.isAdminEnabled());
        assertTrue(DefaultConfig.isClientEnabled());
    }

    @Test
    public void whenDefaultOnOrOffThenToDoNothing() {
        DefaultConfig.off();
        assertTrue(DefaultConfig.isClientEnabled());
        DefaultConfig.on();
        assertTrue(DefaultConfig.isClientEnabled());
    }
}
