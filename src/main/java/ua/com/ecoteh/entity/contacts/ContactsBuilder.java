package ua.com.ecoteh.entity.contacts;

import ua.com.ecoteh.entity.model.ModelBuilder;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class ContactsBuilder extends ModelBuilder<Contacts, ContactsBuilder> {

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

    ContactsBuilder() {
    }

    @Override
    public Contacts build() {
        return new Contacts(
                getId(), isValidated(),
                getEmail(), getMobilePhone(),
                getLandlinePhone(), getFax(),
                getVkontakte(), getFacebook(),
                getTwitter(), getSkype()
        );
    }

    public ContactsBuilder addEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactsBuilder addMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactsBuilder addLandlinePhone(String landlinePhone) {
        this.landlinePhone = landlinePhone;
        return this;
    }

    public ContactsBuilder addFax(String fax) {
        this.fax = fax;
        return this;
    }

    public ContactsBuilder addVkontakte(String vkontakte) {
        this.vkontakte = vkontakte;
        return this;
    }

    public ContactsBuilder addFacebook(String facebook) {
        this.facebook = facebook;
        return this;
    }

    public ContactsBuilder addTwitter(String twitter) {
        this.twitter = twitter;
        return this;
    }

    public ContactsBuilder addSkype(String skype) {
        this.skype = skype;
        return this;
    }

    private String getEmail() {
        return isNotEmpty(this.email) ? this.email : "";
    }

    private String getMobilePhone() {
        return isNotEmpty(this.mobilePhone) ? this.mobilePhone : "";
    }

    private String getLandlinePhone() {
        return isNotEmpty(this.landlinePhone) ? this.landlinePhone : "";
    }

    private String getFax() {
        return isNotEmpty(this.fax) ? this.fax : "";
    }

    private String getVkontakte() {
        return isNotEmpty(this.vkontakte) ? this.vkontakte : "";
    }

    private String getFacebook() {
        return isNotEmpty(this.facebook) ? this.facebook : "";
    }

    private String getTwitter() {
        return isNotEmpty(this.twitter) ? this.twitter : "";
    }

    private String getSkype() {
        return isNotEmpty(this.skype) ? this.skype : "";
    }
}
