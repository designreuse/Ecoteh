package ua.com.ecoteh.entity.model;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class ModelEntityConverter<T extends Model> implements Converter<T>{

    protected ModelEntityConverter() {
    }

    @Override
    public T convert() {
        return prepareBuilder().build();
    }

    protected abstract ModelBuilder<T, ?> prepareBuilder();
}
