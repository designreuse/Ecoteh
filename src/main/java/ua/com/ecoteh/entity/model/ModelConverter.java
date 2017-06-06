package ua.com.ecoteh.entity.model;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class ModelConverter<T extends Model, E extends ModelEntity> implements Converter<E> {

    private final T model;

    /**
     * Constructor.
     * @param model
     */
    protected ModelConverter(final T model) {
        this.model = model;
    }

    @Override
    public E syncConvert() {
        synchronized (this.model) {
            return convert();
        }
    }
}
