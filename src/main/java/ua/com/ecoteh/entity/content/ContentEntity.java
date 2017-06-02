package ua.com.ecoteh.entity.content;

import ua.com.ecoteh.entity.file.FileEntity;
import ua.com.ecoteh.entity.model.ModelEntity;

import javax.persistence.*;

/**
 * The abstract superclass class implements a set of standard methods
 * for working with entity of the {@link ContentEntity} class or subclasses.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
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
     * The title of a contentEntity.
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * The URL of a contentEntity.
     */
    @Column(name = "url", nullable = false, unique = true)
    private String url;

    /**
     * The description of a contentEntity.
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * The keywords of a contentEntity.
     */
    @Column(name = "keywords", nullable = false)
    private String keywords;

    /**
     * The category logo.
     */
    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "logo_id",
            referencedColumnName = "id"
    )
    private FileEntity logo;

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
     * Returns a title of the contentEntity.
     *
     * @return The contentEntity title or empty string (newer null).
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets a new title to the contentEntity.
     * If parameter title is blank, then sets empty string.
     * Also, title translates and sets to URL if this URL is empty.
     * <pre>
     *     setTitle(null) - title = ""
     *     setTitle("") - title = ""
     *     setTitle(" ") - title = ""
     *     setTitle("bob") - title = "bob"
     *     setTitle(" bob ") - title = " bob "
     * </pre>
     *
     * @param title the new title to the contentEntity.
     */
    public void setTitle(final String title) {
        this.title = title;
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
     * Sets a new URL to the contentEntity.
     * If parameter URL is blank, then sets empty string.
     * <pre>
     *     setUrl(null) - url = ""
     *     setUrl("") - url = ""
     *     setUrl(" ") - url = ""
     *     setUrl("bob") - url = "bob"
     *     setUrl(" bob ") - url = " bob "
     * </pre>
     *
     * @param url the new URL to the contentEntity.
     */
    public void setUrl(final String url) {
        this.url = url;
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
     * Sets a new description to the contentEntity.
     * If parameter description is blank, then sets empty string.
     * <pre>
     *     setDescription(null) - description = ""
     *     setDescription("") - description = ""
     *     setDescription(" ") - description = ""
     *     setDescription("bob") - description = "bob"
     *     setDescription(" bob ") - description = " bob "
     * </pre>
     *
     * @param description the new description to the contentEntity.
     */
    public void setDescription(final String description) {
        this.description = description;
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
     * Sets a new keywords to the contentEntity.
     * If parameter keywords is blank, then sets empty string.
     * <pre>
     *     setKeywords(null) - keywords = ""
     *     setKeywords("") - keywords = ""
     *     setKeywords(" ") - keywords = ""
     *     setKeywords("bob") - keywords = "bob"
     *     setKeywords(" bob ") - keywords = " bob "
     * </pre>
     *
     * @param keywords the new keywords to the contentEntity.
     */
    public void setKeywords(final String keywords) {
        this.keywords = keywords;
    }

    /**
     * Returns a logo of the contentEntity.
     *
     * @return The contentEntity logo (newer null).
     */
    public FileEntity getLogoEntity() {
        return this.logo;
    }

    /**
     * Sets a new logo to the contentEntity.
     *
     * @param logo the new logo to the contentEntity.
     */
    public void setLogoEntity(final FileEntity logo) {
        this.logo = logo;
    }
}
