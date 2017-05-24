package ua.com.ecoteh.entity;

import ua.com.ecoteh.util.translator.Translator;

import javax.persistence.*;

import static ua.com.ecoteh.util.validator.ObjectValidator.*;

/**
 * The abstract superclass class implements a set of standard methods
 * for working with entity of the {@link Content} class or subclasses.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Content extends Model implements IContent {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The title of a content.
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * The URL of a content.
     */
    @Column(name = "url", nullable = false, unique = true)
    private String url;

    /**
     * The description of a content.
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * The keywords of a content.
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
    private File logo;

    /**
     * Default constructor.
     */
    public Content() {
        this.title = "";
        this.url = "";
        this.description = "";
        this.keywords = "";
        this.logo = new File();
    }

    /**
     * Constructor.
     *
     * @param title       the title of a new content.
     * @param description the description of a new content.
     * @param keywords    the keywords of a new content.
     */
    public Content(
            final String title,
            final String description,
            final String keywords
    ) {
        this();
        setTitle(title);
        setDescription(description);
        setKeywords(keywords);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "Content{" + super.toString() +
                ", title='" + getTitle() + '\'' +
                ", url='" + getUrl() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", keywords='" + getKeywords() + '\'' +
                ", logo=" + getLogo() +
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
            result = this.getTitle().equalsIgnoreCase(other.getTitle()) &&
                    this.getUrl().equalsIgnoreCase(other.getUrl()) &&
                    this.getDescription().equalsIgnoreCase(other.getDescription());
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
        return getTitle().hashCode() +
                getUrl().hashCode() +
                getDescription().hashCode();
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance (newer null).
     */
    @Override
    public Content clone() {
        final Content content = (Content) super.clone();
        content.setLogo(getLogo().clone());
        return content;
    }

    /**
     * Returns a title of the content.
     *
     * @return The content title or empty string (newer null).
     */
    @Override
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets a new title to the content.
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
     * @param title the new title to the content.
     */
    @Override
    public void setTitle(final String title) {
        this.title = isNotEmpty(title) ? title : "";
        if (isEmpty(this.url)) {
            translateAndSetUrl(this.title);
        }
    }

    /**
     * Returns a URL of the content.
     *
     * @return The content URL or empty string (newer null).
     */
    @Override
    public String getUrl() {
        return this.url;
    }

    /**
     * Sets a new URL to the content.
     * If parameter URL is blank, then sets empty string.
     * <pre>
     *     setUrl(null) - url = ""
     *     setUrl("") - url = ""
     *     setUrl(" ") - url = ""
     *     setUrl("bob") - url = "bob"
     *     setUrl(" bob ") - url = " bob "
     * </pre>
     *
     * @param url the new URL to the content.
     */
    @Override
    public void setUrl(final String url) {
        this.url = isNotEmpty(url) ? url : "";
    }

    /**
     * Translates value and sets to URL.
     *
     * @param value the value to translate.
     */
    @Override
    public void translateAndSetUrl(final String value) {
        setUrl(Translator.fromCyrillicToLatin(value));
    }

    /**
     * Returns a description of the content.
     *
     * @return The content description or empty string (newer null).
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets a new description to the content.
     * If parameter description is blank, then sets empty string.
     * <pre>
     *     setDescription(null) - description = ""
     *     setDescription("") - description = ""
     *     setDescription(" ") - description = ""
     *     setDescription("bob") - description = "bob"
     *     setDescription(" bob ") - description = " bob "
     * </pre>
     *
     * @param description the new description to the content.
     */
    @Override
    public void setDescription(final String description) {
        this.description = isNotEmpty(description) ? description : "";
    }

    /**
     * Returns a keywords of the content.
     *
     * @return The content keywords or empty string (newer null).
     */
    @Override
    public String getKeywords() {
        return this.keywords;
    }

    /**
     * Sets a new keywords to the content.
     * If parameter keywords is blank, then sets empty string.
     * <pre>
     *     setKeywords(null) - keywords = ""
     *     setKeywords("") - keywords = ""
     *     setKeywords(" ") - keywords = ""
     *     setKeywords("bob") - keywords = "bob"
     *     setKeywords(" bob ") - keywords = " bob "
     * </pre>
     *
     * @param keywords the new keywords to the content.
     */
    @Override
    public void setKeywords(final String keywords) {
        this.keywords = isNotEmpty(keywords) ? keywords : "";
    }

    /**
     * Returns a logo of the content.
     *
     * @return The content logo (newer null).
     */
    @Override
    public File getLogo() {
        return this.logo;
    }

    /**
     * Sets a new logo to the content.
     *
     * @param logo the new logo to the content.
     */
    @Override
    public void setLogo(final File logo) {
        if (isNull(this.logo)) {
            this.logo = new File();
        }
        this.logo.initialize(logo);
    }

    /**
     * Initializes the content.
     * Returns this content with a new copied fields.
     * <pre>
     *     initialize(null) - does nothing, returns this content
     *     initialize(new Content()) - does nothing, returns this
     *     content with a new copied fields
     * </pre>
     *
     * @param content the content to copy.
     * @return This content with new fields (newer null).
     */
    @Override
    public Content initialize(final Content content) {
        if (isNotNull(content)) {
            super.initialize(content);
            this.setTitle(content.getTitle());
            this.setUrl(content.getUrl());
            this.setDescription(content.getDescription());
            this.setKeywords(content.getKeywords());
            this.setLogo(content.getLogo());
        }
        return this;
    }
}
