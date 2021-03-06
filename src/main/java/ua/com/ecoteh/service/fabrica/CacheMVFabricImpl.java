package ua.com.ecoteh.service.fabrica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.util.cache.Cache;

import static ua.com.ecoteh.service.fabrica.CachePageKey.*;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNull;

/**
 * The class implements a set of standard methods for creates
 * and returns the main modelAndViews which saves in cache.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Service
public final class CacheMVFabricImpl implements CacheMVFabric {

    /**
     * The interface provides a set of standard methods for creates
     * and returns the main modelAndViews.
     */
    private final MainMVFabric fabric;

    /**
     * Constructor.
     * Initializes a implementation of the interface.
     *
     * @param fabric the implementation of the {@link MainMVFabric} interface.
     */
    @Autowired
    public CacheMVFabricImpl(final MainMVFabric fabric) {
        this.fabric = fabric;
    }

    /**
     * Creates and returns home page.
     *
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView homePage() {
        ModelAndView modelAndView = getFromCache(HOME_PAGE_KEY);
        if (isNull(modelAndView)) {
            modelAndView = this.fabric.homePage();
            putToCache(modelAndView, HOME_PAGE_KEY);
        }
        addAuthUser(modelAndView);
        return modelAndView;
    }

    /**
     * Creates and returns a home page with one category.
     *
     * @param url the category URL.
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView homeCategory(final String url) {
        final String key = HOME_PAGE_KEY + " " + url;
        ModelAndView modelAndView = getFromCache(key);
        if (isNull(modelAndView)) {
            modelAndView = this.fabric.homeCategory(url);
            putToCache(modelAndView, key);
        }
        addAuthUser(modelAndView);
        return modelAndView;
    }

    /**
     * Creates and returns page with all categories.
     *
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView allCategoriesPage() {
        ModelAndView modelAndView = getFromCache(ALL_CATEGORIES_KEY);
        if (isNull(modelAndView)) {
            modelAndView = this.fabric.allCategoriesPage();
            putToCache(modelAndView, ALL_CATEGORIES_KEY);
        }
        addAuthUser(modelAndView);
        return modelAndView;
    }

    /**
     * Creates and returns page with all articles.
     *
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView allArticlesPage() {
        ModelAndView modelAndView = getFromCache(ALL_ARTICLES_KEY);
        if (isNull(modelAndView)) {
            modelAndView = this.fabric.allArticlesPage();
            putToCache(modelAndView, ALL_ARTICLES_KEY);
        }
        addAuthUser(modelAndView);
        return modelAndView;
    }

    /**
     * Creates and returns page with all articles sorted by sortType.
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
        final String key = ALL_SORT_ARTICLES_KEY + ", " + sortType + ", " + revers;
        ModelAndView modelAndView = getFromCache(key);
        if (isNull(modelAndView)) {
            modelAndView = this.fabric.allSortArticlesPage(sortType, revers);
            putToCache(modelAndView, key);
        }
        addAuthUser(modelAndView);
        modelAndView.addObject("sort", sortType);
        modelAndView.addObject("revers", !revers);
        return modelAndView;
    }

    /**
     * Creates and returns page with information about main company.
     *
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView aboutCompanyPage() {
        ModelAndView modelAndView = getFromCache(MAIN_COMPANY_KEY);
        if (isNull(modelAndView)) {
            modelAndView = this.fabric.aboutCompanyPage();
            putToCache(modelAndView, MAIN_COMPANY_KEY);
        }
        addAuthUser(modelAndView);
        return modelAndView;
    }

    /**
     * Creates and returns page with contacts information about main company.
     *
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView contactsPage() {
        ModelAndView modelAndView = getFromCache(CONTACTS_KEY);
        if (isNull(modelAndView)) {
            modelAndView = this.fabric.contactsPage();
            putToCache(modelAndView, CONTACTS_KEY);
        }
        addAuthUser(modelAndView);
        return modelAndView;
    }

    /**
     * Creates and returns page with all partners.
     *
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView allPartnersPage() {
        ModelAndView modelAndView = getFromCache(ALL_PARTNERS_KEY);
        if (isNull(modelAndView)) {
            modelAndView = this.fabric.allPartnersPage();
            putToCache(modelAndView, ALL_PARTNERS_KEY);
        }
        addAuthUser(modelAndView);
        return modelAndView;
    }

    /**
     * Creates and returns page with all sorted partners.
     *
     * @param revers the sorting direction, true or false.
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView allSortPartnersByTitlePage(final boolean revers) {
        final String key = ALL_SORT_PARTNERS_KEY + ", " + revers;
        ModelAndView modelAndView = getFromCache(key);
        if (isNull(modelAndView)) {
            modelAndView = this.fabric.allSortPartnersByTitlePage(revers);
            putToCache(modelAndView, key);
        }
        addAuthUser(modelAndView);
        modelAndView.addObject("revers", !revers);
        return modelAndView;
    }

    /**
     * Creates and returns page with one category with incoming URL.
     *
     * @param url the URL of the category to return.
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView categoryPage(final String url) {
        final String key = CATEGORY_BY_URL_KEY + url;
        ModelAndView modelAndView = getFromCache(key);
        if (isNull(modelAndView)) {
            modelAndView = this.fabric.categoryPage(url);
            putToCache(modelAndView, key);
        }
        addAuthUser(modelAndView);
        return modelAndView;
    }

    /**
     * Creates and returns page with category
     * with all articles sorted by sortType.
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
        final String key = CATEGORY_BY_URL_WITH_SORT_ARTICLES_KEY
                + ", " + url + ", " + sortType + ", " + revers;
        ModelAndView modelAndView = getFromCache(key);
        if (isNull(modelAndView)) {
            modelAndView = this.fabric.categoryWithSortArticlesPage(url, sortType, revers);
            putToCache(modelAndView, key);
        }
        addAuthUser(modelAndView);
        modelAndView.addObject("revers", !revers);
        return modelAndView;
    }

    /**
     * Creates and returns page with one article with incoming URL.
     *
     * @param url the URL of the article to return.
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView articleByUrlPage(final String url) {
        final String key = ARTICLE_BY_URL_KEY + " " + url;
        ModelAndView modelAndView = getFromCache(key);
        if (isNull(modelAndView)) {
            modelAndView = this.fabric.articleByUrlPage(url);
            putToCache(modelAndView, key);
        }
        addAuthUser(modelAndView);
        return modelAndView;
    }

    /**
     * Creates and returns page with one article with incoming number.
     *
     * @param number the number of the article to return.
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView articleByNumberPage(final String number) {
        final String key = ARTICLE_BY_NUMBER_KEY + " " + number;
        ModelAndView modelAndView = getFromCache(key);
        if (isNull(modelAndView)) {
            modelAndView = this.fabric.articleByNumberPage(number);
            putToCache(modelAndView, key);
        }
        addAuthUser(modelAndView);
        return modelAndView;
    }

    /**
     * Creates and returns page with one partner with incoming URL.
     *
     * @param url the URL of the partner to return.
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView partnerPage(final String url) {
        final String key = COMPANY_BY_URL_KEY + " " + url;
        ModelAndView modelAndView = getFromCache(key);
        if (isNull(modelAndView)) {
            modelAndView = this.fabric.partnerPage(url);
            putToCache(modelAndView, key);
        }
        addAuthUser(modelAndView);
        return modelAndView;
    }

    /**
     * Creates and returns page with all responses.
     *
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView allResponsesPage() {
        ModelAndView modelAndView = getFromCache(ALL_RESPONSES_KEY);
        if (isNull(modelAndView)) {
            modelAndView = this.fabric.allResponsesPage();
            putToCache(modelAndView, ALL_RESPONSES_KEY);
        }
        addAuthUser(modelAndView);
        return modelAndView;
    }

    /**
     * Creates and returns page with all sorted responses.
     *
     * @param revers the sorting direction, true or false.
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView allSortResponsesByDatePage(final boolean revers) {
        final String key = ALL_SORT_RESPONSES_KEY + ", " + revers;
        ModelAndView modelAndView = getFromCache(key);
        if (isNull(modelAndView)) {
            modelAndView = this.fabric.allSortResponsesByDatePage(revers);
            putToCache(modelAndView, key);
        }
        addAuthUser(modelAndView);
        modelAndView.addObject("revers", !revers);
        return modelAndView;
    }

    /**
     * Creates and returns a page with all posts sorted by date.
     *
     * @param revers the sorting direction, true or false.
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView allSortBlogPage(final String sortType, boolean revers) {
        final String key = SORT_BLOG_KEY + ", " + sortType + ", " + revers;
        ModelAndView modelAndView = getFromCache(key);
        if (isNull(modelAndView)) {
            modelAndView = this.fabric.allSortBlogPage(sortType, revers);
            putToCache(modelAndView, key);
        }
        addAuthUser(modelAndView);
        modelAndView.addObject("revers", !revers);
        return modelAndView;
    }

    /**
     * Creates and returns a page with all blog posts.
     *
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView blogPage() {
        ModelAndView modelAndView = getFromCache(BLOG_KEY);
        if (isNull(modelAndView)) {
            modelAndView = this.fabric.blogPage();
            putToCache(modelAndView, BLOG_KEY);
        }
        addAuthUser(modelAndView);
        return modelAndView;
    }

    /**
     * Creates and returns a page with one post with the incoming URL.
     *
     * @param url the URL of a post to return.
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView postByUrlPage(final String url) {
        final String key = POST_BY_URL_KEY + " " + url;
        ModelAndView modelAndView = getFromCache(key);
        if (isNull(modelAndView)) {
            modelAndView = this.fabric.postByUrlPage(url);
            putToCache(modelAndView, key);
        }
        addAuthUser(modelAndView);
        return modelAndView;
    }

    /**
     * Creates and returns default modelAndView.
     *
     * @return The ready object of the ModelAndView class.
     */
    @Override
    public ModelAndView getDefaultModelAndView() {
        ModelAndView home = getFromCache(DEFAULT_MAV_KEY);
        if (isNull(home)) {
            home = this.fabric.getDefaultModelAndView();
            putToCache(home, DEFAULT_MAV_KEY);
        }
        addAuthUser(home);
        return new ModelAndView(home.getViewName(), home.getModel());
    }

    /**
     * Adds authenticated user to the modelAndView.
     *
     * @param modelAndView the model and view to update.
     */
    @Override
    public void addAuthUser(final ModelAndView modelAndView) {
        this.fabric.addAuthUser(modelAndView);
    }

    /**
     * Validates output objects.
     *
     * @return Returns true if need to return valid objects,
     * false otherwise.
     */
    @Override
    public boolean isValidContent() {
        return this.fabric.isValidContent();
    }

    /**
     * Returns model and view from cache with key.
     *
     * @param key the model and view key in the cache.
     * @return The model and view with key.
     */
    private ModelAndView getFromCache(final String key) {
        final String cacheKey = prepareKey(key);
        return (ModelAndView) Cache.get(cacheKey);
    }

    /**
     * Saves object in the cache with default lifetime.
     *
     * @param key          the model and view key in the cache.
     * @param modelAndView the model and view to save.
     */
    private void putToCache(final ModelAndView modelAndView, final String key) {
        final String cacheKey = prepareKey(key);
        Cache.put(cacheKey, modelAndView);
    }

    /**
     * Returns a key to cache.
     *
     * @param key the input key.
     * @return The key to cache.
     */
    private String prepareKey(final String key) {
        return key + (isValidContent() ? " for client" : " for admin");
    }
}
