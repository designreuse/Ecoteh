package com.salimov.yurii.entity;

import com.salimov.yurii.entity.interfaces.IAddress;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Entity
@Table(name = "addresses")
public class Address extends Model<Long> implements IAddress<Long> {

    /**
     * The address of a company.
     */
    @Column(name = "address")
    private String address;

    /**
     * The google maps url of a company.
     */
    @Column(name = "google_maps")
    private String googleMaps;

    /**
     * Default constructor.
     */
    public Address() {

    }

    public Address(final String address) {
        this();
        setAddress(address);
    }

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
        return "Address{" +
                "address='" + getAddress() + '\'' +
                ", googleMaps='" + getGoogleMaps() + '\'' +
                '}';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return {@code true} if this object is the same as the object
     * argument, {@code false} otherwise otherwise.
     */
    @Override
    public boolean equals(Object object) {
        boolean result = super.equals(object);
        if (result) {
            final Address other = (Address) object;
            result = (isNotBlank(this.address) ? this.address.equalsIgnoreCase(other.address) :
                    isBlank(other.address)) &&
                    (isNotBlank(this.googleMaps) ? this.googleMaps.equalsIgnoreCase(other.googleMaps) :
                            isBlank(other.googleMaps));
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
        return (this.address != null ? this.address.hashCode() : 0)
                + (this.googleMaps != null ? this.googleMaps.hashCode() : 0);
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
     * If parameter address is blank, then sets {@code null}.
     *
     * @param address a new address to the company.
     */
    @Override
    public void setAddress(final String address) {
        this.address = isNotBlank(address) ? address : null;
    }

    /**
     * Returns a google maps url of the company.
     *
     * @return The company google maps url.
     */
    @Override
    public String getGoogleMaps() {
        return this.googleMaps;
    }

    /**
     * Sets a new google maps url to the company.
     * If parameter google maps url is blank, then sets {@code null}.
     *
     * @param googleMaps a new google maps url to the company.
     */
    @Override
    public void setGoogleMaps(final String googleMaps) {
        this.googleMaps = isNotBlank(googleMaps) ? googleMaps : null;
    }

    /**
     * @param address
     * @return
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
