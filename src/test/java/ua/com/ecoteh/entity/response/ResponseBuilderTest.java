package ua.com.ecoteh.entity.response;

import org.junit.Before;
import org.junit.Test;
import ua.com.ecoteh.entity.model.ModelBuilderTest;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static ua.com.ecoteh.mocks.MockConstants.NAME;
import static ua.com.ecoteh.mocks.MockConstants.TEXT;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ResponseBuilderTest extends ModelBuilderTest<Response> {

    private ResponseBuilder builder;

    @Before
    public void beforeTests() {
        this.builder = new ResponseBuilder();
    }

    @Test
    @Override
    public void whenBuildEmptyThenReturnValidModel() {
        super.whenBuildEmptyThenReturnValidModel();
        final Response response = this.builder.build();
        assertNotNull(response.getUsername());
        assertNotNull(response.getText());
        assertNotNull(response.getDate());
    }

    @Test
    public void whenAddTextThenBuildWithIt() {
        Response response;
        String text;
        for (int i = 0; i < 5; i++) {
            text = TEXT + i;
            this.builder.addText(text);
            response = this.builder.build();
            assertEquals(response.getText(), text);
        }
    }

    @Test
    public void whenAddNullTextThenBuildWithEmptyIt() {
        this.builder.addText(null);
        final Response response = this.builder.build();
        assertTrue(response.getText().isEmpty());
    }

    @Test
    public void whenAddUsernameThenBuildWithIt() {
        Response response;
        String username;
        for (int i = 0; i < 5; i++) {
            username = NAME + i;
            this.builder.addUsername(username);
            response = this.builder.build();
            assertEquals(response.getUsername(), username);
        }
    }

    @Test
    public void whenAddNullUsernameThenBuildWithEmptyIt() {
        this.builder.addUsername(null);
        final Response response = this.builder.build();
        assertTrue(response.getUsername().isEmpty());
    }

    @Test
    public void whenAddDateThenBuildWithIt() {
        Response response;
        Date date;
        for (int i = 0; i < 5; i++) {
            date = new Date();
            this.builder.addDate(date);
            response = this.builder.build();
            assertEquals(response.getDate(), date);
        }
    }

    @Test
    public void whenAddNullDateThenBuildWithNewIt() {
        this.builder.addDate(null);
        final Response response = this.builder.build();
        assertNotNull(response.getDate());
    }

    @Override
    protected ResponseBuilder getBuilder() {
        return this.builder;
    }
}
