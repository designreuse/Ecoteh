package com.salimov.yurii.entity.interfaces;

import com.salimov.yurii.entity.Response;

import java.util.Date;

/**
 * The interface describes a set of methods
 * for working with objects of
 * the {@link com.salimov.yurii.entity.Response} class.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see com.salimov.yurii.entity.Response
 * @see IModel
 */
public interface IResponse extends IModel {

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

    /**
     *
     * @param response
     * @return
     */
    Response initialize(final Response response);
}
