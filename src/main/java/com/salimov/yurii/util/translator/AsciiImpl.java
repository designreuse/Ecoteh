package com.salimov.yurii.util.translator;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class implements a set of methods for translate to ASCII and from ASCII.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Ascii
 */
public final class AsciiImpl implements Ascii {

    /**
     * The string to translate.
     */
    private String value;

    /**
     * @param value a string to translate.
     */
    public AsciiImpl(final String value) {
        this.value = value;
    }

    /**
     * @param value a string to translate.
     */
    public AsciiImpl(final int value) {
        this(
                Integer.toString(value)
        );
    }

    /**
     * Translates value to ASCII.
     * If value is blank then returns empty string.
     *
     * @return The translated string or empty string.
     */
    @Override
    public String to() {
        final StringBuilder sb = new StringBuilder();
        if (isNotBlank(this.value)) {
            for (Character character : this.value.toCharArray()) {
                sb.append((int) character).append(",");
            }
        }
        final String result = sb.toString();
        return result.substring(0, result.length() - 1);
    }

    /**
     * Translates value from ASCII.
     * If value is blank then returns empty string.
     *
     * @return The translated string or empty string.
     */
    @Override
    public String from() {
        StringBuilder sb = new StringBuilder();
        if (isNotBlank(this.value)) {
            for (String st : this.value.split(",")) {
                sb.append(
                        Character.toString(
                                (char) Integer.parseInt(st)
                        )
                );
            }
        }
        return sb.toString();
    }

    /**
     * Sets a string to translate.
     *
     * @param value a string to translate.
     */
    @Override
    public void setValue(final String value) {
        this.value = value;
    }

    /**
     * Sets a integer to translate.
     *
     * @param value a integer to translate.
     */
    @Override
    public void setValue(final int value) {
        setValue(
                Integer.toString(value)
        );
    }

    /**
     * Returns a string to translate.
     *
     * @return The string to translate.
     */
    @Override
    public String getValue() {
        return this.value;
    }
}
