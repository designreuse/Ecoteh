package com.salimov.yurii.dao.impl;


import com.salimov.yurii.config.RootConfig;
import com.salimov.yurii.config.WebConfig;
import com.salimov.yurii.dao.interfaces.MessageDao;
import com.salimov.yurii.entity.Message;
import com.salimov.yurii.util.cache.Cache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static com.salimov.yurii.mocks.enity.MockEntity.getMessage;
import static com.salimov.yurii.mocks.enity.MockEntity.getMessages;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
@SuppressWarnings("SpringJavaAutowiringInspection")
public class MessageDaoImplTest {

    @Autowired
    private MessageDao messageDao;

    @Test
    @Transactional
    public void whenAddMessageThenSaveIt() {
        Cache.clear();
        assertNotNull(
                this.messageDao.add(
                        getMessage()
                )
        );
        assertTrue(Cache.getSize() != 0);
        Cache.clear();
    }

    @Test
    @Transactional
    public void whenAddAllMessagesThenSaveIt() {
        Cache.clear();
        final Collection<Message> messages = this.messageDao.addAll(
                getMessages()
        );
        assertNotNull(messages);
        assertFalse(
                messages.isEmpty()
        );
        assertTrue(Cache.getSize() != 0);
        Cache.clear();
    }

    @Test
    @Transactional
    public void whenUpdateMessageThenSaveIt() {
        Cache.clear();
        final Message message = this.messageDao.update(
                getMessage()
        );
        assertNotNull(message);
        assertTrue(Cache.getSize() != 0);
        Cache.clear();
    }

    @Test
    @Transactional
    public void whenGetMessageThenReturnIt() {
        Cache.clear();
        final Message message1 = this.messageDao.add(
                getMessage()
        );
        assertTrue(Cache.getSize() != 0);
        final Message message2 = this.messageDao.get(
                message1.getId()
        );
        assertNotNull(message2);
        assertEquals(message1, message2);
        Cache.clear();
    }

    @Test
    @Transactional
    public void whenGetAllMessagesThenReturnIt() {
        Cache.clear();
        final Collection<Message> messages = this.messageDao.addAll(
                getMessages()
        );
        assertNotNull(messages);
        assertFalse(
                messages.isEmpty()
        );
        assertTrue(Cache.getSize() != 0);
        assertNotNull(
                this.messageDao.getAll()
        );
        Cache.clear();
    }

    @Test
    @Transactional
    public void whenRemoveMessageById() {
        Cache.clear();
        final Message message = this.messageDao.add(
                getMessage()
        );
        assertNotNull(message);
        assertTrue(Cache.getSize() != 0);
        this.messageDao.remove(
                message.getId()
        );
        assertTrue(Cache.getSize() == 0);
        Cache.clear();
    }

    @Test
    @Transactional
    public void whenRemoveMessage() {
        Cache.clear();
        final Message message = this.messageDao.add(
                getMessage()
        );
        assertNotNull(message);
        assertTrue(Cache.getSize() != 0);
        this.messageDao.remove(message);
        assertTrue(Cache.getSize() == 0);
        Cache.clear();
    }

    @Test
    @Transactional
    public void whenRemoveAllMessage() throws InterruptedException {
        Cache.clear();
        Collection<Message> messages = this.messageDao.addAll(
                getMessages()
        );
        assertNotNull(messages);
        assertFalse(
                messages.isEmpty()
        );
        assertTrue(Cache.getSize() != 0);
        this.messageDao.removeAll();
        Thread.sleep(100);
        assertTrue(Cache.getSize() == 0);
        Cache.clear();
    }

    @Test
    @Transactional
    public void whenExistMessage() {
        Cache.clear();
        final Message message = this.messageDao.add(
                getMessage()
        );
        assertNotNull(message);
        assertTrue(Cache.getSize() != 0);
        assertTrue(
                this.messageDao.exists(
                        message.getId()
                )
        );
        Cache.clear();
    }
}
