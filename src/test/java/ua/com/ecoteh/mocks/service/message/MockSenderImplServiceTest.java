package ua.com.ecoteh.mocks.service.message;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MockSenderImplServiceTest {

    @Test
    public void whenGetMockServiceThenReturnNotNull() {
        assertNotNull(MockSenderService.getSenderService());
    }
}
