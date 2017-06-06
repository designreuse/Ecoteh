package ua.com.ecoteh.entity.address;

import ua.com.ecoteh.entity.model.ModelEditor;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 *
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class AddressEditor extends ModelEditor<Address, AddressEditor> {

    /**
     *
     */
    private final Address address;

    /**
     * The mailing addressEntity.
     */
    private String postAddress;

    /**
     * The Google maps URL.
     */
    private String googleMaps;

    /**
     * Constructor.
     * @param address
     */
    AddressEditor(final Address address) {
        super(address);
        this.address = address;
    }

    @Override
    public Address update() {
        final AddressBuilder builder = Address.getBuilder();
        builder.addId(getId())
                .addValidated(isValidated())
                .addPostAddress(getPostAddress())
                .addGoogleMaps(getGoogleMaps());
        return builder.build();
    }

    @Override
    public AddressEditor copy(final Address address) {
        if (isNotNull(address)) {
            super.copy(address)
                    .addPostAddress(address.getPostAddress())
                    .addGoogleMaps(address.getGoogleMaps());
        }
        return this;
    }

    public AddressEditor addPostAddress(final String postAddress) {
        this.postAddress = postAddress;
        return this;
    }

    public AddressEditor addGoogleMaps(final String googleMaps) {
        this.googleMaps = googleMaps;
        return this;
    }

    private String getPostAddress() {
        return isNotNull(this.postAddress) ? this.postAddress : this.address.getPostAddress();
    }

    private String getGoogleMaps() {
        return isNotNull(this.googleMaps) ? this.googleMaps : this.address.getGoogleMaps();
    }
}
