package ua.com.ecoteh.entity.address;

import ua.com.ecoteh.entity.model.ModelEntityConverter;

/**
 * The class implements a set of methods
 * for converting address entities to addresses.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see AddressEntity
 * @see Address
 */
final class AddressEntityConverter extends ModelEntityConverter<AddressEntity, Address> {

    /**
     * The address entity for converting to address.
     */
    private final AddressEntity entity;

    /**
     * Constructor.
     *
     * @param entity the address entity for converting to address.
     */
    AddressEntityConverter(final AddressEntity entity) {
        super(entity);
        this.entity = entity;
    }

    /**
     * Prepares and returns a address builder for creating
     * a new converted address.
     *
     * @return the prepared address builder.
     */
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
