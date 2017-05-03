package ua.com.ecoteh.util.comparator;

import ua.com.ecoteh.entity.User;
import ua.com.ecoteh.enums.UserRole;

import java.util.Comparator;

/**
 * The class implements a set of methods
 * for working with comparators for {@link User}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public final class UserComparator {

    /**
     * The class implements a method
     * for working with comparator for {@link User} by name.
     */
    public final static class ByName implements Comparator<User> {

        /**
         * Compares its two arguments for order.
         *
         * @param user1 the first object to be compared.
         * @param user2 the second object to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first argument is less than, equal to, or greater than the
         * second.
         */
        @Override
        public int compare(final User user1, final User user2) {
            int result;
            if ((user1 == null) && (user2 == null)) {
                result = 0;
            } else if (user1 == null) {
                result = -1;
            } else if (user2 == null) {
                result = 1;
            } else {
                result = user1.getName().compareToIgnoreCase(user2.getName());
            }
            return result;
        }
    }

    /**
     * The class implements a method for working
     * with comparator for {@link User} by URL.
     */
    public final static class ByUrl implements Comparator<User> {

        /**
         * Compares its two arguments for order.
         *
         * @param user1 the first object to be compared.
         * @param user2 the second object to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first argument is less than, equal to, or greater than the
         * second.
         */
        @Override
        public int compare(final User user1, final User user2) {
            int result;
            if (user1 == null && user2 == null) {
                result = 0;
            } else if (user1 == null) {
                result = -1;
            } else if (user2 == null) {
                result = 1;
            } else {
                result = user1.getUrl().compareToIgnoreCase(user2.getUrl());
            }
            return result;
        }
    }

    /**
     * The class implements a method for working
     * with comparator for {@link User} by role.
     */
    public final static class ByRole implements Comparator<User> {

        /**
         * The role comparing.
         */
        private final UserRole role;

        /**
         * Default constructor.
         *
         * @param role The role comparing.
         */
        public ByRole(final UserRole role) {
            this.role = role;
        }

        /**
         * Compares its two arguments for order.
         *
         * @param user1 the first object to be compared.
         * @param user2 the second object to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first argument is less than, equal to, or greater than the
         * second.
         */
        @Override
        public int compare(final User user1, final User user2) {
            int result = 0;
            if (user1 == null && user2 == null) {
                result = 0;
            } else if (user1 == null) {
                result = -1;
            } else if (user2 == null ||
                    ((user1.getRole() != null) && (user1.getRole().equals(role)))) {
                result = 1;
            } else if ((user2.getRole() != null) &&
                    (user2.getRole().equals(role))) {
                result = -1;
            }
            return result;
        }
    }
}
