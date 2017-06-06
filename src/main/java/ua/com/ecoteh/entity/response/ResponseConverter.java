package ua.com.ecoteh.entity.response;

import ua.com.ecoteh.entity.model.ModelConverter;

/**
 * The class implements a set of methods
 * for converting responses to response entities.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Response
 * @see ResponseEntity
 */
final class ResponseConverter extends ModelConverter<Response, ResponseEntity> {

    /**
     * The response for converting to response entity.
     */
    private final Response response;

    /**
     * Constructor.
     *
     * @param response the response for converting to response entity.
     */
    ResponseConverter(final Response response) {
        super(response);
        this.response = response;
    }

    /**
     * Converts the response and returns a new response entity.
     *
     * @return The converted response entity (newer null).
     */
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
