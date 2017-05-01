package ua.com.ecoteh.util.compressor;

import com.googlecode.htmlcompressor.compressor.Compressor;

/**
 * The class implements a set of methods for compressing HTML data.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public final class HtmlCompressor extends AbstractCompressor implements Compressor {

    /**
     * Remove Intertag Spaces.
     */
    private final static boolean REMOVED_INTERTAG_SPACES = true;

    /**
     * Returns Compressor object.
     *
     * @return The HTML compressor instance.
     */
    @Override
    protected Compressor getCompressor() {
        final com.googlecode.htmlcompressor.compressor.HtmlCompressor compressor =
                new com.googlecode.htmlcompressor.compressor.HtmlCompressor();
        compressor.setRemoveIntertagSpaces(REMOVED_INTERTAG_SPACES);
        return compressor;
    }
}
