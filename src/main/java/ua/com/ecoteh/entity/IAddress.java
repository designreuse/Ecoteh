package ua.com.ecoteh.entity;

/**
 * The interface describes a set of methods
 * for working with objects of the {@link Address} class.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
public interface IAddress extends IModel {

    /**
     * Returns a mailing address of the address.
     *
     * @return The mailing address.
     */
    String getAddress();

    /**
     * Sets a new mailing address to the address.
     *
     * @param address the new mailing address to the address.
     */
    void setAddress(String address);

    /**
     * Returns a google maps URL of the address.
     *
     * @return The address google maps URL.
     */
    String getGoogleMaps();

    /**
     * Sets a new Google maps URL to the address.
     *
     * @param googleMaps the new Google maps URL to the address.
     */
    void setGoogleMaps(String googleMaps);

    /**
     * Initializes the address.
     * Returns this address with a new copied fields.
     *
     * @param address the address to copy.
     * @return This address with new fields.
     */
    Address initialize(Address address);
}
