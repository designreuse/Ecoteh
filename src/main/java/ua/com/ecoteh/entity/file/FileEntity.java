package ua.com.ecoteh.entity.file;

import ua.com.ecoteh.entity.model.ModelEntity;

import javax.persistence.*;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link FileEntity} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see File
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
     * The title of this file entity.
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * The URL of this file entity.
     */
    @Column(name = "url", nullable = false, unique = true)
    private String url;

    /**
     * The type of this file entity.
     *
     * @see FileType
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private FileType type;

    /**
     * Constructor.
     */
    protected FileEntity() {
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object (newer null).
     */
    @Override
    public String toString() {
        return "FileEntity{" + super.toString() +
                ", title='" + this.title + '\'' +
                ", type='" + this.type + '\'' +
                ", url='" + this.url + '\'' +
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
     * @return A clone of this instance (newer null).
     */
    @Override
    public FileEntity clone() {
        return (FileEntity) super.clone();
    }

    /**
     * Returns a title of the file entity.
     *
     * @return The file entity title or empty string (newer null).
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets a new title to the file entity.
     *
     * @param title the new title to the file entity.
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Returns a URL of the file entity.
     *
     * @return The file entity URL or empty string (newer null).
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Sets a new URL to the file entity.
     *
     * @param url the new URL to the file entity.
     */
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * Returns this file entity type.
     *
     * @return The file entity type (newer null).
     * @see FileType
     */
    public FileType getType() {
        return this.type;
    }

    /**
     * Sets a new type to the file entity.
     *
     * @param type the new file entity type.
     * @see FileType
     */
    public void setType(final FileType type) {
        this.type = type;
    }

    /**
     * Converts this entity and returns a object
     * of the {@link File} class.
     *
     * @return The object of the {@link File} class (newer null).
     * @see File
     */
    @Override
    public File convert() {
        return new FileEntityConverter(this).convert();
    }
}
