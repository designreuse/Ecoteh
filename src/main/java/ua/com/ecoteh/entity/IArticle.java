package ua.com.ecoteh.entity;

import java.util.Date;

/**
 * The interface describes a set of methods
 * for working with objects of the {@link Article} class.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
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
     * @param number the new number to the article.
     */
    void setNumber(String number);

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
     * @param text the new text to the article.
     */
    void setText(String text);

    /**
     * Returns a date of the article.
     *
     * @return The article date.
     */
    Date getDate();

    /**
     * Sets a new text to the article.
     *
     * @param date the new date to the article.
     */
    void setDate(Date date);

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
     * @param price the new price to the article.
     */
    void setPrice(String price);

    /**
     * Sets a new category to the article.
     *
     * @param category the new category to the article.
     */
    void setCategory(Category category);

    /**
     * Returns a category of the article.
     *
     * @return The article category.
     */
    Category getCategory();

    /**
     * Initializes the article.
     * Returns this article with a new copied fields.
     *
     * @param article the article to copy.
     * @return This article with new fields.
     */
    Article initialize(Article article);
}
