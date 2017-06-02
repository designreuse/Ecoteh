package ua.com.ecoteh.entity.address;

import ua.com.ecoteh.entity.model.ModelEntityConverter;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class AddressEntityConverter extends ModelEntityConverter<AddressEntity, Address> {

    AddressEntityConverter(final AddressEntity entity) {
        super(entity);
    }

    @Override
    protected AddressBuilder prepareBuilder() {
        final AddressBuilder builder = new AddressBuilder();
        builder.addId(this.entity.getId())
                .addValidated(this.entity.isValidated());
        return builder;
    }
}
