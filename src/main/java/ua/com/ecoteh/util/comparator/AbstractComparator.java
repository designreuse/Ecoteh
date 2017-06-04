package ua.com.ecoteh.util.comparator;

import ua.com.ecoteh.entity.model.Model;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNull;

/**
 * The class implements a set of methods for working
 * with comparators for objects of the {@link Model} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class AbstractComparator {

    /**
     * Compares two models.
     * <pre>
     *     compare(null, null) = 0
     *     compare(null, new Model()) = -1
     *     compare(new Model(), null) = 1
     *     compare(new Model(), new Model()) = 2
     * </pre>
     *
     * @param <T>    entity type, extends {@link Model}.
     * @param first  the first model to be compared.
     * @param second the second model to be compared.
     * @return A negative integer, zero, or a positive integer.
     */
    protected static <T extends Model> int compare(final T first, final T second) {
        int result;
        if (isNull(first) && isNull(second)) {
            result = 0;
        } else if (isNull(first)) {
            result = -1;
        } else if (isNull(second)) {
            result = 1;
        } else {
            result = 2;
        }
        return result;
    }
}
