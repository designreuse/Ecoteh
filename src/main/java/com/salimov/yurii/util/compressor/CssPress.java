package com.salimov.yurii.util.compressor;

import com.googlecode.htmlcompressor.compressor.Compressor;
import com.googlecode.htmlcompressor.compressor.YuiCssCompressor;

/**
 * The class implements a set of methods for compressing CSS data.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public final class CssPress extends Press implements Compressor {

    /**
     * Initializes the Compressor object.
     */
    @Override
    protected Compressor initCompress() {
        return new YuiCssCompressor();
    }
}
