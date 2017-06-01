package ua.com.ecoteh.mocks.enity.test;

import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.response.ResponseEntity;
import ua.com.ecoteh.mocks.enity.MockEntity;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;

public final class MockResponseEntityTest extends MockModelTest<ResponseEntity> {

    @Test
    @Override
    public void whenGetModelThenReturnValidModel() {
        final ResponseEntity responseEntity = MockEntity.getResponseEntity();
        assertNotNull(responseEntity.getUsername());
        assertNotNull(responseEntity.getDate());
        assertNotNull(responseEntity.getDateToString());
        assertNotNull(responseEntity.getText());
        assertNotNull(responseEntity.getId());
    }

    @Ignore
    @Override
    protected ResponseEntity getObject() {
        return MockEntity.getResponseEntity();
    }

    @Ignore
    @Override
    protected Collection<ResponseEntity> getObjects() {
        return MockEntity.getResponseEntities();
    }
}
