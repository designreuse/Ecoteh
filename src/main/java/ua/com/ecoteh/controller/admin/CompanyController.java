package ua.com.ecoteh.controller.admin;

import com.googlecode.htmlcompressor.compressor.Compressor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.entity.address.Address;
import ua.com.ecoteh.entity.address.AddressBuilder;
import ua.com.ecoteh.entity.company.Company;
import ua.com.ecoteh.entity.company.CompanyBuilder;
import ua.com.ecoteh.entity.company.CompanyType;
import ua.com.ecoteh.entity.contacts.Contacts;
import ua.com.ecoteh.entity.contacts.ContactsBuilder;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.file.FileBuilder;
import ua.com.ecoteh.exception.ExceptionMessage;
import ua.com.ecoteh.service.data.CompanyService;
import ua.com.ecoteh.service.data.FileService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;
import ua.com.ecoteh.util.cache.Cache;
import ua.com.ecoteh.util.compressor.HtmlCompressor;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of methods for working with
 * objects of the {@link Company} class or subclasses for admins.
 * Class methods create and return modelsAndView, depending on the request.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Controller
@RequestMapping(
        value = {
                "/admin/company",
                "/admin/companies"
        }
)
@ComponentScan(
        basePackages = {
                "ua.com.ecoteh.service.fabrica",
                "ua.com.ecoteh.service.data"
        }
)
@SuppressWarnings("SpringMVCViewInspection")
public final class CompanyController {

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
     * @param fabric         the implementation of the {@link MainMVFabric} interface.
     * @param companyService the implementation of the {@link CompanyService} interface.
     * @param fileService    the implementation of the {@link FileService} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public CompanyController(
            @Qualifier("cacheMVFabricImpl") final MainMVFabric fabric,
            final CompanyService companyService,
            final FileService fileService
    ) {
        this.fabric = fabric;
        this.companyService = companyService;
        this.fileService = fileService;
    }

    /**
     * Returns the page to edit the main company.
     * Request mapping: /admin/company/edit/main
     * Method: GET
     *
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = "/edit/main",
            method = RequestMethod.GET
    )
    public ModelAndView editMainCompany() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("company", this.companyService.getMainCompany());
        modelAndView.addObject("main", true);
        modelAndView.setViewName("company/edit");
        return modelAndView;
    }

    /**
     * Updates and save the main company with the incoming URL
     * and redirects by the "/company/main" URL.
     * Request mapping: /admin/company/update/main
     * Method: POST
     *
     * @param title          the new title to a main company.
     * @param domain         the new domain to a main company.
     * @param tagline        the new tagline to a main company.
     * @param description    the new description to a main company.
     * @param information    the new information to a main company.
     * @param keywords       the new keywords to a main company.
     * @param workTimeFrom   the new start work time to a main company.
     * @param workTimeTo     the new finish work time to a main company.
     * @param mobilePhone    the new mobile phone to a main company.
     * @param landlinesPhone the new landlines phone to a main company.
     * @param fax            the new fax to a main company.
     * @param email          the new E-mail to a main company.
     * @param senderEmail    the new sender e-mail to a main company.
     * @param senderPass     the new sender password to a main company.
     * @param vkontakte      the new Vkontakte URL to a main company.
     * @param facebook       the new Facebook URL to a main company.
     * @param twitter        the new Twitter URL to a main company.
     * @param skype          the new Skype username to a main company.
     * @param postAddress    the new address to a main company.
     * @param googleMaps     the new google maps URL to a main company.
     * @param multipartLogo  the new logo to a main company.
     * @return The redirect string to the "/company/main" URL.
     */
    @RequestMapping(
            value = "/update/main",
            method = RequestMethod.POST
    )
    public String updateMainCompany(
            @RequestParam(value = "title", defaultValue = "") final String title,
            @RequestParam(value = "domain", defaultValue = "") final String domain,
            @RequestParam(value = "tagline", defaultValue = "") final String tagline,
            @RequestParam(value = "description", defaultValue = "") final String description,
            @RequestParam(value = "information", defaultValue = "") final String information,
            @RequestParam(value = "keywords", defaultValue = "") final String keywords,
            @RequestParam(value = "time_from", defaultValue = "") final String workTimeFrom,
            @RequestParam(value = "time_to", defaultValue = "") final String workTimeTo,
            @RequestParam(value = "mobile_phone", defaultValue = "") final String mobilePhone,
            @RequestParam(value = "landlines_phone", defaultValue = "") final String landlinesPhone,
            @RequestParam(value = "fax", defaultValue = "") final String fax,
            @RequestParam(value = "email") final String email,
            @RequestParam(value = "sender_email", defaultValue = "") final String senderEmail,
            @RequestParam(value = "sender_pass", defaultValue = "") final String senderPass,
            @RequestParam(value = "vkontakte", defaultValue = "") final String vkontakte,
            @RequestParam(value = "facebook", defaultValue = "") final String facebook,
            @RequestParam(value = "twitter", defaultValue = "") final String twitter,
            @RequestParam(value = "skype", defaultValue = "") final String skype,
            @RequestParam(value = "post_address", defaultValue = "") final String postAddress,
            @RequestParam(value = "google_maps", defaultValue = "") final String googleMaps,
            @RequestParam(value = "logo") final MultipartFile multipartLogo
    ) {
        final Compressor compressor = new HtmlCompressor();
        final CompanyBuilder companyBuilder = Company.getBuilder();
        companyBuilder.addType(CompanyType.MAIN).addTitle(title).addDomain(domain).addTagline(tagline)
                .addKeywords(keywords).addWorkTimeFrom(workTimeFrom).addWorkTimeTo(workTimeTo)
                .addSenderEmail(senderEmail).addSenderPass(senderPass)
                .addDescription(compressor.compress(description))
                .addInformation(compressor.compress(information));

        final ContactsBuilder contactsBuilder = Contacts.getBuilder();
        contactsBuilder.addEmail(email).addMobilePhone(mobilePhone)
                .addLandlinesPhone(landlinesPhone).addFax(fax)
                .addVkontakte(vkontakte).addFacebook(facebook)
                .addTwitter(twitter).addSkype(skype);
        final Contacts contacts = contactsBuilder.build();
        companyBuilder.addContacts(contacts);

        final AddressBuilder addressBuilder = Address.getBuilder();
        addressBuilder.addPostAddress(postAddress).addGoogleMaps(googleMaps);
        final Address address = addressBuilder.build();
        companyBuilder.addAddress(address);

        final FileBuilder fileBuilder = File.getBuilder();
        fileBuilder.addTitle(title).addMultipartFile(multipartLogo).isValid();
        final File logo = fileBuilder.build();
        companyBuilder.addLogo(logo);

        final Company updatedMainCompany = companyBuilder.build();
        this.companyService.updateMainCompany(updatedMainCompany);
        Cache.clear();
        return "redirect:/company/main";
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
        final String message = String.format(
                ExceptionMessage.GET_METHOD_NOT_SUPPORTED_MESSAGE,
                "/admin/company/update/main"
        );
        throw new IllegalMappingException(message);
    }

    /**
     * Returns the page to add a new company.
     * Request mapping: /admin/company/new
     * Method: GET
     *
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = "/new",
            method = RequestMethod.GET
    )
    public ModelAndView newPartnerPage() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.setViewName("company/add");
        return modelAndView;
    }

    /**
     * Adds a new company with the incoming parameters
     * and redirects by the "/company/{url}" URL,
     * where {url} is a URL of a saving company.
     * Request mapping: /admin/company/add
     * Method: POST
     *
     * @param title          the title of a new company.
     * @param domain         the domain of a new company.
     * @param tagline        the tagline of a new company.
     * @param description    the description of a new company.
     * @param information    the information of a new company.
     * @param keywords       the keywords of a new company.
     * @param mobilePhone    the mobile phone of a new company.
     * @param landlinesPhone the landline phone of a new company.
     * @param fax            the fax of a new company.
     * @param email          the e-mail of a new company.
     * @param vkontakte      the vkontakte URL of a new company.
     * @param facebook       the facebook URL of a new company.
     * @param twitter        the twitter URL of a new company.
     * @param skype          the skype username of a new company.
     * @param postAddress    the post address of a new company.
     * @param googleMaps     the google maps URL of a new company.
     * @param multipartLogo  the logo to a new company.
     * @param validated      the validated of a new company.
     * @return The redirect string to the "/company/{url}" URL,
     * where {url} is a URL of a saving company.
     */
    @RequestMapping(
            value = "/add",
            method = RequestMethod.POST
    )
    public String addPartner(
            @RequestParam(value = "title", defaultValue = "") String title,
            @RequestParam(value = "domain", defaultValue = "") final String domain,
            @RequestParam(value = "tagline", defaultValue = "") final String tagline,
            @RequestParam(value = "description", defaultValue = "") final String description,
            @RequestParam(value = "information", defaultValue = "") final String information,
            @RequestParam(value = "keywords", defaultValue = "") final String keywords,
            @RequestParam(value = "mobile_phone", defaultValue = "") final String mobilePhone,
            @RequestParam(value = "landlines_phone", defaultValue = "") final String landlinesPhone,
            @RequestParam(value = "fax", defaultValue = "") final String fax,
            @RequestParam(value = "email", defaultValue = "") final String email,
            @RequestParam(value = "vkontakte", defaultValue = "") final String vkontakte,
            @RequestParam(value = "facebook", defaultValue = "") final String facebook,
            @RequestParam(value = "twitter", defaultValue = "") final String twitter,
            @RequestParam(value = "skype", defaultValue = "") final String skype,
            @RequestParam(value = "post_address", defaultValue = "") final String postAddress,
            @RequestParam(value = "google_maps", defaultValue = "") final String googleMaps,
            @RequestParam(value = "logo") final MultipartFile multipartLogo,
            @RequestParam(value = "is_valid", defaultValue = "false") final boolean validated
    ) {
        final Compressor compressor = new HtmlCompressor();

        final CompanyBuilder companyBuilder = Company.getBuilder();
        companyBuilder.addTitle(title).addDomain(domain).addTagline(tagline)
                .addKeywords(keywords).addValidated(validated)
                .addDescription(compressor.compress(description))
                .addInformation(compressor.compress(information))
                .addType(CompanyType.PARTNER);

        final AddressBuilder addressBuilder = Address.getBuilder();
        addressBuilder.addPostAddress(postAddress).addGoogleMaps(googleMaps);
        final Address address = addressBuilder.build();
        companyBuilder.addAddress(address);

        final ContactsBuilder contactsBuilder = Contacts.getBuilder();
        contactsBuilder.addEmail(email).addMobilePhone(mobilePhone)
                .addLandlinesPhone(landlinesPhone).addFax(fax)
                .addVkontakte(vkontakte).addFacebook(facebook)
                .addTwitter(twitter).addSkype(skype);
        final Contacts contacts = contactsBuilder.build();
        companyBuilder.addContacts(contacts);

        final FileBuilder fileBuilder = File.getBuilder();
        fileBuilder.addTitle(title).addMultipartFile(multipartLogo).isValid();
        final File logo = fileBuilder.build();
        companyBuilder.addLogo(logo);

        final Company company = companyBuilder.build();
        final Company savingCompany = this.companyService.add(company);
        Cache.clear();
        return getViewName(savingCompany);
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
        final String message = String.format(
                ExceptionMessage.GET_METHOD_NOT_SUPPORTED_MESSAGE,
                "/admin/company/add"
        );
        throw new IllegalMappingException(message);
    }

    /**
     * Returns the page to edit the company with URL.
     * Request mapping: /admin/company/edit/{url}
     * Method: GET
     *
     * @param url a URL of the company to edit.
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = "/edit/{url}",
            method = RequestMethod.GET
    )
    public ModelAndView editPartnerByUrl(@PathVariable("url") final String url) {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("company", this.companyService.getByUrl(url, false));
        modelAndView.addObject("main", false);
        modelAndView.setViewName("company/edit");
        return modelAndView;
    }

    /**
     * Updates and save a company with the incoming URL
     * and redirects by the "/company/{url}" URL,
     * where {url} is a URL of a saving company.
     * Request mapping: /admin/company/update
     * Method: POST
     *
     * @param url            the URL of a company to update.
     * @param title          the new title to a company.
     * @param domain         the new domain to a company.
     * @param tagline        the new tagline to a company.
     * @param description    the new description to a company.
     * @param information    the new information to a company.
     * @param keywords       the new keywords to a company.
     * @param mobilePhone    the new mobile phone to a company.
     * @param landlinesPhone the new landline phone to a company.
     * @param fax            the new fax to a company.
     * @param email          the new e-mail to a company.
     * @param vkontakte      the new vkontakte URL to a company.
     * @param facebook       the new facebook URL to a company.
     * @param twitter        the new twitter URL to a company.
     * @param skype          the new skype username to a company.
     * @param postAddress    the new address to a company.
     * @param googleMaps     the new google maps URL to a company.
     * @param multipartLogo  the new logo to a company.
     * @param validated      the validated of a article.
     * @return The redirect string to the "/company/{url}" URL,
     * where {url} is a URL of a saving company.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    public String updatePartnerCompany(
            @RequestParam(value = "url", defaultValue = "") final String url,
            @RequestParam(value = "title", defaultValue = "") final String title,
            @RequestParam(value = "domain", defaultValue = "") final String domain,
            @RequestParam(value = "tagline", defaultValue = "") final String tagline,
            @RequestParam(value = "description", defaultValue = "") final String description,
            @RequestParam(value = "information", defaultValue = "") final String information,
            @RequestParam(value = "keywords", defaultValue = "") final String keywords,
            @RequestParam(value = "mobile_phone", defaultValue = "") final String mobilePhone,
            @RequestParam(value = "landlines_phone", defaultValue = "") final String landlinesPhone,
            @RequestParam(value = "fax", defaultValue = "") final String fax,
            @RequestParam(value = "email", defaultValue = "") final String email,
            @RequestParam(value = "vkontakte", defaultValue = "") final String vkontakte,
            @RequestParam(value = "facebook", defaultValue = "") final String facebook,
            @RequestParam(value = "twitter", defaultValue = "") final String twitter,
            @RequestParam(value = "skype", defaultValue = "") final String skype,
            @RequestParam(value = "post_address", defaultValue = "") final String postAddress,
            @RequestParam(value = "google_maps", defaultValue = "") final String googleMaps,
            @RequestParam(value = "logo") final MultipartFile multipartLogo,
            @RequestParam(value = "is_valid", defaultValue = "false") final boolean validated
    ) {
        final Compressor compressor = new HtmlCompressor();
        final CompanyBuilder companyBuilder = Company.getBuilder();
        companyBuilder.addUrl(url).addTitle(title).addDomain(domain)
                .addTagline(tagline).addKeywords(keywords).addValidated(validated)
                .addDescription(compressor.compress(description))
                .addInformation(compressor.compress(information));

        final ContactsBuilder contactsBuilder = Contacts.getBuilder();
        contactsBuilder.addEmail(email).addMobilePhone(mobilePhone)
                .addLandlinesPhone(landlinesPhone).addFax(fax)
                .addVkontakte(vkontakte).addFacebook(facebook)
                .addTwitter(twitter).addSkype(skype);
        final Contacts contacts = contactsBuilder.build();
        companyBuilder.addContacts(contacts);

        final AddressBuilder addressBuilder = Address.getBuilder();
        addressBuilder.addPostAddress(postAddress).addGoogleMaps(googleMaps);
        final Address address = addressBuilder.build();
        companyBuilder.addAddress(address);

        final FileBuilder fileBuilder = File.getBuilder();
        fileBuilder.addTitle(title).addMultipartFile(multipartLogo).isValid();
        final File logo = fileBuilder.build();
        companyBuilder.addLogo(logo);

        final Company company = companyBuilder.build();
        final Company updatedCompany = this.companyService.update(company);
        Cache.clear();
        return getViewName(updatedCompany);
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
        final String message = String.format(
                ExceptionMessage.GET_METHOD_NOT_SUPPORTED_MESSAGE,
                "/admin/company/update"
        );
        throw new IllegalMappingException(message);
    }

    /**
     * Removes an company with the incoming URL
     * and redirects by the "/" URL.
     * Request mapping: /admin/company/delete/{url},
     * where {url} is a URL of an company to remove.
     * Method: GET
     *
     * @param url the URL of a company to remove.
     * @return The redirect string to the "/" URL.
     */
    @RequestMapping(
            value = "/delete/{url}",
            method = RequestMethod.GET
    )
    public String deletePartnerByUrl(@PathVariable("url") final String url) {
        this.companyService.removeByUrl(url);
        Cache.clear();
        return "redirect:/";
    }

    /**
     * Removes an all companies and redirects by the "/" URL.
     * Request mapping: /admin/company/delete/all
     * Method: GET
     *
     * @return The redirect string to the "/" URL.
     */
    @RequestMapping(
            value = "/delete/all",
            method = RequestMethod.GET
    )
    public String deleteAllPartners() {
        this.companyService.removeAll();
        Cache.clear();
        return "redirect:/";
    }

    /**
     * Returns a view name for the company.
     *
     * @param company the company to get view name.
     * @return The view name.
     */
    private String getViewName(final Company company) {
        final String viewName;
        if (isNotNull(company)) {
            viewName = "redirect:/company/" + company.getUrl();
        } else {
            viewName = "redirect:/company/all";
        }
        return viewName;
    }
}
