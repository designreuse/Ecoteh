package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.entity.User;
import com.salimov.yurii.enums.UserRole;
import com.salimov.yurii.service.data.interfaces.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.salimov.yurii.mocks.MockConstants.*;
import static com.salimov.yurii.mocks.dao.MockDao.getUserDao;
import static com.salimov.yurii.mocks.enity.MockEntity.getUser;
import static com.salimov.yurii.mocks.enity.MockEntity.getUsers;
import static org.junit.Assert.*;

public final class UserServiceImplTest extends DataServiceImplTest<User, Long> {

    private UserService service;

    @Before
    public void beforeTest() {
        this.service = new UserServiceImpl(getUserDao());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void whenLoadUserByUnknownUsernameThenThrowsUsernameNotFoundException() {
        this.service.loadUserByUsername(ANY_STRING);
    }

    @Test
    public void whenInitAndAddThenReturnSomeUser() {
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
    public void whenInitAndUpdateThenReturnSomeUser() {
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

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullNameThenThrowsIllegalArgumentException() {
        this.service.getByName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByBlankNameThenThrowsIllegalArgumentException() {
        this.service.getByName("");
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownNameThenThrowsNullPointerException() {
        this.service.getByName(ANY_STRING);
    }

    @Test
    public void whenGetByNameThenReturnSomeUser() {
        assertNotNull(this.service.getByName(NAME));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullUrlThenThrowsIllegalArgumentException() {
        this.service.getByUrl(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByBlankUrlThenThrowsIllegalArgumentException() {
        this.service.getByUrl("");
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownUrlThenThrowsNullPointerException() {
        this.service.getByUrl(ANY_STRING);
    }

    @Test
    public void whenGetByUrlThenReturnSomeUser() {
        assertNotNull(this.service.getByUrl(URL));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullLoginThenThrowsIllegalArgumentException() {
        this.service.getByLogin(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByBlankLoginThenThrowsIllegalArgumentException() {
        this.service.getByLogin("");
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownLoginThenThrowsNullPointerException() {
        this.service.getByLogin(ANY_STRING);
    }

    @Test
    public void whenGetByLoginThenReturnSomeUser() {
        assertNotNull(this.service.getByLogin(LOGIN));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullPhoneThenThrowsIllegalArgumentException() {
        this.service.getByPhone(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByBlankPhoneThenThrowsIllegalArgumentException() {
        this.service.getByPhone("");
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownPhoneThenThrowsNullPointerException() {
        this.service.getByPhone(ANY_STRING);
    }

    @Test
    public void whenGetByPhoneThenReturnSomeUser() {
        assertNotNull(this.service.getByPhone(PHONE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullEmailThenThrowsIllegalArgumentException() {
        this.service.getByEmail(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByBlankEmailThenThrowsIllegalArgumentException() {
        this.service.getByEmail("");
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownEmailThenThrowsNullPointerException() {
        this.service.getByEmail(ANY_STRING);
    }

    @Test
    public void whenGetByEmailThenReturnSomeUser() {
        assertNotNull(this.service.getByEmail(EMAIL));
    }

    @Test
    public void whenGetMainAdminThenReturnSomeUser() {
        final User admin = this.service.getMainAdmin();
        assertNotNull(admin);
        assertEquals(admin.getRole(), UserRole.ADMIN);
    }

    @Test
    public void whenGetAdminsThenReturnSomeCollection() {
        assertFalse(this.service.getAdmins().isEmpty());
    }

    @Test
    public void whenGetPersonnelThenReturnSomeCollection() {
        assertFalse(this.service.getPersonnel().isEmpty());
    }

    @Test
    public void whenSortByNameWithNullCollectionAndTrueReversThenReturnEmptyList() {
        assertTrue(
                this.service.sortByName(
                        null, true
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByNameWithNullCollectionAndFalseReversThenReturnEmptyList() {
        assertTrue(
                this.service.sortByName(
                        null, true
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByNameWithEmptyCollectionAndTrueReversThenReturnEmptyList() {
        assertTrue(
                this.service.sortByName(
                        new ArrayList<>(), true
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByNameWithEmptyCollectionAndFalseReversThenReturnEmptyList() {
        assertTrue(
                this.service.sortByName(
                        new ArrayList<>(), true
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByNameWithTrueReversThenReturnSomeList() {
        assertFalse(
                this.service.sortByName(
                        getUsers(), true
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByNameWithFalseReversThenReturnSomeList() {
        assertFalse(
                this.service.sortByName(
                        getUsers(), false
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByUrlWithNullCollectionAndTrueReversThenReturnEmptyList() {
        assertTrue(
                this.service.sortByUrl(
                        null, true
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByUrlWithNullCollectionAndFalseReversThenReturnEmptyList() {
        assertTrue(
                this.service.sortByUrl(
                        null, true
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByUrlWithEmptyCollectionAndTrueReversThenReturnEmptyList() {
        assertTrue(
                this.service.sortByUrl(
                        new ArrayList<>(), true
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByUrlWithEmptyCollectionAndFalseReversThenReturnEmptyList() {
        assertTrue(
                this.service.sortByUrl(
                        new ArrayList<>(), true
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByUrlWithTrueReversThenReturnSomeList() {
        assertFalse(
                this.service.sortByUrl(
                        getUsers(), true
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByUrlWithFalseReversThenReturnSomeList() {
        assertFalse(
                this.service.sortByUrl(
                        getUsers(), false
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByRoleWithNullCollectionAndTrueReversThenReturnEmptyList() {
        assertTrue(
                this.service.sortByRole(
                        null, USER_ROLE, true
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByRoleWithNullCollectionAndFalseReversThenReturnEmptyList() {
        assertTrue(
                this.service.sortByRole(
                        null, USER_ROLE, true
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByRoleWithEmptyCollectionAndTrueReversThenReturnEmptyList() {
        assertTrue(
                this.service.sortByRole(
                        new ArrayList<>(),
                        USER_ROLE, true
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByRoleWithEmptyCollectionAndFalseReversThenReturnEmptyList() {
        assertTrue(
                this.service.sortByRole(
                        new ArrayList<>(),
                        USER_ROLE, true
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByRoleWithTrueReversThenReturnSomeList() {
        assertFalse(
                this.service.sortByRole(
                        getUsers(),
                        USER_ROLE, true
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByRoleWithFalseReversThenReturnSomeList() {
        assertFalse(
                this.service.sortByRole(
                        getUsers(),
                        USER_ROLE, false
                ).isEmpty()
        );
    }

    @Test
    public void whenGetAndSortByNameWithTrueReversThenReturnSomeList() {
        assertFalse(this.service.getAndSortByName(true).isEmpty());
    }

    @Test
    public void whenGetAndSortByNameWithFalseReversThenReturnSomeList() {
        assertFalse(this.service.getAndSortByName(false).isEmpty());
    }

    @Test
    public void whenGetAndSortByUrlWithTrueReversThenReturnSomeList() {
        assertFalse(
                this.service.getAndSortByUrl(true).isEmpty()
        );
    }

    @Test
    public void whenGetAndSortByUrlWithFalseReversThenReturnSomeList() {
        assertFalse(
                this.service.getAndSortByUrl(false).isEmpty()
        );
    }

    @Test
    public void whenGetAndSortByRoleWithTrueReversThenReturnSomeList() {
        assertFalse(
                this.service.getAndSortByRole(
                        USER_ROLE, true
                ).isEmpty()
        );
    }

    @Test
    public void whenGetAndSortByRoleWithFalseReversThenReturnSomeList() {
        assertFalse(
                this.service.getAndSortByRole(
                        USER_ROLE, false
                ).isEmpty()
        );
    }

    @Test
    public void whenFilterByRoleWithNullCollectionThenReturnEmptyList() {
        assertTrue(
                this.service.filterByRole(
                        null, USER_ROLE
                ).isEmpty()
        );
    }

    @Test
    public void whenFilterByRoleWithEmptyCollectionThenReturnEmptyList() {
        assertTrue(
                this.service.filterByRole(
                        new ArrayList<>(), USER_ROLE
                ).isEmpty()
        );
    }

    @Test
    public void whenFilterByRoleWithNullRoleThenReturnNotFilterList() {
        assertFalse(
                this.service.filterByRole(
                        getUsers(), null
                ).isEmpty()
        );
    }

    @Test
    public void whenFilterByRolesWithNullCollectionThenReturnEmptyList() {
        final List<UserRole> roles = new ArrayList<>();
        roles.add(USER_ROLE);
        assertTrue(
                this.service.filterByRoles(
                        null, roles
                ).isEmpty()
        );
    }

    @Test
    public void whenFilterByRolesWithEmptyCollectionThenReturnEmptyList() {
        final List<UserRole> roles = new ArrayList<>();
        roles.add(USER_ROLE);
        assertTrue(
                this.service.filterByRoles(
                        new ArrayList<>(), roles
                ).isEmpty()
        );
    }

    @Test
    public void whenFilterByRolesWithNullRolesThenReturnNotFilterList() {
        assertFalse(
                this.service.filterByRoles(
                        getUsers(), null
                ).isEmpty()
        );
    }

    @Test
    public void whenFilterByRolesWithEmptyRolesThenReturnNotFilterList() {
        assertFalse(
                this.service.filterByRoles(
                        getUsers(), new ArrayList<>()
                ).isEmpty()
        );
    }

    @Test
    public void whenFilterByRolesThenReturnSomeList() {
        final List<UserRole> roles = new ArrayList<>();
        roles.add(USER_ROLE);
        assertFalse(
                this.service.filterByRoles(
                        getUsers(), roles
                ).isEmpty()
        );
    }

    @Test
    public void whenGetAndFilterByNullRoleThenReturnSomeNotFilterList() {
        assertFalse(this.service.getAndFilterByRole(null).isEmpty());
    }

    @Test
    public void whenGetAndFilterThenReturnSomeList() {
        assertFalse(
                this.service.getAndFilterByRole(
                        USER_ROLE
                ).isEmpty()
        );
    }

    @Test
    public void whenGetAndFilterByNullRolesThenReturnSomeNotFileterList() {
        assertFalse(this.service.getAndFilterByRoles(null).isEmpty());
    }

    @Test
    public void whenGetAndFilterByEmptyRolesThenReturnSomeNotFileterList() {
        assertFalse(
                this.service.getAndFilterByRoles(
                        new ArrayList<>()
                ).isEmpty()
        );
    }

    @Test
    public void whenGetAndFilterByRolesThenReturnSomeList() {
        final List<UserRole> roles = new ArrayList<>();
        roles.add(USER_ROLE);
        assertFalse(this.service.getAndFilterByRoles(roles).isEmpty());
    }

    @Test
    public void whenFilteredByValidWithNullCollectionThenReturnEmptyList() {
        assertTrue(this.service.filteredByValid(null).isEmpty());
    }

    @Test
    public void whenFilteredByValidWithEmptyCollectionThenReturnEmptyList() {
        assertTrue(
                this.service.filteredByValid(
                        new ArrayList<>()
                ).isEmpty()
        );
    }

    @Test
    public void whenFilteredByValidThenReturnSomeList() {
        assertFalse(
                this.service.filteredByValid(
                        getUsers()
                ).isEmpty()
        );
    }

    @Test
    public void whenRemoveByNullNameThenDoNothing() {
        this.service.removeByName(null);
    }

    @Test
    public void whenRemoveByBlankNameThenDoNothing() {
        this.service.removeByName("");
        this.service.removeByName(" ");
        this.service.removeByName("  ");
    }

    @Test
    public void whenRemoveByNameThenDoIt() {
        this.service.removeByName(NAME);
    }

    @Test
    public void whenRemoveByNullUrlThenDoNothing() {
        this.service.removeByName(null);
    }

    @Test
    public void whenRemoveByBlankUrlThenDoNothing() {
        this.service.removeByUrl("");
        this.service.removeByUrl(" ");
        this.service.removeByUrl("  ");
    }

    @Test
    public void whenRemoveByUrlThenDoIt() {
        this.service.removeByUrl(URL);
    }

    @Test
    public void whenRemoveByNullLoginThenDoNothing() {
        this.service.removeByLogin(null);
    }

    @Test
    public void whenRemoveByBlankLoginThenDoNothing() {
        this.service.removeByLogin("");
        this.service.removeByLogin(" ");
        this.service.removeByLogin("  ");
    }

    @Test
    public void whenRemoveByLoginThenDoIt() {
        this.service.removeByLogin(LOGIN);
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
