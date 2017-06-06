package ua.com.ecoteh.entity.response;

import ua.com.ecoteh.entity.model.ModelEntityConverter;

/**
 * The class implements a set of methods
 * for converting response entities to responses.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see ResponseEntity
 * @see Response
 */
final class ResponseEntityConverter extends ModelEntityConverter<ResponseEntity, Response> {

    /**
     * The response entity for converting to response.
     */
    private final ResponseEntity entity;

    /**
     * Constructor.
     *
     * @param entity the response entity for converting to response.
     */
    ResponseEntityConverter(final ResponseEntity entity) {
        super(entity);
        this.entity = entity;
    }

    /**
     * Prepares and returns a response builder for creating
     * a new converted response.
     *
     * @return the prepared response builder.
     */
    @Override
    protected ResponseBuilder prepareBuilder() {
        final ResponseBuilder builder = Response.getBuilder();
        builder.addId(this.entity.getId())
                .addValidated(this.entity.isValidated())
                .addUsername(this.entity.getUsername())
                .addText(this.entity.getText())
                .addDate(this.entity.getDate());
        return builder;
    }
}
