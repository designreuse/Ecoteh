package com.salimov.yurii.service.data.interfaces;

import com.salimov.yurii.entity.User;
import com.salimov.yurii.enums.UserRole;

import java.util.Collection;
import java.util.List;

/**
 * The interface of the service layer, describes a set of methods
 * for working with objects of the class {@link User}.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see User
 * @see DataService
 */
public interface UserService
        extends DataService<User, Long> {

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
     * @param photoUrl    a photo URL to the new user.
     * @param isValid     a validated of the new user.
     * @param isMailing   a permit to send a letters on the user email.
     * @param isLocked    locked the user or not.
     * @return The new saving user.
     * @see User
     */
    User initAndAdd(
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
    );

    /**
     * Initializes, updates and returns user with parameter id.
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
     * @param photoUrl    a photo URL to the user.
     * @param isValid     a validated of the user.
     * @param isMailing   a permit to send a letters on the user email.
     * @param isLocked    locked the user or not.
     * @return The updating user with parameter id.
     * @see User
     */
    User initAndUpdate(
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
    );

    /**
     * Returns user which the parameter name.
     *
     * @param name a name of the user to return.
     * @return The user which the parameter name.
     * @see User
     */
    User getByName(final String name);

    /**
     * Returns user which the parameter url.
     *
     * @param url a url of the user to return.
     * @return The user which the parameter url.
     * @see User
     */
    User getByUrl(final String url);

    /**
     * Returns user which the parameter login.
     *
     * @param login a login of the user to return.
     * @return The user which the parameter login.
     * @see User
     */
    User getByLogin(final String login);

    /**
     * Returns user which the parameter phone.
     *
     * @param phone a phone of the user to return.
     * @return The user which the parameter phone.
     * @see User
     */
    User getByPhone(final String phone);

    /**
     * Returns user which the parameter email.
     *
     * @param email a email of the user to return.
     * @return The user which the parameter email.
     * @see User
     */
    User getByEmail(final String email);

    /**
     * Returns authenticated user.
     *
     * @return The authenticated user.
     * @see User
     */
    User getAuthenticatedUser();

    /**
     * Returns main admin user.
     *
     * @return The main admin user.
     * @see User
     */
    User getMainAdmin();

    /**
     * Returns users with role "ADMIN".
     *
     * @return The all admin.
     * @see User
     */
    Collection<User> getAdmins();

    /**
     * Returns personnel.
     *
     * @return The all personnel.
     * @see User
     */
    Collection<User> getPersonnel();

    /**
     * Removes user with the parameter name.
     *
     * @param name a name of the user to remove.
     * @see User
     */
    void removeByName(final String name);

    /**
     * Removes user with the parameter url.
     *
     * @param url a url of the user to remove.
     * @see User
     */
    void removeByUrl(final String url);

    /**
     * Removes user with the parameter login.
     *
     * @param login a login of the user to remove.
     * @see User
     */
    void removeByLogin(final String login);

    /**
     * Sorts and returns users by name.
     *
     * @param users  the users to sort.
     * @param revers Sort in descending or ascending.
     * @return The sorted list of users.
     * @see User
     */
    List<User> sortByName(
            final Collection<User> users,
            final boolean revers
    );

    /**
     * Sorts and returns users by url.
     *
     * @param users  the users to sort.
     * @param revers Sort in descending or ascending.
     * @return The sorted list of users.
     * @see User
     */
    List<User> sortByUrl(
            final Collection<User> users,
            final boolean revers
    );

    /**
     * Sorts and returns users by by role.
     *
     * @param users  the users to sort.
     * @param role   a role filtering.
     * @param revers Sort in descending or ascending.
     * @return The sorted list of users.
     * @see User
     * @see UserRole
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
     * @see User
     */
    List<User> getAndSortByName(final boolean revers);

    /**
     * Sorts and returns users by url.
     *
     * @param revers Sort in descending or ascending.
     * @return The sorted list of users.
     * @see User
     */
    List<User> getAndSortByUrl(final boolean revers);

    /**
     * Sorts and returns users by role.
     *
     * @param role   the role filtering.
     * @param revers Sort in descending or ascending.
     * @return The sorted list of users.
     * @see User
     * @see UserRole
     */
    List<User> getAndSortByRole(
            final UserRole role,
            final boolean revers
    );

    /**
     * Filters and returns users by role.
     *
     * @param users the users to filter.
     * @param role  a role filtering.
     * @return The filtered list of users.
     * @see User
     * @see UserRole
     */
    List<User> filterByRole(
            final Collection<User> users,
            final UserRole role
    );

    /**
     * Filters and returns users by roles.
     *
     * @param users the users to filter.
     * @param roles a roles filtering.
     * @return The filtered list of users.
     * @see User
     * @see UserRole
     */
    List<User> filterByRoles(
            final Collection<User> users,
            final List<UserRole> roles
    );

    /**
     * Filters and returns users by role.
     *
     * @param role a role filtering.
     * @return The filtered list of users.
     * @see User
     * @see UserRole
     */
    List<User> getAndFilterByRole(final UserRole role);

    /**
     * Filters and returns users by roles.
     *
     * @param roles a roles filtering.
     * @return The filtered list of users.
     * @see User
     * @see UserRole
     */
    List<User> getAndFilterByRoles(
            final List<UserRole> roles
    );
}
