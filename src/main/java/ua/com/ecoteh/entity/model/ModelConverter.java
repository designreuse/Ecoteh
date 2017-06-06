package ua.com.ecoteh.entity.model;

/**
 * The class implements a set of methods
 * for converting models to model entities.
 *
 * @param <T> model type, extends {@link Model}.
 * @param <E> model entity type, extends {@link ModelEntity}.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Model
 * @see ModelEntity
 */
public abstract class ModelConverter<T extends Model, E extends ModelEntity> implements Converter<E> {

    /**
     * The model for converting to model entity.
     */
    private final T model;

    /**
     * Constructor.
     *
     * @param model the model for converting to model entity.
     */
    protected ModelConverter(final T model) {
        this.model = model;
    }

    /**
     * Converts the model and returns a new model entity.
     * Synchronized method, locks by the model.
     *
     * @return The converted model entity (newer null).
     */
    @Override
    public E syncConvert() {
        synchronized (this.model) {
            return convert();
        }
    }
}
