package ua.com.ecoteh.entity.response;

import org.junit.Before;
import org.junit.Test;
import ua.com.ecoteh.entity.model.ModelEntityTest;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.MockConstants.*;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ResponseEntityTest extends ModelEntityTest {

    private ResponseEntity response;

    @Before
    public void beforeTests() {
        this.response = new ResponseEntity();
        this.response.setId(ID);
        this.response.setValidated(VALIDATION);
        this.response.setUsername(NAME);
        this.response.setText(TEXT);
        this.response.setDate(DATE);
    }

    @Test
    @Override
    public void toStringTest() {
        final String toStringTest = "ResponseEntity{" +
                "ModelEntity{" +
                "id=" + this.response.getId() +
                ", validated=" + this.response.isValidated() +
                '}' +
                ", username='" + this.response.getUsername() + '\'' +
                ", text='" + this.response.getText() + '\'' +
                ", date=" + this.response.getDate() +
                '}';
        assertEquals(this.response.toString(), toStringTest);
    }

    @Test
    public void whenSetUsernameThenGetIt() {
        String username;
        for (int i = 0; i < 5; i++) {
            username = NAME + i;
            this.response.setUsername(username);
            assertEquals(this.response.getUsername(), username);
        }
    }

    @Test
    public void whenSetTextThenGetIt() {
        String text;
        for (int i = 0; i < 5; i++) {
            text = TEXT + i;
            this.response.setText(text);
            assertEquals(this.response.getText(), text);
        }
    }

    @Test
    public void whenSetDateThenGetIt() {
        Date date;
        for (int i = 0; i < 5; i++) {
            date = new Date();
            this.response.setDate(date);
            assertEquals(this.response.getDate(), date);
        }
    }

    @Test
    public void whenConvertThenReturnValidModel() {
        final Response response = this.response.convert();
        assertEquals(response.getId(), this.response.getId());
        assertEquals(response.isValidated(), this.response.isValidated());
        assertEquals(response.getUsername(), this.response.getUsername());
        assertEquals(response.getText(), this.response.getText());
        assertEquals(response.getDate(), this.response.getDate());
    }

    @Override
    protected ResponseEntity getInstance() {
        return this.response;
    }
}
