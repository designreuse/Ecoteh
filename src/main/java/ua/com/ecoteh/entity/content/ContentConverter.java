package ua.com.ecoteh.entity.content;

import ua.com.ecoteh.entity.model.ModelConverter;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class ContentConverter<T extends Content, E extends ContentEntity>
        extends ModelConverter<T, E> {

    /**
     * Constructor.
     * @param content
     */
    protected ContentConverter(final T content) {
        super(content);
    }
}
