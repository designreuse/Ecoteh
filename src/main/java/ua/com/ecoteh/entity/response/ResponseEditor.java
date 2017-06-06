package ua.com.ecoteh.entity.response;

import ua.com.ecoteh.entity.model.ModelEditor;

import java.util.Date;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class ResponseEditor extends ModelEditor<Response, ResponseEditor> {

    /**
     *
     */
    private final Response response;

    /**
     * The username of a responseEntity.
     */
    private String username;

    /**
     * The username of a responseEntity.
     */
    private String text;

    /**
     * The date of a responseEntity.
     */
    private Date date;

    /**
     * Constructor.
     * @param response
     */
    ResponseEditor(final Response response) {
        super(response);
        this.response = response;
    }

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

    public ResponseEditor addUsername(final String username) {
        this.username = username;
        return this;
    }

    public ResponseEditor addText(final String text) {
        this.text = text;
        return this;
    }

    public ResponseEditor addDate(final Date date) {
        this.date = date;
        return this;
    }

    private String getUsername() {
        return isNotNull(this.username) ? this.username : this.response.getUsername();
    }

    private String getText() {
        return isNotNull(this.text) ? this.text : this.response.getText();
    }

    private Date getDate() {
        return isNotNull(this.date) ? this.date : this.response.getDate();
    }
}
