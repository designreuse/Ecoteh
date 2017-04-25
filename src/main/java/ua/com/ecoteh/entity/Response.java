package ua.com.ecoteh.entity;

import ua.com.ecoteh.util.time.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link Response} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Entity
@Table(name = "responses")
public class Response extends Model implements IResponse {

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
    @Column(name = "username", nullable = false)
    private String username;

    /**
     * The username of a response.
     */
    @Column(name = "text", nullable = false)
    private String text;

    /**
     * The date of a response.
     */
    @Column(name = "date", nullable = false)
    private Date date;

    /**
     * Default constructor.
     */
    public Response() {
        this.username = "";
        this.text = "";
        this.date = new Date();
        setValidated(false);
    }

    /**
     * Constructor.
     *
     * @param username a username of the new response.
     * @param text     a text of the new response.
     */
    public Response(
            final String username,
            final String text
    ) {
        this();
        setUsername(username);
        setText(text);
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
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "Response{" + super.toString() +
                ", username='" + getUsername() + '\'' +
                ", text='" + getText() + '\'' +
                ", date=" + getDate() +
                '}';
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance.
     */
    @Override
    public Response clone() {
        return (Response) super.clone();
    }

    /**
     * Returns a username of the response.
     *
     * @return The response username.
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets a new username to the response.
     * If parameter username is blank, then sets empty string.
     *
     * @param username a new text to the response.
     */
    @Override
    public void setUsername(final String username) {
        this.username = isNotBlank(username) ? username : "";
    }

    /**
     * Returns a text of the response.
     *
     * @return The response text.
     */
    @Override
    public String getText() {
        return this.text;
    }

    /**
     * Sets a new text to the response.
     * If parameter text is blank, then sets empty string.
     *
     * @param text a new text to the response.
     */
    @Override
    public void setText(final String text) {
        this.text = isNotBlank(text) ? text : "";
    }

    /**
     * Returns a date of the response.
     *
     * @return The response date.
     */
    @Override
    public Date getDate() {
        return this.date;
    }

    /**
     * Sets a new text to the article.
     * If parameter text is blank, then sets new Date().
     *
     * @param date a new date to the article.
     */
    @Override
    public void setDate(final Date date) {
        this.date = date != null ? date : new Date();
    }

    /**
     * Returns an article date in string format.
     *
     * @return The article string-date.
     */
    @Override
    public String getDateToString() {
        return Time.getDateToString(this.date);
    }

    /**
     * Changes response validation.
     */
    @Override
    public void reverseValidated() {
        setValidated(!isValidated());
    }

    /**
     * Initializes the response.
     *
     * @param response a response to copy.
     * @return The this response with new fields.
     */
    @Override
    public Response initialize(final Response response) {
        if (response != null) {
            super.initialize(response);
            this.setUsername(response.getUsername());
            this.setText(response.getText());
            this.setDate(response.getDate());
        }
        return this;
    }
}
