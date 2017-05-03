package ua.com.ecoteh.util.compressor;

import com.googlecode.htmlcompressor.compressor.Compressor;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * The abstract class implements a set of methods for compressing data.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public abstract class AbstractCompressor implements Compressor {

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
     * @return Compressed result.
     */
    @Override
    public String compress(final String source) {
        String result = "";
        if (isNotEmpty(source)) {
            result = getCompressor().compress(source);
        }
        return result;
    }

    /**
     * Returns Compressor object.
     *
     * @return The compressor instance.
     */
    protected abstract Compressor getCompressor();
}
