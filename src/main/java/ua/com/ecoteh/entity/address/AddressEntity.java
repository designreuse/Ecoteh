package ua.com.ecoteh.entity.address;

import ua.com.ecoteh.entity.model.ModelEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link AddressEntity} class.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
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
     * The mailing addressEntity.
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
    public AddressEntity() {
        this.address = "";
        this.googleMaps = "";
    }

    /**
     * Constructor.
     *
     * @param address the mailing addressEntity of the new addressEntity.
     */
    public AddressEntity(final String address) {
        this();
        setAddress(address);
    }

    /**
     * Constructor.
     *
     * @param address    the mailing addressEntity of the new addressEntity.
     * @param googleMaps the google maps URL of the new addressEntity.
     */
    public AddressEntity(final String address, final String googleMaps) {
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
        return "AddressEntity{" + super.toString() +
                ", addressEntity='" + getAddress() + '\'' +
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
            final AddressEntity other = (AddressEntity) object;
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
    public AddressEntity clone() {
        return (AddressEntity) super.clone();
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
     * Sets a new mailing addressEntity to the addressEntity.
     * If parameter addressEntity is blank, then sets empty string.
     * <pre>
     *     setAddressEntity(null) - addressEntity = ""
     *     setAddressEntity("") - addressEntity = ""
     *     setAddressEntity(" ") - addressEntity = ""
     *     setAddressEntity("bob") - addressEntity = "bob"
     *     setAddressEntity(" bob ") - addressEntity = " bob "
     * </pre>
     *
     * @param address the new mailing addressEntity to the addressEntity.
     */
    public void setAddress(final String address) {
        this.address = isNotEmpty(address) ? address : "";
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
     * Sets a new Google maps URL to the addressEntity.
     * If parameter google maps URL is blank, then sets empty string.
     * <pre>
     *     setGoogleMaps(null) - googleMaps = ""
     *     setGoogleMaps("") - googleMaps = ""
     *     setGoogleMaps(" ") - googleMaps = ""
     *     setGoogleMaps("bob") - googleMaps = "bob"
     *     setGoogleMaps(" bob ") - googleMaps = "bob"
     * </pre>
     *
     * @param googleMaps the new Google maps URL to the addressEntity.
     */
    public void setGoogleMaps(final String googleMaps) {
        this.googleMaps = isNotEmpty(googleMaps) ? googleMaps : "";
    }

    /**
     * Initializes the addressEntity.
     * Returns this addressEntity with a new copied fields.
     * <pre>
     *     initialize(null) - does nothing, returns this addressEntity
     *     initialize(new AddressEntity()) - does nothing, returns this
     *     addressEntity with a new copied fields
     * </pre>
     *
     * @param addressEntity the addressEntity to copy.
     * @return This addressEntity with a new copied fields (newer null).
     */
    public AddressEntity initialize(final AddressEntity addressEntity) {
        if (isNotNull(addressEntity)) {
            super.initialize(addressEntity);
            this.setAddress(addressEntity.getAddress());
            this.setGoogleMaps(addressEntity.getGoogleMaps());
        }
        return this;
    }

    /**
     *
     * @return
     */
    public Address convert() {
        return new AddressEntityConverter(this).convert();
    }
}
