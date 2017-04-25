package ua.com.ecoteh.util.comparator;

import ua.com.ecoteh.entity.Response;

import java.util.Comparator;

/**
 * The class implements a set of methods
 * for working with comparators for {@link Response}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public final class ResponseComparator {

    /**
     * The class implements a method
     * for working with comparator for {@link Response} by date.
     */
    public final static class ByDate implements Comparator<Response> {

        /**
         * Compares its two arguments for order.
         *
         * @param response1 The first object to be compared.
         * @param response2 The second object to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first argument is less than, equal to, or greater than the
         * second.
         */
        @Override
        public int compare(
                final Response response1,
                final Response response2
        ) {
            int result;
            if ((response1 == null) && (response2 == null)) {
                result = 0;
            } else if (response1 == null) {
                result = -1;
            } else if (response2 == null) {
                result = 1;
            } else {
                result = response1.getDate().compareTo(response2.getDate());
            }
            return result;
        }
    }
}
