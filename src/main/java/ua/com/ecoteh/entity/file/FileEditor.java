package ua.com.ecoteh.entity.file;

import org.springframework.web.multipart.MultipartFile;
import ua.com.ecoteh.entity.model.ModelEditor;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class FileEditor extends ModelEditor<File, FileEditor> {

    /**
     *
     */
    private final File file;

    /**
     * The title of a fileEntity.
     */
    private String title;

    /**
     * The URL of a fileEntity.
     */
    private String url;

    /**
     * The type of a fileEntity.
     */
    private FileType type;

    /**
     *
     */
    private MultipartFile multipartFile;

    /**
     * Constructor.
     */
    FileEditor(final File file) {
        super(file);
        this.file = file;
    }

    @Override
    public File update() {
        final FileBuilder builder = File.getBuilder();
        builder.addId(getId())
                .addValidated(isValidated())
                .addTitle(getTitle())
                .addMultipartFile(getMultipartFile())
                .addType(getType());
        return builder.build();
    }

    public FileEditor addTitle(final String title) {
        this.title = title;
        return this;
    }

    public FileEditor addUrl(final String url) {
        this.url = url;
        return this;
    }

    public FileEditor addType(final FileType type) {
        this.type = type;
        return this;
    }

    public FileEditor addMultipartFile(final MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
        return this;
    }

    private String getTitle() {
        return isNotNull(this.title) ? this.title : this.file.getTitle();
    }

    private String getUrl() {
        return isNotNull(this.url) ? this.url : this.file.getUrl();
    }

    private FileType getType() {
        return isNotNull(this.type) ? this.type : this.file.getType();
    }

    private MultipartFile getMultipartFile() {
        return isNotNull(this.multipartFile) ? this.multipartFile : this.file.getMultipartFile();
    }
}
