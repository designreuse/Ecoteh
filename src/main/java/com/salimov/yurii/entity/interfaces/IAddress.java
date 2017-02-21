package com.salimov.yurii.entity.interfaces;

import com.salimov.yurii.entity.Address;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface IAddress extends IModel {

    /**
     * Returns a address of the company.
     *
     * @return The company address.
     */
    String getAddress();

    /**
     * Sets a new address to the company.
     *
     * @param address a new address to the company.
     */
    void setAddress(final String address);

    /**
     * Returns a google maps url of the company.
     *
     * @return The company google maps url.
     */
    String getGoogleMaps();

    /**
     * Sets a new google maps url to the company.
     *
     * @param googleMaps a new google maps url to the company.
     */
    void setGoogleMaps(final String googleMaps);

    /**
     * @param address
     * @return
     */
    Address initialize(final Address address);
}
