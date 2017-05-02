package ua.com.ecoteh.entity;

import org.apache.log4j.Logger;

import javax.persistence.*;
import java.io.Serializable;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The abstract superclass implements a set of standard methods
 * for working with entity of the {@link Model} class or subclasses.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Model implements IModel, Serializable, Cloneable {

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
    private static final Logger LOGGER = Logger.getLogger(Model.class);

    /**
     * The unique identifier for each object.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * The value of validations of the model.
     */
    @Column(name = "validated")
    private boolean validated;

    /**
     * Default constructor.
     */
    public Model() {
        this.validated = false;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "Model{" +
                "id=" + getId() +
                ", validated=" + isValidated() +
                '}';
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance (newer null).
     */
    @Override
    public Model clone() {
        Model model = null;
        try {
            model = (Model) super.clone();
        } catch (CloneNotSupportedException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return model;
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
     * Returns a hash code value for the object.
     * This method is supported for the benefit
     * of hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    /**
     * Returns a unique identifier of the model.
     *
     * @return The unique identifier.
     */
    @Override
    public long getId() {
        return this.id;
    }

    /**
     * Sets new identifier to the model.
     *
     * @param id a new identifier to the model.
     */
    @Override
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * Sets validations of the model.
     * Sets true if the model is valid, false is invalid.
     *
     * @param validated a validations of the model.
     */
    @Override
    public void setValidated(final boolean validated) {
        this.validated = validated;
    }

    /**
     * Validates the model.
     *
     * @return true if the model is valid, false otherwise.
     */
    @Override
    public boolean isValidated() {
        return this.validated;
    }

    /**
     * Initializes the model.
     * Returns this model with a new copied fields.
     * <pre>
     *     initialize(null) - does nothing, returns this model
     *     initialize(new Model()) - does nothing, returns this
     *     model with a new copied fields
     * </pre>
     *
     * @param model the model to copy.
     * @return This model with a new copied fields (newer null).
     */
    @Override
    public Model initialize(final Model model) {
        if (isNotNull(model)) {
            this.setValidated(model.isValidated());
        }
        return this;
    }
}
