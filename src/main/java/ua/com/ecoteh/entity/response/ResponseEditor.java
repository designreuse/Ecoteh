package ua.com.ecoteh.entity.response;

import ua.com.ecoteh.entity.model.ModelEditor;

import java.util.Date;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of methods
 * for editing an objects of the {@link Response} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Response
 */
public final class ResponseEditor extends ModelEditor<Response, ResponseEditor> {

    /**
     * The response to edit.
     */
    private final Response response;

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
     *
     * @param response the response to edit.
     */
    ResponseEditor(final Response response) {
        super(response);
        this.response = response;
    }

    /**
     * Updates and returns a new response.
     *
     * @return The updated response.
     */
    @Override
    public Response update() {
        final ResponseBuilder builder = Response.getBuilder();
        builder.addId(getId())
                .addValidated(isValidated())
                .addUsername(getUsername())
                .addText(getText())
                .addDate(getDate());
        return builder.build();
    }

    /**
     * Copies the incoming response.
     *
     * @param response the response to copy.
     * @return the response editor.
     */
    @Override
    public ResponseEditor copy(final Response response) {
        if (isNotNull(response)) {
            super.copy(response)
                    .addUsername(response.getUsername())
                    .addText(response.getText())
                    .addDate(response.getDate());
        }
        return this;
    }

    /**
     * Adds a new username to the response.
     *
     * @param username the new username to the response.
     * @return the response editor.
     */
    public ResponseEditor addUsername(final String username) {
        this.username = username;
        return this;
    }

    /**
     * Adds a new text to the response.
     *
     * @param text the new text to the response.
     * @return the response editor.
     */
    public ResponseEditor addText(final String text) {
        this.text = text;
        return this;
    }

    /**
     * Adds a new date to the response.
     *
     * @param date the new date to the response.
     * @return the response editor.
     */
    public ResponseEditor addDate(final Date date) {
        this.date = date;
        return this;
    }

    /**
     * Returns a new username of the address.
     * Returns the response username if the username is null.
     *
     * @return The username (newer null).
     */
    private String getUsername() {
        return isNotNull(this.username) ? this.username : this.response.getUsername();
    }

    /**
     * Returns a new text of the address.
     * Returns the response text if the text is null.
     *
     * @return The text (newer null).
     */
    private String getText() {
        return isNotNull(this.text) ? this.text : this.response.getText();
    }

    /**
     * Returns a new date of the address.
     * Returns the response date if the date is null.
     *
     * @return The date (newer null).
     */
    private Date getDate() {
        return isNotNull(this.date) ? this.date : this.response.getDate();
    }
}
