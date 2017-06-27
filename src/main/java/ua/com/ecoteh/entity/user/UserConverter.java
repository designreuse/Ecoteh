package ua.com.ecoteh.entity.user;

import ua.com.ecoteh.entity.model.ModelConverter;
import ua.com.ecoteh.entity.response.Response;
import ua.com.ecoteh.entity.response.ResponseEntity;

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
        final UserEntity entity = new UserEntity();
        entity.setId(this.user.getId());
        entity.setValidated(this.user.isValidated());
        entity.setName(this.user.getName());
        entity.setUrl(this.user.getUrl());
        entity.setDescription(this.user.getDescription());
        entity.setRole(this.user.getRole());
        entity.setMailing(this.user.isMailing());
        entity.setLocked(this.user.isLocked());
        entity.setLogin(encrypt(this.user.getLogin()));
        entity.setPassword(encrypt(this.user.getPassword()));
        entity.setContactsEntity(this.user.getContacts().convert());
        entity.setPhotoEntity(this.user.getPhoto().convert());
        return entity;
    }
}
