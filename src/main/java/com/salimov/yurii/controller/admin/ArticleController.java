package com.salimov.yurii.controller.admin;

import com.salimov.yurii.entity.Article;
import com.salimov.yurii.entity.Category;
import com.salimov.yurii.entity.File;
import com.salimov.yurii.service.data.interfaces.ArticleService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class implements a set of methods for working
 * with main ModelAndView objects and object of {@link Article} class
 * or subclasses for admins. Class methods create and return modelsAndView,
 * depending on the request.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see Article
 * @see ArticleService
 * @see CategoryService
 * @see FileService
 * @see MainMVFabric
 */
@Controller
@RequestMapping(value = "/admin/article")
@ComponentScan(basePackages = "com.salimov.yurii.service")
@SuppressWarnings("SpringMVCViewInspection")
public class ArticleController {

    /**
     * The implementation of the interface provides a set of standard methods
     * for creates and returns the main modelAndViews.
     *
     * @see MainMVFabric
     */
    private final MainMVFabric fabric;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link Article} class.
     *
     * @see ArticleService
     */
    private final ArticleService articleService;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link Category} class.
     *
     * @see CategoryService
     */
    private final CategoryService categoryService;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link File} class.
     *
     * @see FileService
     */
    private final FileService fileService;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param fabric          a implementation of the {@link MainMVFabric} interface.
     * @param articleService  a implementation of the {@link ArticleService} interface.
     * @param categoryService a implementation of the {@link CategoryService} interface.
     * @see MainMVFabric
     * @see ArticleService
     * @see CategoryService
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public ArticleController(
            final MainMVFabric fabric,
            final ArticleService articleService,
            final CategoryService categoryService,
            final FileService fileService
    ) {
        this.fabric = new CacheMVFabricImpl(fabric);
        this.articleService = articleService;
        this.categoryService = categoryService;
        this.fileService = fileService;
    }

    /**
     * Returns the page to add a new article.
     * Request mapping: /admin/article/new
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     * @see Article
     */
    @RequestMapping(
            value = "/new",
            method = RequestMethod.GET
    )
    public ModelAndView getNewArticlePage() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("categories", this.categoryService.getAll(false));
        modelAndView.setViewName("admin/article/new_page");
        return modelAndView;
    }

    /**
     * Adds new article and redirects by url /admin/article/{url}.
     * Request mapping: /admin/article/add
     * Method: POST
     *
     * @param title         a title of the new article.
     * @param description   a description of the new article.
     * @param text          a text of the new article.
     * @param keywords      a keywords of the new article.
     * @param number        a number of the new article.
     * @param categoryUrl   a category url of the new article.
     * @param multipartLogo a file of photo to the new category.
     * @param isValid       a validated of the new article.
     * @param modelAndView  a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     * @see Article
     * @see Category
     * @see File
     */
    @RequestMapping(
            value = "/add",
            method = RequestMethod.POST
    )
    public ModelAndView addArticle(
            @RequestParam(value = "title") final String title,
            @RequestParam(value = "desc") final String description,
            @RequestParam(value = "text") final String text,
            @RequestParam(value = "keywords") final String keywords,
            @RequestParam(value = "number") final String number,
            @RequestParam(value = "category_url") final String categoryUrl,
            @RequestParam(value = "logo") final MultipartFile multipartLogo,
            @RequestParam(value = "is_valid") final boolean isValid,
            final ModelAndView modelAndView
    ) {
        final Category category = isNotBlank(categoryUrl) ?
                this.categoryService.getByUrl(categoryUrl, false) : null;
        final Article article = new Article(title, description, text, keywords, number);
        article.setValidated(isValid);
        article.setCategory(category);
        if ((multipartLogo != null) && !multipartLogo.isEmpty()) {
            article.setLogo(this.fileService.add(article.getTitle(), multipartLogo));
        }
        this.articleService.add(article);
        Cache.clear();
        modelAndView.setViewName(getViewName(article));
        return modelAndView;
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception sender:
     * "GET method in "/admin/article/add" is not supported!"
     * Request mapping: /admin/article/add
     * Method: GET
     *
     * @throws IllegalMappingException thrown when an error occurs reading
     *                                 the mapping between object and datastore.
     */
    @RequestMapping(
            value = "/add",
            method = RequestMethod.GET
    )
    public void addArticle() throws IllegalMappingException {
        throw new IllegalMappingException(
                "GET method in \"/admin/article/add\" is not supported!"
        );
    }

    /**
     * Returns the page to edit the article with url.
     * Request mapping: /admin/article/edit/{url}
     * Method: GET
     *
     * @param url a url of the article to edit.
     * @return The ready object of class ModelAndView.
     * @see Article
     */
    @RequestMapping(
            value = "/edit/{url}",
            method = RequestMethod.GET
    )
    public ModelAndView editArticle(@PathVariable("url") final String url) {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("article", this.articleService.getByUrl(url, false));
        modelAndView.addObject("categories", this.categoryService.getAll(false));
        modelAndView.setViewName("admin/article/edit_page");
        return modelAndView;
    }

    /**
     * Updates and save the article with url
     * and redirects by url /admin/article/{url}.
     * Request mapping: /admin/article/update
     * Method: POST
     *
     * @param url           a url of the article to update.
     * @param title         a new title to the article.
     * @param description   a new description to the article.
     * @param text          a new text to the article.
     * @param keywords      a new keywords to the article.
     * @param number        a new number to the article.
     * @param categoryUrl   a category url of the article.
     * @param multipartLogo a file of photo to the new category.
     * @param isValid       a validated of the article.
     * @param modelAndView  a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     * @see Article
     * @see Category
     * @see File
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    public ModelAndView updateArticle(
            @RequestParam(value = "url") final String url,
            @RequestParam(value = "title") final String title,
            @RequestParam(value = "desc") final String description,
            @RequestParam(value = "text") final String text,
            @RequestParam(value = "keywords") final String keywords,
            @RequestParam(value = "number") final String number,
            @RequestParam(value = "category_url") final String categoryUrl,
            @RequestParam(value = "logo") final MultipartFile multipartLogo,
            @RequestParam(value = "is_valid") final boolean isValid,
            final ModelAndView modelAndView
    ) {
        final Category category = isNotBlank(categoryUrl) ?
                this.categoryService.getByUrl(categoryUrl, false) : null;
        final Article article = new Article(title, description, text, keywords, number);
        article.setValidated(isValid);
        article.setCategory(category);
        if ((multipartLogo != null) && !multipartLogo.isEmpty()) {
            article.setLogo(this.fileService.add(article.getTitle(), multipartLogo));
        }
        this.articleService.update(url, article);
        modelAndView.setViewName(getViewName(article));
        Cache.clear();
        return modelAndView;
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception sender:
     * "GET method in "/admin/article/update" is not supported!"
     * Request mapping: /admin/article/update
     * Method: GET
     *
     * @throws IllegalMappingException thrown when an error occurs reading
     *                                 the mapping between object and datastore.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.GET
    )
    public void updateArticle() throws IllegalMappingException {
        throw new IllegalMappingException(
                "GET method in \"/admin/article/update\" is not supported!"
        );
    }

    /**
     * Removes article with url and redirects by url /admin/.
     * Request mapping: /admin/article/delete/{url}
     * Method: GET
     *
     * @param url          a url of the article to remove.
     * @param modelAndView a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     * @see Article
     */
    @RequestMapping(
            value = "/delete/{url}",
            method = RequestMethod.GET
    )
    public ModelAndView deleteArticleByUrl(
            @PathVariable("url") final String url,
            final ModelAndView modelAndView
    ) {
        this.articleService.removeByUrl(url);
        modelAndView.setViewName("redirect:/");
        Cache.clear();
        return modelAndView;
    }

    /**
     * Removes all articles and redirects by url /admin/.
     * Request mapping: /admin/article/delete/all
     * Method: GET
     *
     * @param modelAndView a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     * @see Article
     */
    @RequestMapping(
            value = "/delete/all",
            method = RequestMethod.GET
    )
    public ModelAndView deleteAllArticles(final ModelAndView modelAndView) {
        this.articleService.removeAll();
        modelAndView.setViewName("redirect:/");
        Cache.clear();
        return modelAndView;
    }

    /**
     * Returns a view name for the article.
     * If the article text is not blank then
     * returns "redirect:/admin/article/{article_url}",
     * else if the article category is not {@code null}
     * then returns "redirect:/admin/category/{category_url}",
     * else returns "redirect:/admin/article/all";
     *
     * @param article the article to get view name.
     * @return The view name.
     */
    private static String getViewName(final Article article) {
        String viewName;
        if (isNotBlank(article.getText())) {
            viewName = "redirect:/article/" + article.getUrl();
        } else if ((article.getCategory() != null) && (article.getCategory().isValidated())) {
            viewName = "redirect:/category/" + article.getCategory().getUrl();
        } else {
            viewName = "redirect:/article/all";
        }
        return viewName;
    }
}
