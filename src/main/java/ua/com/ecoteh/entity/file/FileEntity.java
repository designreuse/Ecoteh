package ua.com.ecoteh.entity.file;

import ua.com.ecoteh.entity.model.ModelEntity;
import ua.com.ecoteh.util.translator.Translator;

import javax.persistence.*;

import static ua.com.ecoteh.util.validator.ObjectValidator.*;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link FileEntity} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Entity
@Table(name = "files")
public class FileEntity extends ModelEntity {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The title of a fileEntity.
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * The URL of a fileEntity.
     */
    @Column(name = "url", nullable = false, unique = true)
    private String url;

    /**
     * The type of a fileEntity.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private FileType type;

    /**
     * Default constructor.
     */
    public FileEntity() {
        this.title = "";
        this.url = "";
        this.type = FileType.ANOTHER;
        setValidated(true);
    }

    /**
     * Constructor.
     *
     * @param title the title of a new fileEntity.
     */
    public FileEntity(final String title) {
        this();
        setTitle(title);
    }

    /**
     * Constructor.
     *
     * @param title the title of a new fileEntity.
     * @param url   the URL of a new fileEntity.
     */
    public FileEntity(
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
     * @param title the title of a new fileEntity.
     * @param url   the URL of a new fileEntity.
     * @param type  the type of a new fileEntity.
     */
    public FileEntity(
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
        return "FileEntity{" + super.toString() +
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
            final FileEntity other = (FileEntity) object;
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
    public FileEntity clone() {
        return (FileEntity) super.clone();
    }

    /**
     * Returns a title of the fileEntity.
     *
     * @return The fileEntity title or empty string (newer null).
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets a new title to the fileEntity.
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
     * @param title the new title to the fileEntity.
     */
    public void setTitle(final String title) {
        this.title = isNotEmpty(title) ? title : "";
        if (isEmpty(this.url)) {
            translateAndSetUrl(this.title);
        }
    }

    /**
     * Returns a URL of the fileEntity.
     *
     * @return The fileEntity URL or empty string (newer null).
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Sets a new URL to the fileEntity.
     * If parameter URL is blank, then sets empty string.
     * <pre>
     *     setUrl(null) - url = ""
     *     setUrl("") - url = ""
     *     setUrl(" ") - url = ""
     *     setUrl("bob") - url = "bob"
     *     setUrl(" bob ") - url = "bob"
     * </pre>
     *
     * @param url the new URL to the fileEntity.
     */
    public void setUrl(final String url) {
        this.url = isNotEmpty(url) ? url : "";
    }

    /**
     * Returns a fileEntity type.
     * Returns a enum object of the {@link FileType} class.
     *
     * @return The fileEntity type (newer null).
     */
    public FileType getType() {
        return this.type;
    }

    /**
     * Sets a new type to the fileEntity.
     * Sets default type if incoming type is null.
     * <pre>
     *     setType(null) - type = FileType.OTHER
     *     setType(FileType.SLIDE) - type = FileType.SLIDE
     * </pre>
     *
     * @param type the new fileEntity type.
     */
    public void setType(final FileType type) {
        this.type = isNotNull(type) ? type : FileType.ANOTHER;
    }

    /**
     * Translates and sets a new URL to the fileEntity.
     * If parameter URL is blank, then sets empty string.
     *
     * @param url the URL to translate and set.
     */
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
     * Initializes the fileEntity.
     * Returns this fileEntity with a new copied fields.
     * <pre>
     *     initialize(null) - does nothing, returns this fileEntity
     *     initialize(new FileEntity()) - does nothing, returns this
     *     fileEntity with a new copied fields
     * </pre>
     *
     * @param fileEntity the fileEntity to copy.
     * @return This fileEntity with new fields (newer null).
     */
    public FileEntity initialize(final FileEntity fileEntity) {
        if (isNotEmptyFile(fileEntity)) {
            super.initialize(fileEntity);
            this.setTitle(fileEntity.getTitle());
            if (!isStaticFile()) {
                this.setUrl(fileEntity.getUrl());
            }
            this.setType(fileEntity.getType());
        }
        return this;
    }

    /**
     *
     * @return
     */
    public File convert() {
        return new FileEntityConverter(this).convert();
    }

    /**
     * Checks this fileEntity if it has STATIC fileEntity type.
     *
     * @return true if this fileEntity has STATIC type, false otherwise.
     */
    private boolean isStaticFile() {
        return this.getType().equals(FileType.STATIC);
    }

    /**
     * Checks the incoming fileEntity is not empty.
     *
     * @param fileEntity the fileEntity to check
     * @return true if the fileEntity is not empty, false otherwise.
     */
    private static boolean isNotEmptyFile(final FileEntity fileEntity) {
        return isNotNull(fileEntity) && isNotEmpty(fileEntity.getTitle()) && isNotEmpty(fileEntity.getUrl());
    }
}
