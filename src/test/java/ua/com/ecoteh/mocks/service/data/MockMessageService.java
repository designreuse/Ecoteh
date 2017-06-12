package ua.com.ecoteh.mocks.service.data;

import ua.com.ecoteh.entity.message.Message;
import ua.com.ecoteh.service.data.MessageService;

import java.util.Collection;

import static org.mockito.Mockito.mock;
import static ua.com.ecoteh.mocks.enity.MockModels.getMessage;
import static ua.com.ecoteh.mocks.enity.MockModels.getMessages;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class MockMessageService extends MockDataService<Message> {
    
    private final MessageService service;
    private final Message message;
    private final Collection<Message> messages;

    MockMessageService() {
        this.service = mock(MessageService.class);
        this.message = getMessage();
        this.messages = getMessages();
    }

    @Override
    MessageService create() {
        super.create();
        return this.service;
    }

    @Override
    MessageService getService() {
        return this.service;
    }

    @Override
    Message getModel() {
        return this.message;
    }

    @Override
    Collection<Message> getModels() {
        return this.messages;
    }
}
