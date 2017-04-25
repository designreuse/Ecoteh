package ua.com.ecoteh.entity;

import java.util.Date;

/**
 * The interface describes a set of methods
 * for working with objects of the {@link Article} class.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface IArticle extends IContent {

    /**
     * Returns a number of the article.
     *
     * @return The article number.
     */
    String getNumber();

    /**
     * Sets new number to the article.
     *
     * @param number a new number to the article.
     */
    void setNumber(final String number);

    /**
     * Generates new number to the article.
     */
    void newNumber();

    /**
     * Returns a text of the article.
     *
     * @return The article text.
     */
    String getText();

    /**
     * Sets a new text to the article.
     *
     * @param text a new text to the article.
     */
    void setText(final String text);

    /**
     * Returns a date of the article.
     *
     * @return The article date.
     */
    Date getDate();

    /**
     * Sets a new text to the article.
     *
     * @param date a new date to the article.
     */
    void setDate(final Date date);

    /**
     * Returns an article date in string format.
     *
     * @return The article string-date.
     */
    String getDateToString();

    /**
     * Returns a price of the article.
     *
     * @return The article price.
     */
    String getPrice();

    /**
     * Sets a new price to the article.
     * If parameter text is blank, then sets new Date().
     *
     * @param price a new price to the article.
     */
    void setPrice(final String price);

    /**
     * Sets a new category to the article.
     *
     * @param category a new category to the article.
     */
    void setCategory(final Category category);

    /**
     * Returns a category of the article.
     *
     * @return The article category.
     */
    Category getCategory();

    /**
     * Initializes the article.
     *
     * @param article a article to copy.
     * @return The this article with new fields.
     */
    Article initialize(final Article article);
}
