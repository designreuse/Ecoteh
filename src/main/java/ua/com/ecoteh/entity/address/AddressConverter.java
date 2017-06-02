package ua.com.ecoteh.entity.address;

import ua.com.ecoteh.entity.model.ModelConverter;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class AddressConverter extends ModelConverter<Address, AddressEntity> {

    AddressConverter(final Address model) {
        super(model);
    }

    @Override
    public AddressEntity convert() {
        final AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(this.model.getId());
        addressEntity.setValidated(this.model.isValidated());
        addressEntity.setAddress(this.model.getAddress());
        addressEntity.setGoogleMaps(this.model.getGoogleMaps());
        return addressEntity;
    }
}
