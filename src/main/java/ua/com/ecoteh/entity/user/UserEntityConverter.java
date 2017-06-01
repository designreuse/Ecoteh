package ua.com.ecoteh.entity.user;

import ua.com.ecoteh.entity.model.ModelEntityConverter;
import ua.com.ecoteh.util.encryption.Base64Encryptor;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class UserEntityConverter extends ModelEntityConverter<User> {

    private final UserEntity entity;

    UserEntityConverter(final UserEntity entity) {
        this.entity = entity;
    }

    @Override
    protected UserBuilder prepareBuilder() {
        final UserBuilder builder = new UserBuilder();
        if (isNotNull(this.entity)) {
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
        }
        return builder;
    }

    private String decrypt(final String value) {
        return isNotEmpty(value) ? new Base64Encryptor(value).decrypt() : "";
    }
}
