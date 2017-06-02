package ua.com.ecoteh.entity.contacts;

import ua.com.ecoteh.entity.model.ModelConverter;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class ContactsConverter extends ModelConverter<Contacts, ContactsEntity> {

    ContactsConverter(final Contacts model) {
        super(model);
    }

    @Override
    public ContactsEntity convert() {
        final ContactsEntity contactsEntity = new ContactsEntity();
            contactsEntity.setId(this.model.getId());
            contactsEntity.setValidated(this.model.isValidated());
            contactsEntity.setEmail(this.model.getEmail());
            contactsEntity.setMobilePhone(this.model.getMobilePhone());
            contactsEntity.setLandlinePhone(this.model.getLandlinePhone());
            contactsEntity.setFax(this.model.getFax());
            contactsEntity.setVkontakte(this.model.getVkontakte());
            contactsEntity.setFacebook(this.model.getFacebook());
            contactsEntity.setTwitter(this.model.getTwitter());
            contactsEntity.setSkype(this.model.getSkype());
        return contactsEntity;
    }
}
