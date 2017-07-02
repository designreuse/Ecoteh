package ua.com.ecoteh.service.data.comparator;

import ua.com.ecoteh.entity.response.Response;

import java.util.Comparator;
import java.util.Date;

/**
 * The class implements a set of methods for working
 * with comparators for objects of the {@link Response} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class ResponseComparator extends AbstractComparator {

    /**
     * Constructor.
     */
    private ResponseComparator() {
    }

    /**
     * The class implements a method
     * for working with comparator for {@link Response} by date.
     */
    public final static class ByDate implements Comparator<Response> {

        /**
         * Compares two responses by date.
         * <pre>
         *     compare(null, null) = 0
         *     compare(null, new Response()) = -1
         *     compare(new Response(), null) = 1
         *     compare(new Response(), new Response()) = compares by date
         * </pre>
         *
         * @param first  the first response to be compared.
         * @param second the second response to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first response date is less than, equal to, or greater than the
         * second response date.
         */
        @Override
        public int compare(final Response first, final Response second) {
            int result = ResponseComparator.compare(first, second);
            if (result == 2) {
                result = compareByDate(first, second);
            }
            return result;
        }

        /**
         * Compares two responses by date.
         *
         * @param first  the first response to be compared (newer null).
         * @param second the second response to be compared (newer null).
         * @return A negative integer, zero, or a positive integer as the
         * first response date is less than, equal to, or greater than the
         * second response date.
         */
        private int compareByDate(final Response first, final Response second) {
            final Date firstDate = first.getDate();
            final Date secondDate = second.getDate();
            return firstDate.compareTo(secondDate);
        }
    }
}
