package com.salimov.yurii.exception;

import org.junit.Test;

import static com.salimov.yurii.mocks.MockConstants.TEXT;
import static org.junit.Assert.assertNotNull;

public class DisableExceptionTest extends ExceptionTest {

    @Test
    public void disableExceptionConstructor_1() throws Exception {
        checkException(
                new DisableException(), ""
        );
    }

    @Test
    public void disableExceptionConstructor_2() throws Exception {
        checkException(
                new DisableException(TEXT), TEXT
        );
    }

    @Test
    public void disableExceptionConstructor_3() throws Exception {
        checkException(
                new DisableException(
                        TEXT,
                        new DisableException(TEXT)
                ),
                TEXT
        );
    }

    @Test
    public void disableExceptionConstructor_4() throws Exception {
        assertNotNull(
                new DisableException(
                        new DisableException(TEXT)
                )
        );
    }

    @Test(expected = DisableException.class)
    public void throwDisableExceptionTest1() {
        throw new DisableException();
    }

    @Test(expected = DisableException.class)
    public void throwDisableExceptionWithMessage() {
        throw new DisableException(TEXT);
    }

    @Test(expected = DisableException.class)
    public void throwDisableExceptionWithMessageAndThrowable() {
        throw new DisableException(
                TEXT,
                new DisableException(TEXT)
        );
    }

    @Test(expected = DisableException.class)
    public void throwDisableExceptionWithThrowable() {
        throw new DisableException(
                new DisableException(TEXT)
        );
    }
}
