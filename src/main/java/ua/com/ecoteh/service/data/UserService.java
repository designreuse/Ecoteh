package ua.com.ecoteh.service.data;

import ua.com.ecoteh.entity.user.User;
import ua.com.ecoteh.entity.user.UserRole;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.List;

/**
 * The interface of the service layer, describes a set of methods
 * for working with objects of the class {@link User}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public interface UserService extends DataService<User>, UserDetailsService {

    /**
     * Initializes, updates and returns userEntity with incoming id.
     *
     * @param url  the URL of a userEntity to update.
     * @param userEntity the userEntity to update.
     * @return The updating userEntity with incoming id.
     */
    User update(String url, User userEntity);

    /**
     * Returns userEntity which the incoming name.
     *
     * @param name the name of a userEntity to return.
     * @return The userEntity which the incoming name.
     */
    User getByName(String name);

    /**
     * Returns userEntity which the incoming url.
     *
     * @param url the URL of a userEntity to return.
     * @return The userEntity which the incoming url.
     */
    User getByUrl(String url);

    /**
     * Returns userEntity which the incoming login.
     *
     * @param login the login of a userEntity to return.
     * @return The userEntity which the incoming login.
     */
    User getByLogin(String login);

    /**
     * Returns userEntity which the incoming E-mail.
     *
     * @param email the E-mail of a userEntity to return.
     * @return The userEntity which the incoming email.
     */
    User getByEmail(String email);

    /**
     * Returns userEntity which the incoming phone.
     *
     * @param phone the phone of a userEntity to return.
     * @return The userEntity which the incoming phone.
     */
    User getByPhone(String phone);

    /**
     * Returns authenticated userEntity.
     *
     * @return The authenticated userEntity.
     */
    User getAuthenticatedUser();

    /**
     * Returns main admin userEntity.
     *
     * @return The main admin userEntity.
     */
    User getMainAdmin();

    /**
     * Returns userEntities with role "ADMIN".
     *
     * @return The all admin.
     */
    Collection<User> getAdmins();

    /**
     * Returns personnel.
     *
     * @return The all personnel.
     */
    Collection<User> getPersonnel();

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
    List<User> sortByName(Collection<User> userEntities, boolean revers);

    /**
     * Sorts and returns userEntities by URL.
     *
     * @param userEntities  the userEntities to sort.
     * @param revers Sort in descending or ascending.
     * @return The sorted list of userEntities.
     */
    List<User> sortByUrl(Collection<User> userEntities, boolean revers);

    /**
     * Sorts and returns userEntities by by role.
     *
     * @param userEntities  the userEntities to sort.
     * @param role   the role filtering.
     * @param revers Sort in descending or ascending.
     * @return The sorted list of userEntities.
     */
    List<User> sortByRole(Collection<User> userEntities, UserRole role, boolean revers);

    /**
     * Sorts and returns userEntities by date.
     *
     * @param revers Sort in descending or ascending.
     * @return The sorted list of articles.
     */
    List<User> getAndSortByName(boolean revers);

    /**
     * Sorts and returns userEntities by URL.
     *
     * @param revers Sort in descending or ascending.
     * @return The sorted list of userEntities.
     */
    List<User> getAndSortByUrl(boolean revers);

    /**
     * Sorts and returns userEntities by role.
     *
     * @param role   the role filtering.
     * @param revers Sort in descending or ascending.
     * @return The sorted list of userEntities.
     */
    List<User> getAndSortByRole(UserRole role, boolean revers);

    /**
     * Filters and returns userEntities by role.
     *
     * @param userEntities the userEntities to filter.
     * @param role  the role filtering.
     * @return The filtered list of userEntities.
     */
    List<User> filterByRole(Collection<User> userEntities, UserRole role);

    /**
     * Filters and returns userEntities by roles.
     *
     * @param userEntities the userEntities to filter.
     * @param roles the roles filtering.
     * @return The filtered list of userEntities.
     */
    List<User> filterByRoles(Collection<User> userEntities, Collection<UserRole> roles);

    /**
     * Filters and returns userEntities by role.
     *
     * @param role the role filtering.
     * @return The filtered list of userEntities.
     */
    List<User> getAndFilterByRole(UserRole role);

    /**
     * Filters and returns userEntities by roles.
     *
     * @param roles the roles filtering.
     * @return The filtered list of userEntities.
     */
    List<User> getAndFilterByRoles(Collection<UserRole> roles);
}
