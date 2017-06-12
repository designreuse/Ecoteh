package ua.com.ecoteh.mocks.service.data;

import ua.com.ecoteh.service.data.StyleService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.com.ecoteh.mocks.MockConstants.STYLE;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class MockStyleService {

    private final StyleService service;

    MockStyleService(){
        this.service = mock(StyleService.class);
    }

    StyleService create() {
        initGet();
        return this.service;
    }

    private void initGet() {
        when(this.service.get()).thenReturn(STYLE);
    }
}
