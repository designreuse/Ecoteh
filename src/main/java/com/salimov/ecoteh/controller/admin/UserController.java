package com.salimov.ecoteh.controller.admin;

import com.salimov.ecoteh.entity.Company;
import com.salimov.ecoteh.entity.Contacts;
import com.salimov.ecoteh.entity.File;
import com.salimov.ecoteh.entity.User;
import com.salimov.ecoteh.enums.UserRole;
import com.salimov.ecoteh.service.data.CompanyService;
import com.salimov.ecoteh.service.data.FileService;
import com.salimov.ecoteh.service.data.UserService;
import com.salimov.ecoteh.service.fabrica.CacheMVFabricImpl;
import com.salimov.ecoteh.service.fabrica.CacheMVFabric;
import com.salimov.ecoteh.service.fabrica.MainMVFabric;
import com.salimov.ecoteh.service.sender.SenderService;
import com.salimov.ecoteh.util.cache.Cache;
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

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class implements a set of methods for working
 * with main ModelAndView objects and object of the {@link User}
 * class for admins. Class methods create and return modelsAndView,
 * depending on the request. For the work used implementation
 * of the interface {@link MainMVFabric}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
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
                "com.salimov.ecoteh.service.fabrica",
                "com.salimov.ecoteh.service.data"
        }
)
@SuppressWarnings("SpringMVCViewInspection")
public class UserController {

    /**
     * The implementation of the interface provides a set of standard
     * methods for creates and returns the main modelAndViews for admins.
     */
    private final CacheMVFabric fabric;
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
     * @param fabric         a implementation of the {@link MainMVFabric} interface.
     * @param userService    a implementation of the {@link UserService} interface.
     * @param companyService a implementation of the {@link CompanyService} interface.
     * @param fileService    a implementation of the {@link FileService} interface.
     * @param senderService  a implementation of the {@link SenderService} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public UserController(
            final MainMVFabric fabric,
            final UserService userService,
            final CompanyService companyService,
            final FileService fileService,
            final SenderService senderService
    ) {
        this.fabric = new CacheMVFabricImpl(fabric);
        this.userService = userService;
        this.companyService = companyService;
        this.fileService = fileService;
        this.senderService = senderService;
    }

    /**
     * Returns the page with all users.
     * Request mapping: /admin/user/all
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = {"", "/all"},
            method = RequestMethod.GET
    )
    public ModelAndView getAllUsersPage() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("users_list", this.userService.getPersonnel());
        modelAndView.addObject("is_captcha", null);
        modelAndView.setViewName("admin/user/all");
        return modelAndView;
    }

    /**
     * Returns the page to add a new user.
     * Request mapping: /admin/user/new
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/new",
            method = RequestMethod.GET
    )
    public ModelAndView newUserPage() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.setViewName("admin/user/add");
        return modelAndView;
    }

    /**
     * Adds new user and redirects by URL /admin/user/{url}.
     * Request mapping: /admin/user/add
     * Method: POST
     *
     * @param name           a name of the new user.
     * @param login          a login of the new user.
     * @param password       a password of the new user.
     * @param description    a description of the new user.
     * @param mobilePhone    a mobile phone of the new user.
     * @param landlinePhone  a landline phone of the new user.
     * @param fax            a fax of the new user.
     * @param email          a e-mail of the new user.
     * @param vkontakte      a vkontakte URL of the new user.
     * @param facebook       a facebook URL of the new user.
     * @param twitter        a twitter URL of the new user.
     * @param skype          a skype username of the new user.
     * @param multipartPhoto a photo to the new user.
     * @param isValid        a validated of the new user.
     * @param isMailing      a permit to send a letters on the user email.
     * @param isLocked       locked the user or not.
     * @return The ready object of class ModelAndView.
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
            @RequestParam(value = "is_valid") final boolean isValid,
            @RequestParam(value = "is_mailing") final boolean isMailing,
            @RequestParam(value = "is_locked") final boolean isLocked
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
        if (multipartPhoto != null) {
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
        throw new IllegalMappingException("GET method in \"/admin/user/add\" is not supported!");
    }

    /**
     * Returns the page to edit the user with URL.
     * Request mapping: /admin/user/edit/{url}
     * Method: GET
     *
     * @param url a URL of the article to edit.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/edit/{url}",
            method = RequestMethod.GET
    )
    public ModelAndView editUserByUrl(@PathVariable("url") final String url) {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("user", this.userService.getByUrl(url));
        modelAndView.setViewName("admin/user/edit");
        return modelAndView;
    }

    /**
     * Updates and save the user with url and redirects
     * by URL /admin/user/{url}.
     * Request mapping: /admin/user/update
     * Method: POST
     *
     * @param url            a URL of the user to update.
     * @param name           a new name to the user.
     * @param login          a new login to the user.
     * @param password       a new password to the user.
     * @param description    a new description to the user.
     * @param mobilePhone    a new mobile phone to the user.
     * @param landlinePhone  a new landline phone to the user.
     * @param fax            a new fax to the user.
     * @param email          a new E-mail to the user.
     * @param vkontakte      a new Vkontakte URL to the user.
     * @param facebook       a new Facebook URL to the user.
     * @param twitter        a new Twitter URL to the user.
     * @param skype          a new Skype username to the user.
     * @param multipartPhoto a photo to the user.
     * @param isValid        a validated of the user.
     * @param isMailing      a permit to send a letters on the user email.
     * @param isLocked       locked the user or not.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    public String updateUser(
            @RequestParam(value = "url") final String url,
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
            @RequestParam(value = "is_valid") final boolean isValid,
            @RequestParam(value = "is_mailing") final boolean isMailing,
            @RequestParam(value = "is_locked") final boolean isLocked
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
        if ((multipartPhoto != null) && (!multipartPhoto.isEmpty())) {
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
        throw new IllegalMappingException("GET method in \"/admin/user/update\" is not supported!");
    }

    /**
     * Removes user with url and redirects by URL /admin/user/all.
     * Request mapping: /admin/user/delete/{url}
     * Method: GET
     *
     * @param url          a URL of the user to remove.
     * @return The ready object of class ModelAndView.
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
     * Removes all users and redirects by URL /admin/user/all.
     * Request mapping: /admin/user/delete/all
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
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
     * Sends sender to personnel and redirects by URL /admin/user/all.
     * Request mapping: /admin//user/send_message
     * Method: POST
     *
     * @param subject a subject of the sender.
     * @param text    a text of the sender.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/send_message",
            method = RequestMethod.POST
    )
    public ModelAndView sendMessage(
            @RequestParam(value = "subject") final String subject,
            @RequestParam(value = "sender") final String text
    ) {
        new Thread(() -> {
            String _subject = subject;
            String message = text;
            final User user = this.userService.getAuthenticatedUser();
            if (user != null) {
                if (isNotBlank(user.getName())) {
                    _subject += " - " + user.getName();
                    message += "\n\n" + user.getName();
                }
                if (user.getContacts() != null) {
                    if (isNotBlank(user.getContacts().getMobilePhone())) {
                        message += "\nMobile Phone: " + user.getContacts().getMobilePhone();
                    }
                    if (isNotBlank(user.getContacts().getLandlinePhone())) {
                        message += "\nLandline Phone: " + user.getContacts().getLandlinePhone();
                    }
                    if (isNotBlank(user.getContacts().getEmail())) {
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
     * "GET method in "/user/send_message" is not supported!"
     * Request mapping: /admin/send_message
     * Method: POST
     *
     * @throws IllegalMappingException thrown when an error occurs reading
     *                                 the mapping between object and datastore.
     */
    @RequestMapping(
            value = "/user/send_message",
            method = RequestMethod.GET
    )
    public void sendMessageForPersonnel() throws IllegalMappingException {
        throw new IllegalMappingException("GET method in \"/user/send_message\" is not supported!");
    }
}
