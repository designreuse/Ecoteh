package ua.com.ecoteh.util.translator;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * The class implements a set of methods for translate
 * to ASCII and from ASCII.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class AsciiImpl implements Ascii {

    /**
     * The string to translate.
     */
    private String value;

    /**
     * Constructor.
     *
     * @param value the string to translate.
     */
    public AsciiImpl(final String value) {
        setValue(value);
    }

    /**
     * Constructor.
     *
     * @param value the string to translate.
     */
    public AsciiImpl(final int value) {
        this(Integer.toString(value));
    }

    /**
     * Translates value to ASCII.
     * If value is blank then returns empty string.
     *
     * @return The translated string or empty string.
     */
    @Override
    public String to() {
        String result = "";
        if (isNotEmpty(this.value)) {
            final StringBuilder sb = new StringBuilder();
            final char[] charArray = getValueChars();
            for (int i = 0; i < charArray.length; i++) {
                sb.append(charToInt(charArray[i]));
                if (i != charArray.length - 1) {
                    sb.append(",");
                }
            }
            result = sb.toString();
        }
        return result;
    }

    /**
     * Translates value from ASCII.
     * If value is blank then returns empty string.
     *
     * @return The translated string or empty string.
     */
    @Override
    public String from() {
        String result = "";
        if (isNotEmpty(this.value)) {
            try {
                final StringBuilder sb = new StringBuilder();
                for (String number : this.value.split(",")) {
                    sb.append(numberToChar(number));
                }
                result = sb.toString();
            } catch (NumberFormatException ex) {
                result = "";
            }
        }
        return result;
    }

    /**
     * Sets a string to translate.
     *
     * @param value the string to translate.
     */
    @Override
    public void setValue(final String value) {
        this.value = isNotEmpty(value) ? value : "";
    }

    /**
     * Sets a integer to translate.
     *
     * @param value the integer to translate.
     */
    @Override
    public void setValue(final int value) {
        setValue(Integer.toString(value));
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

    /**
     * Converts this value to a new character array and returns it.
     *
     * @return The char array.
     */
    private char[] getValueChars() {
        return this.value.toCharArray();
    }

    /**
     * Parses the incoming char to integer.
     *
     * @param character the char to parse
     * @return the integer.
     */
    private int charToInt(final char character) {
        return (int) character;
    }

    /**
     * Parses the incoming number to char.
     *
     * @param number the string number to parse (newer null).
     * @return The char.
     */
    private char numberToChar(final String number) {
        return (char) Integer.parseInt(number);
    }
}
