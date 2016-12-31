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
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see MainMVFabric
 * @see CacheMVFabric
 * @see Cache
 */
public final class CacheMVFabricImpl implements CacheMVFabric {

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
        final String key = "Home";
        ModelAndView modelAndView = get(key);
        if (modelAndView == null) {
            modelAndView = this.fabric.homePage();
            put(modelAndView, key);
        }
        addAuthUser(modelAndView);
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
        final String key = "All Sections";
        ModelAndView modelAndView = get(key);
        if (modelAndView == null) {
            modelAndView = this.fabric.allSectionsPage();
            put(modelAndView, key);
        }
        addAuthUser(modelAndView);
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
    public ModelAndView sectionsWithCategoriesPage() {
        final String key = "Sections With Categories";
        ModelAndView modelAndView = get(key);
        if (modelAndView == null) {
            modelAndView = this.fabric.sectionsWithCategoriesPage();
            put(modelAndView, key);
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
        final String key = "All Categories";
        ModelAndView modelAndView = get(key);
        if (modelAndView == null) {
            modelAndView = this.fabric.allCategoriesPage();
            put(modelAndView, key);
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
        final String key = "All Articles";
        ModelAndView modelAndView = get(key);
        if (modelAndView == null) {
            modelAndView = this.fabric.allArticlesPage();
            put(modelAndView, key);
        }
        addAuthUser(modelAndView);
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
        final String key = "Main Company";
        ModelAndView modelAndView = get(key);
        if (modelAndView == null) {
            modelAndView = this.fabric.aboutCompanyPage();
            put(modelAndView, key);
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
        final String key = "Contacts";
        ModelAndView modelAndView = get(key);
        if (modelAndView == null) {
            modelAndView = this.fabric.contactsPage();
            put(modelAndView, key);
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
        final String key = "All Partners";
        ModelAndView modelAndView = get(key);
        if (modelAndView == null) {
            modelAndView = this.fabric.allPartnersPage();
            put(modelAndView, key);
        }
        addAuthUser(modelAndView);
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
        final String key = "Section " + url;
        ModelAndView modelAndView = get(key);
        if (modelAndView == null) {
            modelAndView = this.fabric.sectionPage(url);
            put(modelAndView, key);
        }
        addAuthUser(modelAndView);
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
        final String key = "Category In The Section " + url;
        ModelAndView modelAndView = get(key);
        if (modelAndView == null) {
            modelAndView = this.fabric.categoriesInTheSectionPage(url);
            put(modelAndView, key);
        }
        addAuthUser(modelAndView);
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
        final String key = "Category By Url " + url;
        ModelAndView modelAndView = get(key);
        if (modelAndView == null) {
            modelAndView = this.fabric.categoryPage(url);
            put(modelAndView, key);
        }
        addAuthUser(modelAndView);
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
        final String key = "Article By Url " + url;
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
        final String key = "Article By Number " + number;
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
        final String key = "Company " + url;
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
        final String key = "All Responses";
        ModelAndView modelAndView = get(key);
        if (modelAndView == null) {
            modelAndView = this.fabric.allResponsesPage();
            put(modelAndView, key);
        }
        addAuthUser(modelAndView);
        return modelAndView;
    }

    /**
     * Creates and returns default modelAndView.
     *
     * @return The ready object of class ModelAndView.
     */
    @Override
    public ModelAndView getDefaultModelAndView() {
        final String key = "Default ModelAndView";
        ModelAndView modelAndView = get(key);
        if (modelAndView == null) {
            modelAndView = this.fabric.getDefaultModelAndView();
            put(modelAndView, key);
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
     * Return fabric name which working.
     *
     * @return The fabric class name.
     */
    @Override
    public String getFabricName() {
        return this.fabric.getFabricName();
    }

    /**
     * Returns model and view from cache with key.
     *
     * @param key a model and view key in the cache.
     * @return The model and view with key.
     */
    private ModelAndView get(final String key) {
        return (ModelAndView) Cache.get(
                getKey(key)
        );
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
        Cache.put(
                getKey(key),
                modelAndView
        );
    }

    /**
     * Return fabric key + the key.
     *
     * @param key a model and view key in the cache.
     * @return The fabric key.
     */
    private String getKey(final String key) {
        return getFabricName() + " - " + key;
    }
}
