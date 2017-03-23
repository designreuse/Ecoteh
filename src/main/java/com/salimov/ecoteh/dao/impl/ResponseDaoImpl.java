package com.salimov.ecoteh.dao.impl;

import com.salimov.ecoteh.dao.interfaces.ResponseDao;
import com.salimov.ecoteh.entity.Response;
import com.salimov.ecoteh.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

/**
 * The interface provides a set of standard methods
 * for working {@link Response} objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Repository
@ComponentScan(basePackages = "com.salimov.ecoteh.repository")
public final class ResponseDaoImpl extends DataDaoImpl<Response> implements ResponseDao {

    /**
     * Constructor.
     *
     * @param repository a implementation of the interface provides
     *                   a set of JPA methods for working {@link Response}
     *                   objects with the database.
     */
    @Autowired
    public ResponseDaoImpl(final ResponseRepository repository) {
        super(repository);
    }
}
