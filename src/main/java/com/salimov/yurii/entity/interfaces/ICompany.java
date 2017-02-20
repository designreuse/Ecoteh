package com.salimov.yurii.entity.interfaces;

import com.salimov.yurii.entity.Address;
import com.salimov.yurii.entity.Company;
import com.salimov.yurii.entity.Contacts;
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
public interface ICompany<T extends Number> extends IContent<T> {

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
     * @return
     */
    Contacts getContacts();

    /**
     * @param contacts
     */
    void setContacts(final Contacts contacts);

    /**
     * @return
     */
    Address getAddress();

    /**
     * @param address
     */
    void setAddress(final Address address);

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

    /**
     * @param company
     * @return
     */
    Company initialize(final Company company);
}
