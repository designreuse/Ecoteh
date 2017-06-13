package ua.com.ecoteh.entity.response;

import org.junit.BeforeClass;
import ua.com.ecoteh.entity.model.ModelConverterTest;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.enity.MockModels.getResponse;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ResponseConverterTest extends ModelConverterTest<Response, ResponseEntity> {

    private static ResponseConverter converter;
    private static Response response;

    @BeforeClass
    public static void beforeClass() {
        response = getResponse();
        converter = new ResponseConverter(response);
    }

    @Override
    protected void checkEntity(final ResponseEntity entity) {
        super.checkEntity(entity);
        assertEquals(entity.getUsername(), response.getUsername());
        assertEquals(entity.getText(), response.getText());
        assertEquals(entity.getDate(), response.getDate());
    }

    @Override
    protected ResponseConverter getConverter() {
        return converter;
    }

    @Override
    protected Response getModel() {
        return response;
    }
}