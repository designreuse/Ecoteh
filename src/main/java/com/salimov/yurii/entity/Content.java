package com.salimov.yurii.entity;

import com.salimov.yurii.entity.interfaces.IContent;
import com.salimov.yurii.util.translator.Translator;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The abstract superclass class implements a set of standard methods
 * for working with entity of the {@link Content} class or subclasses.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see Model
 * @see Article
 * @see Category
 * @see Company
 * @see IContent
 */
@MappedSuperclass
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
     * The url of a content.
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
     * Default constructor.
     */
    public Content() {
        this.title = "";
        this.url = "";
        this.description = "";
        this.keywords = "";
    }

    /**
     * Constrictor.
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
        super();
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
        return this.title.hashCode() + this.url.hashCode() + this.description.hashCode();
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance.
     */
    @Override
    public Content clone() {
        return (Content) super.clone();
    }

    /**
     * Returns a title of the content.
     *
     * @return The content title.
     */
    @Override
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
    @Override
    public void setTitle(final String title) {
        this.title = isNotBlank(title) ? title : null;
        if (isBlank(this.url)) {
            translateAndSetUrl(this.title);
        }
    }

    /**
     * Returns a url of the content.
     *
     * @return The content url.
     */
    @Override
    public String getUrl() {
        return this.url;
    }

    /**
     * Sets a new url to the content.
     * If parameter url is blank, then sets {@code null}.
     *
     * @param url a new url to the content.
     */
    @Override
    public void setUrl(final String url) {
        this.url = isNotBlank(url) ? url : "";
    }

    /**
     * Translates value and sets to url.
     *
     * @param value a value to translate.
     */
    @Override
    public void translateAndSetUrl(final String value) {
        setUrl(Translator.fromCyrillicToLatin(value));
    }

    /**
     * Returns a description of the content.
     *
     * @return The content description.
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets a new description to the content.
     * If parameter description is blank, then sets {@code null}.
     *
     * @param description a new description to the content.
     */
    @Override
    public void setDescription(final String description) {
        this.description = isNotBlank(description) ? description : "";
    }

    /**
     * Returns a keywords of the content.
     *
     * @return The content keywords.
     */
    @Override
    public String getKeywords() {
        return this.keywords;
    }

    /**
     * Sets a new keywords to the content.
     * If parameter keywords is blank, then sets {@code null}.
     *
     * @param keywords a new keywords to the content.
     */
    @Override
    public void setKeywords(final String keywords) {
        this.keywords = isNotBlank(keywords) ? keywords : "";
    }

    /**
     * @param content
     * @return
     */
    public Content initialize(final Content content) {
        if (content != null) {
            super.initialize(content);
            this.setTitle(content.getTitle());
            this.setUrl(content.getUrl());
            this.setDescription(content.getDescription());
        }
        return this;
    }
}
