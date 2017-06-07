package ua.com.ecoteh.entity.address;

import ua.com.ecoteh.entity.model.ModelEditor;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of methods
 * for editing an objects of the {@link Address} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Address
 */
public final class AddressEditor extends ModelEditor<Address, AddressEditor> {

    /**
     * The address to edit.
     */
    private final Address address;

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
     *
     * @param address the address to edit.
     */
    AddressEditor(final Address address) {
        super(address);
        this.address = address;
    }

    /**
     * Updates and returns a new address.
     *
     * @return The updated address.
     */
    @Override
    public Address update() {
        final AddressBuilder builder = Address.getBuilder();
        builder.addId(getId())
                .addValidated(isValidated())
                .addPostAddress(getPostAddress())
                .addGoogleMaps(getGoogleMaps());
        return builder.build();
    }

    /**
     * Copies the incoming address.
     *
     * @param address the address to copy.
     * @return the address editor.
     */
    @Override
    public AddressEditor copy(final Address address) {
        if (isNotNull(address)) {
            super.copy(address)
                    .addPostAddress(address.getPostAddress())
                    .addGoogleMaps(address.getGoogleMaps());
        }
        return this;
    }

    /**
     * Adds a new post address to the address.
     *
     * @param postAddress the new post address to the address.
     * @return the address editor.
     */
    public AddressEditor addPostAddress(final String postAddress) {
        this.postAddress = postAddress;
        return this;
    }

    /**
     * Adds a new Google maps to the address.
     *
     * @param googleMaps the new Google maps to the address.
     * @return the address editor.
     */
    public AddressEditor addGoogleMaps(final String googleMaps) {
        this.googleMaps = googleMaps;
        return this;
    }

    /**
     * Returns a new post address of the address.
     * Returns the post address if the post address is null.
     *
     * @return The post address (newer null).
     */
    private String getPostAddress() {
        return isNotNull(this.postAddress) ? this.postAddress : this.address.getPostAddress();
    }

    /**
     * Returns a new Google maps of the address.
     * Returns the address Google maps if the Google maps is null.
     *
     * @return The Google maps (newer null).
     */
    private String getGoogleMaps() {
        return isNotNull(this.googleMaps) ? this.googleMaps : this.address.getGoogleMaps();
    }
}
