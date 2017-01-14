package com.salimov.yurii.dao.interfaces;

import com.salimov.yurii.entity.User;

/**
 * The interface provides a set of standard methods
 * for working {@link User} objects with a database.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see com.salimov.yurii.dao.impl.UserDaoImpl
 * @see DataDao
 * @see User
 */
public interface UserDao
        extends DataDao<User, Long> {

    /**
     * Returns user with the parameter name from a database.
     *
     * @param name a name of the user to return.
     * @return The user with parameter name.
     * @see User
     */
    User getByName(final String name);

    /**
     * Returns user with the parameter url from a database.
     *
     * @param url a url of the user to return.
     * @return The user with parameter url.
     * @see User
     */
    User getByUrl(final String url);

    /**
     * Returns user with the parameter login from a database.
     *
     * @param login a login of the user to return.
     * @return The user with parameter login.
     * @see User
     */
    User getByLogin(final String login);

    /**
     * Returns user with the parameter phone from a database.
     *
     * @param phone a phone of the user to return.
     * @return The user with parameter phone.
     * @see User
     */
    User getByPhone(final String phone);

    /**
     * Returns user with the parameter email from a database.
     *
     * @param email a email of the user to return.
     * @return The user with parameter email.
     * @see User
     */
    User getByEmail(final String email);

    /**
     * Removes user with the parameter name from a database.
     *
     * @param name a name of the user to remove.
     * @see User
     */
    void removeByName(final String name);

    /**
     * Removes user with the parameter url from a database.
     *
     * @param url a url of the user to remove.
     * @see User
     */
    void removeByUrl(final String url);

    /**
     * Removes user with the parameter login from a database.
     *
     * @param login a login of the user to remove.
     * @see User
     */
    void removeByLogin(final String login);
}
