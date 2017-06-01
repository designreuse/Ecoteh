package ua.com.ecoteh.service.fabrica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.entity.article.ArticleEntity;
import ua.com.ecoteh.entity.category.CategoryEntity;
import ua.com.ecoteh.entity.company.CompanyEntity;
import ua.com.ecoteh.entity.file.FileEntity;
import ua.com.ecoteh.entity.response.ResponseEntity;
import ua.com.ecoteh.entity.user.UserEntity;
import ua.com.ecoteh.entity.file.FileType;
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
     * for working with objects of the class {@link ArticleEntity}.
     */
    private final ArticleService articleService;

    /**
     * The interface of the service layer, describes a set of methods
     * for working with objects of the class {@link CategoryEntity}.
     */
    private final CategoryService categoryService;

    /**
     * The interface of the service layer, describes a set of methods
     * for working with objects of the class {@link CompanyEntity}.
     */
    private final CompanyService companyService;

    /**
     * The interface of the service layer, describes a set of methods
     * for working with objects of the class {@link FileEntity}.
     */
    private final FileService fileService;

    /**
     * The interface of the service layer, describes a set of methods
     * for working with objects of the class {@link ResponseEntity}.
     */
    private final ResponseService responseService;

    /**
     * The interface of the service layer, describes a set of methods
     * for working with objects of the class {@link UserEntity}.
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
        modelAndView.setViewName("categoryEntity/all");
        return modelAndView;
    }

    /**
     * Creates and returns a page with all articleEntities sorted by date.
     *
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView allArticlesPage() {
        final ModelAndView modelAndView = allSortByTitleArticlesPage(false);
        modelAndView.setViewName("articleEntity/all");
        return modelAndView;
    }

    /**
     * Creates and returns a page with all articleEntities sorted by sortType.
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
        modelAndView.setViewName("articleEntity/all");
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
        final Collection<UserEntity> personnel;
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
        final CompanyEntity mainCompanyEntity = this.companyService.getMainCompany();
        modelAndView.addObject("company", mainCompanyEntity);
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
     * Creates and returns a page with one categoryEntity with incoming URL.
     *
     * @param url the URL of a categoryEntity to return.
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView categoryPage(final String url) {
        final ModelAndView modelAndView = categoryWithSortByTitleArticlesPage(url, false);
        modelAndView.setViewName("categoryEntity/one");
        return modelAndView;
    }

    /**
     * Creates and returns a page with categoryEntity
     * with all articleEntities sorted by the incoming sort type.
     *
     * @param url      the categoryEntity URL.
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
        modelAndView.setViewName("categoryEntity/one");
        return modelAndView;
    }

    /**
     * Creates and returns a page with one articleEntity with the incoming URL.
     *
     * @param url the URL of a articleEntity to return.
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView articleByUrlPage(final String url) {
        return articlePage(
                this.articleService.getByUrl(url, isValidContent())
        );
    }

    /**
     * Creates and returns a page with one articleEntity with the incoming number.
     *
     * @param number the number of a articleEntity to return.
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
        final CompanyEntity companyEntity = this.companyService.getByUrl(url, isValidContent());
        modelAndView.addObject("company", companyEntity);
        modelAndView.setViewName("companyEntity/one");
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
     * Creates and returns a page with all articleEntities sorted by title.
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
     * Creates and returns a page with all articleEntities sorted by date.
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
     * Creates and returns a page with all articleEntities sorted by number.
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
     * Creates and returns a page with a articleEntities sorted by the incoming sort type.
     *
     * @param sortType the sort type.
     * @param revers   the sorting direction, true or false.
     * @param articleEntities the sorted articleEntities.
     * @return The ready object of the ModelAndView class.
     */
    private ModelAndView sortArticlesPage(
            final String sortType,
            final boolean revers,
            final List<ArticleEntity> articleEntities
    ) {
        final ModelAndView modelAndView = getDefaultModelAndView();
        modelAndView.addObject("articles_list", articleEntities);
        modelAndView.addObject("sort", sortType);
        modelAndView.addObject("revers", !revers);
        return modelAndView;
    }

    /**
     * Creates and returns a page with categoryEntity
     * with all articleEntities sorted by title.
     *
     * @param url    the categoryEntity URL.
     * @param revers the sorting direction, true or false.
     * @return The ready object of the ModelAndView class.
     */
    private ModelAndView categoryWithSortByTitleArticlesPage(
            final String url,
            final boolean revers
    ) {
        final CategoryEntity categoryEntity = this.categoryService.getByUrl(url, isValidContent());
        final ModelAndView modelAndView = sortArticlesPage(
                "date", revers,
                this.articleService.sortByTitle(
                        isValidContent() ?
                                this.articleService.filteredByValid(categoryEntity.getArticleEntities())
                                : categoryEntity.getArticleEntities(),
                        revers
                )
        );
        modelAndView.addObject("category", categoryEntity);
        return modelAndView;
    }

    /**
     * Creates and returns a page with categoryEntity
     * with all articleEntities sorted by date.
     *
     * @param url    the categoryEntity URL.
     * @param revers the sorting direction, true or false.
     * @return The ready object of the ModelAndView class.
     */
    private ModelAndView categoryWithSortByDateArticlesPage(
            final String url,
            final boolean revers
    ) {
        final CategoryEntity categoryEntity = this.categoryService.getByUrl(
                url, isValidContent()
        );
        final ModelAndView modelAndView = sortArticlesPage(
                "date", revers,
                this.articleService.sortByDate(
                        isValidContent() ?
                                this.articleService.filteredByValid(categoryEntity.getArticleEntities())
                                : categoryEntity.getArticleEntities(),
                        revers
                )
        );
        modelAndView.addObject("category", categoryEntity);
        return modelAndView;
    }

    /**
     * Creates and returns a page with categoryEntity
     * with all articleEntities sorted by number.
     *
     * @param url    the categoryEntity URL.
     * @param revers the sorting direction, true or false.
     * @return The ready object of the ModelAndView class.
     */
    private ModelAndView categoryWithSortByNumberArticlesPage(
            final String url,
            final boolean revers
    ) {
        final CategoryEntity categoryEntity = this.categoryService.getByUrl(
                url, isValidContent()
        );
        final ModelAndView modelAndView = sortArticlesPage(
                "date", revers,
                this.articleService.sortByNumber(
                        isValidContent() ?
                                this.articleService.filteredByValid(categoryEntity.getArticleEntities())
                                : categoryEntity.getArticleEntities(),
                        revers
                )
        );
        modelAndView.addObject("category", categoryEntity);
        return modelAndView;
    }

    /**
     * Returns a page with the incoming articleEntity.
     *
     * @param articleEntity the articleEntity to return.
     * @return The ready object of the ModelAndView class.
     */
    private ModelAndView articlePage(final ArticleEntity articleEntity) {
        final ModelAndView modelAndView = getDefaultModelAndView();
        modelAndView.addObject("article", articleEntity);
        if (isValidCategory(articleEntity.getCategoryEntity())) {
            final CategoryEntity categoryEntity = this.categoryService.getByUrl(
                    articleEntity.getCategoryEntity().getUrl(), isValidContent()
            );
            final List<ArticleEntity> articleEntities = this.articleService.shuffle(categoryEntity.getArticleEntities());
            articleEntities.remove(articleEntity);
            modelAndView.addObject("articles", articleEntities);
            modelAndView.addObject("category", categoryEntity);
        }
        modelAndView.setViewName("articleEntity/one");
        return modelAndView;
    }

    /**
     * Validated a incoming categoryEntity.
     * CategoryEntity is valid if it is not null and it URL is not empty.
     * <pre>
     *     validCategory(null) = false
     *     validCategory(new CategoryEntity()) = false
     *
     *     CategoryEntity categoryEntity = new CategoryEntity();
     *     categoryEntity.setUrl("bob");
     *     validCategory(categoryEntity) = true
     * </pre>
     *
     * @param categoryEntity the categoryEntity to check.
     * @return true if the categoryEntity is not null and categoryEntity URL is not empty.
     */
    private boolean isValidCategory(final CategoryEntity categoryEntity) {
        return isNotNull(categoryEntity) && isNotEmpty(categoryEntity.getUrl());
    }
}
