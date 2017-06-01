package ua.com.ecoteh.entity.response;

import ua.com.ecoteh.entity.model.ModelEntity;
import ua.com.ecoteh.util.time.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link ResponseEntity} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
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
     * The username of a responseEntity.
     */
    @Column(name = "username", nullable = false)
    private String username;

    /**
     * The username of a responseEntity.
     */
    @Column(name = "text", nullable = false)
    private String text;

    /**
     * The date of a responseEntity.
     */
    @Column(name = "date", nullable = false)
    private Date date;

    /**
     * Default constructor.
     */
    public ResponseEntity() {
        this.username = "";
        this.text = "";
        this.date = new Date();
        setValidated(false);
    }

    /**
     * Constructor.
     *
     * @param username the username of a new responseEntity.
     * @param text     the text of a new responseEntity.
     */
    public ResponseEntity(
            final String username,
            final String text
    ) {
        this();
        setUsername(username);
        setText(text);
    }

    /**
     * Constructor.
     *
     * @param username  the username of a new responseEntity.
     * @param text      the text of a new responseEntity.
     * @param validated is validated of a new responseEntity.
     */
    public ResponseEntity(
            final String username,
            final String text,
            final boolean validated
    ) {
        this(username, text);
        setValidated(validated);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object (newer null).
     */
    @Override
    public String toString() {
        return "ResponseEntity{" + super.toString() +
                ", username='" + getUsername() + '\'' +
                ", text='" + getText() + '\'' +
                ", date=" + getDate() +
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
            result = this.getUsername().equals(other.getUsername()) &&
                    this.getText().equals(other.getText());
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
        return getUsername().hashCode() + getText().hashCode();
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
     * Returns a username of the responseEntity.
     *
     * @return The responseEntity username (newer null).
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets a new username to the responseEntity.
     * If parameter username is blank, then sets empty string.
     * <pre>
     *     setUsername(null) - username = ""
     *     setUsername("") - username = ""
     *     setUsername(" ") - username = ""
     *     setUsername("bob") - username = "bob"
     *     setUsername(" bob ") - username = "bob"
     * </pre>
     *
     * @param username the new text to the responseEntity.
     */
    public void setUsername(final String username) {
        this.username = isNotEmpty(username) ? username : "";
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
     * Sets a new text to the responseEntity.
     * If parameter text is blank, then sets empty string.
     * <pre>
     *     setText(null) - text = ""
     *     setText("") - text = ""
     *     setText(" ") - text = ""
     *     setText("bob") - text = "bob"
     *     setText(" bob ") - text = "bob"
     * </pre>
     *
     * @param text the new text to the responseEntity.
     */
    public void setText(final String text) {
        this.text = isNotEmpty(text) ? text : "";
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
     * Sets a new date to the responseEntity.
     * If parameter date is null, then sets new Date().
     * <pre>
     *     setDate(null) - date = new Date()
     *     setDate(someRealDate) - date = someRealDate
     * </pre>
     *
     * @param date the new date to the responseEntity.
     */
    public void setDate(final Date date) {
        this.date = isNotNull(date) ? date : new Date();
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
     * Changes responseEntity validation.
     */
    public void reverseValidated() {
        setValidated(!isValidated());
    }

    /**
     * Initializes the responseEntity.
     * Returns this responseEntity with a new copied fields.
     * <pre>
     *     initialize(null) - does nothing, returns this responseEntity
     *     initialize(new ResponseEntity()) - does nothing, returns this
     *     responseEntity with a new copied fields
     * </pre>
     *
     * @param responseEntity the responseEntity to copy.
     * @return This responseEntity with new fields (newer null).
     */
    public ResponseEntity initialize(final ResponseEntity responseEntity) {
        if (isNotNull(responseEntity)) {
            super.initialize(responseEntity);
            this.setUsername(responseEntity.getUsername());
            this.setText(responseEntity.getText());
            this.setDate(responseEntity.getDate());
        }
        return this;
    }

    /**
     *
     * @return
     */
    public Response convert() {
        return new ResponseEntityConverter(this).convert();
    }
}
