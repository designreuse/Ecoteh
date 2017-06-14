package ua.com.ecoteh.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.MockConstants.TEXT;

public final class DuplicateExceptionTest {

    @Test
    public void duplicateExceptionConstructor_1() throws Exception {
        final Exception exception = new DuplicateException();
        final String message = "";
        checkException(exception, message);
    }

    @Test
    public void duplicateExceptionConstructor_2() throws Exception {
        final Exception exception = new DuplicateException(TEXT);
        final String message = TEXT;
        checkException(exception, message);
    }

    @Test
    public void duplicateExceptionConstructor_3() throws Exception {
        final Exception another = new DuplicateException(TEXT);
        final Exception exception = new DuplicateException(TEXT, another);
        final String message = TEXT;
        checkException(exception, message);
    }
    @Test
    public void duplicateExceptionConstructor_4() throws Exception {
        final Exception another = new DuplicateException(TEXT);
        final Exception exception = new DuplicateException(another);
        final String message = DuplicateException.class.getName() + ": " + TEXT;
        checkException(exception, message);
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
        final Exception another = new DuplicateException(TEXT);
        throw new DuplicateException(TEXT, another);
    }

    @Test(expected = DuplicateException.class)
    public void throwDuplicateExceptionWithThrowable() {
        final Exception another = new DuplicateException(TEXT);
        throw new DuplicateException(another);
    }

    private void checkException(final Exception ex, final String message) {
        assertNotNull(ex);
        if (!message.isEmpty()) {
            assertEquals(ex.getMessage(), message);
        }
    }
}
