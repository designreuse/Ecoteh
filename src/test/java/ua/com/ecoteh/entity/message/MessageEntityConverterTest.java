package ua.com.ecoteh.entity.message;

import org.junit.BeforeClass;
import ua.com.ecoteh.entity.model.ModelEntityConverterTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getMessageEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MessageEntityConverterTest extends ModelEntityConverterTest<MessageEntity, Message> {

    private static MessageEntityConverter converter;
    private static MessageEntity entity;

    @BeforeClass
    public static void beforeClass() {
        entity = getMessageEntity();
        converter = new MessageEntityConverter(entity);
    }

    @Override
    protected void checkEntity(final Message message) {
        super.checkEntity(message);
        assertEquals(message.getSubject(), entity.getSubject());
        assertEquals(message.getText(), entity.getText());
        assertEquals(message.getDate(), entity.getDate());
        assertNotNull(message.getUser());
    }

    @Override
    protected MessageEntityConverter getConverter() {
        return converter;
    }

    @Override
    protected MessageEntity getEntity() {
        return entity;
    }
}
