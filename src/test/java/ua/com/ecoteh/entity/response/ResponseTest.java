package ua.com.ecoteh.entity.response;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.entity.model.Model;
import ua.com.ecoteh.entity.model.ModelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.MockConstants.*;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ResponseTest extends ModelTest {

    private static Response response;

    @BeforeClass
    public static void beforeClass() {
        response = new Response(ID, VALIDATION, NAME, TEXT, DATE);
    }

    @Test
    @Override
    public void toStringTest() throws Exception {
        final String testString = "Response{" +
                "Model{id=" + response.getId() +
                ", validated=" + response.isValidated() + '}' +
                ", username='" + response.getUsername() + '\'' +
                ", text='" + response.getText() + '\'' +
                ", date=" + response.getDate() +
                '}';
        assertEquals(response.toString(), testString);
    }

    @Test
    public void getUsername() throws Exception {
        assertFalse(response.getUsername().isEmpty());
    }

    @Test
    public void getText() throws Exception {
        assertFalse(response.getText().isEmpty());
    }

    @Test
    public void getDate() throws Exception {
        assertNotNull(response.getDate());
    }

    @Test
    public void getDateToString() throws Exception {
        assertFalse(response.getDateToString().isEmpty());
    }

    @Test
    @Override
    public void convert() throws Exception {
        super.convert();
        final ResponseEntity entity = response.convert();
        assertEquals(entity.getId(), response.getId());
        assertEquals(entity.isValidated(), response.isValidated());
        assertEquals(entity.getUsername(), response.getUsername());
        assertEquals(entity.getText(), response.getText());
        assertEquals(entity.getDate(), response.getDate());
    }

    @Test
    public void getBuilder() throws Exception {
        final ResponseBuilder builder = Response.getBuilder();
        assertNotNull(builder);
    }

    @Override
    protected Model getInstance() {
        return response;
    }
}
