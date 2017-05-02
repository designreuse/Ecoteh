package ua.com.ecoteh.entity;

import ua.com.ecoteh.enums.FileType;

/**
 * The interface describes a set of methods
 * for working with objects of
 * the {@link File} class.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface IFile extends IModel {

    /**
     * Returns a title of the file.
     *
     * @return The file title.
     */
    String getTitle();

    /**
     * Sets a new title to the file.
     *
     * @param title the new title to the file.
     */
    void setTitle(final String title);

    /**
     * Returns a URL of the file.
     *
     * @return The file URL.
     */
    String getUrl();

    /**
     * Sets a new URL to the file.
     *
     * @param url the new URL to the file.
     */
    void setUrl(final String url);

    /**
     * Returns a file type.
     *
     * @return The file type.
     */
    FileType getType();

    /**
     * Sets a new type to the file.
     *
     * @param type the new file type.
     */
    void setType(final FileType type);

    /**
     * Translates and sets a new URL to the file.
     *
     * @param url the URL to translate and set.
     */
    void translateAndSetUrl(final String url);

    /**
     * Initializes the file.
     * Returns this file with a new copied fields.
     *
     * @param file the file to copy.
     * @return This file with new fields.
     */
    File initialize(final File file);
}
