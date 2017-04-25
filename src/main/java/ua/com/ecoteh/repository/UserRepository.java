package ua.com.ecoteh.repository;

import ua.com.ecoteh.entity.User;

/**
 * The interface provides a set of JPA methods
 * for working {@link User} objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface UserRepository extends DataRepository<User> {

    /**
     * Returns user from a database,
     * which matches the parameter name.
     *
     * @param name a name of the user to return.
     * @return The object of class {@link User}.
     */
    User findByName(final String name);

    /**
     * Returns user from a database,
     * which matches the parameter login.
     *
     * @param login a login of the user to return.
     * @return The object of class {@link User}.
     */
    User findByEncryptedLogin(final String login);

    /**
     * Returns user from a database,
     * which matches the parameter url.
     *
     * @param url a URL of the user to return.
     * @return The object of class {@link User}.
     */
    User findByUrl(final String url);

    /**
     * Removes user from a database,
     * which matches the parameter name.
     *
     * @param name a name of the user to remove.
     */
    void deleteByName(final String name);

    /**
     * Removes user from a database,
     * which matches the parameter url.
     *
     * @param url a URL of the user to remove.
     */
    void deleteByUrl(final String url);

    /**
     * Returns user from a database,
     * which matches the parameter email.
     *
     * @param email a E-mail of the user to return.
     * @return The object of class {@link User}.
     */
    User findByContactsEmail(final String email);

    /**
     * Returns user from a database,
     * which matches the parameter mobilePhone.
     *
     * @param mobilePhone a mobile phone of the user to return.
     * @return The object of class {@link User}.
     */
    User findByContactsMobilePhone(final String mobilePhone);

    /**
     * Returns user from a database,
     * which matches the parameter landlinePhone.
     *
     * @param landlinePhone a landline phone of the user to return.
     * @return The object of class {@link User}.
     */
    User findByContactsLandlinePhone(final String landlinePhone);
}
