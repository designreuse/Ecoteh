package ua.com.ecoteh.mocks.service.data.test;

import org.junit.BeforeClass;
import org.junit.Ignore;
import ua.com.ecoteh.entity.message.Message;
import ua.com.ecoteh.service.data.MessageService;

import java.util.Collection;

import static ua.com.ecoteh.mocks.enity.MockModels.getMessage;
import static ua.com.ecoteh.mocks.enity.MockModels.getMessages;
import static ua.com.ecoteh.mocks.service.data.MockServices.getMessageService;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MockMessageServiceTest extends MockDataServiceTest<Message> {

    private static MessageService service;
    private static Message message;
    private static Collection<Message> messages;

    @BeforeClass
    public static void beforeClass() {
        service = getMessageService();
        message = getMessage();
        messages = getMessages();
    }

    @Ignore
    @Override
    protected MessageService getService() {
        return service;
    }

    @Ignore
    @Override
    protected Message getObject() {
        return message;
    }

    @Ignore
    @Override
    protected Collection<Message> getObjects() {
        return messages;
    }
}
