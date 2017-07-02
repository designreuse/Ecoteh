package ua.com.ecoteh.service.data.comparator;

import ua.com.ecoteh.entity.user.User;
import ua.com.ecoteh.entity.user.UserRole;

import java.util.Comparator;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of methods for working
 * with comparators for objects of the {@link User} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class UserComparator extends AbstractComparator {

    /**
     * Constructor.
     */
    private UserComparator() {
    }

    /**
     * The class implements a method
     * for working with comparator for {@link User} by name.
     */
    public final static class ByName implements Comparator<User> {

        /**
         * Compares two users by name.
         * <pre>
         *     compare(null, null) = 0
         *     compare(null, new User()) = -1
         *     compare(new User(), null) = 1
         *     compare(new User(), new User()) = compares by name
         * </pre>
         *
         * @param first  the first user to be compared.
         * @param second the second user to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first userEntity name is less than, equal to, or greater than the
         * second userEntity name.
         */
        @Override
        public int compare(final User first, final User second) {
            int result = UserComparator.compare(first, second);
            if (result == 2) {
                result = compareByName(first, second);
            }
            return result;
        }

        /**
         * Compares two users by name.
         *
         * @param first  the first user to be compared (newer null).
         * @param second the second user to be compared (newer null).
         * @return A negative integer, zero, or a positive integer as the
         * first userEntity name is less than, equal to, or greater than the
         * second userEntity name.
         */
        private static int compareByName(final User first, final User second) {
            final String firstName = first.getName();
            final String secondName = second.getName();
            return firstName.compareToIgnoreCase(secondName);
        }
    }

    /**
     * The class implements a method for working
     * with comparator for {@link User} by URL.
     */
    public final static class ByUrl implements Comparator<User> {

        /**
         * Compares two users by URL.
         * <pre>
         *     compare(null, null) = 0
         *     compare(null, new User()) = -1
         *     compare(new User(), null) = 1
         *     compare(new User(), new User()) = compares by URL
         * </pre>
         *
         * @param first  the first user to be compared.
         * @param second the second user to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first userEntity URL is less than, equal to, or greater than the
         * second userEntity URL.
         */
        @Override
        public int compare(final User first, final User second) {
            int result = UserComparator.compare(first, second);
            if (result == 2) {
                result = compareByUrl(first, second);
            }
            return result;
        }

        /**
         * Compares two users by URL.
         *
         * @param first  the first user to be compared (newer null).
         * @param second the second user to be compared (newer null).
         * @return A negative integer, zero, or a positive integer as the
         * first userEntity URL is less than, equal to, or greater than the
         * second userEntity URL.
         */
        private static int compareByUrl(final User first, final User second) {
            final String firstUrl = first.getUrl();
            final String secondUrl = second.getUrl();
            return firstUrl.compareToIgnoreCase(secondUrl);
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
            this.role = isNotNull(role) ? role : UserRole.ANOTHER;
        }

        /**
         * Compares two users by role.
         * <pre>
         *     compare(null, null) = 0
         *     compare(null, new User()) = -1
         *     compare(new User(), null) = 1
         *     compare(new User(), new User()) = compares by role
         * </pre>
         *
         * @param first  the first user to be compared.
         * @param second the second user to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first userEntity role is less than, equal to, or greater than the
         * second userEntity role.
         */
        @Override
        public int compare(final User first, final User second) {
            int result = UserComparator.compare(first, second);
            if (result == 2) {
                result = compareByRole(first, second);
            }
            return result;
        }

        /**
         * Compares two users by role.
         *
         * @param first  the first user to be compared (newer null).
         * @param second the second user to be compared (newer null).
         * @return A negative integer, zero, or a positive integer as the
         * first userEntity role is less than, equal to, or greater than the
         * second userEntity role.
         */
        private int compareByRole(final User first, final User second) {
            int result;
            if (equalsRole(first, this.role)) {
                result = 1;
            } else if (equalsRole(second, this.role)) {
                result = -1;
            } else {
                result = 0;
            }
            return result;
        }

        /**
         * Returns true if the user role is equal to the incoming role.
         * <pre>
         *     User user = new User();
         *     userEntity.setRole(UserRole.ANOTHER);
         *     equalsRole(userEntity, UserRole.ANOTHER) = true
         *     equalsRole(userEntity, UserRole.ADMIN) = false
         * </pre>
         *
         * @param user the user to equals to role (newer null).
         * @param role the role to equals (newer null).
         * @return true if the user role is equal to the incoming role.
         */
        private boolean equalsRole(final User user, final UserRole role) {
            final UserRole userRole = user.getRole();
            return userRole.equals(role);
        }
    }
}
