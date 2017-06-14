package ua.com.ecoteh.util.compressor;

import org.junit.BeforeClass;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class CssCompressorTest extends AbstractCompressorTest {

    private static CssCompressor compressor;

    @BeforeClass
    public static void beforeClass() {
        compressor = new CssCompressor();
    }

    @Override
    CssCompressor getCompressor() {
        return compressor;
    }
}