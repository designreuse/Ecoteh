package com.salimov.ecoteh.entity;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;

import static com.salimov.ecoteh.mocks.MockConstants.ANY_STRING;
import static com.salimov.ecoteh.mocks.MockConstants.TEXT;
import static com.salimov.ecoteh.mocks.enity.MockEntity.getMessage;
import static com.salimov.ecoteh.mocks.enity.MockEntity.getUser;
import static org.junit.Assert.*;

public final class MessageTest extends ModelTest<Message> {

    @Test
    public void whenInitializeByConstructorThenSetNotNulDate() {
        final Message message = new Message();
        assertNotNull(message.getDate());
    }

    @Test
    public void toStringTest() {
        final Message message = getMessage();
        final String messageToString = "Message{" +
                "Model{" +
                "id=" + message.getId() +
                ", validated=" + message.isValidated() +
                '}' +
                ", " + message.getUser() +
                ", subject='" + message.getSubject() + '\'' +
                ", text='" + message.getText() + '\'' +
                ", date=" + message.getDate() +
                '}';
        System.out.println(message.getId());
        assertEquals(message.toString(), messageToString);
    }

    @Test
    public void equalsObjects() {
        final Message message1 = new Message();
        final Message message2 = new Message();
        assertTrue(message1.equals(message2));
        message1.setUser(getUser());
        message2.setUser(getUser());
        assertTrue(message1.equals(message2));
        message1.setSubject(ANY_STRING);
        message2.setSubject(ANY_STRING);
        assertTrue(message1.equals(message2));
        message1.setText(TEXT);
        message2.setText(TEXT);
        assertTrue(message1.equals(message2));
    }

    @Test
    public void hashCodeObject() {
        final Message message = new Message();
        int value = message.getUser().hashCode() +
                message.getSubject().hashCode() +
                message.getText().hashCode();
        assertEquals(message.hashCode(), value);
    }

    @Test
    public void whenSetNullSubjectThenGetEmptyString() {
        final Message message = new Message();
        message.setSubject(null);
        assertNotNull(message.getSubject());
        assertEquals(message.getSubject(), "");
    }

    @Test
    public void whenSetBlankSubjectThenGetEmptyString() {
        final Message message = new Message();
        message.setSubject("");
        assertNotNull(message.getSubject());
        message.setSubject(" ");
        assertNotNull(message.getSubject());
        message.setSubject("   ");
        assertNotNull(message.getSubject());
        assertEquals(message.getSubject(), "");
    }

    @Test
    public void whenSetValidSubjectThenGetThisSubject() {
        final Message message = new Message();
        message.setSubject(ANY_STRING);
        assertNotNull(message.getSubject());
        assertEquals(message.getSubject(), ANY_STRING);
    }

    @Test
    public void whenSetNullTextThenGetNull() {
        final Message message = new Message();
        message.setText(null);
        assertNotNull(message.getText());
        assertEquals(message.getText(), "");
    }

    @Test
    public void whenSetBlankTextThenGetEmptyString() {
        final Message message = new Message();
        message.setText("");
        assertNotNull(message.getText());
        message.setText(" ");
        assertNotNull(message.getText());
        message.setText("   ");
        assertNotNull(message.getText());
        assertEquals(message.getText(), "");
    }

    @Test
    public void whenSetValidTextThenGetThisText() {
        final Message message = new Message();
        message.setText(TEXT);
        assertNotNull(message.getText());
        assertEquals(message.getText(), TEXT);
    }

    @Test
    public void whenSetNullDateThenGetNewDate() {
        final Message message = getMessage();
        message.setDate(null);
        assertNotNull(message.getDate());
    }

    @Test
    public void whenSetValidDateThenGetThisDate() {
        final Message message = getMessage();
        final Date date = new Date();
        message.setDate(date);
        assertNotNull(message.getDate());
        assertEquals(message.getDate(), date);
    }

    @Test
    public void whenInitializeByNullThenDoNothing() {
        final Message model1 = getInstance();
        final Message model2 = model1.initialize(null);
        assertEquals(model1, model2);
    }

    @Test
    public void whenInitializeByValidObjectThenCopyIt() {
        final Message model1 = getInstance();
        final Message model2 = getObject();
        model1.initialize(model2);
        assertEquals(model1, model2);
    }

    @Ignore
    @Override
    protected Message getObject() {
        return getMessage();
    }

    @Override
    protected Message getInstance() {
        return new Message();
    }
}
