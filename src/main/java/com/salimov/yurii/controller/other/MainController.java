package com.salimov.yurii.controller.other;

import com.salimov.yurii.entity.*;
import com.salimov.yurii.service.data.interfaces.CompanyService;
import com.salimov.yurii.service.data.interfaces.MessageService;
import com.salimov.yurii.service.data.interfaces.ResponseService;
import com.salimov.yurii.service.data.interfaces.UserService;
import com.salimov.yurii.service.fabrica.interfaces.CacheMVFabric;
import com.salimov.yurii.service.fabrica.interfaces.MainMVFabric;
import com.salimov.yurii.service.sender.SenderService;
import com.salimov.yurii.util.cache.Cache;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The abstract class implements a set of methods for working
 * with main ModelAndView object. Class methods create and return modelsAndView,
 * depending on the request. For the work used implementation of the interface
 * {@link CacheMVFabric} with implementation {@link MainMVFabric} interface.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see CacheMVFabric
 * @see MainMVFabric
 * @see com.salimov.yurii.service.fabrica.interfaces.ClientMVFabric
 * @see com.salimov.yurii.service.fabrica.interfaces.AdminMVFabric
 */
@SuppressWarnings("SpringMVCViewInspection")
public abstract class MainController {

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link User} class.
     *
     * @see UserService
     */
    protected final UserService userService;

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
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link Response} class.
     *
     * @see ResponseService
     */
    private final ResponseService responseService;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link Message} class.
     *
     * @see MessageService
     */
    private final MessageService messageService;

    /**
     * The implementation of the interface describes a set of methods
     * for working with E-mail.
     *
     * @see SenderService
     */
    private final SenderService senderService;

    /**
     * Constructor.
     * Initializes a implementation of the service layer interface.
     *
     * @param fabric          a implementation
     *                        of the {@link MainMVFabric} interface.
     * @param companyService  a implementation
     *                        of the {@link CompanyService} interface.
     * @param userService     a implementation
     *                        of the {@link UserService} interface.
     * @param responseService a implementation
     *                        of the {@link ResponseService} interface.
     * @param messageService  a implementation
     *                        of the {@link MessageService} interface.
     * @param senderService   a implementation
     *                        of the {@link SenderService} interface.
     * @see CompanyService
     * @see UserService
     * @see ResponseService
     * @see MessageService
     * @see SenderService
     * @see MainMVFabric
     */
    protected MainController(
            final MainMVFabric fabric,
            final CompanyService companyService,
            final UserService userService,
            final ResponseService responseService,
            final MessageService messageService,
            final SenderService senderService
    ) {
        this.fabric = fabric;
        this.companyService = companyService;
        this.userService = userService;
        this.responseService = responseService;
        this.messageService = messageService;
        this.senderService = senderService;
    }

    /**
     * Returns modelAndView with information about home page.
     * Request mapping: '', /, /index, /home
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = {
                    "", "/",
                    "/index",
                    "/home"
            },
            method = RequestMethod.GET
    )
    public ModelAndView getHomePage() {
        final ModelAndView modelAndView = this.fabric.homePage();
        modelAndView.addObject("is_captcha", null);
        return modelAndView;
    }

    /**
     * Returns modelAndView with information about page with all categories.
     * Request mapping: /category/all
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     * @see Category
     */
    @RequestMapping(
            value = "/category/all",
            method = RequestMethod.GET
    )
    public ModelAndView getAllCategoriesPage() {
        return this.fabric.allCategoriesPage();
    }

    /**
     * Returns modelAndView with information about page with some category.
     * Request mapping: /category/{url}
     * Method: GET
     *
     * @param url a url of the category to return.
     * @return The ready object of class ModelAndView.
     * @see Category
     */
    @RequestMapping(
            value = "/category/{url}",
            method = RequestMethod.GET
    )
    public ModelAndView getCategoryPage(
            @PathVariable("url") final String url
    ) {
        return this.fabric.categoryPage(url);
    }

    /**
     * Returns modelAndView with information about page with some category.
     * Request mapping: /category/{url}
     * Method: GET
     *
     * @param url     a url of the category to return.
     * @param request a implementation of the interface to provide
     *                request information for HTTP servlets.
     * @return The ready object of class ModelAndView.
     * @see Category
     */
    @RequestMapping(
            value = "/category/{url}/sort",
            method = RequestMethod.GET
    )
    public ModelAndView getCategoryWithSortArticlePage(
            @PathVariable("url") final String url,
            final HttpServletRequest request
    ) {
        return this.fabric.categoryWithSortArticlesPage(
                url,
                request.getParameter("type"),
                Boolean.parseBoolean(
                        request.getParameter("revers")
                )
        );
    }

    /**
     * Returns modelAndView with information about page with some article.
     * Request mapping: /article/{url}
     * Method: GET
     *
     * @param url a url of the article to return.
     * @return The ready object of class ModelAndView.
     * @see Article
     */
    @RequestMapping(
            value = "/article/{url}",
            method = RequestMethod.GET
    )
    public ModelAndView getArticlePage(
            @PathVariable("url") final String url
    ) {
        return this.fabric.articleByUrlPage(url);
    }

    /**
     * Returns modelAndView with information about page with some article.
     * Request mapping: /article/num_{number}
     * Method: GET
     *
     * @param number a number of the article to return.
     * @return The ready object of class ModelAndView.
     * @see Article
     */
    @RequestMapping(
            value = "/article/num_{number}",
            method = RequestMethod.GET
    )
    public ModelAndView getArticleByNumberPage(
            @PathVariable("number") final String number
    ) {
        return this.fabric.articleByNumberPage(number);
    }

    /**
     * Returns modelAndView with information about page with all articles.
     * Request mapping: /article/all
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     * @see Article
     */
    @RequestMapping(
            value = "/article/all",
            method = RequestMethod.GET
    )
    public ModelAndView getAllArticlesPage() {
        return this.fabric.allArticlesPage();
    }

    /**
     * Returns modelAndView with information about page with
     * all sorted article.
     * Request mapping: /article/all/sort
     * Method: GET
     *
     * @param request a implementation of the interface to provide
     *                request information for HTTP servlets.
     * @return The ready object of class ModelAndView.
     * @see Article
     */
    @RequestMapping(
            value = "/article/all/sort",
            method = RequestMethod.GET
    )
    public ModelAndView getAllSortArticlesPage(
            final HttpServletRequest request
    ) {
        return this.fabric.allSortArticlesPage(
                request.getParameter("type"),
                Boolean.parseBoolean(
                        request.getParameter("revers")
                )
        );
    }

    /**
     * Returns modelAndView with information about page with main company.
     * Request mapping: /company/main
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     * @see Company
     */
    @RequestMapping(
            value = "/company/main",
            method = RequestMethod.GET
    )
    public ModelAndView getAboutCompanyPage() {
        return this.fabric.aboutCompanyPage();
    }

    /**
     * Returns modelAndView with information about page
     * with contacts about main company.
     * Request mapping: /contacts, /address
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = {
                    "/contacts",
                    "/address"
            },
            method = RequestMethod.GET
    )
    public ModelAndView getContactsPage() {
        final ModelAndView modelAndView = this.fabric.contactsPage();
        modelAndView.addObject("is_captcha", null);
        return modelAndView;
    }

    /**
     * Returns modelAndView with information about page
     * with all partners companies.
     * Request mapping: /company/all
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     * @see Company
     */
    @RequestMapping(
            value = "/company/all",
            method = RequestMethod.GET
    )
    public ModelAndView getPartnersPage() {
        return this.fabric.allPartnersPage();
    }

    /**
     * Returns modelAndView with information about page with
     * all sorted partner-companies.
     * Request mapping: /company/all/sort
     * Method: GET
     *
     * @param request a implementation of the interface to provide
     *                request information for HTTP servlets.
     * @return The ready object of class ModelAndView.
     * @see Company
     */
    @RequestMapping(
            value = "/company/all/sort",
            method = RequestMethod.GET
    )
    public ModelAndView getAllSortPartnersPage(
            final HttpServletRequest request
    ) {
        return this.fabric.allSortPartnersByTitlePage(
                Boolean.parseBoolean(
                        request.getParameter("revers")
                )
        );
    }

    /**
     * Returns modelAndView with information about page with some company.
     * Request mapping: /company/{url}
     * Method: GET
     *
     * @param url a url of the company to return.
     * @return The ready object of class ModelAndView.
     * @see Company
     */
    @RequestMapping(
            value = "/company/{url}",
            method = RequestMethod.GET
    )
    public ModelAndView getPartnerPage(
            @PathVariable("url") String url
    ) {
        return this.fabric.partnerPage(url);
    }

    /**
     * Returns modelAndView with information about page with all responses.
     * Request mapping: /responses
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     * @see Response
     */
    @RequestMapping(
            value = "/responses",
            method = RequestMethod.GET
    )
    public ModelAndView getAllResponsesPage() {
        final ModelAndView modelAndView = this.fabric.allResponsesPage();
        modelAndView.addObject("is_captcha", null);
        return modelAndView;
    }

    /**
     * Returns modelAndView with information about page with
     * all sorted responses.
     * Request mapping: /responses/sort
     * Method: GET
     *
     * @param request a implementation of the interface to provide
     *                request information for HTTP servlets.
     * @return The ready object of class ModelAndView.
     * @see Response
     */
    @RequestMapping(
            value = "/responses/sort",
            method = RequestMethod.GET
    )
    public ModelAndView getAllSortResponsesByDatePage(
            final HttpServletRequest request
    ) {
        return this.fabric.allSortResponsesByDatePage(
                Boolean.parseBoolean(
                        request.getParameter("revers")
                )
        );
    }

    /**
     * Returns default modelAndView.
     *
     * @return The ready object of class ModelAndView.
     */
    protected ModelAndView getDefaultModelAndView() {
        return this.fabric.getDefaultModelAndView();
    }

    /**
     * Returns page by parameter url.
     *
     * @param url       url of request.
     * @param isCaptcha a result of google-captcha verification.
     * @return The ready object of class ModelAndView.
     */
    protected ModelAndView getMessageMV(
            final String url,
            final boolean isCaptcha
    ) {
        ModelAndView modelAndView = new ModelAndView();
        if (isNotBlank(url)) {
            switch (url) {
                case "":
                case "/":
                case "/index":
                case "/home":
                    modelAndView = getHomePage();
                    break;
                case "/contacts":
                case "/address":
                    modelAndView = getContactsPage();
                    break;
                default:
                    modelAndView.setViewName("redirect:" + url);
            }
        } else {
            modelAndView.setViewName("redirect:" + "/");
        }
        modelAndView.addObject("is_captcha", isCaptcha);
        return modelAndView;
    }

    /**
     * Sends client message to email and saves it.
     *
     * @param name        a client name.
     * @param phone       a phone name.
     * @param email       a email name.
     * @param userMessage a client message.
     */
    protected void sendMess(
            final String name,
            final String phone,
            final String email,
            final String userMessage
    ) {
        new Thread(() -> {
            final String _text = "User name: " + name
                    + "\nPhone: " + phone
                    + (isNotBlank(email) ? "E-mail: " + email : "")
                    + "\nText: \n" + userMessage;
            final String subject = "New Message";
            sendToEmail(subject, _text);
            saveMess(
                    name, phone, email,
                    userMessage, subject
            );
        }).start();
    }

    /**
     * Saves new message.
     *
     * @param name    a client name.
     * @param phone   a phone name.
     * @param email   a email name.
     * @param text    a client message.
     * @param subject a subject of the message.
     * @see MessageService
     * @see Message
     */
    private void saveMess(
            final String name,
            final String phone,
            final String email,
            final String text,
            final String subject
    ) {
        this.messageService.add(
                new Message(
                        name, email, phone,
                        subject, text
                )
        );
    }

    /**
     * Sends client response to email and saves it.
     *
     * @param name a client name.
     * @param text a text of response.
     * @see Response
     */
    protected void sendResp(
            final String name,
            final String text
    ) {
        new Thread(() -> {
            sendToEmail(
                    "New Response",
                    "User name: " + name
                            + "\n\nText: \n" + text
            );
            saveResp(name, text);
        }).start();
    }

    /**
     * Saves new response.
     *
     * @param name a client name.
     * @param text a text of response.
     * @see ResponseService
     * @see Response
     */
    private void saveResp(
            final String name,
            final String text
    ) {
        this.responseService.add(
                new Response(name, text)
        );
        Cache.removeAll("Response");
    }

    /**
     * Sends some message to emails by personnel.
     *
     * @param subject a subject of message.
     * @param text    a text of message.
     */
    private void sendToEmail(
            final String subject,
            final String text
    ) {
        final Company mainCompany = this.companyService.getMainCompany();
        this.senderService.send(
                subject + " | " + mainCompany.getTitle(),
                text,
                this.userService.getPersonnel(),
                mainCompany.getSenderEmail(),
                mainCompany.getSenderPass()
        );
    }

    /**
     * Returns responses page.
     *
     * @param isCaptcha a result of google-captcha verification.
     * @return The ready object of class ModelAndView.
     * @see Response
     */
    protected ModelAndView getResponsesMV(final boolean isCaptcha) {
        final ModelAndView modelAndView = getAllResponsesPage();
        modelAndView.addObject("is_captcha", isCaptcha);
        return modelAndView;
    }
}
