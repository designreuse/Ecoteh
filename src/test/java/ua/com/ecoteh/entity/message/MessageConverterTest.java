package ua.com.ecoteh.entity.message;

import org.junit.BeforeClass;
import ua.com.ecoteh.entity.model.ModelConverterTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.enity.MockModels.getMessage;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MessageConverterTest extends ModelConverterTest<Message, MessageEntity> {

    private static MessageConverter converter;
    private static Message message;

    @BeforeClass
    public static void beforeClass() {
        message = getMessage();
        converter = new MessageConverter(message);
    }

    @Override
    protected void checkEntity(final MessageEntity entity) {
        super.checkEntity(entity);
        assertEquals(entity.getSubject(), message.getSubject());
        assertEquals(entity.getText(), message.getText());
        assertEquals(entity.getDate(), message.getDate());
        assertNotNull(entity.getUserEntity());
    }

    @Override
    protected MessageConverter getConverter() {
        return converter;
    }

    @Override
    protected Message getModel() {
        return message;
    }
}
