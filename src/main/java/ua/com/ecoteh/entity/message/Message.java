package ua.com.ecoteh.entity.message;

import ua.com.ecoteh.entity.model.Model;
import ua.com.ecoteh.entity.user.User;
import ua.com.ecoteh.util.time.Time;

import java.util.Date;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class Message extends Model {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The messageEntity userEntity.
     */
    private final User user;

    /**
     * The subject of a messageEntity.
     */
    private final String subject;

    /**
     * The text of a messageEntity.
     */
    private final String text;

    /**
     * The date of created messageEntity.
     */
    private final Date date;

    /**
     *
     * @param id
     * @param validated
     * @param user
     * @param subject
     * @param text
     * @param date
     */
    Message(
            final long id, final boolean validated, final User user,
            final String subject, final String text, final Date date
    ) {
        super(id, validated);
        this.user = user;
        this.subject = subject;
        this.text = text;
        this.date = date;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object (newer null).
     */
    @Override
    public String toString() {
        return "Message{" + super.toString() +
                ", user=" + this.user +
                ", subject='" + this.subject + '\'' +
                ", text='" + this.text + '\'' +
                ", date=" + this.date +
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
            result = this.user.equals(other.user) &&
                    this.subject.equalsIgnoreCase(other.subject) &&
                    this.text.equalsIgnoreCase(other.text);
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
        return this.user.hashCode() +
                this.subject.hashCode() +
                this.text.hashCode();
    }

    /**
     * Returns a userEntity of the messageEntity.
     *
     * @return The userEntity of the messageEntity (newer null).
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Returns a subject of the messageEntity.
     *
     * @return The messageEntity subject or empty string (newer null).
     */
    public String getSubject() {
        return this.subject;
    }

    /**
     * Returns a text of the messageEntity.
     *
     * @return The messageEntity text or empty string (newer null).
     */
    public String getText() {
        return this.text;
    }

    /**
     * Returns a date of the messageEntity.
     *
     * @return The messageEntity date (newer null).
     */
    public Date getDate() {
        return this.date;
    }


    /**
     * Returns an article date in string format.
     *
     * @return The messageEntity string-date (newer null).
     */
    public String getDateToString() {
        return Time.getDate(getDate());
    }

    /**
     *
     * @return
     */
    public MessageEntity convert() {
        return new MessageConverter(this).convert();
    }

    /**
     *
     * @return
     */
    public static MessageBuilder getBuilder() {
        return new MessageBuilder();
    }
}
