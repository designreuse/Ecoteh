package com.salimov.yurii.entity.interfaces;

import com.salimov.yurii.entity.Article;
import com.salimov.yurii.entity.Category;

import java.util.Date;

/**
 * The interface describes a set of methods
 * for working with objects of the {@link Article} class.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see IContent
 * @see Article
 */
public interface IArticle<T extends Number> extends IContent<T> {

    /**
     * Initializes some parameter of the article.
     *
     * @param title       a new title to the article.
     * @param description a new description to the article.
     * @param text        a new text to the article.
     * @param keywords    a new keywords to the article.
     * @param number      a new number to the article.
     * @param category    a new category to the article.
     * @see Category
     */
    void initialize(
            final String title,
            final String description,
            final String text,
            final String keywords,
            final String number,
            final Category category
    );

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
     * Sets a new category to the article.
     *
     * @param category a new category to the article.
     * @see Category
     */
    void setCategory(final Category category);

    /**
     * Returns a category of the article.
     *
     * @return The article category.
     * @see Category
     */
    Category getCategory();
}
