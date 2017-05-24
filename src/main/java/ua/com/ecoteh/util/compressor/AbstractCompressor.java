package ua.com.ecoteh.util.compressor;

import com.googlecode.htmlcompressor.compressor.Compressor;

import static ua.com.ecoteh.exception.ExceptionMessage.INCOMING_OBJECT_IS_NULL_MESSAGE;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNull;

/**
 * The abstract class implements a set of methods for compressing data.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
abstract class AbstractCompressor implements Compressor {

    /**
     * The instance of the Compressor class.
     */
    private final Compressor compressor;

    /**
     * Constructor.
     *
     * @param compressor the instance of the Compressor class.
     * @throws IllegalArgumentException Throw exception when incoming compressor is null.
     */
    AbstractCompressor(final Compressor compressor) throws IllegalArgumentException {
        if (isNull(compressor)) {
            throw new IllegalArgumentException(
                    String.format(
                            INCOMING_OBJECT_IS_NULL_MESSAGE,
                            Compressor.class.getSimpleName()
                    )
            );
        }
        this.compressor = compressor;
    }

    /**
     * Compresses the incoming source and returns a compressed result.
     * Compresses the source if it not null and not empty.
     * <pre>
     *     compress(null) = "" (empty string)
     *     compress("") = "" (empty string)
     *     compress(" ") = "" (empty string)
     *     compress("bob") = "bob"
     *     compress("bob") = "bob"
     * </pre>
     *
     * @param source the source to compress.
     * @return Compressed result or empty string (newer null).
     */
    @Override
    public String compress(final String source) {
        String result = "";
        if (isNotEmpty(source)) {
            result = this.compressor.compress(source);
        }
        return result;
    }
}
