package ua.com.ecoteh.entity.model;

import java.io.Serializable;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class Model implements Serializable {

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
     * The value of validations of the modelEntity.
     */
    private final boolean validated;

    /**
     *
     * @param id
     * @param validated
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
        return "Model{" +
                "id=" + this.id +
                ", validated=" + this.validated +
                '}';
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
        return isNotNull(object) && (super.equals(object) || (getClass() == object.getClass()));
    }

    /**
     * Returns a unique identifier of the modelEntity.
     *
     * @return The unique identifier.
     */
    public long getId() {
        return this.id;
    }

    /**
     * Validates the modelEntity.
     *
     * @return true if the modelEntity is valid, false otherwise.
     */
    public boolean isValidated() {
        return this.validated;
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit
     * of hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public abstract int hashCode();

    public abstract<E extends ModelEntity> E convert();
}
