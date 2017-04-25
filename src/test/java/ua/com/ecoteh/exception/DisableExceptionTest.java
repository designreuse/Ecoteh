package ua.com.ecoteh.exception;

import org.junit.Test;
import ua.com.ecoteh.mocks.MockConstants;

import static org.junit.Assert.assertNotNull;

public class DisableExceptionTest extends ExceptionTest {

    @Test
    public void disableExceptionConstructor_1() throws Exception {
        checkException(new DisableException(), "");
    }

    @Test
    public void disableExceptionConstructor_2() throws Exception {
        checkException(new DisableException(MockConstants.TEXT), MockConstants.TEXT);
    }

    @Test
    public void disableExceptionConstructor_3() throws Exception {
        checkException(
                new DisableException(
                        MockConstants.TEXT,
                        new DisableException(MockConstants.TEXT)
                ),
                MockConstants.TEXT
        );
    }

    @Test
    public void disableExceptionConstructor_4() throws Exception {
        assertNotNull(
                new DisableException(
                        new DisableException(MockConstants.TEXT)
                )
        );
    }

    @Test(expected = DisableException.class)
    public void throwDisableExceptionTest1() {
        throw new DisableException();
    }

    @Test(expected = DisableException.class)
    public void throwDisableExceptionWithMessage() {
        throw new DisableException(MockConstants.TEXT);
    }

    @Test(expected = DisableException.class)
    public void throwDisableExceptionWithMessageAndThrowable() {
        throw new DisableException(
                MockConstants.TEXT,
                new DisableException(MockConstants.TEXT)
        );
    }

    @Test(expected = DisableException.class)
    public void throwDisableExceptionWithThrowable() {
        throw new DisableException(
                new DisableException(MockConstants.TEXT)
        );
    }
}
