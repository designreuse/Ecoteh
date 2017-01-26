package com.salimov.yurii.exception;

import org.junit.Ignore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public abstract class ExceptionTest {

    @Ignore
    protected static void checkException(
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
