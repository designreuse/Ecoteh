package com.salimov.yurii.mocks.enity.test;

import com.salimov.yurii.entity.User;
import com.salimov.yurii.mocks.enity.MockEntity;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;

public final class MockUserTest extends MockModelTest<User> {

    @Test
    @Override
    public void whenGetModelThenReturnValidModel() {
        final User user = MockEntity.getUser();
        assertNotNull(user.getId());
        assertNotNull(user.getName());
        assertNotNull(user.getLogin());
        assertNotNull(user.getPassword());
        assertNotNull(user.getDescription());
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
}
