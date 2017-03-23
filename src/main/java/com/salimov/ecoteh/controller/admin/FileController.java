package com.salimov.ecoteh.controller.admin;

import com.salimov.ecoteh.entity.File;
import com.salimov.ecoteh.enums.FileType;
import com.salimov.ecoteh.service.data.interfaces.FileService;
import com.salimov.ecoteh.service.fabrica.impl.CacheMVFabricImpl;
import com.salimov.ecoteh.service.fabrica.interfaces.MainMVFabric;
import com.salimov.ecoteh.service.fabrica.interfaces.CacheMVFabric;
import org.apache.log4j.Logger;
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

import javax.servlet.http.HttpServletRequest;

/**
 * The class implements a set of methods for working with
 * objects of {@link File} class for admins.
 * Class methods create and return modelsAndView, depending on the request.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Controller
@RequestMapping(
        value = {
                "/admin/file",
                "/admin/files"
        }
)
@ComponentScan(basePackages = "com.salimov.ecoteh.service")
@SuppressWarnings("SpringMVCViewInspection")
public class FileController {

    /**
     * The object for logging information.
     */
    private static final Logger LOGGER = Logger.getLogger(FileController.class);

    /**
     * The implementation of the interface provides a set of standard
     * methods for creates and returns the main modelAndViews for admins.
     */
    private final CacheMVFabric fabric;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link File} class.
     */
    private final FileService fileService;

    /**
     * Constructor.
     *
     * @param fabric      a implementation of the {@link MainMVFabric}
     *                    interface.
     * @param fileService a implementation of the {@link FileService}
     *                    interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public FileController(
            final MainMVFabric fabric,
            final FileService fileService
    ) {
        this.fabric = new CacheMVFabricImpl(fabric);
        this.fileService = fileService;
    }

    /**
     * Returns modelAndView with information about page
     * with all files sorting by title.
     * Request mapping: /admin/file/all
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = {"", "/", "/all"},
            method = RequestMethod.GET
    )
    public ModelAndView getAllFilePage() {
        return getAllFileSortByTitle(false);
    }

    /**
     * Returns the page to add a new file.
     * Request mapping: /admin/file/new
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/new",
            method = RequestMethod.GET
    )
    public ModelAndView getNewFilePage() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.setViewName("admin/file/add");
        return modelAndView;
    }

    /**
     * Adds new file and redirects by URL /admin/file/all.
     * Request mapping: /admin/file/add
     * Method: POST
     *
     * @param title         a title of the new file.
     * @param type          a type of the new file.
     * @param multipartFile a multipart file of the new file.
     * @param modelAndView  a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/add",
            method = RequestMethod.POST
    )
    public ModelAndView addFile(
            @RequestParam(value = "title") final String title,
            @RequestParam(value = "type") final String type,
            @RequestParam(value = "file") final MultipartFile multipartFile,
            final ModelAndView modelAndView
    ) {
        this.fileService.add(title, getType(type), multipartFile);
        modelAndView.setViewName("redirect:/admin/file/all");
        return modelAndView;
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception message:
     * "GET method in "/admin/file/add" is not supported!"
     * Request mapping: /admin/file/add
     * Method: POST
     *
     * @throws IllegalMappingException thrown when an error occurs reading
     *                                 the mapping between object and datastore.
     */
    @RequestMapping(
            value = "/add",
            method = RequestMethod.GET
    )
    public void addFile() throws IllegalMappingException {
        throw new IllegalMappingException("GET method in \"/admin/file/add\" is not supported!");
    }

    /**
     * Returns the page to edit the article with id.
     * Request mapping: /admin/file/edit/{id}
     * Method: GET
     *
     * @param id a id of the article to edit.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/edit/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView editFilePage(@PathVariable("id") final Long id) {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("file", this.fileService.get(id));
        modelAndView.setViewName("admin/file/edit");
        return modelAndView;
    }

    /**
     * Updates and save the file with id
     * and redirects by URL "/admin/file/all".
     * Request mapping: /admin/file/update
     * Method: POST
     *
     * @param id            a id of the file to update.
     * @param title         a new title to the file.
     * @param type          a type of the new file.
     * @param multipartFile a multipart file of the new file.
     * @param modelAndView  a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    public ModelAndView updateFile(
            @RequestParam(value = "id") final Long id,
            @RequestParam(value = "title") final String title,
            @RequestParam(value = "type") final String type,
            @RequestParam(value = "file") final MultipartFile multipartFile,
            final ModelAndView modelAndView
    ) {
        this.fileService.update(id, title, getType(type), multipartFile);
        modelAndView.setViewName("redirect:/admin/file/all");
        return modelAndView;
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception message:
     * "GET method in "/admin/file/update" is not supported!"
     * Request mapping: /admin/file/update
     * Method: GET
     *
     * @throws IllegalMappingException thrown when an error occurs reading
     *                                 the mapping between object and datastore.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.GET
    )
    public void updateFile() throws IllegalMappingException {
        throw new IllegalMappingException("GET method in \"/admin/file/update\" is not supported!");
    }

    /**
     * Removes file with url and redirects by URL "/admin/file/all".
     * Request mapping: /admin/file/delete/{id}
     * Method: GET
     *
     * @param id           a id of the file to remove.
     * @param modelAndView a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/delete/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView deleteFileById(
            @PathVariable("id") final Long id,
            final ModelAndView modelAndView
    ) {
        this.fileService.remove(id);
        modelAndView.setViewName("redirect:/admin/file/all");
        return modelAndView;
    }

    /**
     * Removes all files and redirects by URL "/admin/file/all".
     * Request mapping: /admin/file/delete/all
     * Method: GET
     *
     * @param modelAndView a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/delete/all",
            method = RequestMethod.GET
    )
    public ModelAndView deleteAllFiles(
            final ModelAndView modelAndView
    ) {
        this.fileService.removeAll();
        modelAndView.setViewName("redirect:/admin/file/all");
        return modelAndView;
    }

    /**
     * Returns modelAndView with information about page
     * with all files sorting by title.
     * Request mapping: /admin/file/all/sort
     * Method: GET
     *
     * @param request a implementation of the interface to provide
     *                request information for HTTP servlets.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/all/sort",
            method = RequestMethod.GET
    )
    public ModelAndView getAllFileSortByTitle(
            final HttpServletRequest request
    ) {
        return getAllFileSortByTitle(
                Boolean.parseBoolean(
                        request.getParameter("revers")
                )
        );
    }

    /**
     * Returns modelAndView with information about page
     * with all files sorting by title.
     *
     * @param revers a sorting direction, {@code true} or {@code false}.
     * @return The ready object of class ModelAndView.
     */
    private ModelAndView getAllFileSortByTitle(final boolean revers) {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject(
                "files",
                this.fileService.sortByTitle(
                        this.fileService.getAll(false), revers
                )
        );
        modelAndView.addObject("revers", !revers);
        modelAndView.setViewName("admin/file/all");
        return modelAndView;
    }

    /**
     * Returns a file type by name.
     *
     * @param name a type of the new file.
     * @return The file type.
     */
    private static FileType getType(final String name) {
        FileType fileType;
        try {
            fileType = FileType.valueOf(name);
        } catch (IllegalArgumentException ex) {
            LOGGER.error(ex.getMessage(), ex);
            fileType = FileType.OTHER;
        }
        return fileType;
    }
}
