package com.salimov.yurii.exception;

/**
 * An exception is thrown when site is disabled.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 */
public final class DisableException extends RuntimeException {

    /** Constructs a new disable exception with {@code null} as its
     * detail sender.
     */
    public DisableException() {
    }

    /**
     * Constructs a new disable exception with the specified detail sender.
     *
     * @param message the detail sender. The detail sender is saved for
     *          later retrieval by the getMessage() method.
     */
    public DisableException(String message) {
        super(message);
    }
}
