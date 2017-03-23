package com.salimov.ecoteh.dao.impl;

import com.salimov.ecoteh.dao.interfaces.UserDao;
import com.salimov.ecoteh.entity.User;
import com.salimov.ecoteh.repository.UserRepository;
import com.salimov.ecoteh.util.encryption.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

/**
 * The interface provides a set of standard methods
 * for working {@link User} objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Repository
@ComponentScan(basePackages = "com.salimov.ecoteh.repository")
public final class UserDaoImpl extends DataDaoImpl<User> implements UserDao {

    /**
     * The interface provides a set of JPA methods
     * for working {@link User} objects with a database.
     */
    private final UserRepository repository;

    /**
     * Constructor.
     *
     * @param repository The interface provides a set of JPA methods
     *                   for working {@link User} objects with a database.
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
     */
    @Override
    public User getByName(final String name) {
        return this.repository.findByName(name);
    }

    /**
     * Returns user with the parameter url from a database.
     *
     * @param url a URL of the user to return.
     * @return The user with parameter URL.
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
     */
    @Override
    public User getByLogin(final String login) {
        return this.repository.findByEncryptedLogin(
                new Encryptor(login).encrypt()
        );
    }

    /**
     * Removes user with the parameter name from a database.
     *
     * @param name a name of the user to remove.
     */
    @Override
    public void removeByName(final String name) {
        this.repository.deleteByName(name);
    }

    /**
     * Removes user with the parameter url from a database.
     *
     * @param url a URL of the user to remove.
     */
    @Override
    public void removeByUrl(final String url) {
        this.repository.deleteByUrl(url);
    }
}
