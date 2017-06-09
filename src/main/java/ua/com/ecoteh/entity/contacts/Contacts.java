package ua.com.ecoteh.entity.contacts;

import ua.com.ecoteh.entity.model.Model;

/**
 * The class implements a set of methods for working
 * with objects of the {@link Contacts} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see ContactsEntity
 */
public final class Contacts extends Model {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The E-mail.
     */
    private final String email;

    /**
     * The mobile phone number.
     */
    private final String mobilePhone;

    /**
     * The landlines phone number.
     */
    private final String landlinesPhone;

    /**
     * The fax number.
     */
    private final String fax;

    /**
     * The Vkontakte URL.
     */
    private final String vkontakte;

    /**
     * The Facebook URL.
     */
    private final String facebook;

    /**
     * The Twitter URL.
     */
    private final String twitter;

    /**
     * The skype username.
     */
    private final String skype;

    /**
     * Constructor.
     *
     * @param id            the unique identifier for each contacts.
     * @param validated     the validations of a new contacts.
     * @param email         the E-mail of a new contacts.
     * @param mobilePhone   the mobile phone of a new contacts.
     * @param landlinesPhone the landlines phone of a new contacts.
     * @param fax           the fax of a new contacts.
     * @param vkontakte     the Vkontakte URL of a new contacts.
     * @param facebook      the Facebook URL of a new contacts.
     * @param twitter       the Twitter URL of a new contacts.
     * @param skype         the Skype username of a new contacts.
     */
    Contacts(
            final long id, final boolean validated,
            final String email, final String mobilePhone,
            final String landlinesPhone, final String fax,
            final String vkontakte, final String facebook,
            final String twitter, final String skype
    ) {
        super(id, validated);
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.landlinesPhone = landlinesPhone;
        this.fax = fax;
        this.vkontakte = vkontakte;
        this.facebook = facebook;
        this.twitter = twitter;
        this.skype = skype;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object (newer null).
     */
    @Override
    public String toString() {
        return "Contacts{" + super.toString() +
                ", email='" + this.email + '\'' +
                ", mobilePhone='" + this.mobilePhone + '\'' +
                ", landlinesPhone='" + this.landlinesPhone + '\'' +
                ", fax='" + this.fax + '\'' +
                ", vkontakte='" + this.vkontakte + '\'' +
                ", facebook='" + this.facebook + '\'' +
                ", twitter='" + this.twitter + '\'' +
                ", skype='" + this.skype + '\'' +
                '}';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return true if this object is the same as the object
     * argument, false otherwise.
     */
    @Override
    public boolean equals(final Object object) {
        boolean result = super.equals(object);
        if (result) {
            final Contacts other = (Contacts) object;
            result = this.email.equalsIgnoreCase(other.email) &&
                    this.mobilePhone.equalsIgnoreCase(other.mobilePhone) &&
                    this.landlinesPhone.equalsIgnoreCase(other.landlinesPhone) &&
                    this.fax.equalsIgnoreCase(other.fax) &&
                    this.vkontakte.equalsIgnoreCase(other.vkontakte) &&
                    this.facebook.equalsIgnoreCase(other.facebook) &&
                    this.twitter.equalsIgnoreCase(other.twitter) &&
                    this.skype.equalsIgnoreCase(other.skype);
        }
        return result;
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit
     * of hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return this.email.hashCode() +
                this.mobilePhone.hashCode() +
                this.landlinesPhone.hashCode() +
                this.fax.hashCode() +
                this.vkontakte.hashCode() +
                this.facebook.hashCode() +
                this.twitter.hashCode() +
                this.skype.hashCode();
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance (newer null).
     */
    @Override
    public Contacts clone() {
        return (Contacts) super.clone();
    }

    /**
     * Returns a E-mail of the contacts.
     *
     * @return The E-mail or empty string (newer null).
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Returns a mobile phone number of the contacts.
     *
     * @return The mobile phone number or empty string (newer null).
     */
    public String getMobilePhone() {
        return this.mobilePhone;
    }

    /**
     * Returns a landlines phone number of the contacts.
     *
     * @return The landlines phone number or empty string (newer null).
     */
    public String getLandlinesPhone() {
        return this.landlinesPhone;
    }

    /**
     * Returns a fax number of the contacts.
     *
     * @return The fax number or empty string (newer null).
     */
    public String getFax() {
        return this.fax;
    }

    /**
     * Returns a Vkontakte URL of the contacts.
     *
     * @return The Vkontakte URL or empty string (newer null).
     */
    public String getVkontakte() {
        return this.vkontakte;
    }

    /**
     * Returns a Facebook URL of the contacts.
     *
     * @return The Facebook URL or empty string (newer null).
     */
    public String getFacebook() {
        return this.facebook;
    }

    /**
     * Returns a Twitter URL of the contacts.
     *
     * @return The Twitter URL or empty string (newer null).
     */
    public String getTwitter() {
        return this.twitter;
    }

    /**
     * Returns a Skype username of the contacts.
     *
     * @return The Skype username or empty string (newer null).
     */
    public String getSkype() {
        return this.skype;
    }

    /**
     * Converts this object and returns an entity
     * of the {@link ContactsEntity} class.
     *
     * @return The entity of the {@link ContactsEntity} class (newer null).
     * @see ContactsConverter
     */
    @Override
    public ContactsEntity convert() {
        return new ContactsConverter(this).convert();
    }

    /**
     * Returns a editor for updating this contacts.
     *
     * @return the contacts editor (newer null).
     */
    @Override
    public ContactsEditor getEditor() {
        return new ContactsEditor(this);
    }

    /**
     * Returns a builder for creating a new contacts.
     *
     * @return the contacts builder (newer null).
     */
    public static ContactsBuilder getBuilder() {
        return new ContactsBuilder();
    }
}
