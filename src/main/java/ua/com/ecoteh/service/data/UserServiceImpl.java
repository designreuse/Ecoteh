package ua.com.ecoteh.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.ecoteh.config.DefaultAccounts;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.file.FileEditor;
import ua.com.ecoteh.entity.file.FileEntity;
import ua.com.ecoteh.entity.user.User;
import ua.com.ecoteh.entity.user.UserEditor;
import ua.com.ecoteh.entity.user.UserEntity;
import ua.com.ecoteh.entity.user.UserRole;
import ua.com.ecoteh.exception.ExceptionMessage;
import ua.com.ecoteh.repository.UserRepository;
import ua.com.ecoteh.service.data.comparator.UserComparator;
import ua.com.ecoteh.util.encryption.Base64Encryptor;
import ua.com.ecoteh.util.encryption.Encryptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ua.com.ecoteh.util.validator.ObjectValidator.*;

/**
 * The class of the service layer, implements a set of methods for working
 * with objects of the {@link UserEntity} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see User
 * @see UserEntity
 */
@Service
@ComponentScan(
        basePackages = {
                "ua.com.ecoteh.repository",
                "ua.com.ecoteh.service.data"
        }
)
public final class UserServiceImpl extends DataServiceImpl<User, UserEntity>
        implements UserService, UserDetailsService {

    /**
     * The interface provides a set of standard methods for working
     * {@link UserEntity} objects with the database.
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
     * Saves and returns object of User class.
     *
     * @param user the user to add.
     * @return The saving user (newer null).
     * @throws IllegalArgumentException Throw exception when incoming user is null.
     * @throws NullPointerException     Throw exception when saving userEntity is null.
     */
    @Override
    @Transactional
    public User add(final User user) throws IllegalArgumentException, NullPointerException {
        if (isNull(user)) {
            throw getIllegalArgumentException(
                    ExceptionMessage.INCOMING_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName()
            );
        }
        final File file = user.getPhoto();
        final String path = this.fileService.saveFile(file.getMultipartFile());
        final UserEntity userEntity = convertToEntity(user);
        final FileEntity fileEntity = userEntity.getPhotoEntity();
        fileEntity.setUrl(path);
        final UserEntity savingEntity = this.repository.save(userEntity);
        if (isNull(savingEntity)) {
            throw getNullPointerException(
                    ExceptionMessage.SAVING_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName()
            );
        }
        return convertToModel(savingEntity);
    }

    /**
     * Updates and returns user with incoming URL.
     *
     * @param user the URL of a user to update.
     * @return The updating user with incoming URL (never null).
     * @throws IllegalArgumentException Throw exception when parameter name is blank.
     */
    @Override
    @Transactional
    public User update(final User user) throws IllegalArgumentException {
        if (isNull(user)) {
            throw getIllegalArgumentException(
                    ExceptionMessage.INCOMING_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName()
            );
        }
        final User old = getByUrl(user.getUrl());
        final UserEntity userEntity = updateUser(user, old);
        final UserEntity savingEntity = this.repository.save(userEntity);
        if (isNull(savingEntity)) {
            throw getNullPointerException(
                    ExceptionMessage.SAVING_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName()
            );
        }
        return convertToModel(savingEntity);
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
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            user = (User) authentication.getPrincipal();
        } catch (Exception ex) {
            user = null;
        }
        return user;
    }

    /**
     * Checks an authenticated user.
     * <pre>
     *     If authenticated user is null:
     *     isAuthenticatedUser(null) = false
     *     isAuthenticatedUser(user) = false
     *
     *     If the incoming user is authenticated user:
     *     isAuthenticatedUser(user) = true
     *
     *     If the incoming user is not authenticated user,
     *     but the authenticated user has super admin role:
     *     isAuthenticatedUser(user) = true
     * </pre>
     *
     * @param user the user to check.
     * @return true if the user is authenticated user or
     * if authenticated user has super admin role.
     */
    @Override
    public boolean isAuthenticatedUser(final User user) {
        boolean result = false;
        if (isNotNull(user)) {
            final User authenticatedUser = getAuthenticatedUser();
            if (isNotNull(authenticatedUser)) {
                result = user.equals(authenticatedUser) ||
                        authenticatedUser.getRole().equals(UserRole.SUPERADMIN);
            }
        }
        return result;
    }

    /**
     * Checks an authenticated user.
     *
     * @return true if the user is authenticated user or
     * if authenticated user has super admin role.
     */
    @Override
    public boolean isAuthenticatedUser() {
        final User user = getAuthenticatedUser();
        return isNotNull(user);
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
            user = DefaultAccounts.get(username);
            if (isNull(user)) {
                user = getByLogin(username);
            }
        } catch (NullPointerException ex) {
            throw new UsernameNotFoundException(ex.getMessage(), ex);
        }
        return user;
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
        final UserEntity user = this.repository.findByName(name);
        if (isNull(user)) {
            throw getNullPointerException(
                    ExceptionMessage.FINDING_BY_NAME_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName(), name
            );
        }
        return convertToModel(user);
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
        final UserEntity user = this.repository.findByUrl(url);
        if (isNull(user)) {
            throw getNullPointerException(
                    ExceptionMessage.FINDING_BY_URL_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName(), url
            );
        }
        return convertToModel(user);
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
        final Encryptor encryptor = new Base64Encryptor();
        final String encryptedLogin = encryptor.encrypt(login);
        final UserEntity entity = this.repository.findByLogin(encryptedLogin);
        if (isNull(entity)) {
            throw getNullPointerException(
                    ExceptionMessage.FINDING_BY_LOGIN_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName(), login
            );
        }
        return convertToModel(entity);
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
        final UserEntity user = this.repository.findByContactsEmail(email);
        if (isNull(user)) {
            throw getNullPointerException(
                    ExceptionMessage.FINDING_BY_EMAIL_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName(), email
            );
        }
        return convertToModel(user);
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
        UserEntity user = getByMobilePhone(phone);
        if (isNull(user)) {
            user = getByLandlinesPhone(phone);
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
        return convertToModel(user);
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
            final User user = getByName(name);
            remove(user);
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
            final User user = getByUrl(url);
            remove(user);
        }
    }

    /**
     * Removes object of {@link User} class.
     * Removes model if it is not null.
     *
     * @param user the user to remove.
     */
    @Override
    @Transactional
    public void remove(final User user) {
        if (isNotNull(user)) {
            final File photo = user.getPhoto();
            this.fileService.deleteFile(photo.getUrl());
            super.remove(user);
        }
    }

    /**
     * Removes objects of {@link User} class .
     * Removes users if are not null.
     *
     * @param users the users to remove.
     */
    @Override
    @Transactional
    public void remove(final Collection<User> users) {
        if (isNotEmpty(users)) {
            users.forEach(this::remove);
        }
    }

    /**
     * Removes all objects of {@link User} class.
     */
    @Override
    @Transactional
    public void removeAll() {
        final Collection<User> users = getAll(false);
        users.forEach(this::remove);
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
    public List<User> sortByName(final Collection<User> users, final boolean revers) {
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
    public List<User> sortByUrl(final Collection<User> users, final boolean revers) {
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
    public List<User> sortByRole(final Collection<User> users, final UserRole role, final boolean revers) {
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
     * @see UserComparator.ByUrl
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
     * @see UserComparator.ByRole
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getAndSortByRole(final UserRole role, final boolean revers) {
        return sortByRole(getAll(), role, revers);
    }

    /**
     * Filters and returns users by the incoming role.
     * <pre>
     *     filterByRole(null, null) = empty ArrayList()
     *     filterByRole(null, UserRole.ADMIN) = empty ArrayList()
     *
     *     if the incoming collections is not empty
     *     filterByCategory(users, UserRole.ADMIN) = filtered list of users
     * </pre>
     *
     * @param users the users to filter.
     * @param role  a role filtering.
     * @return The filtered list of users.
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> filterByRole(final Collection<User> users, final UserRole role) {
        final List<UserRole> roles = new ArrayList<>(1);
        roles.add(role);
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
     *     if the incoming users collections is not empty
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
    public List<User> filterByRoles(final Collection<User> users, final Collection<UserRole> roles) {
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
     *     if the incoming collections has not validated users
     *     filteredByValid(users) = empty ArrayList()
     *
     *     if the incoming collections has validated users
     *     filteredByValid(users) = filtered list of users
     * </pre>
     *
     * @param users the users to filter.
     * @return The filtered list of users or empty list (newer null).
     */
    @Override
    @Transactional
    public List<User> filterByValid(final Collection<User> users) {
        final List<User> result = new ArrayList<>();
        if (isNotEmpty(users)) {
            result.addAll(
                    users.stream()
                            .filter(this::isValidated)
                            .collect(Collectors.toList())
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
    Class<User> getModelClass() {
        return User.class;
    }

    /**
     * Returns user with a mobile phone.
     *
     * @param phone the mobile phone of the user to return.
     * @return The user with a mobile phone.
     */
    private UserEntity getByMobilePhone(final String phone) {
        return this.repository.findByContactsMobilePhone(phone);
    }

    /**
     * Returns user with a landlines phone.
     *
     * @param phone the landlines phone of the user to return.
     * @return The user with a landlines phone.
     */
    private UserEntity getByLandlinesPhone(final String phone) {
        return this.repository.findByContactsLandlinesPhone(phone);
    }

    /**
     * Returns user with a fax number.
     *
     * @param phone the fax number of the user to return.
     * @return The user with a fax number.
     */
    private UserEntity getByFax(final String phone) {
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
    private boolean roleFilter(final User user, final UserRole role) {
        return user.getRole().equals(role);
    }

    /**
     * Updates a users.
     *
     * @param from the user to copy.
     * @param to   the user to update.
     * @return the updated user entity.
     */
    private UserEntity updateUser(final User from, final User to) {
        final UserEditor editor = to.getEditor();
        editor.copy(from);
        final File newPhoto = from.getPhoto();
        if (isNotEmpty(newPhoto.getMultipartFile())) {
            final File oldPhoto = to.getPhoto();
            final File updatedPhoto = updateLogo(oldPhoto, newPhoto);
            editor.addPhoto(updatedPhoto);
        } else {
            editor.addPhoto(to.getPhoto());
        }
        final User updatedUser = editor.update();
        return convertToEntity(updatedUser);
    }

    /**
     * Updated a photos.
     *
     * @param from the photo to copy.
     * @param to   the photo to update.
     * @return the updated photo.
     */
    private File updateLogo(final File from, final File to) {
        this.fileService.deleteFile(from.getUrl());
        final String url = this.fileService.saveFile(to.getMultipartFile());
        final FileEditor fileEditor = from.getEditor();
        fileEditor.addUrl(url);
        return fileEditor.update();
    }
}
