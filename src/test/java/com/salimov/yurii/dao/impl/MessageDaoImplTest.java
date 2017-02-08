package com.salimov.yurii.dao.impl;

import com.salimov.yurii.dao.interfaces.MessageDao;
import com.salimov.yurii.entity.Message;
import com.salimov.yurii.util.cache.Cache;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static com.salimov.yurii.mocks.enity.MockEntity.getMessage;
import static com.salimov.yurii.mocks.enity.MockEntity.getMessages;
import static org.junit.Assert.*;

public final class MessageDaoImplTest {

    private MessageDao dao;

    @Before
    public void beforeTest() {
        this.dao = new MessageDaoImpl();
        Cache.clear();
    }

    @After
    public void afterTest() {
        Cache.clear();
    }

    @Test
    public void whenAddMessageThenReturnsIt() {
        assertNotNull(this.dao.add(getMessage()));
    }

    @Test
    public void whenAddAllMessageThenReturnsIt() {
        assertFalse(
                this.dao.addAll(
                        getMessages()
                ).isEmpty()
        );
    }

    @Test
    public void whenUpdateMessageThenReturnsIt() {
        assertNotNull(
                this.dao.update(getMessage())
        );
    }

    @Test
    public void whenGetMessageByIdThenReturnsSomeMessage() {
        final Message message = getMessage();
        this.dao.add(message);
        assertNotNull(
                this.dao.get(message.getId())
        );
    }

    @Test
    public void whenGetAllMessageThenReturnsMessageCollection() {
        this.dao.addAll(getMessages());
        assertFalse(
                this.dao.getAll().isEmpty()
        );
    }

    @Test
    public void whenRemoveByIdThenDoIt() {
        final Message message = getMessage();
        this.dao.add(message);
        this.dao.remove(
                message.getId()
        );
        assertNull(
                this.dao.get(
                        message.getId()
                )
        );
    }

    @Test
    public void whenRemoveMessageThenDoIt() {
        final Message message = getMessage();
        this.dao.add(message);
        assertNotNull(
                this.dao.get(
                        message.getId()
                )
        );
        this.dao.remove(message);
        assertNull(
                this.dao.get(
                        message.getId()
                )
        );
    }

    @Test
    public void whenRemoveMessageCollectionThenDoIt() {
        final Collection<Message> messages = getMessages();
        this.dao.addAll(messages);
        for (Message message : messages) {
            assertNotNull(
                    this.dao.get(
                            message.getId()
                    )
            );
        }
        this.dao.remove(messages);
        for (Message message : messages) {
            assertNull(
                    this.dao.get(
                            message.getId()
                    )
            );
        }
    }

    @Test
    public void whenRemoveAllMesagesThenDoIt() {
        this.dao.addAll(getMessages());
        assertFalse(
                this.dao.getAll().isEmpty()
        );
        this.dao.removeAll();
        try{
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        assertTrue(
                this.dao.getAll().isEmpty()
        );
    }

    @Test
    public void whenExistMessageByInvalidIdThenReturnFalse() {
        final Message message = getMessage();
        this.dao.remove(message.getId());
        assertFalse(
                this.dao.exists(
                        message.getId()
                )
        );
    }

    @Test
    public void whenExistMessageByValidIdThenReturnTrue() {
        final Message message = getMessage();
        this.dao.add(message);
        assertTrue(
                this.dao.exists(
                        message.getId()
                )
        );
    }
}
