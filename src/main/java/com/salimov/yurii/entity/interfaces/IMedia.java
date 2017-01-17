package com.salimov.yurii.entity.interfaces;

/**
 * The interface describes a set of methods
 * for working with objects of
 * the {@link com.salimov.yurii.entity.Media} class.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see com.salimov.yurii.entity.Media
 * @see IModel
 */
public interface IMedia<E extends Number>
        extends IModel<E> {

    /**
     * Initializes some parameter of the media.
     *
     * @param title a new title to the media.
     * @param url   a new url to the media.
     */
    void initialize(
            final String title,
            final String url
    );

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
     * Returns a url of the media.
     *
     * @return The media url.
     */
    String getUrl();

    /**
     * Sets a new url to the media.
     *
     * @param url a new url to the media.
     */
    void setUrl(final String url);

    /**
     * Translates value and sets to url.
     *
     * @param value a value to translate.
     */
    void translateAndSetUrl(final String value);
}
