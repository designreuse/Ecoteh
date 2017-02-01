package com.salimov.yurii.controller.admin;

import com.salimov.yurii.entity.Message;
import com.salimov.yurii.service.data.interfaces.MessageService;
import com.salimov.yurii.service.fabrica.impl.CacheMVFabricImpl;
import com.salimov.yurii.service.fabrica.interfaces.AdminMVFabric;
import com.salimov.yurii.service.fabrica.interfaces.CacheMVFabric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * The class implements a set of methods for working
 * with main ModelAndView objects and object of the {@link Message}
 * class for admins. Class methods create and return modelsAndView,
 * depending on the request. For the work used implementation
 * of the interface {@link AdminMVFabric}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see Message
 * @see AdminMVFabric
 * @see MessageService
 */
@Controller
@RequestMapping(value = "/admin/messages")
@ComponentScan(basePackages = "com.salimov.yurii.service")
@SuppressWarnings("SpringMVCViewInspection")
public class AdminMessageController {

    /**
     * The implementation of the interface provides a set of standard
     * methods for creates and returns the main modelAndViews for admins.
     *
     * @see CacheMVFabric
     */
    private final CacheMVFabric fabric;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link Message} class.
     *
     * @see MessageService
     */
    private final MessageService messageService;

    /**
     * Constructor.
     * Initializes a implementation of the interface.
     *
     * @param fabric         a implementation of the {@link AdminMVFabric}
     *                       interface.
     * @param messageService a implementation of the {@link MessageService}
     *                       interface.
     * @see MessageService
     * @see AdminMVFabric
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public AdminMessageController(
            final AdminMVFabric fabric,
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
     * @see Message
     */
    @RequestMapping(
            value = {"", "/"},
            method = RequestMethod.GET
    )
    public ModelAndView getMessages() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject(
                "messages", this.messageService.getAll(false)
        );
        modelAndView.setViewName("admin/message/all_page");
        return modelAndView;
    }

    /**
     * Removes message with url and redirects by id /admin/messages.
     * Request mapping: /admin/message/delete/{id}
     * Method: GET
     *
     * @param id           a id of the message to remove.
     * @param modelAndView a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     * @see Message
     */
    @RequestMapping(
            value = "/delete/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView deleteMessage(
            @PathVariable("id") final Long id,
            final ModelAndView modelAndView
    ) {
        this.messageService.remove(id);
        modelAndView.setViewName("redirect:/admin/messages");
        return modelAndView;
    }

    /**
     * Removes all messages and redirects by url /admin/messages.
     * Request mapping: /admin/message/delete/all
     * Method: GET
     *
     * @param modelAndView a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     * @see Message
     */
    @RequestMapping(
            value = "/delete/all",
            method = RequestMethod.GET
    )
    public ModelAndView deleteAllMessages(final ModelAndView modelAndView) {
        this.messageService.removeAll();
        modelAndView.setViewName("redirect:/admin/messages");
        return modelAndView;
    }
}
