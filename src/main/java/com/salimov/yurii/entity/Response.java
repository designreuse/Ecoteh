package com.salimov.yurii.entity;

import com.salimov.yurii.entity.interfaces.IResponse;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link Response} class.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Model
 */
@Entity
@Table(name = "responses")
public final class Response
        extends Model<Long>
        implements IResponse<Long> {

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
    @Column(
            name = "username",
            nullable = false
    )
    private String username;

    /**
     * The username of a response.
     */
    @Column(
            name = "text",
            nullable = false
    )
    private String text;

    /**
     * The date of a response.
     */
    @Column(
            name = "date",
            nullable = false
    )
    private Date date;

    /**
     * Default constructor.
     */
    public Response() {
        setDate(new Date());
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
     * @return {@code true} if this object is the same as the obj
     * argument, {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object object) {
        boolean result = false;
        if (super.equals(object)) {
            final Response other = (Response) object;
            result = (
                    isNotBlank(this.username) ?
                            this.username.equals(other.username) :
                            isBlank(other.username)
            ) && (
                    isNotBlank(this.text) ?
                            this.text.equals(other.text) :
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
                isNotBlank(this.text) ? this.text.hashCode() : 0
        );
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "Date: " + this.date
                + " \nUsername: " + this.username
                + " \nText: " + this.text;
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
     * Initializes some parameter of the response.
     * Also adds new date.
     *
     * @param username a new username to the response.
     * @param text     a new text to the response.
     */
    public void initialize(
            final String username,
            final String text
    ) {
        setUsername(username);
        setText(text);
        setDate(new Date());
    }

    /**
     * Returns a username of the response.
     *
     * @return The response username.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets a new username to the response.
     * If parameter username is blank, then sets {@code null}.
     *
     * @param username a new text to the response.
     */
    public void setUsername(final String username) {
        this.username = isNotBlank(username) ? username : null;
    }

    /**
     * Returns a text of the response.
     *
     * @return The response text.
     */
    public String getText() {
        return this.text;
    }

    /**
     * Sets a new text to the response.
     * If parameter text is blank, then sets {@code null}.
     *
     * @param text a new text to the response.
     */
    public void setText(final String text) {
        this.text = isNotBlank(text) ? text : null;
    }

    /**
     * Returns a date of the response.
     *
     * @return The response date.
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Sets a new text to the article.
     * If parameter text is blank, then sets new Date().
     *
     * @param date a new date to the article.
     */
    public void setDate(final Date date) {
        this.date = date == null ? new Date() : date;
    }

    /**
     * Returns an article date in string format.
     *
     * @return The article string-date.
     */
    public String getDateToString() {
        return getDateToString(getDate());
    }

    /**
     * Changes response validation.
     */
    public void reverseValidated() {
        setValidated(
                !isValidated()
        );
    }

    /**
     * Statically validates the response.
     * Response is valid if it is a valid model object
     * and it has username and text.
     *
     * @param response a response to validate.
     * @return {@code true} if the response is valid, {@code false} otherwise.
     */
    public static boolean isValidated(final Response response) {
        boolean result = false;
        if (Model.isValidated(response)) {
            result = isNotBlank(response.getUsername()) &&
                    isNotBlank(response.getText());
        }
        return result;
    }
}
