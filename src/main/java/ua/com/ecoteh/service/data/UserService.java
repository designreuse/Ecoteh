package ua.com.ecoteh.service.data;

import ua.com.ecoteh.entity.User;
import ua.com.ecoteh.enums.UserRole;
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
     * Initializes, updates and returns user with incoming id.
     *
     * @param url  the URL of a user to update.
     * @param user the user to update.
     * @return The updating user with incoming id.
     */
    User update(String url, User user);

    /**
     * Returns user which the incoming name.
     *
     * @param name the name of a user to return.
     * @return The user which the incoming name.
     */
    User getByName(String name);

    /**
     * Returns user which the incoming url.
     *
     * @param url the URL of a user to return.
     * @return The user which the incoming url.
     */
    User getByUrl(String url);

    /**
     * Returns user which the incoming login.
     *
     * @param login the login of a user to return.
     * @return The user which the incoming login.
     */
    User getByLogin(String login);

    /**
     * Returns user which the incoming E-mail.
     *
     * @param email the E-mail of a user to return.
     * @return The user which the incoming email.
     */
    User getByEmail(String email);

    /**
     * Returns user which the incoming phone.
     *
     * @param phone the phone of a user to return.
     * @return The user which the incoming phone.
     */
    User getByPhone(String phone);

    /**
     * Returns authenticated user.
     *
     * @return The authenticated user.
     */
    User getAuthenticatedUser();

    /**
     * Returns main admin user.
     *
     * @return The main admin user.
     */
    User getMainAdmin();

    /**
     * Returns users with role "ADMIN".
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
     * Removes user with the incoming name.
     *
     * @param name the name of a user to remove.
     */
    void removeByName(String name);

    /**
     * Removes user with the incoming url.
     *
     * @param url the URL of a user to remove.
     */
    void removeByUrl(String url);

    /**
     * Sorts and returns users by name.
     *
     * @param users  the users to sort.
     * @param revers Sort in descending or ascending.
     * @return The sorted list of users.
     */
    List<User> sortByName(Collection<User> users, boolean revers);

    /**
     * Sorts and returns users by URL.
     *
     * @param users  the users to sort.
     * @param revers Sort in descending or ascending.
     * @return The sorted list of users.
     */
    List<User> sortByUrl(Collection<User> users, boolean revers);

    /**
     * Sorts and returns users by by role.
     *
     * @param users  the users to sort.
     * @param role   the role filtering.
     * @param revers Sort in descending or ascending.
     * @return The sorted list of users.
     */
    List<User> sortByRole(Collection<User> users, UserRole role, boolean revers);

    /**
     * Sorts and returns users by date.
     *
     * @param revers Sort in descending or ascending.
     * @return The sorted list of articles.
     */
    List<User> getAndSortByName(boolean revers);

    /**
     * Sorts and returns users by URL.
     *
     * @param revers Sort in descending or ascending.
     * @return The sorted list of users.
     */
    List<User> getAndSortByUrl(boolean revers);

    /**
     * Sorts and returns users by role.
     *
     * @param role   the role filtering.
     * @param revers Sort in descending or ascending.
     * @return The sorted list of users.
     */
    List<User> getAndSortByRole(UserRole role, boolean revers);

    /**
     * Filters and returns users by role.
     *
     * @param users the users to filter.
     * @param role  the role filtering.
     * @return The filtered list of users.
     */
    List<User> filterByRole(Collection<User> users, UserRole role);

    /**
     * Filters and returns users by roles.
     *
     * @param users the users to filter.
     * @param roles the roles filtering.
     * @return The filtered list of users.
     */
    List<User> filterByRoles(Collection<User> users, Collection<UserRole> roles);

    /**
     * Filters and returns users by role.
     *
     * @param role the role filtering.
     * @return The filtered list of users.
     */
    List<User> getAndFilterByRole(UserRole role);

    /**
     * Filters and returns users by roles.
     *
     * @param roles the roles filtering.
     * @return The filtered list of users.
     */
    List<User> getAndFilterByRoles(Collection<UserRole> roles);
}
