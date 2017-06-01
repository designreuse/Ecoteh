package ua.com.ecoteh.entity.contacts;

import ua.com.ecoteh.entity.model.Model;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
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
     * The landline phone number.
     */
    private final String landlinePhone;

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
     * @param id
     * @param validated
     * @param email
     * @param mobilePhone
     * @param landlinePhone
     * @param fax
     * @param vkontakte
     * @param facebook
     * @param twitter
     * @param skype
     */
    Contacts(
            final long id, final boolean validated,
            final String email, final String mobilePhone,
            final String landlinePhone, final String fax,
            final String vkontakte, final String facebook,
            final String twitter, final String skype
    ) {
        super(id, validated);
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.landlinePhone = landlinePhone;
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
                ", landlinePhone='" + this.landlinePhone + '\'' +
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
    public boolean equals(Object object) {
        boolean result = super.equals(object);
        if (result) {
            final Contacts other = (Contacts) object;
            result = this.email.equalsIgnoreCase(other.email) &&
                    this.mobilePhone.equalsIgnoreCase(other.mobilePhone) &&
                    this.landlinePhone.equalsIgnoreCase(other.landlinePhone) &&
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
                this.landlinePhone.hashCode() +
                this.fax.hashCode() +
                this.vkontakte.hashCode() +
                this.facebook.hashCode() +
                this.twitter.hashCode() +
                this.skype.hashCode();
    }

    /**
     * Returns a E-mail of the contactsEntity.
     *
     * @return The contactsEntity E-mail or empty string (newer null).
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Returns a mobile phone number of the contactsEntity.
     *
     * @return The contactsEntity mobile phone number or empty string (newer null).
     */
    public String getMobilePhone() {
        return this.mobilePhone;
    }

    /**
     * Returns a landline phone number of the contactsEntity.
     *
     * @return The contactsEntity landline phone number or empty string (newer null).
     */
    public String getLandlinePhone() {
        return this.landlinePhone;
    }

    /**
     * Returns a fax number of the contactsEntity.
     *
     * @return The contactsEntity fax number or empty string (newer null).
     */
    public String getFax() {
        return this.fax;
    }

    /**
     * Returns a Vkontakte URL of the contactsEntity.
     *
     * @return The contactsEntity Vkontakte URL or empty string (newer null).
     */
    public String getVkontakte() {
        return this.vkontakte;
    }

    /**
     * Returns a Facebook URL of the contactsEntity.
     *
     * @return The contactsEntity Facebook URL or empty string (newer null).
     */
    public String getFacebook() {
        return this.facebook;
    }

    /**
     * Returns a Twitter URL of the contactsEntity.
     *
     * @return The contactsEntity Twitter URL or empty string (newer null).
     */
    public String getTwitter() {
        return this.twitter;
    }

    /**
     * Returns a Skype username of the contactsEntity.
     *
     * @return The contactsEntity Skype username or empty string (newer null).
     */
    public String getSkype() {
        return this.skype;
    }

    /**
     *
     * @return
     */
    public ContactsEntity convert() {
        return new ContactsConverter(this).convert();
    }

    /**
     *
     * @return
     */
    public static ContactsBuilder getBuilder() {
        return new ContactsBuilder();
    }
}
