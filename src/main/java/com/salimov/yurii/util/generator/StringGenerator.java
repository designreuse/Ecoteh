package com.salimov.yurii.util.generator;

import java.util.Random;

/**
 * The class implements a set of methods for generating random string.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class StringGenerator implements Generator<String> {

    /**
     * An instance of Random class is used to generate
     * a stream of pseudorandom numbers.
     */
    private final static Random RANDOM;

    /**
     * Default pattern to generated new string.
     */
    private final static char[] DEFAULT_PATTERN;

    /**
     * Default length to generate string.
     */
    private final static long DEFAULT_LENGTH;

    /**
     * Pattern to generated new string.
     */
    private final char[] pattern;

    /**
     * Length to generate string.
     */
    private final long length;

    /**
     * Generated string.
     */
    private String string;

    /**
     * Static block.
     * Default pattern initialization.
     */
    static {
        RANDOM = new Random();
        DEFAULT_PATTERN = (
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                        "abcdefghijklmnopqrstuvwxyz" +
                        "0123456789"
        ).toCharArray();
        DEFAULT_LENGTH = 6;
    }

    /**
     * Default constructor.
     */
    public StringGenerator() {
        this(DEFAULT_PATTERN, DEFAULT_LENGTH);
    }

    /**
     * Constructor.
     *
     * @param length a length to generate string.
     */
    public StringGenerator(final long length) {
        this(DEFAULT_PATTERN, length);
    }

    /**
     * Constructor.
     *
     * @param pattern a pattern to generated new string.
     */
    public StringGenerator(final char[] pattern) {
        this(pattern, DEFAULT_LENGTH);
    }

    /**
     * Constructor.
     *
     * @param pattern a pattern to generated new string.
     * @param length  a length to generate string.
     */
    public StringGenerator(final char[] pattern, final long length) {
        if (pattern != null && pattern.length > 0) {
            this.pattern = pattern;
        } else {
            this.pattern = DEFAULT_PATTERN;
        }
        this.length = length > 0 ? length : DEFAULT_LENGTH;
    }

    /**
     * Generates random string.
     *
     * @return The generated string.
     */
    @Override
    public String generate() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.length; i++) {
            sb.append(getRandomChar());
        }
        this.string = sb.toString();
        return this.string;
    }

    /**
     * Returns generated string.
     * If string is null then calls method generate().
     *
     * @return The generated string.
     */
    @Override
    public String get() {
        if (this.string == null) {
            generate();
        }
        return this.string;
    }

    /**
     * Returns random char.
     *
     * @return The random char.
     */
    private char getRandomChar() {
        return this.pattern[RANDOM.nextInt(this.pattern.length)];
    }
}
