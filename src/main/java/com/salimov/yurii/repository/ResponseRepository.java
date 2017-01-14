package com.salimov.yurii.repository;

import com.salimov.yurii.entity.Response;

/**
 * The interface provides a set of JPA methods
 * for working {@link Response} objects with a database.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see DataRepository
 * @see Response
 */
public interface ResponseRepository
        extends DataRepository<Response, Long> {
}
