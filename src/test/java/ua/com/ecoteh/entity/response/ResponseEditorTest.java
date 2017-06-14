package ua.com.ecoteh.entity.response;

import org.junit.Before;
import org.junit.Test;
import ua.com.ecoteh.entity.model.ModelEditorTest;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.MockConstants.NAME;
import static ua.com.ecoteh.mocks.MockConstants.TEXT;
import static ua.com.ecoteh.mocks.enity.MockModels.getResponse;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ResponseEditorTest extends ModelEditorTest<Response> {

    private ResponseEditor editor;
    private Response response;

    @Before
    public void beforeTests() {
        this.response = getResponse();
        this.editor = new ResponseEditor(this.response);
    }

    @Test
    @Override
    public void whenBuildEmptyThenReturnValidModel() {
        super.whenBuildEmptyThenReturnValidModel();
        final Response updatedResponse = this.editor.update();
        assertEquals(updatedResponse.getUsername(), this.response.getUsername());
        assertEquals(updatedResponse.getText(), this.response.getText());
        assertEquals(updatedResponse.getDate(), this.response.getDate());
    }

    @Test
    public void whenAddTextThenBuildWithIt() {
        Response response;
        String text;
        for (int i = 0; i < 5; i++) {
            text = TEXT + i;
            this.editor.addText(text);
            response = this.editor.update();
            assertEquals(response.getText(), text);
        }
    }

    @Test
    public void whenAddNullTextThenBuildWithEmptyIt() {
        this.editor.addText(null);
        final Response response = this.editor.update();
        assertEquals(response.getText(), this.response.getText());
    }

    @Test
    public void whenAddUsernameThenBuildWithIt() {
        Response response;
        String username;
        for (int i = 0; i < 5; i++) {
            username = NAME + i;
            this.editor.addUsername(username);
            response = this.editor.update();
            assertEquals(response.getUsername(), username);
        }
    }

    @Test
    public void whenAddNullUsernameThenBuildWithEmptyIt() {
        this.editor.addUsername(null);
        final Response response = this.editor.update();
        assertEquals(response.getUsername(), this.response.getUsername());
    }

    @Test
    public void whenAddDateThenBuildWithIt() {
        Response response;
        Date date;
        for (int i = 0; i < 5; i++) {
            date = new Date();
            this.editor.addDate(date);
            response = this.editor.update();
            assertEquals(response.getDate(), date);
        }
    }

    @Test
    public void whenAddNullDateThenBuildWithNewIt() {
        this.editor.addDate(null);
        final Response response = this.editor.update();
        assertEquals(response.getDate(), this.response.getDate());
    }

    @Override
    protected ResponseEditor getEditor() {
        return this.editor;
    }

    @Override
    protected Response getModel() {
        return this.response;
    }
}