package ua.com.ecoteh.mocks.enity.test;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.user.UserEntity;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getUserEntities;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getUserEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MockUserEntityTest extends MockModelEntityTest<UserEntity> {

    private static UserEntity user;
    private static Collection<UserEntity> users;

    @BeforeClass
    public static void beforeClass() {
        user = getUserEntity();
        users = getUserEntities();
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
        assertNotNull(user.getContactsEntity());
    }

    @Test
    public void whenGetPhotoThenReturnNotNull() {
        assertNotNull(user.getPhotoEntity());
    }

    @Test
    public void whenGetRoleThenReturnNotNull() {
        assertNotNull(user.getRole());
    }

    @Ignore
    @Override
    protected UserEntity getObject() {
        return user;
    }

    @Ignore
    @Override
    protected Collection<UserEntity> getObjects() {
        return users;
    }
}
