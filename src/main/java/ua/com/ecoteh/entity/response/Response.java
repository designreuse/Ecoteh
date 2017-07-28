package ua.com.ecoteh.entity.response;

import ua.com.ecoteh.entity.model.Model;
import ua.com.ecoteh.util.time.Time;

import java.util.Date;

/**
 * The class implements a set of methods for working
 * with objects of the {@link Response} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see ResponseEntity
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
     * The username of a response.
     */
    private final String username;

    /**
     * The username of a response.
     */
    private final String text;

    /**
     * The date of a response.
     */
    private final Date date;

    /**
     * Constructor.
     *
     * @param id        the unique identifier for each response.
     * @param validated the validations of a new response.
     * @param username  the username of a new response.
     * @param text      the text of a new response.
     * @param date      the date of a new response.
     */
    Response(
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
     * Returns a string representation of the object to search.
     *
     * @return A string representation of the object (newer null).
     */
    @Override
    public String toSearch() {
        return " " + this.text + " ";
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
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance (newer null).
     */
    @Override
    public Response clone() {
        return (Response) super.clone();
    }

    /**
     * Returns a username of the response.
     *
     * @return The response username (newer null).
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Returns a text of the response.
     *
     * @return The response text (newer null).
     */
    public String getText() {
        return this.text;
    }

    /**
     * Returns a date of the response.
     *
     * @return The response date (newer null).
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
     * Converts this object and returns an entity
     * of the {@link ResponseEntity} class.
     *
     * @return The entity of the {@link ResponseEntity} class (newer null).
     * @see ResponseConverter
     */
    @Override
    public ResponseEntity convert() {
        return new ResponseConverter(this).convert();
    }

    /**
     * Returns a editor for updating this response.
     *
     * @return the response editor (newer null).
     */
    @Override
    public ResponseEditor getEditor() {
        return new ResponseEditor(this);
    }

    /**
     * Returns a builder for creating a new response.
     *
     * @return the response builder (newer null).
     */
    public static ResponseBuilder getBuilder() {
        return new ResponseBuilder();
    }
}
