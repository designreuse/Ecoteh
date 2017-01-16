package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.dao.interfaces.CompanyDao;
import com.salimov.yurii.entity.Company;
import com.salimov.yurii.entity.File;
import com.salimov.yurii.enums.CompanyType;
import com.salimov.yurii.service.data.interfaces.CompanyService;
import com.salimov.yurii.service.data.interfaces.FileService;
import com.salimov.yurii.util.translator.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The class of the service layer, implements a set of methods for working
 * with objects of the {@link Company} class.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Company
 * @see CompanyService
 * @see ContentServiceImpl
 * @see DataServiceImpl
 * @see CompanyDao
 */
@Service
@ComponentScan(
        basePackages = {
                "com.salimov.yurii.dao",
                "com.salimov.yurii.service.data"
        }
)
public final class CompanyServiceImpl
        extends ContentServiceImpl<Company, Long>
        implements CompanyService {

    /**
     * The interface provides a set of standard methods for working
     * {@link Company} objects with the database.
     *
     * @see CompanyDao
     */
    private final CompanyDao dao;

    /**
     * The interface of the service layer,
     * describes a set of methods for working
     * with objects of the class {@link File}.
     *
     * @see FileService
     */
    private final FileService fileService;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param dao          a implementation
     *                     of the {@link CompanyDao} interface.
     * @param fileService a implementation
     *                     of the {@link FileService} interface.
     * @see CompanyDao
     * @see FileService
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public CompanyServiceImpl(
            final CompanyDao dao,
            final FileService fileService
    ) {
        super(dao);
        this.dao = dao;
        this.fileService = fileService;
    }

    /**
     * Initializes, saves and returns a new company.
     *
     * @param title         a title of the new company.
     * @param domain        a domain of the new company.
     * @param tagline       a tagline of the new company.
     * @param description   a description of the new company.
     * @param information   a information of the new company.
     * @param keywords      a keywords of the new company.
     * @param mobilePhone   a mobile phone of the new company.
     * @param landlinePhone a landline phone of the new company.
     * @param fax           a fax of the new company.
     * @param email         a e-mail of the new company.
     * @param vkontakte     a vkontakte url of the new company.
     * @param facebook      a facebook url of the new company.
     * @param twitter       a twitter url of the new company.
     * @param skype         a skype username of the new company.
     * @param address       a address of the new company.
     * @param googleMaps    a google maps url of the new company.
     * @param logoFile      a file of logo to the new company.
     * @param isValid       a validated of the new company.
     * @return The new saving company.
     * @see Company
     * @see File
     */
    @Override
    @Transactional
    public Company initAndAdd(
            final String title,
            final String domain,
            final String tagline,
            final String description,
            final String information,
            final String keywords,
            final String mobilePhone,
            final String landlinePhone,
            final String fax,
            final String email,
            final String vkontakte,
            final String facebook,
            final String twitter,
            final String skype,
            final String address,
            final String googleMaps,
            final MultipartFile logoFile,
            final boolean isValid
    ) {
        final Company company = new Company();
        company.initialize(
                title, domain,
                tagline, description, information,
                mobilePhone, landlinePhone, fax, email,
                null, null,
                vkontakte, facebook, twitter, skype,
                address, keywords, googleMaps,
                updatePhoto(
                        new File(),
                        logoFile,
                        title
                )
                , null
        );
        company.setType(
                CompanyType.PARTNER
        );
        company.setValidated(isValid);
        return add(company);
    }

    /**
     * Initializes, updates and returns object of class {@link Company}.
     * Returns {@code null} if id is {@code null}.
     *
     * @param url           a url of the company to update.
     * @param title         a new title to the company.
     * @param domain        a new domain to the company.
     * @param tagline       a new tagline to the company.
     * @param description   a new description to the company.
     * @param information   a new information to the company.
     * @param keywords      a new keywords to the company.
     * @param mobilePhone   a new mobile phone to the company.
     * @param landlinePhone a new landline phone to the company.
     * @param fax           a new fax to the company.
     * @param email         a new e-mail to the company.
     * @param vkontakte     a new vkontakte url to the company.
     * @param facebook      a new facebook url to the company.
     * @param twitter       a new twitter url to the company.
     * @param skype         a new skype username to the company.
     * @param address       a new address to the company.
     * @param googleMaps    a new google maps url to the company.
     * @param logoFile      a new file of logo to the company.
     * @param logoAction    a action on the logo.
     * @param isValid       a validated of the article.
     * @return The updating company with parameter id.
     * @see Company
     * @see File
     */
    @Override
    @Transactional
    public Company initAndUpdate(
            final String url,
            final String title,
            final String domain,
            final String tagline,
            final String description,
            final String information,
            final String keywords,
            final String mobilePhone,
            final String landlinePhone,
            final String fax,
            final String email,
            final String vkontakte,
            final String facebook,
            final String twitter,
            final String skype,
            final String address,
            final String googleMaps,
            final MultipartFile logoFile,
            final String logoAction,
            final boolean isValid
    ) {
        final Company company = getByUrl(url, false);
        final File logo = company.getLogo();
        updateLogo(
                company,
                logoFile,
                title,
                logoAction
        );
        company.initialize(
                title, domain,
                tagline, description, information,
                mobilePhone, landlinePhone, fax, email,
                null, null,
                vkontakte, facebook, twitter, skype,
                address, keywords, googleMaps
        );
        company.setValidated(isValid);
        final Company updatingCompany = update(company);
        removePhoto(
                logo, logoAction
        );
        return updatingCompany;
    }

    /**
     * Updates the main company.
     *
     * @param title         a new title to the main company.
     * @param domain        a new domain to the main company.
     * @param tagline       a new tagline to the main company.
     * @param description   a new description to the main company.
     * @param information   a new information to the main company.
     * @param keywords      a new keywords to the main company.
     * @param workTimeFrom  a new start work time to the main company.
     * @param workTimeTo    a new finish work time to the main company.
     * @param mobilePhone   a new mobile phone to the main company.
     * @param landlinePhone a new landline phone to the main company.
     * @param fax           a new fax to the main company.
     * @param email         a new e-mail to the main company.
     * @param senderEmail   a new sender e-mail to the main company.
     * @param senderPass    a new sender password to the main company.
     * @param vkontakte     a new vkontakte url to the main company.
     * @param facebook      a new facebook url to the main company.
     * @param twitter       a new twitter url to the main company.
     * @param skype         a new skype username to the main company.
     * @param address       a new address to the main company.
     * @param googleMaps    a new google maps url to the main company.
     * @param logoFile      a new file of logo to the main company.
     * @param logoAction    a action on the logo.
     * @param faviconFile   a new file of favicon to the main company.
     * @param faviconAction a action on the favicon.
     * @param slideFiles    a files of slides to the main company.
     * @param slidesAction  a new title to the main company.
     * @return The updating main company.
     */
    @Override
    @Transactional
    public Company initAndEditMainCompany(
            final String title,
            final String domain,
            final String tagline,
            final String description,
            final String information,
            final String keywords,
            final String workTimeFrom,
            final String workTimeTo,
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
            final String googleMaps,
            final MultipartFile logoFile,
            final String logoAction,
            final MultipartFile faviconFile,
            final String faviconAction,
            final MultipartFile[] slideFiles,
            final String slidesAction
    ) {
        final Company mainCompany = getMainCompany();
        final File logo = mainCompany.getLogo();
        updateLogo(
                mainCompany,
                logoFile,
                title,
                slidesAction
        );
        final File favicon = mainCompany.getFavicon();
        updateFavicon(
                mainCompany,
                faviconFile,
                title,
                faviconAction
        );
        mainCompany.initialize(
                title, domain,
                tagline, description, information,
                mobilePhone, landlinePhone, fax, email,
                senderEmail, senderPass,
                vkontakte, facebook, twitter, skype,
                address, keywords, googleMaps
        );
        mainCompany.setWorkTimeFrom(workTimeFrom);
        mainCompany.setWorkTimeTo(workTimeTo);
        final List<File> slides = new ArrayList<>(
                mainCompany.getSlides()
        );
        updateSlides(
                mainCompany,
                slideFiles,
                title,
                slidesAction
        );
        final Company updatingCompany = update(mainCompany);
        removePhoto(
                logo, logoAction
        );
        removePhoto(
                favicon, faviconAction
        );
        removeSlides(
                mainCompany,
                slides, slidesAction
        );
        return updatingCompany;
    }

    /**
     * Returns main company.
     *
     * @return The main company.
     * @throws NullPointerException Throws exception if main company is absent.
     * @see Company
     */
    @Override
    @Transactional(readOnly = true)
    public Company getMainCompany() throws NullPointerException {
        try {
            final Company mainCompany =
                    this.dao.getByType(
                            CompanyType.MAIN
                    ).get(0);
            if (!Company.isValidated(mainCompany)) {
                throw new NullPointerException(
                        "Information about main company is absent!"
                );
            }
            return mainCompany;
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
            throw new NullPointerException(
                    "Information about main company is absent!"
            );
        }
    }

    /**
     * Returns main company to home page.
     * Also loads main company sides
     *
     * @return The main company with slides.
     * @see Company
     */
    @Override
    @Transactional(readOnly = true)
    public Company getMainCompanyToHome() {
        final Company mainCompany = getMainCompany();
        mainCompany.getSlides();
        return mainCompany;
    }

    /**
     * Returns company with the parameter url.
     *
     * @param url     a url of the company to return.
     * @param isValid is get valid company or not.
     * @return The company with the parameter url.
     * @see Company
     */
    @Override
    @Transactional(readOnly = true)
    public Company getByUrl(
            final String url,
            final boolean isValid
    ) {
        final Company company = super.getByUrl(
                url, isValid
        );
        company.getSlides();
        return company;
    }

    /**
     * Returns partners companies.
     *
     * @param isValid is get valid company or not.
     * @return The partners companies.
     * @see Company
     */
    @Override
    @Transactional(readOnly = true)
    public List<Company> getPartners(final boolean isValid) {
        List<Company> companies = this.dao.getByType(
                CompanyType.PARTNER
        );
        if (isValid) {
            companies = companies.stream()
                    .filter(
                            company -> company.isValidated()
                    )
                    .collect(
                            Collectors.toList()
                    );
        }
        return companies;
    }

    /**
     * Removes company.
     * Removes company if it not {@code null} and has not type {@code MAIN}.
     *
     * @param company the company to remove.
     * @see Company
     */
    @Override
    @Transactional
    public void remove(final Company company) {
        if ((
                company != null
        ) && (
                !company.getType()
                        .equals(
                                CompanyType.MAIN
                        )
        )) {
            removeCompany(company);
        }
    }

    /**
     * Removes main company.
     *
     * @see Company
     */
    @Override
    @Transactional
    public void removeMain() {
        removeCompany(
                getMainCompany()
        );
    }

    /**
     * Removes all partners companies.
     *
     * @see Company
     */
    @Override
    @Transactional
    public void removeAll() {
        remove(
                getPartners(false)
        );
    }

    /**
     * Return Class object of {@link Company} class.
     *
     * @return The Class object of {@link Company} class.
     */
    @Override
    protected Class<Company> getModelClass() {
        return Company.class;
    }

    /**
     * Removes company. Removes company if it is not {@code null}.
     * Also removes the company logo, favicon and slides.
     *
     * @param company the company to remove.
     * @see Company
     */
    private void removeCompany(final Company company) {
        removePhoto(company);
        super.remove(company);
    }

    /**
     * Remove file in selected company.
     *
     * @param company a selected company.
     */
    private void removePhoto(final Company company) {
        this.fileService.remove(
                company.getLogo()
        );
        this.fileService.remove(
                company.getFavicon()
        );
        if (!company.getSlides().isEmpty()) {
            company.getSlides()
                    .forEach(
                            this.fileService::remove
                    );
        }
    }

    /**
     * Updates company logo.
     *
     * @param company the company to update.
     * @param file    a logo file.
     * @param title   a logo title.
     * @param action  a action on the logo.
     */
    private void updateLogo(
            final Company company,
            final MultipartFile file,
            final String title,
            final String action
    ) {
        switch (action) {
            case "replace":
                company.setLogo(
                        updatePhoto(
                                company.getLogo(),
                                file,
                                title
                        )
                );
                break;
            case "delete":
                company.setLogo(null);
                break;
        }
    }

    /**
     * Updates company favicon.
     *
     * @param company the company to update.
     * @param file    a favicon file.
     * @param title   a favicon title.
     * @param action  a action on the favicon.
     */
    private void updateFavicon(
            final Company company,
            final MultipartFile file,
            final String title,
            final String action
    ) {

        switch (action) {
            case "replace":
                final File favicon = updatePhoto(
                        company.getFavicon(),
                        file,
                        title
                );
                company.setFavicon(favicon);
                break;
            case "delete":
                company.setFavicon(null);
                break;
        }
    }

    /**
     * Updates company slides.
     *
     * @param company the company to update.
     * @param files   a slides files.
     * @param title   a slides title.
     * @param action  a action on the slides.
     */
    private void updateSlides(
            final Company company,
            final MultipartFile[] files,
            final String title,
            final String action
    ) {
        switch (action) {
            case "replace":
                if (files != null && files.length > 0) {
                    final List<File> slides = new ArrayList<>();
                    for (MultipartFile file : files) {
                        if (file != null && !file.isEmpty()) {
                            slides.add(
                                    updateSlide(
                                            new File(),
                                            file,
                                            title
                                    )
                            );
                        }
                    }
                    if (!slides.isEmpty()) {
                        company.setSlides(slides);
                    }
                }
                break;
            case "add":
                if (files != null && files.length > 0) {
                    for (MultipartFile file : files) {
                        if (file != null && !file.isEmpty()) {
                            company.addSlide(
                                    updateSlide(
                                            new File(),
                                            file,
                                            title
                                    )
                            );
                        }
                    }
                }
                break;
            case "delete":
                company.setSlides(null);
                break;
        }
    }

    /**
     * Removes file.
     *
     * @param file  the file to remove.
     * @param action a action on the file.
     */
    private void removePhoto(
            final File file,
            final String action
    ) {
        if (action.equals("delete")) {
            this.fileService.remove(file);
        }
    }

    /**
     * Removes slides.
     *
     * @param company the company to update.
     * @param slides  a slides to remove.
     * @param action  a action on the slides.
     */
    private void removeSlides(
            final Company company,
            final Collection<File> slides,
            final String action
    ) {
        if (action.equals("delete") || action.equals("replace")) {
            slides.stream()
                    .filter(
                            slide -> !company.containsSlide(slide)
                    )
                    .forEach(
                            this.fileService::remove
                    );
        }
    }

    /**
     * Updates the file.
     *
     * @param photo the file to update.
     * @param file  a file file.
     * @param title a file title.
     * @return The updating file.
     */
    private File updatePhoto(
            final File photo,
            final MultipartFile file,
            final String title
    ) {
        return updatePhotoFile(
                photo,
                file,
                Translator.fromCyrillicToLatin(title) + " file"
        );
    }

    /**
     * Updates slide.
     *
     * @param slide the slide to update.
     * @param file  a slide file.
     * @param title a slide title.
     * @return The updating slide.
     */
    private File updateSlide(
            final File slide,
            final MultipartFile file,
            final String title
    ) {
        return updatePhotoFile(
                slide,
                file,
                Translator.fromCyrillicToLatin(title) + " slide"
        );
    }

    /**
     * Updates file.
     *
     * @param photo the file to updates.
     * @param file  a file file.
     * @param title a file title.
     * @return The updating file.
     */
    private File updatePhotoFile(
            final File photo,
            final MultipartFile file,
            final String title
    ) {
        return this.fileService.updatePhoto(
                photo != null ? photo : new File(),
                file,
                title,
                "img/company"
        );
    }
}
