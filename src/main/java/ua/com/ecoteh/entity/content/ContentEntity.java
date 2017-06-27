package ua.com.ecoteh.entity.content;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ua.com.ecoteh.entity.file.FileEntity;
import ua.com.ecoteh.entity.model.ModelEntity;

import javax.persistence.*;

/**
 * The abstract superclass class implements a set of standard methods
 * for working with entity of the {@link ContentEntity} class or subclasses.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Content
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class ContentEntity extends ModelEntity {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The title of this content entity.
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * The URL of this content entity.
     */
    @Column(name = "url", nullable = false, unique = true)
    private String url;

    /**
     * The text of this content entity.
     */
    @Column(name = "text", nullable = false)
    private String text;

    /**
     * The description of this content entity.
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * The keywords of this content entity.
     */
    @Column(name = "keywords", nullable = false)
    private String keywords;

    /**
     * The logo of this content entity.
     */
    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "logo_id",
            referencedColumnName = "id"
    )
    @Fetch(FetchMode.JOIN)
    private FileEntity logo;

    /**
     * Constructor
     */
    protected ContentEntity() {
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "ContentEntity{" + super.toString() +
                ", title='" + this.title + '\'' +
                ", url='" + this.url + '\'' +
                ", text='" + this.text + '\'' +
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
            final ContentEntity other = (ContentEntity) object;
            result = this.title.equalsIgnoreCase(other.title) &&
                    this.url.equalsIgnoreCase(other.url) &&
                    this.text.equalsIgnoreCase(other.text) &&
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
        return this.title.hashCode() + this.url.hashCode() +
                this.text.hashCode() + this.description.hashCode();
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance (newer null).
     */
    @Override
    public ContentEntity clone() {
        final ContentEntity clone = (ContentEntity) super.clone();
        clone.logo = this.logo.clone();
        return clone;
    }

    /**
     * Returns a title of the content entity.
     *
     * @return The content entity title or empty string (newer null).
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets a new title to the content entity.
     *
     * @param title the new title to the content entity.
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Returns a URL of the content entity.
     *
     * @return The content entity URL or empty string (newer null).
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Sets a new URL to the content entity.
     *
     * @param url the new URL to the content entity.
     */
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * Returns a text of the content entity.
     *
     * @return The content entity text or empty string (newer null).
     */
    public String getText() {
        return this.text;
    }

    /**
     * Sets a new text to the content entity.
     *
     * @param text a new text to the content entity.
     */
    public void setText(final String text) {
        this.text = text;
    }

    /**
     * Returns a description of the content entity.
     *
     * @return The content entity description or empty string (newer null).
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets a new description to the content entity.
     *
     * @param description the new description to the content entity.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Returns a keywords of the content entity.
     *
     * @return The content entity keywords or empty string (newer null).
     */
    public String getKeywords() {
        return this.keywords;
    }

    /**
     * Sets a new keywords to the content entity.
     *
     * @param keywords the new keywords to the content entity.
     */
    public void setKeywords(final String keywords) {
        this.keywords = keywords;
    }

    /**
     * Returns a logo of the content entity.
     *
     * @return The content entity logo (newer null).
     * @see FileEntity
     */
    public FileEntity getLogoEntity() {
        return this.logo;
    }

    /**
     * Sets a new logo to the content entity.
     *
     * @param logo the new logo to the content entity.
     * @see FileEntity
     */
    public void setLogoEntity(final FileEntity logo) {
        this.logo = logo;
    }

    /**
     * Converts this entity and returns a object
     * of the {@link Content} class.
     *
     * @return The object of the {@link Content} class.
     * @see Content
     */
    @Override
    public abstract Content convert();
}
