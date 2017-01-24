package com.salimov.yurii.mocks.dao.test;

import com.salimov.yurii.dao.interfaces.UserDao;
import com.salimov.yurii.entity.User;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static com.salimov.yurii.mocks.MockConstants.*;
import static com.salimov.yurii.mocks.dao.MockDao.getUserDao;
import static com.salimov.yurii.mocks.enity.MockEntity.getUser;
import static com.salimov.yurii.mocks.enity.MockEntity.getUsers;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class MockUserDAOTest extends MockDataDAOTest<User> {

    private UserDao dao;

    @Before
    public void initUserDao() {
        this.dao = getUserDao();
    }

    @Test
    public void whenGetByInvalidNameThenReturnNull() {
        assertNull(
                this.dao.getByName(null)
        );
        assertNull(
                this.dao.getByName(ANY_STRING)
        );
    }

    @Test
    public void whenGetValidNameThenReturnSomeUser() {
        assertNotNull(
                this.dao.getByName(NAME)
        );
    }

    @Test
    public void whenGetByInvalidUrlThenReturnNull() {
        assertNull(
                this.dao.getByUrl(null)
        );
        assertNull(
                this.dao.getByUrl(ANY_STRING)
        );
    }

    @Test
    public void whenGetValidUrlThenReturnSomeUser() {
        assertNotNull(
                this.dao.getByUrl(URL)
        );
    }

    @Test
    public void whenGetByInvalidLoginThenReturnNull() {
        assertNull(
                this.dao.getByLogin(null)
        );
        assertNull(
                this.dao.getByLogin(ANY_STRING)
        );
    }

    @Test
    public void whenGetValidLoginThenReturnSomeUser() {
        assertNotNull(
                this.dao.getByLogin(LOGIN)
        );
    }

    @Test
    public void whenGetByInvalidPhoneThenReturnNull() {
        assertNull(
                this.dao.getByPhone(null)
        );
        assertNull(
                this.dao.getByPhone(ANY_STRING)
        );
    }

    @Test
    public void whenGetValidPhoneThenReturnSomeUser() {
        assertNotNull(
                this.dao.getByPhone(PHONE)
        );
    }

    @Test
    public void whenGetByInvalidEmailThenReturnNull() {
        assertNull(
                this.dao.getByEmail(null)
        );
        assertNull(
                this.dao.getByEmail(ANY_STRING)
        );
    }

    @Test
    public void whenGetValidEmailThenReturnSomeUser() {
        assertNotNull(
                this.dao.getByEmail(EMAIL));
    }

    @Ignore
    @Override
    protected UserDao getDao() {
        return this.dao;
    }

    @Ignore
    @Override
    protected User getObject() {
        return getUser();
    }

    @Ignore
    @Override
    protected Collection<User> getObjects() {
        return getUsers();
    }
}
