package ua.com.ecoteh.entity.address;

import ua.com.ecoteh.entity.model.ModelBuilder;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class AddressBuilder extends ModelBuilder<Address, AddressBuilder> {

    /**
     * The mailing addressEntity.
     */
    private String address;

    /**
     * The Google maps URL.
     */
    private String googleMaps;

    AddressBuilder() {
    }

    @Override
    public Address build() {
        return new Address(
                getId(), isValidated(),
                getAddress(), getGoogleMaps()
        );
    }

    public AddressBuilder addAddress(final String address) {
        this.address = address;
        return this;
    }

    public AddressBuilder addGoogleMaps(final String googleMaps) {
        this.googleMaps = googleMaps;
        return this;
    }

    private String getAddress() {
        return isNotEmpty(this.address) ? this.address : "";
    }

    private String getGoogleMaps() {
        return isNotEmpty(this.googleMaps) ? this.googleMaps : "";
    }
}
