package ua.com.ecoteh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

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
     * The address.
     */
    @Column(name = "address", nullable = false)
    private String address;

    /**
     * The google maps URL.
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
     * @param address a address.
     */
    public Address(final String address) {
        this();
        setAddress(address);
    }

    /**
     * Constructor.
     *
     * @param address    a address
     * @param googleMaps a google maps URL.
     */
    public Address(final String address, final String googleMaps) {
        this(address);
        setGoogleMaps(googleMaps);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
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
     * argument, false otherwise otherwise.
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
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance.
     */
    @Override
    public Address clone() {
        return (Address) super.clone();
    }

    /**
     * Returns a address of the company.
     *
     * @return The company address.
     */
    @Override
    public String getAddress() {
        return this.address;
    }

    /**
     * Sets a new address to the company.
     * If parameter address is blank, then sets empty string..
     *
     * @param address a new address to the company.
     */
    @Override
    public void setAddress(final String address) {
        this.address = isNotBlank(address) ? address : "";
    }

    /**
     * Returns a google maps URL of the company.
     *
     * @return The company google maps URL.
     */
    @Override
    public String getGoogleMaps() {
        return this.googleMaps;
    }

    /**
     * Sets a new google maps URL to the company.
     * If parameter google maps URL is blank, then sets empty string..
     *
     * @param googleMaps a new google maps URL to the company.
     */
    @Override
    public void setGoogleMaps(final String googleMaps) {
        this.googleMaps = isNotBlank(googleMaps) ? googleMaps : "";
    }

    /**
     * Initializes the article.
     *
     * @param address a address to copy.
     * @return The this address with new fields.
     */
    @Override
    public Address initialize(final Address address) {
        if (address != null) {
            super.initialize(address);
            this.setAddress(address.getAddress());
            this.setGoogleMaps(address.getGoogleMaps());
        }
        return this;
    }
}
