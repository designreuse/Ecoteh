package ua.com.ecoteh.entity.content;

import ua.com.ecoteh.entity.model.ModelEntityConverter;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class ContentEntityConverter<T extends Content> extends ModelEntityConverter<T> {

    protected ContentEntityConverter() {
    }

    @Override
    protected abstract ContentBuilder<T, ?> prepareBuilder();
}
