package ua.com.ecoteh.entity.file;

import org.springframework.web.multipart.MultipartFile;
import ua.com.ecoteh.entity.model.Model;

/**
 * The class implements a set of methods for working
 * with objects of the {@link File} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see FileEntity
 */
public final class File extends Model {

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
    private final String title;

    /**
     * The URL of a fileEntity.
     */
    private final String url;

    /**
     * The type of a fileEntity.
     *
     * @see FileType
     */
    private final FileType type;

    /**
     *
     */
    private final MultipartFile multipartFile;

    /**
     * Constructor.
     *
     * @param id            the unique identifier for each file.
     * @param validated     the validations of a new file.
     * @param title         the title of a new file.
     * @param url           the URL of a new file.
     * @param type          the type of a new file.
     * @param multipartFile the multipart file of a new file.
     */
    File(
            final long id, final boolean validated,
            final String title, final String url,
            final FileType type, final MultipartFile multipartFile
    ) {
        super(id, validated);
        this.title = title;
        this.url = url;
        this.type = type;
        this.multipartFile = multipartFile;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object (newer null).
     */
    @Override
    public String toString() {
        return "File{" + super.toString() +
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
            final File other = (File) object;
            result = this.title.equalsIgnoreCase(other.title) &&
                    this.url.equalsIgnoreCase(other.url) &&
                    this.type.equals(other.type);
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
        return this.title.hashCode() + this.url.hashCode() + this.type.hashCode();
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
     * Returns a URL of the fileEntity.
     *
     * @return The fileEntity URL or empty string (newer null).
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Returns a fileEntity type.
     * Returns a enum object of the {@link FileType} class.
     *
     * @return The fileEntity type (newer null).
     * @see FileType
     */
    public FileType getType() {
        return this.type;
    }

    /**
     * Returns the multipart file of this file.
     *
     * @return the multipart file.
     */
    public MultipartFile getMultipartFile() {
        return this.multipartFile;
    }

    /**
     * Converts this object and returns an entity
     * of the {@link FileEntity} class.
     *
     * @return The entity of the {@link FileEntity} class (newer null).
     * @see FileEntity
     */
    @Override
    public FileEntity convert() {
        return new FileConverter(this).convert();
    }

    /**
     * Returns a editor for updating this file.
     *
     * @return the file editor (newer null).
     * @see FileEditor
     */
    @Override
    public FileEditor getEditor() {
        return new FileEditor(this);
    }

    /**
     * Returns a builder for creating a new file.
     *
     * @return the file builder (newer null).
     * @see FileBuilder
     */
    public static FileBuilder getBuilder() {
        return new FileBuilder();
    }
}
