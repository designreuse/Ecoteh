package ua.com.ecoteh.util.loader;

import java.io.IOException;
import java.io.RandomAccessFile;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * The class implements the methods for working
 * with files in file system.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class FileContentsLoader extends AbstractLoader implements Loader {

    /**
     * Thr text to save.
     */
    private String text;

    /**
     * Constructor.
     *
     * @param path the root path of a file.
     */
    public FileContentsLoader(final String path) {
        this(path, "");
    }

    /**
     * Constructor.
     *
     * @param path the root path of a file.
     * @param text the information to save.
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
     * @return the information from a file.
     */
    @Override
    public String read() {
        try (final RandomAccessFile in = new RandomAccessFile(getPath(), "r")) {
            byte[] buf = new byte[(int) in.length()];
            in.read(buf);
            setText(new String(buf));
        } catch (IOException ex) {
            logException(ex);
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
            logException(ex);
        }
    }

    /**
     * Sets a new text.
     * If parameter text is null or empty, then sets empty string.
     *
     * @param text the new text.
     */
    private void setText(final String text) {
        this.text = isNotEmpty(text) ? text : "";
    }
}
