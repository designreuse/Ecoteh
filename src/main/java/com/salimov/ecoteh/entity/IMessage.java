package com.salimov.ecoteh.entity;

import java.util.Date;

/**
 * The interface describes a set of methods
 * for working with objects of
 * the {@link com.salimov.ecoteh.entity.Message} class.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface IMessage extends IModel {

    /**
     * Returns a user of the message.
     *
     * @return The user of the message.
     */
    User getUser();

    /**
     * Sets a user of the message.
     *
     * @param user a user of the message.
     */
    void setUser(final User user);

    /**
     * Returns a subject of the message.
     *
     * @return The message subject.
     */
    String getSubject();

    /**
     * Sets a new subject to the message.
     *
     * @param subject a new subject to the message.
     */
    void setSubject(final String subject);

    /**
     * Returns a text of the message.
     *
     * @return The message text.
     */
    String getText();

    /**
     * Sets a new text to the message.
     *
     * @param text a new text to the message.
     */
    void setText(final String text);

    /**
     * Returns a date of the message.
     *
     * @return The message date.
     */
    Date getDate();

    /**
     * Sets a new date to the message.
     *
     * @param date a new text to the message.
     */
    void setDate(final Date date);

    /**
     * Returns an article date in string format.
     *
     * @return The message string-date.
     */
    String getDateToString();

    /**
     * Initializes the message.
     *
     * @param message a message to copy.
     * @return The this message with new fields.
     */
    Message initialize(final Message message);
}
