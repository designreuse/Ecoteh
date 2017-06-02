package ua.com.ecoteh.entity.model;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class ModelConverter<T extends Model, E extends ModelEntity> implements Converter<E> {

    protected final T model;

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
