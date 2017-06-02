package ua.com.ecoteh.entity.content;

import ua.com.ecoteh.entity.model.ModelEntityConverter;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class ContentEntityConverter<E extends ContentEntity, T extends Content>
        extends ModelEntityConverter<E, T> {

    protected ContentEntityConverter(final E contentEntity) {
        super(contentEntity);
    }

    @Override
    protected abstract ContentBuilder<T, ?> prepareBuilder();
}
