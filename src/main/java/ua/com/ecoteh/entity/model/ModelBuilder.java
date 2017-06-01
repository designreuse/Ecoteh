package ua.com.ecoteh.entity.model;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class ModelBuilder<T extends Model, B extends ModelBuilder<T, B>> implements Builder<T> {

    /**
     * The unique identifier for each object.
     */
    private long id;

    /**
     * The value of validations of the model.
     */
    private boolean validated;

    protected ModelBuilder() {
    }

    public B addId(final long id) {
        this.id = id;
        return (B) this;
    }

    public B addValidated(final boolean validated) {
        this.validated = validated;
        return (B) this;
    }

    protected B isValid() {
        return addValidated(true);
    }

    protected B isNotValid() {
        return addValidated(false);
    }

    protected long getId() {
        return (this.id >= 0) ? this.id : 0;
    }

    protected boolean isValidated() {
        return this.validated;
    }
}
