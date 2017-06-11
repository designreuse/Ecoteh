package ua.com.ecoteh.mocks.enity.test;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.entity.message.MessageEntity;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getMessageEntities;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getMessageEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MockMessageEntityTest extends MockModelEntityTest<MessageEntity> {

    private static MessageEntity message;
    private static Collection<MessageEntity> messages;

    @BeforeClass
    public static void beforeClass() {
        message = getMessageEntity();
        messages = getMessageEntities();
    }

    @Test
    public void whenGetSubjectThenReturnNotEmpty() {
        assertFalse(message.getSubject().isEmpty());
    }

    @Test
    public void whenGetTextThenReturnNotEmpty() {
        assertFalse(message.getText().isEmpty());
    }

    @Test
    public void whenGetUserThenReturnNotNull() {
        assertNotNull(message.getUserEntity());
    }

    @Test
    public void whenGetDateThenReturnNotNull() {
        assertNotNull(message.getDate());
    }

    @Override
    protected MessageEntity getObject() {
        return message;
    }

    @Override
    protected Collection<MessageEntity> getObjects() {
        return messages;
    }
}
