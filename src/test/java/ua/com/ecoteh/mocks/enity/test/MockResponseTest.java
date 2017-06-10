package ua.com.ecoteh.mocks.enity.test;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.response.Response;
import ua.com.ecoteh.mocks.enity.MockModels;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public final class MockResponseTest extends MockModelTest<Response> {

    private static Response response;
    private static Collection<Response> responses;

    @BeforeClass
    public static void beforeClass() {
        response = MockModels.getResponse();
        responses = MockModels.getResponses();
    }

    @Test
    public void whenGetUsernameThenReturnNotEmpty() {
        assertFalse(response.getUsername().isEmpty());
    }

    @Test
    public void whenGetTextThenReturnNotEmpty() {
        assertFalse(response.getText().isEmpty());
    }

    @Test
    public void whenGetDateThenReturnNotNull() {
        assertNotNull(response.getDate());
    }

    @Ignore
    @Override
    protected Response getObject() {
        return response;
    }

    @Ignore
    @Override
    protected Collection<Response> getObjects() {
        return responses;
    }
}
