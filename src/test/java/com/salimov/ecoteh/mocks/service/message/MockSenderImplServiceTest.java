package com.salimov.ecoteh.mocks.service.message;

import org.junit.Test;

import static com.salimov.ecoteh.mocks.service.message.MockSenderService.getSenderService;
import static org.junit.Assert.assertNotNull;

public class MockSenderImplServiceTest {

    @Test
    public void whenGetMockServiceThenReturnNotNull() {
        assertNotNull(getSenderService());
    }
}
