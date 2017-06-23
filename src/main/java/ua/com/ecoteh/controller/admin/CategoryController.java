package ua.com.ecoteh.controller.admin;

import com.googlecode.htmlcompressor.compressor.Compressor;
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
import ua.com.ecoteh.entity.category.Category;
import ua.com.ecoteh.entity.category.CategoryBuilder;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.file.FileBuilder;
import ua.com.ecoteh.exception.ExceptionMessage;
import ua.com.ecoteh.service.data.CategoryService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;
import ua.com.ecoteh.util.cache.Cache;
import ua.com.ecoteh.util.compressor.HtmlCompressor;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of methods for working with
 * objects of the {@link Category} class or subclasses for admins.
 * Class methods create and return modelsAndView, depending on the request.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
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
public final class CategoryController {

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
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param fabric          the implementation of the {@link MainMVFabric} interface.
     * @param categoryService the implementation of the {@link CategoryService} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public CategoryController(
            @Qualifier("cacheMVFabricImpl") final MainMVFabric fabric,
            final CategoryService categoryService
    ) {
        this.fabric = fabric;
        this.categoryService = categoryService;
    }

    /**
     * Returns the page to add a new category.
     * Request mapping: /admin/category/new
     * Method: GET
     *
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView getNewCategoryPage() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.setViewName("category/add");
        return modelAndView;
    }

    /**
     * Adds a new category with the incoming parameters
     * and redirects by the "/category/{url}" URL,
     * where {url} is a URL of a saving category.
     * Request mapping: /admin/category/add
     * Method: POST
     *
     * @param title         the title of the a category.
     * @param description   the description of a new category.
     * @param keywords      the keywords of the a category.
     * @param multipartLogo the file of photo to a new category.
     * @param validated     the validated of a new category.
     * @return The redirect string to the "/category/{url}" URL,
     * where {url} is a URL of a saving category.
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCategory(
            @RequestParam(value = "title", defaultValue = "") final String title,
            @RequestParam(value = "text", defaultValue = "") final String description,
            @RequestParam(value = "keywords", defaultValue = "") final String keywords,
            @RequestParam(value = "logo") final MultipartFile multipartLogo,
            @RequestParam(value = "is_valid", defaultValue = "false") final boolean validated
    ) {
        final Compressor compressor = new HtmlCompressor();
        final CategoryBuilder categoryBuilder = Category.getBuilder();
        categoryBuilder.addTitle(title).addKeywords(keywords).addValidated(validated)
                .addDescription(compressor.compress(description));

        final FileBuilder fileBuilder = File.getBuilder();
        fileBuilder.addTitle(title).addMultipartFile(multipartLogo).isValid();
        final File logo = fileBuilder.build();
        categoryBuilder.addLogo(logo);

        final Category category = categoryBuilder.build();
        final Category savingCategory = this.categoryService.add(category);
        Cache.clear();
        return getViewName(savingCategory);
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
        final String message = String.format(
                ExceptionMessage.GET_METHOD_NOT_SUPPORTED_MESSAGE,
                "/admin/category/add"
        );
        throw new IllegalMappingException(message);
    }

    /**
     * Returns a page to edit a category with the incoming URL.
     * Request mapping: /admin/category/edit/{url},
     * where {url} is a URL of a category to edit.
     * Method: GET
     *
     * @param url the URL of a category to edit.
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(value = "/edit/{url}", method = RequestMethod.GET)
    public ModelAndView editCategory(@PathVariable("url") final String url) {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("category", this.categoryService.getByUrl(url, false));
        modelAndView.setViewName("category/edit");
        return modelAndView;
    }

    /**
     * Updates and save a category with the incoming URL
     * and redirects by the "/category/{url}" URL,
     * where {url} is a URL of a saving category.
     * Request mapping: /admin/category/update
     * Method: POST
     *
     * @param url           the URL of a category to update.
     * @param title         the new title to a category.
     * @param description   the new description to a category.
     * @param keywords      the new description to a category.
     * @param multipartLogo the new file of photo to a category.
     * @param validated     the validated of a category.
     * @return The redirect string to the "/category/{url}" URL,
     * where {url} is a URL of a saving category.
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateCategory(
            @RequestParam(value = "url", defaultValue = "") final String url,
            @RequestParam(value = "title", defaultValue = "") final String title,
            @RequestParam(value = "text", defaultValue = "") final String description,
            @RequestParam(value = "keywords", defaultValue = "") final String keywords,
            @RequestParam(value = "logo") final MultipartFile multipartLogo,
            @RequestParam(value = "is_valid", defaultValue = "false") final boolean validated
    ) {
        final Compressor compressor = new HtmlCompressor();
        final CategoryBuilder categoryBuilder = Category.getBuilder();
        categoryBuilder.addUrl(url).addTitle(title).addKeywords(keywords).addValidated(validated)
                .addDescription(compressor.compress(description));

        final FileBuilder fileBuilder = File.getBuilder();
        fileBuilder.addTitle(title).addMultipartFile(multipartLogo).isValid();
        final File logo = fileBuilder.build();
        categoryBuilder.addLogo(logo);

        final Category category = categoryBuilder.build();
        final Category updatedCategory = this.categoryService.update(category);
        Cache.clear();
        return getViewName(updatedCategory);
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
        final String message = String.format(
                ExceptionMessage.GET_METHOD_NOT_SUPPORTED_MESSAGE,
                "/admin/category/update"
        );
        throw new IllegalMappingException(message);
    }

    /**
     * Removes a category with the incoming URL
     * and redirects by the "/" URL.
     * Request mapping: /admin/category/delete/{url},
     * where {url} is a URL of a category to remove.
     * Method: GET
     *
     * @param url the URL of a category to remove.
     * @return The redirect string to the "/" URL.
     */
    @RequestMapping(value = "/delete/{url}", method = RequestMethod.GET)
    public String deleteCategoryByUrl(@PathVariable("url") final String url) {
        this.categoryService.removeByUrl(url);
        Cache.clear();
        return "redirect:/";
    }

    /**
     * Removes an all categories and redirects by the "/" URL.
     * Request mapping: /admin/category/delete/{url}
     * Method: GET
     *
     * @return The redirect string to the "/" URL.
     */
    @RequestMapping(value = "/delete/all", method = RequestMethod.GET)
    public String deleteAllCategories() {
        this.categoryService.removeAll();
        Cache.clear();
        return "redirect:/";
    }

    /**
     * Returns a view name for the category.
     *
     * @param category the category to get view name.
     * @return The view name.
     */
    private String getViewName(final Category category) {
        final String viewName;
        if (isNotNull(category)) {
            viewName = "redirect:/category/" + category.getUrl();
        } else {
            viewName = "redirect:/category/all";
        }
        return viewName;
    }
}
