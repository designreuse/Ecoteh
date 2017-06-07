package ua.com.ecoteh.entity.model;

/**
 * The abstract superclass implements a set of standard methods
 * for editing an objects of the {@link Model} class or subclasses.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Model
 */
public abstract class ModelEditor<T extends Model, R extends ModelEditor<T, R>> implements Editor<T> {

    /**
     * The model to edit.
     */
    private final T model;

    /**
     * The new unique identifier of the model.
     */
    private long id;

    /**
     * The new value of validations of the model.
     */
    private int validated;

    /**
     * Constructor.
     *
     * @param model the model to edit.
     */
    protected ModelEditor(final T model) {
        this.model = model;
    }

    /**
     * Copies the incoming model.
     *
     * @param model the model to copy.
     * @return the model editor.
     */
    @Override
    public R copy(final T model) {
        return addId(model.getId())
                .addValidated(model.isValidated());
    }

    /**
     * Adds new identifier to the model.
     *
     * @param id a new identifier to the model.
     * @return the model editor.
     */
    public R addId(final long id) {
        this.id = id;
        return (R) this;
    }

    /**
     * Adds new validated to the model.
     *
     * @param validated a new validated to the model.
     * @return the model editor.
     */
    public R addValidated(final boolean validated) {
        this.validated = validated ? 1 : 2;
        return (R) this;
    }

    /**
     * Sets valid model.
     *
     * @return the model editor.
     */
    public R isValid() {
        return addValidated(true);
    }

    /**
     * Sets not valid model.
     *
     * @return the model editor.
     */
    public R isNotValid() {
        return addValidated(false);
    }

    /**
     * Returns a unique identifier of a new model.
     * Returns the model id if the id is less 0.
     *
     * @return The unique identifier.
     */
    protected long getId() {
        return (this.id > 0) ? this.id : this.model.getId();
    }

    /**
     * Validates the model.
     *
     * @return true if the model is valid, false otherwise.
     */
    protected boolean isValidated() {
        boolean result;
        if (this.validated > 0) {
            result = (this.validated == 1);
        } else {
            result = this.model.isValidated();
        }
        return result;
    }
}
