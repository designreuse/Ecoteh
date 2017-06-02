package ua.com.ecoteh.entity.response;

import ua.com.ecoteh.entity.model.ModelEntityConverter;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class ResponseEntityConverter extends ModelEntityConverter<ResponseEntity, Response> {

    ResponseEntityConverter(final ResponseEntity entity) {
        super(entity);
    }

    @Override
    protected ResponseBuilder prepareBuilder() {
        final ResponseBuilder builder = new ResponseBuilder();
        builder.addId(this.entity.getId())
                .addValidated(this.entity.isValidated())
                .addUsername(this.entity.getUsername())
                .addText(this.entity.getText())
                .addDate(this.entity.getDate());
        return builder;
    }
}
