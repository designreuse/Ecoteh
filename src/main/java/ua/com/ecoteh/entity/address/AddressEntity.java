package ua.com.ecoteh.entity.address;

import ua.com.ecoteh.entity.model.ModelEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link AddressEntity} class.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @see Address
 */
@Entity
@Table(name = "addresses")
public class AddressEntity extends ModelEntity {

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
    @Column(name = "post_address", nullable = false)
    private String postAddress;

    /**
     * The Google maps URL.
     */
    @Column(name = "google_maps", nullable = false)
    private String googleMaps;

    /**
     * Constructor.
     */
    protected AddressEntity() {
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object (newer null).
     */
    @Override
    public String toString() {
        return "AddressEntity{" + super.toString() +
                ", postAddress='" + this.postAddress + '\'' +
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
    public boolean equals(final Object object) {
        boolean result = super.equals(object);
        if (result) {
            final AddressEntity other = (AddressEntity) object;
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
    public AddressEntity clone() {
        return (AddressEntity) super.clone();
    }

    /**
     * Returns an post address of the address entity.
     *
     * @return The mailing addressEntity or empty string (newer null).
     */
    public String getPostAddress() {
        return this.postAddress;
    }

    /**
     * Sets a new post address to the address entity.
     *
     * @param postAddress the post address to the address entity.
     */
    public void setPostAddress(final String postAddress) {
        this.postAddress = postAddress;
    }

    /**
     * Returns a Google maps URL of the address entity.
     *
     * @return The Google maps URL or empty string (newer null).
     */
    public String getGoogleMaps() {
        return this.googleMaps;
    }

    /**
     * Sets a new Google maps URL to the address entity.
     *
     * @param googleMaps the new Google maps URL to the address entity.
     */
    public void setGoogleMaps(final String googleMaps) {
        this.googleMaps = googleMaps;
    }

    /**
     * Converts this entity and returns a object
     * of the {@link Address} class.
     *
     * @return The object of the {@link Address} class (newer null).
     * @see AddressEntityConverter
     */
    @Override
    public Address convert() {
        return new AddressEntityConverter(this).convert();
    }
}
