package com.salimov.ecoteh.dao.interfaces;

import com.salimov.ecoteh.entity.User;

/**
 * The interface provides a set of standard methods
 * for working {@link User} objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface UserDao extends DataDao<User> {

    /**
     * Returns user with the parameter name from a database.
     *
     * @param name a name of the user to return.
     * @return The user with parameter name.
     */
    User getByName(final String name);

    /**
     * Returns user with the parameter url from a database.
     *
     * @param url a URL of the user to return.
     * @return The user with parameter url.
     */
    User getByUrl(final String url);

    /**
     * Returns user with the parameter login from a database.
     *
     * @param login a login of the user to return.
     * @return The user with parameter login.
     */
    User getByLogin(final String login);

    /**
     * Removes user with the parameter name from a database.
     *
     * @param name a name of the user to remove.
     */
    void removeByName(final String name);

    /**
     * Removes user with the parameter url from a database.
     *
     * @param url a URL of the user to remove.
     */
    void removeByUrl(final String url);
}
