package ua.com.ecoteh.mocks.enity.test;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.user.User;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.enity.MockModels.getUser;
import static ua.com.ecoteh.mocks.enity.MockModels.getUsers;

public final class MockUserTest extends MockModelTest<User> {

    private static User user;
    private static Collection<User> users;

    @BeforeClass
    public static void beforeClass() {
        user = getUser();
        users = getUsers();
    }

    @Test
    public void whenGetNameThenReturnNotEmpty() {
        assertFalse(user.getName().isEmpty());
    }

    @Test
    public void whenGetUrlThenReturnNotEmpty() {
        assertFalse(user.getUrl().isEmpty());
    }

    @Test
    public void whenGetLoginThenReturnNotEmpty() {
        assertFalse(user.getLogin().isEmpty());
    }

    @Test
    public void whenGetPasswordThenReturnNotEmpty() {
        assertFalse(user.getPassword().isEmpty());
    }

    @Test
    public void whenGetDescriptionThenReturnNotEmpty() {
        assertFalse(user.getDescription().isEmpty());
    }

    @Test
    public void whenGetContactsThenReturnNotNull() {
        assertNotNull(user.getContacts());
    }

    @Test
    public void whenGetPhotoThenReturnNotNull() {
        assertNotNull(user.getPhoto());
    }

    @Test
    public void whenGetRoleThenReturnNotNull() {
        assertNotNull(user.getRole());
    }

    @Ignore
    @Override
    protected User getObject() {
        return user;
    }

    @Ignore
    @Override
    protected Collection<User> getObjects() {
        return users;
    }
}
