package ua.com.ecoteh.service.data;

import org.junit.BeforeClass;
import ua.com.ecoteh.entity.message.Message;
import ua.com.ecoteh.entity.message.MessageBuilder;
import ua.com.ecoteh.repository.MessageRepository;

import java.util.Collection;
import java.util.Comparator;

import static org.mockito.Mockito.mock;
import static ua.com.ecoteh.mocks.MockConstants.UNKNOWN_ID;
import static ua.com.ecoteh.mocks.enity.MockModels.getMessage;
import static ua.com.ecoteh.mocks.enity.MockModels.getMessages;
import static ua.com.ecoteh.mocks.repository.MockRepository.getMessageRepository;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MessageServiceImplTest extends DataServiceImplTest<Message> {

    private static MessageService service;
    private static Message message;
    private static Collection<Message> messages;

    @BeforeClass
    public static void beforeClass() {
        final MessageRepository repository = getMessageRepository();
        service = new MessageServiceImpl(repository);
        message = getMessage();
        messages = getMessages();
    }

    @Override
    protected MessageService getService() {
        return service;
    }

    @Override
    protected Message getModel() {
        return message;
    }

    @Override
    protected Collection<Message> getModels() {
        return messages;
    }

    @Override
    protected Message getUnknownModel() {
        final MessageBuilder builder = Message.getBuilder();
        builder.addId(UNKNOWN_ID);
        return builder.build();
    }

    @Override
    protected Comparator<Message> getComparator() {
        return mock(Comparator.class);
    }
}