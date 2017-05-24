package ua.com.ecoteh.util.translator;

/**
 * The interface describes a set of methods
 * for translate to ASCII and from ASCII.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
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
     * @param value the string to translate.
     */
    void setValue(String value);

    /**
     * Sets a integer to translate.
     *
     * @param value the integer to translate.
     */
    void setValue(int value);

    /**
     * Returns a string to translate.
     *
     * @return The string to translate.
     */
    String getValue();
}
