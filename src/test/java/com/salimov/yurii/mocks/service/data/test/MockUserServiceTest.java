package com.salimov.yurii.mocks.service.data.test;

import com.salimov.yurii.entity.User;
import com.salimov.yurii.enums.UserRole;
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

public final class MockUserServiceTest
        extends MockDataServiceTest<User> {

    private UserService service;

    @Before
    public void initUserService() {
        this.service = getUserService();
    }

    @Test
    public void whenInitAndAddUserThenReturnThisUser() {
        assertNotNull(
                this.service.initAndAdd(
                        NAME, LOGIN, PASSWORD,
                        DESCRIPTION,
                        PHONE, EMAIL,
                        VKONTAKTE, FACEBOOK, TWITTER, SKYPE,
                        PHOTO_URL,
                        true, true, true
                )
        );
    }

    @Test
    public void whenInitAndUpdateUserThenReturnThisUser() {
        assertNotNull(
                this.service.initAndUpdate(
                        URL, NAME, LOGIN, PASSWORD,
                        DESCRIPTION,
                        PHONE, EMAIL,
                        VKONTAKTE, FACEBOOK, TWITTER, SKYPE,
                        PHOTO_URL,
                        true, true, true
                )
        );
    }

    @Test
    public void whenGetUserByNameThenReturnSomeUser() {
        assertNotNull(
                this.service.getByName(NAME)
        );
    }

    @Test
    public void whenGetUserByUrlThenReturnSomeUser() {
        assertNotNull(
                this.service.getByUrl(URL)
        );
    }

    @Test
    public void whenGetUserByLoginThenReturnSomeUser() {
        assertNotNull(
                this.service.getByLogin(LOGIN)
        );
    }

    @Test
    public void whenGetUserByEmailThenReturnSomeUser() {
        assertNotNull(
                this.service.getByEmail(EMAIL)
        );
    }

    @Test
    public void whenGetUserByPhoneThenReturnSomeUser() {
        assertNotNull(
                this.service.getByPhone(PHONE)
        );
    }

    @Test
    public void whenGetAuthenticatedUserThenReturnSomeUser() {
        assertNotNull(
                this.service.getAuthenticatedUser()
        );
    }

    @Test
    public void whenGetMainAdminThisReturnSomeUser() {
        assertNotNull(
                this.service.getMainAdmin()
        );
    }

    @Test
    public void whenGetAdminsThenReturnAllAdmins() {
        assertNotNull(
                this.service.getAdmins()
        );
    }

    @Test
    public void whenGetPersonnelThenReturnAllPersonnel() {
        assertNotNull(
                this.service.getPersonnel()
        );
    }

    @Test
    public void whenSortUsersByNameThenReturnSortUsers() {
        final List<User> users = getUsers();
        assertNotNull(
                this.service.sortByName(users, true)
        );
        assertNotNull(
                this.service.sortByName(users, false)
        );
    }

    @Test
    public void whenSortUsersByUrlThenReturnSortUsers() {
        assertNotNull(
                this.service.sortByUrl(
                        getUsers(), true
                )
        );
        assertNotNull(
                this.service.sortByUrl(
                        getUsers(), false
                )
        );
    }

    @Test
    public void whenSortUsersByRoleThenReturnSortUsers() {
        assertNotNull(
                this.service.sortByRole(
                        getUsers(), USER_ROLE, true
                )
        );
        assertNotNull(
                this.service.sortByRole(
                        getUsers(), USER_ROLE, false
                )
        );
    }

    @Test
    public void whenGetAndSortUsersByNameThenReturnSortUsers() {
        assertNotNull(
                this.service.getAndSortByName(true)
        );
        assertNotNull(
                this.service.getAndSortByName(false)
        );
    }

    @Test
    public void whenGetAndSortUsersByUrlThenReturnSortUsers() {
        assertNotNull(
                this.service.getAndSortByUrl(true)
        );
        assertNotNull(
                this.service.getAndSortByUrl(false)
        );
    }

    @Test
    public void whenGetAndSortUsersByRoleThenReturnSortUsers() {
        assertNotNull(
                this.service.getAndSortByRole(
                        USER_ROLE, true
                )
        );
        assertNotNull(
                this.service.getAndSortByRole(
                        USER_ROLE, false
                )
        );
    }

    @Test
    public void whenFilterUsersByRoleThenReturnReturnFilterUsers() {
        assertNotNull(
                this.service.filterByRole(
                        getUsers(),
                        USER_ROLE
                )
        );
    }

    @Test
    public void whenFilterUsersByRolesThenReturnReturnFilterUsers() {
        final List<UserRole> roles = new ArrayList<>();
        roles.add(USER_ROLE);
        assertNotNull(
                this.service.filterByRoles(
                        getUsers(),
                        roles
                )
        );
    }

    @Test
    public void whenGetAndFilterUsersByRoleThenReturnReturnFilterUsers() {
        assertNotNull(
                this.service.getAndFilterByRole(USER_ROLE)
        );
    }

    @Test
    public void whenGetAndFilterUsersByRolesThenReturnReturnFilterUsers() {
        final List<UserRole> roles = new ArrayList<>();
        roles.add(USER_ROLE);
        assertNotNull(
                this.service.getAndFilterByRoles(roles)
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
}
