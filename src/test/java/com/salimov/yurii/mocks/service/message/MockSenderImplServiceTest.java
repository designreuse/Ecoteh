package com.salimov.yurii.mocks.service.message;

import com.salimov.yurii.service.sender.SenderService;
import org.junit.Test;

import static com.salimov.yurii.mocks.service.message.MockSenderService.getSenderService;
import static org.junit.Assert.assertNotNull;

public class MockSenderImplServiceTest {

    @Test
    public void whenGetMockServiceThenReturnNotNull() {
        SenderService senderService = getSenderService();
        assertNotNull(senderService);
    }
}
