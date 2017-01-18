package com.salimov.yurii.entity;

import com.salimov.yurii.entity.interfaces.IMessage;

import java.util.Date;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link Message} class.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Model
 */
public final class Message
        extends Model<Long>
        implements IMessage<Long> {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The client name.
     */
    private String username;

    /**
     * The client email.
     */
    private String email;

    /**
     * The client phone.
     */
    private String phone;

    /**
     * The subject of message.
     */
    private String subject;

    /**
     * The text of message.
     */
    private String text;

    /**
     * The date of created message.
     */
    private Date date;

    /**
     * Default constructor.
     */
    public Message() {
        setDate(
                new Date()
        );
    }

    /**
     * Constructor.
     *
     * @param username a name of the client.
     * @param email    a name of the email.
     * @param phone    a name of the phone.
     * @param subject  a subject of the new message.
     * @param text     a text of the new message.
     */
    public Message(
            final String username,
            final String email,
            final String phone,
            final String subject,
            final String text
    ) {
        this();
        initialize(username, email, phone, subject, text);
    }

    /**
     * Constructor.
     * Initializes a main user parameters.
     *
     * @param username a name of the client.
     * @param phone    a name of the phone.
     * @param subject  a subject of the new message.
     * @param text     a text of the new message.
     */
    public Message(
            final String username,
            final String phone,
            final String subject,
            final String text
    ) {
        this(username, null, phone, subject, text);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Date: ").append(this.date)
                .append(" \n").append(this.username);
        if (isNotBlank(this.email)) {
            sb.append(" \n").append(this.email);
        }
        sb.append(" \n").append(this.phone)
                .append(" \n").append(this.text);
        return sb.toString();
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument, {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object object) {
        boolean result = super.equals(object);
        if (result) {
            final Message other = (Message) object;
            result = (
                    isNotBlank(this.username) ?
                            this.username.equalsIgnoreCase(other.username) :
                            isBlank(other.username)
            ) && (
                    isNotBlank(this.email) ?
                            this.email.equalsIgnoreCase(other.email) :
                            isBlank(other.email)
            ) && (
                    isNotBlank(this.phone) ?
                            this.phone.equalsIgnoreCase(other.phone) :
                            isBlank(other.phone)
            ) && (
                    isNotBlank(this.subject) ?
                            this.subject.equalsIgnoreCase(other.subject) :
                            isBlank(other.subject)
            ) && (
                    isNotBlank(this.text) ?
                            this.text.equalsIgnoreCase(other.text) :
                            isBlank(other.text)
            );
        }
        return result;
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit
     * of hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return (
                isNotBlank(this.username) ? this.username.hashCode() : 0
        ) + (
                isNotBlank(this.email) ? this.email.hashCode() : 0
        ) + (
                isNotBlank(this.phone) ? this.phone.hashCode() : 0
        ) + (
                isNotBlank(this.subject) ? this.subject.hashCode() : 0
        ) + (
                isNotBlank(this.text) ? this.text.hashCode() : 0
        );
    }

    /**
     * Initializes some parameter of the article.
     *
     * @param username a name of the client.
     * @param email    a name of the email.
     * @param phone    a name of the phone.
     * @param subject  a subject of the new message.
     * @param text     a text of the new message.
     */
    @Override
    public void initialize(
            final String username,
            final String email,
            final String phone,
            final String subject,
            final String text
    ) {
        setUsername(username);
        setEmail(email);
        setPhone(phone);
        setSubject(subject);
        setText(text);
    }

    /**
     * Returns a name of the client.
     *
     * @return The client name.
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets a new name to the client.
     * If parameter name is blank, then sets {@code null}.
     *
     * @param username a new name to the client.
     */
    @Override
    public void setUsername(final String username) {
        this.username = isNotBlank(username) ? username : null;
    }

    /**
     * Returns a email of the client.
     *
     * @return The client name.
     */
    @Override
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets a new email to the client.
     * If parameter email is blank, then sets {@code null}.
     *
     * @param email a new email to the client.
     */
    @Override
    public void setEmail(final String email) {
        this.email = isNotBlank(email) ? email : null;
    }

    /**
     * Returns a phone of the client.
     *
     * @return The client name.
     */
    @Override
    public String getPhone() {
        return this.phone;
    }

    /**
     * Sets a new phone to the client.
     * If parameter phone is blank, then sets {@code null}.
     *
     * @param phone a new phone to the client.
     */
    @Override
    public void setPhone(final String phone) {
        this.phone = isNotBlank(phone) ? phone : null;
    }

    /**
     * Returns a subject of the message.
     *
     * @return The message subject.
     */
    @Override
    public String getSubject() {
        return this.subject;
    }

    /**
     * Sets a new subject to the message.
     * If parameter subject is blank, then sets {@code null}.
     *
     * @param subject a new subject to the message.
     */
    @Override
    public void setSubject(final String subject) {
        this.subject = isNotBlank(subject) ? subject : null;
    }

    /**
     * Returns a text of the message.
     *
     * @return The message text.
     */
    @Override
    public String getText() {
        return this.text;
    }

    /**
     * Sets a new text to the message.
     * If parameter text is blank, then sets {@code null}.
     *
     * @param text a new text to the message.
     */
    @Override
    public void setText(final String text) {
        this.text = isNotBlank(text) ? text : null;
    }

    /**
     * Returns a date of the message.
     *
     * @return The message date.
     */
    @Override
    public Date getDate() {
        return this.date;
    }

    /**
     * Sets a new date to the message.
     * If parameter date is {@code null}, then sets {@code new Date()}.
     *
     * @param date a new text to the message.
     */
    @Override
    public void setDate(final Date date) {
        this.date = date != null ? date : new Date();
    }

    /**
     * Returns an article date in string format.
     *
     * @return The message string-date.
     */
    @Override
    public String getDateToString() {
        return getDateToString(
                this.date
        );
    }

    /**
     * Returns a unique identifier of the message.
     *
     * @return The unique identifier.
     */
    @Override
    public Long getId() {
        return (long) Math.abs(
                hashCode()
        );
    }

    /**
     * Statically validates the message.
     * User is valid if it is a valid model object
     * and it has username, phone and text.
     *
     * @param message an message to validate.
     * @return {@code true} if the user is valid, {@code false} otherwise.
     */
    public static boolean isValidated(final Message message) {
        boolean result = false;
        if (Model.isValidated(message)) {
            result = isNotBlank(message.getUsername())
                    && isNotBlank(message.getPhone())
                    && isNotBlank(message.getText());
        }
        return result;
    }
}
