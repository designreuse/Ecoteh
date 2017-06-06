package ua.com.ecoteh.entity.address;

import ua.com.ecoteh.entity.model.ModelConverter;

/**
 * The class implements a set of methods
 * for converting addresses to address entities.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Address
 * @see AddressEntity
 */
final class AddressConverter extends ModelConverter<Address, AddressEntity> {

    /**
     * The address for converting to address entity.
     */
    private final Address address;

    /**
     * Constructor.
     *
     * @param address the address for converting to address entity.
     */
    AddressConverter(final Address address) {
        super(address);
        this.address = address;
    }

    /**
     * Converts the address and returns a new address entity.
     *
     * @return The converted address entity (newer null).
     */
    @Override
    public AddressEntity convert() {
        final AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(this.address.getId());
        addressEntity.setValidated(this.address.isValidated());
        addressEntity.setPostAddress(this.address.getPostAddress());
        addressEntity.setGoogleMaps(this.address.getGoogleMaps());
        return addressEntity;
    }
}
