package ua.com.ecoteh.entity.user;

import ua.com.ecoteh.entity.model.ModelEntityConverter;
import ua.com.ecoteh.util.encryption.Base64Encryptor;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * The class implements a set of methods
 * for converting user entities to users.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see UserEntity
 * @see User
 */
final class UserEntityConverter extends ModelEntityConverter<UserEntity, User> {

    /**
     * The user entity for converting to user.
     */
    private final UserEntity entity;

    /**
     * Constructor.
     *
     * @param entity the user entity for converting to user.
     */
    UserEntityConverter(final UserEntity entity) {
        super(entity);
        this.entity = entity;
    }

    /**
     * Prepares and returns a user builder for creating
     * a new converted user.
     *
     * @return the prepared user builder.
     */
    @Override
    protected UserBuilder prepareBuilder() {
        final UserBuilder builder = User.getBuilder();
        builder.addId(this.entity.getId())
                .addName(this.entity.getName())
                .addUrl(this.entity.getUrl())
                .addLogin(decrypt(this.entity.getLogin()))
                .addPassword(decrypt(this.entity.getPassword()))
                .addDescription(this.entity.getDescription())
                .addRole(this.entity.getRole())
                .addMailing(this.entity.isMailing())
                .addLocked(this.entity.isLocked())
                .addContacts(this.entity.getContactsEntity().convert())
                .addPhoto(this.entity.getPhotoEntity().convert());
        return builder;
    }

    /**
     * Decrypts the incoming value and returns it.
     * <pre>
     *     decrypt(null) -> empty string
     *     decrypt("") -> empty string
     *     decrypt(" ") -> empty string
     *     decrypt("   ") -> empty string
     *     decrypt("value") -> some decrypted value
     * </pre>
     *
     * @param value the value to decrypt.
     * @return the decrypted value or empty string (newer null).
     * @see Base64Encryptor
     */
    private String decrypt(final String value) {
        return isNotEmpty(value) ? new Base64Encryptor(value).decrypt() : "";
    }
}
