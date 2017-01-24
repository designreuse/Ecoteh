package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.dao.interfaces.MessageDao;
import com.salimov.yurii.entity.Message;
import com.salimov.yurii.service.data.interfaces.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

/**
 * The class of the service layer, implements a set of methods for working
 * with objects of the {@link Message} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see Message
 * @see MessageService
 * @see DataServiceImpl
 * @see MessageDao
 */
@Service
@ComponentScan(basePackages = "com.salimov.yurii.dao")
public final class MessageServiceImpl
        extends DataServiceImpl<Message, Long>
        implements MessageService {

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param dao a implementation of the {@link MessageDao} interface.
     * @see MessageDao
     * @see Message
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public MessageServiceImpl(final MessageDao dao) {
        super(dao);
    }

    /**
     * Return Class object of {@link Message} or subclasses.
     *
     * @return The Class object of {@link Message} or subclasses.
     * @see Message
     */
    @Override
    protected Class<Message> getModelClass() {
        return Message.class;
    }

    /**
     * Validates input object of class {@link Message} or subclasses.
     *
     * @param message            the model to valid.
     * @param requiredParameters is validate input model by required parameters.
     * @param exist              is validate input model by exists.
     * @param duplicate          is validate input model by duplicate.
     * @return Returns {@code true} if the model is valid,
     * otherwise returns {@code false}.
     * @see Message
     */
    @Override
    protected boolean validated(
            final Message message,
            final boolean requiredParameters,
            final boolean exist,
            final boolean duplicate
    ) {
        boolean result = true;
        if (message == null) {
            result = false;
        }
        if (result && requiredParameters) {
            result = Message.isValidated(message);
        }
        if (result && exist) {
            result = exists(message);
        }
        return result;
    }
}
