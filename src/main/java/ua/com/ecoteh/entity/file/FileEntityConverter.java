package ua.com.ecoteh.entity.file;

import ua.com.ecoteh.entity.model.ModelEntityConverter;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class FileEntityConverter extends ModelEntityConverter<File> {

    private final FileEntity entity;

    FileEntityConverter(final FileEntity entity) {
        this.entity = entity;
    }

    @Override
    protected FileBuilder prepareBuilder() {
        final FileBuilder builder = new FileBuilder();
        if (isNotNull(this.entity)) {
            builder.addId(this.entity.getId())
                    .addValidated(this.entity.isValidated())
                    .addTitle(this.entity.getTitle())
                    .addUrl(this.entity.getUrl())
                    .addType(this.entity.getType());
        }
        return builder;
    }
}
