package ua.com.ecoteh.entity.address;

import ua.com.ecoteh.entity.model.ModelEntityConverter;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class AddressEntityConverter extends ModelEntityConverter<Address> {

    private final AddressEntity entity;

    AddressEntityConverter(final AddressEntity entity) {
        this.entity = entity;
    }

    @Override
    protected AddressBuilder prepareBuilder() {
        final AddressBuilder builder = new AddressBuilder();
        if (isNotNull(this.entity)) {
            builder.addId(this.entity.getId())
                    .addValidated(this.entity.isValidated());
        }
        return builder;
    }
}
