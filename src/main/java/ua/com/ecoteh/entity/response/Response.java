package ua.com.ecoteh.entity.response;

import ua.com.ecoteh.entity.model.Model;
import ua.com.ecoteh.util.time.Time;

import java.util.Date;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class Response extends Model {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The username of a responseEntity.
     */
    private final String username;

    /**
     * The username of a responseEntity.
     */
    private final String text;

    /**
     * The date of a responseEntity.
     */
    private final Date date;

    public Response(
            final long id, final boolean validated,
            final String username, final String text,
            final Date date
    ) {
        super(id, validated);
        this.username = username;
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
        return "Response{" + super.toString() +
                ", username='" + this.username + '\'' +
                ", text='" + this.text + '\'' +
                ", date=" + this.date +
                '}';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return true if this object is the same as the obj argument,
     * false otherwise.
     */
    @Override
    public boolean equals(final Object object) {
        boolean result = false;
        if (super.equals(object)) {
            final Response other = (Response) object;
            result = this.username.equals(other.username) &&
                    this.text.equals(other.text);
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
        return this.username.hashCode() + this.text.hashCode();
    }

    /**
     * Returns a username of the responseEntity.
     *
     * @return The responseEntity username (newer null).
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Returns a text of the responseEntity.
     *
     * @return The responseEntity text (newer null).
     */
    public String getText() {
        return this.text;
    }

    /**
     * Returns a date of the responseEntity.
     *
     * @return The responseEntity date (newer null).
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Returns an article date in string format.
     *
     * @return The article string-date (newer null).
     */
    public String getDateToString() {
        return Time.getDate(this.date);
    }

    /**
     *
     * @return
     */
    public static ResponseBuilder getBuilder() {
        return new ResponseBuilder();
    }
}
