package com.salimov.yurii.repository;

import com.salimov.yurii.entity.User;

/**
 * The interface provides a set of JPA methods
 * for working {@link User} objects with a database.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see DataRepository
 * @see User
 */
public interface UserRepository extends DataRepository<User, Long> {

    /**
     * Returns user from a database,
     * which matches the parameter name.
     *
     * @param name a name of the user to return.
     * @return The object of class {@link User}.
     * @see User
     */
    User findByName(final String name);

    /**
     * Returns user from a database,
     * which matches the parameter login.
     *
     * @param login a login of the user to return.
     * @return The object of class {@link User}.
     * @see User
     */
    User findByLogin(final String login);

    /**
     * Returns user from a database,
     * which matches the parameter url.
     *
     * @param url a url of the user to return.
     * @return The object of class {@link User}.
     * @see User
     */
    User findByUrl(final String url);

    /**
     * Returns user from a database,
     * which matches the parameter phone.
     *
     * @param phone a phone of the user to return.
     * @return The object of class {@link User}.
     * @see User
     */
    User findByPhone(final String phone);

    /**
     * Returns user from a database,
     * which matches the parameter email.
     *
     * @param email a email of the user to return.
     * @return The object of class {@link User}.
     * @see User
     */
    User findByEmail(final String email);

    /**
     * Removes user from a database,
     * which matches the parameter name.
     *
     * @param name a name of the user to remove.
     * @see User
     */
    void deleteByName(final String name);

    /**
     * Removes user from a database,
     * which matches the parameter login.
     *
     * @param login a login of the user to remove.
     * @see User
     */
    void deleteByLogin(final String login);

    /**
     * Removes user from a database,
     * which matches the parameter url.
     *
     * @param url a url of the user to remove.
     * @see User
     */
    void deleteByUrl(final String url);
}
