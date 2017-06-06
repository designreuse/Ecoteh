package ua.com.ecoteh.entity.model;

/**
 * The class implements a set of methods
 * for converting model entities to models.
 *
 * @param <E> model entity type, extends {@link ModelEntity}.
 * @param <T> model type, extends {@link Model}.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see ModelEntity
 * @see Model
 */
public abstract class ModelEntityConverter<E extends ModelEntity, T extends Model> implements Converter<T> {

    /**
     * The model entity for converting to model.
     */
    private final E entity;

    /**
     * Constructor.
     *
     * @param entity the model entity for converting to model.
     */
    protected ModelEntityConverter(final E entity) {
        this.entity = entity;
    }

    /**
     * Converts the model entity and returns a new model.
     *
     * @return The converted model (newer null).
     */
    @Override
    public T convert() {
        return prepareBuilder().build();
    }

    /**
     * Converts the model entity and returns a new model.
     * Synchronized method, locks by the model entity.
     *
     * @return The converted model (newer null).
     */
    @Override
    public T syncConvert() {
        synchronized (this.entity) {
            return convert();
        }
    }

    /**
     * Prepares and returns a model builder for creating
     * a new converted model.
     *
     * @return the prepared model builder.
     */
    protected abstract ModelBuilder<T, ?> prepareBuilder();
}
