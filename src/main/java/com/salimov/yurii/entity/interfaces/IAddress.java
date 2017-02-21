package com.salimov.yurii.entity.interfaces;

import com.salimov.yurii.entity.Address;

/**
 * The interface describes a set of methods
 * for working with objects of the {@link Address} class.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see IModel
 * @see Address
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
     * Initializes the article.
     *
     * @param address a address to copy.
     * @return The this address with new fields.
     */
    Address initialize(final Address address);
}
