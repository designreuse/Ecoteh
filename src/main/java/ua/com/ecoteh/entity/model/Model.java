package ua.com.ecoteh.entity.model;

import java.io.Serializable;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The abstract superclass implements a set of standard methods
 * for working with objects of the {@link Model} class or subclasses.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see ModelEntity
 */
public abstract class Model implements Serializable, Cloneable {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for each object.
     */
    private final long id;

    /**
     * The value of validations of the model.
     */
    private final boolean validated;

    /**
     * Constructor.
     *
     * @param id        the unique identifier for each model.
     * @param validated the validations of a new model.
     */
    public Model(final long id, final boolean validated) {
        this.id = id;
        this.validated = validated;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object (newer null).
     */
    @Override
    public String toString() {
        return "Model{id=" + this.id +
                ", validated=" + this.validated + '}';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return Returns true if this object is the same as the obj
     * argument, otherwise returns false.
     */
    @Override
    public boolean equals(final Object object) {
        return isNotNull(object) &&
                (super.equals(object) || (getClass() == object.getClass()));
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance (newer null).
     */
    @Override
    public Model clone() {
        Model clone = null;
        try {
            clone = (Model) super.clone();
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        return clone;
    }

    /**
     * Returns a unique identifier of the model.
     *
     * @return The unique identifier.
     */
    public long getId() {
        return this.id;
    }

    /**
     * Validates the model.
     *
     * @return true if the model is valid, false otherwise.
     */
    public boolean isValidated() {
        return this.validated;
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit
     * of hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object (newer null).
     */
    @Override
    public abstract int hashCode();

    /**
     * Returns a editor for updating this model.
     *
     * @return the model editor (newer null).
     */
    public abstract ModelEditor getEditor();

    /**
     * Converts this object and returns an entity
     * of the {@link ModelEntity} class.
     *
     * @return The entity of the {@link ModelEntity} class.
     */
    public abstract ModelEntity convert();
}
