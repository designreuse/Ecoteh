package com.salimov.yurii.util.worktime;

/**
 * The interface describes a set of methods
 * for working with time string.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @see Time
 */
public interface ITime {

    /**
     * Returns an input time.
     *
     * @return The input time.
     */
    String getTime();

    /**
     * Returns correct time.
     *
     * @return The correct time.
     */
    String getCorrectTime();


    /**
     * Returns a correct hours of the time.
     *
     * @return The value of a correct minutes.
     */
    int getHours();

    /**
     * Returns a correct minutes of the time.
     *
     * @return The value of a correct minutes.
     */
    int getMinutes();

    /**
     * Returns the value of input time is not blank.
     *
     * @return {@code true} if the input time is not blank,
     * {@code false} otherwise.
     */
    boolean isNotBlankTime();
}
