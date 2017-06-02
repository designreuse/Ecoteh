package ua.com.ecoteh.entity.model;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class ModelEntityConverter<E extends ModelEntity, T extends Model> implements Converter<T>{

    protected final E entity;

    protected ModelEntityConverter(final E entity) {
        this.entity = entity;
    }

    @Override
    public T convert() {
        return prepareBuilder().build();
    }

    @Override
    public T syncConvert() {
        synchronized (this.entity) {
            return convert();
        }
    }

    protected abstract ModelBuilder<T, ?> prepareBuilder();
}
