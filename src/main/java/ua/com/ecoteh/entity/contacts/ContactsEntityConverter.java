package ua.com.ecoteh.entity.contacts;

import ua.com.ecoteh.entity.model.ModelEntityConverter;

/**
 * The class implements a set of methods
 * for converting contacts entities to contacts.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see ContactsEntity
 * @see Contacts
 */
final class ContactsEntityConverter extends ModelEntityConverter<ContactsEntity, Contacts> {

    /**
     * The contacts entity for converting to contacts.
     */
    private final ContactsEntity entity;

    /**
     * Constructor.
     *
     * @param entity the contacts entity for converting to contacts.
     */
    ContactsEntityConverter(final ContactsEntity entity) {
        super(entity);
        this.entity = entity;
    }

    /**
     * Prepares and returns a contacts builder for creating
     * a new converted contacts.
     *
     * @return the prepared contacts builder.
     */
    @Override
    protected ContactsBuilder prepareBuilder() {
        final ContactsBuilder builder = Contacts.getBuilder();
        builder.addId(this.entity.getId())
                .addValidated(this.entity.isValidated())
                .addEmail(this.entity.getEmail())
                .addMobilePhone(this.entity.getMobilePhone())
                .addLandlinePhone(this.entity.getLandlinePhone())
                .addFax(this.entity.getFax())
                .addVkontakte(this.entity.getVkontakte())
                .addFacebook(this.entity.getFacebook())
                .addTwitter(this.entity.getTwitter())
                .addSkype(this.entity.getSkype());
        return builder;
    }
}
