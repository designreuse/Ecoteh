package ua.com.ecoteh.config;

import org.junit.Test;

import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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

    @Test
    public void whenGetAdminThenReturnSomeUser() {
        assertNotNull(DefaultConfig.getDefaultAdmin());
    }

    @Test
    public void whenGetSuperAdminThenReturnSomeUser() {
        assertNotNull(DefaultConfig.getSuperAdmin());
    }
}
