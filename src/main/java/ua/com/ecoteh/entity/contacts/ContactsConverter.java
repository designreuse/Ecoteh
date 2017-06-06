package ua.com.ecoteh.entity.contacts;

import ua.com.ecoteh.entity.model.ModelConverter;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class ContactsConverter extends ModelConverter<Contacts, ContactsEntity> {

    private final Contacts contacts;

    /**
     * Constructor.
     * @param contacts
     */
    ContactsConverter(final Contacts contacts) {
        super(contacts);
        this.contacts = contacts;
    }

    @Override
    public ContactsEntity convert() {
        final ContactsEntity contactsEntity = new ContactsEntity();
            contactsEntity.setId(this.contacts.getId());
            contactsEntity.setValidated(this.contacts.isValidated());
            contactsEntity.setEmail(this.contacts.getEmail());
            contactsEntity.setMobilePhone(this.contacts.getMobilePhone());
            contactsEntity.setLandlinePhone(this.contacts.getLandlinePhone());
            contactsEntity.setFax(this.contacts.getFax());
            contactsEntity.setVkontakte(this.contacts.getVkontakte());
            contactsEntity.setFacebook(this.contacts.getFacebook());
            contactsEntity.setTwitter(this.contacts.getTwitter());
            contactsEntity.setSkype(this.contacts.getSkype());
        return contactsEntity;
    }
}
