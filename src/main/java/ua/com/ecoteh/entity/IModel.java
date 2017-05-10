package ua.com.ecoteh.entity;

import java.io.Serializable;

/**
 * The interface describes a set of methods
 * for working with objects of
 * the {@link Model} class.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface IModel extends Serializable, Cloneable {

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
    boolean equals(Object object);

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
    long getId();

    /**
     * Sets new identifier to the model.
     *
     * @param id the new identifier to the model.
     */
    void setId(long id);

    /**
     * Sets validations of the model.
     *
     * @param validated the validations of the model.
     */
    void setValidated(boolean validated);

    /**
     * Validates the model.
     *
     * @return true if the model is valid, false otherwise.
     */
    boolean isValidated();

    /**
     * Initializes the model.
     * Returns this model with a new copied fields.
     *
     * @param model the model to copy.
     * @return This model with a new copied fields.
     */
    Model initialize(Model model);
}
