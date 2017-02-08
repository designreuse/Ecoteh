package com.salimov.yurii.controller.admin;

import com.salimov.yurii.controller.other.MainController;
import com.salimov.yurii.entity.Response;
import com.salimov.yurii.service.data.interfaces.CompanyService;
import com.salimov.yurii.service.data.interfaces.MessageService;
import com.salimov.yurii.service.data.interfaces.ResponseService;
import com.salimov.yurii.service.data.interfaces.UserService;
import com.salimov.yurii.service.fabrica.impl.CacheMVFabricImpl;
import com.salimov.yurii.service.fabrica.interfaces.MainMVFabric;
import com.salimov.yurii.service.sender.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * The class implements a set of methods for working
 * with main ModelAndView objects for admins.
 * Class methods create and return modelsAndView,
 * depending on the request.
 * For the work used implementation of the interface
 * {@link MainMVFabric} interface.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see MainMVFabric
 * @see CompanyService
 * @see UserService
 * @see ResponseService
 * @see SenderService
 */
@Controller
@RequestMapping(value = "/admin")
@ComponentScan(basePackages = "com.salimov.yurii.service")
@SuppressWarnings("SpringMVCViewInspection")
public class AdminMainController extends MainController {

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param fabric          a implementation of the {@link MainMVFabric}
     *                        interface.
     * @param companyService  a implementation of the {@link CompanyService}
     *                        interface.
     * @param userService     a implementation of the {@link UserService}
     *                        interface.
     * @param messageService  a implementation of the {@link MessageService}
     *                        interface.
     * @param senderService   a implementation of the {@link SenderService}
     *                        interface.
     * @param responseService a implementation of the {@link ResponseService}
     *                        interface.
     * @see MainMVFabric
     * @see CompanyService
     * @see UserService
     * @see SenderService
     * @see ResponseService
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public AdminMainController(
            final MainMVFabric fabric,
            final CompanyService companyService,
            final UserService userService,
            final MessageService messageService,
            final SenderService senderService,
            final ResponseService responseService) {
        super(
                new CacheMVFabricImpl(fabric),
                companyService, userService, responseService,
                messageService, senderService
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
        final ModelAndView modelAndView = getDefaultModelAndView();
        modelAndView.addObject(
                "user", this.userService.getAuthenticatedUser()
        );
        modelAndView.setViewName("admin/menu/menu_page");
        return modelAndView;
    }

    /**
     * Sends sender and redirects by url if it is not blank,
     * otherwise by url /.
     * Request mapping: /admin/send_message
     * Method: POST
     *
     * @param url         a url of the page which must be to redirect.
     * @param name        a sender name.
     * @param phone       a phone name.
     * @param email       a email name.
     * @param userMessage a sender sender.
     * @return The ready object of class ModelAndView.
     * @see SenderService
     */
    @RequestMapping(
            value = "/send_message",
            method = RequestMethod.POST
    )
    public ModelAndView sendMessage(
            @RequestParam(value = "url") final String url,
            @RequestParam(value = "name") final String name,
            @RequestParam(value = "phone") final String phone,
            @RequestParam(value = "email") final String email,
            @RequestParam(value = "message") final String userMessage
    ) {
        sendMess(name, phone, email, userMessage);
        return getMessageMV(url, true);
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception sender:
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
        throw new IllegalMappingException(
                "GET method in \"/admin/send_message\" is not supported!"
        );
    }

    /**
     * Sends response and redirects by url /responses.
     * Request mapping: /admin/response/send
     * Method: POST
     *
     * @param name a sender name.
     * @param text a sender text.
     * @return The ready object of class ModelAndView.
     * @see Response
     */
    @RequestMapping(
            value = "/response/send",
            method = RequestMethod.POST
    )
    public ModelAndView sendResponse(
            @RequestParam(value = "name") final String name,
            @RequestParam(value = "response") final String text
    ) {
        sendResp(name, text);
        return getResponsesMV(true);
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception sender:
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
        throw new IllegalMappingException(
                "GET method in \"/admin/response/send\" is not supported!"
        );
    }
}
