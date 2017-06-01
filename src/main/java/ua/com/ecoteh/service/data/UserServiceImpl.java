package ua.com.ecoteh.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.ecoteh.config.DefaultAccounts;
import ua.com.ecoteh.entity.file.FileEntity;
import ua.com.ecoteh.entity.user.UserEntity;
import ua.com.ecoteh.entity.user.UserRole;
import ua.com.ecoteh.exception.ExceptionMessage;
import ua.com.ecoteh.repository.UserRepository;
import ua.com.ecoteh.util.comparator.UserComparator;
import ua.com.ecoteh.util.encryption.Base64Encryptor;

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
 */
@Service
@ComponentScan(
        basePackages = {
                "ua.com.ecoteh.repository",
                "ua.com.ecoteh.service.data"
        }
)
public final class UserServiceImpl extends DataServiceImpl<UserEntity>
        implements UserService, UserDetailsService {

    /**
     * The interface provides a set of standard methods for working
     * {@link UserEntity} objects with the database.
     */
    private final UserRepository repository;

    /**
     * The interface of the service layer, describes a set of methods
     * for working with objects of the {@link FileEntity} class.
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
     * Returns authenticated userEntity.
     * If authenticated userEntity is empty then returns null.
     *
     * @return The authenticated userEntity or null.
     */
    @Override
    @Transactional(readOnly = true)
    public UserEntity getAuthenticatedUser() {
        UserEntity userEntity;
        try {
            userEntity = (UserEntity) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
        } catch (Exception ex) {
            userEntity = null;
        }
        return userEntity;
    }

    /**
     * Locates the userEntity based on the username.
     * If can`t find userEntity by incoming username then throws UsernameNotFoundException.
     *
     * @param username the username identifying the userEntity whose data is required.
     * @return A fully populated userEntity record (never null).
     * @throws UsernameNotFoundException if the userEntity could not be found
     *                                   or the userEntity has no GrantedAuthority.
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        UserEntity userEntity;
        try {
            userEntity = DefaultAccounts.get(username);
            if (isNull(userEntity)) {
                userEntity = getByLogin(username);
            }
        } catch (NullPointerException ex) {
            throw new UsernameNotFoundException(ex.getMessage(), ex);
        }
        return userEntity;
    }

    /**
     * Saves and returns a new userEntity.
     *
     * @param userEntity a userEntity to save.
     * @return The new saving userEntity (never null).
     */
    @Override
    @Transactional
    public UserEntity add(final UserEntity userEntity) {
        if (isNotNull(userEntity)) {
            userEntity.setRole(UserRole.ADMIN);
        }
        return super.add(userEntity);
    }

    /**
     * Updates and returns userEntity with incoming URL.
     *
     * @param url  the URL of a userEntity to update.
     * @param userEntity the userEntity to update.
     * @return The updating userEntity with incoming URL (never null).
     */
    @Override
    @Transactional
    public UserEntity update(
            final String url,
            final UserEntity userEntity
    ) {
        final UserEntity userEntityToUpdate = getByUrl(url);
        final FileEntity newPhoto = userEntity.getPhotoEntity();
        final FileEntity oldPhoto = userEntityToUpdate.getPhotoEntity();
        if (isNewPhoto(newPhoto, oldPhoto)) {
            this.fileService.deleteFile(oldPhoto.getUrl());
        }
        copy(userEntity, userEntityToUpdate);
        return update(userEntityToUpdate);
    }

    /**
     * Returns userEntity with the incoming name.
     * If a incoming name is null or empty then throws IllegalArgumentException.
     * If can`t find userEntity by incoming name then throws NullPointerException.
     *
     * @param name the name of a userEntity to return.
     * @return The userEntity with the incoming name (never null).
     * @throws IllegalArgumentException Throw exception when parameter name is blank.
     * @throws NullPointerException     Throws exception if userEntity is absent.
     */
    @Override
    @Transactional(readOnly = true)
    public UserEntity getByName(final String name) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(name)) {
            throw getIllegalArgumentException(
                    ExceptionMessage.BLANK_NAME_MESSAGE,
                    getClassSimpleName()
            );
        }
        final UserEntity userEntity = this.repository.findByName(name);
        if (isNull(userEntity)) {
            throw getNullPointerException(
                    ExceptionMessage.FINDING_BY_NAME_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName(), name
            );
        }
        return userEntity;
    }

    /**
     * Returns userEntity with the incoming URL.
     * If a incoming URL is null or empty then throws IllegalArgumentException.
     * If can`t find userEntity by incoming URL then throws NullPointerException.
     *
     * @param url the URL of a userEntity to return.
     * @return The userEntity with the incoming URL (never null).
     * @throws IllegalArgumentException Throw exception when parameter URL is blank.
     * @throws NullPointerException     Throws exception if userEntity is absent.
     */
    @Override
    @Transactional(readOnly = true)
    public UserEntity getByUrl(final String url) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(url)) {
            throw getIllegalArgumentException(
                    ExceptionMessage.BLANK_URL_MESSAGE,
                    getClassSimpleName()
            );
        }
        final UserEntity userEntity = this.repository.findByUrl(url);
        if (isNull(userEntity)) {
            throw getNullPointerException(
                    ExceptionMessage.FINDING_BY_URL_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName(), url
            );
        }
        return userEntity;
    }

    /**
     * Returns userEntity with the incoming login.
     * If a incoming login is null or empty then throws IllegalArgumentException.
     * If can`t find userEntity by incoming login then throws NullPointerException.
     *
     * @param login the login of a userEntity to return.
     * @return The userEntity with the incoming login (never null).
     * @throws IllegalArgumentException Throw exception when parameter login is blank.
     * @throws NullPointerException     Throws exception if userEntity is absent.
     */
    @Override
    @Transactional(readOnly = true)
    public UserEntity getByLogin(final String login) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(login)) {
            throw getIllegalArgumentException(
                    ExceptionMessage.BLANK_LOGIN_MESSAGE,
                    getClassSimpleName()
            );
        }
        final UserEntity userEntity = this.repository.findByEncryptedLogin(
                new Base64Encryptor(login).encrypt()
        );
        if (isNull(userEntity)) {
            throw getNullPointerException(
                    ExceptionMessage.FINDING_BY_LOGIN_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName(), login
            );
        }
        return userEntity;
    }

    /**
     * Returns userEntity with the incoming E-mail.
     * If a incoming E-mail is null or empty then throws IllegalArgumentException.
     * If can`t find userEntity by incoming E-mail then throws NullPointerException.
     *
     * @param email the E-mail of a userEntity to return.
     * @return The userEntity with the incoming E-mail (never null).
     * @throws IllegalArgumentException Throw exception when parameter E-mail is blank.
     * @throws NullPointerException     Throws exception if userEntity is absent.
     */
    @Override
    @Transactional(readOnly = true)
    public UserEntity getByEmail(final String email) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(email)) {
            throw getIllegalArgumentException(
                    ExceptionMessage.BLANK_EMAIL_MESSAGE,
                    getClassSimpleName()
            );
        }
        UserEntity userEntity = this.repository.findByContactsEntityEmail(email);
        if (isNull(userEntity)) {
            throw getNullPointerException(
                    ExceptionMessage.FINDING_BY_EMAIL_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName(), email
            );
        }
        return userEntity;
    }

    /**
     * Returns userEntity with the incoming phone.
     * If a incoming phone is null or empty then throws IllegalArgumentException.
     * If can`t find userEntity by incoming phone then throws NullPointerException.
     *
     * @param phone the phone of a userEntity to return.
     * @return The userEntity with the incoming phone (never null).
     * @throws IllegalArgumentException Throw exception when parameter phone is blank.
     * @throws NullPointerException     Throws exception if userEntity is absent.
     */
    @Override
    @Transactional(readOnly = true)
    public UserEntity getByPhone(final String phone) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(phone)) {
            throw getIllegalArgumentException(
                    ExceptionMessage.BLANK_PHONE_MESSAGE,
                    getClassSimpleName()
            );
        }
        UserEntity userEntity = getByMobilePhone(phone);
        if (isNull(userEntity)) {
            userEntity = getByLandlinePhone(phone);
            if (isNull(userEntity)) {
                userEntity = getByFax(phone);
                if (isNull(userEntity)) {
                    throw getNullPointerException(
                            ExceptionMessage.FINDING_BY_PHONE_OBJECT_IS_NULL_MESSAGE,
                            getClassSimpleName(), phone
                    );
                }
            }
        }
        return userEntity;
    }

    /**
     * Returns main admin userEntity.
     *
     * @return The main admin (never null).
     */
    @Override
    @Transactional(readOnly = true)
    public UserEntity getMainAdmin() {
        return get(1L);
    }

    /**
     * Returns userEntities with role UserRole.ADMIN.
     *
     * @return The userEntities with role UserRole.ADMIN (never null).
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<UserEntity> getAdmins() {
        return getAndFilterByRole(UserRole.ADMIN);
    }

    /**
     * Returns personnel.
     *
     * @return The all personnel (never null).
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<UserEntity> getPersonnel() {
        return getAdmins();
    }

    /**
     * Removes userEntity with the parameter name.
     * Removes userEntity if name is not null and not empty.
     *
     * @param name the name of a userEntity to remove.
     */
    @Override
    @Transactional
    public void removeByName(final String name) {
        if (isNotEmpty(name)) {
            this.repository.deleteByName(name);
        }
    }

    /**
     * Removes userEntity with the incoming URL.
     * Removes userEntity if URL is not null and not empty.
     *
     * @param url the URL of a userEntity to remove.
     */
    @Override
    @Transactional
    public void removeByUrl(final String url) {
        if (isNotEmpty(url)) {
            this.repository.deleteByUrl(url);
        }
    }

    /**
     * Sorts and returns userEntities by name.
     * For sorting used {@link UserComparator.ByName} comparator.
     *
     * @param userEntities  the userEntities to sort.
     * @param revers Sort in descending or ascending.
     * @return The sorted list of userEntities or empty list (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> sortByName(
            final Collection<UserEntity> userEntities,
            final boolean revers
    ) {
        return sort(userEntities, new UserComparator.ByName(), revers);
    }

    /**
     * Sorts and returns userEntities by URL.
     * For sorting used {@link UserComparator.ByUrl} comparator.
     *
     * @param userEntities  the userEntities to sort.
     * @param revers is sort in descending or ascending.
     * @return The sorted list of userEntities or empty list (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> sortByUrl(
            final Collection<UserEntity> userEntities,
            final boolean revers
    ) {
        return sort(userEntities, new UserComparator.ByUrl(), revers);
    }

    /**
     * Sorts and returns userEntities by role.
     * For sorting used {@link UserComparator.ByRole} comparator.
     *
     * @param userEntities  the userEntities to sort.
     * @param role   the role filtering.
     * @param revers is sort in descending or ascending.
     * @return The sorted list of userEntities or empty list (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> sortByRole(
            final Collection<UserEntity> userEntities,
            final UserRole role,
            final boolean revers
    ) {
        return sort(userEntities, new UserComparator.ByRole(role), revers);
    }

    /**
     * Sorts and returns all userEntities by name.
     * For sorting used {@link UserComparator.ByName} comparator.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of articles.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> getAndSortByName(final boolean revers) {
        return sortByName(getAll(), revers);
    }

    /**
     * Sorts and returns all userEntities by URL.
     * For sorting used {@link UserComparator.ByUrl} comparator.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of userEntities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> getAndSortByUrl(final boolean revers) {
        return sortByUrl(getAll(), revers);
    }

    /**
     * Sorts and returns all userEntities by role.
     * For sorting used {@link UserComparator.ByRole} comparator.
     *
     * @param role   a role filtering.
     * @param revers is sort in descending or ascending.
     * @return The sorted list of userEntities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> getAndSortByRole(
            final UserRole role,
            final boolean revers
    ) {
        return sortByRole(getAll(), role, revers);
    }

    /**
     * Filters and returns userEntities by the incoming role.
     * <pre>
     *     filterByRole(null, null) = empty ArrayList()
     *     filterByRole(null, UserRole.ADMIN) = empty ArrayList()
     *
     *     Collection userEntities = new ArrayList();
     *     userEntities.add(new UserEntity());
     *     filterByCategory(userEntities, UserRole.ADMIN) = filtered list of userEntities
     * </pre>
     *
     * @param userEntities the userEntities to filter.
     * @param role  a role filtering.
     * @return The filtered list of userEntities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> filterByRole(
            final Collection<UserEntity> userEntities,
            final UserRole role
    ) {
        List<UserRole> roles = null;
        if (isNotNull(role)) {
            roles = new ArrayList<>(1);
            roles.add(role);
        }
        return filterByRoles(userEntities, roles);
    }

    /**
     * Filters and returns userEntities by incoming roles.
     * Returns empty list if userEntities is empty.
     * Returns back userEntities if roles is empty.
     * <pre>
     *     filterByRoles(null, ..) = empty ArrayList()
     *     filterByRoles(new ArrayList(), ..) = empty ArrayList()
     *
     *     Collection roles = new ArrayList();
     *     roles.add(UserRole.ADMIN);
     *     filterByCategories(null, roles) = empty ArrayList()
     *
     *     Collection userEntities = new ArrayList();
     *     userEntities.add(new UserEntity());
     *     filterByCategories(userEntities, null) = userEntities
     *
     *     filterByCategories(userEntities, roles) = filtered list of userEntities
     * </pre>
     *
     * @param userEntities the userEntities to filter.
     * @param roles a roles filtering.
     * @return The filtered list of userEntities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> filterByRoles(
            final Collection<UserEntity> userEntities,
            final Collection<UserRole> roles
    ) {
        final List<UserEntity> result = new ArrayList<>();
        if (isNotEmpty(userEntities)) {
            if (isNotEmpty(roles)) {
                for (UserEntity userEntity : userEntities) {
                    result.addAll(
                            roles.stream()
                                    .filter(role -> roleFilter(userEntity, role))
                                    .map(role -> userEntity)
                                    .collect(Collectors.toList())
                    );
                }
            } else {
                result.addAll(userEntities);
            }
        }
        return result;
    }

    /**
     * Filters and returns userEntities by incoming role.
     * <pre>
     *     getAndFilterByRole(null) = all userEntities
     *     getAndFilterByRole(UserRole.ADMIN) = filtered list of userEntities
     * </pre>
     *
     * @param role a role filtering.
     * @return The filtered list of userEntities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> getAndFilterByRole(final UserRole role) {
        return filterByRole(getAll(false), role);
    }

    /**
     * Filters and returns userEntities by roles.
     * <pre>
     *     getAndFilterByRoles(null) = all userEntities
     *     getAndFilterByRoles(new ArrayList()) = all userEntities
     *
     *     Collection roles = new ArrayList();
     *     roles.add(UserRole.ADMIN);
     *     getAndFilterByRoles(roles) = filtered list of userEntities
     * </pre>
     *
     * @param roles a roles filtering.
     * @return The filtered list of userEntities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> getAndFilterByRoles(final Collection<UserRole> roles) {
        return filterByRoles(getAll(), roles);
    }

    /**
     * Returns a list valid userEntities.
     * Returns empty list if userEntities is empty.
     * <pre>
     *     filteredByValid(null) = empty ArrayList()
     *     filteredByValid(new ArrayList()) = empty ArrayList()
     *
     *     Collection userEntities = new ArrayList();
     *     UserEntity userEntity = new UserEntity();
     *     userEntity.setValidated(false);
     *     userEntities.add(userEntity);
     *     filteredByValid(userEntities) = empty ArrayList()
     *
     *     userEntity.setValidated(true);
     *     filteredByValid(userEntities) = filtered list of userEntities
     * </pre>
     *
     * @param userEntities the userEntities to filter.
     * @return The filtered list of userEntities or empty list (newer null).
     */
    @Override
    @Transactional
    public List<UserEntity> filteredByValid(final Collection<UserEntity> userEntities) {
        List<UserEntity> result = new ArrayList<>();
        if (isNotEmpty(userEntities)) {
            result.addAll(
                    userEntities.stream()
                            .filter(UserServiceImpl::isValidated)
                            .collect(Collectors.toList())
            );
        }
        return result;
    }

    /**
     * Validates input userEntity.
     *
     * @param userEntity      the userEntity to valid.
     * @param exist     is validate input object by exists.
     * @param duplicate is validate input object by duplicate.
     * @return Returns true if userEntity is valid, false otherwise.
     */
    @Override
    protected boolean validated(
            final UserEntity userEntity,
            final boolean exist,
            final boolean duplicate
    ) {
        if (isNull(userEntity)) {
            return false;
        }
        if (exist && !exists(userEntity)) {
            return false;
        }
        if (duplicate) {
            if (isNotNull(this.repository.findByName(userEntity.getName())) ||
                    isNotNull(this.repository.findByUrl(userEntity.getUrl()))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return Class object of {@link UserEntity} class.
     *
     * @return The Class object of {@link UserEntity} class.
     */
    @Override
    protected Class<UserEntity> getModelClass() {
        return UserEntity.class;
    }

    /**
     * Copies the object "from" to object "to".
     * Incoming objects must be not null.
     *
     * @param from the copied object
     * @param to   the object to copy
     */
    protected void copy(final UserEntity from, final UserEntity to) {
        if (!isAuthenticatedUser(to)) {
            from.setLogin(to.getLogin());
            from.setPassword(to.getPassword());
        }
        to.initialize(from);
    }

    /**
     * Checks an authenticated userEntity.
     * <pre>
     *     If authenticated userEntity is null:
     *     isAuthenticatedUser(null) = false
     *     isAuthenticatedUser(userEntity) = false
     *
     *     If the incoming userEntity is authenticated userEntity:
     *     isAuthenticatedUser(userEntity) = true
     *
     *     If the incoming userEntity is not authenticated userEntity,
     *     but the authenticated userEntity has super admin role:
     *     isAuthenticatedUser(userEntity) = true
     * </pre>
     *
     * @param userEntity the userEntity to check.
     * @return true if the userEntity is authenticated userEntity or
     * if authenticated userEntity has super admin role.
     */
    private boolean isAuthenticatedUser(final UserEntity userEntity) {
        boolean result = false;
        if (isNotNull(userEntity)) {
            final UserEntity authenticatedUserEntity = getAuthenticatedUser();
            if (isNotNull(authenticatedUserEntity)) {
                result = userEntity.equals(authenticatedUserEntity) ||
                        authenticatedUserEntity.getRole().equals(UserRole.SUPERADMIN);
            }
        }
        return result;
    }

    /**
     * Returns userEntity with a mobile phone.
     *
     * @param phone the mobile phone of the userEntity to return.
     * @return The userEntity with a mobile phone.
     */
    private UserEntity getByMobilePhone(final String phone) {
        return this.repository.findByContactsEntityMobilePhone(phone);
    }

    /**
     * Returns userEntity with a landline phone.
     *
     * @param phone the landline phone of the userEntity to return.
     * @return The userEntity with a landline phone.
     */
    private UserEntity getByLandlinePhone(final String phone) {
        return this.repository.findByContactsEntityLandlinePhone(phone);
    }

    /**
     * Returns userEntity with a fax number.
     *
     * @param phone the fax number of the userEntity to return.
     * @return The userEntity with a fax number.
     */
    private UserEntity getByFax(final String phone) {
        return this.repository.findByContactsEntityFax(phone);
    }

    /**
     * Filters userEntity by the incoming userEntity role.
     * Incoming userEntity must be not null.
     *
     * @param userEntity the userEntity to filter.
     * @param role the role filtering.
     * @return true if userEntity role equals to incoming role,
     * false otherwise.
     */
    private boolean roleFilter(final UserEntity userEntity, final UserRole role) {
        return userEntity.getRole().equals(role);
    }

    /**
     * Checks incoming photos.
     * The new photo must be not equals to the old photo.
     * <pre>
     *     FileEntity photo1 = new FileEntity();
     *     isNewPhoto(photo1, photo1) = false
     *
     *     FileEntity photo2 = new FileEntity();
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
    private boolean isNewPhoto(final FileEntity newPhoto, final FileEntity oldPhoto) {
        return !newPhoto.equals(oldPhoto) && isNotEmpty(newPhoto.getUrl());
    }
}
