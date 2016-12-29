package com.salimov.yurii.mocks.enity.test;

import com.salimov.yurii.mocks.enity.MockEntity;
import org.junit.Ignore;
import org.junit.Test;
import com.salimov.yurii.entity.User;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static com.salimov.yurii.mocks.enity.MockEntity.getUsers;

public class MockUserTest extends MockModelTest<User> {

    @Test
    @Override
    public void whenGetModelThenReturnValidModel() {
        final User user = MockEntity.getUser();
        assertNotNull(user.getId());
        assertNotNull(user.getName());
        assertNotNull(user.getLogin());
        assertNotNull(user.getPassword());
        assertNotNull(user.getEmail());
        assertNotNull(user.getPhone());
        assertNotNull(user.getVkontakte());
        assertNotNull(user.getFacebook());
        assertNotNull(user.getTwitter());
        assertNotNull(user.getSkype());
        assertNotNull(user.getDescription());
        assertNull(user.getPhoto());
    }

    @Ignore
    @Override
    protected User getObject() {
        return MockEntity.getUser();
    }

    @Ignore
    @Override
    protected Collection<User> getObjects() {
        return MockEntity.getUsers();
    }

    @Ignore
    @Override
    protected Collection<User> getObjects(int size) {
        return MockEntity.getUsers(size);
    }
}
