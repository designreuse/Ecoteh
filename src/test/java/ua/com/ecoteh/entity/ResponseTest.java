package ua.com.ecoteh.entity;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.mocks.MockConstants;
import ua.com.ecoteh.mocks.enity.MockEntity;

import java.util.Date;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.junit.Assert.*;

public final class ResponseTest extends ModelTest<Response> {

    @Test
    public void whenInitializeByConstructorThenSetNotNulDate() {
        final Response response = new Response(null, null);
        assertNotNull(response.getDate());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenEmptyString_1() {
        final Response response = new Response("", "");
        assertNotNull(response.getUsername());
        assertNotNull(response.getText());
        assertNotNull(response.getDate());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_2() {
        final Response response = new Response(" ", " ");
        assertNotNull(response.getUsername());
        assertNotNull(response.getText());
        assertNotNull(response.getDate());
    }

    @Test
    public void whenPassInvalidParametersInConstructorThenSaveEmptyString() {
        final Response response = new Response("   ", "   ");
        assertNotNull(response.getUsername());
        assertNotNull(response.getText());
        assertNotNull(response.getDate());
    }

    @Test
    public void whenPassValidParametersInConstructorThenSaveThisValues() {
        Response response = new Response();
        assertNotNull(response.getDate());
        response = new Response(MockConstants.NAME, MockConstants.TEXT);
        assertNotNull(response.getUsername());
        assertNotNull(response.getText());
        assertNotNull(response.getDate());
        Assert.assertEquals(response.getUsername(), MockConstants.NAME);
        Assert.assertEquals(response.getText(), MockConstants.TEXT);
    }

    @Test
    public void equalsInvalidObjects() {
        final Response response1 = new Response();
        final Response response2 = new Response();
        assertTrue(response1.equals(response2));
        response1.setUsername(MockConstants.NAME);
        response2.setUsername(MockConstants.NAME);
        assertTrue(response1.equals(response2));
        response1.setText(MockConstants.TEXT);
        response2.setText(MockConstants.TEXT);
        assertTrue(response1.equals(response2));
    }

    @Test
    @Override
    public void equalsValidObjects() {
        super.equalsValidObjects();
        final Response response1 = MockEntity.getResponse();
        final Response response2 = response1.clone();
        assertEquals(response1, response2);
        assertEquals(
                response1.equals(response2),
                response1.getUsername().equals(response2.getUsername()) &&
                        response1.getText().equals(response2.getText())
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
        for (int i = 0; i < 10; i++) {
            assertEquals(
                    response.hashCode(),
                    response.getUsername().hashCode() + response.getText().hashCode()
            );
        }
    }

    @Test
    public void toStringTest() {
        final Response response = MockEntity.getResponse();
        final String responseToString = "Response{" +
                "Model{" +
                "id=" + response.getId() +
                ", validated=" + response.isValidated() +
                '}' +
                ", username='" + response.getUsername() + '\'' +
                ", text='" + response.getText() + '\'' +
                ", date=" + response.getDate() +
                '}';
        assertEquals(response.toString(), responseToString);
    }

    @Test
    public void whenSetNullUsernameThenGetEmptyString() {
        final Response response = MockEntity.getResponse();
        response.setUsername(null);
        assertNotNull(response.getUsername());
        assertEquals(response.getUsername(), "");
    }

    @Test
    public void whenSetBlankUsernameThenGetEmptyString() {
        final Response response = MockEntity.getResponse();
        response.setUsername("");
        assertNotNull(response.getUsername());
        response.setUsername(" ");
        assertNotNull(response.getUsername());
        response.setUsername("   ");
        assertNotNull(response.getUsername());
        assertEquals(response.getUsername(), "");
    }

    @Test
    public void whenSetValidUsernameThenGetThisUsername() {
        final Response response = MockEntity.getResponse();
        response.setUsername(MockConstants.NAME);
        assertNotNull(response.getUsername());
        Assert.assertEquals(response.getUsername(), MockConstants.NAME);
    }

    @Test
    public void whenSetNullTextThenGetEmptyString() {
        final Response response = MockEntity.getResponse();
        response.setText(null);
        assertNotNull(response.getText());
        assertEquals(response.getText(), "");
    }

    @Test
    public void whenSetBlankTextThenGetEmptyString() {
        final Response response = MockEntity.getResponse();
        response.setText("");
        assertNotNull(response.getText());
        response.setText(" ");
        assertNotNull(response.getText());
        response.setText("   ");
        assertNotNull(response.getText());
        assertEquals(response.getText(), "");
    }

    @Test
    public void whenSetValidTextThenGetThisText() {
        final Response response = MockEntity.getResponse();
        response.setText(MockConstants.TEXT);
        assertNotNull(response.getText());
        Assert.assertEquals(response.getText(), MockConstants.TEXT);
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
        assertEquals(response.getDate(), date);
    }

    @Test
    public void whenSetTrueValidatedThenGetTrue() {
        final Response response = MockEntity.getResponse();
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

    @Test
    public void whenInitializeByNullThenDoNothing() {
        final Response model1 = getInstance();
        final Response model2 = model1.initialize(null);
        assertEquals(model1, model2);
    }

    @Test
    public void whenInitializeByValidObjectThenCopyIt() {
        final Response model1 = getInstance();
        final Response model2 = getObject();
        model1.initialize(model2);
        assertEquals(model1, model2);
    }

    @Ignore
    @Override
    protected Response getObject() {
        return MockEntity.getResponse();
    }

    @Override
    protected Response getInstance() {
        return new Response();
    }
}
