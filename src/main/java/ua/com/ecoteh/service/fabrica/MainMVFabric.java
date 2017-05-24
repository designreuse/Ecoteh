package ua.com.ecoteh.service.fabrica;

import org.springframework.web.servlet.ModelAndView;

/**
 * The interface provides a set of standard methods for creates
 * and returns the main modelAndViews.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public interface MainMVFabric {

    /**
     * Creates and returns a home page.
     *
     * @return The ready object of the ModelAndView class.
     */
    ModelAndView homePage();

    /**
     * Creates and returns a page with all categories.
     *
     * @return The ready object of the ModelAndView class.
     */
    ModelAndView allCategoriesPage();

    /**
     * Creates and returns a page with all articles sorted by date.
     *
     * @return The ready object of the ModelAndView class.
     */
    ModelAndView allArticlesPage();

    /**
     * Creates and returns a page with all articles sorted by sortType.
     *
     * @param sortType the sort type.
     * @param revers   the sorting direction, true or false.
     * @return The ready object of the ModelAndView class.
     */
    ModelAndView allSortArticlesPage(
            String sortType,
            boolean revers
    );

    /**
     * Creates and returns a page with information about main company.
     *
     * @return The ready object of the ModelAndView class.
     */
    ModelAndView aboutCompanyPage();

    /**
     * Creates and returns a page with contacts information about main company.
     *
     * @return The ready object of the ModelAndView class.
     */
    ModelAndView contactsPage();

    /**
     * Creates and returns a page with all partners.
     *
     * @return The ready object of the ModelAndView class.
     */
    ModelAndView allPartnersPage();

    /**
     * Creates and returns a page with all sorted partners.
     *
     * @param revers the sorting direction, true or false.
     * @return The ready object of the ModelAndView class.
     */
    ModelAndView allSortPartnersByTitlePage(boolean revers);

    /**
     * Creates and returns a page with one category with incoming URL.
     *
     * @param url the URL of a category to return.
     * @return The ready object of the ModelAndView class.
     */
    ModelAndView categoryPage(String url);

    /**
     * Creates and returns a page with category
     * with all articles sorted by the incoming sort type.
     *
     * @param url      the category URL.
     * @param sortType the sort type.
     * @param revers   the sorting direction, true or false.
     * @return The ready object of the ModelAndView class.
     */
    ModelAndView categoryWithSortArticlesPage(
            String url,
            String sortType,
            boolean revers
    );

    /**
     * Creates and returns a page with one article with the incoming URL.
     *
     * @param url the URL of a article to return.
     * @return The ready object of the ModelAndView class.
     */
    ModelAndView articleByUrlPage(String url);

    /**
     * Creates and returns a page with one article with the incoming number.
     *
     * @param number the number of a article to return.
     * @return The ready object of the ModelAndView class.
     */
    ModelAndView articleByNumberPage(String number);

    /**
     * Creates and returns a page with one partner with the incoming URL.
     *
     * @param url the URL of a partner to return.
     * @return The ready object of the ModelAndView class.
     */
    ModelAndView partnerPage(String url);

    /**
     * Creates and returns a page with all responses.
     *
     * @return The ready object of the ModelAndView class.
     */
    ModelAndView allResponsesPage();

    /**
     * Creates and returns a page with all sorted responses.
     *
     * @param revers the sorting direction, true or false.
     * @return The ready object of the ModelAndView class.
     */
    ModelAndView allSortResponsesByDatePage(boolean revers);

    /**
     * Creates and returns a default modelAndView.
     *
     * @return The ready object of the ModelAndView class.
     */
    ModelAndView getDefaultModelAndView();

    /**
     * Adds an authenticated user to the modelAndView.
     *
     * @param modelAndView the model and view to update.
     */
    void addAuthUser(ModelAndView modelAndView);

    /**
     * Validates an output objects.
     *
     * @return Returns true if need to return a valid objects,
     * false otherwise.
     */
    boolean isValidContent();
}
