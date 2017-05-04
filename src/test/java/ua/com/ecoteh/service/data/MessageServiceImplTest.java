package ua.com.ecoteh.service.data;

import org.junit.BeforeClass;
import org.junit.Ignore;
import ua.com.ecoteh.entity.Message;
import ua.com.ecoteh.mocks.enity.MockEntity;
import ua.com.ecoteh.mocks.repository.MockRepository;

import java.util.Collection;

public class MessageServiceImplTest extends DataServiceImplTest<Message> {

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
    protected Message getObject() {
        return MockEntity.getMessage();
    }

    @Ignore
    @Override
    protected Collection<Message> getObjects() {
        return MockEntity.getMessages();
    }

    @Ignore
    @Override
    protected Message getInvalidObject() {
        return new Message();
    }
}
