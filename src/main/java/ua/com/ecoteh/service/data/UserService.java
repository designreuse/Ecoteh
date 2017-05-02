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
 * @version 1.0
 */
public interface UserService extends DataService<User>, UserDetailsService {

    /**
     * Initializes, updates and returns user with incoming id.
     *
     * @param url  the URL of a user to update.
     * @param user the user to update.
     * @return The updating user with incoming id.
     */
    User update(
            final String url,
            final User user
    );

    /**
     * Returns user which the incoming name.
     *
     * @param name the name of a user to return.
     * @return The user which the incoming name.
     */
    User getByName(final String name);

    /**
     * Returns user which the incoming url.
     *
     * @param url the URL of a user to return.
     * @return The user which the incoming url.
     */
    User getByUrl(final String url);

    /**
     * Returns user which the incoming login.
     *
     * @param login the login of a user to return.
     * @return The user which the incoming login.
     */
    User getByLogin(final String login);

    /**
     * Returns user which the incoming E-mail.
     *
     * @param email the E-mail of a user to return.
     * @return The user which the incoming email.
     */
    User getByEmail(final String email);

    /**
     * Returns user which the incoming phone.
     *
     * @param phone the phone of a user to return.
     * @return The user which the incoming phone.
     */
    User getByPhone(final String phone);

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
    void removeByName(final String name);

    /**
     * Removes user with the incoming url.
     *
     * @param url the URL of a user to remove.
     */
    void removeByUrl(final String url);

    /**
     * Sorts and returns users by name.
     *
     * @param users  the users to sort.
     * @param revers Sort in descending or ascending.
     * @return The sorted list of users.
     */
    List<User> sortByName(
            final Collection<User> users,
            final boolean revers
    );

    /**
     * Sorts and returns users by URL.
     *
     * @param users  the users to sort.
     * @param revers Sort in descending or ascending.
     * @return The sorted list of users.
     */
    List<User> sortByUrl(
            final Collection<User> users,
            final boolean revers
    );

    /**
     * Sorts and returns users by by role.
     *
     * @param users  the users to sort.
     * @param role   the role filtering.
     * @param revers Sort in descending or ascending.
     * @return The sorted list of users.
     */
    List<User> sortByRole(
            final Collection<User> users,
            final UserRole role,
            final boolean revers
    );

    /**
     * Sorts and returns users by date.
     *
     * @param revers Sort in descending or ascending.
     * @return The sorted list of articles.
     */
    List<User> getAndSortByName(final boolean revers);

    /**
     * Sorts and returns users by URL.
     *
     * @param revers Sort in descending or ascending.
     * @return The sorted list of users.
     */
    List<User> getAndSortByUrl(final boolean revers);

    /**
     * Sorts and returns users by role.
     *
     * @param role   the role filtering.
     * @param revers Sort in descending or ascending.
     * @return The sorted list of users.
     */
    List<User> getAndSortByRole(
            final UserRole role,
            final boolean revers
    );

    /**
     * Filters and returns users by role.
     *
     * @param users the users to filter.
     * @param role  the role filtering.
     * @return The filtered list of users.
     */
    List<User> filterByRole(
            final Collection<User> users,
            final UserRole role
    );

    /**
     * Filters and returns users by roles.
     *
     * @param users the users to filter.
     * @param roles the roles filtering.
     * @return The filtered list of users.
     */
    List<User> filterByRoles(
            final Collection<User> users,
            final Collection<UserRole> roles
    );

    /**
     * Filters and returns users by role.
     *
     * @param role the role filtering.
     * @return The filtered list of users.
     */
    List<User> getAndFilterByRole(final UserRole role);

    /**
     * Filters and returns users by roles.
     *
     * @param roles the roles filtering.
     * @return The filtered list of users.
     */
    List<User> getAndFilterByRoles(final Collection<UserRole> roles);
}
