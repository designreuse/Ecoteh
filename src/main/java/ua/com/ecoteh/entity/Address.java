package ua.com.ecoteh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link Address} class.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Entity
@Table(name = "addresses")
public class Address extends Model implements IAddress {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The mailing address.
     */
    @Column(name = "address", nullable = false)
    private String address;

    /**
     * The Google maps URL.
     */
    @Column(name = "google_maps", nullable = false)
    private String googleMaps;

    /**
     * Default constructor.
     */
    public Address() {
        this.address = "";
        this.googleMaps = "";
    }

    /**
     * Constructor.
     *
     * @param address the mailing address of the new address.
     */
    public Address(final String address) {
        this();
        setAddress(address);
    }

    /**
     * Constructor.
     *
     * @param address    the mailing address of the new address.
     * @param googleMaps the google maps URL of the new address.
     */
    public Address(final String address, final String googleMaps) {
        this(address);
        setGoogleMaps(googleMaps);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object (newer null).
     */
    @Override
    public String toString() {
        return "Address{" + super.toString() +
                ", address='" + getAddress() + '\'' +
                ", googleMaps='" + getGoogleMaps() + '\'' +
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
            result = this.getAddress().equalsIgnoreCase(other.getAddress()) &&
                    this.getGoogleMaps().equalsIgnoreCase(other.getGoogleMaps());
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
        return getAddress().hashCode() + getGoogleMaps().hashCode();
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
     * Returns a mailing address of the address.
     *
     * @return The mailing address or empty string (newer null).
     */
    @Override
    public String getAddress() {
        return this.address;
    }

    /**
     * Sets a new mailing address to the address.
     * If parameter address is blank, then sets empty string.
     * <pre>
     *     setAddress(null) - address = ""
     *     setAddress("") - address = ""
     *     setAddress(" ") - address = ""
     *     setAddress("bob") - address = "bob"
     *     setAddress(" bob ") - address = " bob "
     * </pre>
     *
     * @param address the new mailing address to the address.
     */
    @Override
    public void setAddress(final String address) {
        this.address = isNotEmpty(address) ? address : "";
    }

    /**
     * Returns a Google maps URL of the address.
     *
     * @return The Google maps URL or empty string (newer null).
     */
    @Override
    public String getGoogleMaps() {
        return this.googleMaps;
    }

    /**
     * Sets a new Google maps URL to the address.
     * If parameter google maps URL is blank, then sets empty string.
     * <pre>
     *     setGoogleMaps(null) - googleMaps = ""
     *     setGoogleMaps("") - googleMaps = ""
     *     setGoogleMaps(" ") - googleMaps = ""
     *     setGoogleMaps("bob") - googleMaps = "bob"
     *     setGoogleMaps(" bob ") - googleMaps = "bob"
     * </pre>
     *
     * @param googleMaps the new Google maps URL to the address.
     */
    @Override
    public void setGoogleMaps(final String googleMaps) {
        this.googleMaps = isNotEmpty(googleMaps) ? googleMaps : "";
    }

    /**
     * Initializes the address.
     * Returns this address with a new copied fields.
     * <pre>
     *     initialize(null) - does nothing, returns this address
     *     initialize(new Address()) - does nothing, returns this
     *     address with a new copied fields
     * </pre>
     *
     * @param address the address to copy.
     * @return This address with a new copied fields (newer null).
     */
    @Override
    public Address initialize(final Address address) {
        if (isNotNull(address)) {
            super.initialize(address);
            this.setAddress(address.getAddress());
            this.setGoogleMaps(address.getGoogleMaps());
        }
        return this;
    }
}
