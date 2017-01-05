package com.salimov.yurii.entity;

import com.salimov.yurii.util.translator.Translator;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The abstract superclass class implements a set of standard methods
 * for working with entity of the {@link Content} class or subclasses.
 *
 * @param <E> Content id type, extends Number.
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Model
 * @see Article
 * @see Category
 * @see Company
 * @see Section
 */
@MappedSuperclass
public abstract class Content<E extends Number> extends Model<E> {

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
     * The url of a content.
     */
    @Column(name = "url", nullable = false, unique = true)
    private String url;

    /**
     * The description of a content.
     */
    @Column(name = "description")
    private String description;

    /**
     * The keywords of a content.
     */
    @Column(name = "keywords")
    private String keywords;

    /**
     * Default constructor.
     */
    public Content() {
    }

    /**
     * Constrictor.
     * Initializes a main content parameters.
     *
     * @param title       a title of the new content.
     * @param description a description of the new content.
     * @param keywords    a keywords of the new content.
     */
    public Content(
            final String title,
            final String description,
            final String keywords
    ) {
        this();
        initialize(title, description, keywords);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + this.title
                + " \nKeywords: " + this.keywords
                + " \nURL: " + this.url
                + " \nDescription: " + this.description;
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
            result = (
                    isNotBlank(this.title) ?
                            this.title.equalsIgnoreCase(other.title) :
                            isBlank(other.title)
            ) && (
                    isNotBlank(this.url) ?
                            this.url.equalsIgnoreCase(other.url) :
                            isBlank(other.url)
            );
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
        return (
                isNotBlank(this.title) ? this.title.hashCode() : 0
        ) + (
                isNotBlank(this.url) ? this.url.hashCode() : 0
        );
    }

    /**
     * Initializes some parameter of the content.
     *
     * @param title       a new title to the content.
     * @param description a new description to the content.
     * @param keywords    a new keywords to the content.
     */
    public void initialize(
            final String title,
            final String description,
            final String keywords
    ) {
        setTitle(title);
        setDescription(description);
        setKeywords(keywords);
    }

    /**
     * Returns a title of the content.
     *
     * @return The content title.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets a new title to the content.
     * If parameter title is blank, then sets {@code null}.
     * Also title translates and sets to url.
     *
     * @param title a new title to the content.
     */
    public void setTitle(final String title) {
        this.title = isNotBlank(title) ? title : null;
        translateAndSetUrl(title);
    }

    /**
     * Returns a url of the content.
     *
     * @return The content url.
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Sets a new url to the content.
     * If parameter url is blank, then sets {@code null}.
     *
     * @param url a new url to the content.
     */
    public void setUrl(final String url) {
        this.url = isNotBlank(url) ? url : null;
    }

    /**
     * Translates value and sets to url.
     *
     * @param value a value to translate.
     */
    public void translateAndSetUrl(final String value) {
        setUrl(
                Translator.fromCyrillicToLatin(value)
        );
    }

    /**
     * Returns a description of the content.
     *
     * @return The content description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets a new description to the content.
     * If parameter description is blank, then sets {@code null}.
     *
     * @param description a new description to the content.
     */
    public void setDescription(final String description) {
        this.description = isNotBlank(description) ? description : null;
    }

    /**
     * Returns a keywords of the content.
     *
     * @return The content keywords.
     */
    public String getKeywords() {
        return this.keywords;
    }

    /**
     * Sets a new keywords to the content.
     * If parameter keywords is blank, then sets {@code null}.
     *
     * @param keywords a new keywords to the content.
     */
    public void setKeywords(final String keywords) {
        this.keywords = isNotBlank(keywords) ? keywords : null;
    }

    /**
     * Statically validates the content.
     * Content is valid if it is a valid model object
     * and it has name, phone and email.
     *
     * @param content a content to validate.
     * @param <ID>    a content id type, extends Number.
     * @return {@code true} if the content is valid, {@code false} otherwise.
     */
    public static <ID extends Number> boolean isValidated(
            final Content<ID> content
    ) {
        boolean result = false;
        if (Model.isValidated(content)) {
            final String title = content.getTitle();
            final String url = content.getUrl();
            if (isNotBlank(title) && isNotBlank(url)) {
                result = true;
            }
        }
        return result;
    }
}
