package com.salimov.yurii.mocks.service.message;

import org.junit.Test;

import static com.salimov.yurii.mocks.service.message.MockSenderService.getSenderService;
import static org.junit.Assert.assertNotNull;

public class MockSenderImplServiceTest {

    @Test
    public void whenGetMockServiceThenReturnNotNull() {
        assertNotNull(getSenderService());
    }
}
