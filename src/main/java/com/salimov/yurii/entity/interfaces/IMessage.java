package com.salimov.yurii.entity.interfaces;

import com.salimov.yurii.entity.Message;
import com.salimov.yurii.entity.User;

import java.util.Date;

/**
 * The interface describes a set of methods
 * for working with objects of
 * the {@link com.salimov.yurii.entity.Message} class.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see com.salimov.yurii.entity.Message
 * @see IModel
 */
public interface IMessage extends IModel {

    /**
     *
     *
     * @return
     */
    User getUser();

    /**
     *
     *
     * @param user
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
     * @param message
     * @return
     */
    Message initialize(final Message message);
}
