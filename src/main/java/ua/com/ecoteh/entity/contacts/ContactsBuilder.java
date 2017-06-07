package ua.com.ecoteh.entity.contacts;

import ua.com.ecoteh.entity.model.ModelBuilder;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * The class implements a set of methods
 * for building an objects of the {@link Contacts} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Contacts
 *
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

    /**
     * Constructor.
     */
    ContactsBuilder() {
    }

    /**
     * Builds and returns a new contacts.
     *
     * @return The new contacts.
     */
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

    /**
     * Adds a new E-mail to a new contacts.
     *
     * @param email the new E-mail to a new contacts.
     * @return the contacts builder.
     */
    public ContactsBuilder addEmail(final String email) {
        this.email = email;
        return this;
    }

    /**
     * Adds a new mobile phone number to a new contacts.
     *
     * @param mobilePhone the new mobile phone number to a new contacts.
     * @return the contacts builder.
     */
    public ContactsBuilder addMobilePhone(final String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    /**
     * Adds a new landline phone number to a new contacts.
     *
     * @param landlinePhone the new landline phone number to a new contacts.
     * @return the contacts builder.
     */
    public ContactsBuilder addLandlinePhone(final String landlinePhone) {
        this.landlinePhone = landlinePhone;
        return this;
    }

    /**
     * Adds a new fax number to a new contacts.
     *
     * @param fax the new fax number to a new contacts.
     * @return the contacts builder.
     */
    public ContactsBuilder addFax(final String fax) {
        this.fax = fax;
        return this;
    }

    /**
     * Adds a new Vkontakte URL to a new contacts.
     *
     * @param vkontakte the new Vkontakte URL to a new contacts.
     * @return the contacts builder.
     */
    public ContactsBuilder addVkontakte(final String vkontakte) {
        this.vkontakte = vkontakte;
        return this;
    }

    /**
     * Adds a new Facebook URL to a new contacts.
     *
     * @param facebook the new Facebook URL to a new contacts.
     * @return the contacts builder.
     */
    public ContactsBuilder addFacebook(final String facebook) {
        this.facebook = facebook;
        return this;
    }

    /**
     * Adds a new Twitter URL to a new contacts.
     *
     * @param twitter the new Twitter URL to a new contacts.
     * @return the contacts builder.
     */
    public ContactsBuilder addTwitter(final String twitter) {
        this.twitter = twitter;
        return this;
    }

    /**
     * Adds a new Skype username to a new contacts.
     *
     * @param skype the new Skype username to a new contacts.
     * @return the contacts builder.
     */
    public ContactsBuilder addSkype(final String skype) {
        this.skype = skype;
        return this;
    }

    /**
     * Returns a E-mail of a new contacts.
     * Returns an empty string if the E-mail is null or empty.
     *
     * @return The E-mail or empty string (newer null).
     */
    private String getEmail() {
        return isNotEmpty(this.email) ? this.email : "";
    }

    /**
     * Returns a mobile phone number of a new contacts.
     * Returns an empty string if the mobile phone number is null or empty.
     *
     * @return The mobile phone number or empty string (newer null).
     */
    private String getMobilePhone() {
        return isNotEmpty(this.mobilePhone) ? this.mobilePhone : "";
    }

    /**
     * Returns a landline phone number of a new contacts.
     * Returns an empty string if the landline phone number is null or empty.
     *
     * @return The landline phone number or empty string (newer null).
     */
    private String getLandlinePhone() {
        return isNotEmpty(this.landlinePhone) ? this.landlinePhone : "";
    }

    /**
     * Returns a fax number of a new contacts.
     * Returns an empty string if the fax number is null or empty.
     *
     * @return The fax number or empty string (newer null).
     */
    private String getFax() {
        return isNotEmpty(this.fax) ? this.fax : "";
    }

    /**
     * Returns a Vkontakte URL of a new contacts.
     * Returns an empty string if the Vkontakte URL is null or empty.
     *
     * @return The Vkontakte URL or empty string (newer null).
     */
    private String getVkontakte() {
        return isNotEmpty(this.vkontakte) ? this.vkontakte : "";
    }

    /**
     * Returns a Facebook URL of a new contacts.
     * Returns an empty string if the Facebook URL is null or empty.
     *
     * @return The Facebook URL or empty string (newer null).
     */
    private String getFacebook() {
        return isNotEmpty(this.facebook) ? this.facebook : "";
    }

    /**
     * Returns a Twitter URL of a new contacts.
     * Returns an empty string if the Twitter URL is null or empty.
     *
     * @return The Twitter URL or empty string (newer null).
     */
    private String getTwitter() {
        return isNotEmpty(this.twitter) ? this.twitter : "";
    }

    /**
     * Returns a Skype username of a new contacts.
     * Returns an empty string if the Skype username is null or empty.
     *
     * @return The Skype username or empty string (newer null).
     */
    private String getSkype() {
        return isNotEmpty(this.skype) ? this.skype : "";
    }
}
