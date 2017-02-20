package com.salimov.yurii.entity.interfaces;

import com.salimov.yurii.entity.Contacts;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface IContacts<E extends Number> extends IModel<E> {

    /**
     * Returns a e-mail of the user.
     *
     * @return The user e-mail.
     */
    String getEmail();

    /**
     * Sets a new e-mail to the user.
     *
     * @param email a new e-mail to the user.
     */
    void setEmail(final String email);

    /**
     * Returns a mobile phone of the company.
     *
     * @return The company mobile phone.
     */
    String getMobilePhone();

    /**
     * Sets a new mobile phone to the company.
     *
     * @param mobilePhone a new mobile phone to the company.
     */
    void setMobilePhone(final String mobilePhone);

    /**
     * Returns a landline phone of the company.
     *
     * @return The company landline phone.
     */
    String getLandlinePhone();

    /**
     * Sets a new landline phone to the company.
     *
     * @param landlinePhone a new landline phone to the company.
     */
    void setLandlinePhone(final String landlinePhone);

    /**
     * Returns a fax of the company.
     *
     * @return The company fax.
     */
    String getFax();

    /**
     * Sets a new fax to the company.
     *
     * @param fax a new fax to the company.
     */
    void setFax(final String fax);

    /**
     * Returns a vkontakte url of the user.
     *
     * @return The user vkontakte url.
     */
    String getVkontakte();

    /**
     * Sets a new vkontakte url to the user.
     *
     * @param vkontakte a new vkontakte url to the user.
     */
    void setVkontakte(final String vkontakte);

    /**
     * Returns a facebook url of the user.
     *
     * @return The user facebook url.
     */
    String getFacebook();

    /**
     * Sets a new facebook url to the user.
     *
     * @param facebook a new facebook url to the user.
     */
    void setFacebook(final String facebook);

    /**
     * Returns a twitter url of the user.
     *
     * @return The user twitter url.
     */
    String getTwitter();

    /**
     * Sets a new twitter url to the user.
     *
     * @param twitter a new twitter url to the user.
     */
    void setTwitter(final String twitter);

    /**
     * Returns a skype username of the user.
     *
     * @return The user skype username.
     */
    String getSkype();

    /**
     * Sets a new skype username to the user.
     *
     * @param skype a new skype username to the user.
     */
    void setSkype(final String skype);

    /**
     * @param contacts
     * @return
     */
    Contacts initialize(final Contacts contacts);
}
