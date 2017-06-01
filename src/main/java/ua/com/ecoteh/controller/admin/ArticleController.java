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
import ua.com.ecoteh.entity.article.ArticleEntity;
import ua.com.ecoteh.entity.category.CategoryEntity;
import ua.com.ecoteh.entity.file.FileEntity;
import ua.com.ecoteh.exception.ExceptionMessage;
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
 * objects of the {@link ArticleEntity} class or subclasses for admins.
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
public class ArticleController {

    /**
     * The implementation of the interface provides a set of standard methods
     * for creates and returns the main modelAndViews.
     */
    private final MainMVFabric fabric;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link ArticleEntity} class.
     */
    private final ArticleService articleService;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link CategoryEntity} class.
     */
    private final CategoryService categoryService;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link FileEntity} class.
     */
    private final FileService fileService;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param fabric          the implementation of the {@link MainMVFabric} interface.
     * @param articleService  the implementation of the {@link ArticleService} interface.
     * @param categoryService the implementation of the {@link CategoryService} interface.
     * @param fileService     the implementation of the {@link FileService} interface.
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
     * Returns a page to add a new articleEntity.
     * Request mapping: /admin/articleEntity/new
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
        modelAndView.setViewName("articleEntity/add");
        return modelAndView;
    }

    /**
     * Adds a new articleEntity with the incoming parameters
     * and redirects by the "/articleEntity/{url}" URL,
     * where {url} is a URL of a saving articleEntity.
     * Request mapping: /admin/articleEntity/add
     * Method: POST
     *
     * @param title         the title of a new articleEntity.
     * @param description   the description of a new articleEntity.
     * @param text          the text of a new articleEntity.
     * @param keywords      the keywords of a new articleEntity.
     * @param number        the number of a new articleEntity.
     * @param price         the price to a new articleEntity.
     * @param categoryUrl   the categoryEntity URL of a new articleEntity.
     * @param multipartLogo thea file of photo to a new categoryEntity.
     * @param validated     the validated of a new articleEntity.
     * @return The redirect string to the "/articleEntity/{url}" URL,
     * where {url} is a URL of a saving articleEntity.
     */
    @RequestMapping(
            value = "/add",
            method = RequestMethod.POST
    )
    public String addArticle(
            @RequestParam(value = "title", defaultValue = "") final String title,
            @RequestParam(value = "desc", defaultValue = "") final String description,
            @RequestParam(value = "text", defaultValue = "") final String text,
            @RequestParam(value = "keywords", defaultValue = "") final String keywords,
            @RequestParam(value = "number", defaultValue = "") final String number,
            @RequestParam(value = "price", defaultValue = "0") final String price,
            @RequestParam(value = "category_url", defaultValue = "") final String categoryUrl,
            @RequestParam(value = "logo") final MultipartFile multipartLogo,
            @RequestParam(value = "is_valid") final boolean validated
    ) {
        final CategoryEntity categoryEntity = isNotEmpty(categoryUrl) ?
                this.categoryService.getByUrl(categoryUrl, false) : null;
        final Compressor compressor = new HtmlCompressor();
        final ArticleEntity articleEntity = new ArticleEntity(
                title,
                compressor.compress(description),
                compressor.compress(text),
                keywords, number, price
        );
        articleEntity.setValidated(validated);
        articleEntity.setCategoryEntity(categoryEntity);
        if (isNotEmpty(multipartLogo)) {
            articleEntity.setLogoEntity(this.fileService.add(articleEntity.getTitle(), multipartLogo));
        }
        this.articleService.add(articleEntity);
        Cache.clear();
        return getViewName(articleEntity);
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception message:
     * "GET method in "/admin/articleEntity/add" is not supported!"
     * Request mapping: /admin/articleEntity/add
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
                String.format(
                        ExceptionMessage.GET_METHOD_NOT_SUPPORTED_MESSAGE,
                        "/admin/articleEntity/add"
                )
        );
    }

    /**
     * Returns a page to edit an articleEntity with the incoming URL.
     * Request mapping: /admin/articleEntity/edit/{url},
     * where {url} is a URL of an articleEntity to edit.
     * Method: GET
     *
     * @param url the URL of a articleEntity to edit.
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
        modelAndView.setViewName("articleEntity/edit");
        return modelAndView;
    }

    /**
     * Updates and save an articleEntity with the incoming URL
     * and redirects by the "/articleEntity/{url}" URL,
     * where {url} is a URL of a saving articleEntity.
     * Request mapping: /admin/articleEntity/update
     * Method: POST
     *
     * @param url           the URL of a articleEntity to update.
     * @param title         the new title to a articleEntity.
     * @param description   the new description to a articleEntity.
     * @param text          the new text to a articleEntity.
     * @param keywords      the new keywords to a articleEntity.
     * @param number        the new number to a articleEntity.
     * @param price         the new price to a articleEntity.
     * @param categoryUrl   the categoryEntity URL of a articleEntity.
     * @param multipartLogo the file of photo to a new categoryEntity.
     * @param validated     the validated of a articleEntity.
     * @return The redirect string to the "/articleEntity/{url}" URL,
     * where {url} is a URL of a saving articleEntity.
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
            @RequestParam(value = "is_valid") final boolean validated
    ) {
        final CategoryEntity categoryEntity = isNotEmpty(categoryUrl) ?
                this.categoryService.getByUrl(categoryUrl, false) : null;
        final Compressor compressor = new HtmlCompressor();
        final ArticleEntity articleEntity = new ArticleEntity(
                title,
                compressor.compress(description),
                compressor.compress(text),
                keywords, number, price
        );
        articleEntity.setValidated(validated);
        articleEntity.setCategoryEntity(categoryEntity);
        if (isNotEmpty(multipartLogo)) {
            articleEntity.setLogoEntity(this.fileService.add(articleEntity.getTitle(), multipartLogo));
        }
        this.articleService.update(url, articleEntity);
        Cache.clear();
        return getViewName(articleEntity);
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception message:
     * "GET method in "/admin/articleEntity/update" is not supported!"
     * Request mapping: /admin/articleEntity/update
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
                String.format(
                        ExceptionMessage.GET_METHOD_NOT_SUPPORTED_MESSAGE,
                        "/admin/articleEntity/update"
                )
        );
    }

    /**
     * Removes an articleEntity with the incoming URL
     * and redirects by the "/" URL.
     * Request mapping: /admin/articleEntity/delete/{url},
     * where {url} is a URL of an articleEntity to remove.
     * Method: GET
     *
     * @param url the URL of an articleEntity to remove.
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
     * Request mapping: /admin/articleEntity/delete/all
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
     * Returns a view name for the articleEntity.
     * If an articleEntity text is not blank then returns "redirect:/admin/articleEntity/{url}",
     * where {url} is a URL of an articleEntity;
     * else if an articleEntity categoryEntity is not null
     * then returns "redirect:/admin/categoryEntity/{url}",
     * where {url} is a URL of an articleEntity categoryEntity;
     * else returns "redirect:/admin/articleEntity/all";
     *
     * @param articleEntity the articleEntity to get view name.
     * @return The view name.
     */
    private static String getViewName(final ArticleEntity articleEntity) {
        String viewName;
        if (isNotEmpty(articleEntity.getText())) {
            viewName = "redirect:/articleEntity/" + articleEntity.getUrl();
        } else if (isValidCategory(articleEntity.getCategoryEntity())) {
            viewName = "redirect:/categoryEntity/" + articleEntity.getCategoryEntity().getUrl();
        } else {
            viewName = "redirect:/articleEntity/all";
        }
        return viewName;
    }

    /**
     * Validated a incoming categoryEntity.
     * CategoryEntity is valid if it is not null and and it validated.
     * <pre>
     *     isValidCategory(null) = false
     *
     *     CategoryEntity categoryEntity = new CategoryEntity();
     *     categoryEntity.setValidated(false);
     *     isValidCategory(categoryEntity) = false
     *
     *     categoryEntity.setValidated(true);
     *     isValidCategory(categoryEntity) = true
     * </pre>
     *
     * @param categoryEntity the categoryEntity to check.
     * @return true if the categoryEntity is not null and it validated.
     */
    private static boolean isValidCategory(final CategoryEntity categoryEntity) {
        return isNotNull(categoryEntity) && categoryEntity.isValidated();
    }
}
