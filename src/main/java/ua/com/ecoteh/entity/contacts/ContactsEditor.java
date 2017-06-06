package ua.com.ecoteh.entity.contacts;

import ua.com.ecoteh.entity.model.ModelEditor;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class ContactsEditor extends ModelEditor<Contacts, ContactsEditor> {

    private final Contacts contacts;

    /**
     * The E-mail.
     */
    private String email;

    /**
     * The mobile phone number.
     */
    private String mobilePhone;

    /**
     * The landline phone number.
     */
    private String landlinePhone;

    /**
     * The fax number.
     */
    private String fax;

    /**
     * The Vkontakte URL.
     */
    private String vkontakte;

    /**
     * The Facebook URL.
     */
    private String facebook;

    /**
     * The Twitter URL.
     */
    private String twitter;

    /**
     * The skype username.
     */
    private String skype;

    /**
     * Constructor.
     * @param contacts
     */
    ContactsEditor(final Contacts contacts) {
        super(contacts);
        this.contacts = contacts;
    }

    @Override
    public Contacts update() {
        final ContactsBuilder builder = Contacts.getBuilder();
        builder.addId(getId())
                .addValidated(isValidated())
                .addEmail(getEmail())
                .addMobilePhone(getMobilePhone())
                .addLandlinePhone(getLandlinePhone())
                .addFax(getFax())
                .addVkontakte(getVkontakte())
                .addFacebook(getFacebook())
                .addTwitter(getTwitter())
                .addSkype(getSkype());
        return builder.build();
    }

    @Override
    public ContactsEditor copy(final Contacts contacts) {
        if (isNotNull(contacts)) {
            super.copy(contacts)
                    .addEmail(contacts.getEmail())
                    .addMobilePhone(contacts.getMobilePhone())
                    .addLandlinePhone(contacts.getLandlinePhone())
                    .addFax(contacts.getFax())
                    .addVkontakte(contacts.getVkontakte())
                    .addFacebook(contacts.getFacebook())
                    .addTwitter(contacts.getTwitter())
                    .addSkype(contacts.getSkype());
        }
        return this;
    }

    public ContactsEditor addEmail(final String email) {
        this.email = email;
        return this;
    }

    public ContactsEditor addMobilePhone(final String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactsEditor addLandlinePhone(final String landlinePhone) {
        this.landlinePhone = landlinePhone;
        return this;
    }

    public ContactsEditor addFax(final String fax) {
        this.fax = fax;
        return this;
    }

    public ContactsEditor addVkontakte(final String vkontakte) {
        this.vkontakte = vkontakte;
        return this;
    }

    public ContactsEditor addFacebook(final String facebook) {
        this.facebook = facebook;
        return this;
    }

    public ContactsEditor addTwitter(final String twitter) {
        this.twitter = twitter;
        return this;
    }

    public ContactsEditor addSkype(final String skype) {
        this.skype = skype;
        return this;
    }

    private String getEmail() {
        return isNotNull(this.email) ? this.email : this.contacts.getEmail();
    }

    private String getMobilePhone() {
        return isNotNull(this.mobilePhone) ? this.mobilePhone : this.contacts.getMobilePhone();
    }

    private String getLandlinePhone() {
        return isNotNull(this.landlinePhone) ? this.landlinePhone : this.contacts.getLandlinePhone();
    }

    private String getFax() {
        return isNotNull(this.fax) ? this.fax : this.contacts.getFax();
    }

    private String getVkontakte() {
        return isNotNull(this.vkontakte) ? this.vkontakte : this.contacts.getVkontakte();
    }

    private String getFacebook() {
        return isNotNull(this.facebook) ? this.facebook : this.contacts.getFacebook();
    }

    private String getTwitter() {
        return isNotNull(this.twitter) ? this.twitter : this.contacts.getTwitter();
    }

    private String getSkype() {
        return isNotNull(this.skype) ? this.skype : this.contacts.getSkype();
    }
}
