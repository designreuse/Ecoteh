package ua.com.ecoteh.entity.model;

/**
 * The interface provides a set of methods for converting objects.
 *
 * @param <T> object type.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public interface Converter<T> {

    /**
     * Converts and returns a object.
     *
     * @return The converted object.
     */
    T convert();

    /**
     * Converts and returns a object.
     * Synchronized method.
     *
     * @return The converted object.
     */
    T syncConvert();
}
