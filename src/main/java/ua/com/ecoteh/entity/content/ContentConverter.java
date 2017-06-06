package ua.com.ecoteh.entity.content;

import ua.com.ecoteh.entity.model.ModelConverter;

/**
 * The class implements a set of methods
 * for converting contents to content entities.
 *
 * @param <T> content type, extends {@link Content}.
 * @param <E> content entity type, extends {@link ContentEntity}.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Content
 * @see ContentEntity
 */
public abstract class ContentConverter<T extends Content, E extends ContentEntity>
        extends ModelConverter<T, E> {

    /**
     * Constructor.
     *
     * @param content the content for converting to content entity.
     */
    protected ContentConverter(final T content) {
        super(content);
    }
}
