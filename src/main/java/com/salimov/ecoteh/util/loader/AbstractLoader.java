package com.salimov.ecoteh.util.loader;

import java.io.File;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The abstract class implements the methods for working
 * with files in file system.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public abstract class AbstractLoader implements Loader {

    /**
     * The root path of a file.
     */
    private final String path;

    /**
     * Constructor.
     *
     * @param path a root path of a file.
     */
    public AbstractLoader(final String path) {
        this.path = path;
    }

    /**
     * Deletes a file with the rootPath.
     * Deletes a file if it is exists
     * and it is a file (not a directory)
     *
     * @return {@code true} if a file is deleted,
     * {@code false} otherwise.
     */
    @Override
    public boolean delete() {
        boolean result = false;
        if (isNotBlank(this.path)) {
            final File file = new File(this.path);
            if (file.exists() && file.isFile()) {
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
     * @param path a path to file.
     * @return {@code true} if directories to file is exist,
     * {@code false} otherwise.
     */
    static boolean checkPath(final String path) {
        final File directory = new File(path).getParentFile();
        boolean isExists = directory.exists();
        if (!isExists) {
            isExists = directory.mkdirs();
        }
        return isExists;
    }
}
