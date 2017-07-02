package ua.com.ecoteh.service.data;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.com.ecoteh.entity.user.User;
import ua.com.ecoteh.entity.user.UserBuilder;
import ua.com.ecoteh.entity.user.UserRole;
import ua.com.ecoteh.repository.UserRepository;
import ua.com.ecoteh.service.data.comparator.UserComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModels.getUser;
import static ua.com.ecoteh.mocks.enity.MockModels.getUsers;
import static ua.com.ecoteh.mocks.repository.MockRepository.getUserRepository;
import static ua.com.ecoteh.mocks.service.data.MockServices.getFileService;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class UserServiceImplTest extends DataServiceImplTest<User> {

    private static UserService service;
    private static User user;
    private static Collection<User> users;

    @BeforeClass
    public static void beforeClass() {
        final UserRepository repository = getUserRepository();
        final FileService fileService = getFileService();
        service = new UserServiceImpl(repository, fileService);
        user = getUser();
        users = getUsers();
    }
    
    @Test
    public void whenLoadUserByUsernameThenReturnNotNull() {
        final User user = (User) service.loadUserByUsername(LOGIN);
        assertNotNull(user);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void whenLoadUserByUnknownUsernameThenThrowUsernameNotFoundException() {
        service.loadUserByUsername(ANY_STRING);

    }

    @Test
    public void whenGetByNameThenReturnNotNull() {
        final User user = service.getByName(NAME);
        assertNotNull(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullNameThenThrowIllegalArgumentException() {
        service.getByName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByEmptyNameThenThrowIllegalArgumentException() {
        service.getByName("");
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownNameThenThrowNullPointerException() {
        service.getByName(ANY_STRING);
    }

    @Test
    public void whenGetByUrlThenReturnNotNull() {
        final User user = service.getByUrl(URL);
        assertNotNull(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullUrlThenThrowIllegalArgumentException() {
        service.getByUrl(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByEmptyUrlThenThrowIllegalArgumentException() {
        service.getByUrl("");
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownUrlThenThrowNullPointerException() {
        service.getByUrl(ANY_STRING);
    }


    @Test
    public void whenGetByLoginThenReturnNotNull() {
        final User user = service.getByLogin(LOGIN);
        assertNotNull(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullLoginThenThrowIllegalArgumentException() {
        service.getByLogin(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByEmptyLoginThenThrowIllegalArgumentException() {
        service.getByLogin("");
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownLoginThenThrowNullPointerException() {
        service.getByLogin(ANY_STRING);
    }

    @Test
    public void whenGetByEmailThenReturnNotNull() {
        final User user = service.getByEmail(EMAIL);
        assertNotNull(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullEmailThenThrowIllegalArgumentException() {
        service.getByEmail(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByEmptyEmailThenThrowIllegalArgumentException() {
        service.getByEmail("");
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownEmailThenThrowNullPointerException() {
        service.getByEmail(ANY_STRING);
    }

    @Test
    public void whenGetByPhoneThenReturnNotNull() {
        final User user = service.getByPhone(PHONE);
        assertNotNull(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullPhoneThenThrowIllegalArgumentException() {
        service.getByPhone(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByEmptyPhoneThenThrowIllegalArgumentException() {
        service.getByPhone("");
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownPhoneThenThrowNullPointerException() {
        service.getByPhone(ANY_STRING);
    }

    @Test
    public void whenGetMainAdminThenReturnNotNull() {
        final User admin = service.getMainAdmin();
        assertNotNull(admin);

    }

    @Test
    public void whenGetAdminsThenReturnNotEmptyCollection() {
        final Collection<User> admins = service.getAdmins();
        assertFalse(admins.isEmpty());
    }

    @Test
    public void whenGetAdminsThenReturnCollectionWithNotNullObjects() {
        final Collection<User> admins = service.getAdmins();
        admins.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenGetPersonnelThenReturnNotEmptyCollection() {
        final Collection<User> personnel = service.getPersonnel();
        assertFalse(personnel.isEmpty());
    }

    @Test
    public void whenGetPersonnelThenReturnCollectionWithNotNullObjects() {
        final Collection<User> personnel = service.getPersonnel();
        personnel.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenRemoveByNameThenDoIt() {
        service.removeByName(NAME);
    }

    @Test
    public void whenRemoveByNullNameThenDoNothing() {
        service.removeByName(null);
    }

    @Test
    public void whenRemoveByEmptyNameThenDoNothing() {
        service.removeByName("");
    }

    @Test
    public void whenRemoveByUrlThenDoIt() {
        service.removeByUrl(URL);
    }

    @Test
    public void whenRemoveByNullUrlThenDoNothing() {
        service.removeByUrl(null);
    }

    @Test
    public void whenRemoveByEmptyUrlThenDoNothing() {
        service.removeByUrl("");
    }

    @Test
    public void whenSortByNameThenReturnNotEmptyCollection() {
        Collection<User> sortedUsers = service.sortByName(users, true);
        assertFalse(sortedUsers.isEmpty());
        sortedUsers =service.sortByName(users, true);
        assertFalse(sortedUsers.isEmpty());
    }

    @Test
    public void whenSortByNameThenReturnCollectionWithNotNullObjects() {
        Collection<User> sortedUsers = service.sortByName(users, true);
        sortedUsers.forEach(Assert::assertNotNull);
        sortedUsers = service.sortByName(users, true);
        sortedUsers.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenSortNullCollectionByNameThenReturnEmptyCollection() {
        final Collection<User> users = null;
        Collection<User> sortedUsers = service.sortByName(users, true);
        assertTrue(sortedUsers.isEmpty());
        sortedUsers = service.sortByName(users, true);
        assertTrue(sortedUsers.isEmpty());
    }

    @Test
    public void whenSortEmptyCollectionByNameThenReturnEmptyCollection() {
        final Collection<User> users = new ArrayList<>();
        Collection<User> sortedUsers = service.sortByName(users, true);
        assertTrue(sortedUsers.isEmpty());
        sortedUsers = service.sortByName(users, true);
        assertTrue(sortedUsers.isEmpty());
    }

    @Test
    public void whenSortByUrlThenReturnNotEmptyCollection() {
        Collection<User> sortedUsers = service.sortByUrl(users, true);
        assertFalse(sortedUsers.isEmpty());
        sortedUsers =service.sortByUrl(users, true);
        assertFalse(sortedUsers.isEmpty());
    }

    @Test
    public void whenSortByUrlThenReturnCollectionWithNotNullObjects() {
        Collection<User> sortedUsers = service.sortByUrl(users, true);
        sortedUsers.forEach(Assert::assertNotNull);
        sortedUsers = service.sortByUrl(users, true);
        sortedUsers.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenSortNullCollectionByUrlThenReturnEmptyCollection() {
        final Collection<User> users = null;
        Collection<User> sortedUsers = service.sortByUrl(users, true);
        assertTrue(sortedUsers.isEmpty());
        sortedUsers = service.sortByUrl(users, true);
        assertTrue(sortedUsers.isEmpty());
    }

    @Test
    public void whenSortEmptyCollectionByUrlThenReturnEmptyCollection() {
        final Collection<User> users = new ArrayList<>();
        Collection<User> sortedUsers = service.sortByUrl(users, true);
        assertTrue(sortedUsers.isEmpty());
        sortedUsers = service.sortByUrl(users, true);
        assertTrue(sortedUsers.isEmpty());
    }

    @Test
    public void whenSortByRoleThenReturnNotEmptyCollection() {
        Collection<User> sortedUsers;
        for (UserRole role : UserRole.values()) {
            sortedUsers = service.sortByRole(users, role, true);
            assertFalse(sortedUsers.isEmpty());
            sortedUsers = service.sortByRole(users, role, false);
            assertFalse(sortedUsers.isEmpty());
        }
    }

    @Test
    public void whenSortByRoleThenReturnCollectionWithNotNullObjects() {
        Collection<User> sortedUsers;
        for (UserRole role : UserRole.values()) {
            sortedUsers = service.sortByRole(users, role, true);
            sortedUsers.forEach(Assert::assertNotNull);
            sortedUsers = service.sortByRole(users, role, false);
            sortedUsers.forEach(Assert::assertNotNull);
        }
    }

    @Test
    public void whenSortEmptyCollectionByRoleTheReturnEmptyCollection() {
        Collection<User> sortedUsers;
        for (UserRole role : UserRole.values()) {
            sortedUsers = service.sortByRole(new ArrayList<>(), role, true);
            assertTrue(sortedUsers.isEmpty());
            sortedUsers = service.sortByRole(new ArrayList<>(), role, false);
            assertTrue(sortedUsers.isEmpty());
        }
    }

    @Test
    public void whenSortNullCollectionByRoleTheReturnEmptyCollection() {
        Collection<User> sortedUsers;
        for (UserRole role : UserRole.values()) {
            sortedUsers = service.sortByRole(null, role, true);
            assertTrue(sortedUsers.isEmpty());
            sortedUsers = service.sortByRole(null, role, false);
            assertTrue(sortedUsers.isEmpty());
        }
    }

    @Test
    public void whenSortByNullRoleTheReturnNotEmptyCollection() {
        Collection<User> sortedUsers = service.sortByRole(users, null, true);
        assertFalse(sortedUsers.isEmpty());
        sortedUsers = service.sortByRole(users, null, false);
        assertFalse(sortedUsers.isEmpty());
    }

    @Test
    public void whenSortByNullRoleTheReturnCollectionWithNotNullObjects() {
        Collection<User> sortedUsers = service.sortByRole(users, null, true);
        sortedUsers.forEach(Assert::assertNotNull);
        sortedUsers = service.sortByRole(users, null, false);
        sortedUsers.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenSortEmptyCollectionByNullRoleTheReturnEmptyCollection() {
        Collection<User> sortedUsers = service.sortByRole(new ArrayList<>(), null, true);
        assertTrue(sortedUsers.isEmpty());
        sortedUsers = service.sortByRole(new ArrayList<>(), null, false);
        assertTrue(sortedUsers.isEmpty());
    }

    @Test
    public void whenSortNullCollectionByNullRoleTheReturnEmptyCollection() {
        Collection<User> sortedUsers = service.sortByRole(null, null, true);
        assertTrue(sortedUsers.isEmpty());
        sortedUsers = service.sortByRole(null, null, false);
        assertTrue(sortedUsers.isEmpty());
    }

    @Test
    public void whenGetAndSortByRoleThenReturnNotEmptyCollection() {
        Collection<User> sortedUsers;
        for (UserRole role : UserRole.values()) {
            sortedUsers = service.getAndSortByRole(role, true);
            assertFalse(sortedUsers.isEmpty());
            sortedUsers = service.getAndSortByRole(role, false);
            assertFalse(sortedUsers.isEmpty());
        }
    }

    @Test
    public void whenGetAndSortByRoleThenReturnCollectionWithNotNullObjects() {
        Collection<User> sortedUsers;
        for (UserRole role : UserRole.values()) {
            sortedUsers = service.getAndSortByRole(role, true);
            sortedUsers.forEach(Assert::assertNotNull);
            sortedUsers = service.getAndSortByRole(role, false);
            sortedUsers.forEach(Assert::assertNotNull);
        }
    }

    @Test
    public void whenGetAndSortByNullRoleThenReturnNotEmptyCollection() {
        Collection<User> sortedUsers = service.getAndSortByRole(null, true);
        assertFalse(sortedUsers.isEmpty());
        sortedUsers = service.getAndSortByRole(null, false);
        assertFalse(sortedUsers.isEmpty());
    }

    @Test
    public void whenGetAndSortByNameThenReturnNotEmptyCollection() {
        Collection<User> sortedUsers = service.getAndSortByName(true);
        assertFalse(sortedUsers.isEmpty());
        sortedUsers = service.getAndSortByName(false);
        assertFalse(sortedUsers.isEmpty());
    }

    @Test
    public void whenGetAndSortByNameThenReturnCollectionWithNotNullObjects() {
        Collection<User> sortedUsers = service.getAndSortByName(true);
        sortedUsers.forEach(Assert::assertNotNull);
        sortedUsers = service.getAndSortByName(false);
        sortedUsers.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenGetAndSortByUrlThenReturnNotEmptyCollection() {
        Collection<User> sortedUsers = service.getAndSortByUrl(true);
        assertFalse(sortedUsers.isEmpty());
        sortedUsers = service.getAndSortByUrl(false);
        assertFalse(sortedUsers.isEmpty());
    }

    @Test
    public void whenGetAndSortByUrlThenReturnCollectionWithNotNullObjects() {
        Collection<User> sortedUsers = service.getAndSortByUrl(true);
        sortedUsers.forEach(Assert::assertNotNull);
        sortedUsers = service.getAndSortByUrl(false);
        sortedUsers.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenFilterByRoleThenReturnNotEmptyCollection() {
        Collection<User> sortedUsers;
        for (UserRole role : UserRole.values()) {
            sortedUsers = service.filterByRole(users, role);
            assertFalse(sortedUsers.isEmpty());
        }
    }

    @Test
    public void whenFilterByRoleThenReturnCollectionWithNotNullObjects() {
        Collection<User> sortedUsers;
        for (UserRole role : UserRole.values()) {
            sortedUsers = service.filterByRole(users, role);
            sortedUsers.forEach(Assert::assertNotNull);
        }
    }

    @Test
    public void whenFilterEmptyCollectionByNullRoleThenReturnEmptyCollection() {
        final Collection<User> sortedUsers = service.filterByRole(new ArrayList<>(), null);
        assertTrue(sortedUsers.isEmpty());
    }

    @Test
    public void whenFilterNullCollectionByNullRoleThenReturnEmptyCollection() {
        final Collection<User> sortedUsers = service.filterByRole(null, null);
        assertTrue(sortedUsers.isEmpty());
    }

    @Test
    public void whenFilterEmptyCollectionByRoleThenReturnEmptyCollection() {
        Collection<User> sortedUsers;
        for (UserRole role : UserRole.values()) {
            sortedUsers = service.filterByRole(new ArrayList<>(), role);
            assertTrue(sortedUsers.isEmpty());
        }
    }

    @Test
    public void whenFilterNullCollectionByRoleThenReturnEmptyCollection() {
        Collection<User> sortedUsers;
        for (UserRole role : UserRole.values()) {
            sortedUsers = service.filterByRole(null, role);
            assertTrue(sortedUsers.isEmpty());
        }
    }

    @Test
    public void whenFilterByRolesThenReturnNotEmptyCollection() {
        final Collection<UserRole> roles = Arrays.asList(UserRole.values());
        final Collection<User> filteredUsers = service.filterByRoles(users, roles);
        assertFalse(filteredUsers.isEmpty());
    }

    @Test
    public void whenFilterByRolesThenReturnCollectionWithNotNullObjects() {
        final Collection<UserRole> roles = Arrays.asList(UserRole.values());
        final Collection<User> filteredUsers = service.filterByRoles(users, roles);
        filteredUsers.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenFilterEmptyCollectionByRolesThenReturnEmptyCollection() {
        final Collection<UserRole> roles = Arrays.asList(UserRole.values());
        final Collection<User> filteredUsers = service.filterByRoles(new ArrayList<>(), roles);
        assertTrue(filteredUsers.isEmpty());
    }

    @Test
    public void whenFilterNullCollectionByRolesThenReturnEmptyCollection() {
        final Collection<UserRole> roles = Arrays.asList(UserRole.values());
        final Collection<User> filteredUsers = service.filterByRoles(new ArrayList<>(), roles);
        assertTrue(filteredUsers.isEmpty());
    }

    @Test
    public void whenFilterByEmptyRolesThenReturnNotEmptyCollection() {
        final Collection<User> filteredUsers = service.filterByRoles(users, new ArrayList<>());
        assertFalse(filteredUsers.isEmpty());
    }

    @Test
    public void whenFilterEmptyCollectionByEmptyRolesThenReturnEmptyCollection() {
        final Collection<User> filteredUsers = service.filterByRoles(new ArrayList<>(), new ArrayList<>());
        assertTrue(filteredUsers.isEmpty());
    }

    @Test
    public void whenFilterNullCollectionByEmptyRolesThenReturnEmptyCollection() {
        final Collection<User> filteredUsers = service.filterByRoles(null, new ArrayList<>());
        assertTrue(filteredUsers.isEmpty());
    }

    @Test
    public void whenFilterByNullRolesThenReturnNotEmptyCollection() {
        final Collection<User> filteredUsers = service.filterByRoles(users, null);
        assertFalse(filteredUsers.isEmpty());
    }

    @Test
    public void whenFilterByNullRolesThenReturnCollectionWithNotNullObjects() {
        final Collection<User> filteredUsers = service.filterByRoles(users, null);
        filteredUsers.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenFilterEmptyCollectionByNullRolesThenReturnEmptyCollection() {
        final Collection<User> filteredUsers = service.filterByRoles(new ArrayList<>(), null);
        assertTrue(filteredUsers.isEmpty());
    }

    @Test
    public void whenFilterNullCollectionByNullRolesThenReturnEmptyCollection() {
        final Collection<User> filteredUsers = service.filterByRoles(null, null);
        assertTrue(filteredUsers.isEmpty());
    }

    @Test
    public void whenGetAndFilterByRoleThenReturnNotEmptyCollection() {
        Collection<User> filteredUsers;
        for (UserRole role : UserRole.values()) {
            filteredUsers = service.getAndFilterByRole(role);
            assertFalse(filteredUsers.isEmpty());
        }
    }

    @Test
    public void whenGetAndFilterByRoleThenReturnCollectionWithNotNullObjects() {
        Collection<User> filteredUsers;
        for (UserRole role : UserRole.values()) {
            filteredUsers = service.getAndFilterByRole(role);
            filteredUsers.forEach(Assert::assertNotNull);
        }
    }

    @Test
    public void whenGetAndFilterByNullRoleThenReturnEmptyCollection() {
        final Collection<User> filteredUsers = service.getAndFilterByRole(null);
        assertTrue(filteredUsers.isEmpty());
    }

    @Test
    public void whenGetAndFilterByRolesThenReturnNotEmptyCollection() {
        final Collection<UserRole> roles = Arrays.asList(UserRole.values());
        final Collection<User> filteredUsers = service.getAndFilterByRoles(roles);
        assertFalse(filteredUsers.isEmpty());
    }

    @Test
    public void whenGetAndFilterByRolesThenReturnCollectionWithNotNullObjects() {
        final Collection<UserRole> roles = Arrays.asList(UserRole.values());
        final Collection<User> filteredUsers = service.getAndFilterByRoles(roles);
        filteredUsers.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenGetAndFilterByEmptyRolesThenReturnFullCollection() {
        final Collection<User> filteredUsers = service.getAndFilterByRoles(new ArrayList<>());
        assertFalse(filteredUsers.isEmpty());
    }

    @Test
    public void whenGetAndFilterByNullRolesThenReturnFullCollection() {
        final Collection<User> filteredUsers = service.getAndFilterByRoles(null);
        assertFalse(filteredUsers.isEmpty());
    }

    @Test
    public void whenFilterByValidThenReturnNotEmptyCollection() {
        final Collection<User> filteredUsers = service.filterByValid(users);
        assertFalse(filteredUsers.isEmpty());
    }

    @Test
    public void whenFilterByValidThenReturnCollectionWithNotNullObjects() {
        final Collection<User> filteredUsers = service.filterByValid(users);
        filteredUsers.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenFilterEmptyCollectionByValidThenReturnEmptyCollection() {
        final Collection<User> filteredUsers = service.filterByValid(new ArrayList<>());
        assertTrue(filteredUsers.isEmpty());
    }

    @Test
    public void whenFilterNullCollectionByValidThenReturnEmptyCollection() {
        final Collection<User> filteredUsers = service.filterByValid(null);
        assertTrue(filteredUsers.isEmpty());
    }
    
    @Test
    public void whenGetAuthenticatedUserThenDoIt() {
        service.getAuthenticatedUser();
    }
    
    @Test
    public void whenIsAuthenticatedUserThenReturnFalse() {
        final boolean result = service.isAuthenticatedUser(user);
        assertFalse(result);
    }

    @Test
    public void whenIsAuthenticatedThenReturnFalse() {
        final boolean result = service.isAuthenticatedUser();
        assertFalse(result);
    }

    @Override
    protected UserService getService() {
        return service;
    }

    @Override
    protected User getModel() {
        return user;
    }

    @Override
    protected Collection<User> getModels() {
        return users;
    }

    @Override
    protected User getUnknownModel() {
        final UserBuilder builder = User.getBuilder();
        builder.addId(UNKNOWN_ID).addName(ANY_STRING).addUrl(ANY_STRING)
                .addLogin(ANY_STRING).addPassword(ANY_STRING);
        return builder.build();
    }

    @Override
    protected Comparator<User> getComparator() {
        return new UserComparator.ByName();
    }
}
