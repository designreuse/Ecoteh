package ua.com.ecoteh.mocks.repository.tests;

import org.junit.BeforeClass;
import ua.com.ecoteh.entity.message.MessageEntity;
import ua.com.ecoteh.repository.MessageRepository;

import java.util.Collection;

import static ua.com.ecoteh.mocks.enity.MockModelEntities.getMessageEntities;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getMessageEntity;
import static ua.com.ecoteh.mocks.repository.MockRepository.getMessageRepository;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MockMessageRepositoryTest extends MockDataRepositoryTest<MessageEntity> {

    private static MessageEntity message;
    private static Collection<MessageEntity> messages;
    private static MessageRepository repository;

    @BeforeClass
    public static void beforeClass() {
        message = getMessageEntity();
        messages = getMessageEntities();
        repository = getMessageRepository();
    }

    @Override
    protected MessageRepository getRepository() {
        return repository;
    }

    @Override
    protected MessageEntity getObject() {
        return message;
    }

    @Override
    protected Collection<MessageEntity> getObjects() {
        return messages;
    }
}
