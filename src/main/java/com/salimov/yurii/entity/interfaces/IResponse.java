package com.salimov.yurii.entity.interfaces;

import java.util.Date;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface IResponse<E extends Number> extends IModel<E> {

    /**
     * Initializes some parameter of the response.
     * Also adds new date.
     *
     * @param username a new username to the response.
     * @param text     a new text to the response.
     */
    void initialize(final String username, final String text);

    /**
     * Returns a username of the response.
     *
     * @return The response username.
     */
    String getUsername();

    /**
     * Sets a new username to the response.
     *
     * @param username a new text to the response.
     */
    void setUsername(final String username);

    /**
     * Returns a text of the response.
     *
     * @return The response text.
     */
    String getText();

    /**
     * Sets a new text to the response.
     *
     * @param text a new text to the response.
     */
    void setText(final String text);

    /**
     * Returns a date of the response.
     *
     * @return The response date.
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
     * Changes response validation.
     */
    void reverseValidated();
}
