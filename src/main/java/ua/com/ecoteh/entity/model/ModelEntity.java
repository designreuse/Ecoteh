package ua.com.ecoteh.entity.model;

import org.apache.log4j.Logger;

import javax.persistence.*;
import java.io.Serializable;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The abstract superclass implements a set of standard methods
 * for working with entity of the {@link ModelEntity} class or subclasses.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class ModelEntity implements Serializable, Cloneable {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The object for logging information.
     */
    private static final Logger LOGGER = Logger.getLogger(ModelEntity.class);

    /**
     * The unique identifier for each object.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * The value of validations of the modelEntity.
     */
    @Column(name = "validated")
    private boolean validated;

    /**
     *
     */
    protected ModelEntity() {
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object (newer null).
     */
    @Override
    public String toString() {
        return "ModelEntity{" +
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
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance (newer null).
     */
    @Override
    public ModelEntity clone() {
        ModelEntity clone = null;
        try {
            clone = (ModelEntity) super.clone();
        } catch (CloneNotSupportedException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return clone;
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
     * Sets new identifier to the modelEntity.
     *
     * @param id a new identifier to the modelEntity.
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * Sets validations of the modelEntity.
     * Sets true if the modelEntity is valid, false is invalid.
     *
     * @param validated a validations of the modelEntity.
     */
    public void setValidated(final boolean validated) {
        this.validated = validated;
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

    public abstract <T extends Model> T convert();
}
