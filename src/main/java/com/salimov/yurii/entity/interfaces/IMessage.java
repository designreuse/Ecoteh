package com.salimov.yurii.entity.interfaces;

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
public interface IMessage<T extends Number> extends IModel<T> {

    /**
     * Initializes some parameter of the article.
     *
     * @param username a name of the client.
     * @param email    a name of the email.
     * @param phone    a name of the phone.
     * @param subject  a subject of the new message.
     * @param text     a text of the new message.
     */
    void initialize(
            final String username,
            final String email,
            final String phone,
            final String subject,
            final String text
    );

    /**
     * Returns a name of the client.
     *
     * @return The client name.
     */
    String getUsername();

    /**
     * Sets a new name to the client.
     *
     * @param username a new name to the client.
     */
    void setUsername(final String username);

    /**
     * Returns a email of the client.
     *
     * @return The client name.
     */
    String getEmail();

    /**
     * Sets a new email to the client.
     *
     * @param email a new email to the client.
     */
    void setEmail(final String email);

    /**
     * Returns a phone of the client.
     *
     * @return The client name.
     */
    String getPhone();

    /**
     * Sets a new phone to the client.
     *
     * @param phone a new phone to the client.
     */
    void setPhone(final String phone);

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
}
