package com.salimov.ecoteh.util.translator;

/**
 * The interface describes a set of methods for translate to Latin.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface ToLatin {

    /**
     * Translates value from cyrillic to latin.
     * If value is blank then returns {@code null}.
     *
     * @return The translated string or {@code null}.
     */
    String fromCyrillic();

    /**
     * Returns a string to translate.
     *
     * @return The string to translate.
     */
    String getValue();
}
