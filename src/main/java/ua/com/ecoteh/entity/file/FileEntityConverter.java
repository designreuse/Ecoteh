package ua.com.ecoteh.entity.file;

import ua.com.ecoteh.entity.model.ModelEntityConverter;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class FileEntityConverter extends ModelEntityConverter<FileEntity, File> {

    private final FileEntity entity;

    /**
     * Constructor.
     * @param entity
     */
    FileEntityConverter(final FileEntity entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    protected FileBuilder prepareBuilder() {
        final FileBuilder builder = File.getBuilder();
        builder.addId(this.entity.getId())
                .addValidated(this.entity.isValidated())
                .addTitle(this.entity.getTitle())
                .addUrl(this.entity.getUrl())
                .addType(this.entity.getType());
        return builder;
    }
}
