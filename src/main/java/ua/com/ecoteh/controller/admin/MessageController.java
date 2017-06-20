package ua.com.ecoteh.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.entity.message.Message;
import ua.com.ecoteh.service.data.MessageService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;

/**
 * The class implements a set of methods for working with
 * objects of the {@link Message} class for admins.
 * Class methods create and return modelsAndView, depending on the request.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Controller
@RequestMapping(
        value = {
                "/admin/message",
                "/admin/messages"
        }
)
@ComponentScan(
        basePackages = {
                "ua.com.ecoteh.service.fabrica",
                "ua.com.ecoteh.service.data"
        }
)
@SuppressWarnings("SpringMVCViewInspection")
public final class MessageController {

    /**
     * The implementation of the interface provides a set of standard
     * methods for creates and returns the main modelAndViews for admins.
     */
    private final MainMVFabric fabric;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link Message} class.
     */
    private final MessageService messageService;

    /**
     * Constructor.
     * Initializes a implementation of the interface.
     *
     * @param fabric         the implementation of a {@link MainMVFabric} interface.
     * @param messageService the implementation of the {@link MessageService} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public MessageController(
            @Qualifier("cacheMVFabricImpl") final MainMVFabric fabric,
            final MessageService messageService
    ) {
        this.fabric = fabric;
        this.messageService = messageService;
    }

    /**
     * Returns a page with an all messages.
     * Request mapping: /admin/messages, /admin/messages/all
     * Method: GET
     *
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = { "", "/", "/all" },
            method = RequestMethod.GET
    )
    public ModelAndView getMessagesPage() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("messages", this.messageService.getAll(false));
        modelAndView.setViewName("message/all");
        return modelAndView;
    }

    /**
     * Removes a message with the incoming id
     * and redirects by the "/admin/messages" URL.
     * Request mapping: /admin/message/delete/{id},
     * where {id} is a id of a message to remove.
     * Method: GET
     *
     * @param id the id of a message to remove.
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = "/delete/{id}",
            method = RequestMethod.GET
    )
    public String deleteMessageById(@PathVariable("id") final long id) {
        this.messageService.remove(id);
        return "redirect:/admin/messages";
    }

    /**
     * Removes an all messages and redirects
     * by the "/admin/messages" URL.
     * Request mapping: /admin/article/delete/all
     * Method: GET
     *
     * @return The redirect string to the "/admin/messages" URL.
     */
    @RequestMapping(
            value = "/delete/all",
            method = RequestMethod.GET
    )
    public String deleteAllMessages() {
        this.messageService.removeAll();
        return "redirect:/admin/messages";
    }
}
