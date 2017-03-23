package com.salimov.ecoteh.exception;

import org.junit.Test;

import static com.salimov.ecoteh.mocks.MockConstants.TEXT;
import static org.junit.Assert.assertNotNull;

public final class DuplicateExceptionTest extends ExceptionTest {

    @Test
    public void duplicateExceptionConstructor_1() throws Exception {
        checkException(new DuplicateException(), "");
    }

    @Test
    public void duplicateExceptionConstructor_2() throws Exception {
        checkException(new DuplicateException(TEXT), TEXT);
    }

    @Test
    public void duplicateExceptionConstructor_3() throws Exception {
        checkException(
                new DuplicateException(
                        TEXT,
                        new DuplicateException(TEXT)
                ),
                TEXT
        );
    }
    @Test
    public void duplicateExceptionConstructor_4() throws Exception {
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
}
