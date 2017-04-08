package com.salimov.ecoteh.util.compressor;

import com.googlecode.htmlcompressor.compressor.Compressor;
import com.googlecode.htmlcompressor.compressor.YuiCssCompressor;

/**
 * The class implements a set of methods for compressing CSS data.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public final class CssCompressor extends AbstractCompressor implements Compressor {

    /**
     * Returns Compressor object.
     *
     * @return The CSS compressor instance.
     */
    @Override
    protected Compressor getCompressor() {
        return new YuiCssCompressor();
    }
}
