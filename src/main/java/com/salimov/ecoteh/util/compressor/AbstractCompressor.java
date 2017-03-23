package com.salimov.ecoteh.util.compressor;

import com.googlecode.htmlcompressor.compressor.Compressor;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The abstract class implements a set of methods for compressing data.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public abstract class AbstractCompressor implements Compressor {

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
