package com.salimov.yurii.dao.impl;

import com.salimov.yurii.dao.interfaces.UserDao;
import com.salimov.yurii.entity.User;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static com.salimov.yurii.mocks.MockConstants.*;
import static com.salimov.yurii.mocks.enity.MockEntity.getUser;
import static com.salimov.yurii.mocks.enity.MockEntity.getUsers;
import static com.salimov.yurii.mocks.repository.MockRepository.getUserRepository;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public final class UserDaoImplTest
        extends DataDAOImplTest<User, Long> {

    private UserDao dao;

    @Before
    public void beforeTest() {
        this.dao = new UserDaoImpl(
                getUserRepository()
        );
    }

    @Test
    public void whenGetByInvalidNameThenReturnsNull() {
        assertNull(
                this.dao.getByName(null)
        );
        assertNull(
                this.dao.getByName(ANY_STRING)
        );
    }

    @Test
    public void whenGetByValidNameThenReturnsSomeUser() {
        assertNotNull(
                this.dao.getByName(NAME)
        );
    }

    @Test
    public void whenGetByInvalidUrlThenReturnsNull() {
        assertNull(
                this.dao.getByUrl(null)
        );
        assertNull(
                this.dao.getByUrl(ANY_STRING)
        );
    }

    @Test
    public void whenGetByValidUrlThenReturnsSomeUser() {
        assertNotNull(
                this.dao.getByUrl(URL)
        );
    }

    @Test
    public void whenGetByInvalidLoginThenReturnsNull() {
        assertNull(
                this.dao.getByLogin(null)
        );
        assertNull(
                this.dao.getByLogin(ANY_STRING)
        );
    }

    @Test
    public void whenGetByValidLoginThenReturnsSomeUser() {
        assertNotNull(
                this.dao.getByLogin(LOGIN)
        );
    }

    @Test
    public void whenGetByInvalidPhoneThenReturnsNull() {
        assertNull(
                this.dao.getByPhone(null)
        );
        assertNull(
                this.dao.getByPhone(ANY_STRING)
        );
    }

    @Test
    public void whenGetByValidPhoneThenReturnsSomeUser() {
        assertNotNull(
                this.dao.getByPhone(PHONE)
        );
    }

    @Test
    public void whenGetByInvalidEmailThenReturnsNull() {
        assertNull(
                this.dao.getByEmail(null)
        );
        assertNull(
                this.dao.getByEmail(ANY_STRING)
        );
    }

    @Test
    public void whenGetByValidEmailThenReturnsSomeUser() {
        assertNotNull(
                this.dao.getByEmail(EMAIL)
        );
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
