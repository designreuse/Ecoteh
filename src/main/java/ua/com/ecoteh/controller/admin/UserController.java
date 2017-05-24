package ua.com.ecoteh.controller.admin;

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
import ua.com.ecoteh.entity.Company;
import ua.com.ecoteh.entity.Contacts;
import ua.com.ecoteh.entity.File;
import ua.com.ecoteh.entity.User;
import ua.com.ecoteh.enums.UserRole;
import ua.com.ecoteh.exception.ExceptionMessage;
import ua.com.ecoteh.service.data.CompanyService;
import ua.com.ecoteh.service.data.FileService;
import ua.com.ecoteh.service.data.UserService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;
import ua.com.ecoteh.service.sender.SenderService;
import ua.com.ecoteh.util.cache.Cache;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of methods for working
 * with main ModelAndView objects and object of the {@link User}
 * class for admins. Class methods create and return modelsAndView,
 * depending on the request. For the work used implementation
 * of the interface {@link MainMVFabric}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Controller
@RequestMapping(
        value = {
                "/admin/user",
                "/admin/users"
        }
)
@ComponentScan(
        basePackages = {
                "ua.com.ecoteh.service.fabrica",
                "ua.com.ecoteh.service.data"
        }
)
@SuppressWarnings("SpringMVCViewInspection")
public class UserController {

    /**
     * The implementation of the interface provides a set of standard
     * methods for creates and returns the main modelAndViews for admins.
     */
    private final MainMVFabric fabric;
    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link User} class.
     */
    private final UserService userService;

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
     * The implementation of the interface describes a set of methods
     * for working with E-mail.
     */
    private final SenderService senderService;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param fabric         the implementation of the {@link MainMVFabric} interface.
     * @param userService    the implementation of the {@link UserService} interface.
     * @param companyService the implementation of the {@link CompanyService} interface.
     * @param fileService    the implementation of the {@link FileService} interface.
     * @param senderService  the implementation of the {@link SenderService} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public UserController(
            @Qualifier("cacheMVFabricImpl") final MainMVFabric fabric,
            final UserService userService,
            final CompanyService companyService,
            final FileService fileService,
            final SenderService senderService
    ) {
        this.fabric = fabric;
        this.userService = userService;
        this.companyService = companyService;
        this.fileService = fileService;
        this.senderService = senderService;
    }

    /**
     * Returns a page with an all users.
     * Request mapping: /admin/user/all
     * Method: GET
     *
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = { "", "/all" },
            method = RequestMethod.GET
    )
    public ModelAndView getAllUsersPage() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("users_list", this.userService.getPersonnel());
        modelAndView.addObject("is_captcha", null);
        modelAndView.setViewName("user/all");
        return modelAndView;
    }

    /**
     * Returns the page to add a new user.
     * Request mapping: /admin/user/new
     * Method: GET
     *
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = "/new",
            method = RequestMethod.GET
    )
    public ModelAndView newUserPage() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.setViewName("user/add");
        return modelAndView;
    }

    /**
     * Adds a new user with the incoming parameters
     * and redirects by the "/admin/user/all" URL.
     * Request mapping: /admin/user/add
     * Method: POST
     *
     * @param name           the name of a new user.
     * @param login          the login of a new user.
     * @param password       the password of a new user.
     * @param description    the description of a new user.
     * @param mobilePhone    the mobile phone of a new user.
     * @param landlinePhone  the landline phone of a new user.
     * @param fax            the fax of a new user.
     * @param email          the e-mail of a new user.
     * @param vkontakte      the vkontakte URL of a new user.
     * @param facebook       the facebook URL of a new user.
     * @param twitter        the twitter URL of a new user.
     * @param skype          the skype username of a new user.
     * @param multipartPhoto the photo to a new user.
     * @param validated      the validated of a new user.
     * @param isMailing      the permit to send a letters to a user email.
     * @param locked         the locked a user or not.
     * @return The redirect string to the "/admin/user/all" URL.
     */
    @RequestMapping(
            value = "/add",
            method = RequestMethod.POST
    )
    public String addUser(
            @RequestParam(value = "name") final String name,
            @RequestParam(value = "login") final String login,
            @RequestParam(value = "password") final String password,
            @RequestParam(value = "description") final String description,
            @RequestParam(value = "mobile_phone") final String mobilePhone,
            @RequestParam(value = "landline_phone") final String landlinePhone,
            @RequestParam(value = "fax") final String fax,
            @RequestParam(value = "email") final String email,
            @RequestParam(value = "vkontakte") final String vkontakte,
            @RequestParam(value = "facebook") final String facebook,
            @RequestParam(value = "twitter") final String twitter,
            @RequestParam(value = "skype") final String skype,
            @RequestParam(value = "photo") final MultipartFile multipartPhoto,
            @RequestParam(value = "is_valid") final boolean validated,
            @RequestParam(value = "is_mailing") final boolean isMailing,
            @RequestParam(value = "is_locked") final boolean locked
    ) {
        final User user = new User(
                name, description,
                new Contacts(
                        email, mobilePhone, landlinePhone, fax,
                        vkontakte, facebook, twitter, skype
                )
        );
        user.setLogin(login);
        user.setPassword(password);
        user.setValidated(validated);
        user.setMailing(isMailing);
        user.setLocked(locked);
        user.setRole(UserRole.ADMIN);
        if (isNotEmpty(multipartPhoto)) {
            user.setPhoto(this.fileService.add(user.getName(), multipartPhoto));
        }
        this.userService.add(user);
        Cache.removeAll("Main Company");
        return "redirect:/admin/user/all";
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception sender:
     * "GET method in "/admin/user/add" is not supported!"
     * Request mapping: /admin/user/add
     * Method: POST
     *
     * @throws IllegalMappingException thrown when an error occurs reading
     *                                 the mapping between object and datastore.
     */
    @RequestMapping(
            value = "/add",
            method = RequestMethod.GET
    )
    public void addUser() throws IllegalMappingException {
        throw new IllegalMappingException(
                String.format(
                        ExceptionMessage.GET_METHOD_NOT_SUPPORTED_MESSAGE,
                        "/admin/user/add"
                )
        );
    }

    /**
     * Returns a page to edit a user with the incoming URL.
     * Request mapping: /admin/user/edit/{url},
     * where {url} is a URL of a user to edit.
     * Method: GET
     *
     * @param url the URL of a user to edit.
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = "/edit/{url}",
            method = RequestMethod.GET
    )
    public ModelAndView editUserByUrl(@PathVariable("url") final String url) {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("user", this.userService.getByUrl(url));
        modelAndView.setViewName("user/edit");
        return modelAndView;
    }

    /**
     * Updates and save a user with the incoming URL
     * and redirects by the "/admin/user/all" URL.
     * Request mapping: /admin/user/update
     * Method: POST
     *
     * @param url            the URL of a user to update.
     * @param name           the new name to a user.
     * @param login          the new login to a user.
     * @param password       the new password to a user.
     * @param description    the new description to a user.
     * @param mobilePhone    the new mobile phone to a user.
     * @param landlinePhone  the new landline phone to a user.
     * @param fax            the new fax to a user.
     * @param email          the new E-mail to a user.
     * @param vkontakte      the new Vkontakte URL to a user.
     * @param facebook       the new Facebook URL to a user.
     * @param twitter        the new Twitter URL to a user.
     * @param skype          the new Skype username to a user.
     * @param multipartPhoto the photo to a user.
     * @param isValid        the validated of a user.
     * @param isMailing      the permit to send a letters to a user E-mail.
     * @param isLocked       the locked a user or not.
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    public String updateUser(
            @RequestParam(value = "url") final String url,
            @RequestParam(value = "name", defaultValue = "") final String name,
            @RequestParam(value = "login", defaultValue = "") final String login,
            @RequestParam(value = "password", defaultValue = "") final String password,
            @RequestParam(value = "description", defaultValue = "") final String description,
            @RequestParam(value = "mobile_phone", defaultValue = "") final String mobilePhone,
            @RequestParam(value = "landline_phone", defaultValue = "") final String landlinePhone,
            @RequestParam(value = "fax", defaultValue = "") final String fax,
            @RequestParam(value = "email", defaultValue = "") final String email,
            @RequestParam(value = "vkontakte", defaultValue = "") final String vkontakte,
            @RequestParam(value = "facebook", defaultValue = "") final String facebook,
            @RequestParam(value = "twitter", defaultValue = "") final String twitter,
            @RequestParam(value = "skype", defaultValue = "") final String skype,
            @RequestParam(value = "photo", defaultValue = "") final MultipartFile multipartPhoto,
            @RequestParam(value = "is_valid", defaultValue = "false") final boolean isValid,
            @RequestParam(value = "is_mailing", defaultValue = "false") final boolean isMailing,
            @RequestParam(value = "is_locked", defaultValue = "false") final boolean isLocked
    ) {
        final User user = new User(
                name, description,
                new Contacts(
                        email, mobilePhone, landlinePhone, fax,
                        vkontakte, facebook, twitter, skype
                )
        );
        user.setLogin(login);
        user.setPassword(password);
        user.setValidated(isValid);
        user.setMailing(isMailing);
        user.setLocked(isLocked);
        user.setRole(UserRole.ADMIN);
        if (isNotEmpty(multipartPhoto)) {
            user.setPhoto(this.fileService.add(user.getName(), multipartPhoto));
        }
        this.userService.update(url, user);
        Cache.removeAll("Main Company");
        return "redirect:/admin/user/all";
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception sender:
     * "GET method in "/admin/article/update" is not supported!"
     * Request mapping: /admin/article/update
     * Method: POST
     *
     * @throws IllegalMappingException thrown when an error occurs reading
     *                                 the mapping between object and datastore.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.GET
    )
    public void updateUser() throws IllegalMappingException {
        throw new IllegalMappingException(
                String.format(
                        ExceptionMessage.GET_METHOD_NOT_SUPPORTED_MESSAGE,
                        "/admin/user/update"
                )
        );
    }

    /**
     * Removes a user with the incoming URL
     * and redirects by the "/admin/user/all" URL.
     * Request mapping: /admin/user/delete/{url},
     * where {url} is a URL of a user to remove.
     * Method: GET
     *
     * @param url the URL of a user to remove.
     * @return The redirect string to the "/admin/user/all" URL.
     */
    @RequestMapping(
            value = "/delete/{url}",
            method = RequestMethod.GET
    )
    public String deleteUserByUrl(@PathVariable("url") final String url) {
        this.userService.removeByUrl(url);
        Cache.removeAll("Main Company");
        return "redirect:/admin/user/all";
    }

    /**
     * Removes an all users and redirects
     * by the "/admin/user/all" URL.
     * Request mapping: /admin/article/user/all
     * Method: GET
     *
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = "/delete/all",
            method = RequestMethod.GET
    )
    public String deleteAllUsers() {
        this.userService.removeAll();
        Cache.removeAll("Main Company");
        return "redirect:/admin/user/all";
    }

    /**
     * Creates a message with the incoming parameters and
     * sends it to personnel. Redirects by URL /admin/user/all.
     * Request mapping: /admin/user/send_message
     * Method: POST
     *
     * @param subject the subject of a message.
     * @param text    the text of a message.
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = "/send_message",
            method = RequestMethod.POST
    )
    public ModelAndView sendMessage(
            @RequestParam(value = "subject", defaultValue = "") final String subject,
            @RequestParam(value = "sender", defaultValue = "") final String text
    ) {
        new Thread(() -> {
            String _subject = subject;
            String message = text;
            final User user = this.userService.getAuthenticatedUser();
            if (isNotNull(user)) {
                if (isNotEmpty(user.getName())) {
                    _subject += " - " + user.getName();
                    message += "\n\n" + user.getName();
                }
                if (isNotNull(user.getContacts())) {
                    if (isNotEmpty(user.getContacts().getMobilePhone())) {
                        message += "\nMobile Phone: " + user.getContacts().getMobilePhone();
                    }
                    if (isNotEmpty(user.getContacts().getLandlinePhone())) {
                        message += "\nLandline Phone: " + user.getContacts().getLandlinePhone();
                    }
                    if (isNotEmpty(user.getContacts().getEmail())) {
                        message += "\nE-mail: " + user.getContacts().getEmail();
                    }
                }
            }
            final Company mainCompany = this.companyService.getMainCompany();
            this.senderService.send(
                    _subject + " | " + mainCompany.getTitle(), message,
                    this.userService.getPersonnel(),
                    mainCompany.getSenderEmail(), mainCompany.getSenderPass()
            );
        }).start();
        final ModelAndView modelAndView = getAllUsersPage();
        modelAndView.addObject("is_captcha", true);
        return modelAndView;
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception sender:
     * "GET method in "/admin/user/send_message" is not supported!"
     * Request mapping: /admin/user/send_message
     * Method: POST
     *
     * @throws IllegalMappingException thrown when an error occurs reading
     *                                 the mapping between object and datastore.
     */
    @RequestMapping(
            value = "/send_message",
            method = RequestMethod.GET
    )
    public void sendMessageForPersonnel() throws IllegalMappingException {
        throw new IllegalMappingException(
                String.format(
                        ExceptionMessage.GET_METHOD_NOT_SUPPORTED_MESSAGE,
                        "/admin/user/send_message"
                )
        );
    }
}
