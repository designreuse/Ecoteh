package ua.com.ecoteh.controller.admin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.entity.file.*;
import ua.com.ecoteh.exception.ExceptionMessage;
import ua.com.ecoteh.service.data.FileService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;
import ua.com.ecoteh.util.cache.Cache;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * The class implements a set of methods for working with
 * objects of the {@link File} class for admins.
 * Class methods create and return modelsAndView, depending on the request.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
@Controller
@RequestMapping(
        value = {
                "/admin/file",
                "/admin/files"
        }
)
@ComponentScan(
        basePackages = {
                "ua.com.ecoteh.service.fabrica",
                "ua.com.ecoteh.service.data"
        }
)
@SuppressWarnings("SpringMVCViewInspection")
public final class FileController {

    /**
     * The object for logging information.
     */
    private static final Logger LOGGER = Logger.getLogger(FileController.class);

    /**
     * The implementation of the interface provides a set of standard
     * methods for creates and returns the main modelAndViews for admins.
     */
    private final MainMVFabric fabric;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link File} class.
     */
    private final FileService fileService;

    /**
     * Constructor.
     *
     * @param fabric      the implementation of the {@link MainMVFabric} interface.
     * @param fileService the implementation of the {@link FileService} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public FileController(
            @Qualifier("cacheMVFabricImpl") final MainMVFabric fabric,
            final FileService fileService
    ) {
        this.fabric = fabric;
        this.fileService = fileService;
    }

    /**
     * Returns a page with an all files sorting by title.
     * Request mapping: /admin/file, /admin/file/all
     * Method: GET
     *
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = { "", "/", "/all" },
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
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = "/new",
            method = RequestMethod.GET
    )
    public ModelAndView getNewFilePage() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.setViewName("file/add");
        return modelAndView;
    }

    /**
     * Adds a new file with the incoming parameters
     * and redirects by the "/admin/file/all" URL.
     * Request mapping: /admin/file/add
     * Method: POST
     *
     * @param title         the title of a new file.
     * @param type          the type of a new file.
     * @param multipartFile the multipart file of a new file.
     * @return The redirect string to the "/admin/file/all" URL.
     */
    @RequestMapping(
            value = "/add",
            method = RequestMethod.POST
    )
    public String addFile(
            @RequestParam(value = "title", defaultValue = "") final String title,
            @RequestParam(value = "type", defaultValue = "") final String type,
            @RequestParam(value = "file", defaultValue = "") final MultipartFile multipartFile
    ) {
        final FileBuilder builder = File.getBuilder();
        builder.addTitle(title).addType(type)
                .addMultipartFile(multipartFile).isValid();
        final File file = builder.build();
        this.fileService.add(file);
        return "redirect:/admin/file/all";
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
        final String message = String.format(
                ExceptionMessage.GET_METHOD_NOT_SUPPORTED_MESSAGE,
                "/admin/file/add"
        );
        throw new IllegalMappingException(message);
    }

    /**
     * Returns a page to edit a file with the incoming id.
     * Request mapping: /admin/file/edit/{id},
     * where {id} is a id of a file to edit.
     * Method: GET
     *
     * @param id the id of a file to edit.
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = "/edit/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView editFilePage(@PathVariable("id") final long id) {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("file", this.fileService.get(id));
        modelAndView.setViewName("file/edit");
        return modelAndView;
    }

    /**
     * Updates and save a file with the incoming id
     * and redirects by the "/admin/file/all" URL.
     * Request mapping: /admin/file/update
     * Method: POST
     *
     * @param id            the id of a file to update.
     * @param title         the new title to a file.
     * @param type          the type of a new file.
     * @param multipartFile the multipart file of a new file.
     * @return The redirect string to the "/admin/file/all" URL.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    public String updateFile(
            @RequestParam(value = "id", defaultValue = "0") final long id,
            @RequestParam(value = "title", defaultValue = "") final String title,
            @RequestParam(value = "type", defaultValue = "") final String type,
            @RequestParam(value = "file", defaultValue = "") final MultipartFile multipartFile
    ) {
        final FileBuilder builder = File.getBuilder();
        builder.addId(id).addTitle(title).addType(type)
                .addMultipartFile(multipartFile).isValid();
        final File file = builder.build();
        this.fileService.update(file);
        return "redirect:/admin/file/all";
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
        final String message = String.format(
                ExceptionMessage.GET_METHOD_NOT_SUPPORTED_MESSAGE,
                "/admin/file/update"
        );
        throw new IllegalMappingException(message);
    }

    /**
     * Removes a file with the incoming id
     * and redirects by the "/admin/file/all" URL.
     * Request mapping: /admin/file/delete/{id},
     * where {id} is a id of a file to remove.
     * Method: GET
     *
     * @param id the id of a file to remove.
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = "/delete/{id}",
            method = RequestMethod.GET
    )
    public String deleteFileById(@PathVariable("id") final long id) {
        final boolean result = this.fileService.remove(id);
        if (result) {
            Cache.clear();
        }
        return "redirect:/admin/file/all";
    }

    /**
     * Removes an all files and redirects
     * by the "/admin/file/all" URL.
     * Request mapping: /admin/article/delete/all
     * Method: GET
     *
     * @return The redirect string to the "/admin/file/all" URL.
     */
    @RequestMapping(
            value = "/delete/all",
            method = RequestMethod.GET
    )
    public String deleteAllFiles() {
        this.fileService.removeAll();
        Cache.clear();
        return "redirect:/admin/file/all";
    }

    /**
     * Returns a page with an all files sorting by title.
     * Request mapping: /admin/file/all/sort
     * Method: GET
     *
     * @param request the implementation of the interface to provide
     *                request information for HTTP servlets.
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = "/all/sort",
            method = RequestMethod.GET
    )
    public ModelAndView getAllFileSortByTitle(final HttpServletRequest request) {
        final boolean revers = Boolean.parseBoolean(request.getParameter("revers"));
        return getAllFileSortByTitle(revers);
    }

    /**
     * Returns a page with an all files sorting by title.
     *
     * @param revers the sorting direction, true or false.
     * @return The ready object of the ModelAndView class.
     */
    private ModelAndView getAllFileSortByTitle(final boolean revers) {
        final Collection<File> files = this.fileService.getAll(false);
        final Collection<File> sortedByTitleFiles = this.fileService.sortByTitle(files, revers);
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("files", sortedByTitleFiles);
        modelAndView.addObject("revers", !revers);
        modelAndView.setViewName("file/all");
        return modelAndView;
    }
}
