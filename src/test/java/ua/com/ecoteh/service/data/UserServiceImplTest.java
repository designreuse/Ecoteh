package ua.com.ecoteh.service.data;

import ua.com.ecoteh.entity.User;
import ua.com.ecoteh.enums.UserRole;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.com.ecoteh.mocks.MockConstants;
import ua.com.ecoteh.mocks.enity.MockEntity;
import ua.com.ecoteh.mocks.repository.MockRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ua.com.ecoteh.mocks.service.data.MockServices.getFileService;
import static org.junit.Assert.*;

public final class UserServiceImplTest extends DataServiceImplTest<User> {

    private UserService service;

    @Before
    public void beforeTest() {
        this.service = new UserServiceImpl(
                MockRepository.getUserRepository(),
                getFileService()
        );
    }

    @Test(expected = UsernameNotFoundException.class)
    public void whenLoadUserByUnknownUsernameThenThrowsUsernameNotFoundException() {
        this.service.loadUserByUsername(MockConstants.ANY_STRING);
    }

    @Test
    public void whenAddThenReturnSomeUser() {
        assertNotNull(this.service.add(MockEntity.getUser()));
    }

    @Test
    public void whenUpdateThenReturnSomeUser() {
        assertNotNull(this.service.update(MockConstants.URL, MockEntity.getUser()));
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
        this.service.getByName(MockConstants.ANY_STRING);
    }

    @Test
    public void whenGetByNameThenReturnSomeUser() {
        assertNotNull(this.service.getByName(MockConstants.NAME));
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
        this.service.getByUrl(MockConstants.ANY_STRING);
    }

    @Test
    public void whenGetByUrlThenReturnSomeUser() {
        assertNotNull(this.service.getByUrl(MockConstants.URL));
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
        this.service.getByLogin(MockConstants.ANY_STRING);
    }

    @Test
    public void whenGetByLoginThenReturnSomeUser() {
        assertNotNull(this.service.getByLogin(MockConstants.LOGIN));
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
    public void whenGetByEmailThenReturnSomeUserOrThrowException() {
        assertNotNull(this.service.getByEmail(MockConstants.EMAIL));
    }

    @Test
    public void whenGetMainAdminThenReturnSomeUser() {
        final User admin = this.service.getMainAdmin();
        assertNotNull(admin);
        Assert.assertEquals(admin.getRole(), UserRole.ADMIN);
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
        assertTrue(this.service.sortByName(null, true).isEmpty());
    }

    @Test
    public void whenSortByNameWithNullCollectionAndFalseReversThenReturnEmptyList() {
        assertTrue(this.service.sortByName(null, true).isEmpty());
    }

    @Test
    public void whenSortByNameWithEmptyCollectionAndTrueReversThenReturnEmptyList() {
        assertTrue(this.service.sortByName(new ArrayList<>(), true).isEmpty()
       );
    }

    @Test
    public void whenSortByNameWithEmptyCollectionAndFalseReversThenReturnEmptyList() {
        assertTrue(this.service.sortByName(new ArrayList<>(), true).isEmpty());
    }

    @Test
    public void whenSortByNameWithTrueReversThenReturnSomeList() {
        assertFalse(this.service.sortByName(MockEntity.getUsers(), true).isEmpty());
    }

    @Test
    public void whenSortByNameWithFalseReversThenReturnSomeList() {
        assertFalse(this.service.sortByName(MockEntity.getUsers(), false).isEmpty());
    }

    @Test
    public void whenSortByUrlWithNullCollectionAndTrueReversThenReturnEmptyList() {
        assertTrue(this.service.sortByUrl(null, true).isEmpty());
    }

    @Test
    public void whenSortByUrlWithNullCollectionAndFalseReversThenReturnEmptyList() {
        assertTrue(this.service.sortByUrl(null, true).isEmpty());
    }

    @Test
    public void whenSortByUrlWithEmptyCollectionAndTrueReversThenReturnEmptyList() {
        assertTrue(this.service.sortByUrl(new ArrayList<>(), true).isEmpty());
    }

    @Test
    public void whenSortByUrlWithEmptyCollectionAndFalseReversThenReturnEmptyList() {
        assertTrue(this.service.sortByUrl(new ArrayList<>(), true).isEmpty());
    }

    @Test
    public void whenSortByUrlWithTrueReversThenReturnSomeList() {
        assertFalse(this.service.sortByUrl(MockEntity.getUsers(), true).isEmpty());
    }

    @Test
    public void whenSortByUrlWithFalseReversThenReturnSomeList() {
        assertFalse(this.service.sortByUrl(MockEntity.getUsers(), false).isEmpty());
    }

    @Test
    public void whenSortByRoleWithNullCollectionAndTrueReversThenReturnEmptyList() {
        assertTrue(this.service.sortByRole(null, MockConstants.USER_ROLE, true).isEmpty());
    }

    @Test
    public void whenSortByRoleWithNullCollectionAndFalseReversThenReturnEmptyList() {
        assertTrue(this.service.sortByRole(null, MockConstants.USER_ROLE, true).isEmpty());
    }

    @Test
    public void whenSortByRoleWithEmptyCollectionAndTrueReversThenReturnEmptyList() {
        assertTrue(this.service.sortByRole(new ArrayList<>(), MockConstants.USER_ROLE, true).isEmpty());
    }

    @Test
    public void whenSortByRoleWithEmptyCollectionAndFalseReversThenReturnEmptyList() {
        assertTrue(
                this.service.sortByRole(
                        new ArrayList<>(),
                        MockConstants.USER_ROLE, true
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByRoleWithTrueReversThenReturnSomeList() {
        assertFalse(
                this.service.sortByRole(
                        MockEntity.getUsers(),
                        MockConstants.USER_ROLE, true
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByRoleWithFalseReversThenReturnSomeList() {
        assertFalse(
                this.service.sortByRole(
                        MockEntity.getUsers(),
                        MockConstants.USER_ROLE, false
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
        assertFalse(this.service.getAndSortByUrl(true).isEmpty());
    }

    @Test
    public void whenGetAndSortByUrlWithFalseReversThenReturnSomeList() {
        assertFalse(this.service.getAndSortByUrl(false).isEmpty());
    }

    @Test
    public void whenGetAndSortByRoleWithTrueReversThenReturnSomeList() {
        assertFalse(this.service.getAndSortByRole(MockConstants.USER_ROLE, true).isEmpty());
    }

    @Test
    public void whenGetAndSortByRoleWithFalseReversThenReturnSomeList() {
        assertFalse(this.service.getAndSortByRole(MockConstants.USER_ROLE, false).isEmpty());
    }

    @Test
    public void whenFilterByRoleWithNullCollectionThenReturnEmptyList() {
        assertTrue(this.service.filterByRole(null, MockConstants.USER_ROLE).isEmpty());
    }

    @Test
    public void whenFilterByRoleWithEmptyCollectionThenReturnEmptyList() {
        assertTrue(this.service.filterByRole(new ArrayList<>(), MockConstants.USER_ROLE).isEmpty());
    }

    @Test
    public void whenFilterByRoleWithNullRoleThenReturnNotFilterList() {
        assertFalse(this.service.filterByRole(
                        MockEntity.getUsers(), null).isEmpty());
    }

    @Test
    public void whenFilterByRolesWithNullCollectionThenReturnEmptyList() {
        final List<UserRole> roles = new ArrayList<>();
        roles.add(MockConstants.USER_ROLE);
        assertTrue(this.service.filterByRoles(null, roles).isEmpty());
    }

    @Test
    public void whenFilterByRolesWithEmptyCollectionThenReturnEmptyList() {
        final List<UserRole> roles = new ArrayList<>();
        roles.add(MockConstants.USER_ROLE);
        assertTrue(this.service.filterByRoles(new ArrayList<>(), roles).isEmpty());
    }

    @Test
    public void whenFilterByRolesWithNullRolesThenReturnNotFilterList() {
        assertFalse(this.service.filterByRoles(MockEntity.getUsers(), null).isEmpty());
    }

    @Test
    public void whenFilterByRolesWithEmptyRolesThenReturnNotFilterList() {
        assertFalse(
                this.service.filterByRoles(
                        MockEntity.getUsers(), new ArrayList<>()
                ).isEmpty()
        );
    }

    @Test
    public void whenFilterByRolesThenReturnSomeList() {
        final List<UserRole> roles = new ArrayList<>();
        roles.add(MockConstants.USER_ROLE);
        assertFalse(this.service.filterByRoles(MockEntity.getUsers(), roles).isEmpty());
    }

    @Test
    public void whenGetAndFilterByNullRoleThenReturnSomeNotFilterList() {
        assertFalse(this.service.getAndFilterByRole(null).isEmpty());
    }

    @Test
    public void whenGetAndFilterThenReturnSomeList() {
        assertFalse(this.service.getAndFilterByRole(MockConstants.USER_ROLE).isEmpty());
    }

    @Test
    public void whenGetAndFilterByNullRolesThenReturnSomeNotFileterList() {
        assertFalse(this.service.getAndFilterByRoles(null).isEmpty());
    }

    @Test
    public void whenGetAndFilterByEmptyRolesThenReturnSomeList() {
        assertFalse(this.service.getAndFilterByRoles(new ArrayList<>()).isEmpty());
    }

    @Test
    public void whenGetAndFilterByRolesThenReturnSomeList() {
        final List<UserRole> roles = new ArrayList<>();
        roles.add(MockConstants.USER_ROLE);
        assertFalse(this.service.getAndFilterByRoles(roles).isEmpty());
    }

    @Test
    public void whenFilteredByValidWithNullCollectionThenReturnEmptyList() {
        assertTrue(this.service.filteredByValid(null).isEmpty());
    }

    @Test
    public void whenFilteredByValidWithEmptyCollectionThenReturnEmptyList() {
        assertTrue(this.service.filteredByValid(new ArrayList<>()).isEmpty());
    }

    @Test
    public void whenFilteredByValidThenReturnSomeList() {
        assertFalse(this.service.filteredByValid(MockEntity.getUsers()).isEmpty());
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
        this.service.removeByName(MockConstants.NAME);
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
        this.service.removeByUrl(MockConstants.URL);
    }

    @Ignore
    @Override
    protected UserService getService() {
        return this.service;
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

    @Ignore
    @Override
    protected User getInvalidObject() {
        return new User();
    }
}
