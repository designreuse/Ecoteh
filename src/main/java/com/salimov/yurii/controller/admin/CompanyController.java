package com.salimov.yurii.controller.admin;

import com.salimov.yurii.entity.Company;
import com.salimov.yurii.entity.File;
import com.salimov.yurii.service.data.interfaces.CompanyService;
import com.salimov.yurii.service.data.interfaces.FileService;
import com.salimov.yurii.service.fabrica.impl.CacheMVFabricImpl;
import com.salimov.yurii.service.fabrica.interfaces.MainMVFabric;
import com.salimov.yurii.util.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * The class implements a set of methods for working
 * with main ModelAndView objects and object of {@link Company}
 * class or subclasses for admins. Class methods create
 * and return modelsAndView, depending on the request.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see Company
 * @see CompanyService
 * @see FileService
 * @see MainMVFabric
 */
@Controller
@RequestMapping(value = "/admin/company")
@ComponentScan(basePackages = "com.salimov.yurii.service")
@SuppressWarnings("SpringMVCViewInspection")
public class CompanyController {

    /**
     * The implementation of the interface provides a set of standard methods
     * for creates and returns the main modelAndViews.
     *
     * @see MainMVFabric
     */
    private final MainMVFabric fabric;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link Company} class.
     *
     * @see CompanyService
     */
    private final CompanyService companyService;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param fabric         a implementation of the {@link MainMVFabric} interface.
     * @param companyService a implementation of the {@link CompanyService} interface.
     * @see MainMVFabric
     * @see CompanyService
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public CompanyController(
            final MainMVFabric fabric,
            final CompanyService companyService
    ) {
        this.fabric = new CacheMVFabricImpl(fabric);
        this.companyService = companyService;
    }

    /**
     * Returns the page to edit the main company.
     * Request mapping: /admin/company/edit/main
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     * @see Company
     */
    @RequestMapping(
            value = "/edit/main",
            method = RequestMethod.GET
    )
    public ModelAndView editMainCompany() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        final Company company = this.companyService.getMainCompany();
        modelAndView.addObject("company", company);
        modelAndView.addObject("slides", company.getSlides());
        modelAndView.addObject("main", true);
        modelAndView.setViewName("admin/company/edit_page");
        return modelAndView;
    }

    /**
     * Updates and save the main company and redirects
     * by url /admin/company/main.
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
     * @param vkontakte     a new vkontakte url to the main company.
     * @param facebook      a new facebook url to the main company.
     * @param twitter       a new twitter url to the main company.
     * @param skype         a new skype username to the main company.
     * @param address       a new address to the main company.
     * @param googleMaps    a new google maps url to the main company.
     * @param logoUrl       a new logo Url to the main company.
     * @param faviconUrl    a new favicon Url to the main company.
     * @param slides        a files of slides to the main company.
     * @param modelAndView  a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     * @see Company
     * @see File
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
            @RequestParam(value = "logo") final String logoUrl,
            @RequestParam(value = "favicon") final String faviconUrl,
            @RequestParam(value = "slides") final String slides,
            final ModelAndView modelAndView
    ) {
        this.companyService.initAndEditMainCompany(
                title, domain,
                tagline, description, information, keywords,
                workTimeFrom, workTimeTo,
                mobilePhone, landlinePhone, fax, email,
                senderEmail, senderPass,
                vkontakte, facebook, twitter,
                skype, address, googleMaps,
                logoUrl, faviconUrl, slides
        );
        modelAndView.setViewName("redirect:/company/main");
        Cache.clear();
        return modelAndView;
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception sender:
     * "GET method in "/admin/company/update/main" is not supported!"
     * Request mapping: /admin/company/update/main
     * Method: POST
     *
     * @throws IllegalMappingException thrown when an error occurs reading
     *                                 the mapping between object and datastore.
     */
    @RequestMapping(value = "/update/main", method = RequestMethod.GET)
    public void updateMainCompany() throws IllegalMappingException {
        throw new IllegalMappingException(
                "GET method in \"/admin/company/update/main\" is not supported!"
        );
    }

    /**
     * Returns the page to add a new company.
     * Request mapping: /admin/company/new
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     * @see Company
     */
    @RequestMapping(
            value = "/new",
            method = RequestMethod.GET
    )
    public ModelAndView newPartnerPage() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.setViewName("admin/company/new_page");
        return modelAndView;
    }

    /**
     * Adds new company and redirects by url /admin/company/{url}.
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
     * @param vkontakte     a vkontakte url of the new company.
     * @param facebook      a facebook url of the new company.
     * @param twitter       a twitter url of the new company.
     * @param skype         a skype username of the new company.
     * @param address       a address of the new company.
     * @param googleMaps    a google maps url of the new company.
     * @param logoUrl       a  logo Url to the new company.
     * @param isValid       a validated of the new company.
     * @param modelAndView  a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     * @see Company
     * @see File
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
            @RequestParam(value = "logo") final String logoUrl,
            @RequestParam(value = "is_valid") final boolean isValid,
            final ModelAndView modelAndView
    ) {
        final Company company = this.companyService.initAndAdd(
                title, domain,
                tagline, description, information, keywords,
                mobilePhone, landlinePhone, fax, email,
                vkontakte, facebook, twitter, skype,
                address, googleMaps,
                logoUrl, isValid
        );
        Cache.clear();
        modelAndView.setViewName("redirect:/company/" + company.getUrl());
        return modelAndView;
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception sender:
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
        throw new IllegalMappingException(
                "GET method in \"/admin/company/add\" is not supported!"
        );
    }

    /**
     * Returns the page to edit the company with url.
     * Request mapping: /admin/company/edit/{url}
     * Method: GET
     *
     * @param url a url of the company to edit.
     * @return The ready object of class ModelAndView.
     * @see Company
     */
    @RequestMapping(
            value = "/edit/{url}",
            method = RequestMethod.GET
    )
    public ModelAndView editPartnerByUrl(@PathVariable("url") final String url) {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("company", this.companyService.getByUrl(url, false));
        modelAndView.addObject("main", false);
        modelAndView.setViewName("admin/company/edit_page");
        return modelAndView;
    }

    /**
     * Updates and save the company with url and redirects
     * by url /admin/company/{url}.
     * Request mapping: /admin/company/update
     * Method: POST
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
     * @param logoUrl       a new logo Url to the company.
     * @param isValid       a validated of the article.
     * @param modelAndView  a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     * @see Company
     * @see File
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
            @RequestParam(value = "logo") final String logoUrl,
            @RequestParam(value = "is_valid") final boolean isValid,
            final ModelAndView modelAndView
    ) {
        final Company company = this.companyService.initAndUpdate(
                url, title, domain,
                tagline, description, information, keywords,
                mobilePhone, landlinePhone, fax, email,
                vkontakte, facebook, twitter, skype,
                address, googleMaps,
                logoUrl, isValid
        );
        Cache.clear();
        modelAndView.setViewName("redirect:/company/" + company.getUrl());
        return modelAndView;
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception sender:
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
        throw new IllegalMappingException(
                "GET method in \"/admin/company/update\" is not supported!"
        );
    }

    /**
     * Removes company with url and redirects by url /admin/.
     * Request mapping: /admin/company/delete/{url}
     * Method: GET
     *
     * @param url          a url of the company to remove.
     * @param modelAndView a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     * @see Company
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
     * Removes all companies and redirects by url /admin/.
     * Request mapping: /admin/company/delete/all
     * Method: GET
     *
     * @param modelAndView a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     * @see Company
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
