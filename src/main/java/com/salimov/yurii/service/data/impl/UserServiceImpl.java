package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.config.DefaultConfig;
import com.salimov.yurii.dao.interfaces.UserDao;
import com.salimov.yurii.entity.File;
import com.salimov.yurii.entity.User;
import com.salimov.yurii.enums.UserRole;
import com.salimov.yurii.service.data.interfaces.UserService;
import com.salimov.yurii.util.comparator.UserComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * The class of the service layer, implements a set of methods for working
 * with objects of the {@link User} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see User
 * @see UserService
 * @see DataServiceImpl
 * @see UserDao
 */
@Service
@ComponentScan(
        basePackages = {
                "com.salimov.yurii.dao",
                "com.salimov.yurii.service.data"
        }
)
public final class UserServiceImpl
        extends DataServiceImpl<User, Long>
        implements UserService, UserDetailsService {

    /**
     * The interface provides a set of standard methods for working
     * {@link User} objects with the database.
     *
     * @see UserDao
     * @see User
     */
    private final UserDao dao;

    /**
     * Constructor.
     *
     * @param dao a implementation of the {@link UserDao}
     *            interface.
     * @see UserDao
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public UserServiceImpl(
            final UserDao dao
    ) {
        super(dao);
        this.dao = dao;
    }

    /**
     * Returns authenticated user.
     *
     * @return The authenticated user.
     * @see User
     */
    @Override
    @Transactional(readOnly = true)
    public User getAuthenticatedUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    /**
     * Locates the user based on the username.
     *
     * @param username The username identifying the user whose data is required.
     * @return A fully populated user record (never {@code null}).
     * @throws UsernameNotFoundException if the user could not be found
     *                                   or the user has no GrantedAuthority
     * @see User
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {
        try {
            User user = DefaultConfig.getDefaultUser(username);
            if (user == null) {
                user = getByLogin(username);
            }
            return user;
        } catch (NullPointerException ex) {
            throw new UsernameNotFoundException(ex.getMessage(), ex);
        }
    }

    /**
     * Initializes, saves and returns a new user.
     *
     * @param name        a name of the new user.
     * @param login       a login of the new user.
     * @param password    a password of the new user.
     * @param description a description of the new user.
     * @param phone       a phone of the new user.
     * @param email       a e-mail of the new user.
     * @param vkontakte   a vkontakte url of the new user.
     * @param facebook    a facebook url of the new user.
     * @param twitter     a twitter url of the new user.
     * @param skype       a skype username of the new user.
     * @param photoUrl    a photo Url to the new user.
     * @param isValid     a validated of the new user.
     * @param isMailing   a permit to send a letters on the user email.
     * @param isLocked    locked the user or not.
     * @return The new saving user.
     * @see User
     * @see File
     * @see UserRole
     */
    @Override
    @Transactional
    public User initAndAdd(
            final String name,
            final String login,
            final String password,
            final String description,
            final String phone,
            final String email,
            final String vkontakte,
            final String facebook,
            final String twitter,
            final String skype,
            final String photoUrl,
            final boolean isValid,
            final boolean isMailing,
            final boolean isLocked
    ) {
        final User user = new User();
        user.initialize(
                name, login, password,
                email, phone,
                vkontakte, facebook, twitter, skype,
                description
        );
        user.setPhotoUrl(photoUrl);
        user.setRole(UserRole.ADMIN);
        user.setValidated(isValid);
        user.setMailing(isMailing);
        user.setLocked(isLocked);
        return add(user);
    }

    /**
     * Initializes, updates and returns user with parameter id.
     * Return {@code null} if id is {@code null}.
     *
     * @param url         a url of the user to update.
     * @param name        a new name to the user.
     * @param login       a new login to the user.
     * @param password    a new password to the user.
     * @param description a new description to the user.
     * @param phone       a new phone to the user.
     * @param email       a new e-mail to the user.
     * @param vkontakte   a new vkontakte url to the user.
     * @param facebook    a new facebook url to the user.
     * @param twitter     a new twitter url to the user.
     * @param skype       a new skype username to the user.
     * @param photoUrl    a new photo URL to the user.
     * @param isValid     a validated of the user.
     * @param isMailing   a permit to send a letters on the user email.
     * @param isLocked    locked the user or not.
     * @return The updating user with parameter id or {@code null}.
     * @see User
     * @see UserRole
     */
    @Override
    @Transactional
    public User initAndUpdate(
            final String url,
            final String name,
            final String login,
            final String password,
            final String description,
            final String phone,
            final String email,
            final String vkontakte,
            final String facebook,
            final String twitter,
            final String skype,
            final String photoUrl,
            final boolean isValid,
            final boolean isMailing,
            final boolean isLocked
    ) {
        final User user = getByUrl(url);
        user.initialize(
                name, login, password,
                email, phone,
                vkontakte, facebook, twitter, skype,
                description
        );
        user.setPhotoUrl(photoUrl);
        user.setValidated(isValid);
        user.setMailing(isMailing);
        user.setLocked(isLocked);
        return update(user);
    }

    /**
     * Returns user with the parameter name.
     * Returns {@code null} in name is blank.
     *
     * @param name a name of the user to return.
     * @return The user with the parameter name or {@code null}.
     * @throws IllegalArgumentException Throw exception when parameter name
     *                                  is blank.
     * @throws NullPointerException     Throws exception if user is absent.
     * @see User
     */
    @Override
    @Transactional(readOnly = true)
    public User getByName(final String name)
            throws IllegalArgumentException, NullPointerException {
        if (isBlank(name)) {
            throw new IllegalArgumentException(
                    getClassSimpleName() + " name is blank!"
            );
        }
        final User user = this.dao.getByName(name);
        if (user == null) {
            throw new NullPointerException(
                    "Can`t find user by name \"" + name + "\"!"
            );
        }
        return user;
    }

    /**
     * Returns user with the parameter url.
     * Returns {@code null} in url is blank.
     *
     * @param url a url of the user to return.
     * @return The user with the parameter url or {@code null}.
     * @throws IllegalArgumentException Throw exception when parameter url
     *                                  is blank.
     * @throws NullPointerException     Throws exception if user is absent.
     * @see User
     */
    @Override
    @Transactional(readOnly = true)
    public User getByUrl(final String url)
            throws IllegalArgumentException, NullPointerException {
        if (isBlank(url)) {
            throw new IllegalArgumentException(
                    getClassSimpleName() + " url is blank!"
            );
        }
        final User user = this.dao.getByUrl(url);
        if (user == null) {
            throw new NullPointerException(
                    "Can`t find user by URL \"" + url + "\"!"
            );
        }
        return user;
    }

    /**
     * Returns user with the parameter login.
     * Returns {@code null} in login is blank.
     *
     * @param login a login of the user to return.
     * @return The user with the parameter login or {@code null}.
     * @throws IllegalArgumentException Throw exception when parameter login
     *                                  is blank.
     * @throws NullPointerException     Throws exception if user is absent.
     * @see User
     */
    @Override
    @Transactional(readOnly = true)
    public User getByLogin(final String login)
            throws IllegalArgumentException, NullPointerException {
        if (isBlank(login)) {
            throw new IllegalArgumentException(
                    getClassSimpleName() + " login is blank!"
            );
        }
        final User user = this.dao.getByLogin(login);
        if (user == null) {
            throw new NullPointerException(
                    "Can`t find user by login \"" + login + "\"!"
            );
        }
        return user;
    }

    /**
     * Returns user with the parameter phone.
     * Returns {@code null} in phone is blank.
     *
     * @param phone a phone of the user to return.
     * @return The user with the parameter phone or {@code null}.
     * @throws IllegalArgumentException Throw exception when parameter phone
     *                                  is blank.
     * @throws NullPointerException     Throws exception if user is absent.
     * @see User
     */
    @Override
    @Transactional(readOnly = true)
    public User getByPhone(final String phone) throws NullPointerException {
        if (isBlank(phone)) {
            throw new IllegalArgumentException(
                    getClassSimpleName() + " phone is blank!"
            );
        }
        final User user = this.dao.getByPhone(phone);
        if (user == null) {
            throw new NullPointerException(
                    "Can`t find user by phone \"" + phone + "\"!"
            );
        }
        return user;
    }

    /**
     * Returns user with the parameter e-mail.
     * Returns {@code null} in e-mail is blank.
     *
     * @param email a e-mail of the user to return.
     * @return The user with the parameter e-mail or {@code null}.
     * @throws IllegalArgumentException Throw exception when parameter email
     *                                  is blank.
     * @throws NullPointerException     Throws exception if user is absent.
     * @see User
     */
    @Override
    @Transactional(readOnly = true)
    public User getByEmail(final String email) throws NullPointerException {
        if (isBlank(email)) {
            throw new IllegalArgumentException(
                    getClassSimpleName() + " email is blank!"
            );
        }
        final User user = this.dao.getByEmail(email);
        if (user == null) {
            throw new NullPointerException(
                    "Can`t find user by email \"" + email + "\"!"
            );
        }
        return user;
    }

    /**
     * Returns main admin user.
     *
     * @return The main admin.
     */
    @Override
    @Transactional(readOnly = true)
    public User getMainAdmin() {
        return get(1L);
    }

    /**
     * Returns users with role {@code ADMIN}.
     *
     * @return The users with role {@code ADMIN}.
     * @see User
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<User> getAdmins() {
        return getAndFilterByRole(
                UserRole.ADMIN
        );
    }

    /**
     * Returns personnel.
     *
     * @return The all personnel.
     * @see User
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<User> getPersonnel() {
        return getAdmins();
    }

    /**
     * Removes user with the parameter name.
     * Removes user if name is not blank.
     *
     * @param name a name of the user to remove.
     * @see User
     */
    @Override
    @Transactional
    public void removeByName(final String name) {
        remove(
                getByName(name)
        );
    }

    /**
     * Removes user with the parameter url.
     * Removes user if url is not blank.
     *
     * @param url a url of the user to remove.
     * @see User
     */
    @Override
    @Transactional
    public void removeByUrl(final String url) {
        remove(
                getByUrl(url)
        );
    }

    /**
     * Removes user with the parameter login.
     * Removes user if login is not blank.
     *
     * @param login a login of the user to remove.
     * @see User
     */
    @Override
    @Transactional
    public void removeByLogin(final String login) {
        remove(
                getByLogin(login)
        );
    }

    /**
     * Sorts and returns users by name.
     *
     * @param users  the users to sort.
     * @param revers Sort in descending or ascending.
     * @return The sorted list of users.
     * @see User
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> sortByName(
            final Collection<User> users,
            final boolean revers
    ) {
        return sort(
                users,
                new UserComparator.ByName(),
                revers
        );
    }

    /**
     * Sorts and returns users by url.
     *
     * @param users  the users to sort.
     * @param revers is sort in descending or ascending.
     * @return The sorted list of users.
     * @see User
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> sortByUrl(
            final Collection<User> users,
            final boolean revers
    ) {
        return sort(
                users,
                new UserComparator.ByUrl(),
                revers
        );
    }

    /**
     * Sorts and returns users by role.
     *
     * @param users  the users to sort.
     * @param role   a role filtering.
     * @param revers is ort in descending or ascending.
     * @return The sorted list of users.
     * @see User
     * @see UserRole
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> sortByRole(
            final Collection<User> users,
            final UserRole role,
            final boolean revers
    ) {
        return sort(
                users,
                new UserComparator.ByRole(role),
                revers
        );
    }

    /**
     * Sorts and returns users by date.
     *
     * @param revers is ort in descending or ascending.
     * @return The sorted list of articles.
     * @see User
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getAndSortByName(final boolean revers) {
        return sortByName(
                getAll(),
                revers
        );
    }

    /**
     * Sorts and returns users by url.
     *
     * @param revers is ort in descending or ascending.
     * @return The sorted list of users.
     * @see User
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getAndSortByUrl(final boolean revers) {
        return sortByUrl(
                getAll(),
                revers
        );
    }

    /**
     * Sorts and returns users by role.
     *
     * @param role   a role filtering.
     * @param revers is ort in descending or ascending.
     * @return The sorted list of users.
     * @see User
     * @see UserRole
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getAndSortByRole(
            final UserRole role,
            final boolean revers
    ) {
        return sortByRole(
                getAll(),
                role,
                revers
        );
    }

    /**
     * Filters and returns users by role.
     *
     * @param users the users to filter.
     * @param role  a role filtering.
     * @return The filtered list of users.
     * @see User
     * @see UserRole
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> filterByRole(
            final Collection<User> users,
            final UserRole role
    ) {
        List<UserRole> roles = null;
        if (role != null) {
            roles = new ArrayList<>(1);
            roles.add(role);
        }
        return filterByRoles(users, roles);
    }

    /**
     * Filters and returns users by roles.
     * Returns empty list if users is empty.
     * Returns back users if roles is empty.
     *
     * @param users the users to filter.
     * @param roles a roles filtering.
     * @return The filtered list of users.
     * @see User
     * @see UserRole
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> filterByRoles(
            final Collection<User> users,
            final List<UserRole> roles
    ) {
        final List<User> result = new ArrayList<>();
        if ((users != null) && !users.isEmpty()) {
            if ((roles != null) && !roles.isEmpty()) {
                for (User user : users) {
                    result.addAll(
                            roles.stream()
                                    .filter(
                                            role -> user.getRole()
                                                    .equals(role)
                                    )
                                    .map(
                                            role -> user
                                    )
                                    .collect(
                                            Collectors.toList()
                                    )
                    );
                }
            } else {
                result.addAll(users);
            }
        }
        return result;
    }

    /**
     * Filters and returns users by role.
     *
     * @param role a role filtering.
     * @return The filtered list of users.
     * @see User
     * @see UserRole
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getAndFilterByRole(final UserRole role) {
        return filterByRole(
                getAll(false),
                role
        );
    }

    /**
     * Filters and returns users by roles.
     *
     * @param roles a roles filtering.
     * @return The filtered list of users.
     * @see User
     * @see UserRole
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getAndFilterByRoles(
            final List<UserRole> roles
    ) {
        return filterByRoles(
                getAll(),
                roles
        );
    }

    /**
     * Returns a list of valid users.
     *
     * @param users the users to filter.
     * @return The list of valid users.
     * @see User
     */
    @Override
    @Transactional
    public List<User> filteredByValid(
            final Collection<User> users
    ) {
        List<User> result = new ArrayList<>();
        if (users != null && !users.isEmpty()) {
            result.addAll(
                    users.stream()
                            .filter(
                                    user -> (
                                            user != null
                                    ) && (
                                            user.isValidated()
                                    )
                            )
                            .collect(
                                    Collectors.toList()
                            )
            );
        }
        return result;
    }

    /**
     * Return Class object of {@link User} class.
     *
     * @return The Class object of {@link User} class.
     */
    @Override
    protected Class<User> getModelClass() {
        return User.class;
    }

    /**
     * Validates input user.
     *
     * @param user               the user to valid.
     * @param requiredParameters is validate input object
     *                           by required parameters.
     * @param exist              is validate input object by exists.
     * @param duplicate          is validate input object by duplicate.
     * @return Returns {@code true} if user is valid,
     * {@code false} otherwise.
     * @see User
     */
    @Override
    protected boolean validated(
            final User user,
            final boolean requiredParameters,
            final boolean exist,
            final boolean duplicate
    ) {
        if (user == null) {
            return false;
        }
        if (requiredParameters && !User.isValidated(user)) {
            return false;
        }
        if (exist && !exists(user)) {
            return false;
        }
        if (duplicate) {
            if ((
                    this.dao.getByName(
                            user.getName()
                    ) != null
            ) || (
                    this.dao.getByUrl(
                            user.getUrl()
                    ) != null
            )) {
                return false;
            }
        }
        return true;
    }
}
