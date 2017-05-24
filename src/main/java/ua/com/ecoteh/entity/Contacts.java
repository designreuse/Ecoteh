package ua.com.ecoteh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link Contacts} class.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
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
     * @param email       a E-mail of the new contacts.
     * @param mobilePhone a mobile phone number of the new contacts.
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
     * @param email         a E-mail of the new contacts.
     * @param mobilePhone   a mobile phone number of the new contacts.
     * @param landlinePhone a landline phone number of the new contacts.
     * @param fax           a fax number of the new contacts.
     * @param vkontakte     a Vkontakte URL of the new contacts.
     * @param facebook      a Facebook URL of the new contacts.
     * @param twitter       a Twitter URL of the new contacts.
     * @param skype         a Skype username of the new contacts.
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
     * @return A string representation of the object (newer null).
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
     * argument, false otherwise.
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
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance.
     */
    @Override
    public Contacts clone() {
        return (Contacts) super.clone();
    }

    /**
     * Returns a E-mail of the contacts.
     *
     * @return The contacts E-mail or empty string (newer null).
     */
    @Override
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets a new E-mail to the contacts.
     * If parameter E-mail is blank, then sets empty string.
     * <pre>
     *     setEmail(null) - email = ""
     *     setEmail("") - email = ""
     *     setEmail(" ") - email = ""
     *     setEmail("bob") - email = "bob"
     *     setEmail(" bob ") - email = " bob "
     * </pre>
     *
     * @param email the new E-mail to the contacts.
     */
    @Override
    public void setEmail(final String email) {
        this.email = isNotEmpty(email) ? email : "";
    }

    /**
     * Returns a mobile phone number of the contacts.
     *
     * @return The contacts mobile phone number or empty string (newer null).
     */
    @Override
    public String getMobilePhone() {
        return this.mobilePhone;
    }

    /**
     * Sets a new mobile phone number to the contacts.
     * If parameter mobile phone is blank, then sets empty string.
     * <pre>
     *     setMobilePhone(null) - mobilePhone = ""
     *     setMobilePhone("") - mobilePhone = ""
     *     setMobilePhone(" ") - mobilePhone = ""
     *     setMobilePhone("bob") - mobilePhone = "bob"
     *     setMobilePhone(" bob ") - mobilePhone = " bob "
     * </pre>
     *
     * @param mobilePhone the new mobile phone number to the contacts.
     */
    @Override
    public void setMobilePhone(final String mobilePhone) {
        this.mobilePhone = isNotEmpty(mobilePhone) ? mobilePhone : "";
    }

    /**
     * Returns a landline phone number of the contacts.
     *
     * @return The contacts landline phone number or empty string (newer null).
     */
    @Override
    public String getLandlinePhone() {
        return this.landlinePhone;
    }

    /**
     * Sets a new landline phone number to the contacts.
     * If parameter landline phone is blank, then sets empty string.
     * <pre>
     *     setLandlinePhone(null) - landlinePhone = ""
     *     setLandlinePhone("") - landlinePhone = ""
     *     setLandlinePhone(" ") - landlinePhone = ""
     *     setLandlinePhone("bob") - landlinePhone = "bob"
     *     setLandlinePhone(" bob ") - landlinePhone = " bob "
     * </pre>
     *
     * @param landlinePhone the new landline phone number to the contacts.
     */
    @Override
    public void setLandlinePhone(final String landlinePhone) {
        this.landlinePhone = isNotEmpty(landlinePhone) ? landlinePhone : "";
    }

    /**
     * Returns a fax number of the contacts.
     *
     * @return The contacts fax number or empty string (newer null).
     */
    @Override
    public String getFax() {
        return this.fax;
    }

    /**
     * Sets a new fax number to the contacts.
     * If parameter fax is blank, then sets empty string.
     * <pre>
     *     setFax(null) - fax = ""
     *     setFax("") - fax = ""
     *     setFax(" ") - fax = ""
     *     setFax("bob") - fax = "bob"
     *     setFax(" bob ") - fax = " bob "
     * </pre>
     *
     * @param fax the new fax number to the contacts.
     */
    @Override
    public void setFax(final String fax) {
        this.fax = isNotEmpty(fax) ? fax : "";
    }

    /**
     * Returns a Vkontakte URL of the contacts.
     *
     * @return The contacts Vkontakte URL or empty string (newer null).
     */
    @Override
    public String getVkontakte() {
        return this.vkontakte;
    }

    /**
     * Sets a new Vkontakte URL to the contacts.
     * If parameter Vkontakte URL is blank, then sets empty string.
     * <pre>
     *     setVkontakte(null) - vkontakte = ""
     *     setVkontakte("") - vkontakte = ""
     *     setVkontakte(" ") - vkontakte = ""
     *     setVkontakte("bob") - vkontakte = "bob"
     *     setVkontakte(" bob ") - vkontakte = " bob "
     * </pre>
     *
     * @param vkontakte the new Vkontakte URL to the contacts.
     */
    @Override
    public void setVkontakte(final String vkontakte) {
        this.vkontakte = isNotEmpty(vkontakte) ? vkontakte : "";
    }

    /**
     * Returns a Facebook URL of the contacts.
     *
     * @return The contacts Facebook URL or empty string (newer null).
     */
    @Override
    public String getFacebook() {
        return this.facebook;
    }

    /**
     * Sets a new Facebook URL to the contacts.
     * If parameter Facebook URL is blank, then sets empty string.
     * <pre>
     *     setFacebook(null) - facebook = ""
     *     setFacebook("") - facebook = ""
     *     setFacebook(" ") - facebook = ""
     *     setFacebook("bob") - facebook = "bob"
     *     setFacebook(" bob ") - facebook = " bob "
     * </pre>
     *
     * @param facebook the new Facebook URL to the contacts.
     */
    @Override
    public void setFacebook(final String facebook) {
        this.facebook = isNotEmpty(facebook) ? facebook : "";
    }

    /**
     * Returns a Twitter URL of the contacts.
     *
     * @return The contacts Twitter URL or empty string (newer null).
     */
    @Override
    public String getTwitter() {
        return this.twitter;
    }

    /**
     * Sets a new Twitter URL to the contacts.
     * If parameter Twitter URL is blank, then sets empty string.
     * <pre>
     *     setTwitter(null) - twitter = ""
     *     setTwitter("") - twitter = ""
     *     setTwitter(" ") - twitter = ""
     *     setTwitter("bob") - twitter = "bob"
     *     setTwitter(" bob ") - twitter = " bob "
     * </pre>
     *
     * @param twitter the new Twitter URL to the contacts.
     */
    @Override
    public void setTwitter(final String twitter) {
        this.twitter = isNotEmpty(twitter) ? twitter : "";
    }

    /**
     * Returns a Skype username of the contacts.
     *
     * @return The contacts Skype username or empty string (newer null).
     */
    @Override
    public String getSkype() {
        return this.skype;
    }

    /**
     * Sets a new Skype username.
     * If parameter Skype username is blank, then sets empty string.
     * <pre>
     *     setSkype(null) - skype = ""
     *     setSkype("") - skype = ""
     *     setSkype(" ") - skype = ""
     *     setSkype("bob") - skype = "bob"
     *     setSkype(" bob ") - skype = " bob "
     * </pre>
     *
     * @param skype the new Skype username to the contacts.
     */
    @Override
    public void setSkype(final String skype) {
        this.skype = isNotEmpty(skype) ? skype : "";
    }

    /**
     * Initializes the contacts.
     * Returns this contacts with a new copied fields.
     * <pre>
     *     initialize(null) - does nothing, returns this contacts
     *     initialize(new Contacts()) - does nothing, returns this
     *     contacts with a new copied fields
     * </pre>
     *
     * @param contacts the contacts to copy.
     * @return This contacts with new fields (newer null).
     */
    @Override
    public Contacts initialize(final Contacts contacts) {
        if (isNotNull(contacts)) {
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
