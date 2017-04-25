package ua.com.ecoteh.controller.admin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.controller.client.MainController;
import ua.com.ecoteh.entity.Contacts;
import ua.com.ecoteh.entity.Message;
import ua.com.ecoteh.entity.Response;
import ua.com.ecoteh.entity.User;
import ua.com.ecoteh.enums.UserRole;
import ua.com.ecoteh.service.data.CompanyService;
import ua.com.ecoteh.service.data.MessageService;
import ua.com.ecoteh.service.data.ResponseService;
import ua.com.ecoteh.service.data.UserService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;
import ua.com.ecoteh.service.sender.SenderService;

/**
 * The class implements a set of methods for working
 * with main ModelAndView objects for admins.
 * Class methods create and return modelsAndView, depending on the request.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/admin")
@ComponentScan(basePackages = "ua.com.ecoteh.service")
@SuppressWarnings("SpringMVCViewInspection")
public class AdminMainController extends MainController {

    /**
     * The object for logging information.
     */
    private static final Logger LOGGER = Logger.getLogger(AdminMainController.class);

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param fabric          a implementation of the {@link MainMVFabric} interface.
     * @param companyService  a implementation of the {@link CompanyService} interface.
     * @param userService     a implementation of the {@link UserService} interface.
     * @param messageService  a implementation of the {@link MessageService} interface.
     * @param senderService   a implementation of the {@link SenderService} interface.
     * @param responseService a implementation of the {@link ResponseService} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public AdminMainController(
            @Qualifier("cacheMVFabricImpl") final MainMVFabric fabric,
            final CompanyService companyService,
            final UserService userService,
            final MessageService messageService,
            final SenderService senderService,
            final ResponseService responseService
    ) {
        super(
                fabric, companyService, userService,
                responseService, messageService, senderService
        );
    }

    /**
     * Returns page with administrator menu.
     * Request mapping: /admin/menu
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/menu",
            method = RequestMethod.GET
    )
    public ModelAndView getAdminMenu() {
        ModelAndView modelAndView;
        try {
            modelAndView = getDefaultModelAndView();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            ex.printStackTrace();
            modelAndView = new ModelAndView();
        }
        modelAndView.addObject("authorized_user", this.userService.getAuthenticatedUser());
        modelAndView.setViewName("admin/menu/menu");
        return modelAndView;
    }

    /**
     * Sends message.
     * Request mapping: /admin/send_message
     * Method: POST
     *
     * @param url         a URL of the page which must be to redirect.
     * @param name        a sender name.
     * @param phone       a phone name.
     * @param email       a email name.
     * @param userMessage a sender sender.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/send_message",
            method = RequestMethod.POST
    )
    public ModelAndView sendMessage(
            @RequestParam(value = "url") final String url,
            @RequestParam(value = "name") final String name,
            @RequestParam(value = "phone") final String phone,
            @RequestParam(value = "email", required = false) final String email,
            @RequestParam(value = "message", required = false) final String userMessage
    ) {
        final User user = new User(name, new Contacts(email, phone));
        user.setRole(UserRole.CLIENT);
        sendMess(new Message(user, "New Message", userMessage));
        return getMessageMV(url, true);
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception message:
     * "GET method in "/admin/send_message" is not supported!"
     * Request mapping: /admin/send_message
     * Method: POST
     *
     * @throws IllegalMappingException thrown when an error occurs reading
     *                                 the mapping between object and datastore.
     */
    @RequestMapping(
            value = "/send_message",
            method = RequestMethod.GET
    )
    public void sendMessage() throws IllegalMappingException {
        throw new IllegalMappingException("GET method in \"/admin/send_message\" is not supported!");
    }

    /**
     * Sends response and redirects by URL /responses.
     * Request mapping: /admin/response/send
     * Method: POST
     *
     * @param name a sender name.
     * @param text a sender text.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/response/send",
            method = RequestMethod.POST
    )
    public ModelAndView sendResponse(
            @RequestParam(value = "name") final String name,
            @RequestParam(value = "response") final String text
    ) {
        sendResp(new Response(name, text));
        return getResponsesMV(true);
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception message:
     * "GET method in "/admin/response/send" is not supported!"
     * Request mapping: /admin/send_response
     * Method: POST
     *
     * @throws IllegalMappingException thrown when an error occurs reading
     *                                 the mapping between object and datastore.
     */
    @RequestMapping(
            value = "/response/send",
            method = RequestMethod.GET
    )
    public void sendResponse() throws IllegalMappingException {
        throw new IllegalMappingException("GET method in \"/admin/response/send\" is not supported!");
    }
}
