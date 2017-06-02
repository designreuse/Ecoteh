package ua.com.ecoteh.entity.file;

import org.springframework.web.multipart.MultipartFile;
import ua.com.ecoteh.entity.model.ModelBuilder;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class FileBuilder extends ModelBuilder<File, FileBuilder> {

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

    private MultipartFile multipartFile;

    /**
     *
     */
    FileBuilder() {
    }

    @Override
    public File build() {
        return new File(
                getId(), isValidated(),
                getTitle(), getUrl(),
                getType(), getMultipartFile()
        );
    }

    public FileBuilder addTitle(String title) {
        this.title = title;
        return this;
    }

    public FileBuilder addUrl(String url) {
        this.url = url;
        return this;
    }

    public FileBuilder addType(FileType type) {
        this.type = type;
        return this;
    }

    public FileBuilder addMultipartFile(final MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
        return this;
    }

    private String getTitle() {
        return isNotEmpty(this.title) ? this.title : "";
    }

    private String getUrl() {
        return isNotEmpty(this.url) ? this.url : "";
    }

    private FileType getType() {
        return isNotNull(this.type) ? this.type : FileType.ANOTHER;
    }

    private MultipartFile getMultipartFile() {
        return this.multipartFile;
    }
}
