package com.salimov.yurii.entity;

import com.salimov.yurii.mocks.MockConstants;
import com.salimov.yurii.mocks.enity.MockEntity;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.junit.Assert.*;

public final class ResponseTest extends ModelTest<Response> {

    @Test
    public void whenPassInvalidParametersInConstructorThenSaveNull() {
        Response response = new Response(null, null);
        assertNull(response.getUsername());
        assertNull(response.getText());
        assertNotNull(response.getDate());

        response = new Response("", "");
        assertNull(response.getUsername());
        assertNull(response.getText());
        assertNotNull(response.getDate());

        response = new Response(" ", " ");
        assertNull(response.getUsername());
        assertNull(response.getText());
        assertNotNull(response.getDate());

        response = new Response("   ", "   ");
        assertNull(response.getUsername());
        assertNull(response.getText());
        assertNotNull(response.getDate());
    }

    @Test
    public void whenPassValidParametersInConstructorThenSaveThisValues() {
        Response response = new Response();
        assertNotNull(response.getDate());

        response = new Response(
                MockConstants.NAME,
                MockConstants.TEXT
        );
        assertNotNull(response.getUsername());
        assertNotNull(response.getText());
        assertNotNull(response.getDate());
        Assert.assertEquals(
                response.getUsername(),
                MockConstants.NAME
        );
        Assert.assertEquals(
                response.getText(),
                MockConstants.TEXT
        );
    }

    @Test
    public void equalsInvalidObjects() {
        final Response response1 = new Response();
        final Response response2 = new Response();
        assertTrue(response1.equals(response2));
        response1.setUsername(MockConstants.NAME);
        response2.setUsername(MockConstants.NAME);
        assertTrue(
                response1.equals(response2)
        );
        response1.setText(MockConstants.TEXT);
        response2.setText(MockConstants.TEXT);
        assertTrue(
                response1.equals(response2)
        );
    }

    @Test
    @Override
    public void equalsValidObjects() {
        super.equalsValidObjects();

        final Response response1 = MockEntity.getResponse();
        final Response response2 = (Response) response1.clone();
        assertEquals(response1, response2);

        boolean value = (
                isNotBlank(response1.getUsername()) ?
                        response1.getUsername()
                                .equals(
                                        response2.getUsername()
                                ) :
                        isBlank(response2.getUsername())
        ) && (
                isNotBlank(response1.getText()) ?
                response1.getText()
                        .equals(
                                response2.getText()
                        ) :
                isBlank(response2.getText())
        );
        assertEquals(
                response1.equals(response2),
                value
        );
    }

    @Test
    public void hashCodeInvalidObject() {
        final Response response = new Response();
        int value = 0;
        assertEquals(response.hashCode(), value);
        response.setUsername(MockConstants.NAME);
        value = isNotBlank(response.getUsername()) ? response.getUsername().hashCode() : 0;
        assertEquals(response.hashCode(), value);
        response.setText(MockConstants.TEXT);
        value += isNotBlank(response.getText()) ? response.getText().hashCode() : 0;
        assertEquals(response.hashCode(), value);
    }

    @Test
    @Override
    public void hashCodeValidObject() {
        final Response response = MockEntity.getResponse();
        int value = (
                isNotBlank(response.getUsername()) ?
                        response.getUsername().hashCode() : 0
        ) + (
                isNotBlank(response.getText()) ?
                        response.getText().hashCode() : 0
        );
        for (int i = 0; i < 10; i++) {
            assertEquals(
                    response.hashCode(),
                    value
            );
        }
    }

    @Test
    public void toStringTest() {
        final Response response = MockEntity.getResponse();
        final String value = "Date: " + response.getDate()
                + " \nUsername: " + response.getUsername()
                + " \nText: " + response.getText();
        assertEquals(
                response.toString(),
                value
        );
    }

    @Test
    public void whenInitializeObjectWithInvalidParametersThenGetNull() {
        final Response response = new Response();
        response.initialize(null, null);
        assertNull(response.getUsername());
        assertNull(response.getText());
        assertNotNull(response.getDate());
        response.initialize("", "");
        assertNull(response.getUsername());
        assertNull(response.getText());
        assertNotNull(response.getDate());
        response.initialize(" ", " ");
        assertNull(response.getUsername());
        assertNull(response.getText());
        assertNotNull(response.getDate());
        response.initialize("   ", "   ");
        assertNull(response.getUsername());
        assertNull(response.getText());
        assertNotNull(response.getDate());
    }

    @Test
    public void whenInitializeObjectWithValidParametersThenGetThisValue() {
        final Response response = new Response();
        response.initialize(
                MockConstants.NAME,
                MockConstants.TEXT
        );
        assertNotNull(response.getUsername());
        assertNotNull(response.getText());
        assertNotNull(response.getDate());
        Assert.assertEquals(
                response.getUsername(),
                MockConstants.NAME
        );
        Assert.assertEquals(
                response.getText(),
                MockConstants.TEXT
        );
    }

    @Test
    public void whenSetInvalidUsernameThenGetNull() {
        final Response response = MockEntity.getResponse();
        response.setUsername(null);
        assertNull(response.getUsername());
        response.setUsername("");
        assertNull(response.getUsername());
        response.setUsername(" ");
        assertNull(response.getUsername());
        response.setUsername("   ");
        assertNull(response.getUsername());
    }

    @Test
    public void whenSetValidUsernameThenGetThisUsername() {
        final Response response = MockEntity.getResponse();
        response.setUsername(MockConstants.NAME);
        assertNotNull(response.getUsername());
        Assert.assertEquals(
                response.getUsername(),
                MockConstants.NAME
        );
    }

    @Test
    public void whenSetInvalidTextThenGetNull() {
        final Response response = MockEntity.getResponse();
        response.setText(null);
        assertNull(response.getText());
        response.setText("");
        assertNull(response.getText());
        response.setText(" ");
        assertNull(response.getText());
        response.setText("   ");
        assertNull(response.getText());
    }

    @Test
    public void whenSetValidTextThenGetThisText() {
        final Response response = MockEntity.getResponse();
        response.setText(MockConstants.TEXT);
        assertNotNull(response.getText());
        Assert.assertEquals(
                response.getText(),
                MockConstants.TEXT
        );
    }

    @Test
    public void whenSetNullDateThenGetNewDate() {
        final Response response = MockEntity.getResponse();
        response.setDate(null);
        assertNotNull(response.getDate());
        assertNotNull(response.getDateToString());
    }

    @Test
    public void whenSetNotNullDateThenGetThisDate() {
        final Response response = MockEntity.getResponse();
        final Date date = new Date();
        response.setDate(date);
        assertNotNull(response.getDate());
        assertNotNull(response.getDateToString());
        assertEquals(
                response.getDate(),
                date
        );
    }

    @Test
    @Override
    public void validObject() {
        super.validObject();
        Response response = MockEntity.getResponse();
        assertFalse(Response.isValidated(null));
        assertTrue(Response.isValidated(response));
        assertTrue(response.isValidated());
        response.setUsername(null);
        assertFalse(Response.isValidated(response));
        assertFalse(response.isValidated());
        response = MockEntity.getResponse();
        response.setText(null);
        assertFalse(Response.isValidated(response));
        assertFalse(response.isValidated());
    }

    @Ignore
    @Override
    protected Response getObject() {
        return MockEntity.getResponse();
    }
}
