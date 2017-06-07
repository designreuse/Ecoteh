package ua.com.ecoteh.entity.content;

import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.model.ModelBuilder;
import ua.com.ecoteh.util.translator.Translator;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The abstract superclass implements a set of standard methods
 * for building an objects of the {@link Content} class or subclasses.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Content
 */
public abstract class ContentBuilder<T extends Content, B extends ContentBuilder<T, B>>
        extends ModelBuilder<T, B> {

    /**
     * The title of a new content.
     */
    private String title;

    /**
     * The URL of a new content.
     */
    private String url;

    /**
     * The description of a new contentE.
     */
    private String description;

    /**
     * The keywords of a new content.
     */
    private String keywords;

    /**
     * The logo of a new content.
     *
     * @see File
     */
    private File logo;

    /**
     * Constructor.
     */
    protected ContentBuilder() {
    }

    /**
     * Adds a new title to a new content.
     *
     * @param title the new title to a new content.
     * @return the content builder.
     */
    public B addTitle(final String title) {
        this.title = title;
        return (B) this;
    }

    /**
     * Adds a new title to a new content.
     *
     * @param url the new URL to a new content.
     * @return the content builder.
     */
    public B addUrl(final String url) {
        this.url = url;
        return (B) this;
    }

    /**
     * Adds a new title to a new content.
     *
     * @param description the new description to a new content.
     * @return the content builder.
     */
    public B addDescription(final String description) {
        this.description = description;
        return (B) this;
    }

    /**
     * Adds a new title to a new content.
     *
     * @param keywords the new keywords to a new content.
     * @return the content builder.
     */
    public B addKeywords(final String keywords) {
        this.keywords = keywords;
        return (B) this;
    }

    /**
     * Adds a new title to a new content.
     *
     * @param logo the new logo to a new content.
     * @return the content builder.
     * @see File
     */
    public B addLogo(final File logo) {
        this.logo = logo;
        return (B) this;
    }

    /**
     * Returns a title of a new content.
     * Returns an empty string if the title is null or empty.
     *
     * @return The content title or empty string (newer null).
     */
    protected String getTitle() {
        return isNotEmpty(this.title) ? this.title : "";
    }

    /**
     * Returns a URL of a new content.
     * Returns a translated content title if the URL is null or empty.
     * Returns an empty string if the content title is null or empty.
     *
     * @return The content URL or empty string (newer null).
     */
    protected String getUrl() {
        String result;
        if (isNotEmpty(this.url)) {
            result = this.url;
        } else if (isNotEmpty(this.title)) {
            result = Translator.fromCyrillicToLatin(this.title);
        } else {
            result = "";
        }
        return result;
    }

    /**
     * Returns a description of a new content.
     * Returns an empty string if the description is null or empty.
     *
     * @return The content description or empty string (newer null).
     */
    protected String getDescription() {
        return isNotEmpty(this.description) ? this.description : "";
    }

    /**
     * Returns a keywords of a new content.
     * Returns an empty string if the keywords is null or empty.
     *
     * @return The content keywords or empty string (newer null).
     */
    protected String getKeywords() {
        return isNotEmpty(this.keywords) ? this.keywords : "";
    }

    /**
     * Returns a logo of a new content.
     * Returns a new file if the logo is null.
     *
     * @return The content logo or a new file (newer null).
     * @see File
     */
    protected File getLogo() {
        return isNotNull(this.logo) ? this.logo : File.getBuilder().build();
    }
}
