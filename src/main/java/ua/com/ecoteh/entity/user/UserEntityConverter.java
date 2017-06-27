package ua.com.ecoteh.entity.user;

import ua.com.ecoteh.entity.model.ModelEntityConverter;

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
                .addValidated(this.entity.isValidated())
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
}
