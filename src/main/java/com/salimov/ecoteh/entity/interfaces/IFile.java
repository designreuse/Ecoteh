package com.salimov.ecoteh.entity.interfaces;

import com.salimov.ecoteh.entity.File;
import com.salimov.ecoteh.enums.FileType;

/**
 * The interface describes a set of methods
 * for working with objects of
 * the {@link com.salimov.ecoteh.entity.File} class.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface IFile extends IModel {

    /**
     * Returns a title of the media.
     *
     * @return The media title.
     */
    String getTitle();

    /**
     * Sets a new title to the media.
     *
     * @param title a new title to the media.
     */
    void setTitle(final String title);

    /**
     * Returns a URL of the media.
     *
     * @return The media URL.
     */
    String getUrl();

    /**
     * Sets a new URL to the media.
     *
     * @param url a new URL to the media.
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
     * @param type a new file type.
     */
    void setType(final FileType type);

    /**
     * Translates value and sets to url.
     *
     * @param value a value to translate.
     */
    void translateAndSetUrl(final String value);

    /**
     * Initializes the file.
     *
     * @param file a file to copy.
     * @return The this file with new fields.
     */
    File initialize(final File file);
}
