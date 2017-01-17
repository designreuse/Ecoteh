package com.salimov.yurii.util.translator;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class implements a set of methods for translate to Latin.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see ToLatin
 */
public final class ToLatinImpl implements ToLatin {

    /**
     * The string to translate.
     */
    private final String value;

    /**
     * Constructor.
     *
     * @param value a string to translate.
     */
    public ToLatinImpl(String value) {
        this.value = value;
    }

    /**
     * Translates value from cyrillic to latin.
     *
     * @return The translated string (newer {@code null})..
     */
    @Override
    public String fromCyrillic() {
        StringBuilder sb = new StringBuilder();
        if (isNotBlank(this.value)) {
            for (char ch : convertToChars(this.value)) {
                sb.append(
                        translate(ch)
                );
            }
        }
        return sb.toString().replace("__", "_");
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
     * Converts an input value to chars array
     * and returns it.
     *
     * @param value a value to convert.
     * @return The chars array.
     */
    private static char[] convertToChars(final String value) {
        return value.toLowerCase().toCharArray();
    }

    /**
     * Translates char from cyrillic to latin.
     *
     * @param ch a char to translate.
     * @return The translated char.
     */
    private static String translate(char ch) {
        switch (ch) {
            case 'а':
                return "a";
            case 'б':
                return "b";
            case 'в':
                return "v";
            case 'г':
                return "g";
            case 'д':
                return "d";
            case 'е':
                return "e";
            case 'ё':
                return "je";
            case 'ж':
                return "zh";
            case 'з':
                return "z";
            case 'і':
            case 'и':
                return "i";
            case 'й':
                return "y";
            case 'к':
                return "k";
            case 'л':
                return "l";
            case 'м':
                return "m";
            case 'н':
                return "n";
            case 'о':
                return "o";
            case 'п':
                return "p";
            case 'р':
                return "r";
            case 'с':
                return "s";
            case 'т':
                return "t";
            case 'у':
                return "u";
            case 'ф':
                return "f";
            case 'х':
                return "kh";
            case 'ц':
                return "c";
            case 'ч':
                return "ch";
            case 'ш':
                return "sh";
            case 'щ':
                return "jsh";
            case 'ъ':
                return "hh";
            case 'ы':
                return "ih";
            case 'ь':
                return "jh";
            case 'э':
                return "eh";
            case 'ю':
                return "yu";
            case 'я':
                return "ja";
            case ' ':
            case '.':
            case ',':
            case '!':
            case '?':
            case '/':
            case '\\':
                return "_";
            default:
                return String.valueOf(ch);
        }
    }
}
