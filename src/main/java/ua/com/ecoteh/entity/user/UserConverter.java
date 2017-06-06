package ua.com.ecoteh.entity.user;

import ua.com.ecoteh.entity.model.ModelConverter;
import ua.com.ecoteh.util.encryption.Base64Encryptor;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class UserConverter extends ModelConverter<User, UserEntity> {

    private final User user;

    /**
     * Constructor.
     * @param user
     */
    UserConverter(final User user) {
        super(user);
        this.user = user;
    }

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

    private String encrypt(final String value) {
        return isNotEmpty(value) ? new Base64Encryptor(value).encrypt() : "";
    }
}
