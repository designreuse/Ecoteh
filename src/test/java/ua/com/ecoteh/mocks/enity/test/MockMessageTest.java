package ua.com.ecoteh.mocks.enity.test;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.entity.message.Message;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.enity.MockModels.getMessage;
import static ua.com.ecoteh.mocks.enity.MockModels.getMessages;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MockMessageTest extends MockModelTest<Message> {

    private static Message message;
    private static Collection<Message> messages;

    @BeforeClass
    public static void beforeClass() {
        message = getMessage();
        messages = getMessages();
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
        assertNotNull(message.getUser());
    }

    @Test
    public void whenGetDateThenReturnNotNull() {
        assertNotNull(message.getDate());
    }

    @Override
    protected Message getObject() {
        return message;
    }

    @Override
    protected Collection<Message> getObjects() {
        return messages;
    }
}
