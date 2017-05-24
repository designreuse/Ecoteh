package ua.com.ecoteh.util.compressor;

import com.googlecode.htmlcompressor.compressor.Compressor;

/**
 * The class implements a set of methods for compressing HTML data.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class HtmlCompressor extends AbstractCompressor implements Compressor {

    /**
     * Remove intertag spaces.
     */
    private final static boolean REMOVED_INTERTAG_SPACES = true;

    /**
     * Constructor.
     */
    public HtmlCompressor() {
        super(createCompressor());
    }

    /**
     * Creates and returns a compressor object
     * of the HtmlCompressor class.
     *
     * @return The HTML compressor instance.
     */
    private static Compressor createCompressor() {
        final com.googlecode.htmlcompressor.compressor.HtmlCompressor compressor =
                new com.googlecode.htmlcompressor.compressor.HtmlCompressor();
        compressor.setRemoveIntertagSpaces(REMOVED_INTERTAG_SPACES);
        return compressor;
    }
}
