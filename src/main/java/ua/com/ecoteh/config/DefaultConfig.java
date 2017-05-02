package ua.com.ecoteh.config;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import ua.com.ecoteh.entity.User;
import ua.com.ecoteh.enums.UserRole;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class to work with the default users.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public final class DefaultConfig {

    /**
     * The object for logging information.
     */
    private static final Logger LOGGER = Logger.getLogger(DefaultConfig.class);

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
    public static User getDefaultUser(final String username) {
        User user;
        try {
            user = USERS.get(username).clone();
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
        return getDefaultUser("admin");
    }

    /**
     * Returns a default super admin.
     *
     * @return The default super admin.
     */
    public static User getSuperAdmin() {
        return getDefaultUser("superadmin");
    }

    /**
     * Returns authenticated user.
     * If authenticated user is empty then returns null.
     *
     * @return The authenticated user or null.
     */
    private static User getAuthenticatedUser() {
        User user;
        try {
            user = (User) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
        } catch (NullPointerException ex) {
            LOGGER.error(ex.getMessage(), ex);
            user = null;
        }
        return user;
    }

    /**
     * Adds a default admin to global map.
     * Creates a user with ADMIN role
     * and adds it to global map.
     */
    private static void addDefaultAdmin() {
        final User user = createUser(
                "Default Admin",
                "admin", "eco20pass17",
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
                "superadmin", "super20pass17",
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
        final User user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        return user;
    }
}
