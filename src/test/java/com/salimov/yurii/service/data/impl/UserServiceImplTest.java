package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.entity.User;
import com.salimov.yurii.service.data.interfaces.UserService;
import org.junit.Before;
import org.junit.Ignore;

import java.util.Collection;

import static com.salimov.yurii.mocks.dao.MockDao.getUserDao;
import static com.salimov.yurii.mocks.enity.MockEntity.getUser;
import static com.salimov.yurii.mocks.enity.MockEntity.getUsers;

// TODO: empty
public final class UserServiceImplTest extends DataServiceImplTest<User, Long> {

    private UserService service;

    @Before
    public void beforeTest() {
        this.service = new UserServiceImpl(
                getUserDao()
        );
    }

    @Ignore
    @Override
    protected UserService getService() {
        return this.service;
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

    @Ignore
    @Override
    protected User getInvalidObject() {
        return new User();
    }
}
