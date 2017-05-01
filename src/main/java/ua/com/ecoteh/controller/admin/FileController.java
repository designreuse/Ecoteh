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
import ua.com.ecoteh.entity.File;
import ua.com.ecoteh.enums.FileType;
import ua.com.ecoteh.service.data.FileService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;

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
@ComponentScan(
        basePackages = {
                "ua.com.ecoteh.service.fabrica",
                "ua.com.ecoteh.service.data"
        }
)
@SuppressWarnings("SpringMVCViewInspection")
public class FileController {

    /**
     *
     */
    private final static String GET_METHOD_NOT_SUPPORTED_MESSAGE =
            "GET method in \"%s\" is not supported!";

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
     * @param fabric      a implementation of the {@link MainMVFabric}
     *                    interface.
     * @param fileService a implementation of the {@link FileService}
     *                    interface.
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
     * Returns modelAndView with information about page
     * with all files sorting by title.
     * Request mapping: /admin/file/all
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
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
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/add",
            method = RequestMethod.POST
    )
    public String addFile(
            @RequestParam(value = "title") final String title,
            @RequestParam(value = "type") final String type,
            @RequestParam(value = "file") final MultipartFile multipartFile
    ) {
        this.fileService.add(title, getType(type), multipartFile);
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
        throw new IllegalMappingException(
                String.format(GET_METHOD_NOT_SUPPORTED_MESSAGE, "/admin/file/add")
        );
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
    public ModelAndView editFilePage(@PathVariable("id") final long id) {
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
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    public String updateFile(
            @RequestParam(value = "id") final long id,
            @RequestParam(value = "title") final String title,
            @RequestParam(value = "type") final String type,
            @RequestParam(value = "file") final MultipartFile multipartFile
    ) {
        this.fileService.update(id, title, getType(type), multipartFile);
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
        throw new IllegalMappingException(
                String.format(GET_METHOD_NOT_SUPPORTED_MESSAGE, "/admin/file/update")
        );
    }

    /**
     * Removes file with url and redirects by URL "/admin/file/all".
     * Request mapping: /admin/file/delete/{id}
     * Method: GET
     *
     * @param id a id of the file to remove.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/delete/{id}",
            method = RequestMethod.GET
    )
    public String deleteFileById(@PathVariable("id") final long id) {
        this.fileService.remove(id);
        return "redirect:/admin/file/all";
    }

    /**
     * Removes all files and redirects by URL "/admin/file/all".
     * Request mapping: /admin/file/delete/all
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/delete/all",
            method = RequestMethod.GET
    )
    public String deleteAllFiles() {
        this.fileService.removeAll();
        return "redirect:/admin/file/all";
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
     * @param revers a sorting direction, true or false.
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
