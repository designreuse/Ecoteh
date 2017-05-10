package ua.com.ecoteh.entity;

import java.util.Date;

/**
 * The interface describes a set of methods
 * for working with objects of
 * the {@link Message} class.
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
     * @param user the user of the message.
     */
    void setUser(User user);

    /**
     * Returns a subject of the message.
     *
     * @return The message subject.
     */
    String getSubject();

    /**
     * Sets a new subject to the message.
     *
     * @param subject the new subject to the message.
     */
    void setSubject(String subject);

    /**
     * Returns a text of the message.
     *
     * @return The message text.
     */
    String getText();

    /**
     * Sets a new text to the message.
     *
     * @param text v new text to the message.
     */
    void setText(String text);

    /**
     * Returns a date of the message.
     *
     * @return The message date.
     */
    Date getDate();

    /**
     * Sets a new date to the message.
     *
     * @param date the new text to the message.
     */
    void setDate(Date date);

    /**
     * Returns an article date in string format.
     *
     * @return The message string-date.
     */
    String getDateToString();

    /**
     * Initializes the message.
     * Returns this message with a new copied fields.
     *
     * @param message the message to copy.
     * @return This message with new fields.
     */
    Message initialize(Message message);
}
