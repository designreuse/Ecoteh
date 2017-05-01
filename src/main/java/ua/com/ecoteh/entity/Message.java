package ua.com.ecoteh.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ua.com.ecoteh.util.time.Time;

import javax.persistence.*;
import java.util.Date;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNull;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link Message} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Entity
@Table(name = "messages")
public class Message extends Model implements IMessage {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The message user.
     */
    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "user_id", nullable = false,
            referencedColumnName = "id"
    )
    @Fetch(FetchMode.JOIN)
    private User user;

    /**
     * The subject of message.
     */
    @Column(name = "subject", nullable = false)
    private String subject;

    /**
     * The text of message.
     */
    @Column(name = "text", nullable = false)
    private String text;

    /**
     * The date of created message.
     */
    @Column(name = "date", nullable = false)
    private Date date;

    /**
     * Default constructor.
     */
    public Message() {
        this.user = new User();
        this.subject = "";
        this.text = "";
        this.date = new Date();
    }

    /**
     * Constructor.
     *
     * @param user    a message user.
     * @param subject a subject of the new message.
     * @param text    a text of the new message.
     */
    public Message(
            final User user,
            final String subject,
            final String text
    ) {
        this();
        setUser(user);
        setSubject(subject);
        setText(text);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "Message{" + super.toString() +
                ", user=" + getUser() +
                ", subject='" + getSubject() + '\'' +
                ", text='" + getText() + '\'' +
                ", date=" + getDate() +
                '}';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return true if this object is the same as the obj
     * argument, false otherwise.
     */
    @Override
    public boolean equals(final Object object) {
        boolean result = super.equals(object);
        if (result) {
            final Message other = (Message) object;
            result = this.getUser().equals(other.getUser()) &&
                    this.getSubject().equalsIgnoreCase(other.getSubject()) &&
                    this.getText().equalsIgnoreCase(other.getText());
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
        return getUser().hashCode() +
                getSubject().hashCode() +
                getText().hashCode();
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance.
     */
    @Override
    public Message clone() {
        return (Message) super.clone();
    }

    /**
     * Returns a user of the message.
     *
     * @return The user of the message.
     */
    @Override
    public User getUser() {
        return this.user;
    }

    /**
     * Sets a user of the message.
     *
     * @param user a user of the message.
     */
    @Override
    public void setUser(final User user) {
        if (isNull(this.user)) {
            this.user = new User();
        }
        this.user.initialize(user);
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
     * If parameter subject is blank, then sets empty string.
     *
     * @param subject a new subject to the message.
     */
    @Override
    public void setSubject(final String subject) {
        this.subject = isNotEmpty(subject) ? subject : "";
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
     * If parameter text is blank, then sets empty string.
     *
     * @param text a new text to the message.
     */
    @Override
    public void setText(final String text) {
        this.text = isNotEmpty(text) ? text : "";
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
     * If parameter date is empty string, then sets new Date().
     *
     * @param date a new text to the message.
     */
    @Override
    public void setDate(final Date date) {
        this.date = isNotNull(date) ? date : new Date();
    }

    /**
     * Returns an article date in string format.
     *
     * @return The message string-date.
     */
    @Override
    public String getDateToString() {
        return Time.getDateToString(this.date);
    }

    /**
     * Initializes the message.
     *
     * @param message a message to copy.
     * @return The this message with new fields.
     */
    @Override
    public Message initialize(final Message message) {
        if (isNotNull(message)) {
            super.initialize(message);
            this.setUser(message.getUser());
            this.setSubject(message.getSubject());
            this.setText(message.getText());
            this.setDate(message.getDate());
        }
        return this;
    }
}
