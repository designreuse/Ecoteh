package ua.com.ecoteh.service.data;

import ua.com.ecoteh.entity.message.MessageEntity;
import ua.com.ecoteh.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNull;

/**
 * The class of the service layer, implements a set of methods for working
 * with objects of the {@link MessageEntity} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Service
@ComponentScan(basePackages = "ua.com.ecoteh.repository")
public final class MessageServiceImpl extends DataServiceImpl<MessageEntity> implements MessageService {

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
     * Return Class object of {@link MessageEntity} or subclasses.
     *
     * @return The Class object of {@link MessageEntity} or subclasses.
     */
    @Override
    protected Class<MessageEntity> getModelClass() {
        return MessageEntity.class;
    }

    /**
     * Validates input object of class {@link MessageEntity} or subclasses.
     *
     * @param messageEntity   the model to valid.
     * @param exist     is validate input model by exists.
     * @param duplicate is validate input model by duplicate.
     * @return Returns true if the model is valid, otherwise returns false.
     */
    @Override
    protected boolean validated(
            final MessageEntity messageEntity,
            final boolean exist,
            final boolean duplicate
    ) {
        boolean result = true;
        if (isNull(messageEntity)) {
            result = false;
        }
        if (result && exist) {
            result = exists(messageEntity);
        }
        return result;
    }
}
