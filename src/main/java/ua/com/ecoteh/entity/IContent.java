package ua.com.ecoteh.entity;

/**
 * The interface describes a set of methods
 * for working with objects of
 * the {@link Content} class.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface IContent extends IModel {

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
     * Returns a URL of the content.
     *
     * @return The content URL.
     */
    String getUrl();

    /**
     * Sets a new URL to the content.
     *
     * @param url a new URL to the content.
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

    /**
     * Returns a logo of the content.
     *
     * @return The content logo URL.
     */
    File getLogo();

    /**
     * Sets a new logo to the content.
     *
     * @param logo a new logo to the content.
     */
    void setLogo(final File logo);

    /**
     * Initializes the content.
     *
     * @param content a content to copy.
     * @return The this content with new fields.
     */
    Content initialize(final Content content);
}
