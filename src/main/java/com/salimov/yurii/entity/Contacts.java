package com.salimov.yurii.entity;

import com.salimov.yurii.entity.interfaces.IContacts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Entity
@Table(name = "contacts")
public final class Contacts extends Model implements IContacts {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The e-mail of a user.
     */
    @Column(name = "email", nullable = false)
    private String email;

    /**
     * The mobile phone of a company.
     */
    @Column(name = "mobile_phone", nullable = false)
    private String mobilePhone;

    /**
     * The landline phone of a company.
     */
    @Column(name = "landline_phone", nullable = false)
    private String landlinePhone;

    /**
     * The fax of a company.
     */
    @Column(name = "fax", nullable = false)
    private String fax;

    /**
     * The vkontakte url of a user.
     */
    @Column(name = "vkontakte", nullable = false)
    private String vkontakte;

    /**
     * The facebook url of a user.
     */
    @Column(name = "facebook", nullable = false)
    private String facebook;

    /**
     * The twitter url of a user.
     */
    @Column(name = "twitter", nullable = false)
    private String twitter;

    /**
     * The skype username of a user.
     */
    @Column(name = "skype", nullable = false)
    private String skype;

    /**
     *
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
     * @param email
     * @param mobilePhone
     * @param landlinePhone
     * @param fax
     * @param vkontakte
     * @param facebook
     * @param twitter
     * @param skype
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
        super();
        setEmail(email);
        setMobilePhone(mobilePhone);
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
     * @return {@code true} if this object is the same as the object
     * argument, {@code false} otherwise otherwise.
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
     * Returns a e-mail of the user.
     *
     * @return The user e-mail.
     */
    @Override
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets a new e-mail to the user.
     * If parameter e-mail is blank, then sets {@code null}.
     *
     * @param email a new e-mail to the user.
     */
    @Override
    public void setEmail(final String email) {
        this.email = isNotBlank(email) ? email : "";
    }

    /**
     * Returns a mobile phone of the company.
     *
     * @return The company mobile phone.
     */
    @Override
    public String getMobilePhone() {
        return this.mobilePhone;
    }

    /**
     * Sets a new mobile phone to the company.
     * If parameter mobile phone is blank, then sets {@code null}.
     *
     * @param mobilePhone a new mobile phone to the company.
     */
    @Override
    public void setMobilePhone(final String mobilePhone) {
        this.mobilePhone = isNotBlank(mobilePhone) ? mobilePhone : "";
    }

    /**
     * Returns a landline phone of the company.
     *
     * @return The company landline phone.
     */
    @Override
    public String getLandlinePhone() {
        return this.landlinePhone;
    }

    /**
     * Sets a new landline phone to the company.
     * If parameter landline phone is blank, then sets {@code null}.
     *
     * @param landlinePhone a new landline phone to the company.
     */
    @Override
    public void setLandlinePhone(final String landlinePhone) {
        this.landlinePhone = isNotBlank(landlinePhone) ? landlinePhone : "";
    }

    /**
     * Returns a fax of the company.
     *
     * @return The company fax.
     */
    @Override
    public String getFax() {
        return this.fax;
    }

    /**
     * Sets a new fax to the company.
     * If parameter fax is blank, then sets {@code null}.
     *
     * @param fax a new fax to the company.
     */
    @Override
    public void setFax(final String fax) {
        this.fax = isNotBlank(fax) ? fax : "";
    }

    /**
     * Returns a vkontakte url of the user.
     *
     * @return The user vkontakte url.
     */
    @Override
    public String getVkontakte() {
        return this.vkontakte;
    }

    /**
     * Sets a new vkontakte url to the user.
     * If parameter vkontakte url is blank, then sets {@code null}.
     *
     * @param vkontakte a new vkontakte url to the user.
     */
    @Override
    public void setVkontakte(final String vkontakte) {
        this.vkontakte = isNotBlank(vkontakte) ? vkontakte : "";
    }

    /**
     * Returns a facebook url of the user.
     *
     * @return The user facebook url.
     */
    @Override
    public String getFacebook() {
        return this.facebook;
    }

    /**
     * Sets a new facebook url to the user.
     * If parameter facebook url is blank, then sets {@code null}.
     *
     * @param facebook a new facebook url to the user.
     */
    @Override
    public void setFacebook(final String facebook) {
        this.facebook = isNotBlank(facebook) ? facebook : "";
    }

    /**
     * Returns a twitter url of the user.
     *
     * @return The user twitter url.
     */
    @Override
    public String getTwitter() {
        return this.twitter;
    }

    /**
     * Sets a new twitter url to the user.
     * If parameter twitter url is blank, then sets {@code null}.
     *
     * @param twitter a new twitter url to the user.
     */
    @Override
    public void setTwitter(final String twitter) {
        this.twitter = isNotBlank(twitter) ? twitter : "";
    }

    /**
     * Returns a skype username of the user.
     *
     * @return The user skype username.
     */
    @Override
    public String getSkype() {
        return this.skype;
    }

    /**
     * Sets a new skype username to the user.
     * If parameter skype username is blank, then sets {@code null}.
     *
     * @param skype a new skype username to the user.
     */
    @Override
    public void setSkype(final String skype) {
        this.skype = isNotBlank(skype) ? skype : "";
    }

    /**
     * @param contacts
     * @return
     */
    @Override
    public Contacts initialize(final Contacts contacts) {
        if (contacts != null) {
            super.initialize(contacts);
            this.setEmail(contacts.getEmail());
            this.setMobilePhone(contacts.getMobilePhone());
            this.setLandlinePhone(contacts.getLandlinePhone());
            this.setVkontakte(contacts.getVkontakte());
            this.setFacebook(contacts.getFacebook());
            this.setTwitter(contacts.getTwitter());
            this.setSkype(contacts.getSkype());
        }
        return this;
    }
}
