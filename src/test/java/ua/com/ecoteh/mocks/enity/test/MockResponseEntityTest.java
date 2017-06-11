package ua.com.ecoteh.mocks.enity.test;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.response.ResponseEntity;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getResponseEntities;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getResponseEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MockResponseEntityTest extends MockModelEntityTest<ResponseEntity> {

    private static ResponseEntity response;
    private static Collection<ResponseEntity> responses;

    @BeforeClass
    public static void beforeClass() {
        response = getResponseEntity();
        responses = getResponseEntities();
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
    protected ResponseEntity getObject() {
        return response;
    }

    @Ignore
    @Override
    protected Collection<ResponseEntity> getObjects() {
        return responses;
    }
}
