package ua.com.ecoteh.entity.contacts;

import ua.com.ecoteh.entity.model.ModelEntityConverter;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class ContactsEntityConverter extends ModelEntityConverter<ContactsEntity, Contacts> {

    ContactsEntityConverter(final ContactsEntity entity) {
        super(entity);
    }

    @Override
    protected ContactsBuilder prepareBuilder() {
        final ContactsBuilder builder = new ContactsBuilder();
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
