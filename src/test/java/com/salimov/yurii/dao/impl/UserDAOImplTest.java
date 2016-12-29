package com.salimov.yurii.dao.impl;

import com.salimov.yurii.config.RootConfig;
import com.salimov.yurii.config.WebConfig;
import com.salimov.yurii.dao.interfaces.DataDao;
import com.salimov.yurii.dao.interfaces.UserDao;
import com.salimov.yurii.entity.User;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collection;

import static com.salimov.yurii.mocks.enity.MockEntity.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
@SuppressWarnings("SpringJavaAutowiringInspection")
public class UserDAOImplTest extends DataDAOImplTest<User> {

    @Autowired
    private UserDao dao;

    @Ignore
    @Override
    protected DataDao getDao() {
        return this.dao;
    }

    @Ignore
    @Override
    protected User getObject() {
        final User user = getUser();
        user.setName(user.getName() + " " + getRandomInt());
        user.setLogin(user.getLogin() + " " + getRandomInt());
        user.setId(Long.MAX_VALUE);
        return user;
    }

    @Ignore
    @Override
    protected Collection<User> getObjects() {
        return getUsers();
    }
}
