package com.salimov.yurii.entity;

import com.salimov.yurii.util.translator.Translator;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The abstract superclass class implements a set of standard methods
 * for working with entity of the {@link Media} class or subclasses.
 *
 * @param <E> Media id type, extends Number.
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Model
 * @see Photo
 * @see Video
 */
@MappedSuperclass
public abstract class Media<E extends Number> extends Model<E> {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The title of the media.
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * The url of the media.
     */
    @Column(name = "url", nullable = false, unique = true)
    private String url;

    /**
     * Default constructor.
     */
    public Media() {
    }

    /**
     * Constrictor.
     * Initializes a main media parameters.
     *
     * @param title a title of the new media.
     * @param url   a URL of the new media.
     */
    public Media(final String title, final String url) {
        this();
        initialize(title, url);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName()
                + " " + this.title + " \nURL: " + this.url;
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
            final Media other = (Media) object;
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
     * Initializes some parameter of the media.
     *
     * @param title a new title to the media.
     * @param url   a new url to the media.
     */
    public void initialize(String title, String url) {
        setTitle(title);
        setUrl(url);
    }

    /**
     * Returns a title of the media.
     *
     * @return The media title.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets a new title to the media.
     * If parameter title is blank, then sets {@code null}.
     * Also title translates and sets to url.
     *
     * @param title a new title to the media.
     */
    public void setTitle(final String title) {
        this.title = isNotBlank(title) ? title : null;
        translateAndSetUrl(title);
    }

    /**
     * Returns a url of the media.
     *
     * @return The media url.
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Sets a new url to the media.
     * If parameter url is blank, then sets {@code null}.
     *
     * @param url a new url to the media.
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
        setUrl(Translator.fromCyrillicToLatin(value));
    }

    /**
     * Statically validates the media.
     * Media is valid if it is a valid model object and it has url.
     *
     * @param media a content to validate.
     * @param <ID>  a content id type, extends Number.
     * @return {@code true} if the media is valid, {@code false} otherwise.
     */
    public static <ID extends Number> boolean isValidated(
            final Media<ID> media
    ) {
        boolean result = false;
        if (Model.isValidated(media)) {
            if (media.isValidated()) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Validates the media.
     * Media is validated if url is not blank.
     *
     * @return {@code true} if the media is valid, {@code false} otherwise.
     */
    @Override
    public boolean isValidated() {
        return isNotBlank(this.url);
    }
}
