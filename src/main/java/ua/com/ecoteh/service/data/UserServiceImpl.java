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
     * The message that a incoming user name is null or empty.
     */
    private final static String BLANK_NAME_MESSAGE =
            "Incoming %s name is null or empty!";

    /**
     * The message that a incoming user URL is null or empty.
     */
    private final static String BLANK_URL_MESSAGE =
            "Incoming %s URL is null or empty!";

    /**
     * The message that a incoming user login is null or empty.
     */
    private final static String BLANK_LOGIN_MESSAGE =
            "Incoming %s login is null or empty!";

    /**
     * The message that a incoming user E-mail is null or empty.
     */
    private final static String BLANK_EMAIL_MESSAGE =
            "Incoming %s E-mail is null or empty!";

    /**
     * The message that a incoming user phone is null or empty.
     */
    private final static String BLANK_PHONE_MESSAGE =
            "Incoming %s phone is null or empty!";

    /**
     * The message that a service cannot find user by incoming name.
     */
    private final static String FINDING_BY_NAME_OBJECT_IS_NULL_MESSAGE =
            "Can`t find object of the %s class by incoming name %s!";
    /**
     * The message that a service cannot find user by incoming URL.
     */
    private final static String FINDING_BY_URL_OBJECT_IS_NULL_MESSAGE =
            "Can`t find object of the %s class by incoming URL %s!";
    /**
     * The message that a service cannot find user by incoming login.
     */
    private final static String FINDING_BY_LOGIN_OBJECT_IS_NULL_MESSAGE =
            "Can`t find object of the %s class by incoming login %s!";
    /**
     * The message that a service cannot find user by incoming E-mail.
     */
    private final static String FINDING_BY_EMAIL_OBJECT_IS_NULL_MESSAGE =
            "Can`t find object of the %s class by incoming E-mail %s!";

    /**
     * The message that a service cannot find user by incoming phone.
     */
    private final static String FINDING_BY_PHONE_OBJECT_IS_NULL_MESSAGE =
            "Can`t find object of the %s class by incoming phone %s!";

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
     * @param repository  a implementation of the {@link UserRepository} interface.
     * @param fileService a implementation of the {@link FileService} interface.
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
     *
     * @return The authenticated user.
     */
    @Override
    @Transactional(readOnly = true)
    public User getAuthenticatedUser() {
        User authenticatedUser;
        try {
            authenticatedUser = (User) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
        } catch (ClassCastException ex) {
            LOGGER.error(ex.getMessage(), ex);
            authenticatedUser = null;
        }
        return authenticatedUser;
    }

    /**
     * Locates the user based on the username.
     *
     * @param username The username identifying the user whose data is required.
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
     * Initializes, saves and returns a new user.
     *
     * @param user a user to save.
     * @return The new saving user.
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
     * Initializes, updates and returns user with parameter url.
     *
     * @param url  a URL of the user to update.
     * @param user a user to update.
     * @return The updating user with parameter id.
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
     * Returns user with the parameter name.
     *
     * @param name a name of the user to return.
     * @return The user with the parameter name.
     * @throws IllegalArgumentException Throw exception when parameter name is blank.
     * @throws NullPointerException     Throws exception if user is absent.
     */
    @Override
    @Transactional(readOnly = true)
    public User getByName(final String name) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(name)) {
            throw new IllegalArgumentException(
                    String.format(BLANK_NAME_MESSAGE, getClassSimpleName())
            );
        }
        final User user = this.repository.findByName(name);
        if (isNull(user)) {
            throw new NullPointerException(
                    String.format(
                            FINDING_BY_NAME_OBJECT_IS_NULL_MESSAGE,
                            getClassSimpleName(), name
                    )
            );
        }
        return user;
    }

    /**
     * Returns user with the parameter url.
     *
     * @param url a URL of the user to return.
     * @return The user with the parameter url.
     * @throws IllegalArgumentException Throw exception when parameter url is blank.
     * @throws NullPointerException     Throws exception if user is absent.
     */
    @Override
    @Transactional(readOnly = true)
    public User getByUrl(final String url) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(url)) {
            throw new IllegalArgumentException(
                    String.format(BLANK_URL_MESSAGE, getClassSimpleName())
            );
        }
        final User user = this.repository.findByUrl(url);
        if (isNull(user)) {
            throw new NullPointerException(
                    String.format(
                            FINDING_BY_URL_OBJECT_IS_NULL_MESSAGE,
                            getClassSimpleName(), url
                    )
            );
        }
        return user;
    }

    /**
     * Returns user with the parameter login.
     *
     * @param login a login of the user to return.
     * @return The user with the parameter login.
     * @throws IllegalArgumentException Throw exception when parameter login is blank.
     * @throws NullPointerException     Throws exception if user is absent.
     */
    @Override
    @Transactional(readOnly = true)
    public User getByLogin(final String login) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(login)) {
            throw new IllegalArgumentException(
                    String.format(BLANK_LOGIN_MESSAGE, getClassSimpleName())
            );
        }
        final User user = this.repository.findByEncryptedLogin(
                new Encryptor(login).encrypt()
        );
        if (isNull(user)) {
            throw new NullPointerException(
                    String.format(
                            FINDING_BY_LOGIN_OBJECT_IS_NULL_MESSAGE,
                            getClassSimpleName(), login
                    )
            );
        }
        return user;
    }

    /**
     * Returns user with the parameter e-mail.
     *
     * @param email a e-mail of the user to return.
     * @return The user with the parameter e-mail.
     * @throws IllegalArgumentException Throw exception when parameter email is blank.
     * @throws NullPointerException     Throws exception if user is absent.
     */
    @Override
    @Transactional(readOnly = true)
    public User getByEmail(final String email) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(email)) {
            throw new IllegalArgumentException(
                    String.format(BLANK_EMAIL_MESSAGE, getClassSimpleName())
            );
        }
        User user = this.repository.findByContactsEmail(email);
        if (isNull(user)) {
            throw new NullPointerException(
                    String.format(
                            FINDING_BY_EMAIL_OBJECT_IS_NULL_MESSAGE,
                            getClassSimpleName(), email
                    )
            );
        }
        return user;
    }

    /**
     * Returns user which the parameter phone.
     *
     * @param phone a phone of the user to return.
     * @return The user which the parameter phone.
     * @throws IllegalArgumentException Throw exception when parameter phone is blank.
     * @throws NullPointerException     Throws exception if user is absent.
     */
    @Override
    @Transactional(readOnly = true)
    public User getByPhone(final String phone) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(phone)) {
            throw new IllegalArgumentException(
                    String.format(BLANK_PHONE_MESSAGE, getClassSimpleName())
            );
        }
        User user = getByMobilePhone(phone);
        if (isNull(user)) {
            user = getByLandlinePhone(phone);
            if (isNull(user)) {
                user = getByFax(phone);
                if (isNull(user)) {
                    throw new NullPointerException(
                            String.format(
                                    FINDING_BY_PHONE_OBJECT_IS_NULL_MESSAGE,
                                    getClassSimpleName(), phone
                            )
                    );
                }
            }
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
     * Returns users with role UserRole.ADMIN.
     *
     * @return The users with role UserRole.ADMIN.
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<User> getAdmins() {
        return getAndFilterByRole(UserRole.ADMIN);
    }

    /**
     * Returns personnel.
     *
     * @return The all personnel.
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
     */
    @Override
    @Transactional
    public void removeByName(final String name) {
        if (isNotEmpty(name)) {
            this.repository.deleteByName(name);
        }
    }

    /**
     * Removes user with the parameter url.
     * Removes user if url is not blank.
     *
     * @param url a url of the user to remove.
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
     *
     * @param users  the users to sort.
     * @param revers Sort in descending or ascending.
     * @return The sorted list of users.
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
     * Sorts and returns users by url.
     *
     * @param users  the users to sort.
     * @param revers is sort in descending or ascending.
     * @return The sorted list of users.
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
     *
     * @param users  the users to sort.
     * @param role   a role filtering.
     * @param revers is ort in descending or ascending.
     * @return The sorted list of users.
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
     * Sorts and returns users by date.
     *
     * @param revers is ort in descending or ascending.
     * @return The sorted list of articles.
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getAndSortByName(final boolean revers) {
        return sortByName(getAll(), revers);
    }

    /**
     * Sorts and returns users by url.
     *
     * @param revers is ort in descending or ascending.
     * @return The sorted list of users.
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getAndSortByUrl(final boolean revers) {
        return sortByUrl(getAll(), revers);
    }

    /**
     * Sorts and returns users by role.
     *
     * @param role   a role filtering.
     * @param revers is ort in descending or ascending.
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
     * Filters and returns users by role.
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
     * Filters and returns users by roles.
     * Returns empty list if users is empty.
     * Returns back users if roles is empty.
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
                                    .filter(
                                            role -> user.getRole().equals(role)
                                    ).map(role -> user)
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
     * Filters and returns users by role.
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
     * Returns a list of valid users.
     *
     * @param users the users to filter.
     * @return The list of valid users.
     */
    @Override
    @Transactional
    public List<User> filteredByValid(final Collection<User> users) {
        List<User> result = new ArrayList<>();
        if (isNotEmpty(users)) {
            result.addAll(
                    users.stream()
                            .filter(
                                    UserServiceImpl::isValidUser
                            ).collect(Collectors.toList())
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
     *
     * @param from a copied object
     * @param to   a object to copy
     */
    protected void copy(final User from, final User to) {
        to.initialize(from);
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

    /**
     * Validated a incoming user.
     * User is valid if it is not null and it validated.
     * <pre>
     *     isValidUser(null) = false
     *
     *     User user = new User();
     *     user.setValidated(false);
     *     isValidUser(user) = false
     *
     *     user.setValidated(true);
     *     isValidUser(user) = true
     * </pre>
     *
     * @param user the user to check.
     * @return true if the user is not null and it validated.
     */
    private static boolean isValidUser(final User user) {
        return isNotNull(user) && user.isValidated();
    }
}
