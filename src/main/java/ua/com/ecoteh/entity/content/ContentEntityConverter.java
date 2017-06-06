package ua.com.ecoteh.entity.content;

import ua.com.ecoteh.entity.model.ModelEntityConverter;

/**
 * The class implements a set of methods
 * for converting content entities to contents.
 *
 * @param <E> content entity type, extends {@link ContentEntity}.
 * @param <T> content type, extends {@link Content}.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see ContentEntity
 * @see Content
 */
public abstract class ContentEntityConverter<E extends ContentEntity, T extends Content>
        extends ModelEntityConverter<E, T> {

    /**
     * Constructor.
     *
     * @param entity rhe content entity for converting to content.
     */
    protected ContentEntityConverter(final E entity) {
        super(entity);
    }

    /**
     * Prepares and returns a content builder for creating
     * a new converted content.
     *
     * @return the prepared content builder.
     */
    @Override
    protected abstract ContentBuilder<T, ?> prepareBuilder();
}
