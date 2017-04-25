package ua.com.ecoteh.mocks.service.message;

import ua.com.ecoteh.service.sender.SenderService;
import org.junit.Ignore;

import static org.mockito.Mockito.mock;

@Ignore
public final class MockSenderService {

    private static SenderService senderService;

    public static SenderService getSenderService() {
        if (senderService == null) {
            senderService = mock(SenderService.class);
        }
        return senderService;
    }
}
