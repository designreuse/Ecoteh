package ua.com.ecoteh.entity.file;

import ua.com.ecoteh.entity.model.ModelEntityConverter;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class FileEntityConverter extends ModelEntityConverter<FileEntity, File> {

    FileEntityConverter(final FileEntity entity) {
        super(entity);
    }

    @Override
    protected FileBuilder prepareBuilder() {
        final FileBuilder builder = new FileBuilder();
        builder.addId(this.entity.getId())
                .addValidated(this.entity.isValidated())
                .addTitle(this.entity.getTitle())
                .addUrl(this.entity.getUrl())
                .addType(this.entity.getType());
        return builder;
    }
}
