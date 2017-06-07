package ua.com.ecoteh.entity.address;

import ua.com.ecoteh.entity.model.ModelBuilder;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * The class implements a set of methods
 * for building an objects of the {@link Address} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Address
 */
public final class AddressBuilder extends ModelBuilder<Address, AddressBuilder> {

    /**
     * The post address.
     */
    private String postAddress;

    /**
     * The Google maps URL.
     */
    private String googleMaps;

    /**
     * Constructor.
     */
    AddressBuilder() {
    }

    /**
     * Builds and returns a new address.
     *
     * @return The new address.
     */
    @Override
    public Address build() {
        return new Address(
                getId(), isValidated(),
                getPostAddress(), getGoogleMaps()
        );
    }

    /**
     * Adds a new post address to a new address.
     *
     * @param postAddress the new post address to a new address.
     * @return the address builder.
     */
    public AddressBuilder addPostAddress(final String postAddress) {
        this.postAddress = postAddress;
        return this;
    }

    /**
     * Adds a new Google maps to a new address.
     *
     * @param googleMaps the new Google maps to a new address.
     * @return the address builder.
     */
    public AddressBuilder addGoogleMaps(final String googleMaps) {
        this.googleMaps = googleMaps;
        return this;
    }

    /**
     * Returns a post address of a new address.
     * Returns an empty string if the post address is null or empty.
     *
     * @return The post address or empty string (newer null).
     */
    private String getPostAddress() {
        return isNotEmpty(this.postAddress) ? this.postAddress : "";
    }

    /**
     * Returns a Google maps of a new address.
     * Returns an empty string if the Google maps is null or empty.
     *
     * @return The address Google maps or empty string (newer null).
     */
    private String getGoogleMaps() {
        return isNotEmpty(this.googleMaps) ? this.googleMaps : "";
    }
}
