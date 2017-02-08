package com.salimov.yurii.service.fabrica.interfaces;

import com.salimov.yurii.entity.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * The interface provides a set of standard methods for creates
 * and returns the main modelAndViews.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface MainMVFabric {

    /**
     * Creates and returns home page.
     *
     * @return The ready object of class ModelAndView.
     */
    ModelAndView homePage();

    /**
     * Creates and returns page with all categories.
     *
     * @return The ready object of class ModelAndView.
     * @see Category
     */
    ModelAndView allCategoriesPage();

    /**
     * Creates and returns page with all articles.
     *
     * @return The ready object of class ModelAndView.
     * @see Article
     */
    ModelAndView allArticlesPage();

    /**
     * Creates and returns page with all articles sorted by sortType.
     *
     * @param sortType a sort type.
     * @param revers   a sorting direction, {@code true} or {@code false}.
     * @return The ready object of class ModelAndView.
     */
    ModelAndView allSortArticlesPage(
            final String sortType,
            final boolean revers
    );

    /**
     * Creates and returns page with information about main company.
     *
     * @return The ready object of class ModelAndView.
     * @see Company
     */
    ModelAndView aboutCompanyPage();

    /**
     * Creates and returns page with contacts information about main company.
     *
     * @return The ready object of class ModelAndView.
     */
    ModelAndView contactsPage();

    /**
     * Creates and returns page with all partners.
     *
     * @return The ready object of class ModelAndView.
     * @see Company
     */
    ModelAndView allPartnersPage();

    /**
     * Creates and returns page with all sorted partners.
     *
     * @param revers a sorting direction, {@code true} or {@code false}.
     * @return The ready object of class ModelAndView.
     */
    ModelAndView allSortPartnersByTitlePage(
            final boolean revers
    );

    /**
     * Creates and returns page with one category with parameter url.
     *
     * @param url a url of the category to return.
     * @return The ready object of class ModelAndView.
     * @see Category
     */
    ModelAndView categoryPage(final String url);

    /**
     * Creates and returns page with category
     * with all articles sorted by sortType.
     *
     * @param url      a category URL.
     * @param sortType a sort type.
     * @param revers   a sorting direction, {@code true} or {@code false}.
     * @return The ready object of class ModelAndView.
     */
    ModelAndView categoryWithSortArticlesPage(
            final String url,
            final String sortType,
            final boolean revers
    );

    /**
     * Creates and returns page with one article with parameter url.
     *
     * @param url a url of the article to return.
     * @return The ready object of class ModelAndView.
     * @see Article
     */
    ModelAndView articleByUrlPage(final String url);

    /**
     * Creates and returns page with one article with parameter number.
     *
     * @param number a number of the article to return.
     * @return The ready object of class ModelAndView.
     * @see Article
     */
    ModelAndView articleByNumberPage(final String number);

    /**
     * Creates and returns page with one partner with parameter url.
     *
     * @param url a url of the partner to return.
     * @return The ready object of class ModelAndView.
     * @see Company
     */
    ModelAndView partnerPage(final String url);

    /**
     * Creates and returns page with all responses.
     *
     * @return The ready object of class ModelAndView.
     * @see Response
     */
    ModelAndView allResponsesPage();

    /**
     * Creates and returns page with all sorted responses.
     *
     * @param revers a sorting direction, {@code true} or {@code false}.
     * @return The ready object of class ModelAndView.
     */
    ModelAndView allSortResponsesByDatePage(
            final boolean revers
    );

    /**
     * Creates and returns default modelAndView.
     *
     * @return The ready object of class ModelAndView.
     */
    ModelAndView getDefaultModelAndView();

    /**
     * Adds authenticated user to the modelAndView.
     *
     * @param modelAndView a model and view to update.
     * @see User
     */
    void addAuthUser(final ModelAndView modelAndView);

    /**
     * Validates output objects.
     *
     * @return Returns {@code true} if need to return valid objects,
     * {@code false} otherwise.
     */
    boolean isValidContent();
}
