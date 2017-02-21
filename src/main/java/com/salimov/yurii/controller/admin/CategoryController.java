package com.salimov.yurii.controller.admin;

import com.salimov.yurii.entity.Category;
import com.salimov.yurii.entity.File;
import com.salimov.yurii.service.data.interfaces.CategoryService;
import com.salimov.yurii.service.data.interfaces.FileService;
import com.salimov.yurii.service.fabrica.impl.CacheMVFabricImpl;
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
import org.springframework.web.servlet.ModelAndView;

/**
 * The class implements a set of methods for working with
 * main ModelAndView objects and object of {@link Category} class
 * or subclasses for admins. Class methods create and return modelsAndView,
 * depending on the request.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see Category
 * @see CategoryService
 * @see FileService
 * @see MainMVFabric
 */
@Controller
@RequestMapping(value = "/admin/category")
@ComponentScan(basePackages = "com.salimov.yurii.service")
@SuppressWarnings("SpringMVCViewInspection")
public class CategoryController {

    /**
     * The implementation of the interface provides a set of standard methods
     * for creates and returns the main modelAndViews.
     *
     * @see MainMVFabric
     */
    private final MainMVFabric fabric;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link Category} class.
     *
     * @see CategoryService
     */
    private final CategoryService categoryService;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param fabric          a implementation of the {@link MainMVFabric} interface.
     * @param categoryService a implementation of the {@link CategoryService} interface.
     * @see MainMVFabric
     * @see CategoryService
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public CategoryController(
            final MainMVFabric fabric,
            final CategoryService categoryService
    ) {
        this.fabric = new CacheMVFabricImpl(fabric);
        this.categoryService = categoryService;
    }

    /**
     * Returns the page to add a new category.
     * Request mapping: /admin/category/new
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     * @see Category
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView getNewCategoryPage() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.setViewName("admin/category/new_page");
        return modelAndView;
    }

    /**
     * Adds new category and redirects by url /admin/category/{url}.
     * Request mapping: /admin/category/add
     * Method: POST
     *
     * @param title        a title of the new category.
     * @param description  a title of the new category.
     * @param keywords     a title of the new category.
     * @param photoUrl     a file of photo to the new category.
     * @param isValid      a validated of the new category.
     * @param modelAndView a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     * @see Category
     * @see File
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addCategory(
            @RequestParam(value = "title") final String title,
            @RequestParam(value = "text") final String description,
            @RequestParam(value = "keywords") final String keywords,
            @RequestParam(value = "photo") final String photoUrl,
            @RequestParam(value = "is_valid") final boolean isValid,
            final ModelAndView modelAndView
    ) {
        final Category category = new Category(title, description, keywords);
        category.setPhotoUrl(photoUrl);
        category.setValidated(isValid);
        this.categoryService.add(category);
        Cache.clear();
        modelAndView.setViewName("redirect:/category/" + category.getUrl());
        return modelAndView;
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception sender:
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
                "GET method in \"/admin/category/add\" is not supported!"
        );
    }

    /**
     * Returns the page to edit the category with url.
     * Request mapping: /admin/category/edit/{url}
     * Method: GET
     *
     * @param url a url of the category to edit.
     * @return The ready object of class ModelAndView.
     * @see Category
     */
    @RequestMapping(value = "/edit/{url}", method = RequestMethod.GET)
    public ModelAndView editCategory(@PathVariable("url") final String url) {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("category", this.categoryService.getByUrl(url, false));
        modelAndView.setViewName("admin/category/edit_page");
        return modelAndView;
    }

    /**
     * Updates and save the category with url and redirects
     * by url /admin/category/{url}.
     * Request mapping: /admin/category/update
     * Method: POST
     *
     * @param url          a url of the category to update.
     * @param title        a new title to the category.
     * @param description  a new description to the category.
     * @param keywords     a new description to the category.
     * @param photoUrl     a file of photo to the new category.
     * @param isValid      a validated of the category.
     * @param modelAndView a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     * @see Category
     * @see File
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updateCategory(
            @RequestParam(value = "url") final String url,
            @RequestParam(value = "title") final String title,
            @RequestParam(value = "text") final String description,
            @RequestParam(value = "keywords") final String keywords,
            @RequestParam(value = "photo") final String photoUrl,
            @RequestParam(value = "is_valid") final boolean isValid,
            final ModelAndView modelAndView
    ) {
        final Category category = new Category(title, description, keywords);
        category.setPhotoUrl(photoUrl);
        category.setValidated(isValid);
        this.categoryService.update(url, category);
        Cache.clear();
        modelAndView.setViewName("redirect:/category/" + category.getUrl());
        return modelAndView;
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception sender:
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
                "GET method in \"/admin/category/update\" is not supported!"
        );
    }

    /**
     * Removes category with url and redirects by url /admin/.
     * Request mapping: /admin/category/delete/{url}
     * Method: GET
     *
     * @param url          a url of the article to remove.
     * @param modelAndView a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     * @see Category
     */
    @RequestMapping(value = "/delete/{url}", method = RequestMethod.GET)
    public ModelAndView deleteCategoryByUrl(
            @PathVariable("url") final String url,
            final ModelAndView modelAndView
    ) {
        this.categoryService.removeByUrl(url);
        Cache.clear();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    /**
     * Removes all categories and redirects by url /admin/.
     * Request mapping: /admin/category/delete/{url}
     * Method: GET
     *
     * @param modelAndView a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     * @see Category
     */
    @RequestMapping(value = "/delete/all", method = RequestMethod.GET)
    public ModelAndView deleteAllCategories(final ModelAndView modelAndView) {
        this.categoryService.removeAll();
        modelAndView.setViewName("redirect:/");
        Cache.clear();
        return modelAndView;
    }
}
