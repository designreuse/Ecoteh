package com.salimov.yurii.entity;

import com.salimov.yurii.entity.interfaces.ICompany;
import com.salimov.yurii.enums.CompanyType;
import com.salimov.yurii.util.translator.Translator;
import com.salimov.yurii.util.worktime.Time;

import javax.persistence.*;
import java.util.*;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link Company} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see Content
 * @see ICompany
 */
@Entity
@Table(name = "companies")
public final class Company
        extends Content<Long>
        implements ICompany<Long> {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The tagline of a company.
     */
    @Column(name = "tagline")
    private String tagline;

    /**
     * The information of a company.
     */
    @Column(
            name = "information",
            nullable = false
    )
    private String information;

    /**
     * The domain of a company.
     */
    @Column(name = "domain")
    private String domain;

    /**
     * The mobile phone of a company.
     */
    @Column(name = "mobile_phone")
    private String mobilePhone;

    /**
     * The landline phone of a company.
     */
    @Column(name = "landline_phone")
    private String landlinePhone;

    /**
     * The fax of a company.
     */
    @Column(name = "fax")
    private String fax;

    /**
     * The e-mail of a company.
     */
    @Column(name = "email")
    private String email;

    /**
     * The sender e-mail of a company.
     */
    @Column(name = "sender_email")
    private String senderEmail;

    /**
     * The sender password of a company.
     */
    @Column(name = "sender_pass")
    private String senderPass;

    /**
     * The vkontakte url of a company.
     */
    @Column(name = "vkontakte")
    private String vkontakte;

    /**
     * The facebook url of a company.
     */
    @Column(name = "facebook")
    private String facebook;

    /**
     * The twitter url of a company.
     */
    @Column(name = "twitter")
    private String twitter;

    /**
     * The skype username of a company.
     */
    @Column(name = "skype")
    private String skype;

    /**
     * The address of a company.
     */
    @Column(name = "address")
    private String address;

    /**
     * The start work time of a company.
     */
    @Column(name = "work_time_from")
    private String workTimeFrom;

    /**
     * The finish work time of a company.
     */
    @Column(name = "work_time_to")
    private String workTimeTo;

    /**
     * The google maps url of a company.
     */
    @Column(name = "google_maps")
    private String googleMaps;

    /**
     * The logo URL of a company.
     */
    @Column(name = "logo")
    private String logoUrl;

    /**
     * The favicon URL of a company.
     */
    @Column(name = "favicon")
    private String faviconUrl;

    /**
     * The set of slides.
     *
     * @see File
     */
    @Column(name = "slides")
    private String slides = "";

    /**
     * The type of a company.
     *
     * @see CompanyType
     */
    @Column(
            name = "type",
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private CompanyType type;

    /**
     * Default constructor.
     * Sets default company type {@link CompanyType#PARTNER}.
     */
    public Company() {
        setType(CompanyType.PARTNER);
    }

    /**
     * Constructor.
     *
     * @param title           a title of the new company.
     * @param domain          a domain of the new company.
     * @param tagline         a tagline of the new company.
     * @param description     a description of the new company.
     * @param information     a information of the new company.
     * @param mobilePhone     a mobile phone of the new company.
     * @param landlinePhone   a landline phone of the new company.
     * @param fax             a fax of the new company.
     * @param email           a e-mail of the new company.
     * @param senderEmail     a sender e-mail of the new company.
     * @param senderEmailPass a sender e-mail password of the new company.
     * @param vkontakte       a vkontakte url of the new company.
     * @param facebook        a facebook url of the new company.
     * @param twitter         a twitter url of the new company.
     * @param skype           a skype username of the new company.
     * @param address         a address of the new company.
     * @param keywords        a keywords of the new company.
     * @param googleMaps      a Google maps url of the new company.
     * @param logoUrl         a logo of the new company.
     * @param faviconUrl      a favicon of the new company.
     */
    public Company(
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
            final String senderEmailPass,
            final String vkontakte,
            final String facebook,
            final String twitter,
            final String skype,
            final String address,
            final String keywords,
            final String googleMaps,
            final String logoUrl,
            final String faviconUrl
    ) {
        super(title, description, keywords);
        initialize(
                title, domain,
                tagline, description, information,
                mobilePhone, landlinePhone, fax,
                email, senderEmail, senderEmailPass,
                vkontakte, facebook, twitter, skype,
                address, keywords, googleMaps,
                logoUrl, faviconUrl
        );
        setType(CompanyType.PARTNER);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(type.name()).append(" ").append(super.toString())
                .append(" \nTagline: ").append(tagline)
                .append(" \nInformation: ").append(information)
                .append(" \nDomain: ").append(domain)
                .append(" \nMobile Phone: ").append(mobilePhone)
                .append(" \nLandline Phone: ").append(landlinePhone)
                .append(" \nFax: ").append(fax)
                .append(" \nE-mail:").append(email)
                .append(" \nVkontakte: ").append(vkontakte)
                .append(" \nFacebook: ").append(facebook)
                .append(" \nTwitter: ").append(twitter)
                .append(" \nSkype: ").append(skype);
        if (type.equals(CompanyType.MAIN)) {
            sb.append(" \nSenderImpl E-mail: ").append(senderEmail)
                    .append(" \nSenderImpl Password: ").append(senderPass)
                    .append(" \nWork Time: ").append(workTimeFrom)
                    .append(" - ").append(workTimeTo);
        }
        sb.append(" \nAddress: ").append(address)
                .append(" \nGoogle Maps: ").append(googleMaps);
        return sb.toString();
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return Returns true if this object is the same as the obj
     * argument, otherwise returns false.
     */
    @Override
    public boolean equals(final Object object) {
        boolean result = super.equals(object);
        if (result) {
            final Company other = (Company) object;
            result = (
                    this.type != null ?
                            this.type.equals(other.type) :
                            other.type == null
            ) && (
                    isNotBlank(this.domain) ?
                            this.domain.equalsIgnoreCase(other.domain) :
                            isBlank(other.domain)
            ) && (
                    isNotBlank(this.tagline) ?
                            this.tagline.equals(other.tagline) :
                            isBlank(other.tagline)
            ) && (
                    isNotBlank(this.getDescription()) ?
                            this.getDescription().equalsIgnoreCase(
                                    other.getDescription()
                            ) :
                            isBlank(other.getDescription())
            ) && (
                    isNotBlank(this.information) ?
                            this.information.equals(other.information) :
                            isBlank(other.information)
            ) && (
                    isNotBlank(this.address) ?
                            this.address.equalsIgnoreCase(other.address) :
                            isBlank(other.address)
            );
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
        return super.hashCode() + (
                this.type != null ? this.type.hashCode() : 0
        ) + (
                isNotBlank(this.domain) ? this.domain.hashCode() : 0
        ) + (
                isNotBlank(this.tagline) ? this.tagline.hashCode() : 0
        ) + (
                isNotBlank(getDescription()) ? getDescription().hashCode() : 0
        ) + (
                isNotBlank(this.information) ? this.information.hashCode() : 0
        ) + (
                isNotBlank(this.address) ? this.address.hashCode() : 0
        );
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance.
     */
    @Override
    public Company clone() {
        return (Company) super.clone();
    }

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
    @Override
    public void initialize(
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
    ) {
        initialize(
                title, domain,
                tagline, description, information,
                mobilePhone, landlinePhone, fax, email,
                senderEmail, senderPass,
                vkontakte, facebook, twitter, skype,
                address, keywords, googleMaps
        );
        setLogoUrl(logoUrl);
        setFaviconUrl(faviconUrl);
    }

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
    @Override
    public void initialize(
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
    ) {
        super.initialize(title, description, keywords);
        setDomain(domain);
        setTagline(tagline);
        setInformation(information);
        setMobilePhone(mobilePhone);
        setLandlinePhone(landlinePhone);
        setFax(fax);
        setEmail(email);
        setSenderEmail(senderEmail);
        setSenderPass(senderPass);
        setVkontakte(vkontakte);
        setFacebook(facebook);
        setTwitter(twitter);
        setSkype(skype);
        setAddress(address);
        setGoogleMaps(googleMaps);
    }

    /**
     * Returns a domain of the company.
     *
     * @return The company domain.
     */
    @Override
    public String getDomain() {
        return this.domain;
    }

    /**
     * Sets a new domain to the company.
     * If parameter domain is blank, then sets {@code null}.
     *
     * @param domain a new domain to the company.
     */
    @Override
    public void setDomain(final String domain) {
        if (isNotBlank(domain)) {
            final String temp = domain.replace("http://", "")
                    .replace("https://", "");
            this.domain = isNotBlank(temp) ? temp : null;
        } else {
            this.domain = null;
        }
    }

    /**
     * Returns a domain of the company.
     *
     * @return The company domain.
     */
    @Override
    public String getTagline() {
        return this.tagline;
    }

    /**
     * Sets a new tagline to the company.
     * If parameter tagline is blank, then sets {@code null}.
     *
     * @param tagline a new domain to the company.
     */
    @Override
    public void setTagline(final String tagline) {
        this.tagline = isNotBlank(tagline) ? tagline : null;
    }

    /**
     * Returns a information of the company.
     *
     * @return The information domain.
     */
    @Override
    public String getInformation() {
        return this.information;
    }

    /**
     * Sets a new information to the company.
     * If parameter information is blank, then sets {@code null}.
     *
     * @param information a new information to the company.
     */
    @Override
    public void setInformation(final String information) {
        this.information = isNotBlank(information) ? information : null;
    }

    /**
     * Returns a e-mail of the company.
     *
     * @return The company e-mail.
     */
    @Override
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets a new e-mail to the company.
     * If parameter e-mail is blank, then sets {@code null}.
     *
     * @param email a new e-mail to the company.
     */
    @Override
    public void setEmail(final String email) {
        this.email = isNotBlank(email) ? email : null;
    }

    /**
     * Returns a sender e-mail of the company.
     *
     * @return The company sender e-mail.
     */
    @Override
    public String getSenderEmail() {
        return this.senderEmail;
    }

    /**
     * Sets a new sender e-mail to the company.
     * If parameter sender e-mail is blank, then sets {@code null}.
     *
     * @param senderEmail a new sender e-mail to the company.
     */
    @Override
    public void setSenderEmail(final String senderEmail) {
        this.senderEmail = isNotBlank(senderEmail) ? senderEmail : null;
    }

    /**
     * Returns a sender password of the company.
     *
     * @return The company sender password.
     */
    @Override
    public String getSenderPass() {
        return this.senderPass;
    }

    /**
     * Sets a new sender password to the company.
     * If parameter sender password is blank, then sets {@code null}.
     *
     * @param senderPass a new sender password to the company.
     */
    public void setSenderPass(final String senderPass) {
        this.senderPass = isNotBlank(senderPass) ? senderPass : null;
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
        this.mobilePhone = isNotBlank(mobilePhone) ? mobilePhone : null;
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
        this.landlinePhone = isNotBlank(landlinePhone) ? landlinePhone : null;
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
        this.fax = isNotBlank(fax) ? fax : null;
    }

    /**
     * Returns a vkontakte url of the company.
     *
     * @return The company vkontakte url.
     */
    @Override
    public String getVkontakte() {
        return this.vkontakte;
    }

    /**
     * Sets a new vkontakte url to the company.
     * If parameter vkontakte url is blank, then sets {@code null}.
     *
     * @param vkontakte a new vkontakte url to the company.
     */
    @Override
    public void setVkontakte(final String vkontakte) {
        final String temp = isNotBlank(vkontakte) ? vkontakte.toLowerCase()
                .replace("http://", "").replace("https://", "")
                .replace("m.vk.com", "").replace("vk.com", "") : null;
        this.vkontakte = isNotBlank(temp) ? temp : null;
    }

    /**
     * Returns a facebook url of the company.
     *
     * @return The company facebook url.
     */
    @Override
    public String getFacebook() {
        return this.facebook;
    }

    /**
     * Sets a new facebook url to the company.
     * If parameter facebook url is blank, then sets {@code null}.
     *
     * @param facebook a new facebook url to the company.
     */
    @Override
    public void setFacebook(final String facebook) {
        String temp = isNotBlank(facebook) ? facebook.toLowerCase()
                .replace("http://", "")
                .replace("https://", "")
                .replace("www.facebook.com", "")
                .replace("m.facebook.com", "") : null;
        this.facebook = isNotBlank(temp) ? temp : null;
    }

    /**
     * Returns a twitter url of the company.
     *
     * @return The company twitter url.
     */
    @Override
    public String getTwitter() {
        return this.twitter;
    }

    /**
     * Sets a new twitter url to the company.
     * If parameter twitter url is blank, then sets {@code null}.
     *
     * @param twitter a new twitter url to the company.
     */
    @Override
    public void setTwitter(final String twitter) {
        final String temp = isNotBlank(twitter) ? twitter.toLowerCase()
                .replace("http://", "")
                .replace("https://", "")
                .replace("mobile.twitter.com", "")
                .replace("twitter.com", "") : null;
        this.twitter = isNotBlank(temp) ? temp : null;
    }

    /**
     * Returns a skype username of the company.
     *
     * @return The company skype username.
     */
    @Override
    public String getSkype() {
        return this.skype;
    }

    /**
     * Sets a new skype username to the company.
     * If parameter skype username is blank, then sets {@code null}.
     *
     * @param skype a new skype username to the company.
     */
    @Override
    public void setSkype(final String skype) {
        this.skype = isNotBlank(skype) ? skype : null;
    }

    /**
     * Returns a address of the company.
     *
     * @return The company address.
     */
    @Override
    public String getAddress() {
        return this.address;
    }

    /**
     * Sets a new address to the company.
     * If parameter address is blank, then sets {@code null}.
     *
     * @param address a new address to the company.
     */
    @Override
    public void setAddress(final String address) {
        this.address = isNotBlank(address) ? address : null;
    }

    /**
     * Returns a start work time of the company.
     *
     * @return The company start work time.
     */
    @Override
    public String getWorkTimeFrom() {
        return this.workTimeFrom;
    }

    /**
     * Sets a new start work time to the company.
     * If parameter start work time is blank, then sets "00:00".
     *
     * @param workTimeFrom a new start work time to the company.
     */
    @Override
    public void setWorkTimeFrom(final String workTimeFrom) {
        this.workTimeFrom = new Time(workTimeFrom).getCorrectTime();
    }

    /**
     * Returns a finish work time of the company.
     *
     * @return The company finish work time.
     */
    @Override
    public String getWorkTimeTo() {
        return this.workTimeTo;
    }

    /**
     * Sets a new finish work time to the company.
     * If parameter finish work time is blank, then sets "00:00".
     *
     * @param workTimeTo a new finish work time to the company.
     */
    @Override
    public void setWorkTimeTo(final String workTimeTo) {
        this.workTimeTo = new Time(workTimeTo).getCorrectTime();
    }

    /**
     * Returns a google maps url of the company.
     *
     * @return The company google maps url.
     */
    @Override
    public String getGoogleMaps() {
        return this.googleMaps;
    }

    /**
     * Sets a new google maps url to the company.
     * If parameter google maps url is blank, then sets {@code null}.
     *
     * @param googleMaps a new google maps url to the company.
     */
    @Override
    public void setGoogleMaps(final String googleMaps) {
        this.googleMaps = isNotBlank(googleMaps) ? googleMaps : null;
    }

    /**
     * Returns a logo URL of the company.
     *
     * @return The company logo URL.
     */
    @Override
    public String getLogoUrl() {
        return this.logoUrl;
    }

    /**
     * Sets a new logo URL to the article.
     * If parameter logo URL is invalid
     * then sets {@code null}.
     *
     * @param logoUrl a new logo to the article.
     * @see File
     */
    @Override
    public void setLogoUrl(final String logoUrl) {
        this.logoUrl = isNotBlank(logoUrl) ? logoUrl : null;
    }

    /**
     * Returns a favicon URL of the company.
     *
     * @return The company favicon URL.
     */
    @Override
    public String getFaviconUrl() {
        return this.faviconUrl;
    }

    /**
     * Sets a new favicon URL to the article.
     * If parameter favicon URL is invalid
     * then sets {@code null}.
     *
     * @param faviconUrl a new favicon to the article.
     */
    @Override
    public void setFaviconUrl(final String faviconUrl) {
        this.faviconUrl = isNotBlank(faviconUrl) ? faviconUrl : null;
    }

    /**
     * Adds new photo to the list of slides.
     * Adds a new photo, if it is valid.
     *
     * @param slide a photo to add.
     * @see File
     */
    @Override
    public void addSlide(final String slide) {
        if (isNotBlank(slide)) {
            this.slides += (isNotBlank(this.slides) ? ", " : "")
                    + slide.replace(" ", "");
        }
    }

    /**
     * Adds new photos to the list of slides.
     * Adds a new photos, if they are valid.
     *
     * @param slides a photos to add.
     * @see File
     */
    @Override
    public void addSlides(final Collection<String> slides) {
        if ((slides != null) && !slides.isEmpty()) {
            slides.forEach(this::addSlide);
        }
    }

    /**
     * Adds new photos to the list of slides.
     *
     * @param slides a photos to add.
     */
    @Override
    public void addSlides(final String[] slides) {
        for (String slide : slides) {
            addSlide(slide);
        }
    }

    /**
     * Removes photo from the list of slides.
     *
     * @param slide a photo to remove.
     * @see File
     */
    @Override
    public void removeSlide(final String slide) {
        if (isNotBlank(slide)) {
            this.slides = this.slides.replace(
                    "," + slide, ""
            ).replace(slide, "");
        }
    }

    /**
     * Removes photos from the list of slides.
     *
     * @param slides a photos to remove.
     * @see File
     */
    @Override
    public void removeSlides(final Collection<String> slides) {
        slides.forEach(
                this::removeSlide
        );
    }

    /**
     * Clears the list of slides.
     *
     * @see File
     */
    @Override
    public void clearSlides() {
        this.slides = "";
    }

    /**
     * Returns a copy list of slides.
     *
     * @return The list of slides.
     * @see File
     */
    @Override
    public List<String> getSlidesList() {
        final List<String> result = new ArrayList<>();
        for (String slide : this.slides.split(",")) {
            if (isNotBlank(slide)) {
                result.add(slide);
            }
        }
        return result;
    }

    @Override
    public String getSlides() {
        return this.slides;
    }

    /**
     * Sets a new slides to the article.
     * Clears the list of slides and adds new slides.
     *
     * @param slides a slides to add.
     */
    @Override
    public void setSlides(final String slides) {
        this.slides = isNotBlank(slides) ? slides.replace(" ", "") : "";
    }

    /**
     * Contains slide in the list of slides.
     *
     * @param slide a photo to contain.
     * @return Returns {@code true} if photo is contains,
     * otherwise returns {@code false}.
     */
    @Override
    public boolean containsSlide(final String slide) {
        return this.slides.contains(slide);
    }

    /**
     * Contains slides in the list of slides.
     *
     * @param slides a photos to contain.
     * @return Returns {@code true} if photos are contains,
     * otherwise returns {@code false}.
     */
    @Override
    public boolean containsSlides(final Collection<String> slides) {
        boolean result = true;
        for (String slide : slides) {
            if (!this.slides.contains(slide)) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Returns a domain of the company.
     *
     * @return The company domain.
     * @see CompanyType
     */
    @Override
    public CompanyType getType() {
        return this.type;
    }

    /**
     * Sets a new type to the company.
     *
     * @param type a new logo to the company.
     * @see CompanyType
     */
    @Override
    public void setType(final CompanyType type) {
        this.type = type;
    }

    /**
     * Returns a domain of the company.
     *
     * @return The company domain.
     */
    @Override
    public String getUrl() {
        String url = super.getUrl();
        if (url == null) {
            url = Translator.fromCyrillicToLatin(
                    getDomain()
            );
        }
        return url;
    }

    /**
     * Checks whether the company is opened now.
     *
     * @return Returns {@code true} if the company is opened now,
     * otherwise returns {@code false}.
     */
    @Override
    public boolean isOpen() {
        return Time.isWorkTime(
                this.workTimeFrom,
                this.workTimeTo
        );
    }
}
