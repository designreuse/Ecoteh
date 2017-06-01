package ua.com.ecoteh.service.data;

import ua.com.ecoteh.entity.user.UserEntity;
import ua.com.ecoteh.entity.user.UserRole;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.List;

/**
 * The interface of the service layer, describes a set of methods
 * for working with objects of the class {@link UserEntity}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public interface UserService extends DataService<UserEntity>, UserDetailsService {

    /**
     * Initializes, updates and returns userEntity with incoming id.
     *
     * @param url  the URL of a userEntity to update.
     * @param userEntity the userEntity to update.
     * @return The updating userEntity with incoming id.
     */
    UserEntity update(String url, UserEntity userEntity);

    /**
     * Returns userEntity which the incoming name.
     *
     * @param name the name of a userEntity to return.
     * @return The userEntity which the incoming name.
     */
    UserEntity getByName(String name);

    /**
     * Returns userEntity which the incoming url.
     *
     * @param url the URL of a userEntity to return.
     * @return The userEntity which the incoming url.
     */
    UserEntity getByUrl(String url);

    /**
     * Returns userEntity which the incoming login.
     *
     * @param login the login of a userEntity to return.
     * @return The userEntity which the incoming login.
     */
    UserEntity getByLogin(String login);

    /**
     * Returns userEntity which the incoming E-mail.
     *
     * @param email the E-mail of a userEntity to return.
     * @return The userEntity which the incoming email.
     */
    UserEntity getByEmail(String email);

    /**
     * Returns userEntity which the incoming phone.
     *
     * @param phone the phone of a userEntity to return.
     * @return The userEntity which the incoming phone.
     */
    UserEntity getByPhone(String phone);

    /**
     * Returns authenticated userEntity.
     *
     * @return The authenticated userEntity.
     */
    UserEntity getAuthenticatedUser();

    /**
     * Returns main admin userEntity.
     *
     * @return The main admin userEntity.
     */
    UserEntity getMainAdmin();

    /**
     * Returns userEntities with role "ADMIN".
     *
     * @return The all admin.
     */
    Collection<UserEntity> getAdmins();

    /**
     * Returns personnel.
     *
     * @return The all personnel.
     */
    Collection<UserEntity> getPersonnel();

    /**
     * Removes userEntity with the incoming name.
     *
     * @param name the name of a userEntity to remove.
     */
    void removeByName(String name);

    /**
     * Removes userEntity with the incoming url.
     *
     * @param url the URL of a userEntity to remove.
     */
    void removeByUrl(String url);

    /**
     * Sorts and returns userEntities by name.
     *
     * @param userEntities  the userEntities to sort.
     * @param revers Sort in descending or ascending.
     * @return The sorted list of userEntities.
     */
    List<UserEntity> sortByName(Collection<UserEntity> userEntities, boolean revers);

    /**
     * Sorts and returns userEntities by URL.
     *
     * @param userEntities  the userEntities to sort.
     * @param revers Sort in descending or ascending.
     * @return The sorted list of userEntities.
     */
    List<UserEntity> sortByUrl(Collection<UserEntity> userEntities, boolean revers);

    /**
     * Sorts and returns userEntities by by role.
     *
     * @param userEntities  the userEntities to sort.
     * @param role   the role filtering.
     * @param revers Sort in descending or ascending.
     * @return The sorted list of userEntities.
     */
    List<UserEntity> sortByRole(Collection<UserEntity> userEntities, UserRole role, boolean revers);

    /**
     * Sorts and returns userEntities by date.
     *
     * @param revers Sort in descending or ascending.
     * @return The sorted list of articles.
     */
    List<UserEntity> getAndSortByName(boolean revers);

    /**
     * Sorts and returns userEntities by URL.
     *
     * @param revers Sort in descending or ascending.
     * @return The sorted list of userEntities.
     */
    List<UserEntity> getAndSortByUrl(boolean revers);

    /**
     * Sorts and returns userEntities by role.
     *
     * @param role   the role filtering.
     * @param revers Sort in descending or ascending.
     * @return The sorted list of userEntities.
     */
    List<UserEntity> getAndSortByRole(UserRole role, boolean revers);

    /**
     * Filters and returns userEntities by role.
     *
     * @param userEntities the userEntities to filter.
     * @param role  the role filtering.
     * @return The filtered list of userEntities.
     */
    List<UserEntity> filterByRole(Collection<UserEntity> userEntities, UserRole role);

    /**
     * Filters and returns userEntities by roles.
     *
     * @param userEntities the userEntities to filter.
     * @param roles the roles filtering.
     * @return The filtered list of userEntities.
     */
    List<UserEntity> filterByRoles(Collection<UserEntity> userEntities, Collection<UserRole> roles);

    /**
     * Filters and returns userEntities by role.
     *
     * @param role the role filtering.
     * @return The filtered list of userEntities.
     */
    List<UserEntity> getAndFilterByRole(UserRole role);

    /**
     * Filters and returns userEntities by roles.
     *
     * @param roles the roles filtering.
     * @return The filtered list of userEntities.
     */
    List<UserEntity> getAndFilterByRoles(Collection<UserRole> roles);
}
