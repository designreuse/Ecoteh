package ua.com.ecoteh.entity.file;

import ua.com.ecoteh.entity.model.ModelConverter;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class FileConverter extends ModelConverter<File, FileEntity> {

    FileConverter(final File model) {
        super(model);
    }

    @Override
    public FileEntity convert() {
        final FileEntity fileEntity = new FileEntity();
            fileEntity.setId(this.model.getId());
            fileEntity.setValidated(this.model.isValidated());
            fileEntity.setTitle(this.model.getTitle());
            fileEntity.setUrl(this.model.getUrl());
            fileEntity.setType(this.model.getType());
        return fileEntity;
    }
}
