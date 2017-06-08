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
        final ContactsEntity contactsEntity = new ContactsEntity();
        contactsEntity.setId(this.contacts.getId());
        contactsEntity.setValidated(this.contacts.isValidated());
        contactsEntity.setEmail(this.contacts.getEmail());
        contactsEntity.setMobilePhone(this.contacts.getMobilePhone());
        contactsEntity.setLandlinesPhone(this.contacts.getLandlinesPhone());
        contactsEntity.setFax(this.contacts.getFax());
        contactsEntity.setVkontakte(this.contacts.getVkontakte());
        contactsEntity.setFacebook(this.contacts.getFacebook());
        contactsEntity.setTwitter(this.contacts.getTwitter());
        contactsEntity.setSkype(this.contacts.getSkype());
        return contactsEntity;
    }
}
