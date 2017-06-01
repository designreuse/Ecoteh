package ua.com.ecoteh.util.comparator;

import ua.com.ecoteh.entity.model.ModelEntity;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNull;

/**
 * The class implements a set of methods for working
 * with comparators for objects of the {@link ModelEntity} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class AbstractComparator {

    /**
     * Compares two models.
     * <pre>
     *     compare(null, null) = 0
     *     compare(null, new ModelEntity()) = -1
     *     compare(new ModelEntity(), null) = 1
     *     compare(new ModelEntity(), new ModelEntity()) = 2
     * </pre>
     *
     * @param <T>    entity type, extends {@link ModelEntity}.
     * @param first  the first model to be compared.
     * @param second the second model to be compared.
     * @return A negative integer, zero, or a positive integer.
     */
    protected static <T extends ModelEntity> int compare(final T first, final T second) {
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
