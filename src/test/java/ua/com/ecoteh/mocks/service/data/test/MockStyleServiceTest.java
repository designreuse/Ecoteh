package ua.com.ecoteh.mocks.service.data.test;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.service.data.StyleService;

import static org.junit.Assert.assertFalse;
import static ua.com.ecoteh.mocks.service.data.MockServices.getStyleService;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MockStyleServiceTest {

    private static StyleService service;

    @BeforeClass
    public static void beforeClass() {
        service = getStyleService();
    }

    @Test
    public void whenGetThenReturnNotEmpty() {
        final String styles = service.get();
        assertFalse(styles.isEmpty());
    }
}
