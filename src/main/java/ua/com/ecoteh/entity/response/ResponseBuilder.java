package ua.com.ecoteh.entity.response;

import ua.com.ecoteh.entity.model.ModelBuilder;

import java.util.Date;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class ResponseBuilder extends ModelBuilder<Response, ResponseBuilder> {

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

    ResponseBuilder() {
    }

    @Override
    public Response build() {
        return new Response(
                getId(), isValidated(),
                getUsername(), getText(),
                getDate()
        );
    }

    public ResponseBuilder addUsername(String username) {
        this.username = username;
        return this;
    }

    public ResponseBuilder addText(String text) {
        this.text = text;
        return this;
    }

    public ResponseBuilder addDate(Date date) {
        this.date = date;
        return this;
    }

    private String getUsername() {
        return isNotEmpty(this.username) ? this.username : "";
    }

    private String getText() {
        return isNotEmpty(this.text) ? this.text : "";
    }

    private Date getDate() {
        return isNotNull(this.date) ? this.date : new Date();
    }
}
