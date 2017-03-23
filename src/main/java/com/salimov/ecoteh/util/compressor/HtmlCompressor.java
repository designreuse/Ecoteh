package com.salimov.ecoteh.util.compressor;

import com.googlecode.htmlcompressor.compressor.Compressor;

/**
 * The class implements a set of methods for compressing HTML data.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public final class HtmlCompressor extends AbstractCompressor implements Compressor {

    /**
     * Instance describes compressor methods.
     */
    private Compressor compressor;

    /**
     * Constructor.
     */
    public HtmlCompressor() {
        final com.googlecode.htmlcompressor.compressor.HtmlCompressor compressor =
                new com.googlecode.htmlcompressor.compressor.HtmlCompressor();
        compressor.setRemoveIntertagSpaces(true);
        this.compressor = compressor;
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
