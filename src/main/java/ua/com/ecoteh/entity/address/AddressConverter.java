package ua.com.ecoteh.entity.address;

import ua.com.ecoteh.entity.model.ModelConverter;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class AddressConverter extends ModelConverter<AddressEntity> {

    private final Address address;

    AddressConverter(final Address address) {
        this.address = address;
    }

    @Override
    public AddressEntity convert() {
        Address address;
        if (isNotNull(this.address)) {
            address = this.address;
        } else {
            address = new AddressBuilder().build();
        }
        return convertToEntity(address);
    }

    private AddressEntity convertToEntity(final Address address) {
        final AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(address.getId());
        addressEntity.setValidated(address.isValidated());
        addressEntity.setAddress(address.getAddress());
        addressEntity.setGoogleMaps(address.getGoogleMaps());
        return addressEntity;
    }
}
