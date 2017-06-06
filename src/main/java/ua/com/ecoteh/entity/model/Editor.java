package ua.com.ecoteh.entity.model;

/**
 * The interface provides a set of methods for updating objects.
 *
 * @param <T> object type.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public interface Editor<T> {

    /**
     * Updates and returns a object.
     *
     * @return The updated object.
     */
    T update();

    /**
     * Copies the incoming object.
     * Returns a instance of the editor.
     *
     * @param copied the object to copy.
     * @return The instance of the editor.
     */
    Editor<T> copy(T copied);
}
