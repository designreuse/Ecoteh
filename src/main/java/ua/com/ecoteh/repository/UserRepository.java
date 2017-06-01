package ua.com.ecoteh.repository;

import ua.com.ecoteh.entity.user.UserEntity;

/**
 * The interface provides a set of JPA methods
 * for working {@link UserEntity} objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public interface UserRepository extends DataRepository<UserEntity> {

    /**
     * Returns user from a database,
     * which matches the parameter name.
     *
     * @param name the name of a user to return.
     * @return The object of class {@link UserEntity}.
     */
    UserEntity findByName(String name);

    /**
     * Returns user from a database,
     * which matches the parameter login.
     *
     * @param login the login of a user to return.
     * @return The object of class {@link UserEntity}.
     */
    UserEntity findByEncryptedLogin(String login);

    /**
     * Returns user from a database,
     * which matches the parameter url.
     *
     * @param url the URL of a user to return.
     * @return The object of class {@link UserEntity}.
     */
    UserEntity findByUrl(String url);

    /**
     * Removes user from a database,
     * which matches the parameter name.
     *
     * @param name the name of a user to remove.
     */
    void deleteByName(String name);

    /**
     * Removes user from a database,
     * which matches the parameter url.
     *
     * @param url the URL of a user to remove.
     */
    void deleteByUrl(String url);

    /**
     * Returns user from a database,
     * which matches the parameter email.
     *
     * @param email the E-mail of a user to return.
     * @return The object of class {@link UserEntity}.
     */
    UserEntity findByContactsEntityEmail(String email);

    /**
     * Returns user from a database,
     * which matches the parameter mobilePhone.
     *
     * @param phone the mobile phone of a user to return.
     * @return The object of class {@link UserEntity}.
     */
    UserEntity findByContactsEntityMobilePhone(String phone);

    /**
     * Returns user from a database,
     * which matches the parameter landlinePhone.
     *
     * @param phone the landline phone of a user to return.
     * @return The object of class {@link UserEntity}.
     */
    UserEntity findByContactsEntityLandlinePhone(String phone);

    /**
     * Returns user from a database,
     * which matches the parameter fax.
     *
     * @param fax the fax phone of a user to return.
     * @return The object of class {@link UserEntity}.
     */
    UserEntity findByContactsEntityFax(String fax);
}
