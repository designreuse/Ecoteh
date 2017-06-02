package ua.com.ecoteh.entity.user;

import ua.com.ecoteh.entity.model.ModelConverter;
import ua.com.ecoteh.util.encryption.Base64Encryptor;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class UserConverter extends ModelConverter<User, UserEntity> {

    UserConverter(final User model) {
        super(model);
    }

    @Override
    public UserEntity convert() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setId(this.model.getId());
        userEntity.setValidated(this.model.isValidated());
        userEntity.setName(this.model.getName());
        userEntity.setUrl(this.model.getUrl());
        userEntity.setLogin(encrypt(this.model.getLogin()));
        userEntity.setPassword(encrypt(this.model.getPassword()));
        userEntity.setDescription(this.model.getDescription());
        userEntity.setRole(this.model.getRole());
        userEntity.setMailing(this.model.isMailing());
        userEntity.setLocked(this.model.isLocked());
        userEntity.setContactsEntity(this.model.getContacts().convert());
        userEntity.setPhotoEntity(this.model.getPhoto().convert());
        return userEntity;
    }

    private String encrypt(final String value) {
        return isNotEmpty(value) ? new Base64Encryptor(value).encrypt() : "";
    }
}
