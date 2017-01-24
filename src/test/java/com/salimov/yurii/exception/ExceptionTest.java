package com.salimov.yurii.exception;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static com.salimov.yurii.mocks.MockConstants.TEXT;

public final class ExceptionTest {

    @Test
    public void duplicateExceptionConstructors() throws Exception {
        checkException(
                new DuplicateException(), ""
        );
        checkException(
                new DuplicateException(TEXT),
                TEXT
        );
        checkException(
                new DuplicateException(
                        TEXT,
                        new DuplicateException(TEXT)
                ),
                TEXT
        );
        assertNotNull(
                new DuplicateException(
                        new DuplicateException(TEXT)
                )
        );
    }

    @Test(expected = DuplicateException.class)
    public void throwDuplicateException() {
        throw new DuplicateException();
    }

    @Test(expected = DuplicateException.class)
    public void throwDuplicateExceptionWithMessage() {
        throw new DuplicateException(TEXT);
    }

    @Test(expected = DuplicateException.class)
    public void throwDuplicateExceptionWithMessageAndThrowable() {
        throw new DuplicateException(
                TEXT,
                new DuplicateException(TEXT)
        );
    }

    @Test(expected = DuplicateException.class)
    public void throwDuplicateExceptionWithThrowable() {
        throw new DuplicateException(
                new DuplicateException(TEXT)
        );
    }

    @Test
    public void disableExceptionConstructors() throws Exception {
        checkException(
                new DisableException(), ""
        );
        checkException(
                new DisableException(TEXT), TEXT
        );
        checkException(
                new DisableException(
                        TEXT,
                        new DisableException(TEXT)
                ),
                TEXT
        );
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

    @Ignore
    public static void checkException(
            final Exception ex,
            final String message
    ) {
        assertNotNull(ex);
        if (!message.isEmpty()) {
            assertEquals(
                    ex.getMessage(),
                    message
            );
        }
    }
}
