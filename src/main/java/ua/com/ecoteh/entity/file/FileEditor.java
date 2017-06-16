package ua.com.ecoteh.entity.file;

import org.springframework.web.multipart.MultipartFile;
import ua.com.ecoteh.entity.model.ModelEditor;
import ua.com.ecoteh.util.translator.Translator;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of methods
 * for editing an objects of the {@link File} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see File
 */
public final class FileEditor extends ModelEditor<File, FileEditor> {

    /**
     * The file to edit.
     */
    private final File file;

    /**
     * The new title of the file.
     */
    private String title;

    /**
     * The new URL of the file.
     */
    private String url;

    /**
     * The new type of the file.
     */
    private FileType type;

    /**
     * The new multipart file.
     */
    private MultipartFile multipartFile;

    /**
     * Constructor.
     *
     * @param file the file to edit.
     */
    FileEditor(final File file) {
        super(file);
        this.file = file;
    }

    /**
     * Updates and returns a new file.
     *
     * @return The updated file.
     */
    @Override
    public File update() {
        final FileBuilder builder = File.getBuilder();
        builder.addId(getId())
                .addValidated(isValidated())
                .addTitle(getTitle())
                .addUrl(getUrl())
                .addMultipartFile(getMultipartFile())
                .addType(getType());
        return builder.build();
    }

    /**
     * Copies the incoming file.
     *
     * @param file the file to copy.
     * @return the file editor.
     */
    @Override
    public FileEditor copy(final File file) {
        if (isNotNull(file)) {
            super.copy(file)
                    .addTitle(file.getTitle())
                    .addUrl(file.getUrl())
                    .addMultipartFile(file.getMultipartFile())
                    .addType(file.getType());
        }
        return this;
    }

    /**
     * Adds a new title to the file.
     *
     * @param title the new title to the file.
     * @return the file editor.
     */
    public FileEditor addTitle(final String title) {
        this.title = title;
        return this;
    }

    /**
     * Adds a new URL to the file.
     *
     * @param url the new URL to the file.
     * @return the file editor.
     */
    public FileEditor addUrl(final String url) {
        this.url = url;
        return this;
    }

    /**
     * Adds a new type to the file.
     *
     * @param type the new type to the file.
     * @return the file editor.
     */
    public FileEditor addType(final FileType type) {
        this.type = type;
        return this;
    }

    /**
     * Adds a new multipart file to the file.
     *
     * @param multipartFile the new multipart file to the file.
     * @return the file editor.
     */
    public FileEditor addMultipartFile(final MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
        return this;
    }

    /**
     * Returns a new title of the file.
     * Returns the file title if the title is null.
     *
     * @return The title (newer null).
     */
    private String getTitle() {
        return isNotNull(this.title) ? this.title : this.file.getTitle();
    }

    /**
     * Returns a new URL of the file.
     * Returns the file URL if the URL is null.
     *
     * @return The URL (newer null).
     */
    private String getUrl() {
        final String url = isNotNull(this.url) ? this.url : this.file.getUrl();
        final String result;
        if (isNotEmpty(url)) {
            result = url;
        } else {
            final String title = getTitle();
            if (isNotEmpty(title)) {
                result = Translator.fromCyrillicToLatin(title);
            } else {
                result = "";
            }
        }
        return result;
    }

    /**
     * Returns a new type of the file.
     * Returns the file type if the type is null.
     *
     * @return The type (newer null).
     * @see FileType
     */
    private FileType getType() {
        return isNotNull(this.type) ? this.type : this.file.getType();
    }

    /**
     * Returns a new title of the file.
     * Returns the file title if the title is null.
     *
     * @return The title (newer null).
     */
    private MultipartFile getMultipartFile() {
        return isNotNull(this.multipartFile) ? this.multipartFile : this.file.getMultipartFile();
    }
}
