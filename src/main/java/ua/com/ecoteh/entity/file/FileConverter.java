package ua.com.ecoteh.entity.file;

import ua.com.ecoteh.entity.model.ModelConverter;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class FileConverter extends ModelConverter<File, FileEntity> {

    private final File file;

    /**
     * Constructor.
     * @param file
     */
    FileConverter(final File file) {
        super(file);
        this.file = file;
    }

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
