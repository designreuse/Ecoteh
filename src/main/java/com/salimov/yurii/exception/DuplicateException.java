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
    public DuplicateException(String message) {
        super(message);
    }
}
