package com.salimov.yurii.entity.interfaces;

import java.io.Serializable;

/**
 * The interface describes a set of methods
 * for working with objects of
 * the {@link com.salimov.yurii.entity.Model} class.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see com.salimov.yurii.entity.Model
 */
public interface IModel<T extends Number> extends Serializable, Cloneable {

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance.
     */
    IModel clone();
    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return Returns true if this object is the same as the obj
     * argument, otherwise returns false.
     */
    @Override
    boolean equals(final Object object);
    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit
     * of hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    int hashCode();
    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    String toString();
    /**
     * Returns a unique identifier of the model.
     *
     * @return The unique identifier.
     */
    T getId();
    /**
     * Sets new identifier to the model.
     *
     * @param id a new identifier to the model.
     */
    void setId(final T id);

    /**
     * Sets validations of the model.
     *
     * @param validated a validations of the model.
     */
    void setValidated(final boolean validated);

    /**
     * Validates the model.
     *
     * @return {@code true} if the model is valid,
     * {@code false} otherwise.
     */
    boolean isValidated();
}
