package com.salimov.yurii.dao.impl;

import com.salimov.yurii.dao.interfaces.UserDao;
import com.salimov.yurii.entity.User;
import com.salimov.yurii.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

/**
 * The interface provides a set of standard methods
 * for working {@link User} objects with a database.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see UserDao
 * @see User
 * @see UserRepository
 * @see DataDaoImpl
 */
@Repository
@ComponentScan(basePackages = "com.salimov.yurii.repository")
public final class UserDaoImpl
        extends DataDaoImpl<User, Long>
        implements UserDao {

    /**
     * The interface provides a set of JPA methods
     * for working {@link User} objects with a database.
     *
     * @see UserRepository
     */
    private final UserRepository repository;

    /**
     * Constructor.
     *
     * @param repository The interface provides a set of JPA methods
     *                   for working {@link User} objects with a database.
     * @see UserRepository
     */
    @Autowired
    public UserDaoImpl(final UserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Returns user with the parameter name from a database.
     *
     * @param name a name of the user to return.
     * @return The user with parameter name.
     * @see User
     */
    @Override
    public User getByName(final String name) {
        return this.repository.findByName(name);
    }

    /**
     * Returns user with the parameter url from a database.
     *
     * @param url a url of the user to return.
     * @return The user with parameter url.
     * @see User
     */
    @Override
    public User getByUrl(final String url) {
        return this.repository.findByUrl(url);
    }

    /**
     * Returns user with the parameter login from a database.
     *
     * @param login a login of the user to return.
     * @return The user with parameter login.
     * @see User
     */
    @Override
    public User getByLogin(final String login) {
        return this.repository.findByLogin(login);
    }

    /**
     * Returns user with the parameter phone from a database.
     *
     * @param phone a phone of the user to return.
     * @return The user with parameter phone.
     * @see User
     */
    @Override
    public User getByPhone(final String phone) {
        return this.repository.findByPhone(phone);
    }

    /**
     * Returns user with the parameter email from a database.
     *
     * @param email a email of the user to return.
     * @return The user with parameter email.
     * @see User
     */
    @Override
    public User getByEmail(final String email) {
        return this.repository.findByEmail(email);
    }

    /**
     * Removes user with the parameter name from a database.
     *
     * @param name a name of the user to remove.
     * @see User
     */
    @Override
    public void removeByName(final String name) {
        this.repository.deleteByName(name);
    }

    /**
     * Removes user with the parameter url from a database.
     *
     * @param url a url of the user to remove.
     * @see User
     */
    @Override
    public void removeByUrl(final String url) {
        this.repository.deleteByUrl(url);
    }

    /**
     * Removes user with the parameter login from a database.
     *
     * @param login a login of the user to remove.
     * @see User
     */
    @Override
    public void removeByLogin(final String login) {
        this.repository.deleteByLogin(login);
    }
}
