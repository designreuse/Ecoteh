package ua.com.ecoteh.entity.contacts;

import ua.com.ecoteh.entity.model.ModelEntityConverter;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class ContactsEntityConverter extends ModelEntityConverter<Contacts> {

    private final ContactsEntity entity;

    ContactsEntityConverter(final ContactsEntity entity) {
        this.entity = entity;
    }

    @Override
    protected ContactsBuilder prepareBuilder() {
        final ContactsBuilder builder = new ContactsBuilder();
        if (isNotNull(this.entity)) {
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
        }
        return null;
    }
}
