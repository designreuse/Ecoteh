package ua.com.ecoteh.entity.model;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public interface Converter<T> {

    T convert();

    T syncConvert();
}
