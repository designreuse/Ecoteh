package com.salimov.yurii.mocks.service.data.test;

import com.salimov.yurii.entity.User;
import com.salimov.yurii.service.data.interfaces.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.salimov.yurii.mocks.MockConstants.*;
import static com.salimov.yurii.mocks.enity.MockEntity.getUser;
import static com.salimov.yurii.mocks.enity.MockEntity.getUsers;
import static com.salimov.yurii.mocks.service.data.MockServices.getUserService;
import static org.junit.Assert.assertNotNull;

public class MockUserServiceTest extends MockDataServiceTest<User> {

    private UserService service;

    @Before
    public void initUserService() {
        this.service = getUserService();
    }

    /*@Test
    public void whenInitAndAddUserThenReturnThisUser() {
        final Photo photo = getPhoto();
        User user = this.service.initAndAdd(NAME, LOGIN, PASSWORD, EMAIL, PHONE, DESCRIPTION, photo, USER_ROLE);
        assertNotNull(user);
    }*/

    /*@Test
    public void whenInitAndUpdateUserThenReturnThisUser() {
        final Photo photo = getPhoto();
        User user = this.service.initAndUpdate(ID, NAME, LOGIN, PASSWORD, EMAIL, PHONE, DESCRIPTION, photo, USER_ROLE);
        assertNotNull(user);
    }*/

    @Test
    public void whenGetUserByNameThenReturnSomeUser() {
        User user = this.service.getByName(NAME);
        assertNotNull(user);
    }

    @Test
    public void whenGetUserByUrlThenReturnSomeUser() {
        User user = this.service.getByUrl(URL);
        assertNotNull(user);
    }

    @Test
    public void whenGetUserByLoginThenReturnSomeUser() {
        User user = this.service.getByLogin(LOGIN);
        assertNotNull(user);
    }

    @Test
    public void whenGetUserByEmailThenReturnSomeUser() {
        User user = this.service.getByEmail(EMAIL);
        assertNotNull(user);
    }

    @Test
    public void whenGetUserByPhoneThenReturnSomeUser() {
        User user = this.service.getByPhone(PHONE);
        assertNotNull(user);
    }

    @Test
    public void whenGetAuthenticatedUserThenReturnSomeUser() {
        User user = this.service.getAuthenticatedUser();
        assertNotNull(user);
    }

    @Test
    public void whenGetMainAdminThisReturnSomeUser() {
        User admin = this.service.getMainAdmin();
        assertNotNull(admin);
    }

    @Test
    public void whenGetAdminsThenReturnAllAdmins() {
        Collection<User> admins = this.service.getAdmins();
        assertNotNull(admins);
    }

    @Test
    public void whenGetPersonnelThenReturnAllPersonnel() {
        Collection<User> personnel = this.service.getPersonnel();
        assertNotNull(personnel);
    }

    @Test
    public void whenSortUsersByNameThenReturnSortUsers() {
        final User user = getUser();
        final List<User> users1 = new ArrayList<>();
        users1.add(user);
        List<User> users2 = this.service.sortByName(users1, true);
        assertNotNull(users2);
        users2 = this.service.sortByName(users1, false);
        assertNotNull(users2);
    }

    @Test
    public void whenSortUsersByUrlThenReturnSortUsers() {
        final User user = getUser();
        final List<User> users1 = new ArrayList<>();
        users1.add(user);
        List<User> users2 = this.service.sortByUrl(users1, true);
        assertNotNull(users2);
        users2 = this.service.sortByUrl(users1, false);
        assertNotNull(users2);
    }

    @Test
    public void whenSortUsersByRoleThenReturnSortUsers() {
        final User user = getUser();
        final List<User> users1 = new ArrayList<>();
        users1.add(user);
        List<User> users2 = this.service.sortByRole(users1, USER_ROLE, true);
        assertNotNull(users2);
        users2 = this.service.sortByRole(users1, USER_ROLE, false);
        assertNotNull(users2);
    }

    @Test
    public void whenGetAndSortUsersByNameThenReturnSortUsers() {
        List<User> users = this.service.getAndSortByName(true);
        assertNotNull(users);
        users = this.service.getAndSortByName(false);
        assertNotNull(users);
    }

    @Test
    public void whenGetAndSortUsersByUrlThenReturnSortUsers() {
        List<User> users = this.service.getAndSortByUrl(true);
        assertNotNull(users);
        users = this.service.getAndSortByUrl(false);
        assertNotNull(users);
    }

    @Test
    public void whenGetAndSortUsersByRoleThenReturnSortUsers() {
        List<User> users = this.service.getAndSortByRole(USER_ROLE, true);
        assertNotNull(users);
        users = this.service.getAndSortByRole(USER_ROLE, false);
        assertNotNull(users);
    }

    @Test
    public void whenFilterUsersByRoleThenReturnReturnFilterUsers() {
        final User user = getUser();
        final List<User> users1 = new ArrayList<>();
        users1.add(user);
        Collection<User> users2 = this.service.filterByRole(users1, USER_ROLE);
        assertNotNull(users2);
    }

    @Test
    public void whenFilterUsersByRolesThenReturnReturnFilterUsers() {
        final User user = getUser();
        final List<User> users1 = new ArrayList<>();
        users1.add(user);
        final List<User.Role> roles = new ArrayList<>();
        roles.add(USER_ROLE);
        Collection<User> users2 = this.service.filterByRoles(users1, roles);
        assertNotNull(users2);
    }

    @Test
    public void whenGetAndFilterUsersByRoleThenReturnReturnFilterUsers() {
        Collection<User> users = this.service.getAndFilterByRole(USER_ROLE);
        assertNotNull(users);
    }

    @Test
    public void whenGetAndFilterUsersByRolesThenReturnReturnFilterUsers() {
        final List<User.Role> roles = new ArrayList<>();
        roles.add(USER_ROLE);
        Collection<User> users = this.service.getAndFilterByRoles(roles);
        assertNotNull(users);
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
}
