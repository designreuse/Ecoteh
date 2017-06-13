package ua.com.ecoteh.entity.message;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.entity.model.ModelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModels.getUser;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MessageTest extends ModelTest {

    private static Message message;

    @BeforeClass
    public static void beforeClass() {
        message = new Message(ID, VALIDATION, getUser(), TITLE, TEXT, DATE);
    }

    @Test
    @Override
    public void toStringTest() throws Exception {
        final String testString = "Message{" +
                "Model{id=" + message.getId() +
                ", validated=" + message.isValidated() + '}'+
                ", user=" + message.getUser() +
                ", subject='" + message.getSubject() + '\'' +
                ", text='" + message.getText() + '\'' +
                ", date=" + message.getDate() +
                '}';
        assertEquals(message.toString(), testString);
    }

    @Test
    public void getUserTest() throws Exception {
        assertNotNull(message.getUser());
    }

    @Test
    public void getSubject() throws Exception {
        assertFalse(message.getSubject().isEmpty());
    }

    @Test
    public void getText() throws Exception {
        assertFalse(message.getText().isEmpty());
    }

    @Test
    public void getDate() throws Exception {
        assertNotNull(message.getDate());
    }

    @Test
    public void getDateToString() throws Exception {
        assertFalse(message.getDateToString().isEmpty());
    }

    @Test
    @Override
    public void convert() throws Exception {
        super.convert();
        final MessageEntity entity = message.convert();
        assertEquals(entity.getId(), message.getId());
        assertEquals(entity.isValidated(), message.isValidated());
        assertEquals(entity.getSubject(), message.getSubject());
        assertEquals(entity.getText(), message.getText());
        assertEquals(entity.getDate(), message.getDate());
        assertNotNull(entity.getUserEntity());
    }

    @Test
    public void getBuilder() throws Exception {
        final MessageBuilder builder = Message.getBuilder();
        assertNotNull(builder);
    }

    @Override
    protected Message getInstance() {
        return message;
    }
}