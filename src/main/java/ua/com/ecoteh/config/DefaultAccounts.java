package ua.com.ecoteh.config;

import org.apache.log4j.Logger;
import ua.com.ecoteh.entity.user.UserEntity;
import ua.com.ecoteh.entity.user.UserRole;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    private final static Map<String, UserEntity> USERS;

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
    public static UserEntity get(final String username) {
        UserEntity userEntity;
        try {
            userEntity = USERS.get(username).clone();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            userEntity = null;
        }
        return userEntity;
    }

    /**
     * Returns a default admin.
     *
     * @return The default admin.
     */
    public static UserEntity getDefaultAdmin() {
        return get("admin");
    }

    /**
     * Returns a default super admin.
     *
     * @return The default super admin.
     */
    public static UserEntity getSuperAdmin() {
        return get("superadmin");
    }

    /**
     * Adds a default admin to global map.
     * Creates a user with ADMIN role
     * and adds it to global map.
     */
    private static void addDefaultAdmin() {
        final UserEntity userEntity = createUser(
                "Default Admin",
                "login", "password",
                UserRole.ADMIN
        );
        USERS.put(userEntity.getLogin(), userEntity);
    }

    /**
     * Adds a super admin to global map.
     * Creates a user with SUPERADMIN role
     * and adds it to global map.
     */
    private static void addSuperAdmin() {
        final UserEntity userEntity = createUser(
                "Super Admin",
                "login", "password",
                UserRole.SUPERADMIN
        );
        USERS.put(userEntity.getLogin(), userEntity);
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
    private static UserEntity createUser(
            final String name,
            final String login,
            final String password,
            final UserRole role
    ) {
        final UserEntity userEntity = new UserEntity();
        userEntity.setName(name);
        userEntity.setLogin(login);
        userEntity.setPassword(password);
        userEntity.setRole(role);
        return userEntity;
    }
}
