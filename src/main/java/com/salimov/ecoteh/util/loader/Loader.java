package com.salimov.ecoteh.util.loader;

/**
 * The interface describes the methods for working
 * with files in file system.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface Loader {

    /**
     * Reads a file in the file system.
     *
     * @return a information from a file.
     */
    String read();

    /**
     * Saves a file in the file system.
     */
    void write();

    /**
     * Deletes a file with the rootPath.
     *
     * @return true if a file is deleted, false otherwise.
     */
    boolean delete();

    /**
     * Returns root path of a file.
     *
     * @return The root path of a file.
     */
    String getPath();
}
