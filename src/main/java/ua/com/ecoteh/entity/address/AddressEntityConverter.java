package ua.com.ecoteh.entity.address;

import ua.com.ecoteh.entity.model.ModelEntityConverter;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class AddressEntityConverter extends ModelEntityConverter<AddressEntity, Address> {

    private final AddressEntity entity;

    /**
     * Constructor.
     * @param entity
     */
    AddressEntityConverter(final AddressEntity entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    protected AddressBuilder prepareBuilder() {
        final AddressBuilder builder = Address.getBuilder();
        builder.addId(this.entity.getId())
                .addValidated(this.entity.isValidated())
                .addPostAddress(this.entity.getPostAddress())
                .addGoogleMaps(this.entity.getGoogleMaps());
        return builder;
    }
}
