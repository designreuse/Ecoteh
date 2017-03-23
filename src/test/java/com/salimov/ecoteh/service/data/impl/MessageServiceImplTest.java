package com.salimov.ecoteh.service.data.impl;

import com.salimov.ecoteh.entity.Message;
import com.salimov.ecoteh.service.data.interfaces.MessageService;
import org.junit.Before;
import org.junit.Ignore;

import java.util.Collection;

import static com.salimov.ecoteh.mocks.dao.MockDao.getMessageDao;
import static com.salimov.ecoteh.mocks.enity.MockEntity.getMessage;
import static com.salimov.ecoteh.mocks.enity.MockEntity.getMessages;

public class MessageServiceImplTest extends DataServiceImplTest<Message> {

    private MessageService service;

    @Before
    public void beforeTest() {
        this.service = new MessageServiceImpl(getMessageDao());
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
        return getMessage();
    }

    @Ignore
    @Override
    protected Collection<Message> getObjects() {
        return getMessages();
    }

    @Ignore
    @Override
    protected Message getInvalidObject() {
        return new Message();
    }
}
