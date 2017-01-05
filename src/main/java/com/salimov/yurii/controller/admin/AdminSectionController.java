package com.salimov.yurii.controller.admin;

import com.salimov.yurii.entity.Photo;
import com.salimov.yurii.entity.Section;
import com.salimov.yurii.service.data.interfaces.PhotoService;
import com.salimov.yurii.service.data.interfaces.SectionService;
import com.salimov.yurii.service.fabrica.impl.CacheMVFabricImpl;
import com.salimov.yurii.service.fabrica.interfaces.AdminMVFabric;
import com.salimov.yurii.service.fabrica.interfaces.MainMVFabric;
import com.salimov.yurii.util.cache.Cache;
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

/**
 * The class implements a set of methods for working
 * with main ModelAndView objects and object of {@link Section}
 * class or subclasses for admins. Class methods create
 * and return modelsAndView, depending on the request.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Section
 * @see SectionService
 * @see AdminMVFabric
 */
@Controller
@RequestMapping(value = "/admin/section")
@ComponentScan(basePackages = "com.salimov.yurii.service")
@SuppressWarnings("SpringMVCViewInspection")
public class AdminSectionController {

    /**
     * The implementation of the interface provides a set of standard methods
     * for creates and returns the main modelAndViews.
     *
     * @see MainMVFabric
     */
    private final MainMVFabric fabric;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link Section} class.
     *
     * @see SectionService
     */
    private final SectionService sectionService;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param fabric         a implementation of the {@link AdminMVFabric}
     *                       interface.
     * @param sectionService a implementation of the {@link SectionService}
     *                       interface.
     * @see AdminMVFabric
     * @see SectionService
     * @see PhotoService
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public AdminSectionController(
            final AdminMVFabric fabric,
            final SectionService sectionService
    ) {
        this.fabric = new CacheMVFabricImpl(fabric);
        this.sectionService = sectionService;
    }

    /**
     * Returns the page to add a new section.
     * Request mapping: /admin/section/new
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     * @see Section
     */
    @RequestMapping(
            value = "/new",
            method = RequestMethod.GET
    )
    public ModelAndView newSection() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.setViewName("admin/section/new_page");
        return modelAndView;
    }

    /**
     * Adds new article and redirects by url /admin/section/{url}.
     * Request mapping: /admin/section/add
     * Method: POST
     *
     * @param title        a title of the new section.
     * @param description  a description of the new section.
     * @param keywords     a keywords of the new section.
     * @param photoFile    a file of main photo to the new section.
     * @param isValid      a validated of the new section.
     * @param modelAndView a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     * @see Section
     * @see Photo
     */
    @RequestMapping(
            value = "/add",
            method = RequestMethod.POST
    )
    public ModelAndView addSection(
            @RequestParam(value = "title") final String title,
            @RequestParam(value = "description") final String description,
            @RequestParam(value = "keywords") final String keywords,
            @RequestParam(value = "main_photo") final MultipartFile photoFile,
            @RequestParam(value = "is_valid") final boolean isValid,
            final ModelAndView modelAndView
    ) {
        final Section section = this.sectionService.initAndAdd(
                title, description, keywords,
                photoFile, isValid
        );
        Cache.removeAll("All Sections");
        modelAndView.setViewName(
                "redirect:/admin/section/" + section.getUrl()
        );
        return modelAndView;
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception sender:
     * "GET method in "/admin/section/add" is not supported!"
     * Request mapping: /admin/section/add
     * Method: POST
     *
     * @throws IllegalMappingException thrown when an error occurs reading
     *                                 the mapping between object and datastore.
     */
    @RequestMapping(
            value = "/add",
            method = RequestMethod.GET
    )
    public void addSection() throws IllegalMappingException {
        throw new IllegalMappingException(
                "GET method in \"/admin/section/add\" is not supported!"
        );
    }

    /**
     * Returns the page to edit the section with url.
     * Request mapping: /admin/section/edit/{url}
     * Method: GET
     *
     * @param url a url of the article to edit.
     * @return The ready object of class ModelAndView.
     * @see Section
     */
    @RequestMapping(
            value = "/edit/{url}",
            method = RequestMethod.GET
    )
    public ModelAndView editSection(@PathVariable("url") final String url) {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject(
                "section",
                this.sectionService.getByUrl(url, false)
        );
        Cache.removeAll("All Sections");
        modelAndView.setViewName("admin/section/edit_page");
        return modelAndView;
    }

    /**
     * Updates and save the section with url and redirects
     * by url /admin/section/{url}.
     * Request mapping: /admin/section/update
     * Method: POST
     *
     * @param url          a url of the section to update.
     * @param title        a new title to the section.
     * @param description  a new title to the section.
     * @param keywords     a new title to the section.
     * @param photoFile    a file of photo to the section.
     * @param photoAction  a action on the photo.
     * @param isValid      a validated of the section.
     * @param modelAndView a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     * @see Section
     * @see Photo
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    public ModelAndView updateSection(
            @RequestParam(value = "url") final String url,
            @RequestParam(value = "title") final String title,
            @RequestParam(value = "description") final String description,
            @RequestParam(value = "keywords") final String keywords,
            @RequestParam(value = "main_photo") final MultipartFile photoFile,
            @RequestParam(value = "photo_action") final String photoAction,
            @RequestParam(value = "is_valid") final boolean isValid,
            final ModelAndView modelAndView
    ) {
        final Section section = this.sectionService.initAndUpdate(
                url, title, description, keywords,
                photoFile, photoAction, isValid
        );
        Cache.removeAll("All Sections", section.getUrl());
        modelAndView.setViewName(
                "redirect:/admin/section/" + section.getUrl()
        );
        return modelAndView;
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception sender:
     * "GET method in "/admin/section/update" is not supported!"
     * Request mapping: /admin/section/update
     * Method: POST
     *
     * @throws IllegalMappingException thrown when an error occurs reading
     *                                 the mapping between object and datastore.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.GET
    )
    public void updateSection() throws IllegalMappingException {
        throw new IllegalMappingException(
                "GET method in \"/admin/section/update\" is not supported!"
        );
    }

    /**
     * Removes section with url and redirects by url /admin/.
     * Request mapping: /admin/section/delete/{url}
     * Method: GET
     *
     * @param url          a url of the section to remove.
     * @param modelAndView a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     * @see Section
     */
    @RequestMapping(
            value = "/delete/{url}",
            method = RequestMethod.GET
    )
    public ModelAndView deleteSection(
            @PathVariable("url") final String url,
            final ModelAndView modelAndView
    ) {
        this.sectionService.removeByUrl(url);
        modelAndView.setViewName("redirect:/admin/");
        Cache.removeAll("All Sections", url);
        return modelAndView;
    }

    /**
     * Removes all sections and redirects by url /admin/.
     * Request mapping: /admin/section/delete/all
     * Method: GET
     *
     * @param modelAndView a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     * @see Section
     */
    @RequestMapping(
            value = "/delete/all",
            method = RequestMethod.GET
    )
    public ModelAndView deleteAllSections(final ModelAndView modelAndView) {
        this.sectionService.removeAll();
        modelAndView.setViewName("redirect:/admin/");
        Cache.removeAll("Section", "Home");
        return modelAndView;
    }
}
