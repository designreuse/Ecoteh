package com.salimov.ecoteh.controller.admin;

import com.salimov.ecoteh.entity.Message;
import com.salimov.ecoteh.service.data.MessageService;
import com.salimov.ecoteh.service.fabrica.CacheMVFabricImpl;
import com.salimov.ecoteh.service.fabrica.MainMVFabric;
import com.salimov.ecoteh.service.fabrica.CacheMVFabric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * The class implements a set of methods for working with
 * objects of the {@link Message} class for admins.
 * Class methods create and return modelsAndView, depending on the request.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
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
                "com.salimov.ecoteh.service.fabrica",
                "com.salimov.ecoteh.service.data"
        }
)
@SuppressWarnings("SpringMVCViewInspection")
public class MessageController {

    /**
     * The implementation of the interface provides a set of standard
     * methods for creates and returns the main modelAndViews for admins.
     */
    private final CacheMVFabric fabric;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link Message} class.
     */
    private final MessageService messageService;

    /**
     * Constructor.
     * Initializes a implementation of the interface.
     *
     * @param fabric         a implementation of the {@link MainMVFabric} interface.
     * @param messageService a implementation of the {@link MessageService} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public MessageController(
            final MainMVFabric fabric,
            final MessageService messageService
    ) {
        this.fabric = new CacheMVFabricImpl(fabric);
        this.messageService = messageService;
    }

    /**
     * Returns modelAndView with information about page with all messages.
     * Request mapping: /admin/messages, /admin/messages/
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = {"", "/", "/all"},
            method = RequestMethod.GET
    )
    public ModelAndView getMessagesPage() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("messages", this.messageService.getAll(false));
        modelAndView.setViewName("admin/message/all");
        return modelAndView;
    }

    /**
     * Removes message with id and redirects by URL /admin/messages.
     * Request mapping: /admin/message/delete/{id}
     * Method: GET
     *
     * @param id           a id of the message to remove.
     * @return The ready object of class ModelAndView.
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
     * Removes all messages and redirects by URL /admin/messages.
     * Request mapping: /admin/message/delete/all
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
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
