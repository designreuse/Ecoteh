package ua.com.ecoteh.util.generator;

import java.util.Random;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * The class implements a set of methods for generating random string.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public final class StringGenerator implements Generator<String> {

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
     * Static block.
     * Default pattern initialization.
     */
    static {
        RANDOM = new Random();
        DEFAULT_PATTERN = ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789").toCharArray();
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
     * @param length the length to generate string.
     */
    public StringGenerator(final long length) {
        this(DEFAULT_PATTERN, length);
    }

    /**
     * Constructor.
     *
     * @param pattern the pattern to generated new string.
     */
    public StringGenerator(final char[] pattern) {
        this(pattern, DEFAULT_LENGTH);
    }

    /**
     * Constructor.
     *
     * @param pattern the pattern to generated new string.
     * @param length  the length to generate string.
     */
    public StringGenerator(final char[] pattern, final long length) {
        if (isNotEmpty(pattern)) {
            this.pattern = pattern;
        } else {
            this.pattern = DEFAULT_PATTERN;
        }
        this.length = (length > 0) ? length : DEFAULT_LENGTH;
    }

    /**
     * Generates random string.
     *
     * @return The generated string (newer null).
     */
    @Override
    public String generate() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.length; i++) {
            sb.append(getRandomChar());
        }
        return sb.toString();
    }

    /**
     * Returns random char.
     *
     * @return The random char.
     */
    private char getRandomChar() {
        return this.pattern[getRandomInt()];
    }

    /**
     * Returns random integer.
     *
     * @return The random integer.
     */
    private int getRandomInt() {
        return RANDOM.nextInt(this.pattern.length);
    }
}
