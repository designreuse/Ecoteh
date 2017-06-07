package ua.com.ecoteh.entity.model;

/**
 * The abstract superclass implements a set of standard methods
 * for building an objects of the {@link Model} class or subclasses.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Model
 */
public abstract class ModelBuilder<T extends Model, B extends ModelBuilder<T, B>> implements Builder<T> {

    /**
     * The unique identifier for each object.
     */
    private long id;

    /**
     * The value of validations of a model.
     */
    private boolean validated;

    /**
     * Constructor.
     */
    protected ModelBuilder() {
    }

    /**
     * Adds new identifier to a new model.
     *
     * @param id a new identifier to a new model.
     * @return the model builder.
     */
    public B addId(final long id) {
        this.id = id;
        return (B) this;
    }

    /**
     * Adds a new validated to a new model.
     * Adds true if the model is valid, false is invalid.
     *
     * @param validated the validations of the model.
     * @return the model builder.
     */
    public B addValidated(final boolean validated) {
        this.validated = validated;
        return (B) this;
    }

    /**
     * Adds valid model.
     *
     * @return the model builder.
     */
    public B isValid() {
        return addValidated(true);
    }

    /**
     * Adds invalid model.
     *
     * @return the model builder.
     */
    public B isNotValid() {
        return addValidated(false);
    }

    /**
     * Returns a unique identifier of a new model.
     * Returns 0 if the id is less 0.
     *
     * @return The unique identifier.
     */
    protected long getId() {
        return (this.id >= 0) ? this.id : 0;
    }

    /**
     * Validates the model.
     *
     * @return true if the model is valid, false otherwise.
     */
    protected boolean isValidated() {
        return this.validated;
    }
}
