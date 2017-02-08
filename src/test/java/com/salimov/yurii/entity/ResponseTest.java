package com.salimov.yurii.entity;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;

import static com.salimov.yurii.mocks.MockConstants.NAME;
import static com.salimov.yurii.mocks.MockConstants.TEXT;
import static com.salimov.yurii.mocks.enity.MockEntity.getResponse;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.junit.Assert.*;

public final class ResponseTest extends ModelTest<Response> {

    @Test
    public void whenInitializeByConstructorThenSetNotNulDate() {
        final Response response = new Response(null, null);
        assertNotNull(response.getDate());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveNull_1() {
        final Response response = new Response("", "");
        assertNull(response.getUsername());
        assertNull(response.getText());
        assertNotNull(response.getDate());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveNull_2() {
        final Response response = new Response(" ", " ");
        assertNull(response.getUsername());
        assertNull(response.getText());
        assertNotNull(response.getDate());
    }

    @Test
    public void whenPassInvalidParametersInConstructorThenSaveNull() {
        final Response response = new Response("   ", "   ");
        assertNull(response.getUsername());
        assertNull(response.getText());
        assertNotNull(response.getDate());
    }

    @Test
    public void whenPassValidParametersInConstructorThenSaveThisValues() {
        Response response = new Response();
        assertNotNull(response.getDate());
        response = new Response(NAME, TEXT);
        assertNotNull(response.getUsername());
        assertNotNull(response.getText());
        assertNotNull(response.getDate());
        assertEquals(response.getUsername(), NAME);
        assertEquals(response.getText(), TEXT);
    }

    @Test
    public void equalsInvalidObjects() {
        final Response response1 = new Response();
        final Response response2 = new Response();
        assertTrue(response1.equals(response2));
        response1.setUsername(NAME);
        response2.setUsername(NAME);
        assertTrue(response1.equals(response2));
        response1.setText(TEXT);
        response2.setText(TEXT);
        assertTrue(response1.equals(response2));
    }

    @Test
    @Override
    public void equalsValidObjects() {
        super.equalsValidObjects();
        final Response response1 = getResponse();
        final Response response2 = response1.clone();
        assertEquals(response1, response2);
        assertEquals(
                response1.equals(response2),
                (
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
                )
        );
    }

    @Test
    public void hashCodeInvalidObject() {
        final Response response = new Response();
        int value = 0;
        assertEquals(response.hashCode(), value);
        response.setUsername(NAME);
        value = isNotBlank(response.getUsername()) ? response.getUsername().hashCode() : 0;
        assertEquals(response.hashCode(), value);
        response.setText(TEXT);
        value += isNotBlank(response.getText()) ? response.getText().hashCode() : 0;
        assertEquals(response.hashCode(), value);
    }

    @Test
    @Override
    public void hashCodeValidObject() {
        final Response response = getResponse();
        for (int i = 0; i < 10; i++) {
            assertEquals(
                    response.hashCode(),
                    (
                            isNotBlank(response.getUsername()) ?
                                    response.getUsername().hashCode() : 0
                    ) + (
                            isNotBlank(response.getText()) ?
                                    response.getText().hashCode() : 0
                    )
            );
        }
    }

    @Test
    public void toStringTest() {
        final Response response = getResponse();
        final String value = "Date: " + response.getDate()
                + " \nUsername: " + response.getUsername()
                + " \nText: " + response.getText();
        assertEquals(response.toString(), value);
    }

    @Test
    public void whenInitializeObjectWithNullParametersThenGetNull() {
        final Response response = new Response();
        response.initialize(null, null);
        assertNull(response.getUsername());
        assertNull(response.getText());
        assertNotNull(response.getDate());
    }

    @Test
    public void whenInitializeObjectWithBlankParametersThenGetNull_1() {
        final Response response = new Response();
        response.initialize("", "");
        assertNull(response.getUsername());
        assertNull(response.getText());
        assertNotNull(response.getDate());
    }

    @Test
    public void whenInitializeObjectWithBlankParametersThenGetNull_2() {
        final Response response = new Response();
        response.initialize(" ", " ");
        assertNull(response.getUsername());
        assertNull(response.getText());
        assertNotNull(response.getDate());
    }

    @Test
    public void whenInitializeObjectWithBlankParametersThenGetNull_3() {
        final Response response = new Response();
        response.initialize("   ", "   ");
        assertNull(response.getUsername());
        assertNull(response.getText());
        assertNotNull(response.getDate());
    }

    @Test
    public void whenInitializeObjectWithValidParametersThenGetThisValue() {
        final Response response = new Response();
        response.initialize(NAME, TEXT);
        assertNotNull(response.getUsername());
        assertNotNull(response.getText());
        assertNotNull(response.getDate());
        assertEquals(response.getUsername(), NAME);
        assertEquals(response.getText(), TEXT);
    }

    @Test
    public void whenSetNullUsernameThenGetNull() {
        final Response response = getResponse();
        response.setUsername(null);
        assertNull(response.getUsername());
    }

    @Test
    public void whenSetBlankUsernameThenGetNull() {
        final Response response = getResponse();
        response.setUsername("");
        assertNull(response.getUsername());
        response.setUsername(" ");
        assertNull(response.getUsername());
        response.setUsername("   ");
        assertNull(response.getUsername());
    }

    @Test
    public void whenSetValidUsernameThenGetThisUsername() {
        final Response response = getResponse();
        response.setUsername(NAME);
        assertNotNull(response.getUsername());
        assertEquals(response.getUsername(), NAME);
    }

    @Test
    public void whenSetNullTextThenGetNull() {
        final Response response = getResponse();
        response.setText(null);
        assertNull(response.getText());
    }

    @Test
    public void whenSetBlankTextThenGetNull() {
        final Response response = getResponse();
        response.setText("");
        assertNull(response.getText());
        response.setText(" ");
        assertNull(response.getText());
        response.setText("   ");
        assertNull(response.getText());
    }

    @Test
    public void whenSetValidTextThenGetThisText() {
        final Response response = getResponse();
        response.setText(TEXT);
        assertNotNull(response.getText());
        assertEquals(response.getText(), TEXT);
    }

    @Test
    public void whenSetNullDateThenGetNewDate() {
        final Response response = getResponse();
        response.setDate(null);
        assertNotNull(response.getDate());
        assertNotNull(response.getDateToString());
    }

    @Test
    public void whenSetNotNullDateThenGetThisDate() {
        final Response response = getResponse();
        final Date date = new Date();
        response.setDate(date);
        assertNotNull(response.getDate());
        assertNotNull(response.getDateToString());
        assertEquals(response.getDate(), date);
    }

    @Test
    public void whenCallStaticResponseIsValidatedToNullThenReturnsFalse() {
        assertFalse(Response.isValidated(null));
    }

    @Test
    @Override
    public void validObject() {
        super.validObject();
        final Response response = getResponse();
        assertTrue(Response.isValidated(response));
        assertTrue(response.isValidated());
    }

    @Test
    public void whenSetNullUsernameThenSetNotResponseStaticValidated() {
        final Response response = getResponse();
        response.setUsername(null);
        assertFalse(Response.isValidated(response));
        assertTrue(response.isValidated());
    }

    @Test
    public void whenSetNullTextThenSetNotResponseStaticValidated() {
        final Response response = getResponse();
        response.setText(null);
        assertFalse(Response.isValidated(response));
        assertTrue(response.isValidated());
    }

    @Test
    public void whenSetTrueValidatedThenGetTrue() {
        final Response response = getResponse();
        response.setValidated(true);
        assertTrue(response.isValidated());
    }

    @Test
    public void whenReverseValidatedThenChangeValidated() {
        final Response response = new Response();
        response.setValidated(false);
        assertFalse(response.isValidated());
        response.reverseValidated();
        assertTrue(response.isValidated());
    }

    @Ignore
    @Override
    protected Response getObject() {
        return getResponse();
    }
}
