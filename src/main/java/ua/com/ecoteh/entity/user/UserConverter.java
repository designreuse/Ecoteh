package ua.com.ecoteh.entity.user;

import ua.com.ecoteh.entity.model.ModelConverter;
import ua.com.ecoteh.entity.response.Response;
import ua.com.ecoteh.entity.response.ResponseEntity;
import ua.com.ecoteh.util.encryption.Base64Encryptor;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * The class implements a set of methods
 * for converting users to user entities.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Response
 * @see ResponseEntity
 */
final class UserConverter extends ModelConverter<User, UserEntity> {

    /**
     * The user for converting to user entity.
     */
    private final User user;

    /**
     * Constructor.
     *
     * @param user the user for converting to user entity.
     */
    UserConverter(final User user) {
        super(user);
        this.user = user;
    }

    /**
     * Converts the user and returns a new user entity.
     *
     * @return The converted user entity (newer null).
     */
    @Override
    public UserEntity convert() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setId(this.user.getId());
        userEntity.setValidated(this.user.isValidated());
        userEntity.setName(this.user.getName());
        userEntity.setUrl(this.user.getUrl());
        userEntity.setLogin(encrypt(this.user.getLogin()));
        userEntity.setPassword(encrypt(this.user.getPassword()));
        userEntity.setDescription(this.user.getDescription());
        userEntity.setRole(this.user.getRole());
        userEntity.setMailing(this.user.isMailing());
        userEntity.setLocked(this.user.isLocked());
        userEntity.setContactsEntity(this.user.getContacts().convert());
        userEntity.setPhotoEntity(this.user.getPhoto().convert());
        return userEntity;
    }

    /**
     * Encrypts the incoming value and returns it.
     * <pre>
     *     encrypt(null) -> empty string
     *     encrypt("") -> empty string
     *     encrypt(" ") -> empty string
     *     encrypt("   ") -> empty string
     *     encrypt("value") -> some encrypted value
     * </pre>
     *
     * @param value the value to encrypt.
     * @return the encrypted value or empty string (newer null).
     * @see Base64Encryptor
     */
    private String encrypt(final String value) {
        return isNotEmpty(value) ? new Base64Encryptor(value).encrypt() : "";
    }
}
