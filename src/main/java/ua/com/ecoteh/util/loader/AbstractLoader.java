package ua.com.ecoteh.util.loader;

import org.apache.log4j.Logger;

import java.io.File;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * The abstract class implements the methods for working
 * with files in file system.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
abstract class AbstractLoader implements Loader {

    /**
     * The object for logging information.
     */
    private final static Logger LOGGER = Logger.getLogger(AbstractLoader.class);

    /**
     * The root path of a file.
     */
    private final String path;

    /**
     * Constructor.
     *
     * @param path the root path of a file.
     */
    AbstractLoader(final String path) {
        this.path = path;
    }

    /**
     * Deletes a file with the rootPath.
     * Deletes a file if it is exists
     * and it is a file (not a directory)
     *
     * @return true if a file is deleted, false otherwise.
     */
    @Override
    public boolean delete() {
        boolean result = false;
        if (isNotEmpty(this.path)) {
            final File file = new File(this.path);
            if (isFile(file)) {
                result = file.delete();
            }
        }
        return result;
    }

    /**
     * Returns a path to file.
     *
     * @return The file path.
     */
    @Override
    public String getPath() {
        return this.path;
    }

    /**
     * Checks a path to file.
     * Creates directories if it is not exist.
     *
     * @param path the path to file.
     * @return true if directories to file is exist, false otherwise.
     */
    protected boolean checkPath(final String path) {
        final File directory = new File(path).getParentFile();
        boolean isExists = directory.exists();
        if (!isExists) {
            isExists = directory.mkdirs();
        }
        return isExists;
    }

    /**
     * Error logging.
     *
     * @param ex the intercepted exception.
     */
    protected void logException(final Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        ex.printStackTrace();
    }

    /**
     * Checks input file.
     *
     * @param file the file to check.
     * @return true if a file is file, false otherwise.
     */
    private boolean isFile(final File file) {
        return file.exists() && file.isFile();
    }
}
