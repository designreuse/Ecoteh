package ua.com.ecoteh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link Contacts} class.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Entity
@Table(name = "contacts")
public class Contacts extends Model implements IContacts {

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
    @Column(name = "email", nullable = false)
    private String email;

    /**
     * The mobile phone number.
     */
    @Column(name = "mobile_phone", nullable = false)
    private String mobilePhone;

    /**
     * The landline phone number.
     */
    @Column(name = "landline_phone", nullable = false)
    private String landlinePhone;

    /**
     * The fax number.
     */
    @Column(name = "fax", nullable = false)
    private String fax;

    /**
     * The Vkontakte URL.
     */
    @Column(name = "vkontakte", nullable = false)
    private String vkontakte;

    /**
     * The Facebook URL.
     */
    @Column(name = "facebook", nullable = false)
    private String facebook;

    /**
     * The Twitter URL.
     */
    @Column(name = "twitter", nullable = false)
    private String twitter;

    /**
     * The skype username.
     */
    @Column(name = "skype", nullable = false)
    private String skype;

    /**
     * Default constructor.
     */
    public Contacts() {
        this.email = "";
        this.mobilePhone = "";
        this.landlinePhone = "";
        this.fax = "";
        this.vkontakte = "";
        this.facebook = "";
        this.twitter = "";
        this.skype = "";
    }

    /**
     * Constructor.
     *
     * @param email       a E-mail.
     * @param mobilePhone a mobile phone number.
     */
    public Contacts(
            final String email,
            final String mobilePhone
    ) {
        this();
        setEmail(email);
        setMobilePhone(mobilePhone);
    }

    /**
     * Constructor.
     *
     * @param email         a E-mail.
     * @param mobilePhone   a mobile phone number.
     * @param landlinePhone a landline phone number.
     * @param fax           a fax number.
     * @param vkontakte     a Vkontakte URL
     * @param facebook      a Facebook URL.
     * @param twitter       a Twitter URL.
     * @param skype         a Skype username.
     */
    public Contacts(
            final String email,
            final String mobilePhone,
            final String landlinePhone,
            final String fax,
            final String vkontakte,
            final String facebook,
            final String twitter,
            final String skype
    ) {
        this(email, mobilePhone);
        setLandlinePhone(landlinePhone);
        setFax(fax);
        setVkontakte(vkontakte);
        setFacebook(facebook);
        setTwitter(twitter);
        setSkype(skype);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "Contacts{" + super.toString() +
                ", email='" + getEmail() + '\'' +
                ", mobilePhone='" + getMobilePhone() + '\'' +
                ", landlinePhone='" + getLandlinePhone() + '\'' +
                ", fax='" + getFax() + '\'' +
                ", vkontakte='" + getVkontakte() + '\'' +
                ", facebook='" + getFacebook() + '\'' +
                ", twitter='" + getTwitter() + '\'' +
                ", skype='" + getSkype() + '\'' +
                '}';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return true if this object is the same as the object
     * argument, false otherwise otherwise.
     */
    @Override
    public boolean equals(Object object) {
        boolean result = super.equals(object);
        if (result) {
            final Contacts other = (Contacts) object;
            result = this.getEmail().equalsIgnoreCase(other.getEmail()) &&
                    this.getMobilePhone().equalsIgnoreCase(other.getMobilePhone()) &&
                    this.getLandlinePhone().equalsIgnoreCase(other.getLandlinePhone()) &&
                    this.getFax().equalsIgnoreCase(other.getFax()) &&
                    this.getVkontakte().equalsIgnoreCase(other.getVkontakte()) &&
                    this.getFacebook().equalsIgnoreCase(other.getFacebook()) &&
                    this.getTwitter().equalsIgnoreCase(other.getTwitter()) &&
                    this.getSkype().equalsIgnoreCase(other.getSkype());
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
        return getEmail().hashCode() +
                getMobilePhone().hashCode() +
                getLandlinePhone().hashCode() +
                getFax().hashCode() +
                getVkontakte().hashCode() +
                getFacebook().hashCode() +
                getTwitter().hashCode() +
                getSkype().hashCode();
    }

    /**
     * Returns a E-mail.
     *
     * @return The E-mail.
     */
    @Override
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets a new E-mail.
     * If parameter E-mail is blank, then sets empty string.
     *
     * @param email a new E-mail.
     */
    @Override
    public void setEmail(final String email) {
        this.email = isNotBlank(email) ? email : "";
    }

    /**
     * Returns a mobile phone number.
     *
     * @return The mobile phone number.
     */
    @Override
    public String getMobilePhone() {
        return this.mobilePhone;
    }

    /**
     * Sets a new mobile phone number.
     * If parameter mobile phone is blank, then sets empty string.
     *
     * @param mobilePhone a new mobile phone number.
     */
    @Override
    public void setMobilePhone(final String mobilePhone) {
        this.mobilePhone = isNotBlank(mobilePhone) ? mobilePhone : "";
    }

    /**
     * Returns a landline phone number.
     *
     * @return The landline phone number.
     */
    @Override
    public String getLandlinePhone() {
        return this.landlinePhone;
    }

    /**
     * Sets a new landline phone number.
     * If parameter landline phone is blank, then sets empty string.
     *
     * @param landlinePhone a new landline phone number.
     */
    @Override
    public void setLandlinePhone(final String landlinePhone) {
        this.landlinePhone = isNotBlank(landlinePhone) ? landlinePhone : "";
    }

    /**
     * Returns a fax number.
     *
     * @return The fax number.
     */
    @Override
    public String getFax() {
        return this.fax;
    }

    /**
     * Sets a new fax number.
     * If parameter fax is blank, then sets empty string.
     *
     * @param fax a new fax number.
     */
    @Override
    public void setFax(final String fax) {
        this.fax = isNotBlank(fax) ? fax : "";
    }

    /**
     * Returns a Vkontakte URL.
     *
     * @return The Vkontakte URL.
     */
    @Override
    public String getVkontakte() {
        return this.vkontakte;
    }

    /**
     * Sets a new Vkontakte URL.
     * If parameter Vkontakte URL is blank, then sets empty string.
     *
     * @param vkontakte a new Vkontakte URL.
     */
    @Override
    public void setVkontakte(final String vkontakte) {
        this.vkontakte = isNotBlank(vkontakte) ? vkontakte : "";
    }

    /**
     * Returns a Facebook URL.
     *
     * @return The Facebook URL.
     */
    @Override
    public String getFacebook() {
        return this.facebook;
    }

    /**
     * Sets a new Facebook URL.
     * If parameter Facebook URL is blank, then sets empty string.
     *
     * @param facebook a new Facebook URL.
     */
    @Override
    public void setFacebook(final String facebook) {
        this.facebook = isNotBlank(facebook) ? facebook : "";
    }

    /**
     * Returns a Twitter URL.
     *
     * @return The Twitter URL.
     */
    @Override
    public String getTwitter() {
        return this.twitter;
    }

    /**
     * Sets a new Twitter URL.
     * If parameter Twitter URL is blank, then sets empty string.
     *
     * @param twitter a new Twitter url to the user.
     */
    @Override
    public void setTwitter(final String twitter) {
        this.twitter = isNotBlank(twitter) ? twitter : "";
    }

    /**
     * Returns a Skype username.
     *
     * @return The Skype username.
     */
    @Override
    public String getSkype() {
        return this.skype;
    }

    /**
     * Sets a new Skype username.
     * If parameter Skype username is blank, then sets empty string.
     *
     * @param skype a new skype username to the user.
     */
    @Override
    public void setSkype(final String skype) {
        this.skype = isNotBlank(skype) ? skype : "";
    }

    /**
     * Initializes the contacts.
     *
     * @param contacts a contacts to copy.
     * @return The this contacts with new fields.
     */
    @Override
    public Contacts initialize(final Contacts contacts) {
        if (contacts != null) {
            super.initialize(contacts);
            this.setEmail(contacts.getEmail());
            this.setMobilePhone(contacts.getMobilePhone());
            this.setLandlinePhone(contacts.getLandlinePhone());
            this.setFax(contacts.getFax());
            this.setVkontakte(contacts.getVkontakte());
            this.setFacebook(contacts.getFacebook());
            this.setTwitter(contacts.getTwitter());
            this.setSkype(contacts.getSkype());
        }
        return this;
    }
}
