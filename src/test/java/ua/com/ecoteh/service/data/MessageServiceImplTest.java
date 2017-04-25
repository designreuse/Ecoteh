package ua.com.ecoteh.service.data;

import ua.com.ecoteh.entity.Message;
import org.junit.Before;
import org.junit.Ignore;
import ua.com.ecoteh.mocks.enity.MockEntity;
import ua.com.ecoteh.mocks.repository.MockRepository;

import java.util.Collection;

public class MessageServiceImplTest extends DataServiceImplTest<Message> {

    private MessageService service;

    @Before
    public void beforeTest() {
        this.service = new MessageServiceImpl(MockRepository.getMessageRepository());
    }

    @Ignore
    @Override
    public void whenExistsByModelThenReturnsTrue() {
    }

    @Ignore
    @Override
    protected MessageService getService() {
        return this.service;
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
