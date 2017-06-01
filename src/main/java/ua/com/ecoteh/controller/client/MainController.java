package ua.com.ecoteh.controller.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.entity.company.CompanyEntity;
import ua.com.ecoteh.entity.message.MessageEntity;
import ua.com.ecoteh.entity.response.ResponseEntity;
import ua.com.ecoteh.entity.user.UserEntity;
import ua.com.ecoteh.service.data.CompanyService;
import ua.com.ecoteh.service.data.MessageService;
import ua.com.ecoteh.service.data.ResponseService;
import ua.com.ecoteh.service.data.UserService;
import ua.com.ecoteh.service.fabrica.CacheMVFabric;
import ua.com.ecoteh.service.fabrica.MainMVFabric;
import ua.com.ecoteh.service.sender.SenderService;
import ua.com.ecoteh.util.cache.Cache;

import javax.servlet.http.HttpServletRequest;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * The abstract class implements a set of methods for working
 * with main ModelAndView object. Class methods create and return modelsAndView,
 * depending on the request. For the work used implementation of the interface
 * {@link CacheMVFabric} with implementation {@link MainMVFabric} interface.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@SuppressWarnings("SpringMVCViewInspection")
public abstract class MainController {

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link UserEntity} class.
     */
    protected final UserService userService;

    /**
     * The implementation of the interface provides a set of standard methods
     * for creates and returns the main modelAndViews.
     */
    protected final MainMVFabric fabric;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link CompanyEntity} class.
     */
    protected final CompanyService companyService;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link ResponseEntity} class.
     */
    protected final ResponseService responseService;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link MessageEntity} class.
     */
    protected final MessageService messageService;

    /**
     * The implementation of the interface describes a set of methods
     * for working with E-mail.
     */
    protected final SenderService senderService;

    /**
     * Constructor.
     * Initializes a implementation of the service layer interface.
     *
     * @param fabric          the implementation of the {@link MainMVFabric} interface.
     * @param companyService  the implementation of the {@link CompanyService} interface.
     * @param userService     the implementation of the {@link UserService} interface.
     * @param responseService the implementation of the {@link ResponseService} interface.
     * @param messageService  the implementation of the {@link MessageService} interface.
     * @param senderService   the implementation of the {@link SenderService} interface.
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
     * Returns a home page.
     * Request mapping: '', /, /index, /home
     * Method: GET
     *
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = { "", "/", "/index", "/home" },
            method = RequestMethod.GET
    )
    public ModelAndView getHomePage() {
        final ModelAndView modelAndView = this.fabric.homePage();
        modelAndView.addObject("is_captcha", null);
        return modelAndView;
    }

    /**
     * Returns a page with an all categories.
     * Request mapping: /category/all
     * Method: GET
     *
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = { "/categories", "/category/all" },
            method = RequestMethod.GET
    )
    public ModelAndView getAllCategoriesPage() {
        return this.fabric.allCategoriesPage();
    }

    /**
     * Returns a page with a category with the incoming URL.
     * Request mapping: /category/{url},
     * where {url} is a URL of a category to return.
     * Method: GET
     *
     * @param url the URL of a category to return.
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = "/category/{url}",
            method = RequestMethod.GET
    )
    public ModelAndView getCategoryPage(@PathVariable("url") final String url) {
        return this.fabric.categoryPage(url);
    }

    /**
     * Returns a page with a category with the incoming URL.
     * And sorts a category articles by title.
     * Request mapping: /category/{url}/sort,
     * where {url} is a URL of a category to return.
     * Method: GET
     *
     * @param url     the URL of a category to return.
     * @param request the implementation of the interface to provide
     *                request information for HTTP servlets.
     * @return The ready object of the ModelAndView class.
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
                url, request.getParameter("type"),
                Boolean.parseBoolean(request.getParameter("revers"))
        );
    }

    /**
     * Returns a page with a article with the incoming URL.
     * Request mapping: /article/{url},
     * where {url} is a URL of an article to return.
     * Method: GET
     *
     * @param url the URL of a article to return.
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = "/article/{url}",
            method = RequestMethod.GET
    )
    public ModelAndView getArticlePage(@PathVariable("url") final String url) {
        return this.fabric.articleByUrlPage(url);
    }

    /**
     * Returns a page with a article with the incoming number.
     * Request mapping: /article/num_{number},
     * where {number} is a number of an article to return.
     * Method: GET
     *
     * @param number the number of a article to return.
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = "/article/num_{number}",
            method = RequestMethod.GET
    )
    public ModelAndView getArticleByNumberPage(@PathVariable("number") final String number) {
        return this.fabric.articleByNumberPage(number);
    }

    /**
     * Returns a page with an all articles.
     * Request mapping: /articles, /article/all
     * Method: GET
     *
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = { "/articles", "/article/all" },
            method = RequestMethod.GET
    )
    public ModelAndView getAllArticlesPage() {
        return this.fabric.allArticlesPage();
    }

    /**
     * Returns a page with an all articles sorted by the incoming type.
     * Request mapping: /articles/sort, /article/all/sort,
     * Method: GET
     *
     * @param request the implementation of the interface to provide
     *                request information for HTTP servlets.
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = { "/articles/sort", "/article/all/sort" },
            method = RequestMethod.GET
    )
    public ModelAndView getAllSortArticlesPage(final HttpServletRequest request) {
        return this.fabric.allSortArticlesPage(
                request.getParameter("type"),
                Boolean.parseBoolean(request.getParameter("revers"))
        );
    }

    /**
     * Returns a page with main company.
     * Request mapping: /company/main
     * Method: GET
     *
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = "/company/main",
            method = RequestMethod.GET
    )
    public ModelAndView getAboutCompanyPage() {
        return this.fabric.aboutCompanyPage();
    }

    /**
     * Returns a page with contacts about a main company.
     * Request mapping: /contacts, /address
     * Method: GET
     *
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = { "/contacts", "/address" },
            method = RequestMethod.GET
    )
    public ModelAndView getContactsPage() {
        final ModelAndView modelAndView = this.fabric.contactsPage();
        modelAndView.addObject("is_captcha", null);
        return modelAndView;
    }

    /**
     * Returns  page with an all partners companies.
     * Request mapping: /company/all
     * Method: GET
     *
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = { "/companies", "/partners", "/company/all" },
            method = RequestMethod.GET
    )
    public ModelAndView getPartnersPage() {
        return this.fabric.allPartnersPage();
    }

    /**
     * Returns a page with all sorted partner-companies.
     * Request mapping: /companies/sort, /company/all/sort
     * Method: GET
     *
     * @param request a implementation of the interface to provide
     *                request information for HTTP servlets.
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = { "/companies/sort", "/company/all/sort" },
            method = RequestMethod.GET
    )
    public ModelAndView getAllSortPartnersPage(final HttpServletRequest request) {
        return this.fabric.allSortPartnersByTitlePage(
                Boolean.parseBoolean(request.getParameter("revers"))
        );
    }

    /**
     * Returns a page with a company with the incoming URL.
     * Request mapping: /company/{url},
     * where {url} is a URL of a company to return.
     * Method: GET
     *
     * @param url the URL of a company to return.
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = "/company/{url}",
            method = RequestMethod.GET
    )
    public ModelAndView getPartnerPage(@PathVariable("url") String url) {
        return this.fabric.partnerPage(url);
    }

    /**
     * Returns page with all responses.
     * Request mapping: /responses, /responseEntity/all
     * Method: GET
     *
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = { "/responses", "/response/all" },
            method = RequestMethod.GET
    )
    public ModelAndView getAllResponsesPage() {
        final ModelAndView modelAndView = this.fabric.allResponsesPage();
        modelAndView.addObject("is_captcha", null);
        modelAndView.setViewName("responseEntity/all");
        return modelAndView;
    }

    /**
     * Returns page with an all responses sorted by date.
     * Request mapping: /responses/sort, /responseEntity/all/sort
     * Method: GET
     *
     * @param request the implementation of the interface to provide
     *                request information for HTTP servlets.
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = { "/responses/sort", "/response/all/sort" },
            method = RequestMethod.GET
    )
    public ModelAndView getAllSortResponsesByDatePage(final HttpServletRequest request) {
        final ModelAndView modelAndView = this.fabric.allSortResponsesByDatePage(
                Boolean.parseBoolean(request.getParameter("revers"))
        );
        modelAndView.addObject("is_captcha", null);
        return modelAndView;
    }

    /**
     * Returns default modelAndView.
     *
     * @return The ready object of the ModelAndView class.
     */
    protected ModelAndView getDefaultModelAndView() {
        return this.fabric.getDefaultModelAndView();
    }

    /**
     * Returns a page by the incoming URL.
     *
     * @param url       the URL of request.
     * @param isCaptcha the result of a google-captcha verification.
     * @return The ready object of the ModelAndView class.
     */
    protected ModelAndView getMessageMV(
            final String url,
            final boolean isCaptcha
    ) {
        ModelAndView modelAndView = new ModelAndView();
        if (isNotEmpty(url)) {
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
     * Sends a client messageEntity to E-mail and saves it.
     *
     * @param messageEntity the messageEntity to send.
     */
    protected void sendMess(final MessageEntity messageEntity) {
        new Thread(() -> {
            final UserEntity userEntity = messageEntity.getUserEntity();
            final String text = "UserEntity name: " + userEntity.getName() +
                    "\nPhone: " + userEntity.getContactsEntity().getMobilePhone() +
                    (isNotEmpty(userEntity.getContactsEntity().getEmail()) ? "\nE-mail: " + userEntity.getContactsEntity().getEmail() : "") +
                    (isNotEmpty(messageEntity.getText()) ? "\nText: " + messageEntity.getText() : "");
            sendToEmail(messageEntity.getSubject(), text);
        }).start();
        this.messageService.add(messageEntity);
    }

    /**
     * Sends a client responseEntity to E-mail and saves it.
     *
     * @param responseEntity the responseEntity.
     */
    protected void sendResp(final ResponseEntity responseEntity) {
        new Thread(() -> {
            sendToEmail(
                    "New ResponseEntity",
                    "UserEntity name: " + responseEntity.getUsername() +
                            "\nText: " + responseEntity.getText()
            );
        }).start();
        this.responseService.add(responseEntity);
        Cache.removeAll("ResponseEntity");
    }

    /**
     * Creates and sends a messageEntity to personnel E-mails.
     *
     * @param subject the subject of a new messageEntity.
     * @param text    the text of a new messageEntity.
     */
    private void sendToEmail(
            final String subject,
            final String text
    ) {
        final CompanyEntity mainCompanyEntity = this.companyService.getMainCompany();
        this.senderService.send(
                subject + " | " + mainCompanyEntity.getTitle(), text,
                this.userService.getPersonnel(),
                mainCompanyEntity.getSenderEmail(), mainCompanyEntity.getSenderPass()
        );
    }

    /**
     * Returns a responses page.
     *
     * @param isCaptcha the result of a google-captcha verification.
     * @return The ready object of the ModelAndView class.
     */
    protected ModelAndView getResponsesMV(final boolean isCaptcha) {
        final ModelAndView modelAndView = getAllResponsesPage();
        modelAndView.addObject("is_captcha", isCaptcha);
        return modelAndView;
    }
}
