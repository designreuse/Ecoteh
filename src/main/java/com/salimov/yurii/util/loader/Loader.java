package com.salimov.yurii.util.loader;

import org.springframework.web.multipart.MultipartFile;

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

    /**
     * Returns a multipart file.
     *
     * @return The multipart file.
     */
    MultipartFile getFile();

    /**
     * Returns root path of a file.
     *
     * @return The root path of a file.
     */
    String getPath();
}
