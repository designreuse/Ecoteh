package ua.com.ecoteh.entity;

/**
 * The interface describes a set of methods
 * for working with objects of the {@link Contacts} class.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface IContacts extends IModel {

    /**
     * Returns a E-mail.
     *
     * @return The E-mail.
     */
    String getEmail();

    /**
     * Sets a new E-mail .
     *
     * @param email the new E-mail.
     */
    void setEmail(String email);

    /**
     * Returns a mobile phone number.
     *
     * @return The mobile phone number.
     */
    String getMobilePhone();

    /**
     * Sets a new mobile phone number.
     *
     * @param mobilePhone a new mobile phone number.
     */
    void setMobilePhone(String mobilePhone);

    /**
     * Returns a landline phone number.
     *
     * @return The landline phone number.
     */
    String getLandlinePhone();

    /**
     * Sets a new landline phone number.
     *
     * @param landlinePhone a new landline phone number.
     */
    void setLandlinePhone(String landlinePhone);

    /**
     * Returns a fax number.
     *
     * @return The fax number.
     */
    String getFax();

    /**
     * Sets a new fax number.
     *
     * @param fax a new fax number.
     */
    void setFax(String fax);

    /**
     * Returns a Vkontakte URL.
     *
     * @return The Vkontakte url.
     */
    String getVkontakte();

    /**
     * Sets a new Vkontakte URL.
     *
     * @param vkontakte a new Vkontakte URL.
     */
    void setVkontakte(String vkontakte);

    /**
     * Returns a Facebook URL.
     *
     * @return The Facebook URL.
     */
    String getFacebook();

    /**
     * Sets a new Facebook URL.
     *
     * @param facebook a new Facebook URL.
     */
    void setFacebook(String facebook);

    /**
     * Returns a Twitter URL.
     *
     * @return The Twitter URL.
     */
    String getTwitter();

    /**
     * Sets a new Twitter URL.
     *
     * @param twitter a new Twitter url URL.
     */
    void setTwitter(String twitter);

    /**
     * Returns a skype username.
     *
     * @return The user skype username.
     */
    String getSkype();

    /**
     * Sets a new skype username.
     *
     * @param skype a new skype username.
     */
    void setSkype(String skype);

    /**
     * Initializes the contacts.
     *
     * @param contacts a contacts to copy.
     * @return This contacts with new fields.
     */
    Contacts initialize(Contacts contacts);
}
