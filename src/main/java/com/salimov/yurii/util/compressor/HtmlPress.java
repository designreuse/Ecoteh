package com.salimov.yurii.util.compressor;

import com.googlecode.htmlcompressor.compressor.Compressor;
import com.googlecode.htmlcompressor.compressor.HtmlCompressor;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class implements a set of methods for compressing HTML data.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public final class HtmlPress extends Press implements Compressor {

    /**
     * Initializes the Compressor object.
     *
     * @return The HTML compressor instance.
     */
    @Override
    protected Compressor initCompress() {
        final HtmlCompressor compressor = new HtmlCompressor();
        compressor.setRemoveIntertagSpaces(true);
        return compressor;
    }
}
