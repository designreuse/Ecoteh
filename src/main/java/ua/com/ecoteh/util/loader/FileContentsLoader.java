package ua.com.ecoteh.util.loader;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.RandomAccessFile;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class implements the methods for working
 * with files in file system.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public final class FileContentsLoader extends AbstractLoader implements Loader {

    /**
     * The object for logging information.
     */
    private static final Logger LOGGER = Logger.getLogger(FileContentsLoader.class);

    /**
     * Thr text to save.
     */
    private String text;

    /**
     * Constructor.
     *
     * @param path a root path of a file.
     */
    public FileContentsLoader(final String path) {
        this(path, "");
    }

    /**
     * Constructor.
     *
     * @param path a root path of a file.
     * @param text a information to save.
     */
    public FileContentsLoader(
            final String path,
            final String text
    ) {
        super(path);
        setText(text);
    }

    /**
     * Reads a information from a file.
     *
     * @return a information from a file.
     */
    @Override
    public String read() {
        try (final RandomAccessFile in = new RandomAccessFile(getPath(), "r")) {
            byte[] buf = new byte[(int) in.length()];
            in.read(buf);
            setText(new String(buf));
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            setText("");
        }
        return this.text;
    }

    /**
     * Writes a information in file.
     */
    @Override
    public void write() {
        checkPath(getPath());
        try (final RandomAccessFile out = new RandomAccessFile(getPath(), "rw")) {
            byte[] buf = this.text.getBytes();
            out.setLength(0);
            out.write(buf);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }

    /**
     * Sets a new text to the article.
     * If parameter text is blank, then sets empty string.
     *
     * @param text a new text to the article.
     */
    private void setText(final String text) {
        this.text = isNotBlank(text) ? text : "";
    }
}
