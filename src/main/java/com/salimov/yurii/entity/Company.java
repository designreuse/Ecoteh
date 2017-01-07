package com.salimov.yurii.entity;

import com.salimov.yurii.enums.CompanyType;
import com.salimov.yurii.util.translator.Translator;

import javax.persistence.*;
import java.util.*;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link Company} class.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Content
 * @see Model
 */
@Entity
@Table(name = "companies")
public final class Company extends Content<Long> {

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
    @Column(name = "information", nullable = false)
    private String information;

    /**
     * The advantages of a company.
     */
    @Column(name = "advantages")
    private String advantages;

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
    @Column(name = "address", nullable = false)
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
     * The logo of a company.
     *
     * @see Photo
     */
    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "logo_id",
            referencedColumnName = "id"
    )
    private Photo logo;

    /**
     * The favicon of a company.
     *
     * @see Photo
     */
    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "favicon_id",
            referencedColumnName = "id"
    )
    private Photo favicon;

    /**
     * The set of slides.
     *
     * @see Photo
     */
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "company_photo",
            joinColumns = {
                    @JoinColumn(
                            name = "company_id",
                            referencedColumnName = "id"
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "photo_id",
                            referencedColumnName = "id"
                    )
            }
    )
    private final Set<Photo> slides = new HashSet<>();

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
     * Initializes a main company parameters.
     *
     * @param title           a title of the new company.
     * @param domain          a domain of the new company.
     * @param tagline         a tagline of the new company.
     * @param description     a description of the new company.
     * @param information     a information of the new company.
     * @param advantages      a advantages of the new company.
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
     * @param logo            a logo of the new company.
     * @param favicon         a favicon of the new company.
     * @see Photo
     */
    public Company(
            final String title,
            final String domain,
            final String tagline,
            final String description,
            final String information,
            final String advantages,
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
            final Photo logo,
            final Photo favicon
    ) {
        super(title, description, keywords);
        initialize(
                title, domain, tagline, description, information,
                advantages, mobilePhone, landlinePhone, fax,
                email, senderEmail, senderEmailPass, vkontakte,
                facebook, twitter, skype, address,
                keywords, googleMaps, logo, favicon
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
                .append(" \nAdvantages: ").append(advantages)
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
     * Initializes some parameter of the company.
     *
     * @param title         a new title to the company.
     * @param domain        a new domain to the company.
     * @param tagline       a new tagline to the company.
     * @param description   a new description to the company.
     * @param information   a new information to the company.
     * @param advantages    a new advantages to the company.
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
     * @param logo          a new logo to the company.
     * @param favicon       a new favicon to the company.
     * @see Photo
     */
    public void initialize(
            final String title,
            final String domain,
            final String tagline,
            final String description,
            final String information,
            final String advantages,
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
            final Photo logo,
            final Photo favicon
    ) {
        initialize(
                title, domain, tagline, description,
                information, advantages, mobilePhone,
                landlinePhone, fax, email, senderEmail,
                senderPass, vkontakte, facebook, twitter,
                skype,address, keywords, googleMaps
        );
        setLogo(logo);
        setFavicon(favicon);
    }

    /**
     * Initializes some parameter of the company.
     *
     * @param title         a new title to the company.
     * @param domain        a new domain to the company.
     * @param tagline       a new tagline to the company.
     * @param description   a new description to the company.
     * @param information   a new information to the company.
     * @param advantages    a new advantages to the company.
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
    public void initialize(
            final String title,
            final String domain,
            final String tagline,
            final String description,
            final String information,
            final String advantages,
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
        setAdvantages(advantages);
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
    public String getDomain() {
        return this.domain;
    }

    /**
     * Sets a new domain to the company.
     * If parameter domain is blank, then sets {@code null}.
     *
     * @param domain a new domain to the company.
     */
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
    public String getTagline() {
        return this.tagline;
    }

    /**
     * Sets a new tagline to the company.
     * If parameter tagline is blank, then sets {@code null}.
     *
     * @param tagline a new domain to the company.
     */
    public void setTagline(final String tagline) {
        this.tagline = isNotBlank(tagline) ? tagline : null;
    }

    /**
     * Returns a information of the company.
     *
     * @return The information domain.
     */
    public String getInformation() {
        return this.information;
    }

    /**
     * Sets a new information to the company.
     * If parameter information is blank, then sets {@code null}.
     *
     * @param information a new information to the company.
     */
    public void setInformation(final String information) {
        this.information = isNotBlank(information) ? information : null;
    }

    /**
     * Returns a advantages of the company.
     *
     * @return The company advantages.
     */
    public String getAdvantages() {
        return this.advantages;
    }

    /**
     * Sets a new advantages to the company.
     * If parameter advantages is blank, then sets {@code null}.
     *
     * @param advantages a new advantages to the company.
     */
    public void setAdvantages(final String advantages) {
        this.advantages = isNotBlank(advantages) ? advantages : null;
    }

    /**
     * Returns a e-mail of the company.
     *
     * @return The company e-mail.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets a new e-mail to the company.
     * If parameter e-mail is blank, then sets {@code null}.
     *
     * @param email a new e-mail to the company.
     */
    public void setEmail(final String email) {
        this.email = isNotBlank(email) ? email : null;
    }

    /**
     * Returns a sender e-mail of the company.
     *
     * @return The company sender e-mail.
     */
    public String getSenderEmail() {
        return this.senderEmail;
    }

    /**
     * Sets a new sender e-mail to the company.
     * If parameter sender e-mail is blank, then sets {@code null}.
     *
     * @param senderEmail a new sender e-mail to the company.
     */
    public void setSenderEmail(final String senderEmail) {
        this.senderEmail = isNotBlank(senderEmail) ? senderEmail : null;
    }

    /**
     * Returns a sender password of the company.
     *
     * @return The company sender password.
     */
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
    public String getMobilePhone() {
        return this.mobilePhone;
    }

    /**
     * Sets a new mobile phone to the company.
     * If parameter mobile phone is blank, then sets {@code null}.
     *
     * @param mobilePhone a new mobile phone to the company.
     */
    public void setMobilePhone(final String mobilePhone) {
        this.mobilePhone = isNotBlank(mobilePhone) ? mobilePhone : null;
    }

    /**
     * Returns a landline phone of the company.
     *
     * @return The company landline phone.
     */
    public String getLandlinePhone() {
        return this.landlinePhone;
    }

    /**
     * Sets a new landline phone to the company.
     * If parameter landline phone is blank, then sets {@code null}.
     *
     * @param landlinePhone a new landline phone to the company.
     */
    public void setLandlinePhone(final String landlinePhone) {
        this.landlinePhone = isNotBlank(landlinePhone) ? landlinePhone : null;
    }

    /**
     * Returns a fax of the company.
     *
     * @return The company fax.
     */
    public String getFax() {
        return this.fax;
    }

    /**
     * Sets a new fax to the company.
     * If parameter fax is blank, then sets {@code null}.
     *
     * @param fax a new fax to the company.
     */
    public void setFax(final String fax) {
        this.fax = isNotBlank(fax) ? fax : null;
    }

    /**
     * Returns a vkontakte url of the company.
     *
     * @return The company vkontakte url.
     */
    public String getVkontakte() {
        return this.vkontakte;
    }

    /**
     * Sets a new vkontakte url to the company.
     * If parameter vkontakte url is blank, then sets {@code null}.
     *
     * @param vkontakte a new vkontakte url to the company.
     */
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
    public String getFacebook() {
        return this.facebook;
    }

    /**
     * Sets a new facebook url to the company.
     * If parameter facebook url is blank, then sets {@code null}.
     *
     * @param facebook a new facebook url to the company.
     */
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
    public String getTwitter() {
        return this.twitter;
    }

    /**
     * Sets a new twitter url to the company.
     * If parameter twitter url is blank, then sets {@code null}.
     *
     * @param twitter a new twitter url to the company.
     */
    public void setTwitter(final String twitter) {
        String temp = isNotBlank(twitter) ? twitter.toLowerCase()
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
    public String getSkype() {
        return this.skype;
    }

    /**
     * Sets a new skype username to the company.
     * If parameter skype username is blank, then sets {@code null}.
     *
     * @param skype a new skype username to the company.
     */
    public void setSkype(final String skype) {
        this.skype = isNotBlank(skype) ? skype : null;
    }

    /**
     * Returns a address of the company.
     *
     * @return The company address.
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Sets a new address to the company.
     * If parameter address is blank, then sets {@code null}.
     *
     * @param address a new address to the company.
     */
    public void setAddress(final String address) {
        this.address = isNotBlank(address) ? address : null;
    }

    /**
     * Returns a start work time of the company.
     *
     * @return The company start work time.
     */
    public String getWorkTimeFrom() {
        return this.workTimeFrom;
    }

    /**
     * Sets a new start work time to the company.
     * If parameter start work time is blank, then sets "00:00".
     *
     * @param workTimeFrom a new start work time to the company.
     */
    public void setWorkTimeFrom(final String workTimeFrom) {
        this.workTimeFrom = isNotBlank(workTimeFrom) ?
                correctTime(workTimeFrom) : "00:00";
    }

    /**
     * Returns a finish work time of the company.
     *
     * @return The company finish work time.
     */
    public String getWorkTimeTo() {
        return this.workTimeTo;
    }

    /**
     * Sets a new finish work time to the company.
     * If parameter finish work time is blank, then sets "00:00".
     *
     * @param workTimeTo a new finish work time to the company.
     */
    public void setWorkTimeTo(final String workTimeTo) {
        this.workTimeTo = isNotBlank(workTimeTo) ?
                correctTime(workTimeTo) : "00:00";
    }

    /**
     * Returns a google maps url of the company.
     *
     * @return The company google maps url.
     */
    public String getGoogleMaps() {
        return this.googleMaps;
    }

    /**
     * Sets a new google maps url to the company.
     * If parameter google maps url is blank, then sets {@code null}.
     *
     * @param googleMaps a new google maps url to the company.
     */
    public void setGoogleMaps(final String googleMaps) {
        this.googleMaps = isNotBlank(googleMaps) ? googleMaps : null;
    }

    /**
     * Returns a logo of the company.
     *
     * @return The company logo.
     * @see Photo
     */
    public Photo getLogo() {
        return this.logo;
    }

    /**
     * Sets a new logo to the article.
     * If parameter logo is invalid, then sets {@code null}.
     *
     * @param logo a new logo to the article.
     * @see Photo
     */
    public void setLogo(final Photo logo) {
        this.logo = Photo.isValidated(logo) ? logo : null;
    }

    /**
     * Returns a favicon of the company.
     *
     * @return The company favicon.
     * @see Photo
     */
    public Photo getFavicon() {
        return this.favicon;
    }

    /**
     * Sets a new favicon to the article.
     * If parameter favicon is invalid, then sets {@code null}.
     *
     * @param favicon a new favicon to the article.
     * @see Photo
     */
    public void setFavicon(final Photo favicon) {
        this.favicon = Photo.isValidated(favicon) ? favicon : null;
    }

    /**
     * Adds new photo to the list of slides.
     * Adds a new photo, if it is valid.
     *
     * @param slide a photo to add.
     * @see Photo
     */
    public void addSlide(final Photo slide) {
        if (Photo.isValidated(slide)) {
            this.slides.add(slide);
        }
    }

    /**
     * Adds new photos to the list of slides.
     * Adds a new photos, if they are valid.
     *
     * @param slides a photos to add.
     * @see Photo
     */
    public void addSlides(final Collection<Photo> slides) {
        if ((slides != null) && !slides.isEmpty()) {
            slides.forEach(this::addSlide);
        }
    }

    /**
     * Removes photo from the list of slides.
     *
     * @param slide a photo to remove.
     * @see Photo
     */
    public void removeSlide(final Photo slide) {
        this.slides.remove(slide);
    }

    /**
     * Removes photos from the list of slides.
     *
     * @param slides a photos to remove.
     * @see Photo
     */
    public void removeSlides(final Collection<Photo> slides) {
        this.slides.removeAll(slides);
    }

    /**
     * Clears the list of slides.
     *
     * @see Photo
     */
    public void clearSlides() {
        this.slides.clear();
    }

    /**
     * Returns a copy list of slides.
     *
     * @return The list of slides.
     * @see Photo
     */
    public List<Photo> getSlides() {
        return new ArrayList<>(this.slides);
    }

    /**
     * Sets a new slides to the article.
     * Clears the list of slides and adds new slides.
     *
     * @param slides a slides to add.
     * @see Photo
     */
    public void setSlides(final Collection<Photo> slides) {
        clearSlides();
        addSlides(slides);
    }

    /**
     * Contains slide in the list of slides.
     *
     * @param slide a photo to contain.
     * @return Returns {@code true} if photo is contains,
     * otherwise returns {@code false}.
     * @see Photo
     */
    public boolean containsSlide(final Photo slide) {
        return this.slides.contains(slide);
    }

    /**
     * Contains slides in the list of slides.
     *
     * @param slides a photos to contain.
     * @return Returns {@code true} if photos are contains,
     * otherwise returns {@code false}.
     * @see Photo
     */
    public boolean containsSlides(final Collection<Photo> slides) {
        return this.slides.containsAll(slides);
    }

    /**
     * Returns a domain of the company.
     *
     * @return The company domain.
     * @see CompanyType
     */
    public CompanyType getType() {
        return this.type;
    }

    /**
     * Sets a new type to the company.
     *
     * @param type a new logo to the company.
     * @see CompanyType
     */
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
    public boolean isOpen() {
        return isWorkDay() && isWorkHour(this.workTimeFrom, this.workTimeTo);
    }

    /**
     * Corrects time.
     * If input time has more than 24 hours or less than 0, then sets 0 hours.
     * If input time has than 0 minutes, then sets 0 minutes.
     * And If input time has more than 60 minutes, then adds one hours.
     * Returns "00:00" if method throws some exception.
     * Returns time in format "00:00".
     * Parse input time: 13:17 - hours = 13 and minutes = 17.
     * If hours greater or equal 24 or hours less or equal then sets hours = 0.
     * If minutes less 0 then sets minutes = 0.
     * If minutes greater 60 then sets hours = hours + minutes / 60.
     * If minutes greater 60 then sets minutes = minutes - minutes / 60.
     * If hours greater or equal 24 or hours less or equal then sets hours = 0.
     * Returns time in format "00:00".
     * Returns "00:00" if method throws some exception.
     *
     * @param time a time to check.
     * @return The correct time.
     */
    private static String correctTime(final String time) {
        String correctTime;
        try {
            int hours = Integer.parseInt(time.split(":")[0]);
            int minutes = Integer.parseInt(time.split(":")[1]);
            hours = hours >= 24 || hours <= 0 ? 0 : hours;
            minutes = minutes < 0 ? 0 : minutes;
            hours += minutes / 60;
            minutes %= 60;
            hours = hours >= 24 || hours <= 0 ? 0 : hours;
            correctTime = (hours < 10 ? "0" + hours : hours) +
                    ":" + (minutes < 10 ? "0" + minutes : minutes);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            correctTime = "00:00";
        }
        return correctTime;
    }

    /**
     * Checks whether a now time belongs at a given time interval.
     *
     * @param workTimeFrom a start work time of a company.
     * @param workTimeTo   a finish work time of a company.
     * @return Returns {@code true} if a now time belongs at a given
     * time interval, {@code false} otherwise.
     */
    private static boolean isWorkHour(
            final String workTimeFrom,
            final String workTimeTo
    ) {
        boolean result;
        try {
            final int from = getHour(workTimeFrom);
            final int to = getHour(workTimeTo);
            final Calendar calendar = Calendar.getInstance();
            final int hourNow = calendar.get(Calendar.HOUR_OF_DAY);
            result = hourNow >= from && hourNow < to;
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            result = false;
        }
        return result;
    }

    /**
     * Returns {@code true} if today is the working day.
     *
     * @return {@code true} if today is the working day,
     * {@code false} otherwise.
     */
    private static boolean isWorkDay() {
        final Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_WEEK);
        boolean result = true;
        switch (day) {
            case Calendar.SUNDAY:
            case Calendar.SATURDAY:
                result = false;
        }
        return result;
    }

    /**
     * Return number of hours in the inputted time.
     * Also the number of minutes is rounded to zero.
     *
     * @param time a time to work.
     * @return The number of hours in inputted time.
     */
    private static int getHour(final String time) {
        final String hours = time.substring(0, time.indexOf(':'));
        final String minutes = time.substring(time.indexOf(':') + 1);
        int result = Integer.parseInt(hours);
        if (Integer.parseInt(minutes) > 30) {
            result++;
        }
        return result;
    }
}
