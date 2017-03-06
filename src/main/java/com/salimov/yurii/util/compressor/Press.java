package com.salimov.yurii.util.compressor;

import com.googlecode.htmlcompressor.compressor.Compressor;
import com.googlecode.htmlcompressor.compressor.HtmlCompressor;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The abstract class implements a set of methods for compressing data.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public abstract class Press implements Compressor {

    /**
     * Instance describes compressor methods.
     */
    private static Compressor compressor;

    /**
     * Compresses the given source and returns a compressed result.
     *
     * @param source The source to compress.
     * @return Compressed result.
     */
    @Override
    public String compress(final String source) {
        String result = "";
        if (isNotBlank(source)) {
            if (compressor == null) {
                compressor = initCompress();
            }
            result = compressor.compress(source);
        }
        return result;
    }

    /**
     * Initializes the Compressor object.
     */
    protected abstract Compressor initCompress();
}
