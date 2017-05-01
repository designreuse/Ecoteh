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
import ua.com.ecoteh.entity.Article;
import ua.com.ecoteh.entity.Category;
import ua.com.ecoteh.entity.File;
import ua.com.ecoteh.service.data.ArticleService;
import ua.com.ecoteh.service.data.CategoryService;
import ua.com.ecoteh.service.data.FileService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;
import ua.com.ecoteh.util.cache.Cache;
import ua.com.ecoteh.util.compressor.HtmlCompressor;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of methods for working with
 * objects of {@link Article} class or subclasses for admins.
 * Class methods create and return modelsAndView, depending on the request.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Controller
@RequestMapping(
        value = {
                "/admin/article",
                "/admin/articles"
        }
)
@ComponentScan(
        basePackages = {
                "ua.com.ecoteh.service.fabrica",
                "ua.com.ecoteh.service.data"
        }
)
@SuppressWarnings("SpringMVCViewInspection")
public class ArticleController {

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
     * for working with objects of the {@link Article} class.
     */
    private final ArticleService articleService;

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
     * @param articleService  a implementation of the {@link ArticleService} interface.
     * @param categoryService a implementation of the {@link CategoryService} interface.
     * @param fileService     a implementation of the {@link FileService} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public ArticleController(
            @Qualifier("cacheMVFabricImpl") final MainMVFabric fabric,
            final ArticleService articleService,
            final CategoryService categoryService,
            final FileService fileService
    ) {
        this.fabric = fabric;
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
     */
    @RequestMapping(
            value = "/new",
            method = RequestMethod.GET
    )
    public ModelAndView getNewArticlePage() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("categories", this.categoryService.getAll(false));
        modelAndView.setViewName("admin/article/add");
        return modelAndView;
    }

    /**
     * Adds new article and redirects by URL /admin/article/{url}.
     * Request mapping: /admin/article/add
     * Method: POST
     *
     * @param title         a title of the new article.
     * @param description   a description of the new article.
     * @param text          a text of the new article.
     * @param keywords      a keywords of the new article.
     * @param number        a number of the new article.
     * @param price         a new price to the article.
     * @param categoryUrl   a category url of the new article.
     * @param multipartLogo a file of photo to the new category.
     * @param isValid       a validated of the new article.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/add",
            method = RequestMethod.POST
    )
    public String addArticle(
            @RequestParam(value = "title") final String title,
            @RequestParam(value = "desc") final String description,
            @RequestParam(value = "text") final String text,
            @RequestParam(value = "keywords") final String keywords,
            @RequestParam(value = "number") final String number,
            @RequestParam(value = "price", defaultValue = "0") final String price,
            @RequestParam(value = "category_url") final String categoryUrl,
            @RequestParam(value = "logo") final MultipartFile multipartLogo,
            @RequestParam(value = "is_valid") final boolean isValid
    ) {
        final Category category = isNotEmpty(categoryUrl) ?
                this.categoryService.getByUrl(categoryUrl, false) : null;
        final Compressor compressor = new HtmlCompressor();
        final Article article = new Article(
                title,
                compressor.compress(description),
                compressor.compress(text),
                keywords, number, price
        );
        article.setValidated(isValid);
        article.setCategory(category);
        if (isNotEmpty(multipartLogo)) {
            article.setLogo(this.fileService.add(article.getTitle(), multipartLogo));
        }
        this.articleService.add(article);
        Cache.clear();
        return getViewName(article);
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception message:
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
                String.format(GET_METHOD_NOT_SUPPORTED_MESSAGE, "/admin/article/add")
        );
    }

    /**
     * Returns the page to edit the article with url.
     * Request mapping: /admin/article/edit/{url}
     * Method: GET
     *
     * @param url a URL of the article to edit.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/edit/{url}",
            method = RequestMethod.GET
    )
    public ModelAndView editArticle(@PathVariable("url") final String url) {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("article", this.articleService.getByUrl(url, false));
        modelAndView.addObject("categories", this.categoryService.getAll(false));
        modelAndView.setViewName("admin/article/edit");
        return modelAndView;
    }

    /**
     * Updates and save the article with url
     * and redirects by URL /admin/article/{url}.
     * Request mapping: /admin/article/update
     * Method: POST
     *
     * @param url           a URL of the article to update.
     * @param title         a new title to the article.
     * @param description   a new description to the article.
     * @param text          a new text to the article.
     * @param keywords      a new keywords to the article.
     * @param number        a new number to the article.
     * @param price         a new price to the article.
     * @param categoryUrl   a category URL of the article.
     * @param multipartLogo a file of photo to the new category.
     * @param isValid       a validated of the article.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    public String updateArticle(
            @RequestParam(value = "url") final String url,
            @RequestParam(value = "title") final String title,
            @RequestParam(value = "desc") final String description,
            @RequestParam(value = "text") final String text,
            @RequestParam(value = "keywords") final String keywords,
            @RequestParam(value = "number") final String number,
            @RequestParam(value = "price") final String price,
            @RequestParam(value = "category_url") final String categoryUrl,
            @RequestParam(value = "logo") final MultipartFile multipartLogo,
            @RequestParam(value = "is_valid") final boolean isValid
    ) {
        final Category category = isNotEmpty(categoryUrl) ?
                this.categoryService.getByUrl(categoryUrl, false) : null;
        final Compressor compressor = new HtmlCompressor();
        final Article article = new Article(
                title,
                compressor.compress(description),
                compressor.compress(text),
                keywords, number, price
        );
        article.setValidated(isValid);
        article.setCategory(category);
        if (isNotEmpty(multipartLogo)) {
            article.setLogo(this.fileService.add(article.getTitle(), multipartLogo));
        }
        this.articleService.update(url, article);
        Cache.clear();
        return getViewName(article);
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception message:
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
                String.format(GET_METHOD_NOT_SUPPORTED_MESSAGE, "/admin/article/update")
        );
    }

    /**
     * Removes article with url and redirects by URL /admin/.
     * Request mapping: /admin/article/delete/{url}
     * Method: GET
     *
     * @param url a URL of the article to remove.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/delete/{url}",
            method = RequestMethod.GET
    )
    public String deleteArticleByUrl(@PathVariable("url") final String url) {
        this.articleService.removeByUrl(url);
        Cache.clear();
        return "redirect:/";
    }

    /**
     * Removes all articles and redirects by URL /admin/.
     * Request mapping: /admin/article/delete/all
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/delete/all",
            method = RequestMethod.GET
    )
    public String deleteAllArticles() {
        this.articleService.removeAll();
        Cache.clear();
        return "redirect:/";
    }

    /**
     * Returns a view name for the article.
     * If the article text is not blank then
     * returns "redirect:/admin/article/{article_url}",
     * else if the article category is not null
     * then returns "redirect:/admin/category/{category_url}",
     * else returns "redirect:/admin/article/all";
     *
     * @param article the article to get view name.
     * @return The view name.
     */
    private static String getViewName(final Article article) {
        String viewName;
        if (isNotEmpty(article.getText())) {
            viewName = "redirect:/article/" + article.getUrl();
        } else if (isValidCategory(article.getCategory())) {
            viewName = "redirect:/category/" + article.getCategory().getUrl();
        } else {
            viewName = "redirect:/article/all";
        }
        return viewName;
    }

    /**
     * @param category
     * @return
     */
    private static boolean isValidCategory(final Category category) {
        return isNotNull(category) && category.isValidated();
    }
}
