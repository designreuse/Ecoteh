package ua.com.ecoteh.entity.response;

import ua.com.ecoteh.entity.model.ModelConverter;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class ResponseConverter extends ModelConverter<Response, ResponseEntity> {

    ResponseConverter(final Response model) {
        super(model);
    }

    @Override
    public ResponseEntity convert() {
        final ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setId(this.model.getId());
        responseEntity.setValidated(this.model.isValidated());
        responseEntity.setUsername(this.model.getUsername());
        responseEntity.setText(this.model.getText());
        responseEntity.setDate(this.model.getDate());
        return null;
    }
}
