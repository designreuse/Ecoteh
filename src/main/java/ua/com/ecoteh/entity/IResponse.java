package ua.com.ecoteh.entity;

import java.util.Date;

/**
 * The interface describes a set of methods
 * for working with objects of
 * the {@link Response} class.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
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
     * @param username the new text to the response.
     */
    void setUsername(String username);

    /**
     * Returns a text of the response.
     *
     * @return The response text.
     */
    String getText();

    /**
     * Sets a new text to the response.
     *
     * @param text the new text to the response.
     */
    void setText(String text);

    /**
     * Returns a date of the response.
     *
     * @return The response date.
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
     * Changes response validation.
     */
    void reverseValidated();

    /**
     * Initializes the response.
     * Returns this response with a new copied fields.
     *
     * @param response the response to copy.
     * @return This response with new fields.
     */
    Response initialize(Response response);
}
