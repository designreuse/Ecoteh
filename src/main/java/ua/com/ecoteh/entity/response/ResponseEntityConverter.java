package ua.com.ecoteh.entity.response;

import ua.com.ecoteh.entity.model.ModelEntityConverter;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class ResponseEntityConverter extends ModelEntityConverter<ResponseEntity, Response> {

    private final ResponseEntity entity;

    /**
     * Constructor.
     * @param entity
     */
    ResponseEntityConverter(final ResponseEntity entity) {
        super(entity);
        this.entity = entity;
    }

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
