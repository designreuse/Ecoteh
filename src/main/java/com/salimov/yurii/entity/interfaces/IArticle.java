package com.salimov.yurii.entity.interfaces;

import com.salimov.yurii.entity.Category;
import com.salimov.yurii.entity.File;

import java.util.Date;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface IArticle<E extends Number>
        extends IContent<E> {

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
     * Initializes some parameter of the article.
     *
     * @param title       a new title to the article.
     * @param description a new description to the article.
     * @param text        a new text to the article.
     * @param keywords    a new keywords to the article.
     * @param number      a new number to the article.
     * @param category    a new category to the article.
     * @param photo       a new main photo pf the article.
     * @see Category
     * @see File
     */
    void initialize(
            final String title,
            final String description,
            final String text,
            final String keywords,
            final String number,
            final Category category,
            final File photo
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

    /**
     * Returns a main photo of the article.
     *
     * @return The article main photo.
     * @see File
     */
    File getPhoto();

    /**
     * Sets a new main photo to the article.
     *
     * @param photo a new main photo to the article.
     * @see File
     */
    void setPhoto(final File photo);
}
