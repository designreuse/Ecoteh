package com.salimov.yurii.exception;

/**
 * An exception is thrown if data duplicated in the database.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 */
public final class DuplicateException extends RuntimeException {

    /** Constructs a new duplicate exception with {@code null} as its
     * detail sender.
     */
    public DuplicateException() {
    }

    /**
     * Constructs a new duplicate exception with the specified detail sender.
     *
     * @param message the detail sender. The detail sender is saved for
     *          later retrieval by the getMessage() method.
     */
    public DuplicateException(final String message) {
        super(message);
    }

    /**
     * Constructs a new runtime exception
     * with the specified detail message and cause.
     *
     * @param message the detail message
     *                (which is saved for later retrieval
     *                by the getMessage() method).
     * @param cause   the cause (which is saved for later retrieval
     *                by the getCause() method).
     */
    public DuplicateException(
            final String message,
            final Throwable cause
    ) {
        super(message, cause);
    }

    /**
     * Constructs a new runtime exception with the specified cause
     * and a detail message of (cause==null ? null : cause.toString())
     * (which typically contains the class and detail message of cause).
     *
     * @param cause the cause (which is saved for later retrieval
     *              by the getCause() method).
     */
    public DuplicateException(final Throwable cause) {
        super(cause);
    }
}
