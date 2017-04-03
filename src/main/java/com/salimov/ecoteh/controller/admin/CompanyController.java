package com.salimov.ecoteh.controller.admin;

import com.googlecode.htmlcompressor.compressor.Compressor;
import com.salimov.ecoteh.entity.Address;
import com.salimov.ecoteh.entity.Company;
import com.salimov.ecoteh.entity.Contacts;
import com.salimov.ecoteh.entity.File;
import com.salimov.ecoteh.enums.CompanyType;
import com.salimov.ecoteh.service.data.interfaces.CompanyService;
import com.salimov.ecoteh.service.data.interfaces.FileService;
import com.salimov.ecoteh.service.fabrica.impl.CacheMVFabricImpl;
import com.salimov.ecoteh.service.fabrica.interfaces.MainMVFabric;
import com.salimov.ecoteh.util.cache.Cache;
import com.salimov.ecoteh.util.compressor.HtmlCompressor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * The class implements a set of methods for working with
 * objects of {@link Company} class or subclasses for admins.
 * Class methods create and return modelsAndView, depending on the request.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Controller
@RequestMapping(
        value = {
                "/admin/company",
                "/admin/companies"
        }
)
@ComponentScan(basePackages = "com.salimov.ecoteh.service")
@SuppressWarnings("SpringMVCViewInspection")
public class CompanyController {

    /**
     * The implementation of the interface provides a set of standard methods
     * for creates and returns the main modelAndViews.
     */
    private final MainMVFabric fabric;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link Company} class.
     */
    private final CompanyService companyService;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link File} class.
     */
    private final FileService fileService;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param fabric         a implementation of the {@link MainMVFabric} interface.
     * @param companyService a implementation of the {@link CompanyService} interface.
     * @param fileService    a implementation of the {@link FileService} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public CompanyController(
            final MainMVFabric fabric,
            final CompanyService companyService,
            final FileService fileService
    ) {
        this.fabric = new CacheMVFabricImpl(fabric);
        this.companyService = companyService;
        this.fileService = fileService;
    }

    /**
     * Returns the page to edit the main company.
     * Request mapping: /admin/company/edit/main
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/edit/main",
            method = RequestMethod.GET
    )
    public ModelAndView editMainCompany() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("company", this.companyService.getMainCompany());
        modelAndView.addObject("main", true);
        modelAndView.setViewName("admin/company/edit");
        return modelAndView;
    }

    /**
     * Updates and save the main company and redirects
     * by URL /admin/company/main.
     * Request mapping: /admin/article/update/main
     * Method: POST
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
     * @param vkontakte     a new vkontakte URL to the main company.
     * @param facebook      a new facebook URL to the main company.
     * @param twitter       a new twitter URL to the main company.
     * @param skype         a new skype username to the main company.
     * @param address       a new address to the main company.
     * @param googleMaps    a new google maps URL to the main company.
     * @param multipartLogo a new logo to the main company.
     * @param modelAndView  a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/update/main",
            method = RequestMethod.POST
    )
    public ModelAndView updateMainCompany(
            @RequestParam(value = "title") final String title,
            @RequestParam(value = "domain") final String domain,
            @RequestParam(value = "tagline") final String tagline,
            @RequestParam(value = "text") final String description,
            @RequestParam(value = "information") final String information,
            @RequestParam(value = "keywords") final String keywords,
            @RequestParam(value = "time_from") final String workTimeFrom,
            @RequestParam(value = "time_to") final String workTimeTo,
            @RequestParam(value = "mobile_phone") final String mobilePhone,
            @RequestParam(value = "landline_phone") final String landlinePhone,
            @RequestParam(value = "fax") final String fax,
            @RequestParam(value = "email") final String email,
            @RequestParam(value = "sender_email") final String senderEmail,
            @RequestParam(value = "sender_pass") final String senderPass,
            @RequestParam(value = "vkontakte") final String vkontakte,
            @RequestParam(value = "facebook") final String facebook,
            @RequestParam(value = "twitter") final String twitter,
            @RequestParam(value = "skype") final String skype,
            @RequestParam(value = "address") final String address,
            @RequestParam(value = "google_maps") final String googleMaps,
            @RequestParam(value = "logo") final MultipartFile multipartLogo,
            final ModelAndView modelAndView
    ) {
        final Compressor compressor = new HtmlCompressor();
        final Company company = new Company(title, compressor.compress(description), keywords);
        company.setInformation(compressor.compress(information));
        company.setDomain(domain);
        company.setTagline(tagline);
        company.setSenderEmail(senderEmail);
        company.setSenderPass(senderPass);
        company.setWorkTimeFrom(workTimeFrom);
        company.setWorkTimeTo(workTimeTo);
        company.setContacts(
                new Contacts(
                        email, mobilePhone, landlinePhone, fax,
                        vkontakte, facebook, twitter, skype
                )
        );
        company.setAddress(new Address(address, googleMaps));
        if ((multipartLogo != null) && !multipartLogo.isEmpty()) {
            company.setLogo(this.fileService.add(company.getTitle(), multipartLogo));
        }
        company.setType(CompanyType.MAIN);
        this.companyService.updateMainCompany(company);
        modelAndView.setViewName("redirect:/company/main");
        Cache.clear();
        return modelAndView;
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception message:
     * "GET method in "/admin/company/update/main" is not supported!"
     * Request mapping: /admin/company/update/main
     * Method: POST
     *
     * @throws IllegalMappingException thrown when an error occurs reading
     *                                 the mapping between object and datastore.
     */
    @RequestMapping(value = "/update/main", method = RequestMethod.GET)
    public void updateMainCompany() throws IllegalMappingException {
        throw new IllegalMappingException("GET method in \"/admin/company/update/main\" is not supported!");
    }

    /**
     * Returns the page to add a new company.
     * Request mapping: /admin/company/new
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/new",
            method = RequestMethod.GET
    )
    public ModelAndView newPartnerPage() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.setViewName("admin/company/add");
        return modelAndView;
    }

    /**
     * Adds new company and redirects by URL /admin/company/{url}.
     * Request mapping: /admin/company/add
     * Method: POST
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
     * @param vkontakte     a vkontakte URL of the new company.
     * @param facebook      a facebook URL of the new company.
     * @param twitter       a twitter URL of the new company.
     * @param skype         a skype username of the new company.
     * @param address       a address of the new company.
     * @param googleMaps    a google maps URL of the new company.
     * @param multipartLogo a logo to the new company.
     * @param isValid       a validated of the new company.
     * @param modelAndView  a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/add",
            method = RequestMethod.POST
    )
    public ModelAndView addPartner(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "domain") final String domain,
            @RequestParam(value = "tagline") final String tagline,
            @RequestParam(value = "text") final String description,
            @RequestParam(value = "information") final String information,
            @RequestParam(value = "keywords") final String keywords,
            @RequestParam(value = "mobile_phone") final String mobilePhone,
            @RequestParam(value = "landline_phone") final String landlinePhone,
            @RequestParam(value = "fax") final String fax,
            @RequestParam(value = "email") final String email,
            @RequestParam(value = "vkontakte") final String vkontakte,
            @RequestParam(value = "facebook") final String facebook,
            @RequestParam(value = "twitter") final String twitter,
            @RequestParam(value = "skype") final String skype,
            @RequestParam(value = "address") final String address,
            @RequestParam(value = "google_maps") final String googleMaps,
            @RequestParam(value = "logo") final MultipartFile multipartLogo,
            @RequestParam(value = "is_valid") final boolean isValid,
            final ModelAndView modelAndView
    ) {
        final Compressor compressor = new HtmlCompressor();
        final Company company = new Company(title, compressor.compress(description), keywords);
        company.setInformation(compressor.compress(information));
        company.setTitle(title);
        company.setKeywords(keywords);
        company.setDomain(domain);
        company.setTagline(tagline);
        company.setValidated(isValid);
        company.setContacts(
                new Contacts(
                        email, mobilePhone, landlinePhone, fax,
                        vkontakte, facebook, twitter, skype
                )
        );
        company.setAddress(new Address(address, googleMaps));
        if ((multipartLogo != null) && !multipartLogo.isEmpty()) {
            company.setLogo(this.fileService.add(company.getTitle(), multipartLogo));
        }
        this.companyService.add(company);
        Cache.clear();
        modelAndView.setViewName("redirect:/company/" + company.getUrl());
        return modelAndView;
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception message:
     * "GET method in "/admin/company/add" is not supported!"
     * Request mapping: /admin/company/add
     * Method: POST
     *
     * @throws IllegalMappingException thrown when an error occurs reading
     *                                 the mapping between object and datastore.
     */
    @RequestMapping(
            value = "/add",
            method = RequestMethod.GET
    )
    public void addPartner() throws IllegalMappingException {
        throw new IllegalMappingException("GET method in \"/admin/company/add\" is not supported!");
    }

    /**
     * Returns the page to edit the company with URL.
     * Request mapping: /admin/company/edit/{url}
     * Method: GET
     *
     * @param url a URL of the company to edit.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/edit/{url}",
            method = RequestMethod.GET
    )
    public ModelAndView editPartnerByUrl(@PathVariable("url") final String url) {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("company", this.companyService.getByUrl(url, false));
        modelAndView.addObject("main", false);
        modelAndView.setViewName("admin/company/edit");
        return modelAndView;
    }

    /**
     * Updates and save the company with url and redirects
     * by URL /admin/company/{url}.
     * Request mapping: /admin/company/update
     * Method: POST
     *
     * @param url           a URL of the company to update.
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
     * @param vkontakte     a new vkontakte URL to the company.
     * @param facebook      a new facebook URL to the company.
     * @param twitter       a new twitter URL to the company.
     * @param skype         a new skype username to the company.
     * @param address       a new address to the company.
     * @param googleMaps    a new google maps URL to the company.
     * @param multipartLogo a new logo to the company.
     * @param isValid       a validated of the article.
     * @param modelAndView  a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    public ModelAndView updatePartnerCompany(
            @RequestParam(value = "url") final String url,
            @RequestParam(value = "title") final String title,
            @RequestParam(value = "domain") final String domain,
            @RequestParam(value = "tagline") final String tagline,
            @RequestParam(value = "text") final String description,
            @RequestParam(value = "information") final String information,
            @RequestParam(value = "keywords") final String keywords,
            @RequestParam(value = "mobile_phone") final String mobilePhone,
            @RequestParam(value = "landline_phone") final String landlinePhone,
            @RequestParam(value = "fax") final String fax,
            @RequestParam(value = "email") final String email,
            @RequestParam(value = "vkontakte") final String vkontakte,
            @RequestParam(value = "facebook") final String facebook,
            @RequestParam(value = "twitter") final String twitter,
            @RequestParam(value = "skype") final String skype,
            @RequestParam(value = "address") final String address,
            @RequestParam(value = "google_maps") final String googleMaps,
            @RequestParam(value = "logo") final MultipartFile multipartLogo,
            @RequestParam(value = "is_valid") final boolean isValid,
            final ModelAndView modelAndView
    ) {
        final Compressor compressor = new HtmlCompressor();
        final Company company = new Company(title, compressor.compress(description), keywords);
        company.setInformation(compressor.compress(information));
        company.setTitle(title);
        company.setKeywords(keywords);
        company.setDomain(domain);
        company.setTagline(tagline);
        company.setValidated(isValid);
        company.setContacts(
                new Contacts(
                        email, mobilePhone, landlinePhone, fax,
                        vkontakte, facebook, twitter, skype
                )
        );
        company.setAddress(new Address(address, googleMaps));
        if ((multipartLogo != null) && !multipartLogo.isEmpty()) {
            company.setLogo(this.fileService.add(company.getTitle(), multipartLogo));
        }
        this.companyService.update(url, company);
        Cache.clear();
        modelAndView.setViewName("redirect:/company/" + company.getUrl());
        return modelAndView;
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception message:
     * "GET method in "/admin/company/update" is not supported!"
     * Request mapping: /admin/company/update
     * Method: POST
     *
     * @throws IllegalMappingException thrown when an error occurs reading
     *                                 the mapping between object and datastore.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.GET
    )
    public void updatePartnerCompany() throws IllegalMappingException {
        throw new IllegalMappingException("GET method in \"/admin/company/update\" is not supported!");
    }

    /**
     * Removes company with url and redirects by URL /admin/.
     * Request mapping: /admin/company/delete/{url}
     * Method: GET
     *
     * @param url          a URL of the company to remove.
     * @param modelAndView a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/delete/{url}",
            method = RequestMethod.GET
    )
    public ModelAndView deletePartnerByUrl(
            @PathVariable("url") final String url,
            final ModelAndView modelAndView
    ) {
        this.companyService.removeByUrl(url);
        modelAndView.setViewName("redirect:/");
        Cache.clear();
        return modelAndView;
    }

    /**
     * Removes all companies and redirects by URL /admin/.
     * Request mapping: /admin/company/delete/all
     * Method: GET
     *
     * @param modelAndView a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/delete/all",
            method = RequestMethod.GET
    )
    public ModelAndView deleteAllPartners(final ModelAndView modelAndView) {
        this.companyService.removeAll();
        modelAndView.setViewName("redirect:/");
        Cache.clear();
        return modelAndView;
    }
}
