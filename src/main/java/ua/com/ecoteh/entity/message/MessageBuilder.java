package ua.com.ecoteh.entity.message;

import ua.com.ecoteh.entity.model.ModelBuilder;
import ua.com.ecoteh.entity.user.User;

import java.util.Date;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of methods
 * for building an objects of the {@link Message} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Message
 */
public final class MessageBuilder extends ModelBuilder<Message, MessageBuilder> {

    /**
     * The message user.
     */
    private User user;

    /**
     * The subject of a new message.
     */
    private String subject;

    /**
     * The text of a new message.
     */
    private String text;

    /**
     * The date of created a new message.
     */
    private Date date;

    /**
     * Constructor.
     */
    MessageBuilder() {
    }

    /**
     * Builds and returns a new message.
     *
     * @return The new message.
     * @see Message
     */
    @Override
    public Message build() {
        return new Message(
                getId(), isValidated(), getUser(),
                getSubject(), getText(), getDate()
        );
    }

    /**
     * Adds a new user to a new message.
     *
     * @param user the new user to a new message.
     * @return the message builder.
     */
    public MessageBuilder addUser(final User user) {
        this.user = user;
        return this;
    }

    /**
     * Adds a new subject to a new message.
     *
     * @param subject the new subject to a new message.
     * @return the message builder.
     */
    public MessageBuilder addSubject(final String subject) {
        this.subject = subject;
        return this;
    }

    /**
     * Adds a new text to a new message.
     *
     * @param text the new text to a new message.
     * @return the message builder.
     */
    public MessageBuilder addText(final String text) {
        this.text = text;
        return this;
    }

    /**
     * Adds a new date to a new message.
     *
     * @param date the new date to a new message.
     * @return the message builder.
     */
    public MessageBuilder addDate(final Date date) {
        this.date = date;
        return this;
    }

    /**
     * Returns a user of a new message.
     * Returns a new user if the user is null or empty.
     *
     * @return The message user (newer null).
     */
    private User getUser() {
        return isNotNull(this.user) ? this.user : User.getBuilder().build();
    }

    /**
     * Returns a subject of a new message.
     * Returns an empty string if the subject is null or empty.
     *
     * @return The subject or empty string (newer null).
     */
    private String getSubject() {
        return isNotEmpty(this.subject) ? this.subject : "";
    }

    /**
     * Returns a text of a new message.
     * Returns an empty string if the text is null or empty.
     *
     * @return The text or empty string (newer null).
     */
    private String getText() {
        return isNotEmpty(this.text) ? this.text : "";
    }

    /**
     * Returns a date of a new message.
     * Returns a new date if the date is null.
     *
     * @return The message date (newer null).
     */
    private Date getDate() {
        return isNotNull(this.date) ? this.date : new Date();
    }
}
