package ua.com.ecoteh.entity.contacts;

import ua.com.ecoteh.entity.model.ModelConverter;

/**
 * The class implements a set of methods
 * for converting contacts to contacts entities.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Contacts
 * @see ContactsEntity
 */
final class ContactsConverter extends ModelConverter<Contacts, ContactsEntity> {

    /**
     * The contacts for converting to contacts entity.
     */
    private final Contacts contacts;

    /**
     * Constructor.
     *
     * @param contacts the contacts for converting to contacts entity.
     */
    ContactsConverter(final Contacts contacts) {
        super(contacts);
        this.contacts = contacts;
    }

    /**
     * Converts the contacts and returns a new contacts entity.
     *
     * @return The converted contacts entity (newer null).
     */
    @Override
    public ContactsEntity convert() {
        final ContactsEntity entity = new ContactsEntity();
        entity.setId(this.contacts.getId());
        entity.setValidated(this.contacts.isValidated());
        entity.setEmail(this.contacts.getEmail());
        entity.setMobilePhone(this.contacts.getMobilePhone());
        entity.setLandlinesPhone(this.contacts.getLandlinesPhone());
        entity.setFax(this.contacts.getFax());
        entity.setVkontakte(this.contacts.getVkontakte());
        entity.setFacebook(this.contacts.getFacebook());
        entity.setTwitter(this.contacts.getTwitter());
        entity.setSkype(this.contacts.getSkype());
        return entity;
    }
}
