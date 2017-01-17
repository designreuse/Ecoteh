package com.salimov.yurii.entity.interfaces;

/**
 * The interface describes a set of methods
 * for working with objects of
 * the {@link com.salimov.yurii.entity.Content} class.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see IModel
 * @see com.salimov.yurii.entity.Content
 */
public interface IContent<E extends Number>
        extends IModel<E> {

    /**
     * Initializes some parameter of the content.
     *
     * @param title       a new title to the content.
     * @param description a new description to the content.
     * @param keywords    a new keywords to the content.
     */
    void initialize(
            final String title,
            final String description,
            final String keywords
    );

    /**
     * Returns a title of the content.
     *
     * @return The content title.
     */
    String getTitle();

    /**
     * Sets a new title to the content.
     *
     * @param title a new title to the content.
     */
    void setTitle(final String title);

    /**
     * Returns a url of the content.
     *
     * @return The content url.
     */
    String getUrl();

    /**
     * Sets a new url to the content.
     *
     * @param url a new url to the content.
     */
    void setUrl(final String url);

    /**
     * Translates value and sets to url.
     *
     * @param value a value to translate.
     */
    void translateAndSetUrl(final String value);

    /**
     * Returns a description of the content.
     *
     * @return The content description.
     */
    String getDescription();

    /**
     * Sets a new description to the content.
     *
     * @param description a new description to the content.
     */
    void setDescription(final String description);

    /**
     * Returns a keywords of the content.
     *
     * @return The content keywords.
     */
    String getKeywords();

    /**
     * Sets a new keywords to the content.
     *
     * @param keywords a new keywords to the content.
     */
    void setKeywords(final String keywords);
}
