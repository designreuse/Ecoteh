package ua.com.ecoteh.entity.file;

import ua.com.ecoteh.entity.model.ModelConverter;

/**
 * The class implements a set of methods
 * for converting files to file entities.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see File
 * @see FileEntity
 */
final class FileConverter extends ModelConverter<File, FileEntity> {

    /**
     * The file for converting to file entity.
     */
    private final File file;

    /**
     * Constructor.
     *
     * @param file the file for converting to file entity.
     */
    FileConverter(final File file) {
        super(file);
        this.file = file;
    }

    /**
     * Converts the file and returns a new file entity.
     *
     * @return The converted file entity (newer null).
     */
    @Override
    public FileEntity convert() {
        final FileEntity fileEntity = new FileEntity();
        fileEntity.setId(this.file.getId());
        fileEntity.setValidated(this.file.isValidated());
        fileEntity.setTitle(this.file.getTitle());
        fileEntity.setUrl(this.file.getUrl());
        fileEntity.setType(this.file.getType());
        return fileEntity;
    }
}
