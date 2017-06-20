package ua.com.ecoteh.service.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.entity.article.ArticleEntity;
import ua.com.ecoteh.entity.category.CategoryEntity;
import ua.com.ecoteh.entity.company.CompanyEntity;
import ua.com.ecoteh.entity.model.Model;
import ua.com.ecoteh.entity.user.UserEntity;
import ua.com.ecoteh.service.data.*;
import ua.com.ecoteh.service.fabrica.MainMVFabric;

import java.util.ArrayList;
import java.util.List;

import static ua.com.ecoteh.service.search.DefaultPage.*;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * The class of the service layer, implements a set of methods
 * for search for content on the site.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Service
@ComponentScan(basePackages = "ua.com.ecoteh.service")
@SuppressWarnings("SpringMVCViewInspection")
public final class SearchServiceImpl implements SearchService {

    /**
     * The implementation of the interface provides a set of standard methods
     * for creates and returns the main modelAndViews.
     */
    private final MainMVFabric fabric;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link CategoryEntity}
     * class.
     */
    private final CategoryService categoryService;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link ArticleEntity}
     * class.
     */
    private final ArticleService articleService;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link CompanyEntity}
     * class.
     */
    private final CompanyService companyService;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link UserEntity}
     * class.
     */
    private final UserService userService;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param fabric          the implementation of the {@link MainMVFabric} interface.
     * @param categoryService the implementation of the {@link CategoryService} interface.
     * @param articleService  the implementation of the {@link ArticleService} interface.
     * @param companyService  the implementation of the {@link CompanyService} interface.
     * @param userService     the implementation of the {@link UserService} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public SearchServiceImpl(
            @Qualifier("cacheMVFabricImpl") final MainMVFabric fabric,
            final CategoryService categoryService,
            final ArticleService articleService,
            final CompanyService companyService,
            final UserService userService
    ) {
        this.fabric = fabric;
        this.categoryService = categoryService;
        this.articleService = articleService;
        this.companyService = companyService;
        this.userService = userService;
    }

    /**
     * Returns the page with the found content.
     *
     * @param keywords  the keywords for content search.
     * @param content   the objects list for content search.
     * @param howSearch the search mode.
     * @return The ready object of class ModelAndView.
     */
    @Override
    public ModelAndView search(
            final String keywords,
            final String content,
            final boolean howSearch
    ) {
        final String _content = isNotEmpty(content) ? content : "all";
        ModelAndView modelAndView = new ModelAndView();
        if (isNotEmpty(keywords)) {
            final boolean isPage = getPageBySearch(keywords, modelAndView);
            if (!isPage) {
                modelAndView = prepareDefaultSearchPage(keywords);
                searchFromContent(keywords, howSearch, _content, modelAndView);
            }
        } else {
            modelAndView = prepareDefaultSearchPage(keywords);
        }
        addKeys(_content, howSearch, modelAndView);
        modelAndView.addObject("authorized_user", this.userService.getAuthenticatedUser());
        return modelAndView;
    }

    /**
     * Returns one of the pages of a site if it corresponds to the keywords.
     *
     * @param keywords     the keywords for content search.
     * @param modelAndView the object of class ModelAndView for to update.
     * @return true if page is exist, false otherwise.
     */
    private boolean getPageBySearch(
            final String keywords,
            final ModelAndView modelAndView
    ) {
        final String temp = keywords.toLowerCase();
        String viewName = "";
        if (HOME_KEYWORDS.contains(temp)) {
            viewName = HOME_URL;
        } else if (ALL_CATEGORIES_KEYWORDS.contains(temp)) {
            viewName = ALL_CATEGORIES_URL;
        } else if (ALL_ARTICLES_KEYWORDS.contains(temp)) {
            viewName = ALL_ARTICLES_URL;
        } else if (ABOUT_COMPANY_KEYWORDS.contains(temp)) {
            viewName = ABOUT_COMPANY_URL;
        } else if (CONTACTS_KEYWORDS.contains(temp)) {
            viewName = CONTACTS_URL;
        } else if (COMPANY_KEYWORDS.contains(temp)) {
            viewName = COMPANY_URL;
        } else if (RESPONSES_KEYWORDS.contains(temp)) {
            viewName = RESPONSES_URL;
        } else if (USERS_KEYWORDS.contains(temp)) {
            viewName = USERS_URL;
        }
        modelAndView.setViewName(viewName);
        return isNotEmpty(viewName);
    }

    /**
     * Searches for all content.
     *
     * @param keywords     the keywords for content search.
     * @param howSearch    the search mode.
     * @param content      the objects list for content search.
     * @param modelAndView the object of class ModelAndView for to update.
     */
    private void searchFromContent(
            final String keywords,
            final boolean howSearch,
            final String content,
            final ModelAndView modelAndView
    ) {
        if (isNotEmpty(keywords)) {
            if (content.contains("all")) {
                searchByAllContent(keywords, howSearch, modelAndView);
            } else {
                searchByChooseContent(content, keywords, howSearch, modelAndView);
            }
        }
    }

    /**
     * Search by all content.
     *
     * @param keywords     the keywords for content search.
     * @param howSearch    the search mode.
     * @param modelAndView the object of class ModelAndView for to update.
     */
    private void searchByAllContent(
            final String keywords,
            final boolean howSearch,
            final ModelAndView modelAndView
    ) {
        final String[] keywordArray = keywords.toLowerCase().split(", ");
        searchFromModelAndAdd(
                keywordArray, howSearch,
                this.categoryService,
                "categories_list",
                modelAndView
        );
        searchFromModelAndAdd(
                keywordArray, howSearch,
                this.articleService,
                "articles_list",
                modelAndView
        );
        searchFromModelAndAdd(
                keywordArray, howSearch,
                this.companyService,
                "partners_list",
                modelAndView
        );
        searchFromModelAndAdd(
                keywordArray, howSearch,
                this.userService,
                "users_list",
                modelAndView
        );
    }

    /**
     * Search by choose content.
     *
     * @param content      the objects list for content search.
     * @param keywords     the keywords for content search.
     * @param howSearch    the search mode.
     * @param modelAndView the object of class ModelAndView for to update.
     */
    private void searchByChooseContent(
            final String content,
            final String keywords,
            final boolean howSearch,
            final ModelAndView modelAndView
    ) {
        final String[] keywordArray = keywords.toLowerCase().split(", ");
        if (content.contains("in_categories")) {
            searchFromModelAndAdd(
                    keywordArray, howSearch,
                    this.categoryService,
                    "categories_list",
                    modelAndView
            );
        }
        if (content.contains("in_articles")) {
            searchFromModelAndAdd(
                    keywordArray, howSearch,
                    this.articleService,
                    "articles_list",
                    modelAndView
            );
        }
        if (content.contains("in_companies")) {
            searchFromModelAndAdd(
                    keywordArray, howSearch,
                    this.companyService,
                    "partners_list",
                    modelAndView
            );
        }
    }

    /**
     * Searches for some content and adds it to modelAndView.
     *
     * @param <T>          the entity type, extends {@link Model}.
     * @param keywordArray the keyword array for content search.
     * @param howSearch    the search mode.
     * @param dataService  the implementation of the interface describes a set
     *                     of methods for working with objects.
     * @param name         the name of the object to add in modelAndView.
     * @param modelAndView the object of class ModelAndView for to update.
     */
    private <T extends Model> void searchFromModelAndAdd(
            final String[] keywordArray,
            final boolean howSearch,
            final DataService<T> dataService,
            final String name,
            final ModelAndView modelAndView
    ) {
        final List<T> result = searchFromModel(keywordArray, howSearch, dataService);
        modelAndView.addObject(name, result);
    }

    /**
     * Searches for some content.
     *
     * @param <T>          the entity type, extends {@link Model}.
     * @param keywordArray the keyword array for content search.
     * @param howSearch    the search mode.
     * @param dataService  the implementation of the interface describes a set
     *                     of methods for working with objects.
     * @return The list of found objects.
     */
    private <T extends Model> List<T> searchFromModel(
            final String[] keywordArray,
            final boolean howSearch,
            final DataService<T> dataService
    ) {
        final List<T> models = new ArrayList<>();
        for (T model : dataService.getAll()) {
            for (String keyword : keywordArray) {
                if (howSearch) {
                    keyword = " " + keyword + " ";
                }
                if (model.toString().toLowerCase().contains(keyword.toLowerCase())) {
                    models.add(model);
                    break;
                }
            }
        }
        return models;
    }

    /**
     * Prepares default modelAndView.
     *
     * @param keywords the keywords for content search.
     * @return The ready object of class ModelAndView.
     */
    private ModelAndView prepareDefaultSearchPage(final String keywords) {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("keywords", keywords);
        modelAndView.setViewName("search/search");
        return modelAndView;
    }

    /**
     * Adds content keys to modelAndView.
     *
     * @param content      the objects list for content search.
     * @param howSearch    the search mode.
     * @param modelAndView the object of class ModelAndView for to update.
     */
    private void addKeys(
            final String content,
            final boolean howSearch,
            final ModelAndView modelAndView
    ) {
        modelAndView.addObject("in_categories", content.contains("in_categories"));
        modelAndView.addObject("in_articles", content.contains("in_articles"));
        modelAndView.addObject("in_companies", content.contains("in_companies"));
        modelAndView.addObject("all", content.contains("all"));
        modelAndView.addObject("is_search", true);
        modelAndView.addObject("how_search", howSearch);
    }
}
