package ua.com.ecoteh.entity.file;

import ua.com.ecoteh.entity.model.ModelEntityConverter;

/**
 * The class implements a set of methods
 * for converting file entities to files.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see FileEntity
 * @see File
 */
final class FileEntityConverter extends ModelEntityConverter<FileEntity, File> {

    /**
     * The file entity for converting to file.
     */
    private final FileEntity entity;

    /**
     * Constructor.
     *
     * @param entity the file entity for converting to file.
     */
    FileEntityConverter(final FileEntity entity) {
        super(entity);
        this.entity = entity;
    }

    /**
     * Prepares and returns a file builder for creating
     * a new converted file.
     *
     * @return the prepared file builder.
     */
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
