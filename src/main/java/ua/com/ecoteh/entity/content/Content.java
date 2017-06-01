package ua.com.ecoteh.entity.content;

import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.model.Model;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
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
     * The title of a contentEntity.
     */
    private final String title;

    /**
     * The URL of a contentEntity.
     */
    private final String url;

    /**
     * The description of a contentEntity.
     */
    private final String description;

    /**
     * The keywords of a contentEntity.
     */
    private final String keywords;

    /**
     * The category logo.
     */
    private final File logo;

    /**
     *
     * @param id
     * @param validated
     * @param title
     * @param url
     * @param description
     * @param keywords
     * @param logo
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
     * Returns a title of the contentEntity.
     *
     * @return The contentEntity title or empty string (newer null).
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Returns a URL of the contentEntity.
     *
     * @return The contentEntity URL or empty string (newer null).
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Returns a description of the contentEntity.
     *
     * @return The contentEntity description or empty string (newer null).
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a keywords of the contentEntity.
     *
     * @return The contentEntity keywords or empty string (newer null).
     */
    public String getKeywords() {
        return this.keywords;
    }

    /**
     * Returns a logo of the contentEntity.
     *
     * @return The contentEntity logo (newer null).
     */
    public File getLogo() {
        return this.logo;
    }
}
