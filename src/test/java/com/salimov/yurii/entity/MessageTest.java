package com.salimov.yurii.entity;

import org.junit.Ignore;
import org.junit.Test;

import static com.salimov.yurii.mocks.MockConstants.*;
import static com.salimov.yurii.mocks.enity.MockEntity.getMessage;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.junit.Assert.*;

public class MessageTest extends ModelTest<Message> {

    @Test
    public void whenPassInvalidParametersInConstructorThenSaveNull() {
        Message message = new Message();
        assertNotNull(message.getDate());

        message = new Message(null, null, null, null, null);
        assertNull(message.getUsername());
        assertNull(message.getEmail());
        assertNull(message.getPhone());
        assertNull(message.getSubject());
        assertNull(message.getText());
        assertNotNull(message.getDate());

        message = new Message("", "", "", "", "");
        assertNull(message.getUsername());
        assertNull(message.getEmail());
        assertNull(message.getPhone());
        assertNull(message.getSubject());
        assertNull(message.getText());
        assertNotNull(message.getDate());

        message = new Message(" ", " ", " ", " ", " ");
        assertNull(message.getUsername());
        assertNull(message.getEmail());
        assertNull(message.getPhone());
        assertNull(message.getSubject());
        assertNull(message.getText());
        assertNotNull(message.getDate());

        message = new Message(" ", " ", " ", " ", " ");
        assertNull(message.getUsername());
        assertNull(message.getEmail());
        assertNull(message.getPhone());
        assertNull(message.getSubject());
        assertNull(message.getText());
        assertNotNull(message.getDate());
    }

    @Test
    public void whenPassValidParametersInConstructorThenSaveThisValues() {
        final Message message = new Message(
                NAME, EMAIL, PHONE,
                ANY_STRING, TEXT
        );
        assertNotNull(message.getUsername());
        assertNotNull(message.getEmail());
        assertNotNull(message.getPhone());
        assertNotNull(message.getSubject());
        assertNotNull(message.getText());
        assertEquals(
                message.getUsername(),
                NAME
        );
        assertEquals(
                message.getEmail(),
                EMAIL
        );
        assertEquals(
                message.getPhone(),
                PHONE
        );
        assertEquals(
                message.getSubject(),
                ANY_STRING
        );
        assertEquals(
                message.getText(),
                TEXT
        );
    }

    @Test
    public void toStringTest() {
        final Message message = getMessage();
        final StringBuilder sb = new StringBuilder();
        sb.append("Date: ").append(message.getDate())
                .append(" \n").append(message.getUsername());
        if (isNotBlank(message.getEmail())) {
            sb.append(" \n").append(message.getEmail());
        }
        sb.append(" \n").append(message.getPhone())
                .append(" \n").append(message.getText());
        assertEquals(
                message.toString(),
                sb.toString()
        );
    }

    @Test
    public void equalsObjects() {
        final Message message1 = new Message();
        final Message message2 = new Message();
        assertTrue(
                message1.equals(message2)
        );
        message1.setUsername(NAME);
        message2.setUsername(NAME);
        assertTrue(
                message1.equals(message2)
        );
        message1.setEmail(EMAIL);
        message2.setEmail(EMAIL);
        assertTrue(
                message1.equals(message2)
        );
        message1.setPhone(PHONE);
        message2.setPhone(PHONE);
        assertTrue(
                message1.equals(message2)
        );
        message1.setSubject(ANY_STRING);
        message2.setSubject(ANY_STRING);
        assertTrue(
                message1.equals(message2)
        );
        message1.setText(TEXT);
        message2.setText(TEXT);
        assertTrue(
                message1.equals(message2)
        );
    }

    @Test
    public void hashCodeObject() {
        final Message message = new Message();
        int value = 0;
        assertEquals(
                message.hashCode(),
                value
        );
        message.setUsername(NAME);
        value += isNotBlank(message.getUsername()) ? message.getUsername().hashCode() : 0;
        assertEquals(
                message.hashCode(),
                value
        );
        message.setEmail(EMAIL);
        value += isNotBlank(message.getEmail()) ? message.getEmail().hashCode() : 0;
        assertEquals(
                message.hashCode(),
                value
        );
        message.setPhone(PHONE);
        value += isNotBlank(message.getPhone()) ? message.getPhone().hashCode() : 0;
        assertEquals(
                message.hashCode(),
                value
        );
        message.setSubject(ANY_STRING);
        value += isNotBlank(message.getSubject()) ? message.getSubject().hashCode() : 0;
        assertEquals(
                message.hashCode(),
                value
        );
        message.setText(TEXT);
        value += isNotBlank(message.getText()) ? message.getText().hashCode() : 0;
        assertEquals(
                message.hashCode(),
                value
        );
    }

    @Test
    public void whenInitializeObjectWithInvalidParametersThenGetNull() {
        final Message message = new Message();
        message.initialize(null, null, null, null, null);
        assertNull(message.getUsername());
        assertNull(message.getEmail());
        assertNull(message.getPhone());
        assertNull(message.getSubject());
        assertNull(message.getText());
        assertNotNull(message.getDate());

        message.initialize("", "", "", "", "");
        assertNull(message.getUsername());
        assertNull(message.getEmail());
        assertNull(message.getPhone());
        assertNull(message.getSubject());
        assertNull(message.getText());
        assertNotNull(message.getDate());

        message.initialize(" ", " ", " ", " ", " ");
        assertNull(message.getUsername());
        assertNull(message.getEmail());
        assertNull(message.getPhone());
        assertNull(message.getSubject());
        assertNull(message.getText());
        assertNotNull(message.getDate());

        message.initialize(" ", " ", " ", " ", " ");
        assertNull(message.getUsername());
        assertNull(message.getEmail());
        assertNull(message.getPhone());
        assertNull(message.getSubject());
        assertNull(message.getText());
        assertNotNull(message.getDate());
    }

    @Test
    public void whenInitializeObjectWithValidParametersThenGetThisValue() {
        final Message message = new Message();
        message.initialize(
                NAME, EMAIL, PHONE,
                ANY_STRING, TEXT
        );
        assertNotNull(message.getUsername());
        assertNotNull(message.getEmail());
        assertNotNull(message.getPhone());
        assertNotNull(message.getSubject());
        assertNotNull(message.getText());
        assertNotNull(message.getDate());
        assertEquals(
                message.getUsername(),
                NAME
        );
        assertEquals(
                message.getEmail(),
                EMAIL
        );
        assertEquals(
                message.getPhone(),
                PHONE
        );
        assertEquals(
                message.getSubject(),
                ANY_STRING
        );
        assertEquals(
                message.getText(),
                TEXT
        );
    }

    @Test
    public void whenSetInvalidUsernameThenGetNull() {
        final Message message = new Message();
        message.setUsername(null);
        assertNull(message.getUsername());
        message.setUsername("");
        assertNull(message.getUsername());
        message.setUsername(" ");
        assertNull(message.getUsername());
        message.setUsername("   ");
        assertNull(message.getUsername());
    }

    @Test
    public void whenSetValidUsernameThenGetThisUsername() {
        final Message message = new Message();
        message.setUsername(NAME);
        assertNotNull(message.getUsername());
        assertEquals(message.getUsername(), NAME);
    }

    @Test
    public void whenSetInvalidEmailThenGetNull() {
        final Message message = new Message();
        message.setEmail(null);
        assertNull(message.getEmail());
        message.setEmail("");
        assertNull(message.getEmail());
        message.setEmail(" ");
        assertNull(message.getEmail());
        message.setEmail("   ");
        assertNull(message.getEmail());
    }

    @Test
    public void whenSetValidEmailThenGetThisEmail() {
        final Message message = new Message();
        message.setEmail(EMAIL);
        assertNotNull(message.getEmail());
        assertEquals(message.getEmail(), EMAIL);
    }

    @Test
    public void whenSetInvalidPhoneThenGetNull() {
        final Message message = new Message();
        message.setPhone(null);
        assertNull(message.getPhone());
        message.setPhone("");
        assertNull(message.getPhone());
        message.setPhone(" ");
        assertNull(message.getPhone());
        message.setPhone("   ");
        assertNull(message.getPhone());
    }

    @Test
    public void whenSetValidPhoneThenGetThisPhone() {
        final Message message = new Message();
        message.setPhone(PHONE);
        assertNotNull(message.getPhone());
        assertEquals(message.getPhone(), PHONE);
    }

    @Test
    public void whenSetInvalidSubjectThenGetNull() {
        final Message message = new Message();
        message.setSubject(null);
        assertNull(message.getSubject());
        message.setSubject("");
        assertNull(message.getSubject());
        message.setSubject(" ");
        assertNull(message.getSubject());
        message.setSubject("   ");
        assertNull(message.getSubject());
    }

    @Test
    public void whenSetValidSubjectThenGetThisSubject() {
        final Message message = new Message();
        message.setSubject(ANY_STRING);
        assertNotNull(message.getSubject());
        assertEquals(message.getSubject(), ANY_STRING);
    }

    @Test
    public void whenSetInvalidTextThenGetNull() {
        final Message message = new Message();
        message.setText(null);
        assertNull(message.getText());
        message.setText("");
        assertNull(message.getText());
        message.setText(" ");
        assertNull(message.getText());
        message.setText("   ");
        assertNull(message.getText());
    }

    @Test
    public void whenSetValidTextThenGetThisText() {
        final Message message = new Message();
        message.setText(TEXT);
        assertNotNull(message.getText());
        assertEquals(message.getText(), TEXT);
    }

    @Test
    public void whenGetIdThenGetHashCode() {
        final Message message = getMessage();
        assertEquals(
                message.getId(),
                new Long(message.hashCode())
        );
    }

    @Ignore
    @Override
    public void whenSetInvalidIdThenGetNull() {
    }

    @Ignore
    @Override
    public void whenSetValidIdThenGetThisId() {
    }

    @Ignore
    @Override
    protected Message getObject() {
        return getMessage();
    }
}
