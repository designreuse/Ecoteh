package ua.com.ecoteh.entity.contacts;

import ua.com.ecoteh.entity.model.ModelConverter;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class ContactsConverter extends ModelConverter<ContactsEntity> {

    private final Contacts contacts;

    ContactsConverter(final Contacts contacts) {
        this.contacts = contacts;
    }

    @Override
    public ContactsEntity convert() {
        Contacts contacts;
        if (isNotNull(this.contacts)) {
            contacts = this.contacts;
        } else {
            contacts = new ContactsBuilder().build();
        }
        return convertToEntity(contacts);
    }

    
    private ContactsEntity convertToEntity(final Contacts contacts) {
        final ContactsEntity contactsEntity = new ContactsEntity();
        contactsEntity.setId(contacts.getId());
        contactsEntity.setValidated(contacts.isValidated());
        contactsEntity.setEmail(contacts.getEmail());
        contactsEntity.setMobilePhone(contacts.getMobilePhone());
        contactsEntity.setLandlinePhone(contacts.getLandlinePhone());
        contactsEntity.setFax(contacts.getFax());
        contactsEntity.setVkontakte(contacts.getVkontakte());
        contactsEntity.setFacebook(contacts.getFacebook());
        contactsEntity.setTwitter(contacts.getTwitter());
        contactsEntity.setSkype(contacts.getSkype());
        return contactsEntity;
    }
}
