package ua.com.ecoteh.service.fabrica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.entity.*;
import ua.com.ecoteh.enums.FileType;
import ua.com.ecoteh.service.data.*;

import java.util.Collection;
import java.util.List;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNull;

/**
 * The abstract class implements a set of standard methods for creates
 * and returns the main modelAndViews.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Service
@SuppressWarnings("SpringMVCViewInspection")
public final class MainMVFabricImpl implements MainMVFabric {

    /**
     * The interface of the service layer, describes a set of methods
     * for working with objects of the class {@link Article}.
     */
    private final ArticleService articleService;

    /**
     * The interface of the service layer, describes a set of methods
     * for working with objects of the class {@link Category}.
     */
    private final CategoryService categoryService;

    /**
     * The interface of the service layer, describes a set of methods
     * for working with objects of the class {@link Company}.
     */
    private final CompanyService companyService;

    /**
     * The interface of the service layer, describes a set of methods
     * for working with objects of the class {@link File}.
     */
    private final FileService fileService;

    /**
     * The interface of the service layer, describes a set of methods
     * for working with objects of the class {@link Response}.
     */
    private final ResponseService responseService;

    /**
     * The interface of the service layer, describes a set of methods
     * for working with objects of the class {@link User}.
     */
    private final UserService userService;

    /**
     * Constructor.
     *
     * @param articleService  the implementation  of the {@link ArticleService} interface.
     * @param categoryService the implementation of the {@link CategoryService} interface.
     * @param companyService  the implementation of the {@link CompanyService} interface.
     * @param fileService     the implementation of the {@link FileService} interface.
     * @param responseService the implementation of the {@link ResponseService} interface.
     * @param userService     the implementation of the {@link UserService} interface.
     */
    @Autowired
    public MainMVFabricImpl(
            final ArticleService articleService,
            final CategoryService categoryService,
            final CompanyService companyService,
            final FileService fileService,
            final ResponseService responseService,
            final UserService userService
    ) {
        super();
        this.articleService = articleService;
        this.categoryService = categoryService;
        this.companyService = companyService;
        this.fileService = fileService;
        this.responseService = responseService;
        this.userService = userService;
    }

    /**
     * Creates and returns a home page.
     *
     * @return The ready object of the ModelAndView class.
     */
    @Override
    @Transactional(readOnly = true)
    public ModelAndView homePage() {
        final ModelAndView modelAndView = getDefaultModelAndView();
        modelAndView.addObject("company", this.companyService.getMainCompany());
        modelAndView.addObject("slides", this.fileService.getByType(FileType.SLIDE));
        modelAndView.addObject("partners", this.companyService.getPartners(isValidContent()));
        modelAndView.addObject("print_partners", 6);
        modelAndView.addObject("responses", this.responseService.getAndSortByDate(true));
        modelAndView.addObject("print_responses", 3);
        modelAndView.setViewName("home/index");
        return modelAndView;
    }

    /**
     * Creates and returns a page with all categories.
     *
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView allCategoriesPage() {
        final ModelAndView modelAndView = getDefaultModelAndView();
        modelAndView.setViewName("category/all");
        return modelAndView;
    }

    /**
     * Creates and returns a page with all articles sorted by date.
     *
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView allArticlesPage() {
        final ModelAndView modelAndView = allSortByTitleArticlesPage(false);
        modelAndView.setViewName("article/all");
        return modelAndView;
    }

    /**
     * Creates and returns a page with all articles sorted by sortType.
     *
     * @param sortType the sort type.
     * @param revers   the sorting direction, true or false.
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView allSortArticlesPage(
            final String sortType,
            final boolean revers
    ) {
        ModelAndView modelAndView;
        switch (sortType) {
        case "title":
            modelAndView = allSortByTitleArticlesPage(revers);
            break;
        case "date":
            modelAndView = allSortByDateArticlesPage(revers);
            break;
        case "number":
            modelAndView = allSortByNumberArticlesPage(revers);
            break;
        default:
            modelAndView = allArticlesPage();
        }
        modelAndView.setViewName("article/all");
        return modelAndView;
    }

    /**
     * Creates and returns a page with information about main company.
     *
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView aboutCompanyPage() {
        final ModelAndView modelAndView = getDefaultModelAndView();
        modelAndView.addObject("company", this.companyService.getMainCompany());
        final Collection<User> personnel;
        if (isValidContent()) {
            personnel = this.userService.filteredByValid(
                    this.userService.getPersonnel()
            );
        } else {
            personnel = this.userService.getPersonnel();
        }
        modelAndView.addObject("users_list", personnel);
        modelAndView.setViewName("company/main");
        return modelAndView;
    }

    /**
     * Creates and returns a page with contacts information about main company.
     *
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView contactsPage() {
        final ModelAndView modelAndView = getDefaultModelAndView();
        final Company mainCompany = this.companyService.getMainCompany();
        modelAndView.addObject("company", mainCompany);
        modelAndView.setViewName("company/main_contacts");
        return modelAndView;
    }

    /**
     * Creates and returns a page with all partners.
     *
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView allPartnersPage() {
        return allSortPartnersByTitlePage(false);
    }

    /**
     * Creates and returns a page with all sorted partners.
     *
     * @param revers the sorting direction, true or false.
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView allSortPartnersByTitlePage(final boolean revers) {
        final ModelAndView modelAndView = getDefaultModelAndView();
        modelAndView.addObject(
                "partners_list",
                this.companyService.sortByTitle(
                        this.companyService.getPartners(isValidContent()),
                        revers
                )
        );
        modelAndView.addObject("revers", !revers);
        modelAndView.setViewName("company/all");
        return modelAndView;
    }

    /**
     * Creates and returns a page with one category with incoming URL.
     *
     * @param url the URL of a category to return.
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView categoryPage(final String url) {
        final ModelAndView modelAndView = categoryWithSortByTitleArticlesPage(url, false);
        modelAndView.setViewName("category/one");
        return modelAndView;
    }

    /**
     * Creates and returns a page with category
     * with all articles sorted by the incoming sort type.
     *
     * @param url      the category URL.
     * @param sortType the sort type.
     * @param revers   the sorting direction, true or false.
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView categoryWithSortArticlesPage(
            final String url,
            final String sortType,
            final boolean revers
    ) {
        ModelAndView modelAndView;
        switch (sortType) {
        case "title":
            modelAndView = categoryWithSortByTitleArticlesPage(url, revers);
            break;
        case "date":
            modelAndView = categoryWithSortByDateArticlesPage(url, revers);
            break;
        case "number":
            modelAndView = categoryWithSortByNumberArticlesPage(url, revers);
            break;
        default:
            modelAndView = categoryPage(url);
        }
        modelAndView.setViewName("category/one");
        return modelAndView;
    }

    /**
     * Creates and returns a page with one article with the incoming URL.
     *
     * @param url the URL of a article to return.
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView articleByUrlPage(final String url) {
        return articlePage(
                this.articleService.getByUrl(url, isValidContent())
        );
    }

    /**
     * Creates and returns a page with one article with the incoming number.
     *
     * @param number the number of a article to return.
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView articleByNumberPage(final String number) {
        return articlePage(
                this.articleService.getByNumber(number, isValidContent())
        );
    }

    /**
     * Creates and returns a page with one partner with the incoming URL.
     *
     * @param url the URL of a partner to return.
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView partnerPage(final String url) {
        final ModelAndView modelAndView = getDefaultModelAndView();
        final Company company = this.companyService.getByUrl(url, isValidContent());
        modelAndView.addObject("company", company);
        modelAndView.setViewName("company/one");
        return modelAndView;
    }

    /**
     * Creates and returns a page with all responses.
     *
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView allResponsesPage() {
        return allSortResponsesByDatePage(true);
    }

    /**
     * Creates and returns a page with all sorted responses.
     *
     * @param revers the sorting direction, true or false.
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView allSortResponsesByDatePage(final boolean revers) {
        final ModelAndView modelAndView = getDefaultModelAndView();
        modelAndView.addObject(
                "responses",
                this.responseService.sortByDate(
                        this.responseService.getAll(isValidContent()),
                        revers
                )
        );
        modelAndView.addObject("revers", !revers);
        modelAndView.setViewName("response/all");
        return modelAndView;
    }

    /**
     * Creates and returns a default modelAndView.
     *
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView getDefaultModelAndView() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("main_company", this.companyService.getMainCompany());
        modelAndView.addObject("categories", this.categoryService.getAll(isValidContent()));
        modelAndView.addObject("favicon", this.fileService.getLastByType(FileType.FAVICON));
        return modelAndView;
    }

    /**
     * Adds an authenticated user to the modelAndView.
     *
     * @param modelAndView the model and view to update.
     */
    @Override
    public void addAuthUser(final ModelAndView modelAndView) {
        if (isNotNull(modelAndView)) {
            modelAndView.addObject("authorized_user", this.userService.getAuthenticatedUser());
        }
    }

    /**
     * Validates an output objects.
     *
     * @return Returns true if need to return a valid objects,
     * false otherwise.
     */
    @Override
    public boolean isValidContent() {
        return isNull(this.userService.getAuthenticatedUser());
    }

    /**
     * Creates and returns a page with all articles sorted by title.
     *
     * @param revers the sorting direction, true or false.
     * @return The ready object of the ModelAndView class.
     */
    private ModelAndView allSortByTitleArticlesPage(final boolean revers) {
        return sortArticlesPage(
                "title", revers,
                this.articleService.sortByTitle(
                        this.articleService.getAll(isValidContent()),
                        revers
                )
        );
    }

    /**
     * Creates and returns a page with all articles sorted by date.
     *
     * @param revers the sorting direction, true or false.
     * @return The ready object of the ModelAndView class.
     */
    private ModelAndView allSortByDateArticlesPage(final boolean revers) {
        return sortArticlesPage(
                "date", revers,
                this.articleService.sortByDate(
                        this.articleService.getAll(isValidContent()),
                        revers
                )
        );
    }

    /**
     * Creates and returns a page with all articles sorted by number.
     *
     * @param revers the sorting direction, true or false.
     * @return The ready object of the ModelAndView class.
     */
    private ModelAndView allSortByNumberArticlesPage(final boolean revers) {
        return sortArticlesPage(
                "date", revers,
                this.articleService.sortByNumber(
                        this.articleService.getAll(isValidContent()),
                        revers
                )
        );
    }

    /**
     * Creates and returns a page with a articles sorted by the incoming sort type.
     *
     * @param sortType the sort type.
     * @param revers   the sorting direction, true or false.
     * @param articles the sorted articles.
     * @return The ready object of the ModelAndView class.
     */
    private ModelAndView sortArticlesPage(
            final String sortType,
            final boolean revers,
            final List<Article> articles
    ) {
        final ModelAndView modelAndView = getDefaultModelAndView();
        modelAndView.addObject("articles_list", articles);
        modelAndView.addObject("sort", sortType);
        modelAndView.addObject("revers", !revers);
        return modelAndView;
    }

    /**
     * Creates and returns a page with category
     * with all articles sorted by title.
     *
     * @param url    the category URL.
     * @param revers the sorting direction, true or false.
     * @return The ready object of the ModelAndView class.
     */
    private ModelAndView categoryWithSortByTitleArticlesPage(
            final String url,
            final boolean revers
    ) {
        final Category category = this.categoryService.getByUrl(url, isValidContent());
        final ModelAndView modelAndView = sortArticlesPage(
                "date", revers,
                this.articleService.sortByTitle(
                        isValidContent() ?
                                this.articleService.filteredByValid(category.getArticles())
                                : category.getArticles(),
                        revers
                )
        );
        modelAndView.addObject("category", category);
        return modelAndView;
    }

    /**
     * Creates and returns a page with category
     * with all articles sorted by date.
     *
     * @param url    the category URL.
     * @param revers the sorting direction, true or false.
     * @return The ready object of the ModelAndView class.
     */
    private ModelAndView categoryWithSortByDateArticlesPage(
            final String url,
            final boolean revers
    ) {
        final Category category = this.categoryService.getByUrl(
                url, isValidContent()
        );
        final ModelAndView modelAndView = sortArticlesPage(
                "date", revers,
                this.articleService.sortByDate(
                        isValidContent() ?
                                this.articleService.filteredByValid(category.getArticles())
                                : category.getArticles(),
                        revers
                )
        );
        modelAndView.addObject("category", category);
        return modelAndView;
    }

    /**
     * Creates and returns a page with category
     * with all articles sorted by number.
     *
     * @param url    the category URL.
     * @param revers the sorting direction, true or false.
     * @return The ready object of the ModelAndView class.
     */
    private ModelAndView categoryWithSortByNumberArticlesPage(
            final String url,
            final boolean revers
    ) {
        final Category category = this.categoryService.getByUrl(
                url, isValidContent()
        );
        final ModelAndView modelAndView = sortArticlesPage(
                "date", revers,
                this.articleService.sortByNumber(
                        isValidContent() ?
                                this.articleService.filteredByValid(category.getArticles())
                                : category.getArticles(),
                        revers
                )
        );
        modelAndView.addObject("category", category);
        return modelAndView;
    }

    /**
     * Returns a page with the incoming article.
     *
     * @param article the article to return.
     * @return The ready object of the ModelAndView class.
     */
    private ModelAndView articlePage(final Article article) {
        final ModelAndView modelAndView = getDefaultModelAndView();
        modelAndView.addObject("article", article);
        if (isValidCategory(article.getCategory())) {
            final Category category = this.categoryService.getByUrl(
                    article.getCategory().getUrl(), isValidContent()
            );
            final List<Article> articles = this.articleService.shuffle(category.getArticles());
            articles.remove(article);
            modelAndView.addObject("articles", articles);
            modelAndView.addObject("category", category);
        }
        modelAndView.setViewName("article/one");
        return modelAndView;
    }

    /**
     * Validated a incoming category.
     * Category is valid if it is not null and it URL is not empty.
     * <pre>
     *     validCategory(null) = false
     *     validCategory(new Category()) = false
     *
     *     Category category = new Category();
     *     category.setUrl("bob");
     *     validCategory(category) = true
     * </pre>
     *
     * @param category the category to check.
     * @return true if the category is not null and category URL is not empty.
     */
    private boolean isValidCategory(final Category category) {
        return isNotNull(category) && isNotEmpty(category.getUrl());
    }
}
