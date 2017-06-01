package ua.com.ecoteh.service.data;

import org.junit.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.com.ecoteh.config.DefaultAccounts;
import ua.com.ecoteh.entity.user.UserEntity;
import ua.com.ecoteh.entity.user.UserRole;
import ua.com.ecoteh.mocks.MockConstants;
import ua.com.ecoteh.mocks.enity.MockEntity;
import ua.com.ecoteh.mocks.repository.MockRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;
import static ua.com.ecoteh.mocks.service.data.MockServices.getFileService;

public final class UserEntityServiceImplTest extends DataServiceImplTest<UserEntity> {

    private static UserService service;

    @BeforeClass
    public static void beforeTest() {
        service = new UserServiceImpl(
                MockRepository.getUserRepository(),
                getFileService()
        );
    }

    @Test(expected = UsernameNotFoundException.class)
    public void whenLoadUserByUnknownUsernameThenThrowsUsernameNotFoundException() {
        service.loadUserByUsername(ANY_STRING);
    }

    @Test
    public void whenLoadUserByUsernameThenReturnSomeUser() {
        assertNotNull(service.loadUserByUsername(MockConstants.LOGIN));
    }

    @Test
    public void whenLoadUserByDefaultUsernameThenReturnSomeUser() {
        assertNotNull(service.loadUserByUsername(DefaultAccounts.getDefaultAdmin().getLogin()));
    }

    @Test
    public void whenAddThenReturnSomeUser() {
        assertNotNull(service.add(MockEntity.getUserEntity()));
    }

    @Test
    public void whenUpdateThenReturnSomeUser() {
        assertNotNull(service.update(MockConstants.URL, MockEntity.getUserEntity()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullNameThenThrowsIllegalArgumentException() {
        service.getByName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByBlankNameThenThrowsIllegalArgumentException() {
        service.getByName("");
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownNameThenThrowsNullPointerException() {
        service.getByName(ANY_STRING);
    }

    @Test
    public void whenGetByNameThenReturnSomeUser() {
        assertNotNull(service.getByName(MockConstants.NAME));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullUrlThenThrowsIllegalArgumentException() {
        service.getByUrl(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByBlankUrlThenThrowsIllegalArgumentException() {
        service.getByUrl("");
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownUrlThenThrowsNullPointerException() {
        service.getByUrl(ANY_STRING);
    }

    @Test
    public void whenGetByUrlThenReturnSomeUser() {
        assertNotNull(service.getByUrl(MockConstants.URL));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullLoginThenThrowsIllegalArgumentException() {
        service.getByLogin(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByBlankLoginThenThrowsIllegalArgumentException() {
        service.getByLogin("");
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownLoginThenThrowsNullPointerException() {
        service.getByLogin(ANY_STRING);
    }

    @Test
    public void whenGetByLoginThenReturnSomeUser() {
        assertNotNull(service.getByLogin(MockConstants.LOGIN));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullEmailThenThrowsIllegalArgumentException() {
        service.getByEmail(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByBlankEmailThenThrowsIllegalArgumentException() {
        service.getByEmail("");
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownEmailThenThrowsException() {
        service.getByEmail(MockConstants.ANY_STRING);
    }

    @Test
    public void whenGetByEmailThenReturnSomeUser() {
        assertNotNull(service.getByEmail(MockConstants.EMAIL));
    }

    @Test
    public void whenGetMainAdminThenReturnSomeUser() {
        final UserEntity admin = service.getMainAdmin();
        assertNotNull(admin);
        Assert.assertEquals(admin.getRole(), UserRole.ADMIN);
    }

    @Test
    public void whenGetAdminsThenReturnSomeCollection() {
        assertFalse(service.getAdmins().isEmpty());
    }

    @Test
    public void whenGetPersonnelThenReturnSomeCollection() {
        assertFalse(service.getPersonnel().isEmpty());
    }

    @Test
    public void whenSortByNameWithNullCollectionAndTrueReversThenReturnEmptyList() {
        assertTrue(service.sortByName(null, true).isEmpty());
    }

    @Test
    public void whenSortByNameWithNullCollectionAndFalseReversThenReturnEmptyList() {
        assertTrue(service.sortByName(null, true).isEmpty());
    }

    @Test
    public void whenSortByNameWithEmptyCollectionAndTrueReversThenReturnEmptyList() {
        assertTrue(service.sortByName(new ArrayList<>(), true).isEmpty()
       );
    }

    @Test
    public void whenSortByNameWithEmptyCollectionAndFalseReversThenReturnEmptyList() {
        assertTrue(service.sortByName(new ArrayList<>(), true).isEmpty());
    }

    @Test
    public void whenSortByNameWithTrueReversThenReturnSomeList() {
        assertFalse(service.sortByName(MockEntity.getUserEntities(), true).isEmpty());
    }

    @Test
    public void whenSortByNameWithFalseReversThenReturnSomeList() {
        assertFalse(service.sortByName(MockEntity.getUserEntities(), false).isEmpty());
    }

    @Test
    public void whenSortByUrlWithNullCollectionAndTrueReversThenReturnEmptyList() {
        assertTrue(service.sortByUrl(null, true).isEmpty());
    }

    @Test
    public void whenSortByUrlWithNullCollectionAndFalseReversThenReturnEmptyList() {
        assertTrue(service.sortByUrl(null, true).isEmpty());
    }

    @Test
    public void whenSortByUrlWithEmptyCollectionAndTrueReversThenReturnEmptyList() {
        assertTrue(service.sortByUrl(new ArrayList<>(), true).isEmpty());
    }

    @Test
    public void whenSortByUrlWithEmptyCollectionAndFalseReversThenReturnEmptyList() {
        assertTrue(service.sortByUrl(new ArrayList<>(), true).isEmpty());
    }

    @Test
    public void whenSortByUrlWithTrueReversThenReturnSomeList() {
        assertFalse(service.sortByUrl(MockEntity.getUserEntities(), true).isEmpty());
    }

    @Test
    public void whenSortByUrlWithFalseReversThenReturnSomeList() {
        assertFalse(service.sortByUrl(MockEntity.getUserEntities(), false).isEmpty());
    }

    @Test
    public void whenSortByRoleWithNullCollectionAndTrueReversThenReturnEmptyList() {
        assertTrue(service.sortByRole(null, MockConstants.USER_ROLE, true).isEmpty());
    }

    @Test
    public void whenSortByRoleWithNullCollectionAndFalseReversThenReturnEmptyList() {
        assertTrue(service.sortByRole(null, MockConstants.USER_ROLE, true).isEmpty());
    }

    @Test
    public void whenSortByRoleWithEmptyCollectionAndTrueReversThenReturnEmptyList() {
        assertTrue(service.sortByRole(new ArrayList<>(), MockConstants.USER_ROLE, true).isEmpty());
    }

    @Test
    public void whenSortByRoleWithEmptyCollectionAndFalseReversThenReturnEmptyList() {
        assertTrue(
                service.sortByRole(
                        new ArrayList<>(),
                        MockConstants.USER_ROLE, true
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByRoleWithTrueReversThenReturnSomeList() {
        assertFalse(
                service.sortByRole(
                        MockEntity.getUserEntities(),
                        MockConstants.USER_ROLE, true
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByRoleWithFalseReversThenReturnSomeList() {
        assertFalse(
                service.sortByRole(
                        MockEntity.getUserEntities(),
                        MockConstants.USER_ROLE, false
                ).isEmpty()
        );
    }

    @Test
    public void whenGetAndSortByNameWithTrueReversThenReturnSomeList() {
        assertFalse(service.getAndSortByName(true).isEmpty());
    }

    @Test
    public void whenGetAndSortByNameWithFalseReversThenReturnSomeList() {
        assertFalse(service.getAndSortByName(false).isEmpty());
    }

    @Test
    public void whenGetAndSortByUrlWithTrueReversThenReturnSomeList() {
        assertFalse(service.getAndSortByUrl(true).isEmpty());
    }

    @Test
    public void whenGetAndSortByUrlWithFalseReversThenReturnSomeList() {
        assertFalse(service.getAndSortByUrl(false).isEmpty());
    }

    @Test
    public void whenGetAndSortByRoleWithTrueReversThenReturnSomeList() {
        assertFalse(service.getAndSortByRole(MockConstants.USER_ROLE, true).isEmpty());
    }

    @Test
    public void whenGetAndSortByRoleWithFalseReversThenReturnSomeList() {
        assertFalse(service.getAndSortByRole(MockConstants.USER_ROLE, false).isEmpty());
    }

    @Test
    public void whenFilterByRoleWithNullCollectionThenReturnEmptyList() {
        assertTrue(service.filterByRole(null, MockConstants.USER_ROLE).isEmpty());
    }

    @Test
    public void whenFilterByRoleWithEmptyCollectionThenReturnEmptyList() {
        assertTrue(service.filterByRole(new ArrayList<>(), MockConstants.USER_ROLE).isEmpty());
    }

    @Test
    public void whenFilterByRoleWithNullRoleThenReturnNotFilterList() {
        assertFalse(service.filterByRole(
                        MockEntity.getUserEntities(), null).isEmpty());
    }

    @Test
    public void whenFilterByRolesWithNullCollectionThenReturnEmptyList() {
        final List<UserRole> roles = new ArrayList<>();
        roles.add(MockConstants.USER_ROLE);
        assertTrue(service.filterByRoles(null, roles).isEmpty());
    }

    @Test
    public void whenFilterByRolesWithEmptyCollectionThenReturnEmptyList() {
        final List<UserRole> roles = new ArrayList<>();
        roles.add(MockConstants.USER_ROLE);
        assertTrue(service.filterByRoles(new ArrayList<>(), roles).isEmpty());
    }

    @Test
    public void whenFilterByRolesWithNullRolesThenReturnNotFilterList() {
        assertFalse(service.filterByRoles(MockEntity.getUserEntities(), null).isEmpty());
    }

    @Test
    public void whenFilterByRolesWithEmptyRolesThenReturnNotFilterList() {
        assertFalse(
                service.filterByRoles(
                        MockEntity.getUserEntities(), new ArrayList<>()
                ).isEmpty()
        );
    }

    @Test
    public void whenFilterByRolesThenReturnSomeList() {
        final List<UserRole> roles = new ArrayList<>();
        roles.add(MockConstants.USER_ROLE);
        assertFalse(service.filterByRoles(MockEntity.getUserEntities(), roles).isEmpty());
    }

    @Test
    public void whenGetAndFilterByNullRoleThenReturnSomeNotFilterList() {
        assertFalse(service.getAndFilterByRole(null).isEmpty());
    }

    @Test
    public void whenGetAndFilterThenReturnSomeList() {
        assertFalse(service.getAndFilterByRole(MockConstants.USER_ROLE).isEmpty());
    }

    @Test
    public void whenGetAndFilterByNullRolesThenReturnSomeNotFileterList() {
        assertFalse(service.getAndFilterByRoles(null).isEmpty());
    }

    @Test
    public void whenGetAndFilterByEmptyRolesThenReturnSomeList() {
        assertFalse(service.getAndFilterByRoles(new ArrayList<>()).isEmpty());
    }

    @Test
    public void whenGetAndFilterByRolesThenReturnSomeList() {
        final List<UserRole> roles = new ArrayList<>();
        roles.add(MockConstants.USER_ROLE);
        assertFalse(service.getAndFilterByRoles(roles).isEmpty());
    }

    @Test
    public void whenFilteredByValidWithNullCollectionThenReturnEmptyList() {
        assertTrue(service.filteredByValid(null).isEmpty());
    }

    @Test
    public void whenFilteredByValidWithEmptyCollectionThenReturnEmptyList() {
        assertTrue(service.filteredByValid(new ArrayList<>()).isEmpty());
    }

    @Test
    public void whenFilteredByValidThenReturnSomeList() {
        assertFalse(service.filteredByValid(MockEntity.getUserEntities()).isEmpty());
    }

    @Test
    public void whenRemoveByNullNameThenDoNothing() {
        service.removeByName(null);
    }

    @Test
    public void whenRemoveByBlankNameThenDoNothing() {
        service.removeByName("");
        service.removeByName(" ");
        service.removeByName("  ");
    }

    @Test
    public void whenRemoveByNameThenDoIt() {
        service.removeByName(MockConstants.NAME);
    }

    @Test
    public void whenRemoveByNullUrlThenDoNothing() {
        service.removeByName(null);
    }

    @Test
    public void whenRemoveByBlankUrlThenDoNothing() {
        service.removeByUrl("");
        service.removeByUrl(" ");
        service.removeByUrl("  ");
    }

    @Test
    public void whenRemoveByUrlThenDoIt() {
        service.removeByUrl(MockConstants.URL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullPhoneThenThrowsIllegalArgumentException() {
        service.getByPhone(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByEmptyPhoneThenThrowsIllegalArgumentException() {
        service.getByPhone("");
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownPhoneThenThrowsException() {
        service.getByPhone(MockConstants.ANY_STRING);
    }

    @Test
    public void whenGetByMobilePhoneThenReturnsSomeUser() {
        assertNotNull(service.getByPhone(MockConstants.PHONE));
    }

    @Test
    public void whenGetByLandlinePhoneThenReturnsSomeUser() {
        assertNotNull(service.getByPhone(MockConstants.PHONE_2));
    }

    @Test
    public void whenGetByFaxPhoneThenReturnsSomeUser() {
        assertNotNull(service.getByPhone(MockConstants.FAX));
    }

    @Test
    public void whenGetAuthenticatedUserThenReturnNull() {
        assertNull(service.getAuthenticatedUser());
    }

    @Ignore
    @Override
    protected UserService getService() {
        return service;
    }

    @Ignore
    @Override
    protected UserEntity getObject() {
        return MockEntity.getUserEntity();
    }

    @Ignore
    @Override
    protected Collection<UserEntity> getObjects() {
        return MockEntity.getUserEntities();
    }

    @Ignore
    @Override
    protected UserEntity getInvalidObject() {
        return new UserEntity();
    }
}
