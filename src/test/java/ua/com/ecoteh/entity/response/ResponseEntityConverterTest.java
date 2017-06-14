package ua.com.ecoteh.entity.response;

import org.junit.BeforeClass;
import ua.com.ecoteh.entity.model.ModelEntityConverterTest;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getResponseEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ResponseEntityConverterTest extends ModelEntityConverterTest<ResponseEntity, Response> {

    private static ResponseEntityConverter converter;
    private static ResponseEntity entity;

    @BeforeClass
    public static void beforeClass() {
        entity = getResponseEntity();
        converter = new ResponseEntityConverter(entity);
    }

    @Override
    protected void checkEntity(final Response response) {
        super.checkEntity(response);
        assertEquals(response.getUsername(), entity.getUsername());
        assertEquals(response.getText(), entity.getText());
        assertEquals(response.getDate(), entity.getDate());
    }

    @Override
    protected ResponseEntityConverter getConverter() {
        return converter;
    }

    @Override
    protected ResponseEntity getEntity() {
        return entity;
    }
}