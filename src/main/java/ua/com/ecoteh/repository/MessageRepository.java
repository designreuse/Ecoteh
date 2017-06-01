package ua.com.ecoteh.repository;

import ua.com.ecoteh.entity.message.MessageEntity;

/**
 * The interface provides a set of JPA methods
 * for working {@link MessageEntity} objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public interface MessageRepository extends DataRepository<MessageEntity> {
}
