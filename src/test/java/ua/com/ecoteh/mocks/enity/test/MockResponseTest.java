package ua.com.ecoteh.mocks.enity.test;

import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.Response;
import ua.com.ecoteh.mocks.enity.MockEntity;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;

public final class MockResponseTest extends MockModelTest<Response> {

    @Test
    @Override
    public void whenGetModelThenReturnValidModel() {
        final Response response = MockEntity.getResponse();
        assertNotNull(response.getUsername());
        assertNotNull(response.getDate());
        assertNotNull(response.getDateToString());
        assertNotNull(response.getText());
        assertNotNull(response.getId());
    }

    @Ignore
    @Override
    protected Response getObject() {
        return MockEntity.getResponse();
    }

    @Ignore
    @Override
    protected Collection<Response> getObjects() {
        return MockEntity.getResponses();
    }
}
