package ua.com.ecoteh.entity.address;

import ua.com.ecoteh.entity.model.ModelConverter;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class AddressConverter extends ModelConverter<Address, AddressEntity> {

    private final Address address;

    /**
     * Constructor.
     * @param address
     */
    AddressConverter(final Address address) {
        super(address);
        this.address = address;
    }

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
