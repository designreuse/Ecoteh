package com.salimov.yurii.service.fabrica.impl;

import com.salimov.yurii.entity.*;
import com.salimov.yurii.service.data.interfaces.*;
import com.salimov.yurii.service.fabrica.interfaces.MainMVFabric;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

/**
 * The abstract class implements a set of standard methods for creates
 * and returns the main modelAndViews.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see MainMVFabric
 * @see ClientMVFabricImpl
 * @see AdminMVFabricImpl
 */
@SuppressWarnings("SpringMVCViewInspection")
public abstract class MainMVFabricImpl implements MainMVFabric {

    /**
     * The interface of the service layer, describes a set of methods
     * for working with objects of the class {@link Article}.
     *
     * @see ArticleService
     */
    private final ArticleService articleService;

    /**
     * The interface of the service layer, describes a set of methods
     * for working with objects of the class {@link Category}.
     *
     * @see CategoryService
     */
    private final CategoryService categoryService;

    /**
     * The interface of the service layer, describes a set of methods
     * for working with objects of the class {@link Company}.
     *
     * @see CompanyService
     */
    private final CompanyService companyService;

    /**
     * The interface of the service layer, describes a set of methods
     * for working with objects of the class {@link Section}.
     *
     * @see SectionService
     */
    private final SectionService sectionService;

    /**
     * The interface of the service layer, describes a set of methods
     * for working with objects of the class {@link User}.
     *
     * @see UserService
     */
    private final UserService userService;

    /**
     * The interface of the service layer, describes a set of methods
     * for working with objects of the class {@link Response}.
     *
     * @see ResponseService
     */
    private final ResponseService responseService;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param articleService  a implementation
     *                        of the {@link ArticleService} interface.
     * @param categoryService a implementation
     *                        of the {@link CategoryService} interface.
     * @param companyService  a implementation
     *                        of the {@link CompanyService} interface.
     * @param sectionService  a implementation
     *                        of the {@link SectionService} interface.
     * @param userService     a implementation
     *                        of the {@link UserService} interface.
     * @param responseService a implementation
     *                        of the {@link ResponseService} interface.
     * @see ArticleService
     * @see CategoryService
     * @see CompanyService
     * @see SectionService
     * @see UserService
     * @see ResponseService
     */
    MainMVFabricImpl(
            final ArticleService articleService,
            final CategoryService categoryService,
            final CompanyService companyService,
            final SectionService sectionService,
            final UserService userService,
            final ResponseService responseService
    ) {
        super();
        this.articleService = articleService;
        this.categoryService = categoryService;
        this.companyService = companyService;
        this.sectionService = sectionService;
        this.userService = userService;
        this.responseService = responseService;
    }

    /**
     * Creates and returns home page.
     *
     * @return The ready object of class ModelAndView.
     */
    @Override
    @Transactional(readOnly = true)
    public ModelAndView homePage() {
        final ModelAndView modelAndView = getDefaultModelAndView();
        final Company mainCompany = this.companyService.getMainCompanyToHome();
        modelAndView.addObject("company", mainCompany);
        modelAndView.addObject(
                "slides",
                mainCompany.getSlides()
        );
        modelAndView.addObject(
                "articles_list",
                this.articleService.sortByDate(
                        this.articleService.getAll(
                                isValidContent()
                        ),
                        true
                )
        );
        modelAndView.addObject("print_articles", 6);
        modelAndView.addObject("print_partners", 6);
        modelAndView.addObject(
                "responses",
                this.responseService.getAndSortByDate(true)
        );
        modelAndView.addObject("print_responses", 3);
        modelAndView.setViewName("client/main/index_page");
        return modelAndView;
    }

    /**
     * Creates and returns page with all sections.
     *
     * @return The ready object of class ModelAndView.
     * @see Section
     */
    @Override
    public ModelAndView allSectionsPage() {
        final ModelAndView modelAndView = getDefaultModelAndView();
        modelAndView.addObject(
                "sections_list",
                this.sectionService.sortByTitle(
                        this.sectionService.getAll(
                                isValidContent()
                        ),
                        false
                )
        );
        modelAndView.setViewName("client/section/all_page");
        return modelAndView;
    }

    /**
     * Creates and returns page wits all sections with categories.
     *
     * @return The ready object of class ModelAndView.
     * @see Section
     * @see Category
     */
    @Override
    @Transactional(readOnly = true)
    public ModelAndView sectionsWithCategoriesPage() {
        final ModelAndView modelAndView = getDefaultModelAndView();
        final Collection<Section> sections = this.sectionService.getAll(
                isValidContent()
        );
        for (Section section : sections) {
            section.getCategories().size();
        }
        modelAndView.addObject("sections_list", sections);
        modelAndView.setViewName("client/section/with_categories_page");
        return modelAndView;
    }

    /**
     * Creates and returns page with all categories.
     *
     * @return The ready object of class ModelAndView.
     * @see Category
     */
    @Override
    public ModelAndView allCategoriesPage() {
        final ModelAndView modelAndView = getDefaultModelAndView();
        modelAndView.addObject(
                "categories_list",
                this.categoryService.sortByTitle(
                        this.categoryService.getAll(
                                isValidContent()
                        ),
                        false
                )
        );
        modelAndView.setViewName("client/category/all_page");
        return modelAndView;
    }

    /**
     * Creates and returns page with all articles sorted by date.
     *
     * @return The ready object of class ModelAndView.
     * @see Article
     */
    @Override
    public ModelAndView allArticlesPage() {
        final ModelAndView modelAndView = getDefaultModelAndView();
        modelAndView.addObject(
                "articles_list",
                this.articleService.sortByDate(
                        this.articleService.getAll(
                                isValidContent()
                        ),
                        true
                )
        );
        modelAndView.setViewName("client/article/all_page");
        return modelAndView;
    }

    /**
     * Creates and returns page with information about main company.
     *
     * @return The ready object of class ModelAndView.
     * @see Company
     */
    @Override
    public ModelAndView aboutCompanyPage() {
        final ModelAndView modelAndView = getDefaultModelAndView();
        modelAndView.addObject("company", this.companyService.getMainCompany());
        modelAndView.addObject(
                "users_list",
                this.userService.getAll(
                        isValidContent()
                )
        );
        modelAndView.setViewName("client/company/main_page");
        return modelAndView;
    }

    /**
     * Creates and returns page with contacts information about main company.
     *
     * @return The ready object of class ModelAndView.
     */
    @Override
    public ModelAndView contactsPage() {
        final ModelAndView modelAndView = getDefaultModelAndView();
        modelAndView.addObject("company", this.companyService.getMainCompany());
        modelAndView.setViewName("client/company/contacts_page");
        return modelAndView;
    }

    /**
     * Creates and returns page with all partners.
     *
     * @return The ready object of class ModelAndView.
     * @see Company
     */
    @Override
    public ModelAndView allPartnersPage() {
        final ModelAndView modelAndView = getDefaultModelAndView();
        modelAndView.addObject(
                "partners_list",
                this.companyService.sortByTitle(
                        this.companyService.getPartners(
                                isValidContent()
                        ),
                        false
                )
        );
        modelAndView.setViewName("client/company/all_page");
        return modelAndView;
    }

    /**
     * Creates and returns page with one section with parameter url.
     *
     * @param url a url of the section to return.
     * @return The ready object of class ModelAndView.
     * @see Section
     */
    @Override
    public ModelAndView sectionPage(final String url) {
        final ModelAndView modelAndView = getDefaultModelAndView();
        final Section section = this.sectionService.getByUrl(
                url,
                isValidContent()
        );
        modelAndView.addObject("section", section);
        modelAndView.addObject(
                "categories_list",
                this.categoryService.sortByTitle(
                        this.categoryService.filteredByValid(
                                section.getCategories()
                        ),
                        false
                )
        );
        modelAndView.setViewName("client/section/one_page");
        return modelAndView;
    }

    /**
     * Creates and returns page with all categories
     * in the section with parameter url.
     *
     * @param url a url of the section.
     * @return The ready object of class ModelAndView.
     * @see Category
     * @see Section
     */
    @Override
    public ModelAndView categoriesInTheSectionPage(final String url) {
        final ModelAndView modelAndView = sectionPage(url);
        modelAndView.setViewName("client/category/all_page");
        return modelAndView;
    }

    /**
     * Creates and returns page with one category with parameter url.
     *
     * @param url a url of the category to return.
     * @return The ready object of class ModelAndView.
     * @see Category
     */
    @Override
    public ModelAndView categoryPage(final String url) {
        final ModelAndView modelAndView = getDefaultModelAndView();
        final Category category = this.categoryService.getByUrl(
                url,
                isValidContent()
        );
        modelAndView.addObject("category", category);
        modelAndView.addObject(
                "articles_list",
                this.articleService.sortByTitle(
                        this.articleService.filteredByValid(
                                category.getArticles()
                        ),
                        false
                )
        );
        modelAndView.setViewName("client/category/one_page");
        return modelAndView;
    }

    /**
     * Creates and returns page with one article with parameter url.
     *
     * @param url a url of the article to return.
     * @return The ready object of class ModelAndView.
     * @see Article
     */
    @Override
    public ModelAndView articleByUrlPage(final String url) {
        return articlePage(
                this.articleService.getByUrl(
                        url,
                        isValidContent()
                )
        );
    }

    /**
     * Creates and returns page with one article with parameter number.
     *
     * @param number a number of the article to return.
     * @return The ready object of class ModelAndView.
     * @see Article
     */
    @Override
    public ModelAndView articleByNumberPage(final String number) {
        return articlePage(
                this.articleService.getByNumber(
                        number,
                        isValidContent()
                )
        );
    }

    /**
     * Creates and returns page with one partner with parameter url.
     *
     * @param url a url of the partner to return.
     * @return The ready object of class ModelAndView.
     * @see Company
     */
    @Override
    public ModelAndView partnerPage(final String url) {
        final ModelAndView modelAndView = getDefaultModelAndView();
        modelAndView.addObject(
                "company",
                this.companyService.getByUrl(
                        url,
                        isValidContent()
                )
        );
        modelAndView.setViewName("client/company/one_page");
        return modelAndView;
    }

    /**
     * Creates and returns page with all responses.
     *
     * @return The ready object of class ModelAndView.
     * @see Response
     */
    @Override
    public ModelAndView allResponsesPage() {
        final ModelAndView modelAndView = getDefaultModelAndView();
        modelAndView.addObject(
                "responses_list",
                this.responseService.sortByDate(
                        this.responseService.getAll(
                                isValidContent()
                        ),
                        true
                )
        );
        modelAndView.setViewName("client/response/all_page");
        return modelAndView;
    }

    /**
     * Creates and returns default modelAndView.
     *
     * @return The ready object of class ModelAndView.
     */
    @Override
    public ModelAndView getDefaultModelAndView() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(
                "main_company",
                this.companyService.getMainCompany()
        );
        modelAndView.addObject(
                "sections",
                this.sectionService.sortByTitle(
                        this.sectionService.getAll(
                                isValidContent()
                        ),
                        false
                )
        );
        modelAndView.addObject(
                "partners",
                this.companyService.getPartners(
                        isValidContent()
                )
        );
        return modelAndView;
    }

    /**
     * Validates output objects.
     *
     * @return Returns {@code true} if need to return valid objects,
     * {@code false} otherwise.
     */
    protected abstract boolean isValidContent();

    /**
     * Return fabric name which working.
     *
     * @return The fabric class name.
     */
    @Override
    public abstract String getFabricName();

    /**
     * Adds authenticated user to the modelAndView.
     *
     * @param modelAndView a model and view to update.
     * @see User
     */
    @Override
    public abstract void addAuthUser(final ModelAndView modelAndView);

    /**
     * Returns page with the article.
     *
     * @param article the article to return.
     * @return The ready object of class ModelAndView.
     */
    private ModelAndView articlePage(final Article article) {
        final ModelAndView modelAndView = getDefaultModelAndView();
        modelAndView.addObject("article", article);
        modelAndView.addObject("slides_list", article.getSlides());
        modelAndView.addObject("videos_list", article.getVideos());
        modelAndView.setViewName("client/article/one_page");
        return modelAndView;
    }
}
