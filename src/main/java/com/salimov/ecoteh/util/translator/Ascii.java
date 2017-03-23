package com.salimov.ecoteh.util.translator;

/**
 * The interface describes a set of methods
 * for translate to ASCII and from ASCII.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface Ascii {

    /**
     * Translates value to ASCII.
     * If value is blank then returns empty string.
     *
     * @return The translated string or empty string.
     */
    String to();

    /**
     * Translates value from ASCII.
     * If value is blank then returns empty string.
     *
     * @return The translated string or empty string.
     */
    String from();

    /**
     * Sets a string to translate.
     *
     * @param value a string to translate.
     */
    void setValue(final String value);

    /**
     * Sets a integer to translate.
     *
     * @param value a integer to translate.
     */
    void setValue(final int value);

    /**
     * Returns a string to translate.
     *
     * @return The string to translate.
     */
    String getValue();
}
