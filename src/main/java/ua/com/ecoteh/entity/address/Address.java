package ua.com.ecoteh.entity.address;

import ua.com.ecoteh.entity.model.Model;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
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
     * The mailing addressEntity.
     */
    private final String address;

    /**
     * The Google maps URL.
     */
    private final String googleMaps;

    /**
     *
     * @param id
     * @param validated
     * @param address
     * @param googleMaps
     */
    Address(
            final long id,final  boolean validated,
            final String address, final String googleMaps
    ) {
        super(id, validated);
        this.address = address;
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
                ", address='" + this.address + '\'' +
                ", googleMaps='" + this.googleMaps + '\'' +
                '}';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return true if this object is the same as the object
     * argument, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        boolean result = super.equals(object);
        if (result) {
            final Address other = (Address) object;
            result = this.address.equalsIgnoreCase(other.address) &&
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
        return this.address.hashCode() + this.googleMaps.hashCode();
    }

    /**
     * Returns a mailing addressEntity of the addressEntity.
     *
     * @return The mailing addressEntity or empty string (newer null).
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Returns a Google maps URL of the addressEntity.
     *
     * @return The Google maps URL or empty string (newer null).
     */
    public String getGoogleMaps() {
        return this.googleMaps;
    }

    /**
     *
     * @return
     */
    public AddressEntity convert() {
        return new AddressConverter(this).convert();
    }

    /**
     *
     * @return
     */
    public static AddressBuilder getBuilder() {
        return new AddressBuilder();
    }
}
