package ua.com.ecoteh.entity;

import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.message.MessageEntity;

import java.util.Date;

import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;
import static ua.com.ecoteh.mocks.MockConstants.TEXT;
import static ua.com.ecoteh.mocks.enity.MockEntity.getMessageEntity;
import static ua.com.ecoteh.mocks.enity.MockEntity.getUserEntity;
import static org.junit.Assert.*;

public final class MessageEntityTest extends ModelTest<MessageEntity> {

    @Test
    public void whenInitializeByConstructorThenSetNotNulDate() {
        final MessageEntity messageEntity = new MessageEntity();
        assertNotNull(messageEntity.getDate());
    }

    @Test
    public void toStringTest() {
        final MessageEntity messageEntity = getMessageEntity();
        final String messageToString = "MessageEntity{" +
                "ModelEntity{" +
                "id=" + messageEntity.getId() +
                ", validated=" + messageEntity.isValidated() +
                '}' +
                ", user=" + messageEntity.getUserEntity() +
                ", subject='" + messageEntity.getSubject() + '\'' +
                ", text='" + messageEntity.getText() + '\'' +
                ", date=" + messageEntity.getDate() +
                '}';
        assertEquals(messageEntity.toString(), messageToString);
    }

    @Test
    public void equalsObjects() {
        final MessageEntity messageEntity1 = new MessageEntity();
        final MessageEntity messageEntity2 = new MessageEntity();
        assertTrue(messageEntity1.equals(messageEntity2));
        messageEntity1.setUserEntity(getUserEntity());
        messageEntity2.setUserEntity(getUserEntity());
        assertTrue(messageEntity1.equals(messageEntity2));
        messageEntity1.setSubject(ANY_STRING);
        messageEntity2.setSubject(ANY_STRING);
        assertTrue(messageEntity1.equals(messageEntity2));
        messageEntity1.setText(TEXT);
        messageEntity2.setText(TEXT);
        assertTrue(messageEntity1.equals(messageEntity2));
    }

    @Test
    public void hashCodeObject() {
        final MessageEntity messageEntity = new MessageEntity();
        int value = messageEntity.getUserEntity().hashCode() +
                messageEntity.getSubject().hashCode() +
                messageEntity.getText().hashCode();
        assertEquals(messageEntity.hashCode(), value);
    }

    @Test
    public void whenSetNullSubjectThenGetEmptyString() {
        final MessageEntity messageEntity = new MessageEntity();
        messageEntity.setSubject(null);
        assertNotNull(messageEntity.getSubject());
        assertEquals(messageEntity.getSubject(), "");
    }

    @Test
    public void whenSetBlankSubjectThenGetEmptyString() {
        final MessageEntity messageEntity = new MessageEntity();
        messageEntity.setSubject("");
        assertNotNull(messageEntity.getSubject());
        messageEntity.setSubject(" ");
        assertNotNull(messageEntity.getSubject());
        messageEntity.setSubject("   ");
        assertNotNull(messageEntity.getSubject());
        assertEquals(messageEntity.getSubject(), "");
    }

    @Test
    public void whenSetValidSubjectThenGetThisSubject() {
        final MessageEntity messageEntity = new MessageEntity();
        messageEntity.setSubject(ANY_STRING);
        assertNotNull(messageEntity.getSubject());
        assertEquals(messageEntity.getSubject(), ANY_STRING);
    }

    @Test
    public void whenSetNullTextThenGetNull() {
        final MessageEntity messageEntity = new MessageEntity();
        messageEntity.setText(null);
        assertNotNull(messageEntity.getText());
        assertEquals(messageEntity.getText(), "");
    }

    @Test
    public void whenSetBlankTextThenGetEmptyString() {
        final MessageEntity messageEntity = new MessageEntity();
        messageEntity.setText("");
        assertNotNull(messageEntity.getText());
        messageEntity.setText(" ");
        assertNotNull(messageEntity.getText());
        messageEntity.setText("   ");
        assertNotNull(messageEntity.getText());
        assertEquals(messageEntity.getText(), "");
    }

    @Test
    public void whenSetValidTextThenGetThisText() {
        final MessageEntity messageEntity = new MessageEntity();
        messageEntity.setText(TEXT);
        assertNotNull(messageEntity.getText());
        assertEquals(messageEntity.getText(), TEXT);
    }

    @Test
    public void whenSetNullDateThenGetNewDate() {
        final MessageEntity messageEntity = getMessageEntity();
        messageEntity.setDate(null);
        assertNotNull(messageEntity.getDate());
    }

    @Test
    public void whenSetValidDateThenGetThisDate() {
        final MessageEntity messageEntity = getMessageEntity();
        final Date date = new Date();
        messageEntity.setDate(date);
        assertNotNull(messageEntity.getDate());
        assertEquals(messageEntity.getDate(), date);
    }

    @Test
    public void whenInitializeByNullThenDoNothing() {
        final MessageEntity model1 = getInstance();
        final MessageEntity model2 = model1.initialize(null);
        assertEquals(model1, model2);
    }

    @Test
    public void whenInitializeByValidObjectThenCopyIt() {
        final MessageEntity model1 = getInstance();
        final MessageEntity model2 = getObject();
        model1.initialize(model2);
        assertEquals(model1, model2);
    }

    @Ignore
    @Override
    protected MessageEntity getObject() {
        return getMessageEntity();
    }

    @Override
    protected MessageEntity getInstance() {
        return new MessageEntity();
    }
}
