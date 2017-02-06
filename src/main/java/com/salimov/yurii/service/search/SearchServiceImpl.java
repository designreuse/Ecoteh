package com.salimov.yurii.service.search;

import com.salimov.yurii.entity.Model;
import com.salimov.yurii.service.data.interfaces.*;
import com.salimov.yurii.service.fabrica.interfaces.ClientMVFabric;
import com.salimov.yurii.service.fabrica.interfaces.MainMVFabric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class of the service layer, implements a set of methods
 * for search for content on the site.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see CategoryService
 * @see ArticleService
 * @see CompanyService
 * @see UserService
 * @see MainMVFabric
 * @see ClientMVFabric
 */
@Service
@ComponentScan(basePackages = "com.salimov.yurii.service")
@SuppressWarnings("SpringMVCViewInspection")
public class SearchServiceImpl implements SearchService {

    /**
     * The keywords of home page.
     */
    private final static String HOME_KEYWORDS
            = "домой, главная, index, home";

    /**
     * The keywords of page with all categories.
     */
    private final static String ALL_CATEGORIES_KEYWORDS
            = "все категории, all categories";

    /**
     * The keywords of page with all articles.
     */
    private final static String ALL_ARTICLES_KEYWORDS
            = "все статьи, all articles";

    /**
     * The keywords of page with information about main company.
     */
    private final static String ABOUT_COMPANY_KEYWORDS =
            "о компании, описание, main company, " +
                    "about company, about main company";

    /**
     * The keywords of page with contacts of main company.
     */
    private final static String CONTACTS_KEYWORDS =
            "контакты, позвонить, номер телефона, адресс, " +
            "как доехать, почта, электронная почта, e-mail, contacts, address";

    /**
     * The keywords of page with all companies.
     */
    private final static String COMPANY_KEYWORDS
            = "все партнеры, все компании, " +
            "all company, all partners";

    /**
     * The keywords of page with all responses.
     */
    private final static String RESPONSES_KEYWORDS
            = "все отзывы, all responses";

    /**
     * The keywords of page with personnel.
     */
    private final static String USER_KEYWORDS
            = "персонал, работники";

    /**
     * The implementation of the interface provides a set of standard methods
     * for creates and returns the main modelAndViews.
     *
     * @see MainMVFabric
     */
    private final MainMVFabric fabric;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link com.salimov.yurii.entity.Category}
     * class.
     *
     * @see CategoryService
     */
    private final CategoryService categoryService;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link com.salimov.yurii.entity.Article}
     * class.
     *
     * @see ArticleService
     */
    private final ArticleService articleService;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link com.salimov.yurii.entity.Company}
     * class.
     *
     * @see CompanyService
     */
    private final CompanyService companyService;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link com.salimov.yurii.entity.User}
     * class.
     *
     * @see UserService
     */
    private final UserService userService;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param fabric          a implementation
     *                        of the {@link ClientMVFabric} interface.
     * @param categoryService a implementation
     *                        of the {@link CategoryService} interface.
     * @param articleService  a implementation
     *                        of the {@link ArticleService} interface.
     * @param companyService  a implementation
     *                        of the {@link CompanyService} interface.
     * @param userService     a implementation
     *                        of the {@link UserService} interface.
     * @see ClientMVFabric
     * @see CategoryService
     * @see ArticleService
     * @see CompanyService
     * @see UserService
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public SearchServiceImpl(
            final ClientMVFabric fabric,
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
     * @param keywords  a keywords for content search.
     * @param content   a objects list for content search.
     * @param howSearch a search mode.
     * @return The ready object of class ModelAndView.
     */
    public ModelAndView search(
            final String keywords,
            final String content,
            final boolean howSearch
    ) {
        final String _content = isNotBlank(content) ? content : "all";
        ModelAndView modelAndView = new ModelAndView();
        if (isNotBlank(keywords)) {
            final boolean isPage = getPageBySearch(keywords, modelAndView);
            if (!isPage) {
                modelAndView = prepareDefaultSearchPage(keywords);
                searchFromContent(keywords, howSearch, _content, modelAndView);
            }
        } else {
            modelAndView = prepareDefaultSearchPage(keywords);
        }
        addKeys(_content, howSearch, modelAndView);
        return modelAndView;
    }

    /**
     * Returns one of the pages of a site if it corresponds to the keywords.
     *
     * @param keywords     a keywords for content search.
     * @param modelAndView a object of class ModelAndView for to update.
     * @return {@code true} if page is exist, {@code false} otherwise.
     */
    private static boolean getPageBySearch(
            final String keywords,
            final ModelAndView modelAndView
    ) {
        final String temp = keywords.toLowerCase();
        String viewName = null;
        if (HOME_KEYWORDS.contains(temp)) {
            viewName = "redirect:/home";
        } else if (ALL_CATEGORIES_KEYWORDS.contains(temp)) {
            viewName = "redirect:/category/all";
        } else if (ALL_ARTICLES_KEYWORDS.contains(temp)) {
            viewName = "redirect:/article/all";
        } else if (ABOUT_COMPANY_KEYWORDS.contains(temp)) {
            viewName = "redirect:/company/main";
        } else if (CONTACTS_KEYWORDS.contains(temp)) {
            viewName = "redirect:/contacts";
        } else if (COMPANY_KEYWORDS.contains(temp)) {
            viewName = "redirect:/company/all";
        } else if (RESPONSES_KEYWORDS.contains(temp)) {
            viewName = "redirect:/responses";
        } else if (USER_KEYWORDS.contains(temp)) {
            viewName = "redirect:/company/main";
        }
        modelAndView.setViewName(viewName);
        return viewName != null;
    }

    /**
     * Searches for all content.
     *
     * @param keywords     a keywords for content search.
     * @param howSearch    a search mode.
     * @param content      a objects list for content search.
     * @param modelAndView a object of class ModelAndView for to update.
     */
    private void searchFromContent(
            final String keywords,
            final boolean howSearch,
            final String content,
            final ModelAndView modelAndView
    ) {
        if (isNotBlank(keywords)) {
            if (content.contains("all")) {
                searchByAllContent(
                        keywords,
                        howSearch,
                        modelAndView
                );
            } else {
                searchByChooseContent(
                        content,
                        keywords,
                        howSearch,
                        modelAndView
                );
            }
        }
    }

    /**
     * Search by all content.
     *
     * @param keywords     a keywords for content search.
     * @param howSearch    a search mode.
     * @param modelAndView a object of class ModelAndView for to update.
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
     * @param content      a objects list for content search.
     * @param keywords     a keywords for content search.
     * @param howSearch    a search mode.
     * @param modelAndView a object of class ModelAndView for to update.
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
     * @param keywordArray a keyword array for content search.
     * @param howSearch    a search mode.
     * @param dataService  a implementation of the interface describes a set
     *                     of methods for working with objects.
     * @param name         a name of the object to add in modelAndView.
     * @param modelAndView a object of class ModelAndView for to update.
     * @param <T>          entity type, extends {@link Model}.
     */
    private static <T extends Model> void searchFromModelAndAdd(
            final String[] keywordArray,
            final boolean howSearch,
            final DataService<T, Long> dataService,
            final String name,
            final ModelAndView modelAndView
    ) {
        modelAndView.addObject(
                name,
                searchFromModel(
                        keywordArray, howSearch, dataService
                )
        );
    }

    /**
     * Searches for some content.
     *
     * @param keywordArray a keyword array for content search.
     * @param howSearch    a search mode.
     * @param dataService  a implementation of the interface describes a set
     *                     of methods for working with objects.
     * @param <T>          entity type, extends {@link Model}.
     * @return The list of found objects.
     */
    private static <T extends Model> List<T> searchFromModel(
            final String[] keywordArray,
            final boolean howSearch,
            final DataService<T, Long> dataService
    ) {
        final List<T> models = new ArrayList<>();
        for (T model : dataService.getAll()) {
            for (String keyword : keywordArray) {
                if (howSearch) {
                    keyword = " " + keyword + " ";
                }
                if (model.toString().toLowerCase().contains(keyword)) {
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
     * @param keywords a keywords for content search.
     * @return The ready object of class ModelAndView.
     */
    private ModelAndView prepareDefaultSearchPage(final String keywords) {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("keywords", keywords);
        modelAndView.setViewName("client/search/result_page");
        return modelAndView;
    }

    /**
     * Adds content keys to modelAndView.
     *
     * @param content      a objects list for content search.
     * @param howSearch    a search mode.
     * @param modelAndView a object of class ModelAndView for to update.
     */
    private static void addKeys(
            final String content,
            final boolean howSearch,
            final ModelAndView modelAndView
    ) {
        modelAndView.addObject(
                "in_categories", content.contains("in_categories")
        );
        modelAndView.addObject(
                "in_articles", content.contains("in_articles")
        );
        modelAndView.addObject(
                "in_companies", content.contains("in_companies")
        );
        modelAndView.addObject("all", content.contains("all"));
        modelAndView.addObject("is_search", true);
        modelAndView.addObject("how_search", howSearch);
    }
}
