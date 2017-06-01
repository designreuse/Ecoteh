package ua.com.ecoteh.entity;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.response.ResponseEntity;
import ua.com.ecoteh.mocks.MockConstants;
import ua.com.ecoteh.mocks.enity.MockEntity;

import java.util.Date;

import static org.junit.Assert.*;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

public final class ResponseEntityTest extends ModelTest<ResponseEntity> {

    @Test
    public void whenInitializeByConstructorThenSetNotNulDate() {
        final ResponseEntity responseEntity = new ResponseEntity(null, null);
        assertNotNull(responseEntity.getDate());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenEmptyString_1() {
        final ResponseEntity responseEntity = new ResponseEntity("", "");
        assertNotNull(responseEntity.getUsername());
        assertNotNull(responseEntity.getText());
        assertNotNull(responseEntity.getDate());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_2() {
        final ResponseEntity responseEntity = new ResponseEntity(" ", " ");
        assertNotNull(responseEntity.getUsername());
        assertNotNull(responseEntity.getText());
        assertNotNull(responseEntity.getDate());
    }

    @Test
    public void whenPassInvalidParametersInConstructorThenSaveEmptyString() {
        final ResponseEntity responseEntity = new ResponseEntity("   ", "   ");
        assertNotNull(responseEntity.getUsername());
        assertNotNull(responseEntity.getText());
        assertNotNull(responseEntity.getDate());
    }

    @Test
    public void whenPassValidParametersInConstructorThenSaveThisValues() {
        ResponseEntity responseEntity = new ResponseEntity();
        assertNotNull(responseEntity.getDate());
        responseEntity = new ResponseEntity(MockConstants.NAME, MockConstants.TEXT);
        assertNotNull(responseEntity.getUsername());
        assertNotNull(responseEntity.getText());
        assertNotNull(responseEntity.getDate());
        Assert.assertEquals(responseEntity.getUsername(), MockConstants.NAME);
        Assert.assertEquals(responseEntity.getText(), MockConstants.TEXT);
    }

    @Test
    public void equalsInvalidObjects() {
        final ResponseEntity responseEntity1 = new ResponseEntity();
        final ResponseEntity responseEntity2 = new ResponseEntity();
        assertTrue(responseEntity1.equals(responseEntity2));
        responseEntity1.setUsername(MockConstants.NAME);
        responseEntity2.setUsername(MockConstants.NAME);
        assertTrue(responseEntity1.equals(responseEntity2));
        responseEntity1.setText(MockConstants.TEXT);
        responseEntity2.setText(MockConstants.TEXT);
        assertTrue(responseEntity1.equals(responseEntity2));
    }

    @Test
    @Override
    public void equalsValidObjects() {
        super.equalsValidObjects();
        final ResponseEntity responseEntity1 = MockEntity.getResponseEntity();
        final ResponseEntity responseEntity2 = responseEntity1.clone();
        assertEquals(responseEntity1, responseEntity2);
        assertEquals(
                responseEntity1.equals(responseEntity2),
                responseEntity1.getUsername().equals(responseEntity2.getUsername()) &&
                        responseEntity1.getText().equals(responseEntity2.getText())
        );
    }

    @Test
    public void hashCodeInvalidObject() {
        final ResponseEntity responseEntity = new ResponseEntity();
        int value = 0;
        assertEquals(responseEntity.hashCode(), value);
        responseEntity.setUsername(MockConstants.NAME);
        value = isNotEmpty(responseEntity.getUsername()) ? responseEntity.getUsername().hashCode() : 0;
        assertEquals(responseEntity.hashCode(), value);
        responseEntity.setText(MockConstants.TEXT);
        value += isNotEmpty(responseEntity.getText()) ? responseEntity.getText().hashCode() : 0;
        assertEquals(responseEntity.hashCode(), value);
    }

    @Test
    @Override
    public void hashCodeValidObject() {
        final ResponseEntity responseEntity = MockEntity.getResponseEntity();
        for (int i = 0; i < 10; i++) {
            assertEquals(
                    responseEntity.hashCode(),
                    responseEntity.getUsername().hashCode() + responseEntity.getText().hashCode()
            );
        }
    }

    @Test
    public void toStringTest() {
        final ResponseEntity responseEntity = MockEntity.getResponseEntity();
        final String responseToString = "ResponseEntity{" +
                "ModelEntity{" +
                "id=" + responseEntity.getId() +
                ", validated=" + responseEntity.isValidated() +
                '}' +
                ", username='" + responseEntity.getUsername() + '\'' +
                ", text='" + responseEntity.getText() + '\'' +
                ", date=" + responseEntity.getDate() +
                '}';
        assertEquals(responseEntity.toString(), responseToString);
    }

    @Test
    public void whenSetNullUsernameThenGetEmptyString() {
        final ResponseEntity responseEntity = MockEntity.getResponseEntity();
        responseEntity.setUsername(null);
        assertNotNull(responseEntity.getUsername());
        assertEquals(responseEntity.getUsername(), "");
    }

    @Test
    public void whenSetBlankUsernameThenGetEmptyString() {
        final ResponseEntity responseEntity = MockEntity.getResponseEntity();
        responseEntity.setUsername("");
        assertNotNull(responseEntity.getUsername());
        responseEntity.setUsername(" ");
        assertNotNull(responseEntity.getUsername());
        responseEntity.setUsername("   ");
        assertNotNull(responseEntity.getUsername());
        assertEquals(responseEntity.getUsername(), "");
    }

    @Test
    public void whenSetValidUsernameThenGetThisUsername() {
        final ResponseEntity responseEntity = MockEntity.getResponseEntity();
        responseEntity.setUsername(MockConstants.NAME);
        assertNotNull(responseEntity.getUsername());
        Assert.assertEquals(responseEntity.getUsername(), MockConstants.NAME);
    }

    @Test
    public void whenSetNullTextThenGetEmptyString() {
        final ResponseEntity responseEntity = MockEntity.getResponseEntity();
        responseEntity.setText(null);
        assertNotNull(responseEntity.getText());
        assertEquals(responseEntity.getText(), "");
    }

    @Test
    public void whenSetBlankTextThenGetEmptyString() {
        final ResponseEntity responseEntity = MockEntity.getResponseEntity();
        responseEntity.setText("");
        assertNotNull(responseEntity.getText());
        responseEntity.setText(" ");
        assertNotNull(responseEntity.getText());
        responseEntity.setText("   ");
        assertNotNull(responseEntity.getText());
        assertEquals(responseEntity.getText(), "");
    }

    @Test
    public void whenSetValidTextThenGetThisText() {
        final ResponseEntity responseEntity = MockEntity.getResponseEntity();
        responseEntity.setText(MockConstants.TEXT);
        assertNotNull(responseEntity.getText());
        Assert.assertEquals(responseEntity.getText(), MockConstants.TEXT);
    }

    @Test
    public void whenSetNullDateThenGetNewDate() {
        final ResponseEntity responseEntity = MockEntity.getResponseEntity();
        responseEntity.setDate(null);
        assertNotNull(responseEntity.getDate());
        assertNotNull(responseEntity.getDateToString());
    }

    @Test
    public void whenSetNotNullDateThenGetThisDate() {
        final ResponseEntity responseEntity = MockEntity.getResponseEntity();
        final Date date = new Date();
        responseEntity.setDate(date);
        assertNotNull(responseEntity.getDate());
        assertNotNull(responseEntity.getDateToString());
        assertEquals(responseEntity.getDate(), date);
    }

    @Test
    public void whenSetTrueValidatedThenGetTrue() {
        final ResponseEntity responseEntity = MockEntity.getResponseEntity();
        responseEntity.setValidated(true);
        assertTrue(responseEntity.isValidated());
    }

    @Test
    public void whenReverseValidatedThenChangeValidated() {
        final ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setValidated(false);
        assertFalse(responseEntity.isValidated());
        responseEntity.reverseValidated();
        assertTrue(responseEntity.isValidated());
    }

    @Test
    public void whenInitializeByNullThenDoNothing() {
        final ResponseEntity model1 = getInstance();
        final ResponseEntity model2 = model1.initialize(null);
        assertEquals(model1, model2);
    }

    @Test
    public void whenInitializeByValidObjectThenCopyIt() {
        final ResponseEntity model1 = getInstance();
        final ResponseEntity model2 = getObject();
        model1.initialize(model2);
        assertEquals(model1, model2);
    }

    @Ignore
    @Override
    protected ResponseEntity getObject() {
        return MockEntity.getResponseEntity();
    }

    @Override
    protected ResponseEntity getInstance() {
        return new ResponseEntity();
    }
}
