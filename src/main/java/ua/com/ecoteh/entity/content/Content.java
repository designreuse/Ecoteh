package ua.com.ecoteh.entity.content;

import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.model.Model;

/**
 * The abstract superclass implements a set of methods for working
 * with objects of the {@link Content} class or subclasses.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see ContentEntity
 */
public abstract class Content extends Model {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The title of this content.
     */
    private final String title;

    /**
     * The URL of this contentE.
     */
    private final String url;

    /**
     * The description of this content.
     */
    private final String description;

    /**
     * The keywords of this content.
     */
    private final String keywords;

    /**
     * The logo of this content.
     */
    private final File logo;

    /**
     * Constructor.
     *
     * @param id          the unique identifier for each content.
     * @param validated   the validations of a new content.
     * @param title       the title of a new content.
     * @param url         the URL of a new content.
     * @param description the description of a new content.
     * @param keywords    the keywords of a new content.
     * @param logo        the logo of a new content.
     */
    protected Content(
            final long id, final boolean validated,
            final String title, final String url,
            final String description, final String keywords,
            final File logo
    ) {
        super(id, validated);
        this.title = title;
        this.url = url;
        this.description = description;
        this.keywords = keywords;
        this.logo = logo;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "Content{" + super.toString() +
                ", title='" + this.title + '\'' +
                ", url='" + this.url + '\'' +
                ", description='" + this.description + '\'' +
                ", keywords='" + this.keywords + '\'' +
                ", logo=" + this.logo +
                '}';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return Returns true if this object is the same as the obj
     * argument, otherwise returns false.
     */
    @Override
    public boolean equals(final Object object) {
        boolean result = super.equals(object);
        if (result) {
            final Content other = (Content) object;
            result = this.title.equalsIgnoreCase(other.title) &&
                    this.url.equalsIgnoreCase(other.url) &&
                    this.description.equalsIgnoreCase(other.description);
        }
        return result;
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit
     * of hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return this.title.hashCode() +
                this.url.hashCode() +
                this.description.hashCode();
    }

    /**
     * Returns a title of the content.
     *
     * @return The content title or empty string (newer null).
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Returns a URL of the content.
     *
     * @return The content URL or empty string (newer null).
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Returns a description of the content.
     *
     * @return The content description or empty string (newer null).
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a keywords of the content.
     *
     * @return The content keywords or empty string (newer null).
     */
    public String getKeywords() {
        return this.keywords;
    }

    /**
     * Returns a logo of the content.
     *
     * @return The content logo (newer null).
     * @see File
     */
    public File getLogo() {
        return this.logo;
    }

    /**
     * Converts this object and returns an entity
     * of the {@link ContentEntity} class.
     *
     * @return The entity of the {@link ContentEntity} class.
     * @see ContentEntity
     */
    @Override
    public abstract ContentEntity convert();

    /**
     * Returns a editor for updating this content.
     *
     * @return the content editor.
     * @see ContentEditor
     */
    @Override
    public abstract ContentEditor getEditor();
}
