package ua.com.ecoteh.entity.response;

import ua.com.ecoteh.entity.model.ModelBuilder;

import java.util.Date;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of methods
 * for building an objects of the {@link Response} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Response
 */
public final class ResponseBuilder extends ModelBuilder<Response, ResponseBuilder> {

    /**
     * The username of a new response.
     */
    private String username;

    /**
     * The username of a new response.
     */
    private String text;

    /**
     * The date of a new response.
     */
    private Date date;

    /**
     * Constructor.
     */
    ResponseBuilder() {
    }

    /**
     * Builds and returns a new response.
     *
     * @return The new response.
     * @see Response
     */
    @Override
    public Response build() {
        return new Response(
                getId(), isValidated(),
                getUsername(), getText(),
                getDate()
        );
    }

    /**
     * Adds a new username to a new response.
     *
     * @param username the new username to a new response.
     * @return the response builder.
     */
    public ResponseBuilder addUsername(final String username) {
        this.username = username;
        return this;
    }

    /**
     * Adds a new text to a new response.
     *
     * @param text the new text to a new response.
     * @return the response builder.
     */
    public ResponseBuilder addText(final String text) {
        this.text = text;
        return this;
    }

    /**
     * Adds a new username to a new response.
     *
     * @param date the new date to a new response.
     * @return the response builder.
     */
    public ResponseBuilder addDate(final Date date) {
        this.date = date;
        return this;
    }

    /**
     * Returns a username of a new response.
     * Returns an empty string if the username is null or empty.
     *
     * @return The username or empty string (newer null).
     */
    private String getUsername() {
        return isNotEmpty(this.username) ? this.username : "";
    }

    /**
     * Returns a text of a new response.
     * Returns an empty string if the text is null or empty.
     *
     * @return The text or empty string (newer null).
     */
    private String getText() {
        return isNotEmpty(this.text) ? this.text : "";
    }

    /**
     * Returns a date of a new response.
     * Returns a new date if the date is null.
     *
     * @return The response date.
     */
    private Date getDate() {
        return isNotNull(this.date) ? this.date : new Date();
    }
}
