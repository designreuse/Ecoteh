package ua.com.ecoteh.entity.file;

import ua.com.ecoteh.entity.model.ModelConverter;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class FileConverter extends ModelConverter<FileEntity> {

    private final File file;

    FileConverter(final File file) {
        this.file = file;
    }

    @Override
    public FileEntity convert() {
        File file;
        if (isNotNull(this.file)) {
            file = this.file;
        } else {
            file = new FileBuilder().build();
        }
        return convertToEntity(file);
    }

    private FileEntity convertToEntity(final File file) {
        final FileEntity fileEntity = new FileEntity();
        fileEntity.setId(file.getId());
        fileEntity.setValidated(file.isValidated());
        fileEntity.setTitle(file.getTitle());
        fileEntity.setUrl(file.getUrl());
        fileEntity.setType(file.getType());
        return fileEntity;
    }
}
