package com.salimov.yurii.mocks.dao.test;

import com.salimov.yurii.dao.interfaces.UserDao;
import com.salimov.yurii.entity.User;
import com.salimov.yurii.mocks.MockConstants;
import com.salimov.yurii.mocks.dao.MockDAO;
import com.salimov.yurii.mocks.enity.MockEntity;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static com.salimov.yurii.mocks.enity.MockEntity.getUsers;

public class MockUserDAOTest extends MockDataDAOTest<User> {

    private UserDao dao;

    @Before
    public void initUserDao() {
        this.dao = MockDAO.getUserDAO();
    }

    @Test
    public void whenGetByInvalidNameThenReturnNull() {
        User user = this.dao.getByName(null);
        assertNull(user);

        user = this.dao.getByName(MockConstants.ANY_STRING);
        assertNull(user);
    }

    @Test
    public void whenGetValidNameThenReturnSomeUser() {
        User user = this.dao.getByName(MockConstants.NAME);
        assertNotNull(user);
    }

    @Test
    public void whenGetByInvalidUrlThenReturnNull() {
        User user = this.dao.getByUrl(null);
        assertNull(user);

        user = this.dao.getByUrl(MockConstants.ANY_STRING);
        assertNull(user);
    }

    @Test
    public void whenGetValidUrlThenReturnSomeUser() {
        User user = this.dao.getByUrl(MockConstants.URL);
        assertNotNull(user);
    }

    @Test
    public void whenGetByInvalidLoginThenReturnNull() {
        User user = this.dao.getByLogin(null);
        assertNull(user);

        user = this.dao.getByLogin(MockConstants.ANY_STRING);
        assertNull(user);
    }

    @Test
    public void whenGetValidLoginThenReturnSomeUser() {
        User user = this.dao.getByLogin(MockConstants.LOGIN);
        assertNotNull(user);
    }

    @Test
    public void whenGetByInvalidPhoneThenReturnNull() {
        User user = this.dao.getByPhone(null);
        assertNull(user);

        user = this.dao.getByPhone(MockConstants.ANY_STRING);
        assertNull(user);
    }

    @Test
    public void whenGetValidPhoneThenReturnSomeUser() {
        User user = this.dao.getByPhone(MockConstants.PHONE);
        assertNotNull(user);
    }

    @Test
    public void whenGetByInvalidEmailThenReturnNull() {
        User user = this.dao.getByEmail(null);
        assertNull(user);

        user = this.dao.getByEmail(MockConstants.ANY_STRING);
        assertNull(user);
    }

    @Test
    public void whenGetValidEmailThenReturnSomeUser() {
        User user = this.dao.getByEmail(MockConstants.EMAIL);
        assertNotNull(user);
    }

    @Ignore
    @Override
    protected UserDao getDao() {
        return this.dao;
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
