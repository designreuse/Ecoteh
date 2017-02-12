package com.salimov.yurii.util.loader;

/**
 * The interface describes the methods for working
 * with files on the file system.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface Loader {

    /**
     * Saves a file in the file system.
     */
    void write();

    /**
     * Deletes a file with the rootPath.
     *
     * @return {@code true} if a file is deleted,
     * {@code false} otherwise.
     */
    boolean delete();
}
