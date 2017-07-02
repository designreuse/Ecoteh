package ua.com.ecoteh.service.data;

import com.googlecode.htmlcompressor.compressor.Compressor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import ua.com.ecoteh.util.compressor.CssCompressor;
import ua.com.ecoteh.util.loader.FileContentsLoader;
import ua.com.ecoteh.util.loader.Loader;
import ua.com.ecoteh.config.properties.ContentProperties;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * The class of the service layer, implements a set of methods
 * for working with CSS styles.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Service
@ComponentScan(basePackages = "ua.com.ecoteh.config.properties")
public final class StyleServiceImpl implements StyleService {

    /**
     * The relative path to file with default CSS styles.
     */
    private final static String DEFAULT_STYLES_PATH = "css/style.default.css";

    /**
     * The relative path to file with CSS styles.
     */
    private final static String STYLES_PATH = "css/style.css";

    /**
     * The relative path to file with compressing CSS styles.
     */
    private final static String MIN_STYLES_PATH = "css/style.min.css";

    /**
     * The implementation of the interface describes
     * the methods for getting application properties.
     */
    private final ContentProperties properties;

    /**
     * Constructor.
     *
     * @param properties the implementation of the {@link ContentProperties} interface.
     */
    @Autowired
    public StyleServiceImpl(final ContentProperties properties) {
        this.properties = properties;
    }

    /**
     * Returns a CSS styles of the site.
     *
     * @return The CSS styles.
     */
    @Override
    public String get() {
        return read(STYLES_PATH);
    }

    /**
     * Saves a new CSS styles of the site.
     * Saves a styles if it is not empty.
     * <pre>
     *     save(null) - does nothing
     *     save("") - does nothing
     *     save(" ") - does nothing
     *     save("bob") - saves a styles
     *     save("  bob  ") = saves a styles
     * </pre>
     *
     * @param styles the new CSS styles to save.
     */
    @Override
    public void save(final String styles) {
        if (isNotEmpty(styles)) {
            saveStyles(styles);
            saveCompressStyles(styles);
        }
    }

    /**
     * Rollbacks a CSS styles to default.
     * Read styles from the DEFAULT_STYLES_PATH file
     * with the default styles and save it.
     */
    @Override
    public void rollback() {
        final String defaultStyles = readDefaultStyles();
        save(defaultStyles);
    }

    /**
     * Reads and returns a default CSS styles.
     *
     * @return The default CSS styles.
     */
    private String readDefaultStyles() {
        return read(DEFAULT_STYLES_PATH);
    }

    /**
     * Saves styles in the STYLES_PATH files.
     *
     * @param styles the styles to write.
     */
    private void saveStyles(final String styles) {
        write(styles, STYLES_PATH);
    }

    /**
     * Compresses and saves styles in the MIN_STYLES_PATH files.
     *
     * @param styles the styles to write.
     */
    private void saveCompressStyles(final String styles) {
        final String compressedStyles = compress(styles);
        write(compressedStyles, MIN_STYLES_PATH);
    }

    /**
     * Compresses the given styles and returns a compressed result.
     *
     * @param styles The styles to compress.
     * @return Compressed result.
     */
    private String compress(final String styles) {
        final Compressor compressor = new CssCompressor();
        return compressor.compress(styles);
    }

    /**
     * Writes a incoming styles to a file in a incoming path.
     *
     * @param styles the styles to write.
     * @param path   the path to a file.
     */
    private void write(final String styles, final String path) {
        final String absolutePath = getAbsolutePath(path);
        final Loader loader = new FileContentsLoader(absolutePath, styles);
        loader.write();
    }

    /**
     * Reads file in a incoming path and returns it styles.
     *
     * @param path the path to a file.
     * @return The styles from a file.
     */
    private String read(final String path) {
        final String absolutePath = getAbsolutePath(path);
        final Loader loader = new FileContentsLoader(absolutePath);
        return loader.read();
    }

    /**
     * Returns a absolute path to the file.
     * Absolute path to the file is a resources
     * absolute path and incoming path.
     *
     * @param path the relative path to file.
     * @return The absolute path
     */
    private String getAbsolutePath(final String path) {
        return this.properties.getResourcesAbsolutePath() + path;
    }
}
