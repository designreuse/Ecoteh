package ua.com.ecoteh.config;

import org.junit.Test;

import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public final class DefaultAccountsTest {

    @Test
    public void whenGetDefaultUserWithNullUsernameThenReturnNull() {
        assertNull(DefaultAccounts.get(null));
    }

    @Test
    public void whenGetDefaultUserWithBlankUsernameThenReturnNull() {
        assertNull(DefaultAccounts.get(""));
        assertNull(DefaultAccounts.get(" "));
        assertNull(DefaultAccounts.get("   "));
    }

    @Test
    public void whenGetDefaultUserWithUnknownUsernameThenReturnNull() {
        assertNull(DefaultAccounts.get(ANY_STRING));
    }

    @Test
    public void whenGetAdminUserThenReturnSomeUser() {
        assertNotNull(DefaultAccounts.get("admin"));
    }

    @Test
    public void whenGetSuperAdminUserThenReturnSomeUser() {
        assertNotNull(DefaultAccounts.get("superadmin"));
    }

    @Test
    public void whenGetAdminThenReturnSomeUser() {
        assertNotNull(DefaultAccounts.getDefaultAdmin());
    }

    @Test
    public void whenGetSuperAdminThenReturnSomeUser() {
        assertNotNull(DefaultAccounts.getSuperAdmin());
    }
}
