package ua.com.ecoteh.entity;

import ua.com.ecoteh.enums.FileType;
import ua.com.ecoteh.util.translator.Translator;

import javax.persistence.*;

import static ua.com.ecoteh.util.validator.ObjectValidator.*;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link File} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Entity
@Table(name = "files")
public class File extends Model implements IFile {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The title of a file.
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * The URL of a file.
     */
    @Column(name = "url", nullable = false, unique = true)
    private String url;

    /**
     * The type of a file.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private FileType type;

    /**
     * Default constructor.
     */
    public File() {
        this.title = "";
        this.url = "";
        this.type = FileType.ANOTHER;
        setValidated(true);
    }

    /**
     * Constructor.
     *
     * @param title the title of a new file.
     */
    public File(final String title) {
        this();
        setTitle(title);
    }

    /**
     * Constructor.
     *
     * @param title the title of a new file.
     * @param url   the URL of a new file.
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
     * @param title the title of a new file.
     * @param url   the URL of a new file.
     * @param type  the type of a new file.
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
     * @return A string representation of the object (newer null).
     */
    @Override
    public String toString() {
        return "File{" + super.toString() +
                ", title='" + getTitle() + '\'' +
                ", type='" + getType() + '\'' +
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
            result = this.getTitle().equalsIgnoreCase(other.getTitle()) &&
                    this.getUrl().equalsIgnoreCase(other.getUrl());
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
        return getTitle().hashCode() + getUrl().hashCode();
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance (newer null).
     */
    @Override
    public File clone() {
        return (File) super.clone();
    }

    /**
     * Returns a title of the file.
     *
     * @return The file title or empty string (newer null).
     */
    @Override
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets a new title to the file.
     * If parameter title is blank, then sets empty string.
     * Also title translates and sets to URL if this URL is empty.
     * <pre>
     *     setTitle(null) - title = ""
     *     setTitle("") - title = ""
     *     setTitle(" ") - title = ""
     *     setTitle("bob") - title = "bob"
     *     setTitle(" bob ") - title = "bob"
     * </pre>
     *
     * @param title the new title to the file.
     */
    @Override
    public void setTitle(final String title) {
        this.title = isNotEmpty(title) ? title : "";
        if (isEmpty(this.url)) {
            translateAndSetUrl(this.title);
        }
    }

    /**
     * Returns a URL of the file.
     *
     * @return The file URL or empty string (newer null).
     */
    @Override
    public String getUrl() {
        return this.url;
    }

    /**
     * Sets a new URL to the file.
     * If parameter URL is blank, then sets empty string.
     * <pre>
     *     setUrl(null) - url = ""
     *     setUrl("") - url = ""
     *     setUrl(" ") - url = ""
     *     setUrl("bob") - url = "bob"
     *     setUrl(" bob ") - url = "bob"
     * </pre>
     *
     * @param url the new URL to the file.
     */
    @Override
    public void setUrl(final String url) {
        this.url = isNotEmpty(url) ? url : "";
    }

    /**
     * Returns a file type.
     * Returns a enum object of the {@link FileType} class.
     *
     * @return The file type (newer null).
     */
    @Override
    public FileType getType() {
        return this.type;
    }

    /**
     * Sets a new type to the file.
     * Sets default type if incoming type is null.
     * <pre>
     *     setType(null) - type = FileType.OTHER
     *     setType(FileType.SLIDE) - type = FileType.SLIDE
     * </pre>
     *
     * @param type the new file type.
     */
    @Override
    public void setType(final FileType type) {
        this.type = isNotNull(type) ? type : FileType.ANOTHER;
    }

    /**
     * Translates and sets a new URL to the file.
     * If parameter URL is blank, then sets empty string.
     *
     * @param url the URL to translate and set.
     */
    @Override
    public void translateAndSetUrl(final String url) {
        String newUrl = null;
        if (isNotEmpty(url)) {
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
     * Returns this file with a new copied fields.
     * <pre>
     *     initialize(null) - does nothing, returns this file
     *     initialize(new File()) - does nothing, returns this
     *     file with a new copied fields
     * </pre>
     *
     * @param file the file to copy.
     * @return This file with new fields (newer null).
     */
    @Override
    public File initialize(final File file) {
        if (isNotEmptyFile(file)) {
            super.initialize(file);
            this.setTitle(file.getTitle());
            if (!isStaticFile()) {
                this.setUrl(file.getUrl());
            }
            this.setType(file.getType());
        }
        return this;
    }

    /**
     * Checks this file if it has STATIC file type.
     *
     * @return true if this file has STATIC type, false otherwise.
     */
    private boolean isStaticFile() {
        return this.getType().equals(FileType.STATIC);
    }

    /**
     * Checks the incoming file is not empty.
     *
     * @param file the file to check
     * @return true if the file is not empty, false otherwise.
     */
    private static boolean isNotEmptyFile(final File file) {
        return isNotNull(file) && isNotEmpty(file.getTitle()) && isNotEmpty(file.getUrl());
    }
}
