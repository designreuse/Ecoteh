package ua.com.ecoteh.controller.admin;

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
import ua.com.ecoteh.entity.Category;
import ua.com.ecoteh.entity.File;
import ua.com.ecoteh.service.data.CategoryService;
import ua.com.ecoteh.service.data.FileService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;
import ua.com.ecoteh.util.cache.Cache;
import ua.com.ecoteh.util.compressor.HtmlCompressor;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * The class implements a set of methods for working with
 * objects of {@link Category} class or subclasses for admins.
 * Class methods create and return modelsAndView, depending on the request.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Controller
@RequestMapping(
        value = {
                "/admin/category",
                "/admin/categories"
        }
)
@ComponentScan(
        basePackages = {
                "ua.com.ecoteh.service.fabrica",
                "ua.com.ecoteh.service.data"
        }
)
@SuppressWarnings("SpringMVCViewInspection")
public class CategoryController {

    /**
     *
     */
    private final static String GET_METHOD_NOT_SUPPORTED_MESSAGE =
            "GET method in \"%s\" is not supported!";

    /**
     * The implementation of the interface provides a set of standard methods
     * for creates and returns the main modelAndViews.
     */
    private final MainMVFabric fabric;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link Category} class.
     */
    private final CategoryService categoryService;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link File} class.
     */
    private final FileService fileService;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param fabric          a implementation of the {@link MainMVFabric} interface.
     * @param categoryService a implementation of the {@link CategoryService} interface.
     * @param fileService     a implementation of the {@link CategoryService} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public CategoryController(
            @Qualifier("cacheMVFabricImpl") final MainMVFabric fabric,
            final CategoryService categoryService,
            final FileService fileService
    ) {
        this.fabric = fabric;
        this.categoryService = categoryService;
        this.fileService = fileService;
    }

    /**
     * Returns the page to add a new category.
     * Request mapping: /admin/category/new
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView getNewCategoryPage() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.setViewName("admin/category/add");
        return modelAndView;
    }

    /**
     * Adds new category and redirects by URL /admin/category/{url}.
     * Request mapping: /admin/category/add
     * Method: POST
     *
     * @param title         a title of the new category.
     * @param description   a title of the new category.
     * @param keywords      a title of the new category.
     * @param multipartLogo a file of photo to the new category.
     * @param isValid       a validated of the new category.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCategory(
            @RequestParam(value = "title") final String title,
            @RequestParam(value = "text") final String description,
            @RequestParam(value = "keywords") final String keywords,
            @RequestParam(value = "logo") final MultipartFile multipartLogo,
            @RequestParam(value = "is_valid") final boolean isValid
    ) {
        final Category category = new Category(
                title,
                new HtmlCompressor().compress(description),
                keywords
        );
        category.setValidated(isValid);
        if (isNotEmpty(multipartLogo)) {
            category.setLogo(this.fileService.add(category.getTitle(), multipartLogo));
        }
        this.categoryService.add(category);
        Cache.clear();
        return "redirect:/category/" + category.getUrl();
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception message:
     * "GET method in "/admin/category/add" is not supported!"
     * Request mapping: /admin/category/add
     * Method: POST
     *
     * @throws IllegalMappingException thrown when an error occurs reading
     *                                 the mapping between object and datastore.
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public void addCategory() throws IllegalMappingException {
        throw new IllegalMappingException(
                String.format(GET_METHOD_NOT_SUPPORTED_MESSAGE, "/admin/category/add")
        );
    }

    /**
     * Returns the page to edit the category with url.
     * Request mapping: /admin/category/edit/{url}
     * Method: GET
     *
     * @param url a URL of the category to edit.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(value = "/edit/{url}", method = RequestMethod.GET)
    public ModelAndView editCategory(@PathVariable("url") final String url) {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("category", this.categoryService.getByUrl(url, false));
        modelAndView.setViewName("admin/category/edit");
        return modelAndView;
    }

    /**
     * Updates and save the category with url and redirects
     * by URL /admin/category/{url}.
     * Request mapping: /admin/category/update
     * Method: POST
     *
     * @param url           a URL of the category to update.
     * @param title         a new title to the category.
     * @param description   a new description to the category.
     * @param keywords      a new description to the category.
     * @param multipartLogo a file of photo to the new category.
     * @param isValid       a validated of the category.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateCategory(
            @RequestParam(value = "url") final String url,
            @RequestParam(value = "title") final String title,
            @RequestParam(value = "text") final String description,
            @RequestParam(value = "keywords") final String keywords,
            @RequestParam(value = "logo") final MultipartFile multipartLogo,
            @RequestParam(value = "is_valid") final boolean isValid
    ) {
        final Category category = new Category(
                title,
                new HtmlCompressor().compress(description),
                keywords
        );
        category.setValidated(isValid);
        if (isNotEmpty(multipartLogo)) {
            category.setLogo(this.fileService.add(category.getTitle(), multipartLogo));
        }
        this.categoryService.update(url, category);
        Cache.clear();
        return "redirect:/category/" + category.getUrl();
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception message:
     * "GET method in "/admin/category/update" is not supported!"
     * Request mapping: /admin/category/update
     * Method: POST
     *
     * @throws IllegalMappingException thrown when an error occurs reading
     *                                 the mapping between object and datastore.
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public void updateCategory() throws IllegalMappingException {
        throw new IllegalMappingException(
                String.format(GET_METHOD_NOT_SUPPORTED_MESSAGE, "/admin/category/update")
        );
    }

    /**
     * Removes category with url and redirects by URL /admin/.
     * Request mapping: /admin/category/delete/{url}
     * Method: GET
     *
     * @param url          a URL of the article to remove.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(value = "/delete/{url}", method = RequestMethod.GET)
    public String deleteCategoryByUrl(@PathVariable("url") final String url) {
        this.categoryService.removeByUrl(url);
        Cache.clear();
        return "redirect:/";
    }

    /**
     * Removes all categories and redirects by URL /admin/.
     * Request mapping: /admin/category/delete/{url}
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(value = "/delete/all", method = RequestMethod.GET)
    public String deleteAllCategories() {
        this.categoryService.removeAll();
        Cache.clear();
        return "redirect:/";
    }
}
