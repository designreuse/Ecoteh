package ua.com.ecoteh.entity.contacts;

import ua.com.ecoteh.entity.model.ModelEditor;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of methods
 * for editing an objects of the {@link Contacts} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Contacts
 */
public final class ContactsEditor extends ModelEditor<Contacts, ContactsEditor> {

    /**
     * The contacts to edit.
     */
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
     * The landlines phone number.
     */
    private String landlinesPhone;

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
     *
     * @param contacts the contacts to edit.
     */
    ContactsEditor(final Contacts contacts) {
        super(contacts);
        this.contacts = contacts;
    }

    /**
     * Updates and returns a new contacts.
     *
     * @return The updated contacts.
     */
    @Override
    public Contacts update() {
        final ContactsBuilder builder = Contacts.getBuilder();
        builder.addId(getId())
                .addValidated(isValidated())
                .addEmail(getEmail())
                .addMobilePhone(getMobilePhone())
                .addLandlinesPhone(getLandlinesPhone())
                .addFax(getFax())
                .addVkontakte(getVkontakte())
                .addFacebook(getFacebook())
                .addTwitter(getTwitter())
                .addSkype(getSkype());
        return builder.build();
    }

    /**
     * Copies the incoming contacts.
     *
     * @param contacts the contacts to copy.
     * @return the contacts editor.
     */
    @Override
    public ContactsEditor copy(final Contacts contacts) {
        if (isNotNull(contacts)) {
            super.copy(contacts)
                    .addEmail(contacts.getEmail())
                    .addMobilePhone(contacts.getMobilePhone())
                    .addLandlinesPhone(contacts.getLandlinesPhone())
                    .addFax(contacts.getFax())
                    .addVkontakte(contacts.getVkontakte())
                    .addFacebook(contacts.getFacebook())
                    .addTwitter(contacts.getTwitter())
                    .addSkype(contacts.getSkype());
        }
        return this;
    }

    /**
     * Adds a new E-mail to the contacts.
     *
     * @param email the new E-mail to the contacts.
     * @return the contacts editor.
     */
    public ContactsEditor addEmail(final String email) {
        this.email = email;
        return this;
    }

    /**
     * Adds a new mobile phone number to the contacts.
     *
     * @param mobilePhone the new mobile phone number to the contacts.
     * @return the contacts editor.
     */
    public ContactsEditor addMobilePhone(final String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    /**
     * Adds a new landlines phone number to the contacts.
     *
     * @param landlinesPhone the new landlines phone number to the contacts.
     * @return the contacts editor.
     */
    public ContactsEditor addLandlinesPhone(final String landlinesPhone) {
        this.landlinesPhone = landlinesPhone;
        return this;
    }

    /**
     * Adds a new fax number to the contacts.
     *
     * @param fax the new fax number to the contacts.
     * @return the contacts editor.
     */
    public ContactsEditor addFax(final String fax) {
        this.fax = fax;
        return this;
    }

    /**
     * Adds a new Vkontakte URL to the contacts.
     *
     * @param vkontakte the new Vkontakte URL to the contacts.
     * @return the contacts editor.
     */
    public ContactsEditor addVkontakte(final String vkontakte) {
        this.vkontakte = vkontakte;
        return this;
    }

    /**
     * Adds a new Facebook URL to the contacts.
     *
     * @param facebook the new Facebook URL to the contacts.
     * @return the contacts editor.
     */
    public ContactsEditor addFacebook(final String facebook) {
        this.facebook = facebook;
        return this;
    }

    /**
     * Adds a new Twitter URL to the contacts.
     *
     * @param twitter the new Twitter URL to the contacts.
     * @return the contacts editor.
     */
    public ContactsEditor addTwitter(final String twitter) {
        this.twitter = twitter;
        return this;
    }

    /**
     * Adds a new Skype username to the contacts.
     *
     * @param skype the new Skype username to the contacts.
     * @return the contacts editor.
     */
    public ContactsEditor addSkype(final String skype) {
        this.skype = skype;
        return this;
    }

    /**
     * Returns a new E-mail of the contacts.
     * Returns the contacts E-mail if the E-mail is null.
     *
     * @return The E-mail (newer null).
     */
    private String getEmail() {
        return isNotNull(this.email) ? this.email : this.contacts.getEmail();
    }

    /**
     * Returns a new mobile phone number of the contacts.
     * Returns the contacts mobile phone number if the mobile phone number is null.
     *
     * @return The mobile phone number (newer null).
     */
    private String getMobilePhone() {
        return isNotNull(this.mobilePhone) ? this.mobilePhone : this.contacts.getMobilePhone();
    }

    /**
     * Returns a new landlines phone number of the contacts.
     * Returns the contacts landlines phone number if the landlines phone number is null.
     *
     * @return The landlines phone number (newer null).
     */
    private String getLandlinesPhone() {
        return isNotNull(this.landlinesPhone) ? this.landlinesPhone : this.contacts.getLandlinesPhone();
    }

    /**
     * Returns a new fax number of the contacts.
     * Returns the contacts fax number if the fax number is null.
     *
     * @return The fax number (newer null).
     */
    private String getFax() {
        return isNotNull(this.fax) ? this.fax : this.contacts.getFax();
    }

    /**
     * Returns a new Vkontakte URL of the contacts.
     * Returns the contacts Vkontakte URL if the Vkontakte URL is null.
     *
     * @return The Vkontakte URL (newer null).
     */
    private String getVkontakte() {
        return isNotNull(this.vkontakte) ? this.vkontakte : this.contacts.getVkontakte();
    }

    /**
     * Returns a new Facebook URL of the contacts.
     * Returns the contacts Facebook URL if the Facebook URL is null.
     *
     * @return The Facebook URL (newer null).
     */
    private String getFacebook() {
        return isNotNull(this.facebook) ? this.facebook : this.contacts.getFacebook();
    }

    /**
     * Returns a new Twitter URL of the contacts.
     * Returns the contacts Twitter URL if the Twitter URL is null.
     *
     * @return The Twitter URL (newer null).
     */
    private String getTwitter() {
        return isNotNull(this.twitter) ? this.twitter : this.contacts.getTwitter();
    }

    /**
     * Returns a new Skype username of the contacts.
     * Returns the contacts Skype URL if the Skype username is null.
     *
     * @return The Skype URL (newer null).
     */
    private String getSkype() {
        return isNotNull(this.skype) ? this.skype : this.contacts.getSkype();
    }
}
