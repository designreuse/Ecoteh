package ua.com.ecoteh.entity.response;

import ua.com.ecoteh.entity.model.ModelEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link ResponseEntity} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Response
 */
@Entity
@Table(name = "responses")
public class ResponseEntity extends ModelEntity {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The username of this response entity.
     */
    @Column(name = "username", nullable = false)
    private String username;

    /**
     * The username of this response entity.
     */
    @Column(name = "text", nullable = false)
    private String text;

    /**
     * The date of this response entity.
     */
    @Column(name = "date", nullable = false)
    private Date date;

    /**
     * Constructor.
     */
    protected ResponseEntity() {
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object (newer null).
     */
    @Override
    public String toString() {
        return "ResponseEntity{" + super.toString() +
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
            final ResponseEntity other = (ResponseEntity) object;
            result = this.username.equals(other.username) &&
                    this.date.equals(other.date) &&
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
        return this.username.hashCode() +
                this.date.hashCode() +
                this.text.hashCode();
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance (newer null).
     */
    @Override
    public ResponseEntity clone() {
        return (ResponseEntity) super.clone();
    }

    /**
     * Returns a username of the response entity.
     *
     * @return The response entity username (newer null).
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets a new username to the response entity.
     *
     * @param username the new text to the response entity.
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Returns a text of the response entity.
     *
     * @return The response entity text (newer null).
     */
    public String getText() {
        return this.text;
    }

    /**
     * Sets a new text to the response entity.
     *
     * @param text the new text to the response entity.
     */
    public void setText(final String text) {
        this.text = text;
    }

    /**
     * Returns a date of the response entity.
     *
     * @return The response entity date (newer null).
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Sets a new date to the response entity.
     *
     * @param date the new date to the response entity.
     */
    public void setDate(final Date date) {
        this.date = date;
    }

    /**
     * Converts this entity and returns a object
     * of the {@link Response} class.
     *
     * @return The object of the {@link Response} class (newer null).
     * @see Response
     */
    @Override
    public Response convert() {
        return new ResponseEntityConverter(this).convert();
    }
}
