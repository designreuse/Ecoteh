package com.salimov.yurii.dao.interfaces;

import com.salimov.yurii.entity.Response;

/**
 * The interface provides a set of standard methods
 * for working {@link Response} objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see com.salimov.yurii.dao.impl.ResponseDaoImpl
 * @see DataDao
 * @see Response
 */
public interface ResponseDao
        extends DataDao<Response, Long> {
}
