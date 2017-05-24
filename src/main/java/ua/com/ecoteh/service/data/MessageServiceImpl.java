package ua.com.ecoteh.service.data;

import ua.com.ecoteh.entity.Message;
import ua.com.ecoteh.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNull;

/**
 * The class of the service layer, implements a set of methods for working
 * with objects of the {@link Message} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Service
@ComponentScan(basePackages = "ua.com.ecoteh.repository")
public final class MessageServiceImpl extends DataServiceImpl<Message> implements MessageService {

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
    protected Class<Message> getModelClass() {
        return Message.class;
    }

    /**
     * Validates input object of class {@link Message} or subclasses.
     *
     * @param message   the model to valid.
     * @param exist     is validate input model by exists.
     * @param duplicate is validate input model by duplicate.
     * @return Returns true if the model is valid, otherwise returns false.
     */
    @Override
    protected boolean validated(
            final Message message,
            final boolean exist,
            final boolean duplicate
    ) {
        boolean result = true;
        if (isNull(message)) {
            result = false;
        }
        if (result && exist) {
            result = exists(message);
        }
        return result;
    }
}
