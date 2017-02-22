package com.salimov.yurii.entity;

import com.salimov.yurii.entity.interfaces.IFile;
import com.salimov.yurii.enums.FileType;
import com.salimov.yurii.util.translator.Translator;

import javax.persistence.*;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link File} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see IFile
 * @see Model
 */
@Entity
@Table(name = "files")
public final class File extends Model implements IFile {

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

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private FileType type;

    /**
     * Default constructor.
     */
    public File() {
        this.title = "";
        this.url = "";
        this.type = FileType.OTHER;
        setValidated(true);
    }

    /**
     * Constructor.
     *
     * @param title a title of the new file.
     */
    public File(
            final String title
    ) {
        this();
        setTitle(title);
    }

    /**
     * Constructor.
     *
     * @param title a title of the new file.
     * @param url   a url of the new file.
     */
    public File(
            final String title,
            final String url
    ) {
        this();
        setTitle(title);
        setUrl(url);
    }

    /**
     * Constructor.
     *
     * @param title a title of the new file.
     * @param url   a url of the new file.
     * @param type  a type of the new file.
     */
    public File(
            final String title,
            final String url,
            final FileType type
    ) {
        this(title, url);
        setType(type);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "File{" + super.toString() +
                ", title='" + getTitle() + '\'' +
                ", url='" + getUrl() + '\'' +
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
            final File other = (File) object;
            result = this.title.equalsIgnoreCase(other.title) &&
                    this.url.equalsIgnoreCase(other.url);
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
        return this.title.hashCode() + this.url.hashCode();
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance.
     */
    @Override
    public File clone() {
        return (File) super.clone();
    }

    /**
     * Returns a title of the media.
     *
     * @return The media title.
     */
    @Override
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
    @Override
    public void setTitle(final String title) {
        this.title = isNotBlank(title) ? title : "";
        if (isBlank(this.url)) {
            translateAndSetUrl(this.title);
        }
    }

    /**
     * Returns a url of the media.
     *
     * @return The media url.
     */
    @Override
    public String getUrl() {
        return this.url;
    }

    /**
     * Sets a new url to the media.
     * If parameter url is blank, then sets {@code null}.
     *
     * @param url a new url to the media.
     */
    @Override
    public void setUrl(final String url) {
        this.url = isNotBlank(url) ? url : "";
    }

    /**
     * Returns a file type.
     *
     * @return The file type.
     */
    @Override
    public FileType getType() {
        return this.type;
    }

    /**
     * Sets a new type to the file.
     * If parameter url is {@code null}, then sets {@code FileType.OTHER}.
     *
     * @param type a new file type.
     */
    @Override
    public void setType(final FileType type) {
        this.type = type != null ? type : FileType.OTHER;
    }

    /**
     * Translates and sets a new url to the file.
     * If parameter url is blank, then sets {@code null}.
     *
     * @param url a url to translate and set.
     */
    @Override
    public void translateAndSetUrl(final String url) {
        String newUrl = null;
        if (isNotBlank(url)) {
            final char oldChar = '.';
            final char newChar = '!';
            newUrl = Translator.fromCyrillicToLatin(
                    url.replace(oldChar, newChar)
            ).replace(newChar, oldChar);
        }
        setUrl(newUrl);
    }

    /**
     * Initializes the file.
     *
     * @param file a file to copy.
     * @return The this file with new fields.
     */
    @Override
    public File initialize(final File file) {
        if (file != null) {
            super.initialize(file);
            this.setTitle(file.getTitle());
            this.setUrl(file.getUrl());
            this.setType(file.getType());
        }
        return this;
    }
}
