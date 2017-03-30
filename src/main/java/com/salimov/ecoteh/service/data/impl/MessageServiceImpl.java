package com.salimov.ecoteh.service.data.impl;

import com.salimov.ecoteh.entity.Message;
import com.salimov.ecoteh.repository.MessageRepository;
import com.salimov.ecoteh.service.data.interfaces.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

/**
 * The class of the service layer, implements a set of methods for working
 * with objects of the {@link Message} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Service
@ComponentScan(basePackages = "com.salimov.ecoteh.dao")
public final class MessageServiceImpl extends DataServiceImpl<Message> implements MessageService {

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param repository a implementation of the {@link MessageRepository} interface.
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
     * @return Returns {@code true} if the model is valid,
     * otherwise returns {@code false}.
     */
    @Override
    protected boolean validated(
            final Message message,
            final boolean exist,
            final boolean duplicate
    ) {
        boolean result = true;
        if (message == null) {
            result = false;
        }
        if (result && exist) {
            result = exists(message);
        }
        return result;
    }
}
