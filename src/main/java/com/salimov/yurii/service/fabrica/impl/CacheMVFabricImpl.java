package com.salimov.yurii.service.fabrica.impl;

import com.salimov.yurii.entity.*;
import com.salimov.yurii.service.fabrica.interfaces.CacheMVFabric;
import com.salimov.yurii.service.fabrica.interfaces.MainMVFabric;
import com.salimov.yurii.util.cache.Cache;
import org.springframework.web.servlet.ModelAndView;

/**
 * The class implements a set of standard methods for creates
 * and returns the main modelAndViews which saves in cache.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see MainMVFabric
 * @see CacheMVFabric
 * @see Cache
 */
public final class CacheMVFabricImpl implements CacheMVFabric {

    /**
     * The home page key.
     */
    private final static String HOME_PAGE_KEY = "Home";

    /**
     * The all categories page key.
     */
    private final static String ALL_CATEGORIES_KEY = "All Categories";

    /**
     * The all articles page key.
     */
    private final static String ALL_ARTICLES_KEY = "All Articles";

    /**
     * The all sort articles page key.
     */
    private final static String ALL_SORT_ARTICLES_KEY = "All sort Articles";

    /**
     * The main company page key.
     */
    private final static String MAIN_COMPANY_KEY = "Main Company";

    /**
     * The contacts (address) page key.
     */
    private final static String CONTACTS_KEY = "Contacts";

    /**
     * The all partners companies page key.
     */
    private final static String ALL_PARTNERS_KEY = "All Partners";

    /**
     * The all sort partners companies page key.
     */
    private final static String ALL_SORT_PARTNERS_KEY = "All Sort Partners";

    /**
     * The category by url page key.
     */
    private final static String CATEGORY_BY_URL_KEY = "Category By Url ";

    /**
     * The category by url with sort articles page key.
     */
    private final static String CATEGORY_BY_URL_WITH_SORT_ARTICLES_KEY = "Category By Url with sort articles";

    /**
     * The article by url page key.
     */
    private final static String ARTICLE_BY_URL_KEY = "Article By Url";

    /**
     * The article by number page key.
     */
    private final static String ARTICLE_BY_NUMBER_KEY = "Article By Number";

    /**
     * The company by url page key.
     */
    private final static String COMPANY_BY_URL_KEY = "Company by url";

    /**
     * The all responses page key.
     */
    private final static String ALL_RESPONSES_KEY = "All Responses";

    /**
     * The all sort responses page key.
     */
    private final static String ALL_SORT_RESPONSES_KEY = "All Sort Responses";

    /**
     * The default modelAndView key.
     */
    private final static String DEFAULT_MAV_KEY = "Default ModelAndView";

    /**
     * The interface provides a set of standard methods for creates
     * and returns the main modelAndViews.
     *
     * @see MainMVFabric
     */
    private final MainMVFabric fabric;

    /**
     * Constructor.
     * Initializes a implementation of the interface.
     *
     * @param fabric a implementation of the {@link MainMVFabric} interface.
     * @see MainMVFabric
     */
    public CacheMVFabricImpl(final MainMVFabric fabric) {
        this.fabric = fabric;
    }

    /**
     * Creates and returns home page.
     *
     * @return The ready object of class ModelAndView.
     */
    @Override
    public ModelAndView homePage() {
        ModelAndView modelAndView = get(HOME_PAGE_KEY);
        if (modelAndView == null) {
            modelAndView = this.fabric.homePage();
            put(modelAndView, HOME_PAGE_KEY);
        }
        addAuthUser(modelAndView);
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
        ModelAndView modelAndView = get(ALL_CATEGORIES_KEY);
        if (modelAndView == null) {
            modelAndView = this.fabric.allCategoriesPage();
            put(modelAndView, ALL_CATEGORIES_KEY);
        }
        addAuthUser(modelAndView);
        return modelAndView;
    }

    /**
     * Creates and returns page with all articles.
     *
     * @return The ready object of class ModelAndView.
     * @see Article
     */
    @Override
    public ModelAndView allArticlesPage() {
        ModelAndView modelAndView = get(ALL_ARTICLES_KEY);
        if (modelAndView == null) {
            modelAndView = this.fabric.allArticlesPage();
            put(modelAndView, ALL_ARTICLES_KEY);
        }
        addAuthUser(modelAndView);
        return modelAndView;
    }

    /**
     * Creates and returns page with all articles sorted by sortType.
     *
     * @param sortType a sort type.
     * @param revers   a sorting direction, {@code true} or {@code false}.
     * @return The ready object of class ModelAndView.
     */
    @Override
    public ModelAndView allSortArticlesPage(
            final String sortType,
            final boolean revers
    ) {
        final String key = ALL_SORT_ARTICLES_KEY
                + ", " + sortType + ", " + revers;
        ModelAndView modelAndView = get(key);
        if (modelAndView == null) {
            modelAndView = this.fabric.allSortArticlesPage(
                    sortType, revers
            );
            put(modelAndView, key);
        }
        addAuthUser(modelAndView);
        modelAndView.addObject("sort", sortType);
        modelAndView.addObject("revers", !revers);
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
        ModelAndView modelAndView = get(MAIN_COMPANY_KEY);
        if (modelAndView == null) {
            modelAndView = this.fabric.aboutCompanyPage();
            put(modelAndView, MAIN_COMPANY_KEY);
        }
        addAuthUser(modelAndView);
        return modelAndView;
    }

    /**
     * Creates and returns page with contacts information about main company.
     *
     * @return The ready object of class ModelAndView.
     */
    @Override
    public ModelAndView contactsPage() {
        ModelAndView modelAndView = get(CONTACTS_KEY);
        if (modelAndView == null) {
            modelAndView = this.fabric.contactsPage();
            put(modelAndView, CONTACTS_KEY);
        }
        addAuthUser(modelAndView);
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
        ModelAndView modelAndView = get(ALL_PARTNERS_KEY);
        if (modelAndView == null) {
            modelAndView = this.fabric.allPartnersPage();
            put(modelAndView, ALL_PARTNERS_KEY);
        }
        addAuthUser(modelAndView);
        return modelAndView;
    }

    /**
     * Creates and returns page with all sorted partners.
     *
     * @param revers a sorting direction, {@code true} or {@code false}.
     * @return The ready object of class ModelAndView.
     */
    public ModelAndView allSortPartnersByTitlePage(
            final boolean revers
    ) {
        final String key = ALL_SORT_PARTNERS_KEY + ", " + revers;
        ModelAndView modelAndView = get(key);
        if (modelAndView == null) {
            modelAndView = this.fabric.allSortPartnersByTitlePage(revers);
            put(modelAndView, key);
        }
        addAuthUser(modelAndView);
        modelAndView.addObject("revers", !revers);
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
        final String key = CATEGORY_BY_URL_KEY + url;
        ModelAndView modelAndView = get(key);
        if (modelAndView == null) {
            modelAndView = this.fabric.categoryPage(url);
            put(modelAndView, key);
        }
        addAuthUser(modelAndView);
        return modelAndView;
    }

    /**
     * Creates and returns page with category
     * with all articles sorted by sortType.
     *
     * @param url      a category URL.
     * @param sortType a sort type.
     * @param revers   a sorting direction, {@code true} or {@code false}.
     * @return The ready object of class ModelAndView.
     */
    public ModelAndView categoryWithSortArticlesPage(
            final String url,
            final String sortType,
            final boolean revers
    ) {
        final String key = CATEGORY_BY_URL_WITH_SORT_ARTICLES_KEY
                + ", " + url + ", " + sortType + ", " + revers;
        ModelAndView modelAndView = get(key);
        if (modelAndView == null) {
            modelAndView = this.fabric.categoryWithSortArticlesPage(
                    url, sortType, revers
            );
            put(modelAndView, key);
        }
        addAuthUser(modelAndView);
        modelAndView.addObject("revers", !revers);
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
        final String key = ARTICLE_BY_URL_KEY + " " + url;
        ModelAndView modelAndView = get(key);
        if (modelAndView == null) {
            modelAndView = this.fabric.articleByUrlPage(url);
            put(modelAndView, key);
        }
        addAuthUser(modelAndView);
        return modelAndView;
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
        final String key = ARTICLE_BY_NUMBER_KEY + " " + number;
        ModelAndView modelAndView = get(key);
        if (modelAndView == null) {
            modelAndView = this.fabric.articleByNumberPage(number);
            put(modelAndView, key);
        }
        addAuthUser(modelAndView);
        return modelAndView;
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
        final String key = COMPANY_BY_URL_KEY + " " + url;
        ModelAndView modelAndView = get(key);
        if (modelAndView == null) {
            modelAndView = this.fabric.partnerPage(url);
            put(modelAndView, key);
        }
        addAuthUser(modelAndView);
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
        ModelAndView modelAndView = get(ALL_RESPONSES_KEY);
        if (modelAndView == null) {
            modelAndView = this.fabric.allResponsesPage();
            put(modelAndView, ALL_RESPONSES_KEY);
        }
        addAuthUser(modelAndView);
        return modelAndView;
    }

    /**
     * Creates and returns page with all sorted responses.
     *
     * @param revers a sorting direction, {@code true} or {@code false}.
     * @return The ready object of class ModelAndView.
     */
    public ModelAndView allSortResponsesByDatePage(
            final boolean revers
    ) {
        final String key = ALL_SORT_RESPONSES_KEY + ", " + revers;
        ModelAndView modelAndView = get(key);
        if (modelAndView == null) {
            modelAndView = this.fabric.allSortResponsesByDatePage(revers);
            put(modelAndView, key);
        }
        addAuthUser(modelAndView);
        modelAndView.addObject("revers", !revers);
        return modelAndView;
    }

    /**
     * Creates and returns default modelAndView.
     *
     * @return The ready object of class ModelAndView.
     */
    @Override
    public ModelAndView getDefaultModelAndView() {
        ModelAndView modelAndView = get(DEFAULT_MAV_KEY);
        if (modelAndView == null) {
            modelAndView = this.fabric.getDefaultModelAndView();
            put(modelAndView, DEFAULT_MAV_KEY);
        }
        addAuthUser(modelAndView);
        return modelAndView;
    }

    /**
     * Adds authenticated user to the modelAndView.
     *
     * @param modelAndView a model and view to update.
     * @see User
     */
    @Override
    public void addAuthUser(final ModelAndView modelAndView) {
        this.fabric.addAuthUser(modelAndView);
    }

    /**
     * Returns model and view from cache with key.
     *
     * @param key a model and view key in the cache.
     * @return The model and view with key.
     */
    private ModelAndView get(final String key) {
        return (ModelAndView) Cache.get(key);
    }

    /**
     * Saves object in the cache with default lifetime.
     *
     * @param key          a model and view key in the cache.
     * @param modelAndView a model and view to save.
     */
    private void put(
            final ModelAndView modelAndView,
            final String key
    ) {
        Cache.put(getKey(key), modelAndView);
    }

    /**
     * Returns a key to cache.
     *
     * @param key a input key.
     * @return The key to cache.
     */
    private String getKey(final String key) {
        return key + (isValidContent() ? " for admin" : " for client");
    }

    /**
     * Validates output objects.
     *
     * @return Returns {@code true} if need to return valid objects,
     * {@code false} otherwise.
     */
    public boolean isValidContent() {
        return this.fabric.isValidContent();
    }
}
