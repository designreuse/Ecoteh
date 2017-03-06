package com.salimov.yurii.repository;

import com.salimov.yurii.entity.Message;
import com.salimov.yurii.entity.Response;

/**
 * The interface provides a set of JPA methods
 * for working {@link Message} objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface MessageRepository extends DataRepository<Message> {
}
