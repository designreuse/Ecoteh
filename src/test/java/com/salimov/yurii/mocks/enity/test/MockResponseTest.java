package com.salimov.yurii.mocks.enity.test;

import org.junit.Ignore;
import org.junit.Test;
import com.salimov.yurii.entity.Response;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static com.salimov.yurii.mocks.enity.MockEntity.getResponse;
import static com.salimov.yurii.mocks.enity.MockEntity.getResponses;

public final class MockResponseTest extends MockModelTest<Response> {

    @Test
    @Override
    public void whenGetModelThenReturnValidModel() {
        Response response = getResponse();
        assertNotNull(response.getUsername());
        assertNotNull(response.getDate());
        assertNotNull(response.getDateToString());
        assertNotNull(response.getText());
        assertNotNull(response.getId());
    }

    @Ignore
    @Override
    protected Response getObject() {
        return getResponse();
    }

    @Ignore
    @Override
    protected Collection<Response> getObjects() {
        return getResponses();
    }
}
