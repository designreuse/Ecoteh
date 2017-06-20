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
import ua.com.ecoteh.entity.article.Article;
import ua.com.ecoteh.entity.article.ArticleBuilder;
import ua.com.ecoteh.entity.category.Category;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.file.FileBuilder;
import ua.com.ecoteh.exception.ExceptionMessage;
import ua.com.ecoteh.service.data.ArticleService;
import ua.com.ecoteh.service.data.CategoryService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;
import ua.com.ecoteh.util.cache.Cache;
import ua.com.ecoteh.util.compressor.HtmlCompressor;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of methods for working with
 * objects of the {@link Article} class or subclasses for admins.
 * Class methods create and return modelsAndView, depending on the request.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
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
public final class ArticleController {

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
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param fabric          the implementation of the {@link MainMVFabric} interface.
     * @param articleService  the implementation of the {@link ArticleService} interface.
     * @param categoryService the implementation of the {@link CategoryService} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public ArticleController(
            @Qualifier("cacheMVFabricImpl") final MainMVFabric fabric,
            final ArticleService articleService,
            final CategoryService categoryService
    ) {
        this.fabric = fabric;
        this.articleService = articleService;
        this.categoryService = categoryService;
    }

    /**
     * Returns a page to add a new article.
     * Request mapping: /admin/article/new
     * Method: GET
     *
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = "/new",
            method = RequestMethod.GET
    )
    public ModelAndView getNewArticlePage() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("categories", this.categoryService.getAll(false));
        modelAndView.setViewName("article/add");
        return modelAndView;
    }

    /**
     * Adds a new article with the incoming parameters
     * and redirects by the "/article/{url}" URL,
     * where {url} is a URL of a saving article.
     * Request mapping: /admin/article/add
     * Method: POST
     *
     * @param title         the title of a new article.
     * @param description   the description of a new article.
     * @param text          the text of a new article.
     * @param keywords      the keywords of a new article.
     * @param number        the number of a new article.
     * @param price         the price to a new article.
     * @param currency      the new price currency to a article.
     * @param categoryUrl   the categoryarticle URL of a new article.
     * @param multipartLogo thea file of photo to a new categoryarticle.
     * @param validated     the validated of a new article.
     * @return The redirect string to the "/article/{url}" URL,
     * where {url} is a URL of a saving article.
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
            @RequestParam(value = "price") final double price,
            @RequestParam(value = "currency") final String currency,
            @RequestParam(value = "category_url") final String categoryUrl,
            @RequestParam(value = "logo") final MultipartFile multipartLogo,
            @RequestParam(value = "is_valid") final boolean validated
    ) {
        final Compressor compressor = new HtmlCompressor();
        final ArticleBuilder articleBuilder = Article.getBuilder();
        articleBuilder.addTitle(title).addKeywords(keywords).addNumber(number)
                .addPrice(price).addCurrency(currency).addValidated(validated)
                .addDescription(compressor.compress(description))
                .addText(compressor.compress(text));

        final Category category = this.categoryService.getByUrl(categoryUrl, false);
        articleBuilder.addCategory(category);

        final FileBuilder fileBuilder = File.getBuilder();
        fileBuilder.addTitle(title).addMultipartFile(multipartLogo);
        final File logo = fileBuilder.build();
        articleBuilder.addLogo(logo);

        final Article article = articleBuilder.build();
        final Article savingArticle = this.articleService.add(article);
        Cache.clear();
        return getViewName(savingArticle);
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
        final String message = String.format(
                ExceptionMessage.GET_METHOD_NOT_SUPPORTED_MESSAGE,
                "/admin/article/add"
        );
        throw new IllegalMappingException(message);
    }

    /**
     * Returns a page to edit an article with the incoming URL.
     * Request mapping: /admin/article/edit/{url},
     * where {url} is a URL of an article to edit.
     * Method: GET
     *
     * @param url the URL of a article to edit.
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = "/edit/{url}",
            method = RequestMethod.GET
    )
    public ModelAndView editArticle(@PathVariable("url") final String url) {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("article", this.articleService.getByUrl(url, false));
        modelAndView.addObject("categories", this.categoryService.getAll(false));
        modelAndView.setViewName("article/edit");
        return modelAndView;
    }

    /**
     * Updates and save an article with the incoming URL
     * and redirects by the "/article/{url}" URL,
     * where {url} is a URL of a saving article.
     * Request mapping: /admin/article/update
     * Method: POST
     *
     * @param url           the URL of a article to update.
     * @param title         the new title to a article.
     * @param description   the new description to a article.
     * @param text          the new text to a article.
     * @param keywords      the new keywords to a article.
     * @param number        the new number to a article.
     * @param price         the new price to a article.
     * @param currency      the new price currency to a article.
     * @param categoryUrl   the categoryarticle URL of a article.
     * @param multipartLogo the file of photo to a new categoryarticle.
     * @param validated     the validated of a article.
     * @return The redirect string to the "/article/{url}" URL,
     * where {url} is a URL of a saving article.
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
            @RequestParam(value = "price") final double price,
            @RequestParam(value = "currency") final String currency,
            @RequestParam(value = "category_url") final String categoryUrl,
            @RequestParam(value = "logo") final MultipartFile multipartLogo,
            @RequestParam(value = "is_valid") final boolean validated
    ) {
        final Compressor compressor = new HtmlCompressor();
        final ArticleBuilder articleBuilder = Article.getBuilder();
        articleBuilder.addUrl(url).addTitle(title).addKeywords(keywords).addNumber(number)
                .addPrice(price).addCurrency(currency).addValidated(validated)
                .addDescription(compressor.compress(description))
                .addText(compressor.compress(text));

        final Category category = this.categoryService.getByUrl(categoryUrl, false);
        articleBuilder.addCategory(category);

        final FileBuilder fileBuilder = File.getBuilder();
        fileBuilder.addTitle(title).addMultipartFile(multipartLogo);
        final File logo = fileBuilder.build();
        articleBuilder.addLogo(logo);

        final Article article = articleBuilder.build();
        final Article updatedArticle = this.articleService.update(article);
        Cache.clear();
        return getViewName(updatedArticle);
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
        final String message = String.format(
                ExceptionMessage.GET_METHOD_NOT_SUPPORTED_MESSAGE,
                "/admin/article/update"
        );
        throw new IllegalMappingException(message);
    }

    /**
     * Removes an article with the incoming URL
     * and redirects by the "/" URL.
     * Request mapping: /admin/article/delete/{url},
     * where {url} is a URL of an article to remove.
     * Method: GET
     *
     * @param url the URL of an article to remove.
     * @return The redirect string to the "/" URL.
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
     * Removes an all articles and redirects by the "/" URL.
     * Request mapping: /admin/article/delete/all
     * Method: GET
     *
     * @return The redirect string to the "/" URL.
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
     * If an article text is not blank then returns "redirect:/admin/article/{url}",
     * where {url} is a URL of an article;
     * else if an article categoryarticle is not null
     * then returns "redirect:/admin/categoryarticle/{url}",
     * where {url} is a URL of an article categoryarticle;
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
     * Validated a incoming categoryarticle.
     * Categoryarticle is valid if it is not null and and it validated.
     * <pre>
     *     isValidCategory(null) = false
     *
     *     if the incoming category is not null and invalid
     *     isValidCategory(category) = false
     *
     *     if the incoming category is not null and valid
     *     isValidCategory(category) = true
     * </pre>
     *
     * @param category the category to check.
     * @return true if the category is not null and it validated.
     */
    private static boolean isValidCategory(final Category category) {
        return isNotNull(category) && category.isValidated();
    }
}
