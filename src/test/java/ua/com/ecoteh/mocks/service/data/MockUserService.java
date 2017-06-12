package ua.com.ecoteh.mocks.service.data;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.com.ecoteh.entity.user.User;
import ua.com.ecoteh.entity.user.UserRole;
import ua.com.ecoteh.service.data.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModels.getUser;
import static ua.com.ecoteh.mocks.enity.MockModels.getUsers;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class MockUserService extends MockDataService<User> {

    private final UserService service;
    private final User user;
    private final Collection<User> users;
    private final Collection<UserRole> roles;

    MockUserService() {
        this.service = mock(UserService.class);
        this.user = getUser();
        this.users = getUsers();
        this.roles = Arrays.asList(UserRole.values());
    }

    @Override
    UserService create() {
        super.create();
        initAuthenticatedUser();
        initLoadUserByUsername();
        initGetByName();
        initGetByUrl();
        initGetByLogin();
        initGetByEmail();
        initGetByPhone();
        initGetPersonnel();
        initSortByName();
        initSortByUrl();
        initGetAndSortByName();
        initGetAndSortByUrl();
        initSortByRole();
        initGetAndSortByRole();
        initFilterByRole();
        initFilterByRoles();
        initGetAndFilterByRoles();
        initFilterByValid();
        return this.service;
    }

    @Override
    UserService getService() {
        return this.service;
    }

    @Override
    User getModel() {
        return this.user;
    }

    @Override
    Collection<User> getModels() {
        return this.users;
    }

    private void initAuthenticatedUser() {
        when(this.service.getAuthenticatedUser()).thenReturn(this.user);
        when(this.service.isAuthenticatedUser(this.user)).thenReturn(true);
        when(this.service.isAuthenticatedUser(null)).thenReturn(false);
    }

    private void initLoadUserByUsername() {
        when(this.service.loadUserByUsername(NAME)).thenReturn(this.user);
        when(this.service.loadUserByUsername(LOGIN)).thenReturn(this.user);
        when(this.service.loadUserByUsername(ANY_STRING)).thenThrow(new UsernameNotFoundException(""));
        when(this.service.loadUserByUsername("")).thenThrow(new UsernameNotFoundException(""));
        when(this.service.loadUserByUsername(null)).thenThrow(new UsernameNotFoundException(""));
    }

    private void initGetByName() {
        when(this.service.getByName(NAME)).thenReturn(this.user);
        when(this.service.getByName(ANY_STRING)).thenThrow(new NullPointerException());
        when(this.service.getByName("")).thenThrow(new IllegalArgumentException());
        when(this.service.getByName(null)).thenThrow(new IllegalArgumentException());
    }

    private void initGetByUrl() {
        when(this.service.getByUrl(URL)).thenReturn(this.user);
        when(this.service.getByUrl(ANY_STRING)).thenThrow(new NullPointerException());
        when(this.service.getByUrl("")).thenThrow(new IllegalArgumentException());
        when(this.service.getByUrl(null)).thenThrow(new IllegalArgumentException());
    }

    private void initGetByLogin() {
        when(this.service.getByLogin(LOGIN)).thenReturn(this.user);
        when(this.service.getByLogin(ANY_STRING)).thenThrow(new NullPointerException());
        when(this.service.getByLogin("")).thenThrow(new IllegalArgumentException());
        when(this.service.getByLogin(null)).thenThrow(new IllegalArgumentException());
    }

    private void initGetByEmail() {
        when(this.service.getByEmail(EMAIL)).thenReturn(this.user);
        when(this.service.getByEmail(ANY_STRING)).thenThrow(new NullPointerException());
        when(this.service.getByEmail("")).thenThrow(new IllegalArgumentException());
        when(this.service.getByEmail(null)).thenThrow(new IllegalArgumentException());
    }

    private void initGetByPhone() {
        when(this.service.getByPhone(PHONE)).thenReturn(this.user);
        when(this.service.getByPhone(PHONE_2)).thenReturn(this.user);
        when(this.service.getByPhone(ANY_STRING)).thenThrow(new NullPointerException());
        when(this.service.getByPhone("")).thenThrow(new IllegalArgumentException());
        when(this.service.getByPhone(null)).thenThrow(new IllegalArgumentException());
    }

    private void initGetPersonnel() {
        when(this.service.getMainAdmin()).thenReturn(this.user);
        when(this.service.getAdmins()).thenReturn(this.users);
        when(this.service.getPersonnel()).thenReturn(this.users);
    }

    private void initSortByName() {
        when(this.service.sortByName(this.users, true)).thenReturn(new ArrayList<>(this.users));
        when(this.service.sortByName(this.users, false)).thenReturn(new ArrayList<>(this.users));
        when(this.service.sortByName(new ArrayList<>(), true)).thenReturn(new ArrayList<>());
        when(this.service.sortByName(new ArrayList<>(), false)).thenReturn(new ArrayList<>());
        when(this.service.sortByName(null, true)).thenReturn(new ArrayList<>());
        when(this.service.sortByName(null, false)).thenReturn(new ArrayList<>());
    }

    private void initSortByUrl() {
        when(this.service.sortByUrl(this.users, true)).thenReturn(new ArrayList<>(this.users));
        when(this.service.sortByUrl(this.users, false)).thenReturn(new ArrayList<>(this.users));
        when(this.service.sortByUrl(new ArrayList<>(), true)).thenReturn(new ArrayList<>());
        when(this.service.sortByUrl(new ArrayList<>(), false)).thenReturn(new ArrayList<>());
        when(this.service.sortByUrl(null, true)).thenReturn(new ArrayList<>());
        when(this.service.sortByUrl(null, false)).thenReturn(new ArrayList<>());
    }

    private void initGetAndSortByName() {
        when(this.service.getAndSortByName(true)).thenReturn(new ArrayList<>(this.users));
        when(this.service.getAndSortByName(false)).thenReturn(new ArrayList<>(this.users));
    }

    private void initGetAndSortByUrl() {
        when(this.service.getAndSortByUrl(true)).thenReturn(new ArrayList<>(this.users));
        when(this.service.getAndSortByUrl(false)).thenReturn(new ArrayList<>(this.users));
    }

    private void initSortByRole() {
        when(this.service.sortByRole(this.users, null, true)).thenReturn(new ArrayList<>(this.users));
        when(this.service.sortByRole(this.users, null, false)).thenReturn(new ArrayList<>(this.users));
        when(this.service.sortByRole(new ArrayList<>(), null, true)).thenReturn(new ArrayList<>());
        when(this.service.sortByRole(new ArrayList<>(), null, false)).thenReturn(new ArrayList<>());
        when(this.service.sortByRole(null, null, true)).thenReturn(new ArrayList<>());
        when(this.service.sortByRole(null, null, false)).thenReturn(new ArrayList<>());
        for (UserRole role : roles) {
            when(this.service.sortByRole(this.users, role, true)).thenReturn(new ArrayList<>(this.users));
            when(this.service.sortByRole(this.users, role, false)).thenReturn(new ArrayList<>(this.users));
            when(this.service.sortByRole(new ArrayList<>(), role, true)).thenReturn(new ArrayList<>());
            when(this.service.sortByRole(new ArrayList<>(), role, false)).thenReturn(new ArrayList<>());
            when(this.service.sortByRole(null, role, true)).thenReturn(new ArrayList<>());
            when(this.service.sortByRole(null, role, false)).thenReturn(new ArrayList<>());
        }
    }

    private void initGetAndSortByRole() {
        when(this.service.getAndSortByRole(null, true)).thenReturn(new ArrayList<>());
        when(this.service.getAndSortByRole(null, false)).thenReturn(new ArrayList<>());
        for (UserRole role : roles) {
            when(this.service.getAndSortByRole(role, true)).thenReturn(new ArrayList<>(this.users));
            when(this.service.getAndSortByRole(role, false)).thenReturn(new ArrayList<>(this.users));
        }
    }

    private void initFilterByRole() {
        when(this.service.filterByRole(this.users, null)).thenReturn(new ArrayList<>(this.users));
        when(this.service.filterByRole(new ArrayList<>(), null)).thenReturn(new ArrayList<>());
        when(this.service.filterByRole(null, null)).thenReturn(new ArrayList<>());
        for (UserRole role : roles) {
            when(this.service.filterByRole(this.users, role)).thenReturn(new ArrayList<>(this.users));
            when(this.service.filterByRole(new ArrayList<>(), role)).thenReturn(new ArrayList<>());
            when(this.service.filterByRole(null, role)).thenReturn(new ArrayList<>());
        }
    }

    private void initFilterByRoles() {
        when(this.service.filterByRoles(this.users, this.roles)).thenReturn(new ArrayList<>(this.users));
        when(this.service.filterByRoles(new ArrayList<>(), this.roles)).thenReturn(new ArrayList<>());
        when(this.service.filterByRoles(null, this.roles)).thenReturn(new ArrayList<>());
        when(this.service.filterByRoles(this.users, new ArrayList<>())).thenReturn(new ArrayList<>(this.users));
        when(this.service.filterByRoles(new ArrayList<>(), new ArrayList<>())).thenReturn(new ArrayList<>());
        when(this.service.filterByRoles(null, new ArrayList<>())).thenReturn(new ArrayList<>());
        when(this.service.filterByRoles(this.users, null)).thenReturn(new ArrayList<>(this.users));
        when(this.service.filterByRoles(new ArrayList<>(), null)).thenReturn(new ArrayList<>());
        when(this.service.filterByRoles(null, null)).thenReturn(new ArrayList<>());
    }

    private void initGetAndFilterByRoles() {
        when(this.service.getAndFilterByRole(null)).thenReturn(new ArrayList<>());
        when(this.service.getAndFilterByRoles(this.roles)).thenReturn(new ArrayList<>(this.users));
        when(this.service.getAndFilterByRoles(new ArrayList<>())).thenReturn(new ArrayList<>());
        when(this.service.getAndFilterByRoles(null)).thenReturn(new ArrayList<>());
        for (UserRole role : roles) {
            when(this.service.getAndFilterByRole(role)).thenReturn(new ArrayList<>(this.users));
        }
    }

    private void initFilterByValid() {
        when(this.service.filterByValid(this.users)).thenReturn(new ArrayList<>(this.users));
        when(this.service.filterByValid(new ArrayList<>())).thenReturn(new ArrayList<>());
        when(this.service.filterByValid(null)).thenReturn(new ArrayList<>());
    }
}
