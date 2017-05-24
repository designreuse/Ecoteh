package ua.com.ecoteh.util.compressor;

import com.googlecode.htmlcompressor.compressor.Compressor;
import com.googlecode.htmlcompressor.compressor.YuiCssCompressor;

/**
 * The class implements a set of methods for compressing CSS data.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class CssCompressor extends AbstractCompressor implements Compressor {

    /**
     * Constructor.
     */
    public CssCompressor() {
        super(createCompressor());
    }

    /**
     * Creates and returns a compressor object
     * of the YuiCssCompressor class.
     *
     * @return The CSS compressor instance.
     */
    private static Compressor createCompressor() {
        return new YuiCssCompressor();
    }
}
