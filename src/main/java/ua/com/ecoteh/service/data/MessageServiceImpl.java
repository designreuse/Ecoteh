package ua.com.ecoteh.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import ua.com.ecoteh.entity.message.Message;
import ua.com.ecoteh.entity.message.MessageEntity;
import ua.com.ecoteh.repository.MessageRepository;

/**
 * The class of the service layer, implements a set of methods for working
 * with objects of the {@link Message} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Message
 * @see MessageEntity
 */
@Service
@ComponentScan(basePackages = "ua.com.ecoteh.repository")
public final class MessageServiceImpl extends DataServiceImpl<Message, MessageEntity> implements MessageService {

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param repository the implementation of the {@link MessageRepository} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public MessageServiceImpl(final MessageRepository repository) {
        super(repository);
    }

    /**
     * Return Class object of {@link Message} or subclasses.
     *
     * @return The Class object of {@link Message} or subclasses.
     */
    @Override
    Class<Message> getModelClass() {
        return Message.class;
    }
}
