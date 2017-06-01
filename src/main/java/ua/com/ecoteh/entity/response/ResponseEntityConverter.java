package ua.com.ecoteh.entity.response;

import ua.com.ecoteh.entity.model.ModelEntityConverter;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class ResponseEntityConverter extends ModelEntityConverter<Response> {

    private final ResponseEntity entity;

    ResponseEntityConverter(final ResponseEntity entity) {
        this.entity = entity;
    }

    @Override
    protected ResponseBuilder prepareBuilder() {
        final ResponseBuilder builder = new ResponseBuilder();
        if (isNotNull(this.entity)) {
            builder.addId(this.entity.getId())
                    .addValidated(this.entity.isValidated())
                    .addUsername(this.entity.getUsername())
                    .addText(this.entity.getText())
                    .addDate(this.entity.getDate());
        }
        return builder;
    }
}
