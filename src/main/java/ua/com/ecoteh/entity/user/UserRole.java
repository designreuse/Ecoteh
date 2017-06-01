package ua.com.ecoteh.entity.user;

import java.util.Arrays;

/**
 * The role of some user.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
public enum UserRole {

    /**
     * The super admin role.
     */
    SUPERADMIN,

    /**
     * The admin role.
     */
    ADMIN,

    /**
     * The client role.
     */
    CLIENT,

    /**
     * The another role.
     */
    ANOTHER;

    /**
     * Returns the elements of this enum class or null if this
     * Class object does not represent an enum type.
     *
     * @return an array containing the values comprising the enum class
     * represented by this Class object in the order they're
     * declared, or null if this Class object does not
     * represent an enum type
     */
    public static UserRole[] getEnumConstants() {
        final UserRole[] roles = UserRole.class.getEnumConstants();
        return Arrays.copyOfRange(roles, 1, roles.length);
    }
}
