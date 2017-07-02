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
     * The text to save.
     */
    private final String text;

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
    public FileContentsLoader(final String path, final String text) {
        super(path);
        this.text = text;
    }

    /**
     * Reads a information from a file.
     *
     * @return the information from a file.
     */
    @Override
    public String read() {
        final String result;
        final String path = getPath();
        if (isNotEmpty(path)) {
            result = readFromFile(path);
        } else {
            result = "";
        }
        return result;
    }

    /**
     * Writes a information in file.
     * Return false if the file path is null or empty.
     *
     * @return true if a file is saved, false otherwise.
     */
    @Override
    public boolean write() {
        final String path = getPath();
        return isNotEmpty(path) && writeToFile(path);
    }

    /**
     * Reads a information from a file.
     *
     * @param path the file path to read.
     * @return the information from a file.
     */
    private String readFromFile(final String path) {
        String result;
        try (RandomAccessFile in = new RandomAccessFile(path, "r")) {
            byte[] buf = new byte[(int) in.length()];
            in.read(buf);
            result = new String(buf);
        } catch (IOException ex) {
            logException(ex);
            result = "";
        }
        return result;
    }

    /**
     * Writes a information in file.
     *
     * @param path the file path to write.
     * @return true if a file is saved, false otherwise.
     */
    private boolean writeToFile(final String path) {
        boolean result = true;
        checkPath(path);
        try (RandomAccessFile out = new RandomAccessFile(path, "rw")) {
            byte[] buf = this.text.getBytes();
            out.setLength(0);
            out.write(buf);
        } catch (IOException ex) {
            logException(ex);
            result = false;
        }
        return result;
    }
}
