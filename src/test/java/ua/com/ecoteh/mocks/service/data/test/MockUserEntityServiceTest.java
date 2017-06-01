package ua.com.ecoteh.mocks.service.data.test;

import ua.com.ecoteh.entity.user.UserEntity;
import ua.com.ecoteh.entity.user.UserRole;
import ua.com.ecoteh.service.data.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockEntity.getUserEntity;
import static ua.com.ecoteh.mocks.enity.MockEntity.getUserEntities;
import static ua.com.ecoteh.mocks.service.data.MockServices.getUserService;
import static org.junit.Assert.assertNotNull;

public final class MockUserEntityServiceTest extends MockDataServiceTest<UserEntity> {

    private UserService service;

    @Before
    public void initUserService() {
        this.service = getUserService();
    }

    @Test
    public void whenAddUserThenReturnThisUser() {
        assertNotNull(this.service.add(getUserEntity()));
    }

    @Test
    public void whenUpdateUserThenReturnThisUser() {
        assertNotNull(this.service.update(URL, getUserEntity()));
    }

    @Test
    public void whenGetUserByNameThenReturnSomeUser() {
        assertNotNull(this.service.getByName(NAME));
    }

    @Test
    public void whenGetUserByUrlThenReturnSomeUser() {
        assertNotNull(this.service.getByUrl(URL));
    }

    @Test
    public void whenGetUserByLoginThenReturnSomeUser() {
        assertNotNull(this.service.getByLogin(LOGIN));
    }

    @Test
    public void whenGetUserByEmailThenReturnSomeUser() {
        assertNotNull(this.service.getByEmail(EMAIL));
    }

    @Test
    public void whenGetAuthenticatedUserThenReturnSomeUser() {
        assertNotNull(this.service.getAuthenticatedUser());
    }

    @Test
    public void whenGetMainAdminThisReturnSomeUser() {
        assertNotNull(this.service.getMainAdmin());
    }

    @Test
    public void whenGetAdminsThenReturnAllAdmins() {
        assertNotNull(this.service.getAdmins());
    }

    @Test
    public void whenGetPersonnelThenReturnAllPersonnel() {
        assertNotNull(this.service.getPersonnel());
    }

    @Test
    public void whenSortUsersByNameThenReturnSortUsers() {
        final List<UserEntity> userEntities = getUserEntities();
        assertNotNull(this.service.sortByName(userEntities, true));
        assertNotNull(this.service.sortByName(userEntities, false));
    }

    @Test
    public void whenSortUsersByUrlThenReturnSortUsers() {
        assertNotNull(this.service.sortByUrl(getUserEntities(), true));
        assertNotNull(this.service.sortByUrl(getUserEntities(), false));
    }

    @Test
    public void whenSortUsersByRoleThenReturnSortUsers() {
        assertNotNull(this.service.sortByRole(getUserEntities(), USER_ROLE, true));
        assertNotNull(this.service.sortByRole(getUserEntities(), USER_ROLE, false));
    }

    @Test
    public void whenGetAndSortUsersByNameThenReturnSortUsers() {
        assertNotNull(this.service.getAndSortByName(true));
        assertNotNull(this.service.getAndSortByName(false));
    }

    @Test
    public void whenGetAndSortUsersByUrlThenReturnSortUsers() {
        assertNotNull(this.service.getAndSortByUrl(true));
        assertNotNull(this.service.getAndSortByUrl(false));
    }

    @Test
    public void whenGetAndSortUsersByRoleThenReturnSortUsers() {
        assertNotNull(this.service.getAndSortByRole(USER_ROLE, true));
        assertNotNull(this.service.getAndSortByRole(USER_ROLE, false));
    }

    @Test
    public void whenFilterUsersByRoleThenReturnReturnFilterUsers() {
        assertNotNull(this.service.filterByRole(getUserEntities(), USER_ROLE));
    }

    @Test
    public void whenFilterUsersByRolesThenReturnReturnFilterUsers() {
        final List<UserRole> roles = new ArrayList<>();
        roles.add(USER_ROLE);
        assertNotNull(this.service.filterByRoles(getUserEntities(), roles));
    }

    @Test
    public void whenGetAndFilterUsersByRoleThenReturnReturnFilterUsers() {
        assertNotNull(this.service.getAndFilterByRole(USER_ROLE));
    }

    @Test
    public void whenGetAndFilterUsersByRolesThenReturnReturnFilterUsers() {
        final List<UserRole> roles = new ArrayList<>();
        roles.add(USER_ROLE);
        assertNotNull(this.service.getAndFilterByRoles(roles));
    }

    @Ignore
    @Override
    protected UserService getService() {
        return this.service;
    }

    @Ignore
    @Override
    protected UserEntity getObject() {
        return getUserEntity();
    }

    @Ignore
    @Override
    protected Collection<UserEntity> getObjects() {
        return getUserEntities();
    }
}
