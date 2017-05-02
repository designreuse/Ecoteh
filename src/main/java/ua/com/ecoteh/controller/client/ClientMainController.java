package ua.com.ecoteh.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.entity.Contacts;
import ua.com.ecoteh.entity.Message;
import ua.com.ecoteh.entity.Response;
import ua.com.ecoteh.entity.User;
import ua.com.ecoteh.enums.UserRole;
import ua.com.ecoteh.service.captcha.CaptchaService;
import ua.com.ecoteh.service.data.CompanyService;
import ua.com.ecoteh.service.data.MessageService;
import ua.com.ecoteh.service.data.ResponseService;
import ua.com.ecoteh.service.data.UserService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;
import ua.com.ecoteh.service.sender.SenderService;

import javax.servlet.http.HttpServletRequest;

/**
 * The class implements a set of methods for working
 * with main ModelAndView objects for clients. Class methods create
 * and return modelsAndView, depending on the request.
 * For the work used implementation of the interface CacheMVFabric
 * with implementation ClientMVFabric interface.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Controller
@ComponentScan(basePackages = "ua.com.ecoteh.service")
@SuppressWarnings("SpringMVCViewInspection")
public class ClientMainController extends MainController {

    /**
     *
     */
    private final static String GET_METHOD_NOT_SUPPORTED_MESSAGE =
            "GET method in \"%s\" is not supported!";

    /**
     * The implementation of the {@link CaptchaService} interface.
     */
    private final CaptchaService captchaService;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param fabric          a implementation of the {@link MainMVFabric} interface.
     * @param companyService  a implementation of the {@link CompanyService} interface.
     * @param userService     a implementation of the {@link UserService} interface.
     * @param responseService a implementation of the {@link ResponseService} interface.
     * @param messageService  a implementation of the {@link MessageService} interface.
     * @param senderService   a implementation of the {@link SenderService} interface.
     * @param captchaService  a implementation of the {@link CaptchaService} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public ClientMainController(
            @Qualifier("cacheMVFabricImpl") final MainMVFabric fabric,
            final CompanyService companyService,
            final UserService userService,
            final ResponseService responseService,
            final MessageService messageService,
            final SenderService senderService,
            final CaptchaService captchaService
    ) {
        super(
                fabric, companyService, userService,
                responseService, messageService, senderService
        );
        this.captchaService = captchaService;
    }

    /**
     * Send client message and redirect by URL
     * if it is not blank, otherwise by URL /.
     * Request mapping: /send_message
     * Method: POST
     *
     * @param url         a URL of the page which must be to redirect.
     * @param name        a client name.
     * @param phone       a phone name.
     * @param email       a email name.
     * @param userMessage a client message.
     * @param request     a implementation of the interface to provide
     *                    request information for HTTP servlets.
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
            @RequestParam(value = "message", required = false) final String userMessage,
            final HttpServletRequest request
    ) {
        final boolean isCaptcha = this.captchaService.isVerify(request);
        if (isCaptcha) {
            final User user = new User(name, new Contacts(email, phone));
            user.setRole(UserRole.CLIENT);
            sendMess(new Message(user, "New Message", userMessage));
        }
        return getMessageMV(url, isCaptcha);
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception message:
     * "GET method in "/send_message" is not supported!"
     * Request mapping: /send_message
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
        throw new IllegalMappingException(
                String.format(GET_METHOD_NOT_SUPPORTED_MESSAGE, "/send_message")
        );
    }

    /**
     * Sends response and redirects by URL /responses.
     * Request mapping: /send_response
     * Method: POST
     *
     * @param name    a sender name.
     * @param text    a sender text.
     * @param request a implementation of the interface to provide
     *                request information for HTTP servlets.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/response/send",
            method = RequestMethod.POST
    )
    public ModelAndView sendResponse(
            @RequestParam(value = "name") final String name,
            @RequestParam(value = "response") final String text,
            final HttpServletRequest request
    ) {
        final boolean isCaptcha = this.captchaService.isVerify(request);
        if (isCaptcha) {
            sendResp(new Response(name, text));
        }
        return getResponsesMV(isCaptcha);
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception message: "GET method in "/response/send" is not supported!"
     * Request mapping: /send_response
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
        throw new IllegalMappingException(
                String.format(GET_METHOD_NOT_SUPPORTED_MESSAGE, "/response/send")
        );
    }
}