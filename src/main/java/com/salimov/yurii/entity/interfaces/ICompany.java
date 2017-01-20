package com.salimov.yurii.entity.interfaces;

import com.salimov.yurii.enums.CompanyType;

import java.util.Collection;
import java.util.List;

/**
 * The interface describes a set of methods
 * for working with objects of
 * the {@link com.salimov.yurii.entity.Company} class.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see IContent
 * @see com.salimov.yurii.entity.Company
 */
public interface ICompany<E extends Number>
        extends IContent<E> {

    /**
     * Initializes some parameter of the company.
     *
     * @param title         a new title to the company.
     * @param domain        a new domain to the company.
     * @param tagline       a new tagline to the company.
     * @param description   a new description to the company.
     * @param information   a new information to the company.
     * @param mobilePhone   a new mobile phone to the company.
     * @param landlinePhone a new landline phone to the company.
     * @param fax           a new fax to the company.
     * @param email         a new e-mail to the company.
     * @param senderEmail   a new sender e-mail to the company.
     * @param senderPass    a new sender pass  to the company.
     * @param vkontakte     a new vkontakte url to the company.
     * @param facebook      a new facebook url to the company.
     * @param twitter       a new twitter url to the company.
     * @param skype         a new skype username to the company.
     * @param address       a new address to the company.
     * @param keywords      a new keywords to the company.
     * @param googleMaps    a new google maps url to the company.
     * @param logoUrl       a new logo URL to the company.
     * @param faviconUrl    a new favicon URL to the company.
     */
    void initialize(
            final String title,
            final String domain,
            final String tagline,
            final String description,
            final String information,
            final String mobilePhone,
            final String landlinePhone,
            final String fax,
            final String email,
            final String senderEmail,
            final String senderPass,
            final String vkontakte,
            final String facebook,
            final String twitter,
            final String skype,
            final String address,
            final String keywords,
            final String googleMaps,
            final String logoUrl,
            final String faviconUrl
    );

    /**
     * Initializes some parameter of the company.
     *
     * @param title         a new title to the company.
     * @param domain        a new domain to the company.
     * @param tagline       a new tagline to the company.
     * @param description   a new description to the company.
     * @param information   a new information to the company.
     * @param mobilePhone   a new mobile phone to the company.
     * @param landlinePhone a new landline phone to the company.
     * @param fax           a new fax to the company.
     * @param email         a new e-mail to the company.
     * @param senderEmail   a new sender e-mail to the company.
     * @param senderPass    a new sender pass  to the company.
     * @param vkontakte     a new vkontakte url to the company.
     * @param facebook      a new facebook url to the company.
     * @param twitter       a new twitter url to the company.
     * @param skype         a new skype username to the company.
     * @param address       a new address to the company.
     * @param keywords      a new keywords to the company.
     * @param googleMaps    a new google maps url to the company.
     */
    void initialize(
            final String title,
            final String domain,
            final String tagline,
            final String description,
            final String information,
            final String mobilePhone,
            final String landlinePhone,
            final String fax,
            final String email,
            final String senderEmail,
            final String senderPass,
            final String vkontakte,
            final String facebook,
            final String twitter,
            final String skype,
            final String address,
            final String keywords,
            final String googleMaps
    );

    /**
     * Returns a domain of the company.
     *
     * @return The company domain.
     */
    String getDomain();

    /**
     * Sets a new domain to the company.
     *
     * @param domain a new domain to the company.
     */
    void setDomain(final String domain);

    /**
     * Returns a domain of the company.
     *
     * @return The company domain.
     */
    String getTagline();

    /**
     * Sets a new tagline to the company.
     *
     * @param tagline a new domain to the company.
     */
    void setTagline(final String tagline);

    /**
     * Returns a information of the company.
     *
     * @return The information domain.
     */
    String getInformation();

    /**
     * Sets a new information to the company.
     *
     * @param information a new information to the company.
     */
    void setInformation(final String information);

    /**
     * Returns a e-mail of the company.
     *
     * @return The company e-mail.
     */
    String getEmail();

    /**
     * Sets a new e-mail to the company.
     *
     * @param email a new e-mail to the company.
     */
    void setEmail(final String email);

    /**
     * Returns a sender e-mail of the company.
     *
     * @return The company sender e-mail.
     */
    String getSenderEmail();

    /**
     * Sets a new sender e-mail to the company.
     *
     * @param senderEmail a new sender e-mail to the company.
     */
    void setSenderEmail(final String senderEmail);

    /**
     * Returns a sender password of the company.
     *
     * @return The company sender password.
     */
    String getSenderPass();

    /**
     * Sets a new sender password to the company.
     *
     * @param senderPass a new sender password to the company.
     */
    void setSenderPass(final String senderPass);

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
     * Returns a vkontakte url of the company.
     *
     * @return The company vkontakte url.
     */
    String getVkontakte();

    /**
     * Sets a new vkontakte url to the company.
     *
     * @param vkontakte a new vkontakte url to the company.
     */
    void setVkontakte(final String vkontakte);

    /**
     * Returns a facebook url of the company.
     *
     * @return The company facebook url.
     */
    String getFacebook();

    /**
     * Sets a new facebook url to the company.
     *
     * @param facebook a new facebook url to the company.
     */
    void setFacebook(final String facebook);

    /**
     * Returns a twitter url of the company.
     *
     * @return The company twitter url.
     */
    String getTwitter();

    /**
     * Sets a new twitter url to the company.
     *
     * @param twitter a new twitter url to the company.
     */
    void setTwitter(final String twitter);

    /**
     * Returns a skype username of the company.
     *
     * @return The company skype username.
     */
    String getSkype();

    /**
     * Sets a new skype username to the company.
     *
     * @param skype a new skype username to the company.
     */
    void setSkype(final String skype);

    /**
     * Returns a address of the company.
     *
     * @return The company address.
     */
    String getAddress();

    /**
     * Sets a new address to the company.
     *
     * @param address a new address to the company.
     */
    void setAddress(final String address);

    /**
     * Returns a start work time of the company.
     *
     * @return The company start work time.
     */
    String getWorkTimeFrom();

    /**
     * Sets a new start work time to the company.
     *
     * @param workTimeFrom a new start work time to the company.
     */
    void setWorkTimeFrom(final String workTimeFrom);

    /**
     * Returns a finish work time of the company.
     *
     * @return The company finish work time.
     */
    String getWorkTimeTo();

    /**
     * Sets a new finish work time to the company.
     *
     * @param workTimeTo a new finish work time to the company.
     */
    void setWorkTimeTo(final String workTimeTo);

    /**
     * Returns a google maps url of the company.
     *
     * @return The company google maps url.
     */
    String getGoogleMaps();

    /**
     * Sets a new google maps url to the company.
     *
     * @param googleMaps a new google maps url to the company.
     */
    void setGoogleMaps(final String googleMaps);

    /**
     * Returns a logo URL of the company.
     *
     * @return The company logo URL.
     */
    String getLogoUrl();

    /**
     * Sets a new logo URL to the article.
     * If parameter logo URL is invalid
     * then sets {@code null}.
     *
     * @param logoUrl a new logo to the article.
     */
    void setLogoUrl(final String logoUrl);

    /**
     * Returns a favicon URL of the company.
     *
     * @return The company favicon URL.
     */
    String getFaviconUrl();

    /**
     * Sets a new favicon URL to the article.
     * If parameter favicon URL is invalid
     * then sets {@code null}.
     *
     * @param faviconUrl a new favicon to the article.
     */
    void setFaviconUrl(final String faviconUrl);

    /**
     * Adds new photo to the list of slides.
     *
     * @param slide a photo to add.
     */
    void addSlide(final String slide);

    /**
     * Adds new photos to the list of slides.
     *
     * @param slides a photos to add.
     */
    void addSlides(final Collection<String> slides);

    /**
     * Adds new photos to the list of slides.
     *
     * @param slides a photos to add.
     */
    void addSlides(final String[] slides);

    /**
     * Removes photo from the list of slides.
     *
     * @param slide a photo to remove.
     */
    void removeSlide(final String slide);

    /**
     * Removes photos from the list of slides.
     *
     * @param slides a photos to remove.=
     */
    void removeSlides(final Collection<String> slides);

    /**
     * Clears the list of slides.
     *
     */
    void clearSlides();

    /**
     * Returns a copy list of slides.
     *
     * @return The list of slides.
     */
    List<String> getSlidesList();

    /**
     * Returns a slides string.
     *
     * @return The slides string.
     */
    String getSlides();

    /**
     * Sets a new slides to the article.
     *
     * @param slides a slides to add.
     */
    void setSlides(final String slides);

    /**
     * Contains slide in the list of slides.
     *
     * @param slide a photo to contain.
     * @return Returns {@code true} if photo is contains,
     * otherwise returns {@code false}.
     */
    boolean containsSlide(final String slide);

    /**
     * Contains slides in the list of slides.
     *
     * @param slides a photos to contain.
     * @return Returns {@code true} if photos are contains,
     * otherwise returns {@code false}.
     */
    boolean containsSlides(final Collection<String> slides);

    /**
     * Returns a domain of the company.
     *
     * @return The company domain.
     * @see CompanyType
     */
    CompanyType getType();

    /**
     * Sets a new type to the company.
     *
     * @param type a new logo to the company.
     * @see CompanyType
     */
    void setType(final CompanyType type);

    /**
     * Checks whether the company is opened now.
     *
     * @return Returns {@code true} if the company is opened now,
     * otherwise returns {@code false}.
     */
    boolean isOpen();
}
