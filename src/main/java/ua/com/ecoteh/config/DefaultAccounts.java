package ua.com.ecoteh.config;

import org.apache.log4j.Logger;
import ua.com.ecoteh.entity.user.User;
import ua.com.ecoteh.entity.user.UserBuilder;
import ua.com.ecoteh.entity.user.UserRole;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * Class to work with the default users.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class DefaultAccounts {

    /**
     * The object for logging information.
     */
    private static final Logger LOGGER = Logger.getLogger(DefaultAccounts.class);

    /**
     * The map of a default users.
     */
    private final static Map<String, User> USERS;

    /**
     * Static block for initialization of the global map
     * with a default users.
     */
    static {
        USERS = new ConcurrentHashMap<>();
        addDefaultAdmin();
        addSuperAdmin();
    }

    /**
     * Returns a default user with the username.
     *
     * @param username the name of user to return.
     * @return The user if it exist, null otherwise.
     */
    public static User get(final String username) {
        User user;
        try {
            user = USERS.get(username);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            user = null;
        }
        return user;
    }

    /**
     * Returns a default admin.
     *
     * @return The default admin.
     */
    public static User getDefaultAdmin() {
        return get("admin");
    }

    /**
     * Returns a default super admin.
     *
     * @return The default super admin.
     */
    public static User getSuperAdmin() {
        return get("superadmin");
    }

    /**
     * Check if the incoming user is a default user.
     * <pre>
     *      isDefaultAdmin(null) - false
     *      isDefaultAdmin(unknown_user) - false
     *      isDefaultAdmin(default_admin) - true
     * </pre>
     *
     * @param user the user to check.
     * @return true if the incoming user is default admin, false otherwise.
     */
    public static boolean isDefaultAdmin(final User user) {
        return isNotNull(user) && USERS.containsValue(user);
    }

    /**
     * Adds a default admin to global map.
     * Creates a user with ADMIN role
     * and adds it to global map.
     */
    private static void addDefaultAdmin() {
        final User user = createUser(
                "Default Admin",
                "login", "password",
                UserRole.ADMIN
        );
        USERS.put(user.getLogin(), user);
    }

    /**
     * Adds a super admin to global map.
     * Creates a user with SUPERADMIN role
     * and adds it to global map.
     */
    private static void addSuperAdmin() {
        final User user = createUser(
                "Super Admin",
                "login", "password",
                UserRole.SUPERADMIN
        );
        USERS.put(user.getLogin(), user);
    }

    /**
     * Creates a new user and returns it.
     *
     * @param name     the name of a new user.
     * @param login    the login of a new user.
     * @param password the password of a new user.
     * @param role     the role of a new user.
     * @return The new user.
     */
    private static User createUser(
            final String name,
            final String login,
            final String password,
            final UserRole role
    ) {
        final UserBuilder userBuilder = User.getBuilder();
        userBuilder.addName(name).addLogin(login).addPassword(password).addRole(role);
        return userBuilder.build();
    }
}
