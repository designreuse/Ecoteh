package ua.com.ecoteh.service.data;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.ecoteh.config.DefaultConfig;
import ua.com.ecoteh.entity.File;
import ua.com.ecoteh.entity.User;
import ua.com.ecoteh.enums.UserRole;
import ua.com.ecoteh.exception.ExceptionMessage;
import ua.com.ecoteh.repository.UserRepository;
import ua.com.ecoteh.util.comparator.UserComparator;
import ua.com.ecoteh.util.encryption.Encryptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ua.com.ecoteh.util.validator.ObjectValidator.*;

/**
 * The class of the service layer, implements a set of methods for working
 * with objects of the {@link User} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Service
@ComponentScan(
        basePackages = {
                "ua.com.ecoteh.repository",
                "ua.com.ecoteh.service.data"
        }
)
public final class UserServiceImpl extends DataServiceImpl<User>
        implements UserService, UserDetailsService {

    /**
     * The object for logging information.
     */
    private final static Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    /**
     * The interface provides a set of standard methods for working
     * {@link User} objects with the database.
     */
    private final UserRepository repository;

    /**
     * The interface of the service layer, describes a set of methods
     * for working with objects of the {@link File} class.
     */
    private final FileService fileService;

    /**
     * Constructor.
     *
     * @param repository  the implementation of the {@link UserRepository} interface.
     * @param fileService the implementation of the {@link FileService} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public UserServiceImpl(
            final UserRepository repository,
            final FileService fileService
    ) {
        super(repository);
        this.repository = repository;
        this.fileService = fileService;
    }

    /**
     * Returns authenticated user.
     * If authenticated user is empty then returns null.
     *
     * @return The authenticated user or null.
     */
    @Override
    @Transactional(readOnly = true)
    public User getAuthenticatedUser() {
        User user;
        try {
            user = (User) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            user = null;
        }
        return user;
    }

    /**
     * Locates the user based on the username.
     * If can`t find user by incoming username then throws UsernameNotFoundException.
     *
     * @param username the username identifying the user whose data is required.
     * @return A fully populated user record (never null).
     * @throws UsernameNotFoundException if the user could not be found
     *                                   or the user has no GrantedAuthority.
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user;
        try {
            user = DefaultConfig.getDefaultUser(username);
            if (isNull(user)) {
                user = getByLogin(username);
            }
        } catch (NullPointerException ex) {
            throw new UsernameNotFoundException(ex.getMessage(), ex);
        }
        return user;
    }

    /**
     * Saves and returns a new user.
     *
     * @param user a user to save.
     * @return The new saving user (never null).
     */
    @Override
    @Transactional
    public User add(final User user) {
        if (isNotNull(user)) {
            user.setRole(UserRole.ADMIN);
        }
        return super.add(user);
    }

    /**
     * Updates and returns user with incoming URL.
     *
     * @param url  the URL of a user to update.
     * @param user the user to update.
     * @return The updating user with incoming URL (never null).
     */
    @Override
    @Transactional
    public User update(
            final String url,
            final User user
    ) {
        final User userToUpdate = getByUrl(url);
        final File newPhoto = user.getPhoto();
        final File oldPhoto = userToUpdate.getPhoto();
        if (isNewPhoto(newPhoto, oldPhoto)) {
            this.fileService.deleteFile(oldPhoto.getUrl());
        }
        copy(user, userToUpdate);
        return update(userToUpdate);
    }

    /**
     * Returns user with the incoming name.
     * If a incoming name is null or empty then throws IllegalArgumentException.
     * If can`t find user by incoming name then throws NullPointerException.
     *
     * @param name the name of a user to return.
     * @return The user with the incoming name (never null).
     * @throws IllegalArgumentException Throw exception when parameter name is blank.
     * @throws NullPointerException     Throws exception if user is absent.
     */
    @Override
    @Transactional(readOnly = true)
    public User getByName(final String name) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(name)) {
            throw getIllegalArgumentException(
                    ExceptionMessage.BLANK_NAME_MESSAGE,
                    getClassSimpleName()
            );
        }
        final User user = this.repository.findByName(name);
        if (isNull(user)) {
            throw getNullPointerException(
                    ExceptionMessage.FINDING_BY_NAME_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName(), name
            );
        }
        return user;
    }

    /**
     * Returns user with the incoming URL.
     * If a incoming URL is null or empty then throws IllegalArgumentException.
     * If can`t find user by incoming URL then throws NullPointerException.
     *
     * @param url the URL of a user to return.
     * @return The user with the incoming URL (never null).
     * @throws IllegalArgumentException Throw exception when parameter URL is blank.
     * @throws NullPointerException     Throws exception if user is absent.
     */
    @Override
    @Transactional(readOnly = true)
    public User getByUrl(final String url) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(url)) {
            throw getIllegalArgumentException(
                    ExceptionMessage.BLANK_URL_MESSAGE,
                    getClassSimpleName()
            );
        }
        final User user = this.repository.findByUrl(url);
        if (isNull(user)) {
            throw getNullPointerException(
                    ExceptionMessage.FINDING_BY_URL_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName(), url
            );
        }
        return user;
    }

    /**
     * Returns user with the incoming login.
     * If a incoming login is null or empty then throws IllegalArgumentException.
     * If can`t find user by incoming login then throws NullPointerException.
     *
     * @param login the login of a user to return.
     * @return The user with the incoming login (never null).
     * @throws IllegalArgumentException Throw exception when parameter login is blank.
     * @throws NullPointerException     Throws exception if user is absent.
     */
    @Override
    @Transactional(readOnly = true)
    public User getByLogin(final String login) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(login)) {
            throw getIllegalArgumentException(
                    ExceptionMessage.BLANK_LOGIN_MESSAGE,
                    getClassSimpleName()
            );
        }
        final User user = this.repository.findByEncryptedLogin(
                new Encryptor(login).encrypt()
        );
        if (isNull(user)) {
            throw getNullPointerException(
                    ExceptionMessage.FINDING_BY_LOGIN_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName(), login
            );
        }
        return user;
    }

    /**
     * Returns user with the incoming E-mail.
     * If a incoming E-mail is null or empty then throws IllegalArgumentException.
     * If can`t find user by incoming E-mail then throws NullPointerException.
     *
     * @param email the E-mail of a user to return.
     * @return The user with the incoming E-mail (never null).
     * @throws IllegalArgumentException Throw exception when parameter E-mail is blank.
     * @throws NullPointerException     Throws exception if user is absent.
     */
    @Override
    @Transactional(readOnly = true)
    public User getByEmail(final String email) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(email)) {
            throw getIllegalArgumentException(
                    ExceptionMessage.BLANK_EMAIL_MESSAGE,
                    getClassSimpleName()
            );
        }
        User user = this.repository.findByContactsEmail(email);
        if (isNull(user)) {
            throw getNullPointerException(
                    ExceptionMessage.FINDING_BY_EMAIL_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName(), email
            );
        }
        return user;
    }

    /**
     * Returns user with the incoming phone.
     * If a incoming phone is null or empty then throws IllegalArgumentException.
     * If can`t find user by incoming phone then throws NullPointerException.
     *
     * @param phone the phone of a user to return.
     * @return The user with the incoming phone (never null).
     * @throws IllegalArgumentException Throw exception when parameter phone is blank.
     * @throws NullPointerException     Throws exception if user is absent.
     */
    @Override
    @Transactional(readOnly = true)
    public User getByPhone(final String phone) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(phone)) {
            throw getIllegalArgumentException(
                    ExceptionMessage.BLANK_PHONE_MESSAGE,
                    getClassSimpleName()
            );
        }
        User user = getByMobilePhone(phone);
        if (isNull(user)) {
            user = getByLandlinePhone(phone);
            if (isNull(user)) {
                user = getByFax(phone);
                if (isNull(user)) {
                    throw getNullPointerException(
                            ExceptionMessage.FINDING_BY_PHONE_OBJECT_IS_NULL_MESSAGE,
                            getClassSimpleName(), phone
                    );
                }
            }
        }
        return user;
    }

    /**
     * Returns main admin user.
     *
     * @return The main admin (never null).
     */
    @Override
    @Transactional(readOnly = true)
    public User getMainAdmin() {
        return get(1L);
    }

    /**
     * Returns users with role UserRole.ADMIN.
     *
     * @return The users with role UserRole.ADMIN (never null).
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<User> getAdmins() {
        return getAndFilterByRole(UserRole.ADMIN);
    }

    /**
     * Returns personnel.
     *
     * @return The all personnel (never null).
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<User> getPersonnel() {
        return getAdmins();
    }

    /**
     * Removes user with the parameter name.
     * Removes user if name is not null and not empty.
     *
     * @param name the name of a user to remove.
     */
    @Override
    @Transactional
    public void removeByName(final String name) {
        if (isNotEmpty(name)) {
            this.repository.deleteByName(name);
        }
    }

    /**
     * Removes user with the incoming URL.
     * Removes user if URL is not null and not empty.
     *
     * @param url the URL of a user to remove.
     */
    @Override
    @Transactional
    public void removeByUrl(final String url) {
        if (isNotEmpty(url)) {
            this.repository.deleteByUrl(url);
        }
    }

    /**
     * Sorts and returns users by name.
     * For sorting used {@link UserComparator.ByName} comparator.
     *
     * @param users  the users to sort.
     * @param revers Sort in descending or ascending.
     * @return The sorted list of users or empty list (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> sortByName(
            final Collection<User> users,
            final boolean revers
    ) {
        return sort(users, new UserComparator.ByName(), revers);
    }

    /**
     * Sorts and returns users by URL.
     * For sorting used {@link UserComparator.ByUrl} comparator.
     *
     * @param users  the users to sort.
     * @param revers is sort in descending or ascending.
     * @return The sorted list of users or empty list (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> sortByUrl(
            final Collection<User> users,
            final boolean revers
    ) {
        return sort(users, new UserComparator.ByUrl(), revers);
    }

    /**
     * Sorts and returns users by role.
     * For sorting used {@link UserComparator.ByRole} comparator.
     *
     * @param users  the users to sort.
     * @param role   the role filtering.
     * @param revers is sort in descending or ascending.
     * @return The sorted list of users or empty list (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> sortByRole(
            final Collection<User> users,
            final UserRole role,
            final boolean revers
    ) {
        return sort(users, new UserComparator.ByRole(role), revers);
    }

    /**
     * Sorts and returns all users by name.
     * For sorting used {@link UserComparator.ByName} comparator.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of articles.
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getAndSortByName(final boolean revers) {
        return sortByName(getAll(), revers);
    }

    /**
     * Sorts and returns all users by URL.
     * For sorting used {@link UserComparator.ByUrl} comparator.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of users.
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getAndSortByUrl(final boolean revers) {
        return sortByUrl(getAll(), revers);
    }

    /**
     * Sorts and returns all users by role.
     * For sorting used {@link UserComparator.ByRole} comparator.
     *
     * @param role   a role filtering.
     * @param revers is sort in descending or ascending.
     * @return The sorted list of users.
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getAndSortByRole(
            final UserRole role,
            final boolean revers
    ) {
        return sortByRole(getAll(), role, revers);
    }

    /**
     * Filters and returns users by the incoming role.
     * <pre>
     *     filterByRole(null, null) = empty ArrayList()
     *     filterByRole(null, UserRole.ADMIN) = empty ArrayList()
     *
     *     Collection users = new ArrayList();
     *     users.add(new User());
     *     filterByCategory(users, UserRole.ADMIN) = filtered list of users
     * </pre>
     *
     * @param users the users to filter.
     * @param role  a role filtering.
     * @return The filtered list of users.
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> filterByRole(
            final Collection<User> users,
            final UserRole role
    ) {
        List<UserRole> roles = null;
        if (isNotNull(role)) {
            roles = new ArrayList<>(1);
            roles.add(role);
        }
        return filterByRoles(users, roles);
    }

    /**
     * Filters and returns users by incoming roles.
     * Returns empty list if users is empty.
     * Returns back users if roles is empty.
     * <pre>
     *     filterByRoles(null, ..) = empty ArrayList()
     *     filterByRoles(new ArrayList(), ..) = empty ArrayList()
     *
     *     Collection roles = new ArrayList();
     *     roles.add(UserRole.ADMIN);
     *     filterByCategories(null, roles) = empty ArrayList()
     *
     *     Collection users = new ArrayList();
     *     users.add(new User());
     *     filterByCategories(users, null) = users
     *
     *     filterByCategories(users, roles) = filtered list of users
     * </pre>
     *
     * @param users the users to filter.
     * @param roles a roles filtering.
     * @return The filtered list of users.
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> filterByRoles(
            final Collection<User> users,
            final Collection<UserRole> roles
    ) {
        final List<User> result = new ArrayList<>();
        if (isNotEmpty(users)) {
            if (isNotEmpty(roles)) {
                for (User user : users) {
                    result.addAll(
                            roles.stream()
                                    .filter(role -> roleFilter(user, role))
                                    .map(role -> user)
                                    .collect(Collectors.toList())
                    );
                }
            } else {
                result.addAll(users);
            }
        }
        return result;
    }

    /**
     * Filters and returns users by incoming role.
     * <pre>
     *     getAndFilterByRole(null) = all users
     *     getAndFilterByRole(UserRole.ADMIN) = filtered list of users
     * </pre>
     *
     * @param role a role filtering.
     * @return The filtered list of users.
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getAndFilterByRole(final UserRole role) {
        return filterByRole(getAll(false), role);
    }

    /**
     * Filters and returns users by roles.
     * <pre>
     *     getAndFilterByRoles(null) = all users
     *     getAndFilterByRoles(new ArrayList()) = all users
     *
     *     Collection roles = new ArrayList();
     *     roles.add(UserRole.ADMIN);
     *     getAndFilterByRoles(roles) = filtered list of users
     * </pre>
     *
     * @param roles a roles filtering.
     * @return The filtered list of users.
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getAndFilterByRoles(final Collection<UserRole> roles) {
        return filterByRoles(getAll(), roles);
    }

    /**
     * Returns a list valid users.
     * Returns empty list if users is empty.
     * <pre>
     *     filteredByValid(null) = empty ArrayList()
     *     filteredByValid(new ArrayList()) = empty ArrayList()
     *
     *     Collection users = new ArrayList();
     *     User user = new User();
     *     user.setValidated(false);
     *     users.add(user);
     *     filteredByValid(users) = empty ArrayList()
     *
     *     user.setValidated(true);
     *     filteredByValid(users) = filtered list of users
     * </pre>
     *
     * @param users the users to filter.
     * @return The filtered list of users or empty list (newer null).
     */
    @Override
    @Transactional
    public List<User> filteredByValid(final Collection<User> users) {
        List<User> result = new ArrayList<>();
        if (isNotEmpty(users)) {
            result.addAll(
                    users.stream()
                            .filter(UserServiceImpl::isValidated)
                            .collect(Collectors.toList())
            );
        }
        return result;
    }

    /**
     * Validates input user.
     *
     * @param user      the user to valid.
     * @param exist     is validate input object by exists.
     * @param duplicate is validate input object by duplicate.
     * @return Returns true if user is valid, false otherwise.
     */
    @Override
    protected boolean validated(
            final User user,
            final boolean exist,
            final boolean duplicate
    ) {
        if (isNull(user)) {
            return false;
        }
        if (exist && !exists(user)) {
            return false;
        }
        if (duplicate) {
            if (isNotNull(this.repository.findByName(user.getName())) ||
                    isNotNull(this.repository.findByUrl(user.getUrl()))) {
                return false;
            }
        }
        return true;
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
     * Copies the object "from" to object "to".
     * Incoming objects must be not null.
     *
     * @param from the copied object
     * @param to   the object to copy
     */
    protected void copy(final User from, final User to) {
        if (!isUser(to)) {
            from.setLogin(to.getLogin());
            from.setPassword(to.getPassword());
        }
        to.initialize(from);
    }

    private boolean isUser(final User user) {
        return user.equals(getAuthenticatedUser()) ||
                getAuthenticatedUser().getRole().equals(UserRole.SUPERADMIN);
    }

    /**
     * Returns user with a mobile phone.
     *
     * @param phone the mobile phone of the user to return.
     * @return The user with a mobile phone.
     */
    private User getByMobilePhone(final String phone) {
        return this.repository.findByContactsMobilePhone(phone);
    }

    /**
     * Returns user with a landline phone.
     *
     * @param phone the landline phone of the user to return.
     * @return The user with a landline phone.
     */
    private User getByLandlinePhone(final String phone) {
        return this.repository.findByContactsLandlinePhone(phone);
    }

    /**
     * Returns user with a fax number.
     *
     * @param phone the fax number of the user to return.
     * @return The user with a fax number.
     */
    private User getByFax(final String phone) {
        return this.repository.findByContactsFax(phone);
    }

    /**
     * Filters user by the incoming user role.
     * Incoming user must be not null.
     *
     * @param user the user to filter.
     * @param role the role filtering.
     * @return true if user role equals to incoming role,
     * false otherwise.
     */
    private static boolean roleFilter(final User user, final UserRole role) {
        return user.getRole().equals(role);
    }

    /**
     * Checks incoming photos.
     * The new photo must be not equals to the old photo.
     * <pre>
     *     File photo1 = new File();
     *     isNewPhoto(photo1, photo1) = false
     *
     *     File photo2 = new File();
     *     isNewPhoto(photo1, photo2) = false
     *
     *     photo2.setUrl("photo_2);
     *     isNewPhoto(photo1, photo2) = true
     * </pre>
     *
     * @param newPhoto the new photo (newer null).
     * @param oldPhoto the old photo (newer null).
     * @return true if the incoming photos is not equals and
     * new photo URL is not empty.
     */
    private static boolean isNewPhoto(final File newPhoto, final File oldPhoto) {
        return !newPhoto.equals(oldPhoto) && isNotEmpty(newPhoto.getUrl());
    }
}
