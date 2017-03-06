package com.salimov.yurii.dao.impl;

import com.salimov.yurii.dao.interfaces.MessageDao;
import com.salimov.yurii.entity.Message;
import com.salimov.yurii.repository.MessageRepository;
import com.salimov.yurii.util.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * The interface provides a set of standard methods
 * for working {@link Message} objects with the {@link Cache}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Repository
public final class MessageDaoImpl extends DataDaoImpl<Message> implements MessageDao {

    /**
     * Constructor.
     *
     * @param repository a implementation of the interface provides
     *                   a set of JPA methods for working {@link Message}
     *                   objects with the database.
     */
    @Autowired
    public MessageDaoImpl(final MessageRepository repository) {
        super(repository);
    }
}
