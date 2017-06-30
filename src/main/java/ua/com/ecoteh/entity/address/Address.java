package ua.com.ecoteh.entity.address;

import ua.com.ecoteh.entity.model.Model;

/**
 * The class implements a set of methods for working
 * with objects of the {@link Address} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see AddressEntity
 */
public final class Address extends Model {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The post address.
     */
    private final String postAddress;

    /**
     * The Google maps URL.
     */
    private final String googleMaps;

    /**
     * Constructor.
     *
     * @param id          the unique identifier for each address.
     * @param validated   the validations of a new address.
     * @param postAddress the post address of a new address.
     * @param googleMaps  the Google maps of a new address.
     */
    Address(
            final long id, final boolean validated,
            final String postAddress, final String googleMaps
    ) {
        super(id, validated);
        this.postAddress = postAddress;
        this.googleMaps = googleMaps;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object (newer null).
     */
    @Override
    public String toString() {
        return "Address{" + super.toString() +
                ", postAddress='" + this.postAddress + '\'' +
                ", googleMaps='" + this.googleMaps + '\'' +
                '}';
    }

    /**
     * Returns a string representation of the object to search.
     *
     * @return A string representation of the object (newer null).
     */
    @Override
    public String toSearch() {
        return " " + this.postAddress + " ";
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return true if this object is the same as the object
     * argument, false otherwise.
     */
    @Override
    public boolean equals(final Object object) {
        boolean result = super.equals(object);
        if (result) {
            final Address other = (Address) object;
            result = this.postAddress.equalsIgnoreCase(other.postAddress) &&
                    this.googleMaps.equalsIgnoreCase(other.googleMaps);
        }
        return result;
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit
     * of hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return this.postAddress.hashCode() + this.googleMaps.hashCode();
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance (newer null).
     */
    @Override
    public Address clone() {
        return (Address) super.clone();
    }

    /**
     * Returns a post address of the object.
     *
     * @return The post address or empty string (newer null).
     */
    public String getPostAddress() {
        return this.postAddress;
    }

    /**
     * Returns a Google maps URL of the address.
     *
     * @return The Google maps URL or empty string (newer null).
     */
    public String getGoogleMaps() {
        return this.googleMaps;
    }

    /**
     * Converts this object and returns an entity
     * of the {@link AddressEntity} class.
     *
     * @return The entity of the {@link AddressEntity} class (newer null).
     * @see AddressConverter
     */
    @Override
    public AddressEntity convert() {
        return new AddressConverter(this).convert();
    }

    /**
     * Returns a editor for updating this address.
     *
     * @return the address editor (newer null).
     */
    @Override
    public AddressEditor getEditor() {
        return new AddressEditor(this);
    }

    /**
     * Returns a builder for creating a new address.
     *
     * @return the address builder (newer null).
     */
    public static AddressBuilder getBuilder() {
        return new AddressBuilder();
    }
}
