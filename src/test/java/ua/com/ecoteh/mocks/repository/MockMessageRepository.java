package ua.com.ecoteh.mocks.repository;

import ua.com.ecoteh.entity.message.MessageEntity;
import ua.com.ecoteh.repository.MessageRepository;

import java.util.Collection;

import static org.mockito.Mockito.mock;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getMessageEntities;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getMessageEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MockMessageRepository extends MockDataRepository<MessageEntity> {

    private final MessageRepository repository;
    private final MessageEntity entity;
    private final Collection<MessageEntity> entities;

    MockMessageRepository() {
        this.repository = mock(MessageRepository.class);
        this.entity = getMessageEntity();
        this.entities = getMessageEntities();
    }

    @Override
    MessageRepository create() {
        super.create();
        return this.repository;
    }

    @Override
    MessageRepository getRepository() {
        return this.repository;
    }

    @Override
    MessageEntity getEntity() {
        return this.entity;
    }

    @Override
    Collection<MessageEntity> getEntities() {
        return this.entities;
    }
}
