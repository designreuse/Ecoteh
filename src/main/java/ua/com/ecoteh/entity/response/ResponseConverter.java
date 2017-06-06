package ua.com.ecoteh.entity.response;

import ua.com.ecoteh.entity.model.ModelConverter;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class ResponseConverter extends ModelConverter<Response, ResponseEntity> {

    private final Response response;

    /**
     * Constructor.
     * @param response
     */
    ResponseConverter(final Response response) {
        super(response);
        this.response = response;
    }

    @Override
    public ResponseEntity convert() {
        final ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setId(this.response.getId());
        responseEntity.setValidated(this.response.isValidated());
        responseEntity.setUsername(this.response.getUsername());
        responseEntity.setText(this.response.getText());
        responseEntity.setDate(this.response.getDate());
        return null;
    }
}
