package ua.com.ecoteh.service.data;

import org.junit.BeforeClass;
import org.junit.Ignore;
import ua.com.ecoteh.entity.message.MessageEntity;
import ua.com.ecoteh.mocks.enity.MockEntity;
import ua.com.ecoteh.mocks.repository.MockRepository;

import java.util.Collection;

public class MessageEntityServiceImplTest extends DataServiceImplTest<MessageEntity> {

    private static MessageService service;

    @BeforeClass
    public static void beforeTest() {
        service = new MessageServiceImpl(MockRepository.getMessageRepository());
    }

    @Ignore
    @Override
    public void whenExistsByModelThenReturnsTrue() {
    }

    @Ignore
    @Override
    protected MessageService getService() {
        return service;
    }

    @Ignore
    @Override
    protected MessageEntity getObject() {
        return MockEntity.getMessageEntity();
    }

    @Ignore
    @Override
    protected Collection<MessageEntity> getObjects() {
        return MockEntity.getMessageEntities();
    }

    @Ignore
    @Override
    protected MessageEntity getInvalidObject() {
        return new MessageEntity();
    }
}
