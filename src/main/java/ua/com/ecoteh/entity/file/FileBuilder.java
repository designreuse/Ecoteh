package ua.com.ecoteh.entity.file;

import org.springframework.web.multipart.MultipartFile;
import ua.com.ecoteh.entity.model.ModelBuilder;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of methods
 * for building an objects of the {@link File} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see File
 */
public final class FileBuilder extends ModelBuilder<File, FileBuilder> {

    /**
     * The title of a new file.
     */
    private String title;

    /**
     * The URL of a new file.
     */
    private String url;

    /**
     * The type of a new file.
     */
    private FileType type;

    /**
     * The multipart file.
     */
    private MultipartFile multipartFile;

    /**
     * Constructor.
     */
    FileBuilder() {
    }

    /**
     * Builds and returns a new file.
     *
     * @return The new file.
     * @see File
     */
    @Override
    public File build() {
        return new File(
                getId(), isValidated(),
                getTitle(), getUrl(),
                getType(), getMultipartFile()
        );
    }

    /**
     * Adds a new title to a new file.
     *
     * @param title the new title to a new file.
     * @return the file builder.
     */
    public FileBuilder addTitle(final String title) {
        this.title = title;
        return this;
    }

    /**
     * Adds a new URL to a new file.
     *
     * @param url the new URL to a new file.
     * @return the file builder.
     */
    public FileBuilder addUrl(final String url) {
        this.url = url;
        return this;
    }

    /**
     * Adds a new type to a new file.
     *
     * @param type the new type to a new file.
     * @return the file builder.
     */
    public FileBuilder addType(final FileType type) {
        this.type = type;
        return this;
    }

    /**
     * Adds a new multipart file to a new file.
     *
     * @param multipartFile the new multipart file to a new file.
     * @return the file builder.
     */
    public FileBuilder addMultipartFile(final MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
        return this;
    }

    /**
     * Returns a title of a new file.
     * Returns an empty string if the title is null or empty.
     *
     * @return The title or empty string (newer null).
     */
    private String getTitle() {
        return isNotEmpty(this.title) ? this.title : "";
    }

    /**
     * Returns a URL of a new file.
     * Returns an empty string if the URL is null or empty.
     *
     * @return The URL or empty string (newer null).
     */
    private String getUrl() {
        return isNotEmpty(this.url) ? this.url : "";
    }

    /**
     * Returns a type of a new file.
     * Returns an ANOTHER file type if the type is null.
     *
     * @return The type or an ANOTHER file type (newer null).
     */
    private FileType getType() {
        return isNotNull(this.type) ? this.type : FileType.ANOTHER;
    }

    /**
     * Returns a multipart file of a new file.
     *
     * @return The multipart file.
     */
    private MultipartFile getMultipartFile() {
        return this.multipartFile;
    }
}
