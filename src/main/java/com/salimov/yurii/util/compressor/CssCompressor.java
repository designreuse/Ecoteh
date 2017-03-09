package com.salimov.yurii.util.compressor;

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
     * Instance describes compressor methods.
     */
    private Compressor compressor;

    /**
     * Constructor.
     */
    public CssCompressor() {
        this.compressor = new YuiCssCompressor();
    }

    /**
     * Returns Compressor object.
     *
     * @return The HTML compressor instance.
     */
    @Override
    protected Compressor getCompressor() {
        return this.compressor;
    }
}
