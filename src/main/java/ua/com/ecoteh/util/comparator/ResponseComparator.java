package ua.com.ecoteh.util.comparator;

import ua.com.ecoteh.entity.response.ResponseEntity;

import java.util.Comparator;

/**
 * The class implements a set of methods for working
 * with comparators for objects of the {@link ResponseEntity} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ResponseComparator extends AbstractComparator {

    /**
     * The class implements a method
     * for working with comparator for {@link ResponseEntity} by date.
     */
    public final static class ByDate implements Comparator<ResponseEntity> {

        /**
         * Compares two responses by date.
         * <pre>
         *     compare(null, null) = 0
         *     compare(null, new ResponseEntity()) = -1
         *     compare(new ResponseEntity(), null) = 1
         *     compare(new ResponseEntity(), new ResponseEntity()) = compares by date
         * </pre>
         *
         * @param first  the first response to be compared.
         * @param second the second response to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first response date is less than, equal to, or greater than the
         * second response date.
         */
        @Override
        public int compare(final ResponseEntity first, final ResponseEntity second) {
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
        private static int compareByDate(final ResponseEntity first, final ResponseEntity second) {
            return first.getDate().compareTo(second.getDate());
        }
    }
}
